package fr.pharma.eclipse.service.essai.impl;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.util.Assert;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.dao.search.EssaiSearchDao;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.essai.EssaiSearchCriteria;
import fr.pharma.eclipse.domain.dto.EssaiDTO;
import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.enums.TypeHistoriqueEssai;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.enums.evenement.ResultatVisite;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.etat.DetailEtatEssai;
import fr.pharma.eclipse.domain.model.essai.detail.faisabilite.embedded.InfosConclusionFaisabilite;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.DetailDonneesPharma;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.domain.model.localisation.Site;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;
import fr.pharma.eclipse.factory.common.BeanObjectWithParentFactory;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.predicate.essai.EssaiActifPredicate;
import fr.pharma.eclipse.predicate.pharmacie.PharmacieEssaiPredicate;
import fr.pharma.eclipse.service.acl.AclService;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.essai.updator.EssaiBeforeSaveUpdator;
import fr.pharma.eclipse.service.evenement.EvenementService;
import fr.pharma.eclipse.service.helper.DroitAccesHelper;
import fr.pharma.eclipse.service.helper.common.BeanHelper;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.service.user.UserService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.introspection.BeanTool;
import fr.pharma.eclipse.validator.save.SaveValidator;

/**
 * Classe d'implémentation du service de gestion de essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiServiceImpl extends GenericServiceImpl<Essai> implements EssaiService, BeanFactoryAware {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2938823438001362504L;

    /**
     * Helper.
     */
    @Resource(name = "commonServicesHelper")
    private BeanHelper<Essai> beanHelper;

    /**
     * Fabrique d'objets Spring.
     */
    private BeanFactory beanFactory;

    /**
     * Updators à appliquer avant la sauvegarde d'un essai.
     */
    private List<EssaiBeforeSaveUpdator> beforeSaveUpdators;

    /**
     * Liste des validateurs à appliquer lors de la la sauvegarde.
     */
    private List<SaveValidator<Essai>> saveValidators;

    /**
     * Service de gestion des pharmacies.
     */
    @Resource
    private PharmacieService pharmacieService;

    /**
     * Factory de DetailEtatEssai.
     */
    @Resource
    private SuiviFactory<DetailEtatEssai> detailEtatEssaiFactory;

    /**
     * Service utilisateur.
     */
    @Resource
    private DroitAccesHelper droitAccesHelper;

    /** Service evenement. */
    @Resource
    private EvenementService evenementService;

    /**
     * Service de gestion des utilisateurs.
     */
    @Resource
    private UserService userService;

    /**
     * Dao de recherhce des essais.
     */
    private EssaiSearchDao essaiSearchDao;

    /**
     * Service de gestion des acls.
     */
    @Resource
    private AclService aclService;

    /**
     * Map des fabriques d'historiques pour les différents types d'historiques
     * de l'essai.<br>
     * - clé : nom de la valeurs de l'énumération {@link TypeHistoriqueEssai}.<br>
     * - valeur : fabrique d'objets avec parent.
     */
    private Map<String, BeanObjectWithParentFactory<? extends BeanObject, ? extends BeanObject>> mapFactories;

    /**
     * Constructeur.
     * @param essaiDao Dao de gestion des essais.
     */
    public EssaiServiceImpl(final GenericDao<Essai> essaiDao) {
        super(essaiDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initNumEnregistrement(final Essai essai) {
        // Initialisation du critère de recherche sur l'année de création de
        // l'essai.
        final EssaiSearchCriteria criteria = (EssaiSearchCriteria) this.beanFactory.getBean("essaiCriteria");
        criteria.setAnneeCreation(essai.getAnneeCreation());

        // Récupération des résultats et calcul du nombre d'essais sur cette
        // année.
        final int nbEssais = this.getAll(criteria).size();

        // Formation du numéro Sigrec.
        final NumberFormat nf = NumberFormat.getIntegerInstance();
        nf.setMinimumIntegerDigits(EclipseConstants.NUM_SIGREC_MIN_DIGITS);
        final StringBuilder numSigrecBuilder = new StringBuilder(String.valueOf(essai.getAnneeCreation()));
        numSigrecBuilder.append(EclipseConstants.DASH);
        numSigrecBuilder.append(nf.format(nbEssais + 1));

        // Valorisation du numéro Sigrec.
        essai.getDetailRecherche().setNumEnregistrement(numSigrecBuilder.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Essai save(final Essai essai) {
        if (this.droitAccesHelper.isEssaiLectureSeule()) {
            return essai;
        }
        // Mise à jour avant validation.
        for (final EssaiBeforeSaveUpdator updator : this.beforeSaveUpdators) {
            updator.update(essai, this);
        }

        // Validation.
        for (final SaveValidator<Essai> validator : this.saveValidators) {
            validator.validate(essai, this);
        }

        // Ajout d'une modification générale.
        this.addHistorique(essai, TypeHistoriqueEssai.GENERAL);

        // Sauvegarde.
        final Essai essaiSaved = super.save(essai);

        // Mise à jour des acls.
        this.aclService.updateAclsEssais(essaiSaved);

        return essaiSaved;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Essai save(final Essai essai,
                      final String idOngletsVisites) {
        // Ajout d'une modification sur chacun des onglets visités.
        for (final String idOngletVisite : StringUtils.trimToEmpty(idOngletsVisites).split(EclipseConstants.COMMA)) {
            if (StringUtils.isBlank(idOngletVisite)) {
                // ignore this item.
                continue;
            }

            this.addHistorique(essai, TypeHistoriqueEssai.valueOf(idOngletVisite));
        }

        // Sauvegarde.
        return this.save(essai);
    }

    /**
     * {@inheritDoc}<br>
     * L'état de l'essai peut être modifié par les evenements externe. Mettre à
     * jour l'état si besoin.
     */
    @Override
    public Essai get(final Long id) {
        final Essai e = super.get(id);
        // @TODO il faut eviter que GenericConverter.getAsObject appele cette
        // methode avec une mauvaise ID pendant l'autocomplet
        if (e != null) {
            this.updateEtat(e);
        }
        return e;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Essai> getAll() {
        final Personne personne = this.userService.getPersonne();
        List<Essai> essais = new ArrayList<Essai>();

        // Admin => pas de purge
        if (personne.getIsAdmin()) {
            final String sql = "select distinct e.* from essai e";
            essais = this.executeSQLQuery(sql, null);
        }
        // Pharmacien
        else if (TypePersonne.PHARMACIEN.equals(personne.getType())) {
            final String sql =
                "select distinct e.* from essai e, habilitation h, pharmacien_pharmacie p, essai_detail_pharma_pharmacie ep"
                        + " where e.id=h.id_detail_contacts and h.active is true" + " and h.id_personne=?"
                        + " and (e.id_pharma = p.id_pharmacie or (e.id=ep.id_detail_pharma and p.id_pharmacie=ep.id_pharmacie))";
            essais = this.executeSQLQuery(sql, new Object[]{personne.getId() });
        } else {
            final String sql = "select distinct e.* from essai e, habilitation h" + " where e.id=h.id_detail_contacts and h.active is true" + " and h.id_personne=?";
            essais = this.executeSQLQuery(sql, new Object[]{personne.getId() });
        }

        return essais;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Essai> getAllWithoutPurge() {
        final List<Essai> essais = super.getAll();
        return essais;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Essai> getAll(final SearchCriteria criteria) {
        final List<Essai> essais = super.getAll(criteria);
        // Filtre sur les sites des pharmacies de l'essai
        // NB : la requête se génère mal lorsque l'on recherche un essai par
        // site en utilisant
        // EssaiSearchCriteriaManager. On obtient une GrammarException. On
        // filtre donc par site en
        // java.
        final List<Essai> essaisFiltres = this.filtreEssaisParSite(essais, ((EssaiSearchCriteria) criteria).getSite());
        return essaisFiltres;
    }

    /**
     * Filtre les essais par site.
     * @param essais : liste d'essais à filrer
     * @param siteRecherche : site recherché.
     * @return la liste d'essai filtrée.
     */
    List<Essai> filtreEssaisParSite(final List<Essai> essais,
                                    final Site siteRecherche) {
        if (siteRecherche != null) {
            final List<Essai> essaisFiltres = new ArrayList<Essai>();

            for (final Essai essai : essais) {
                boolean siteTrouve = false;

                siteTrouve = this.hasEssaiSite(siteRecherche, essai);

                if (siteTrouve) {
                    essaisFiltres.add(essai);
                }
            }
            return essaisFiltres;
        } else {
            return essais;
        }
    }

    /**
     * Verifie si l'essai est lié au site recherché
     * @param siteRecherche : site recherché.
     * @param essai : essai.
     * @return true si la pharmacie coordinatrice ou une pharmacie liée possède
     * le site recherché.
     */
    boolean hasEssaiSite(final Site siteRecherche,
                         final Essai essai) {
        boolean siteTrouve;
        siteTrouve = this.hasPharmacieSite(siteRecherche, essai.getPharmaciePrincipale());

        if (!siteTrouve && essai.getDetailDonneesPharma() != null) {
            siteTrouve = this.hasPharmaciesSite(siteRecherche, essai.getDetailDonneesPharma().getPharmacies());
        }
        return siteTrouve;
    }

    /**
     * Vérifie si une des pharmacies possède le site recherché.
     * @param siteRecherche
     * @param pharmacies
     * @return true si une des pharmacies possède le site recherché.
     */
    boolean hasPharmaciesSite(final Site siteRecherche,
                              final SortedSet<Pharmacie> pharmacies) {

        boolean siteTrouve = false;
        for (final Pharmacie pharmaLiee : pharmacies) {
            siteTrouve = this.hasPharmacieSite(siteRecherche, pharmaLiee);
            if (siteTrouve) {
                break;
            }
        }
        return siteTrouve;
    }

    /**
     * Vérifie si la pharmacie possède le site recherché.
     * @param Site siteRecherche.
     * @param Pharmacie pharmacie.
     * @return true si la pharmacie possède le site recherché.
     */
    boolean hasPharmacieSite(final Site siteRecherche,
                             final Pharmacie pharmacie) {
        boolean siteTrouve = false;
        if (pharmacie != null && pharmacie.getSites() != null) {
            final SortedSet<Site> sites = pharmacie.getSites();
            for (final Site site : sites) {
                if (site.getId().longValue() == siteRecherche.getId().longValue()) {
                    siteTrouve = true;
                    break;
                }
            }
        }
        return siteTrouve;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dettach(final Essai bean) {
        // Gestion du dettach sur les bras
        final SortedSet<Bras> listeBras = bean.getDetailDesign().getBras();
        for (final Bras bras : listeBras) {
            if (bras.getId() == null && bras.getParent() != null) {
                bras.getParent().getSousBras().remove(bras);
            }
        }
        super.dettach(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pharmacie> getAllPharmacies(final Essai essai) {
        final List<Pharmacie> pharmacies = new ArrayList<Pharmacie>();
        final DetailDonneesPharma dataPharma = essai.getDetailDonneesPharma();
        pharmacies.add(essai.getPharmaciePrincipale());
        pharmacies.addAll(dataPharma.getPharmacies());
        return pharmacies;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pharmacie> getAllPharmaciesOfUser(final Essai essai) {
        // Récupération des pharmacies de l'essai
        final List<Pharmacie> pharmaciesEssai = this.getAllPharmacies(essai);

        // Récupération des pharmacies de l'utilisateur
        final List<Pharmacie> pharmaciesUser = this.pharmacieService.getAll();

        @SuppressWarnings("unchecked")
        final List<Pharmacie> intersection = (List<Pharmacie>) CollectionUtils.intersection(pharmaciesEssai, pharmaciesUser);

        return intersection;
    }
    /**
     * Méthode en charge d'ajouter une modification à l'essai.
     * @param essai Essai.
     * @param typeHistorique Type de la modification à ajouter.
     */
    private void addHistorique(final Essai essai,
                               final TypeHistoriqueEssai typeHistorique) {
        // Récupération du beanObject servant de parent à la modification.
        BeanObject parent = essai;
        if (StringUtils.isNotBlank(typeHistorique.getModifsParentPropertyFromEssai())) {
            parent = (BeanObject) BeanTool.getPropriete(essai, typeHistorique.getModifsParentPropertyFromEssai());
        }

        // Création de la nouvelle modification.
        Assert.isTrue(this.mapFactories.containsKey(typeHistorique.name()),
                      new StringBuilder("Aucune fabrique pour le type d'historique '").append(typeHistorique.name()).append("' !").toString());

        @SuppressWarnings("unchecked")
        final Suivi nouvelleMdif = ((BeanObjectWithParentFactory<Suivi, BeanObject>) this.mapFactories.get(typeHistorique.name())).getInitializedObject(parent);

        // Ajout de la modification à la collection du parent.
        this.beanHelper.addToCollection(essai, typeHistorique.getModifsPropertyFromEssai(), nouvelleMdif);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEvenement(final Essai essai,
                                final Evenement evenement) {
        final SortedSet<Evenement> evts = essai.getEvenements();
        evts.remove(evenement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDetailEtatEssai(final Essai essai,
                                   final EtatEssai newEtat,
                                   final String commentaireNewEtat) {
        final DetailEtatEssai detailEtatEssai = this.detailEtatEssaiFactory.getInitializedObject();
        detailEtatEssai.setCommentaire(commentaireNewEtat);
        detailEtatEssai.setEtatEssai(newEtat);
        detailEtatEssai.setEssai(essai);
        essai.getDetailsEtatEssai().add(detailEtatEssai);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Essai> getEssaisActifs(final Calendar dateFin,
                                       final Pharmacie pharmacie) {
        final List<Essai> essais = this.getAllWithoutPurge();

        // On filtre les essais qui ne sont pas associés à la pharmacie.
        CollectionUtils.filter(essais, new PharmacieEssaiPredicate(pharmacie));

        // on filtre les essais clos à la date de fin.
        CollectionUtils.filter(essais, new EssaiActifPredicate(dateFin));

        // On vérifie qu'ils ont un mouvement
        CollectionUtils.filter(essais, new Predicate() {

            @Override
            public boolean evaluate(final Object object) {
                return !((Essai) object).getMvts().isEmpty();
            }
        });
        return essais;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EtatEssai updateEtat(final Essai essai) {

        if (essai == null) {
            return null;
        }

        if (essai.getDetailFaisabilite() != null) {
            final InfosConclusionFaisabilite infoConclusion = essai.getDetailFaisabilite().getInfosConclusion();

            if (infoConclusion != null && infoConclusion.getFavorable() == Boolean.TRUE && essai.getEtat().equals(EtatEssai.EN_EVALUATION)) {
                essai.setEtat(EtatEssai.EN_ATTENTE_MISE_EN_PLACE);
                this.addDetailEtatEssai(essai, EtatEssai.EN_ATTENTE_MISE_EN_PLACE, "Grille de faisabilité complétée");
            }

            if (essai.getId() != null) {

                // Passage à l'état EN COURS.
                if (essai.getEtat().equals(EtatEssai.EN_ATTENTE_MISE_EN_PLACE)
                    && (BooleanUtils.isTrue(infoConclusion.getConvSignee()) || BooleanUtils.isTrue(essai.getDetailAdministratif().getInfosConvention().getConvSignee()))
                    && essai.getDetailSurcout().getGrille() != null && !essai.getDetailSurcout().getDocumentsPrevisionnels().isEmpty() && this.isVisiteEffectue(essai)) {
                    essai.setEtat(EtatEssai.MISE_EN_PLACE);
                    this.addDetailEtatEssai(essai, EtatEssai.MISE_EN_PLACE, "Mise en place et convention signée.");
                    // Enregistrement parce que l'evenement externe peut
                    // déclancher
                    // le changement
                    // d'état et
                    // l'essai doit être mis à jour definitivement.
                    super.save(essai);
                }
            }
        }
        return essai.getEtat();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Long> getIdsEssaisOfUser(final Personne personne) {
        final List<Long> idsEssais = new ArrayList<Long>();
        String requete;

        // Pharmacien
        if (TypePersonne.PHARMACIEN.equals(personne.getType())) {
            requete =
                "select distinct e.id, e.id from essai e, habilitation h, pharmacien_pharmacie p, essai_detail_pharma_pharmacie ep "
                        + "where e.id = h.id_detail_contacts and h.active is true and h.id_personne=? "
                        + "and (e.id_pharma = p.id_pharmacie or (e.id=ep.id_detail_pharma and p.id_pharmacie=ep.id_pharmacie))";
        } else {
            requete = "select distinct e.id, e.id from essai e, habilitation h where e.id = h.id_detail_contacts and h.active is true and h.id_personne=? ";
        }
        final List<Object[]> reqResults = this.executeSQLQueryTabObject(requete, new Object[]{personne.getId() });
        for (final Object[] reqResult : reqResults) {
            idsEssais.add(((BigInteger) reqResult[0]).longValue());
        }
        return idsEssais;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EssaiDTO> autoCompleteEssai(final String requete) {

        final List<EssaiDTO> essaiDTOs = this.essaiSearchDao.findEssaiDTOByNumInterneOrNomOrPromoteur(requete);
        return essaiDTOs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EssaiDTO getEssaiDTO(final Long id) {

        final List<EssaiDTO> essaiDTOs = this.essaiSearchDao.findEssaiDTOById(id);;

        // Renvoie le 1er et seul élément.
        return essaiDTOs.get(0);
    }

    /**
     * @return true si visite déjà effectué pour l'essai
     */
    private boolean isVisiteEffectue(final Essai essai) {
        final Evenement visite = this.evenementService.getVisiteMonitoring(essai);
        return visite != null && visite.getResultatVisite() != null && visite.getResultatVisite().equals(ResultatVisite.EFFECTUE);
    }

    /**
     * Setter pour mapFactories.
     * @param mapFactories le mapFactories à écrire.
     */
    public void setMapFactories(final Map<String, BeanObjectWithParentFactory<? extends BeanObject, ? extends BeanObject>> mapFactories) {
        this.mapFactories = mapFactories;
    }

    /**
     * Setter pour beanHelper.
     * @param beanHelper le beanHelper à écrire.
     */
    public void setBeanHelper(final BeanHelper<Essai> beanHelper) {
        this.beanHelper = beanHelper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBeanFactory(final BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    /**
     * Setter pour saveValidators.
     * @param saveValidators le saveValidators à écrire.
     */
    public void setSaveValidators(final List<SaveValidator<Essai>> saveValidators) {
        this.saveValidators = saveValidators;
    }

    /**
     * Setter pour beforeSaveUpdators.
     * @param beforeSaveUpdators le beforeSaveUpdators à écrire.
     */
    public void setBeforeSaveUpdators(final List<EssaiBeforeSaveUpdator> beforeSaveUpdators) {
        this.beforeSaveUpdators = beforeSaveUpdators;
    }

    /**
     * Setter pour pharmacieService.
     * @param pharmacieService Le pharmacieService à écrire.
     */
    public void setPharmacieService(final PharmacieService pharmacieService) {
        this.pharmacieService = pharmacieService;
    }

    /**
     * Setter pour detailEtatEssaiFactory.
     * @param detailEtatEssaiFactory Le detailEtatEssaiFactory à écrire.
     */
    public void setDetailEtatEssaiFactory(final SuiviFactory<DetailEtatEssai> detailEtatEssaiFactory) {
        this.detailEtatEssaiFactory = detailEtatEssaiFactory;
    }

    /**
     * Getter pour droitAccesHelper.
     * @return Le droitAccesHelper
     */
    public DroitAccesHelper getDroitAccesHelper() {
        return this.droitAccesHelper;
    }

    /**
     * Setter pour droitAccesHelper.
     * @param droitAccesHelper Le droitAccesHelper à écrire.
     */
    public void setDroitAccesHelper(final DroitAccesHelper droitAccesHelper) {
        this.droitAccesHelper = droitAccesHelper;
    }

    /** @param evenementService Le evenementService à écrire. */
    public void setEvenementService(final EvenementService evenementService) {
        this.evenementService = evenementService;
    }

    /**
     * Setter pour userService.
     * @param userService Le userService à écrire.
     */
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Setter pour essaiSearchDao.
     * @param essaiSearchDao Le essaiSearchDao à écrire.
     */
    public void setEssaiSearchDao(final EssaiSearchDao essaiSearchDao) {
        this.essaiSearchDao = essaiSearchDao;
    }

    /**
     * Setter pour aclService.
     * @param aclService Le aclService à écrire.
     */
    public void setAclService(final AclService aclService) {
        this.aclService = aclService;
    }

}

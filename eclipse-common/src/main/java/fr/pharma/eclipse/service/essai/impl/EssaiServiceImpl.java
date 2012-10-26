package fr.pharma.eclipse.service.essai.impl;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.essai.EssaiSearchCriteria;
import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.enums.TypeHistoriqueEssai;
import fr.pharma.eclipse.domain.enums.evenement.ResultatVisite;
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
import fr.pharma.eclipse.handler.habilitation.HabilitationHandler;
import fr.pharma.eclipse.predicate.essai.EssaiActifPredicate;
import fr.pharma.eclipse.predicate.pharmacie.PharmacieEssaiPredicate;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.essai.updator.EssaiBeforeSaveUpdator;
import fr.pharma.eclipse.service.evenement.EvenementService;
import fr.pharma.eclipse.service.helper.DroitAccesHelper;
import fr.pharma.eclipse.service.helper.common.BeanHelper;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.introspection.BeanTool;
import fr.pharma.eclipse.validator.save.SaveValidator;

/**
 * Classe d'implémentation du service de gestion de essai.
 
 * @version $Revision$ $Date$
 */
public class EssaiServiceImpl
    extends GenericServiceImpl<Essai>
    implements EssaiService, BeanFactoryAware
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2938823438001362504L;

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(EssaiServiceImpl.class);

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
     * Gestionnaire d'habilitations sur les essais.
     */
    @Resource(name = "essaiHabilitationHandler")
    private HabilitationHandler<Essai> habilitationHandler;

    /**
     * Service de gestion des pharmacies.
     */
    @Resource(name = "pharmacieService")
    private PharmacieService pharmacieService;

    /**
     * Factory de DetailEtatEssai.
     */
    @Resource(name = "detailEtatEssaiFactory")
    private SuiviFactory<DetailEtatEssai> detailEtatEssaiFactory;

    /**
     * Service utilisateur.
     */
    @Resource(name = "droitAccesHelper")
    private DroitAccesHelper droitAccesHelper;

    /** Service evenement. */
    @Resource(name = "evenementService")
    private EvenementService evenementService;

    /**
     * Map des fabriques d'historiques pour les différents types d'historiques de l'essai.<br>
     * - clé : nom de la valeurs de l'énumération {@link TypeHistoriqueEssai}.<br>
     * - valeur : fabrique d'objets avec parent.
     */
    private Map<String, BeanObjectWithParentFactory<? extends BeanObject, ? extends BeanObject>> mapFactories;

    /**
     * Constructeur.
     * @param essaiDao Dao de gestion des essais.
     */
    public EssaiServiceImpl(final GenericDao<Essai> essaiDao)
    {
        super(essaiDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initNumEnregistrement(final Essai essai)
    {
        // Initialisation du critère de recherche sur l'année de création de l'essai.
        final EssaiSearchCriteria criteria =
            (EssaiSearchCriteria) this.beanFactory.getBean("essaiCriteria");
        criteria.setAnneeCreation(essai.getAnneeCreation());

        // Récupération des résultats et calcul du nombre d'essais sur cette année.
        final int nbEssais = this.getAll(criteria).size();

        // Formation du numéro Sigrec.
        final NumberFormat nf = NumberFormat.getIntegerInstance();
        nf.setMinimumIntegerDigits(EclipseConstants.NUM_SIGREC_MIN_DIGITS);
        final StringBuilder numSigrecBuilder =
            new StringBuilder(String.valueOf(essai.getAnneeCreation()));
        numSigrecBuilder.append(EclipseConstants.DASH);
        numSigrecBuilder.append(nf.format(nbEssais + 1));

        // Valorisation du numéro Sigrec.
        essai.getDetailRecherche().setNumEnregistrement(numSigrecBuilder.toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Essai save(final Essai essai)
    {
        if (this.droitAccesHelper.isEssaiLectureSeule())
        {
            return essai;
        }
        // Mise à jour avant validation.
        for (final EssaiBeforeSaveUpdator updator : this.beforeSaveUpdators)
        {
            updator.update(essai,
                           this);
        }

        // Validation.
        for (final SaveValidator<Essai> validator : this.saveValidators)
        {
            validator.validate(essai,
                               this);
        }

        // Ajout d'une modification générale.
        this.addHistorique(essai,
                           TypeHistoriqueEssai.GENERAL);

        // Sauvegarde.
        return super.save(essai);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Essai save(final Essai essai,
                      final String idOngletsVisites)
    {
        // Ajout d'une modification sur chacun des onglets visités.
        if (StringUtils.hasText(idOngletsVisites))
        {
            for (final String idOngletVisite : idOngletsVisites.split(EclipseConstants.COMMA))
            {
                TypeHistoriqueEssai typeHistoriqueOnglet = null;
                try
                {
                    typeHistoriqueOnglet = TypeHistoriqueEssai.valueOf(idOngletVisite);
                }
                catch (final IllegalArgumentException e)
                {
                    this.log.error(new StringBuilder()
                            .append("Aucune correspondance pour le type d'historique '")
                            .append(idOngletVisite)
                            .append("' : aucun historique d'onglet n'est ajouté.")
                            .toString());
                }

                if (typeHistoriqueOnglet != null)
                {
                    this.addHistorique(essai,
                                       typeHistoriqueOnglet);
                }
            }
        }

        // Sauvegarde.
        return this.save(essai);
    }

    /**
     * {@inheritDoc}<br>
     * L'état de l'essai peut être modifié par les evenements externe. Mettre à jour l'état si
     * besoin.
     */
    @Override
    public Essai get(final Long id)
    {
        final Essai e = super.get(id);
        this.updateEtat(e);
        return e;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Essai> getAll()
    {
        final List<Essai> essais = super.getAll();
        // Purge des essais par rapport aux habilitations
        this.habilitationHandler.purge(essais);
        return essais;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Essai> getAllWithoutPurge()
    {
        final List<Essai> essais = super.getAll();
        return essais;
    }

    /**
     * {@inheritDoc}
     */
    public List<Essai> getAll(final EssaiSearchCriteria criteria)
    {
        final List<Essai> essais = super.getAll(criteria);

        // Filtre sur les sites des pharmacies de l'essai
        // NB : la requête se génère mal lorsque l'on recherche un essai par site en utilisant
        // EssaiSearchCriteriaManager. On obtient une GrammarException. On filtre donc par site en
        // java.
        final List<Essai> essaisFiltres = this.filtreEssaisParSite(essais,
                                                                   criteria.getSite());

        // Purge des essais par rapport aux habilitations
        this.habilitationHandler.purge(essaisFiltres);
        return essaisFiltres;
    }

    /**
     * Filtre les essais par site.
     * @param essais : liste d'essais à filrer
     * @param siteRecherche : site recherché.
     * @return la liste d'essai filtrée.
     */
    List<Essai> filtreEssaisParSite(final List<Essai> essais,
                                    final Site siteRecherche)
    {
        if (siteRecherche != null)
        {
            final List<Essai> essaisFiltres = new ArrayList<Essai>();

            for (final Essai essai : essais)
            {
                boolean siteTrouve = false;

                siteTrouve = this.hasEssaiSite(siteRecherche,
                                               essai);

                if (siteTrouve)
                {
                    essaisFiltres.add(essai);
                }
            }
            return essaisFiltres;
        }
        else
        {
            return essais;
        }
    }

    /**
     * Verifie si l'essai est lié au site recherché
     * @param siteRecherche : site recherché.
     * @param essai : essai.
     * @return true si la pharmacie coordinatrice ou une pharmacie liée possède le site recherché.
     */
    boolean hasEssaiSite(final Site siteRecherche,
                         final Essai essai)
    {
        boolean siteTrouve;
        siteTrouve = this.hasPharmacieSite(siteRecherche,
                                           essai.getPharmaciePrincipale());

        if (!siteTrouve
            && essai.getDetailDonneesPharma() != null)
        {
            siteTrouve = this.hasPharmaciesSite(siteRecherche,
                                                essai.getDetailDonneesPharma().getPharmacies());
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
                              final SortedSet<Pharmacie> pharmacies)
    {

        boolean siteTrouve = false;
        for (final Pharmacie pharmaLiee : pharmacies)
        {
            siteTrouve = this.hasPharmacieSite(siteRecherche,
                                               pharmaLiee);
            if (siteTrouve)
            {
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
                             final Pharmacie pharmacie)
    {
        boolean siteTrouve = false;
        if (pharmacie != null
            && pharmacie.getSites() != null)
        {
            final SortedSet<Site> sites = pharmacie.getSites();
            for (final Site site : sites)
            {
                if (site.getId().longValue() == siteRecherche.getId().longValue())
                {
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
    public void dettach(final Essai bean)
    {
        // Gestion du dettach sur les bras
        final SortedSet<Bras> listeBras = bean.getDetailDesign().getBras();
        for (final Bras bras : listeBras)
        {
            if (bras.getId() == null
                && bras.getParent() != null)
            {
                bras.getParent().getSousBras().remove(bras);
            }
        }
        super.dettach(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pharmacie> getAllPharmacies(final Essai essai)
    {
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
    @SuppressWarnings("unchecked")
    public List<Pharmacie> getAllPharmaciesOfUser(final Essai essai)
    {
        // Récupération des pharmacies de l'essai
        final List<Pharmacie> pharmacies = this.getAllPharmacies(essai);

        // Récupération des pharmacies de l'utilisateur
        final List<Pharmacie> pharmaciesUser = this.pharmacieService.getAll();

        return (List<Pharmacie>) CollectionUtils.intersection(pharmacies,
                                                              pharmaciesUser);
    }

    /**
     * Méthode en charge d'ajouter une modification à l'essai.
     * @param essai Essai.
     * @param typeHistorique Type de la modification à ajouter.
     */
    @SuppressWarnings("unchecked")
    private void addHistorique(final Essai essai,
                               final TypeHistoriqueEssai typeHistorique)
    {
        // Récupération du beanObject servant de parent à la modification.
        BeanObject parent = essai;
        if (StringUtils.hasText(typeHistorique.getModifsParentPropertyFromEssai()))
        {
            parent =
                (BeanObject) BeanTool.getPropriete(essai,
                                                   typeHistorique
                                                           .getModifsParentPropertyFromEssai());
        }

        // Création de la nouvelle modification.
        Assert.isTrue(this.mapFactories.containsKey(typeHistorique.name()),
                      new StringBuilder("Aucune fabrique pour le type d'historique '")
                              .append(typeHistorique.name())
                              .append("' !")
                              .toString());
        final Suivi nouvelleMdif =
            ((BeanObjectWithParentFactory<Suivi, BeanObject>) this.mapFactories
                    .get(typeHistorique.name())).getInitializedObject(parent);

        // Ajout de la modification à la collection du parent.
        this.beanHelper.addToCollection(essai,
                                        typeHistorique.getModifsPropertyFromEssai(),
                                        nouvelleMdif);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEvenement(final Essai essai,
                                final Evenement evenement)
    {
        final SortedSet<Evenement> evts = essai.getEvenements();
        evts.remove(evenement);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDetailEtatEssai(final Essai essai,
                                   final EtatEssai newEtat,
                                   final String commentaireNewEtat)
    {
        final DetailEtatEssai detailEtatEssai =
            this.detailEtatEssaiFactory.getInitializedObject();
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
                                       final Pharmacie pharmacie)
    {
        final List<Essai> essais = this.getAllWithoutPurge();

        // On filtre les essais qui ne sont pas associés à la pharmacie.
        CollectionUtils.filter(essais,
                               new PharmacieEssaiPredicate(pharmacie));

        // on filtre les essais clos à la date de fin.
        CollectionUtils.filter(essais,
                               new EssaiActifPredicate(dateFin));

        // On vérifie qu'ils ont un mouvement
        CollectionUtils.filter(essais,
                               new Predicate() {

                                   @Override
                                   public boolean evaluate(final Object object)
                                   {
                                       return !((Essai) object).getMvts().isEmpty();
                                   }
                               });
        return essais;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EtatEssai updateEtat(final Essai essai)
    {

        final InfosConclusionFaisabilite infoConclusion =
            essai.getDetailFaisabilite().getInfosConclusion();

        if (infoConclusion.getFavorable() == Boolean.TRUE
            && essai.getEtat().equals(EtatEssai.EN_EVALUATION))
        {
            essai.setEtat(EtatEssai.EN_ATTENTE_MISE_EN_PLACE);
            this.addDetailEtatEssai(essai,
                                    EtatEssai.EN_ATTENTE_MISE_EN_PLACE,
                                    "Grille de faisabilité complétée");
        }

        if (essai.getId() != null)
        {

            // Passage à l'état EN COURS.
            final Evenement visite = this.evenementService.getVisiteMonitoring(essai);
            if (essai.getEtat().equals(EtatEssai.EN_ATTENTE_MISE_EN_PLACE)
                && (BooleanUtils.isTrue(infoConclusion.getConvSignee()) || BooleanUtils
                        .isTrue(essai
                                .getDetailAdministratif()
                                .getInfosConvention()
                                .getConvSignee()))
                && essai.getDetailSurcout().getGrille() != null
                && !essai.getDetailSurcout().getDocumentsPrevisionnels().isEmpty()
                && visite != null
                && visite.getResultatVisite() != null
                && visite.getResultatVisite().equals(ResultatVisite.EFFECTUE))
            {
                essai.setEtat(EtatEssai.MISE_EN_PLACE);
                this.addDetailEtatEssai(essai,
                                        EtatEssai.MISE_EN_PLACE,
                                        "Mise en place et convention signée.");
                // Enregistrement parce que l'evenement externe peut déclancher le changement
                // d'état et
                // l'essai doit être mis à jour definitivement.
                super.save(essai);
            }
        }

        return essai.getEtat();
    }

    /**
     * Setter pour mapFactories.
     * @param mapFactories le mapFactories à écrire.
     */
    public void setMapFactories(final Map<String, BeanObjectWithParentFactory<? extends BeanObject, ? extends BeanObject>> mapFactories)
    {
        this.mapFactories = mapFactories;
    }

    /**
     * Setter pour beanHelper.
     * @param beanHelper le beanHelper à écrire.
     */
    public void setBeanHelper(final BeanHelper<Essai> beanHelper)
    {
        this.beanHelper = beanHelper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBeanFactory(final BeanFactory beanFactory)
        throws BeansException
    {
        this.beanFactory = beanFactory;
    }

    /**
     * Setter pour saveValidators.
     * @param saveValidators le saveValidators à écrire.
     */
    public void setSaveValidators(final List<SaveValidator<Essai>> saveValidators)
    {
        this.saveValidators = saveValidators;
    }

    /**
     * Setter pour beforeSaveUpdators.
     * @param beforeSaveUpdators le beforeSaveUpdators à écrire.
     */
    public void setBeforeSaveUpdators(final List<EssaiBeforeSaveUpdator> beforeSaveUpdators)
    {
        this.beforeSaveUpdators = beforeSaveUpdators;
    }

    /**
     * Setter pour habilitationHandler.
     * @param habilitationHandler Le habilitationHandler à écrire.
     */
    public void setHabilitationHandler(final HabilitationHandler<Essai> habilitationHandler)
    {
        this.habilitationHandler = habilitationHandler;
    }

    /**
     * Setter pour pharmacieService.
     * @param pharmacieService Le pharmacieService à écrire.
     */
    public void setPharmacieService(final PharmacieService pharmacieService)
    {
        this.pharmacieService = pharmacieService;
    }

    /**
     * Setter pour detailEtatEssaiFactory.
     * @param detailEtatEssaiFactory Le detailEtatEssaiFactory à écrire.
     */
    public void setDetailEtatEssaiFactory(final SuiviFactory<DetailEtatEssai> detailEtatEssaiFactory)
    {
        this.detailEtatEssaiFactory = detailEtatEssaiFactory;
    }

    /**
     * Getter pour droitAccesHelper.
     * @return Le droitAccesHelper
     */
    public DroitAccesHelper getDroitAccesHelper()
    {
        return this.droitAccesHelper;
    }

    /**
     * Setter pour droitAccesHelper.
     * @param droitAccesHelper Le droitAccesHelper à écrire.
     */
    public void setDroitAccesHelper(final DroitAccesHelper droitAccesHelper)
    {
        this.droitAccesHelper = droitAccesHelper;
    }

    /** @param evenementService Le evenementService à écrire. */
    public void setEvenementService(final EvenementService evenementService)
    {
        this.evenementService = evenementService;
    }
}

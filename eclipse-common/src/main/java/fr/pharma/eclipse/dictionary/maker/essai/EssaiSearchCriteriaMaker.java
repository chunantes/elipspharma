package fr.pharma.eclipse.dictionary.maker.essai;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.essai.EssaiSearchCriteria;
import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.enums.TypeDispensation;

/**
 * Artisan de recherche pour les essais.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiSearchCriteriaMaker extends AbstractCriteriaMaker {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1073970765339009251L;

    /**
     * Constructeur par défaut.
     */
    public EssaiSearchCriteriaMaker() {
        super(EssaiSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        final EssaiSearchCriteria crit = (EssaiSearchCriteria) searchCrit;

        // Caractéristiques
        this.handleCriteriaCaracteristiquesEssai(criteria, crit);

        // Num SIGREC
        this.handleCriteriaNumSigrec(criteria, crit);

        // Num Eudract
        this.handleCriteriaNumEudract(criteria, crit);

        // Année de création
        this.handleCriteriaAnneeCreation(criteria, crit);

        // Mots clés
        this.handleCriteriaMotsCles(criteria, crit);
        // Etat
        if (crit.getEtat() != null) {
            CriteriaMakerUtils.addCritere(criteria, "etat", crit.getEtat());
        }

        // Promoteur
        if (crit.getPromoteur() != null) {
            CriteriaMakerUtils.addCritere(criteria, "promoteur", crit.getPromoteur());
        }

        // Investigateur principal
        this.handleCriteriaInvestigateurPrincipal(criteria, crit);

        // Pharmacie
        this.handleCriteriaPharma(criteria, crit);

        // Service
        this.handleCriteriaService(criteria, crit);

        // Numéro interne ou nom ou promoteur
        this.handleCriteriaNumInterneOrNomOrPromoteur(criteria, crit);

        // Essais rentrant en compte pour la dispensation globable
        this.handleCriteriaEssaisDispensationGlobale(criteria, crit);

        // Restriction sur les droits de voir les essais
        final List<Long> idsEssais = this.getAclSearchDao().findIdsEssais();
        CriteriaMakerUtils.addInCritere(criteria, "this.id", idsEssais.toArray(new Object[idsEssais.size()]));
    }

    /**
     * Méthode en charge de traiter les critères directs sur les
     * caractéristiques de l'essai.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche Essai.
     */
    private void handleCriteriaCaracteristiquesEssai(final Criteria criteria,
                                                     final EssaiSearchCriteria crit) {
        // Num interne
        if (StringUtils.isNotEmpty(crit.getNumInterne())) {
            CriteriaMakerUtils.addSqlCritere(criteria, "this_.numInterne", crit.getNumInterne());
        }

        // Num interne strict
        if (StringUtils.isNotEmpty(crit.getNumInterneStrict())) {
            CriteriaMakerUtils.addCritere(criteria, "numInterne", crit.getNumInterneStrict());
        }

        // Nom
        if (StringUtils.isNotEmpty(crit.getNom())) {
            CriteriaMakerUtils.addSqlCritere(criteria, "this_.nom", crit.getNom());
        }
        // DCI
        if (StringUtils.isNotEmpty(crit.getDci())) {
            CriteriaMakerUtils.addSqlCritere(criteria, "this_.dci", crit.getDci());
        }
    }

    /**
     * Méthode en charge de traiter le critère posé sur la récupération des
     * essais pour une dispensation globale.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche Essai.
     */
    private void handleCriteriaEssaisDispensationGlobale(final Criteria criteria,
                                                         final EssaiSearchCriteria crit) {
        if (crit.getEssaisDispensationGlobale() != null && Boolean.TRUE.equals(crit.getEssaisDispensationGlobale())) {
            final Criteria detailPharma = criteria.createCriteria("detailDonneesPharma", "detailDonneesPharma");
            final Disjunction dij = Restrictions.disjunction();
            CriteriaMakerUtils.addCritere(dij, "infosDispensations.typeDispensation", TypeDispensation.GLOBALE);
            dij.add(Restrictions.isNull("infosDispensations.typeDispensation"));
            detailPharma.add(dij);
        }
    }

    /**
     * Méthode en charge de traiter le critère posé sur numéro interne ou nom ou
     * promoteur.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche Essai.
     */
    private void handleCriteriaNumInterneOrNomOrPromoteur(final Criteria criteria,
                                                          final EssaiSearchCriteria crit) {
        if (StringUtils.isNotEmpty(crit.getNumInterneOrNomOrPromoteur())) {
            final Disjunction dij = Restrictions.disjunction();
            // Numéro interne
            CriteriaMakerUtils.addSqlCritere(dij, "this_.numInterne", crit.getNumInterneOrNomOrPromoteur());
            // Nom
            CriteriaMakerUtils.addSqlCritere(dij, "this_.nom", crit.getNumInterneOrNomOrPromoteur());
            // Promoteur
            criteria.createCriteria("promoteur");
            CriteriaMakerUtils.addSqlCritere(dij, "raisonSociale", crit.getNumInterneOrNomOrPromoteur());

            criteria.add(dij);
        }
    }

    /**
     * Méthode en charge de traiter le critère posé sur investigateur principal.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche Essai.
     */
    private void handleCriteriaInvestigateurPrincipal(final Criteria criteria,
                                                      final EssaiSearchCriteria crit) {
        if (crit.getInvestigateur() != null) {
            // Création d'un critère sur les habilitations du du détail des
            // contacts de l'essai
            final Criteria critDetailContacts = criteria.createCriteria("detailContacts", "detailContacts");
            final Criteria critHabilitations = critDetailContacts.createCriteria("habilitations", "habilitations");
            CriteriaMakerUtils.addCritere(critHabilitations, "habilitations.personne", crit.getInvestigateur());
            CriteriaMakerUtils.addCritere(critHabilitations, "habilitations.droit", Droit.INVESTIGATEUR_PRINCIPAL);
        }
    }

    /**
     * Méthode en charge de traiter le critère posé sur le numéro SIGREC.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche Essai.
     */
    private void handleCriteriaNumSigrec(final Criteria criteria,
                                         final EssaiSearchCriteria crit) {
        if (StringUtils.isNotEmpty(crit.getNumSigrec())) {
            final Criteria detail = criteria.createCriteria("detailRecherche", "detailRecherche");
            CriteriaMakerUtils.addSqlCritere(detail, "numEnregistrement", crit.getNumSigrec());
        }
    }

    /**
     * Méthode en charge de traiter le critère posé sur le numéro Eudract.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche Essai.
     */
    private void handleCriteriaNumEudract(final Criteria criteria,
                                          final EssaiSearchCriteria crit) {
        if (StringUtils.isNotEmpty(crit.getNumEudract())) {
            final Criteria detail = criteria.createCriteria("detailAdministratif", "detailAdministratif");
            CriteriaMakerUtils.addSqlCritere(detail, "ac_numIdent", crit.getNumEudract());
        }
    }

    /**
     * Méthode en charge de traiter le critère posé sur AnneeCreation.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche Essai.
     */
    private void handleCriteriaAnneeCreation(final Criteria criteria,
                                             final EssaiSearchCriteria crit) {
        if (crit.getAnneeCreation() != null) {
            CriteriaMakerUtils.addCritere(criteria, "anneeCreation", crit.getAnneeCreation());
        }
    }

    /**
     * Méthode en charge de traiter les critères posés sur Pharmacie.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche Essai.
     */
    protected void handleCriteriaPharma(final Criteria criteria,
                                        final EssaiSearchCriteria crit) {
        // Une Pharmacie ou un Site est renseigné
        if (crit.getPharmacie() != null) {
            final Criteria detail = criteria.createCriteria("detailDonneesPharma", "detailDonneesPharma");

            detail.createCriteria("pharmacies", "aliasPharmacies", CriteriaSpecification.FULL_JOIN);
            // Pharmacie
            if (crit.getPharmacie() != null) {
                final Disjunction disjonctionPharma = Restrictions.disjunction();
                // critère sur pharmacie principale
                CriteriaMakerUtils.addCritere(disjonctionPharma, "pharmaciePrincipale", crit.getPharmacie());
                // critère sur autres pharmacies
                CriteriaMakerUtils.addInCritere(disjonctionPharma, "aliasPharmacies.id", new Long[]{crit.getPharmacie().getId() });
                criteria.add(disjonctionPharma);
            }
        }
    }

    /**
     * Méthode en charge de traiter les critères posés sur Service.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche Essai.
     */
    protected void handleCriteriaService(final Criteria criteria,
                                         final EssaiSearchCriteria crit) {
        if (crit.getService() != null) {
            final Criteria critService = criteria.createCriteria("services", "services");
            CriteriaMakerUtils.addInCritere(critService, "services.id", new Long[]{crit.getService().getId() });
        }
    }

    /**
     * Méthode en charge de traiter les critères posés sur les mots clés.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche Essai.
     */
    private void handleCriteriaMotsCles(final Criteria criteria,
                                        final EssaiSearchCriteria crit) {
        if (StringUtils.isNotEmpty(crit.getMotsCles())) {
            final Criteria critDetailRecherche = criteria.createCriteria("detailRecherche", "detailRecherche");
            CriteriaMakerUtils.addSqlCritere(critDetailRecherche, "motsCles", crit.getMotsCles());
        }
    }
}

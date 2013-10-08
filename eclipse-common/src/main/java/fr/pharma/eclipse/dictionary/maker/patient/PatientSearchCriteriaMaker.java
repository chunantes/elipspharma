package fr.pharma.eclipse.dictionary.maker.patient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.patient.PatientSearchCriteria;
import fr.pharma.eclipse.domain.enums.TypeRechercheParEssai;

/**
 * Artisan de recherche pour les patients.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PatientSearchCriteriaMaker extends AbstractCriteriaMaker {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3474958869287377467L;

    /**
     * Constructeur par défaut.
     */
    public PatientSearchCriteriaMaker() {
        super(PatientSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        final PatientSearchCriteria crit = (PatientSearchCriteria) searchCrit;

        // Nom
        if (StringUtils.isNotEmpty(crit.getNom())) {
            CriteriaMakerUtils.addSqlCritere(criteria, "this_.nom", crit.getNom());
        }

        // Prénom
        if (StringUtils.isNotEmpty(crit.getPrenom())) {
            CriteriaMakerUtils.addSqlCritere(criteria, "this_.prenom", crit.getPrenom());
        }

        // Prénom
        if (StringUtils.isNotEmpty(crit.getInitiales())) {
            CriteriaMakerUtils.addSqlCritere(criteria, "this_.initiales", crit.getInitiales());
        }

        // Numéro IPP
        if (StringUtils.isNotEmpty(crit.getNumeroIpp())) {
            CriteriaMakerUtils.addSqlCritere(criteria, "this_.numeroIpp", crit.getNumeroIpp());
        }

        // Numéro IPP
        if (StringUtils.isNotEmpty(crit.getNumeroIppExact())) {
            CriteriaMakerUtils.addCritere(criteria, "numeroIpp", crit.getNumeroIppExact());
        }

        this.handleCriteriaEssai(criteria, crit);

        this.handleCriteriaByTypeInclusion(criteria, crit);

        this.handleCriteriaNumeroIppOrNomOrPrenom(criteria, crit);
    }

    /**
     * Méthode en charge de traiter le critère posé sur le type de recherche par
     * essai.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche patient.
     */
    private void handleCriteriaByTypeInclusion(final Criteria criteria,
                                               final PatientSearchCriteria crit) {
        // Si seulelement l'énumération est sélectionnée
        if ((crit.getByEssai() != null) && (crit.getEssaiByType() == null)) {
            if (crit.getByEssai().equals(TypeRechercheParEssai.ACUTELLEMENT_INCLU)) {
                final Criteria critInclusion = criteria.createCriteria("inclusions", "inclusions");

                CriteriaMakerUtils.addCritere(critInclusion, "inclusions.actif", true);
            } else {
                criteria.add(Restrictions.isNotEmpty("inclusions"));
            }
        }

        // si un essai est saisi sans type d'inclusion.
        if ((crit.getByEssai() == null) && (crit.getEssaiByType() != null)) {
            final Criteria critInclusion = criteria.createCriteria("inclusions", "inclusions");

            CriteriaMakerUtils.addCritere(critInclusion, "inclusions.essai", crit.getEssaiByType());

            CriteriaMakerUtils.addCritere(critInclusion, "inclusions.actif", true);
        }

        // si un type est saisi et l'essai aussi
        if ((crit.getByEssai() != null) && (crit.getEssaiByType() != null)) {

            final Criteria critInclusion = criteria.createCriteria("inclusions", "inclusions");
            CriteriaMakerUtils.addCritere(critInclusion, "inclusions.essai", crit.getEssaiByType());

            if (crit.getByEssai().equals(TypeRechercheParEssai.ACUTELLEMENT_INCLU)) {
                CriteriaMakerUtils.addCritere(critInclusion, "inclusions.actif", true);
            }
        }
    }
    /**
     * Méthode en charge de traiter le critère posé sur numéro IPP ou nom ou
     * prénom.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche Patient.
     */
    private void handleCriteriaNumeroIppOrNomOrPrenom(final Criteria criteria,
                                                      final PatientSearchCriteria crit) {
        if (StringUtils.isNotEmpty(crit.getNumeroIppOrNomOrPrenom())) {
            final Disjunction dij = Restrictions.disjunction();
            // Numéro interne
            CriteriaMakerUtils.addSqlCritere(dij, "this_.numeroIpp", crit.getNumeroIppOrNomOrPrenom());
            // Nom
            CriteriaMakerUtils.addSqlCritere(dij, "this_.nom", crit.getNumeroIppOrNomOrPrenom());
            // Promoteur
            CriteriaMakerUtils.addSqlCritere(dij, "this_.prenom", crit.getNumeroIppOrNomOrPrenom());

            criteria.add(dij);
        }
    }

    /**
     * Méthode en charge de traiter les critères posés sur Essai.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche Patient.
     */
    protected void handleCriteriaEssai(final Criteria criteria,
                                       final PatientSearchCriteria crit) {
        if (crit.getEssai() != null) {
            final Criteria critInclusion = criteria.createCriteria("inclusions", "inclusions");

            CriteriaMakerUtils.addCritere(critInclusion, "inclusions.essai", crit.getEssai());
        }
    }
}

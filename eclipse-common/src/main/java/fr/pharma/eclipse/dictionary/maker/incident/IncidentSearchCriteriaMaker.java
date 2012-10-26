package fr.pharma.eclipse.dictionary.maker.incident;

import java.util.Calendar;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.incident.IncidentSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Artisan de recherche pour les incidents.
 
 * @version $Revision$ $Date$
 */
public class IncidentSearchCriteriaMaker
    extends AbstractCriteriaMaker
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6424292792443550242L;

    /**
     * Constructeur par défaut.
     */
    public IncidentSearchCriteriaMaker()
    {
        super(IncidentSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit)
    {
        final IncidentSearchCriteria crit = (IncidentSearchCriteria) searchCrit;

        // Critères sur Essai
        this.handleCriteriaEssai(criteria,
                                 crit);

        // Date de début
        if (crit.getDateDebut() != null)
        {
            criteria.add(Restrictions.ge("date",
                                         crit.getDateDebut()));
        }

        // Date de fin
        if (crit.getDateFin() != null)
        {
            final Calendar cal = Calendar.getInstance();
            cal.setTime(crit.getDateFin().getTime());
            // Ajout d'un jour pour gérer les heures
            cal.add(Calendar.DAY_OF_MONTH,
                    1);
            criteria.add(Restrictions.le("date",
                                         cal));
        }
    }

    /**
     * Méthode en charge de gérer les critères posés sur les essais.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche sur Incident.
     */
    private void handleCriteriaEssai(final Criteria criteria,
                                     final IncidentSearchCriteria crit)
    {
        // Essai
        if (crit.getEssai() != null)
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "essai",
                                          crit.getEssai());
        }

        // Liste d'essais
        if (crit.getEssais() != null)
        {
            CriteriaMakerUtils.addInCritere(criteria,
                                            "essai",
                                            crit.getEssais().toArray(new Essai[crit
                                                    .getEssais()
                                                    .size()]));
        }
    }

}

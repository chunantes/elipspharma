package fr.pharma.eclipse.dictionary.maker.patient;

import org.hibernate.Criteria;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.patient.InclusionSearchCriteria;

/**
 * Artisan de recherche pour les inclusions.
 
 * @version $Revision$ $Date$
 */
public class InclusionSearchCriteriaMaker
    extends AbstractCriteriaMaker
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -4441012063893341303L;

    /**
     * Constructeur par défaut.
     */
    public InclusionSearchCriteriaMaker()
    {
        super(InclusionSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit)
    {
        final InclusionSearchCriteria crit = (InclusionSearchCriteria) searchCrit;

        // Essai
        if (crit.getEssai() != null)
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "essai",
                                          crit.getEssai());
        }

        // PAtient
        if (crit.getPatient() != null)
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "patient",
                                          crit.getPatient());
        }

        // Actif
        if (crit.getActif() != null)
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "actif",
                                          crit.getActif());
        }

        // // Date de début
        // if (crit.getDateDebut() != null)
        // {
        // criteria.add(Restrictions.ge("dateDispensation",
        // crit.getDateDebut()));
        // }
        //
        // // Date de fin
        // if (crit.getDateFin() != null)
        // {
        // final Calendar fin = Calendar.getInstance(EclipseConstants.LOCALE);
        // fin.setTime(crit.getDateFin().getTime());
        // // Ajout d'un jour
        // fin.add(Calendar.DAY_OF_MONTH,
        // 1);
        // criteria.add(Restrictions.le("datePrescription",
        // fin));
        // }

    }
}

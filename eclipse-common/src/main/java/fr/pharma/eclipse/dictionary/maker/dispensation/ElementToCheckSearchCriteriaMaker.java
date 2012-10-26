package fr.pharma.eclipse.dictionary.maker.dispensation;

import java.util.Calendar;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.dispensation.ElementToCheckSearchCriteria;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Artisan de recherche pour les ElementToCheck.
 
 * @version $Revision$ $Date$
 */
public class ElementToCheckSearchCriteriaMaker
    extends AbstractCriteriaMaker
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 867636352829179156L;

    /**
     * Constructeur par défaut.
     */
    public ElementToCheckSearchCriteriaMaker()
    {
        super(ElementToCheckSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit)
    {
        final ElementToCheckSearchCriteria crit = (ElementToCheckSearchCriteria) searchCrit;

        final Criteria critDispensation = criteria.createCriteria("dispensation");
        final Criteria critPrescription = critDispensation.createCriteria("prescription");
        final Criteria critInclusion = critPrescription.createCriteria("inclusion");

        // Type
        if (crit.getType() != null)
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "type",
                                          crit.getType());
        }

        // Essai
        if (crit.getEssai() != null)
        {
            CriteriaMakerUtils.addCritere(critInclusion,
                                          "essai",
                                          crit.getEssai());
        }

        // Patient
        if (crit.getPatient() != null)
        {
            CriteriaMakerUtils.addCritere(critInclusion,
                                          "patient",
                                          crit.getPatient());
        }

        // Pharmacie
        if (crit.getPharmacie() != null)
        {
            CriteriaMakerUtils.addCritere(critDispensation,
                                          "pharmacie",
                                          crit.getPharmacie());
        }

        // Date de début
        if (crit.getDateDebut() != null)
        {
            critDispensation.add(Restrictions.ge("dateDispensation",
                                                 crit.getDateDebut()));
        }

        // Date de fin
        if (crit.getDateFin() != null)
        {
            final Calendar fin = Calendar.getInstance(EclipseConstants.LOCALE);
            fin.setTime(crit.getDateFin().getTime());
            // Ajout d'un jour
            fin.add(Calendar.DAY_OF_MONTH,
                    1);
            critDispensation.add(Restrictions.le("dateDispensation",
                                                 fin));
        }

    }

}

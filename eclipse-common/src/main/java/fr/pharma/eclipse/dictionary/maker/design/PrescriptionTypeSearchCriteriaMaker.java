package fr.pharma.eclipse.dictionary.maker.design;

import org.hibernate.Criteria;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.design.PrescriptionTypeSearchCriteria;

/**
 * Artisan de recherche pour les prescription type.
 
 * @version $Revision$ $Date$
 */
public class PrescriptionTypeSearchCriteriaMaker
    extends AbstractCriteriaMaker
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5396246636387414477L;

    /**
     * Constructeur par défaut.
     */
    public PrescriptionTypeSearchCriteriaMaker()
    {
        super(PrescriptionTypeSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit)
    {
        final PrescriptionTypeSearchCriteria crit = (PrescriptionTypeSearchCriteria) searchCrit;

        // Essai
        if (null != (crit.getConditionnement()))
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "conditionnement",
                                          crit.getConditionnement());
        }
    }
}

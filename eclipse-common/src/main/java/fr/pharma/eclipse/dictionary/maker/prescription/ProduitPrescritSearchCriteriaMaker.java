package fr.pharma.eclipse.dictionary.maker.prescription;

import org.hibernate.Criteria;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.prescription.ProduitPrescritSearchCriteria;

/**
 * Artisan de recherche pour les ProduitPrescrit type.
 
 * @version $Revision$ $Date$
 */
public class ProduitPrescritSearchCriteriaMaker
    extends AbstractCriteriaMaker
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5396246636387414477L;

    /**
     * Constructeur par défaut.
     */
    public ProduitPrescritSearchCriteriaMaker()
    {
        super(ProduitPrescritSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit)
    {
        final ProduitPrescritSearchCriteria crit = (ProduitPrescritSearchCriteria) searchCrit;

        // Essai
        if (null != crit.getConditionnement())
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "conditionnement",
                                          crit.getConditionnement());
        }
        // Essai
        if (null != crit.getProduit())
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "produit",
                                          crit.getProduit());
        }
    }
}

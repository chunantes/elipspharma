package fr.pharma.eclipse.dictionary.maker.ordonnancier;

import org.hibernate.Criteria;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.ordonnancier.OrdonnancierSearchCriteria;

/**
 * Artisan de recherche pour les ordonnanciers.
 
 * @version $Revision$ $Date$
 */
public class OrdonnancierSearchCriteriaMaker
    extends AbstractCriteriaMaker
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 201990930513930334L;

    /**
     * Constructeur par défaut.
     */
    public OrdonnancierSearchCriteriaMaker()
    {
        super(OrdonnancierSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit)
    {
        final OrdonnancierSearchCriteria crit = (OrdonnancierSearchCriteria) searchCrit;

        // Pharmacie
        if (crit.getPharmacie() != null)
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "pharmacie",
                                          crit.getPharmacie());
        }
    }
}

package fr.pharma.eclipse.dictionary.maker.localisation;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.localisation.ServiceSearchCriteria;

/**
 * Artisan de recherche pour les services.
 
 * @version $Revision$ $Date$
 */
public class ServiceSearchCriteriaMaker
    extends AbstractCriteriaMaker
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6921446245100762061L;

    /**
     * Constructeur par défaut.
     */
    public ServiceSearchCriteriaMaker()
    {
        super(ServiceSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit)
    {
        final ServiceSearchCriteria crit = (ServiceSearchCriteria) searchCrit;

        // Nom
        if (StringUtils.isNotEmpty(crit.getNom()))
        {
            CriteriaMakerUtils.addSqlCritere(criteria,
                                             "this_.nom",
                                             crit.getNom());
        }

        // Pole
        if (crit.getPole() != null)
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "pole",
                                          crit.getPole());
        }
    }
}

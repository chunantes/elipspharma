package fr.pharma.eclipse.dictionary.maker.localisation;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.localisation.SiteSearchCriteria;

/**
 * Artisan de recherche pour les sites.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SiteSearchCriteriaMaker extends AbstractCriteriaMaker {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5881864146516782740L;

    /**
     * Constructeur par défaut.
     */
    public SiteSearchCriteriaMaker() {
        super(SiteSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        final SiteSearchCriteria crit = (SiteSearchCriteria) searchCrit;

        // Nom
        if (StringUtils.isNotEmpty(crit.getNom())) {
            CriteriaMakerUtils.addSqlCritere(criteria, "this_.nom", crit.getNom());
        }

        // Etablissement
        if (crit.getEtablissement() != null) {
            CriteriaMakerUtils.addCritere(criteria, "etablissement", crit.getEtablissement());
        }
    }
}

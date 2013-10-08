package fr.pharma.eclipse.dictionary.maker.stockage;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.stockage.StockageSearchCriteria;

/**
 * Artisan de recherche pour les stockages.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class StockageSearchCriteriaMaker extends AbstractCriteriaMaker {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8436407178973727303L;

    /**
     * Constructeur par défaut.
     */
    public StockageSearchCriteriaMaker() {
        super(StockageSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        final StockageSearchCriteria crit = (StockageSearchCriteria) searchCrit;
        // Nom
        if (StringUtils.isNotEmpty(crit.getNom())) {
            CriteriaMakerUtils.addSqlCritere(criteria, "this_.nom", crit.getNom());
        }

        // stockage parent
        if (crit.getHasParent().booleanValue()) {
            if (crit.getParent() != null) {
                final Criteria criteriaParent = criteria.createCriteria("parent");
                criteriaParent.add(Restrictions.idEq(crit.getParent().getId()));
            }
        } else {
            criteria.add(Restrictions.isNull("parent"));
        }

        // pharmacie du stockage
        if (crit.getPharmacie() != null) {
            final Criteria criteriaParent = criteria.createCriteria("pharmacie");
            criteriaParent.add(Restrictions.idEq(crit.getPharmacie().getId()));
        }
    }
}

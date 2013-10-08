package fr.pharma.eclipse.dictionary.maker.surcout;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.surcout.GrilleModeleSearchCriteria;

/**
 * Artisan de recherche pour les grilles Modèles.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GrilleModeleSearchCriteriaMaker extends AbstractCriteriaMaker {
    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 7281613187999636286L;

    /**
     * Constructeur.
     */
    public GrilleModeleSearchCriteriaMaker() {
        super(GrilleModeleSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        final GrilleModeleSearchCriteria crit = (GrilleModeleSearchCriteria) searchCrit;

        // Nom
        if (StringUtils.isNotEmpty(crit.getNom())) {
            CriteriaMakerUtils.addSqlCritere(criteria, "this_.nom", crit.getNom());
        }
    }
}

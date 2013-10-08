package fr.pharma.eclipse.dictionary.maker.localisation;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.localisation.PoleSearchCriteria;

/**
 * Artisan de recherche pour les poles.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PoleSearchCriteriaMaker extends AbstractCriteriaMaker {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3474958869287377467L;

    /**
     * Constructeur par défaut.
     */
    public PoleSearchCriteriaMaker() {
        super(PoleSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        final PoleSearchCriteria crit = (PoleSearchCriteria) searchCrit;

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

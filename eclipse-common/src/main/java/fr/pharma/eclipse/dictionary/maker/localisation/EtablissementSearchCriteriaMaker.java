package fr.pharma.eclipse.dictionary.maker.localisation;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.localisation.EtablissementSearchCriteria;

/**
 * Artisan de recherche pour les établissements.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EtablissementSearchCriteriaMaker extends AbstractCriteriaMaker {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -9038965597201419764L;

    /**
     * Constructeur par défaut.
     */
    public EtablissementSearchCriteriaMaker() {
        super(EtablissementSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        final EtablissementSearchCriteria crit = (EtablissementSearchCriteria) searchCrit;

        // Nom
        if (StringUtils.isNotEmpty(crit.getNom())) {
            CriteriaMakerUtils.addSqlCritere(criteria, "nom", crit.getNom());
        }
    }
}

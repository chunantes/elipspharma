package fr.pharma.eclipse.dictionary.maker.acteur;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.acteur.PromoteurSearchCriteria;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;

/**
 * Artisan de recherche pour les promoteurs.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PromoteurSearchCriteriaMaker extends AbstractCriteriaMaker {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3393609208195803906L;

    /**
     * Constructeur par défaut.
     */
    public PromoteurSearchCriteriaMaker() {
        super(PromoteurSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        final PromoteurSearchCriteria crit = (PromoteurSearchCriteria) searchCrit;

        // Raison sociale
        if (StringUtils.isNotEmpty(crit.getRaisonSociale())) {
            CriteriaMakerUtils.addSqlCritere(criteria, "raisonsociale", crit.getRaisonSociale());
        }
        // Identifiant
        if (StringUtils.isNotEmpty(crit.getIdentifiant())) {
            CriteriaMakerUtils.addSqlCritere(criteria, "identifiant", crit.getIdentifiant());
        }

        // Type de promoteur
        if (crit.getType() != null) {
            CriteriaMakerUtils.addCritere(criteria, "type", crit.getType());
        }
    }
}

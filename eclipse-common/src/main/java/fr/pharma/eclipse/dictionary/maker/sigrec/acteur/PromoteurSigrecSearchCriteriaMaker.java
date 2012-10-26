package fr.pharma.eclipse.dictionary.maker.sigrec.acteur;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.sigrec.acteur.PromoteurSigrecSearchCriteria;

/**
 * Artisan de recherche pour les promoteurs Sigrec.
 
 * @version $Revision$ $Date$
 */
public class PromoteurSigrecSearchCriteriaMaker
    extends AbstractCriteriaMaker
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3393609208195803906L;

    /**
     * Constructeur par défaut.
     */
    public PromoteurSigrecSearchCriteriaMaker()
    {
        super(PromoteurSigrecSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit)
    {
        final PromoteurSigrecSearchCriteria crit = (PromoteurSigrecSearchCriteria) searchCrit;

        // Raison sociale
        if (StringUtils.isNotEmpty(crit.getRaisonSociale()))
        {
            final Criteria critContact = criteria.createCriteria("contact",
                                                                 "contact");
            CriteriaMakerUtils.addSqlCritere(critContact,
                                             "raisonsociale",

                                             crit.getRaisonSociale());
        }

        // identifiant
        if (StringUtils.isNotEmpty(crit.getIdentifiant()))
        {
            CriteriaMakerUtils.addSqlCritere(criteria,
                                             "identifiant",
                                             crit.getIdentifiant());
        }

        // Type de promoteur
        if (crit.getType() != null)
        {
            CriteriaMakerUtils.addCritere(criteria,
                                          "type",
                                          crit.getType());
        }
    }
}

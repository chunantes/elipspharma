package fr.pharma.eclipse.dictionary.maker.stock;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.stock.LigneStockSearchCriteria;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Artisan de recherche pour les lignes de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class LigneStockSearchCriteriaMaker extends AbstractCriteriaMaker {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8442390856641648306L;

    /**
     * Constructeur par défaut.
     */
    public LigneStockSearchCriteriaMaker() {
        super(LigneStockSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {

        final LigneStockSearchCriteria crit = (LigneStockSearchCriteria) searchCrit;

        // Essai
        if (crit.getEssai() != null) {
            CriteriaMakerUtils.addCritere(criteria, "essai", crit.getEssai());
        }

        // Pharmacie
        if (crit.getPharmacie() != null) {
            CriteriaMakerUtils.addCritere(criteria, "pharmacie", crit.getPharmacie());
        }

        // Produit
        if (crit.getProduit() != null) {
            CriteriaMakerUtils.addCritere(criteria, "produit", crit.getProduit());
        }

        // Conditionnement
        if (crit.getConditionnement() != null) {
            CriteriaMakerUtils.addCritere(criteria, "conditionnement", crit.getConditionnement());
        }

        // Numéro de lot
        if (StringUtils.isNotEmpty(crit.getNumLot())) {
            CriteriaMakerUtils.addCritere(criteria, "numLot", crit.getNumLot());
        }

        // Numéro de traitement
        if(crit.getNumTraitementNA() == null || !crit.getNumTraitementNA()){
        	if (StringUtils.isNotEmpty(crit.getNumTraitement()) && !EclipseConstants.NON_APPLICABLE.equals(crit.getNumTraitement())) {
                CriteriaMakerUtils.addCritere(criteria, "numTraitement", crit.getNumTraitement());
            } else {
                criteria.add(Restrictions.isNull("numTraitement"));
            }
        }        

        if (crit.getApproApprouve() != null) {
            CriteriaMakerUtils.addCritere(criteria, "approApprouve", crit.getApproApprouve());
        }

        // Date de péremption    
        if(crit.getDatePeremptionNA() == null || !crit.getDatePeremptionNA()){
        	if (crit.getDatePeremption() != null) {
                criteria.add(Restrictions.eq("datePeremption", crit.getDatePeremption()));
            } else {
                criteria.add(Restrictions.isNull("datePeremption"));
            }
        }
    }
}

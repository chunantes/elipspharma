package fr.pharma.eclipse.dictionary.maker.stock;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.stock.RetourPatientSearchCriteria;
import fr.pharma.eclipse.domain.enums.EtatRetour;

/**
 * Artisan de recherche pour les retours de patients.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class RetourPatientSearchCriteriaMaker extends AbstractCriteriaMaker {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8442390856641648306L;

    /**
     * Constructeur par défaut.
     */
    public RetourPatientSearchCriteriaMaker() {
        super(RetourPatientSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        final RetourPatientSearchCriteria crit = (RetourPatientSearchCriteria) searchCrit;
        // Essai
        if (crit.getEssai() != null) {
            CriteriaMakerUtils.addCritere(criteria, "essai", crit.getEssai());
        }

        // Produit
        if (crit.getProduit() != null) {
            CriteriaMakerUtils.addCritere(criteria, "produit", crit.getProduit());
        }

        // Produit
        if (crit.getPatient() != null) {
            CriteriaMakerUtils.addCritere(criteria, "patient", crit.getPatient());
        }

        // Stocakge
        if (crit.getStockage() != null) {
            CriteriaMakerUtils.addCritere(criteria, "detailStockage", crit.getStockage());
        }

        // Etat
        if (crit.getEtat() != null) {
            // si l'utilisateur a sélectionné l'état aggrégé SORTI.
            if (crit.getEtat().equals(EtatRetour.SORTI)) {
                criteria.add(Restrictions.ne("etat", EtatRetour.PRESENT));
            } else {
                CriteriaMakerUtils.addCritere(criteria, "etat", crit.getEtat());
            }
        }
    }

}

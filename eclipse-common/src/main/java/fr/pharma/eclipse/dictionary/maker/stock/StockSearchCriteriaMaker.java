package fr.pharma.eclipse.dictionary.maker.stock;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.stock.StockSearchCriteria;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Artisan de recherche pour le stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class StockSearchCriteriaMaker extends AbstractCriteriaMaker {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3729802035499523042L;

    /**
     * Constructeur par défaut.
     */
    public StockSearchCriteriaMaker() {
        super(StockSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        final StockSearchCriteria crit = (StockSearchCriteria) searchCrit;

        Criteria critEssai = null;
        Criteria critPharmacie = null;

        // Essai
        if (crit.getEssai() != null) {
            critEssai = criteria.createCriteria("essai");
            critEssai.add(Restrictions.idEq(crit.getEssai().getId()));
        }

        // Essai DTO
        if (crit.getEssaiDTO() != null) {
            critEssai = criteria.createCriteria("essai");
            critEssai.add(Restrictions.idEq(crit.getEssaiDTO().getId()));
        }

        // Qté disponible en dispensation global
        if (crit.getNotNullQteDispensationGlobal() != null && crit.getNotNullQteDispensationGlobal()) {
            criteria.add(Restrictions.isNotNull("qteDispensationGlobal")).add(Restrictions.gt("qteDispensationGlobal", 0));
        }

        // Pharmacie
        if (crit.getPharmacie() != null) {
            critPharmacie = criteria.createCriteria("pharmacie");
            critPharmacie.add(Restrictions.idEq(crit.getPharmacie().getId()));
        }

        // Numéro de lot
        if (StringUtils.isNotEmpty(crit.getNumLot())) {
            CriteriaMakerUtils.addSqlCritere(criteria, "numLot", crit.getNumLot());
        }

        // Numéro de lot strict
        if (StringUtils.isNotEmpty(crit.getNumLotStrict())) {
            CriteriaMakerUtils.addCritere(criteria, "numLot", crit.getNumLotStrict());
        }

        // Numéro de traitement
        if (StringUtils.isNotEmpty(crit.getNumTraitement()) && !EclipseConstants.NON_APPLICABLE.equals(crit.getNumTraitement())) {
            CriteriaMakerUtils.addSqlCritere(criteria, "numTraitement", crit.getNumTraitement());
        }

        // Produit
        if (crit.getProduit() != null) {
            final Criteria critProd = criteria.createCriteria("produit");
            critProd.add(Restrictions.idEq(crit.getProduit().getId()));
        }

        // Conditionnement
        if (crit.getConditionnement() != null) {
            final Criteria critCond = criteria.createCriteria("conditionnement");
            critCond.add(Restrictions.idEq(crit.getConditionnement().getId()));
        }

        // Critères sur produit
        this.handleCriteriaProduit(criteria, crit);

        // Restriction par rapport aux acls des essais
        final List<Long> idsEssais = this.getAclSearchDao().findIdsEssais();
        if (critEssai == null) {
            critEssai = criteria.createCriteria("essai");
        }
        CriteriaMakerUtils.addInCritere(criteria, "essai.id", idsEssais.toArray(new Object[idsEssais.size()]));

        // Restriction par rapport aux acls des pharmacies
        final List<Long> idsPharmacies = this.getAclSearchDao().findIdsPharmacies();
        if (critPharmacie == null) {
            critPharmacie = criteria.createCriteria("pharmacie");
        }
        CriteriaMakerUtils.addInCritere(criteria, "pharmacie.id", idsPharmacies.toArray(new Object[idsPharmacies.size()]));
    }

    /**
     * Méthode en charge de gérer les critères par rapport au produit.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche sur Stock.
     */
    private void handleCriteriaProduit(final Criteria criteria,
                                       final StockSearchCriteria crit) {
        // Produit
        if (StringUtils.isNotEmpty(crit.getDenominationProduit()) || crit.getStockage() != null) {
            final Criteria critProduit = criteria.createCriteria("produit", "produit");
            if (StringUtils.isNotEmpty(crit.getDenominationProduit())) {
                CriteriaMakerUtils.addSqlCritere(critProduit, "{alias}.denomination", crit.getDenominationProduit());
            }
            if (crit.getStockage() != null) {
                // La création des trois Criteria évite un sqlgrammarexception
                // qu'on voit si
                // le path detailLogistique.detailsStockages.stockage est
                // dans un seul Criteria
                final Criteria critDetailLogistique = critProduit.createCriteria("detailLogistique", "detailLogistique");
                final Criteria critDetailsStockages = critDetailLogistique.createCriteria("detailsStockages", "detailsStockages");
                final Criteria critStockage = critDetailsStockages.createCriteria("stockage", "stockage");
                CriteriaMakerUtils.addInCritere(critStockage, "stockage.id", new Long[]{crit.getStockage().getId() });
            }
        }
    }

}

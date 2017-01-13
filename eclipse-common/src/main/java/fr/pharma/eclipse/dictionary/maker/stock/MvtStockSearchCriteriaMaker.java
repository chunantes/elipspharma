package fr.pharma.eclipse.dictionary.maker.stock;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Artisan de recherche pour les mouvements de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class MvtStockSearchCriteriaMaker extends AbstractCriteriaMaker {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8442390856641648306L;

    /**
     * Constructeur par défaut.
     */
    public MvtStockSearchCriteriaMaker() {
        super(MvtStockSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {

        final MvtStockSearchCriteria crit = (MvtStockSearchCriteria) searchCrit;
        Criteria critEssai = null;
        Criteria critPharmacie = null;

        // Essai DTO
        if (crit.getEssaiDTO() != null) {
            critEssai = criteria.createCriteria("essai");
            critEssai.add(Restrictions.idEq(crit.getEssaiDTO().getId()));
        } else
        // Essai
        if (crit.getEssai() != null) {
            critEssai = criteria.createCriteria("essai");
            critEssai.add(Restrictions.idEq(crit.getEssai().getId()));
        }


        // Pharmacie
        if (crit.getPharmacie() != null) {
            critPharmacie = criteria.createCriteria("pharmacie");
            critPharmacie.add(Restrictions.idEq(crit.getPharmacie().getId()));
        }
        // Stockage
        if (crit.getStockage() != null) {
            CriteriaMakerUtils.addCritere(criteria, "produit", crit.getStockage().getDetailLogistique().getProduit());
        }

        // Conditionnement
        if (crit.getConditionnement() != null) {
            CriteriaMakerUtils.addCritere(criteria, "conditionnement", crit.getConditionnement());
        }

        // Mode de prescriptiion
        if (crit.getModePrescription() != null) {
            final Criteria conditionnementCriteria = criteria.createCriteria("conditionnement");
            CriteriaMakerUtils.addCritere(conditionnementCriteria, "modePrescription", crit.getConditionnement());
        }

        // Type de mouvement
        if (crit.getTypeMouvement() != null) {
            CriteriaMakerUtils.addCritere(criteria, "type", crit.getTypeMouvement());
        }
        // Types de mouvement
        if (crit.getTypesMouvement() != null) {
            CriteriaMakerUtils.addInCritere(criteria, "type", crit.getTypesMouvement());
        }
        // Numéro de lot
        if (StringUtils.isNotEmpty(crit.getNumLot())) {
            if (crit.isSimilarToEnabled()) {
                CriteriaMakerUtils.addSqlCritere(criteria, "numLot", crit.getNumLot());
            } else {
                CriteriaMakerUtils.addCritere(criteria, "numLot", crit.getNumLot());
            }
        }
        // Numéro de traitement
        if (StringUtils.isNotEmpty(crit.getNumTraitement()) && !EclipseConstants.NON_APPLICABLE.equals(crit.getNumTraitement())) {
            if (crit.isSimilarToEnabled()) {
                CriteriaMakerUtils.addSqlCritere(criteria, "numTraitement", crit.getNumTraitement());
            } else {
                CriteriaMakerUtils.addCritere(criteria, "numTraitement", crit.getNumTraitement());
            }
        }

        // Numéro ordonnancier
        if (crit.getNotNullNumOrdonnancier() != null && crit.getNotNullNumOrdonnancier()) {
            criteria.add(Restrictions.isNotNull("numOrdonnancier"));
        }

        // stérile
        if (crit.getSterile() != null) {
            CriteriaMakerUtils.addCritere(criteria, "sterile", crit.getSterile());
        }

        if (crit.getApproApprouve() != null) {
            CriteriaMakerUtils.addCritere(criteria, "approApprouve", crit.getApproApprouve());
        }

        this.handleCriteriaForDispensationGlobale(criteria, crit);

        // Critères sur produit
        this.handleCriteriaProduit(criteria, crit);

        // Critères sur date
        this.handleCriteriaDate(criteria, crit);

        if (crit.getWithAcl()) {
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
    }

    /**
     * @param criteria
     * @param crit
     */
    private void handleCriteriaForDispensationGlobale(final Criteria criteria,
                                                      final MvtStockSearchCriteria crit) {
        if (crit.getTypeMouvement() != null && crit.getTypeMouvement().equals(TypeMvtStock.DOTATION)) {
            if (crit.getService() != null) {
                CriteriaMakerUtils.addCritere(criteria, "dotation.service", crit.getService());
            }

            if (crit.getDispenseNominativement() != null) {
                criteria.add(Restrictions.ltProperty("quantiteDispensee", "quantite"));
            }
        }

    }

    /**
     * Méthode en charge de gérer les critères par rapport au produit.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche sur Mouvement.
     */
    private void handleCriteriaProduit(final Criteria criteria,
                                       final MvtStockSearchCriteria crit) {
        // Produit
        if (crit.getProduit() != null) {
            CriteriaMakerUtils.addCritere(criteria, "produit", crit.getProduit());
        }
        // Dénomination du produit
        if (StringUtils.isNotEmpty(crit.getDenominationProduit())) {
            final Criteria critProduit = criteria.createCriteria("produit", "produit");
            CriteriaMakerUtils.addSqlCritere(critProduit, "{alias}.denomination", crit.getDenominationProduit());
        }
    }

    /**
     * Méthode en charge de gérer les critères par rapport à la date de création
     * des mouvements de stock.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche sur Mouvement.
     */
    private void handleCriteriaDate(final Criteria criteria,
                                    final MvtStockSearchCriteria crit) {
        // Date de début
        if (crit.getDateDebut() != null) {
            criteria.add(Restrictions.ge("dateCreation", crit.getDateDebut()));
        }

        // Date de fin
        if (crit.getDateFin() != null) {
            final Calendar fin = Calendar.getInstance(EclipseConstants.LOCALE);
            fin.setTime(crit.getDateFin().getTime());
            // Ajout d'un jour
            fin.add(Calendar.DAY_OF_MONTH, 1);
            criteria.add(Restrictions.le("dateCreation", fin));
        }

        // Date de péremption
        if (crit.getDatePeremption() != null) {
            criteria.add(Restrictions.eq("datePeremption", crit.getDatePeremption()));
        } else if (crit.isDatePeremptionIsNull()) {
            criteria.add(Restrictions.isNull("datePeremption"));
        }

    }

}

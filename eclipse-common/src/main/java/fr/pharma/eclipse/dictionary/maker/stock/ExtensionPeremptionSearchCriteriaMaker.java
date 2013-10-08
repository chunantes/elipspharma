package fr.pharma.eclipse.dictionary.maker.stock;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.stock.ExtensionPeremptionSearchCriteria;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Artisan de recherche pour les extensions de péremption.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ExtensionPeremptionSearchCriteriaMaker extends AbstractCriteriaMaker {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7959887256268619681L;

    /**
     * Constructeur par défaut.
     */
    public ExtensionPeremptionSearchCriteriaMaker() {
        super(ExtensionPeremptionSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        final ExtensionPeremptionSearchCriteria crit = (ExtensionPeremptionSearchCriteria) searchCrit;

        // Essai + Pharmacie
        this.handleCriteriaEssaiPharmacie(criteria, crit);

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
            CriteriaMakerUtils.addSqlCritere(criteria, "numLot", crit.getNumLot());
        }

        // Numéro de traitement
        if (StringUtils.isNotEmpty(crit.getNumTraitement())) {
            CriteriaMakerUtils.addSqlCritere(criteria, "numTraitement", crit.getNumTraitement());
        }
        // Critères sur date
        this.handleCriteriaDatePeremption(criteria, crit);
    }

    /**
     * Méthode en charge de gérer les critères sur Essai et Pharmacie.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche sur l'extension de péremption.
     */
    private void handleCriteriaEssaiPharmacie(final Criteria criteria,
                                              final ExtensionPeremptionSearchCriteria crit) {

        Criteria critEssai = null;
        Criteria critPharmacie = null;

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
     * Méthode en charge de gérer les critères par rapport à la date de
     * péremption sur Approvisionnement.
     * @param criteria Criteria Hibernate.
     * @param crit Critère de recherche sur l'extension de péremption.
     */
    private void handleCriteriaDatePeremption(final Criteria criteria,
                                              final ExtensionPeremptionSearchCriteria crit) {
        // Date de début
        if (crit.getDateDebut() != null) {
            criteria.add(Restrictions.ge("datePeremption", crit.getDateDebut()));
        }

        // Date de fin
        if (crit.getDateFin() != null) {
            final Calendar fin = Calendar.getInstance(EclipseConstants.LOCALE);
            fin.setTime(crit.getDateFin().getTime());
            // Ajout d'un jour
            fin.add(Calendar.DAY_OF_MONTH, 1);
            criteria.add(Restrictions.le("datePeremption", fin));
        }
    }

}

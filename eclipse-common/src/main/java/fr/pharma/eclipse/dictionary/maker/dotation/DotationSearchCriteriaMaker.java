package fr.pharma.eclipse.dictionary.maker.dotation;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.dotation.DotationSearchCriteria;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Artisan de recherche pour les dotations.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DotationSearchCriteriaMaker extends AbstractCriteriaMaker {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2250846651261257044L;

    /**
     * Constructeur par défaut.
     */
    public DotationSearchCriteriaMaker() {
        super(DotationSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        final DotationSearchCriteria crit = (DotationSearchCriteria) searchCrit;
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

        // Service
        if (crit.getService() != null) {
            CriteriaMakerUtils.addCritere(criteria, "service", crit.getService());
        }

        // Produit
        if (crit.getProduit() != null) {
            CriteriaMakerUtils.addCritere(criteria, "produit", crit.getProduit());
        }

        // Traitee
        if (crit.getTraitee() != null) {
            CriteriaMakerUtils.addCritere(criteria, "traitee", crit.getTraitee());
        }

        // Date de début
        if (crit.getDateDebut() != null) {
            criteria.add(Restrictions.ge("dateDemande", crit.getDateDebut()));
        }

        // Date de fin
        if (crit.getDateFin() != null) {
            final Calendar fin = Calendar.getInstance(EclipseConstants.LOCALE);
            fin.setTime(crit.getDateFin().getTime());
            // Ajout d'un jour
            fin.add(Calendar.DAY_OF_MONTH, 1);
            criteria.add(Restrictions.le("dateDemande", fin));
        }

        // Alerte sur produit actif
        if (crit.getAlerteActiveProduit() != null && Boolean.TRUE.equals(crit.getAlerteActiveProduit())) {
            final Criteria critProduit = criteria.createCriteria("produit", CriteriaSpecification.INNER_JOIN);
            CriteriaMakerUtils.addCritere(critProduit, "alerteActive", Boolean.TRUE);
        }

        // Liste d'identifiants d'essais
        if (crit.getIdsEssais() != null && crit.getIdsEssais().size() > 0) {
            critEssai = criteria.createCriteria("essai");
            CriteriaMakerUtils.addInCritere(criteria, "essai.id", crit.getIdsEssais().toArray(new Long[crit.getIdsEssais().size()]));
        }

        // Liste d'identifiants de pharmacies
        if (crit.getIdsPharmacies() != null && crit.getIdsPharmacies().size() > 0) {
            critPharmacie = criteria.createCriteria("pharmacie");
            CriteriaMakerUtils.addInCritere(criteria, "pharmacie.id", crit.getIdsPharmacies().toArray(new Long[crit.getIdsPharmacies().size()]));
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

}

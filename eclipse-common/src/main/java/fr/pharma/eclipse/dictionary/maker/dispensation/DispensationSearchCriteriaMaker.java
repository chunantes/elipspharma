package fr.pharma.eclipse.dictionary.maker.dispensation;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.dispensation.DispensationSearchCriteria;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Artisan de recherche pour les Dispensations.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationSearchCriteriaMaker extends AbstractCriteriaMaker {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -1878191353808518875L;

    /**
     * Constructeur par défaut.
     */
    public DispensationSearchCriteriaMaker() {
        super(DispensationSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {
        final DispensationSearchCriteria crit = (DispensationSearchCriteria) searchCrit;

        final Criteria critPrescription = criteria.createCriteria("prescription");
        final Criteria critInclusion = critPrescription.createCriteria("inclusion");
        Criteria critPharmacie = null;
        Criteria critEssai = null;

        // Patient
        if (crit.getPatient() != null) {
            CriteriaMakerUtils.addCritere(critInclusion, "patient", crit.getPatient());
        }

        // Pharmacie
        if (crit.getPharmacie() != null) {
            critPharmacie = criteria.createCriteria("pharmacie");
            critPharmacie.add(Restrictions.idEq(crit.getPharmacie().getId()));
        }

        // Essai
        if (crit.getEssai() != null) {
            critEssai = critInclusion.createCriteria("essai");
            critEssai.add(Restrictions.idEq(crit.getEssai().getId()));
        }

        // Essai DTO
        if (crit.getEssaiDTO() != null) {
            critEssai = critInclusion.createCriteria("essai");
            critEssai.add(Restrictions.idEq(crit.getEssaiDTO().getId()));
        }

        // Dispensé
        if (crit.getDispense() != null) {
            CriteriaMakerUtils.addCritere(criteria, "dispense", crit.getDispense());
        }

        // Date de début
        if (crit.getDateDebut() != null) {
            criteria.add(Restrictions.ge("dateDispensation", crit.getDateDebut()));
        }

        // Date de fin
        if (crit.getDateFin() != null) {
            final Calendar fin = Calendar.getInstance(EclipseConstants.LOCALE);
            fin.setTime(crit.getDateFin().getTime());
            // Ajout d'un jour
            fin.add(Calendar.DAY_OF_MONTH, 1);
            criteria.add(Restrictions.le("dateDispensation", fin));
        }

        // Restriction par rapport aux acls des essais
        final List<Long> idsEssais = this.getAclSearchDao().findIdsEssais();
        if (critEssai == null) {
            critEssai = critInclusion.createCriteria("essai");
        }
        CriteriaMakerUtils.addInCritere(critInclusion, "essai.id", idsEssais.toArray(new Object[idsEssais.size()]));

        // Restriction par rapport aux acls des pharmacies
        final List<Long> idsPharmacies = this.getAclSearchDao().findIdsPharmacies();
        if (critPharmacie == null) {
            critPharmacie = criteria.createCriteria("pharmacie");
        }
        CriteriaMakerUtils.addInCritere(criteria, "pharmacie.id", idsPharmacies.toArray(new Object[idsPharmacies.size()]));

    }
}

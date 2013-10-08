package fr.pharma.eclipse.dictionary.maker.prescription;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import fr.pharma.eclipse.dictionary.maker.common.AbstractCriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.prescription.PrescriptionSearchCriteria;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Artisan de recherche pour les prescriptions.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PrescriptionSearchCriteriaMaker extends AbstractCriteriaMaker {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3474958869287377467L;

    /**
     * Constructeur par défaut.
     */
    public PrescriptionSearchCriteriaMaker() {
        super(PrescriptionSearchCriteria.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void transform(final Criteria criteria,
                          final SearchCriteria searchCrit) {

        final PrescriptionSearchCriteria crit = (PrescriptionSearchCriteria) searchCrit;
        final Criteria critInclusion = criteria.createCriteria("inclusion");
        Criteria critEssai = null;

        // Nom
        if (crit.getInclusion() != null) {
            CriteriaMakerUtils.addCritere(criteria, "inclusion", crit.getInclusion());
        }

        // Sequence
        if (crit.getSequence() != null) {
            CriteriaMakerUtils.addCritere(criteria, "sequence", crit.getSequence());
        }

        // PATIENT
        if (crit.getPatient() != null) {
            CriteriaMakerUtils.addCritere(critInclusion, "patient", crit.getPatient());
        }

        // Essai
        if (crit.getEssai() != null) {
            critEssai = critInclusion.createCriteria("essai", "essai");
            critEssai.add(Restrictions.eq("essai.id", crit.getEssai().getId()));
        }

        // Essai DTO
        if (crit.getEssaiDTO() != null) {
            critEssai = critInclusion.createCriteria("essai", "essai");
            critEssai.add(Restrictions.eq("essai.id", crit.getEssaiDTO().getId()));
        }

        // Dispensé ?
        if (crit.getDispense() != null) {
            CriteriaMakerUtils.addCritere(criteria, "dispense", crit.getDispense());
        }

        // critères sur les prescriptions avec dispensations non dispensées
        if (crit.isWithDispensationsNonDispenseesOnly()) {
            final Criteria critDispensations = criteria.createCriteria("dispensations", "dispensations");
            CriteriaMakerUtils.addCritere(critDispensations, "dispense", false);
        }

        // Date de début
        if (crit.getDateDebut() != null) {
            criteria.add(Restrictions.ge("datePrescription", crit.getDateDebut()));
        }

        // Date de fin
        if (crit.getDateFin() != null) {
            final Calendar fin = Calendar.getInstance(EclipseConstants.LOCALE);
            fin.setTime(crit.getDateFin().getTime());
            // Ajout d'un jour
            fin.add(Calendar.DAY_OF_MONTH, 1);
            criteria.add(Restrictions.le("datePrescription", fin));
        }

        // Restriction par rapport aux acls des essais
        final List<Long> idsEssais = this.getAclSearchDao().findIdsEssais();
        if (critEssai == null) {
            critEssai = critInclusion.createCriteria("essai", "essai");
        }
        CriteriaMakerUtils.addInCritere(criteria, "essai.id", idsEssais.toArray(new Object[idsEssais.size()]));
    }

}

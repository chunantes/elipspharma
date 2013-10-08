package fr.pharma.eclipse.dao.search;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import fr.pharma.eclipse.dictionary.maker.common.utils.CriteriaMakerUtils;
import fr.pharma.eclipse.domain.dto.EssaiDTO;
import fr.pharma.eclipse.domain.dto.PrescriptionDTO;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.prescription.Prescription;

/**
 * Classe de DAO de recherche des prescriptions.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PrescriptionSearchDao extends CommonSearchDao implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -7763131630983476744L;

    /**
     * DAO de recherche des ACLs.
     */
    @Autowired
    private AclSearchDao aclSearchDao;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<PrescriptionDTO> findByEssaiAndPatientAndDispenseOrderByIdAsc(final EssaiDTO essai,
                                                                              final Patient patient,
                                                                              final Boolean dispense) {

        final Criteria crit = this.createCriteria(Prescription.class);

        final Criteria critInclusion = crit.createCriteria("inclusion", "inclusion", CriteriaSpecification.INNER_JOIN);
        critInclusion.createCriteria("patient", "patient", CriteriaSpecification.INNER_JOIN);
        final Criteria critEssai = critInclusion.createCriteria("essai", "essai", CriteriaSpecification.INNER_JOIN);
        critEssai.createCriteria("detailDonneesPharma", "detailDonneesPharma", CriteriaSpecification.INNER_JOIN);

        crit.createCriteria("dispensations", "dispensations", CriteriaSpecification.LEFT_JOIN);

        // Projections
        crit.setProjection(Projections.projectionList().add(Projections.count("dispensations.id").as("nbDispensations")).add(Projections.groupProperty("id"), "id")
                .add(Projections.groupProperty("essai.id"), "idEssai").add(Projections.groupProperty("essai.nom"), "nomEssai")
                .add(Projections.groupProperty("essai.codePromoteur"), "codePromoteur").add(Projections.groupProperty("patient.nom"), "nomPatient")
                .add(Projections.groupProperty("patient.prenom"), "prenomPatient").add(Projections.groupProperty("patient.initiales"), "initialesPatient")
                .add(Projections.groupProperty("numPrescription"), "numPrescription").add(Projections.groupProperty("dispense"), "dispense")
                .add(Projections.groupProperty("inclusion.numInclusion"), "numInclusion").add(Projections.groupProperty("essai.etat"), "etatEssai")
                .add(Projections.groupProperty("inclusion.actif"), "inclusionActive").add(Projections.groupProperty("datePrescription"), "datePrescription")
                .add(Projections.groupProperty("detailDonneesPharma.infosDispensations.typeDispensation"), "typeDispensation"));
        crit.setResultTransformer(Transformers.aliasToBean(PrescriptionDTO.class));

        // Restrictions
        if (patient != null) {
            CriteriaMakerUtils.addCritere(critInclusion, "patient", patient);
        }

        // Essai
        if (essai != null) {
            critEssai.add(Restrictions.idEq(essai.getId()));
        }

        // Dispensé ?
        if (dispense != null) {
            crit.add(Restrictions.eq("this.dispense", dispense));
        }

        // Tri par id ascendant
        crit.addOrder(Order.asc("this.id"));

        // Restriction sur les droits de voir les essais
        final List<Long> idsEssais = this.aclSearchDao.findIdsEssais();
        CriteriaMakerUtils.addInCritere(critEssai, "essai.id", idsEssais.toArray(new Object[idsEssais.size()]));

        return crit.list();
    }
}

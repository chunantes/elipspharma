package fr.pharma.eclipse.service.surcout.counter.impl;

import java.io.Serializable;
import java.util.Calendar;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.prescription.PrescriptionSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.service.prescription.PrescriptionService;
import fr.pharma.eclipse.service.surcout.counter.ActeCounter;

/**
 * Classe en charge de compter le nombre de prescriptions pour un essai ou un
 * patient dans un essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PrescriptionCounter implements ActeCounter, Serializable {
    /**
     * Serial UID.
     */
    private static final long serialVersionUID = 1862468049591120070L;

    /**
     * Service dispensation.
     */
    @Resource(name = "prescriptionService")
    private PrescriptionService prescriptionService;

    /**
     * Méthode en charge de compter le nombre de dispensation pour l'essai (ou
     * pour le patient si le patient n'est pas null) dans l'intervalle en
     * paramètre.
     * @param essai L'essai.
     * @param patient Le patient (si null alors le calcul se fait pour l'essai).
     * @param dateDebut Date Début de l'intervalle.
     * @param dateFin Date de fin de l'intervalle.
     * @return Le nombre de dispensations.
     */
    @Override
    public int process(final Essai essai,
                       final Patient patient,
                       final Calendar dateDebut,
                       final Calendar dateFin) {
        final PrescriptionSearchCriteria criteria = new PrescriptionSearchCriteria();
        criteria.setEssai(essai);
        criteria.setPatient(patient);
        criteria.setDateDebut(dateDebut);
        criteria.setDateFin(dateFin);
        return this.prescriptionService.getAll(criteria).size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int process(final Essai essai,
                       final Patient patient,
                       final DonneesPrevision prevision) {
        return prevision.getNbPrescriptions();
    }

    /**
     * Setter pour dispensationService.
     * @param prescriptionService le dispensationService à écrire.
     */
    public void setPrescriptionService(final PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

}

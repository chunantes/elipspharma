package fr.pharma.eclipse.utils;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.contact.DetailContacts;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.prescription.Prescription;

/**
 * Classe utilitaire pour les jeux de tests de Prescription.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PrescriptionUtils {
    public static Prescription makePrescriptionTest(final Long id) {

        final Prescription prescription = new Prescription();
        prescription.setId(id);
        final Essai essai = new Essai();
        essai.setDetailContacts(new DetailContacts());
        final Inclusion inclusion = new Inclusion();
        inclusion.setEssai(essai);
        prescription.setInclusion(inclusion);
        return prescription;
    }
}

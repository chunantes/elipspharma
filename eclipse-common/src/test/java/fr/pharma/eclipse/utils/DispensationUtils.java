package fr.pharma.eclipse.utils;

import fr.pharma.eclipse.domain.model.dispensation.Dispensation;

/**
 * Classe utilitaire pour les jeux de tests de Prescription.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationUtils {
    /**
     * Retour une dispensation.
     * @param id Id.
     * @return La dispensation.
     */
    public static Dispensation makeDispensationTest(final Long id) {
        final Dispensation dispensation = new Dispensation();
        dispensation.setId(id);
        dispensation.setPrescription(PrescriptionUtils.makePrescriptionTest(1L));
        return dispensation;
    }
}

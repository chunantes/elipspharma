package fr.pharma.eclipse.service.stock;

import java.util.List;

import fr.pharma.eclipse.domain.model.stock.RetourPatient;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Service de gestion des retours patients.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface RetourPatientService extends GenericService<RetourPatient> {
    /**
     * Méthode en charge de sauvegarder uine liste de retours patient.
     * @param retours La liste de retours.
     * @return Laliste de retours sauvegardée.
     */
    List<RetourPatient> save(final List<RetourPatient> retours);
}

package fr.pharma.eclipse.service.patient;

import java.util.List;

import fr.pharma.eclipse.domain.enums.patient.FormuleSurfaceCorporelle;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.HistoriquePatient;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Interface de service de gestion de Patient.
 
 * @version $Revision$ $Date$
 */
public interface PatientService
    extends GenericService<Patient>
{

    /**
     * Méthode en charge de mettre à jour la surface corporelle de l'objet HistoriquePatient en
     * paramètre.
     * @param historique Objet HistoriquePatient.
     * @return la formule utilisée.
     */
    FormuleSurfaceCorporelle updateSurfaceCorporelle(HistoriquePatient historique);

    /**
     * Méthode en charge de retourner l'inclusion active si elle existe.
     * @param patient Le patient.
     * @return L'inclusion active ou null si il n'y en a pas.
     */
    Inclusion getInclusionCourante(final Patient patient);

    /**
     * Méthode en charge de sortir le patient de son essai courant.
     * @param patient Le patient.
     */
    void desinclure(final Patient patient);

    /**
     * Méthode en charge de retourner la liste des patients inclu dans un essai donné.
     * @param essai L'essai.
     * @return La liste des patients.
     */
    List<Patient> getAllPatientsForEssai(final Essai essai);
}

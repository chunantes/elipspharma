package fr.pharma.eclipse.domain.model.suivi.patient;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;

/**
 * Bean métier représentant les suivis de modifications sur le bean Patient.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "patient_suivi")
public class PatientSuivi
    extends Suivi
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5085178908346730004L;

    /**
     * Objet Patient.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient", nullable = false)
    @Index(name = "idx_suivi_patient")
    private Patient patient;

    /**
     * Getter sur patient.
     * @return Retourne le patient.
     */
    public Patient getPatient()
    {
        return this.patient;
    }

    /**
     * Setter pour patient.
     * @param patient le patient à écrire.
     */
    public void setPatient(final Patient patient)
    {
        this.patient = patient;
    }

}

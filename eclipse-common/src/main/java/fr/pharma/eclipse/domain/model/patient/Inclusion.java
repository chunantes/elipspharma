package fr.pharma.eclipse.domain.model.patient;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Classe du modèle représentant une inclusion.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "inclusion")
public class Inclusion extends BeanObject {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -5807423446843847546L;

    /**
     * Patient.
     */
    @ManyToOne
    @JoinColumn(name = "id_patient", nullable = false)
    @Index(name = "idx_patient_inclusion")
    @NotNull
    private Patient patient;

    /**
     * Essai.
     */
    @ManyToOne
    @JoinColumn(name = "id_essai", nullable = false)
    @Index(name = "idx_essai_inclusion")
    @NotNull
    private Essai essai;

    /**
     * Numéro d'inclusion.
     */
    @Column(name = "numInclusion")
    private String numInclusion;

    /**
     * Numéro de randomisation.
     */
    @Column(name = "numRandomisation")
    private String numRandomisation;

    /**
     * Inclusion active.
     */
    @Column(name = "actif")
    private Boolean actif;

    /**
     * Date d'inclusion.
     */
    @Column(name = "dateInclusion")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateInclusion;

    /**
     * Date de désinclusion.
     */
    @Column(name = "dateDesinclusion")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateDesinclusion;

    /**
     * Getter sur patient.
     * @return Retourne le patient.
     */
    public Patient getPatient() {
        return this.patient;
    }

    /**
     * Setter pour patient.
     * @param patient le patient à écrire.
     */
    public void setPatient(final Patient patient) {
        this.patient = patient;
    }

    /**
     * Getter sur essai.
     * @return Retourne le essai.
     */
    public Essai getEssai() {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai le essai à écrire.
     */
    public void setEssai(final Essai essai) {
        this.essai = essai;
    }

    /**
     * Getter sur numInclusion.
     * @return Retourne le numInclusion.
     */
    public String getNumInclusion() {
        return this.numInclusion;
    }

    /**
     * Setter pour numInclusion.
     * @param numInclusion le numInclusion à écrire.
     */
    public void setNumInclusion(final String numInclusion) {
        this.numInclusion = numInclusion;
    }

    /**
     * Getter sur numRandomisation.
     * @return Retourne le numRandomisation.
     */
    public String getNumRandomisation() {
        return this.numRandomisation;
    }

    /**
     * Setter pour numRandomisation.
     * @param numRandomisation le numRandomisation à écrire.
     */
    public void setNumRandomisation(final String numRandomisation) {
        this.numRandomisation = numRandomisation;
    }

    /**
     * Getter sur actif.
     * @return Retourne le actif.
     */
    public Boolean getActif() {
        return this.actif;
    }

    /**
     * Setter pour actif.
     * @param actif le actif à écrire.
     */
    public void setActif(final Boolean actif) {
        this.actif = actif;
    }

    /**
     * Getter sur dateInclusion.
     * @return Retourne le dateInclusion.
     */
    public Calendar getDateInclusion() {
        return this.dateInclusion;
    }

    /**
     * Setter pour dateInclusion.
     * @param dateInclusion le dateInclusion à écrire.
     */
    public void setDateInclusion(final Calendar dateInclusion) {
        this.dateInclusion = dateInclusion;
    }

    /**
     * Getter sur dateDesinclusion.
     * @return Retourne le dateDesinclusion.
     */
    public Calendar getDateDesinclusion() {
        return this.dateDesinclusion;
    }

    /**
     * Setter pour dateDesinclusion.
     * @param dateDesinclusion le dateDesinclusion à écrire.
     */
    public void setDateDesinclusion(final Calendar dateDesinclusion) {
        this.dateDesinclusion = dateDesinclusion;
    }

}

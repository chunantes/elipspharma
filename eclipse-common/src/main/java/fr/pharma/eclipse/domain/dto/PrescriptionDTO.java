package fr.pharma.eclipse.domain.dto;

import java.io.Serializable;
import java.util.Calendar;

import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.enums.TypeDispensation;

/**
 * Classe DTO de prescription.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PrescriptionDTO implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2728858767771039746L;

    /**
     * Identifiant de prescription.
     */
    private Long id;

    /**
     * Identifiant technique de l'essai.
     */
    private Long idEssai;

    /**
     * Nom de l'essai.
     */
    private String nomEssai;

    /**
     * Nom du patient.
     */
    private String nomPatient;

    /**
     * Prénom du patient.
     */
    private String prenomPatient;

    /**
     * Initiales du patient.
     */
    private String initialesPatient;

    /**
     * Code promoteur.
     */
    private String codePromoteur;

    /**
     * Numéro de prescription.
     */
    private Integer numPrescription;

    /**
     * Dispensé ?.
     */
    private Boolean dispense;

    /**
     * Numéro d'inclusion.
     */
    private String numInclusion;

    /**
     * Nombre de dispensations.
     */
    private Long nbDispensations;

    /**
     * Type de dispensation de l'essai.
     */
    private TypeDispensation typeDispensation;

    /**
     * Etat de l'essai.
     */
    private EtatEssai etatEssai;

    /**
     * Inclusion active.
     */
    private Boolean inclusionActive;

    /**
     * Date de prescription.
     */
    private Calendar datePrescription;

    /**
     * Getter pour dispense.
     * @return Le dispense
     */
    public Boolean getDispense() {
        return this.dispense;
    }

    /**
     * Setter pour dispense.
     * @param dispense Le dispense à écrire.
     */
    public void setDispense(final Boolean dispense) {
        this.dispense = dispense;
    }

    /**
     * Getter pour numInclusion.
     * @return Le numInclusion
     */
    public String getNumInclusion() {
        return this.numInclusion;
    }

    /**
     * Setter pour numInclusion.
     * @param numInclusion Le numInclusion à écrire.
     */
    public void setNumInclusion(final String numInclusion) {
        this.numInclusion = numInclusion;
    }

    /**
     * Getter pour id.
     * @return Le id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter pour id.
     * @param id Le id à écrire.
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * Getter pour nomEssai.
     * @return Le nomEssai
     */
    public String getNomEssai() {
        return this.nomEssai;
    }

    /**
     * Setter pour nomEssai.
     * @param nomEssai Le nomEssai à écrire.
     */
    public void setNomEssai(final String nomEssai) {
        this.nomEssai = nomEssai;
    }

    /**
     * Getter pour codePromoteur.
     * @return Le codePromoteur
     */
    public String getCodePromoteur() {
        return this.codePromoteur;
    }

    /**
     * Setter pour codePromoteur.
     * @param codePromoteur Le codePromoteur à écrire.
     */
    public void setCodePromoteur(final String codePromoteur) {
        this.codePromoteur = codePromoteur;
    }

    /**
     * Getter pour nomPatient.
     * @return Le nomPatient
     */
    public String getNomPatient() {
        return this.nomPatient;
    }

    /**
     * Setter pour nomPatient.
     * @param nomPatient Le nomPatient à écrire.
     */
    public void setNomPatient(final String nomPatient) {
        this.nomPatient = nomPatient;
    }

    /**
     * Getter pour prenomPatient.
     * @return Le prenomPatient
     */
    public String getPrenomPatient() {
        return this.prenomPatient;
    }

    /**
     * Setter pour prenomPatient.
     * @param prenomPatient Le prenomPatient à écrire.
     */
    public void setPrenomPatient(final String prenomPatient) {
        this.prenomPatient = prenomPatient;
    }

    /**
     * Getter pour initialesPatient.
     * @return Le initialesPatient
     */
    public String getInitialesPatient() {
        return this.initialesPatient;
    }

    /**
     * Setter pour initialesPatient.
     * @param initialesPatient Le initialesPatient à écrire.
     */
    public void setInitialesPatient(final String initialesPatient) {
        this.initialesPatient = initialesPatient;
    }

    /**
     * Getter pour nbDispensations.
     * @return Le nbDispensations
     */
    public Long getNbDispensations() {
        return this.nbDispensations;
    }

    /**
     * Setter pour nbDispensations.
     * @param nbDispensations Le nbDispensations à écrire.
     */
    public void setNbDispensations(final Long nbDispensations) {
        this.nbDispensations = nbDispensations;
    }

    /**
     * Getter pour numPrescription.
     * @return Le numPrescription
     */
    public Integer getNumPrescription() {
        return this.numPrescription;
    }

    /**
     * Setter pour numPrescription.
     * @param numPrescription Le numPrescription à écrire.
     */
    public void setNumPrescription(final Integer numPrescription) {
        this.numPrescription = numPrescription;
    }

    /**
     * Getter pour idEssai.
     * @return Le idEssai
     */
    public Long getIdEssai() {
        return this.idEssai;
    }

    /**
     * Setter pour idEssai.
     * @param idEssai Le idEssai à écrire.
     */
    public void setIdEssai(final Long idEssai) {
        this.idEssai = idEssai;
    }

    /**
     * Getter pour typeDispensation.
     * @return Le typeDispensation
     */
    public TypeDispensation getTypeDispensation() {
        return this.typeDispensation;
    }

    /**
     * Setter pour typeDispensation.
     * @param typeDispensation Le typeDispensation à écrire.
     */
    public void setTypeDispensation(final TypeDispensation typeDispensation) {
        this.typeDispensation = typeDispensation;
    }

    /**
     * Getter pour etatEssai.
     * @return Le etatEssai
     */
    public EtatEssai getEtatEssai() {
        return this.etatEssai;
    }

    /**
     * Setter pour etatEssai.
     * @param etatEssai Le etatEssai à écrire.
     */
    public void setEtatEssai(final EtatEssai etatEssai) {
        this.etatEssai = etatEssai;
    }

    /**
     * Getter pour inclusionActive.
     * @return Le inclusionActive
     */
    public Boolean getInclusionActive() {
        return this.inclusionActive;
    }

    /**
     * Setter pour inclusionActive.
     * @param inclusionActive Le inclusionActive à écrire.
     */
    public void setInclusionActive(final Boolean inclusionActive) {
        this.inclusionActive = inclusionActive;
    }

    /**
     * Getter pour datePrescription.
     * @return Le datePrescription
     */
    public Calendar getDatePrescription() {
        return this.datePrescription;
    }

    /**
     * Setter pour datePrescription.
     * @param datePrescription Le datePrescription à écrire.
     */
    public void setDatePrescription(final Calendar datePrescription) {
        this.datePrescription = datePrescription;
    }

}

package fr.pharma.eclipse.domain.dto;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Classe DTO de dispensation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationDTO implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4898115847704505659L;

    /**
     * Identifiant de dispensation.
     */
    private Long id;

    /**
     * Date de la prescription.
     */
    private Calendar datePrescription;

    /**
     * Identifiant technique de l'essai.
     */
    private Long idEssai;

    /**
     * Raison sociale du promoteur.
     */
    private String raisonSociale;

    /**
     * Code promoteur.
     */
    private String codePromoteur;

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
     * Date de dispensation.
     */
    private Calendar dateDispensation;

    /**
     * Description des produits.
     */
    private String descriptionProduits;

    /**
     * Personne ayant réalisée la dispensations de produit.
     */
    private String realisePar;

    /**
     * Numéro d'ordonnancier.
     */
    private Integer numOrdonnancier;

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

    /**
     * Getter pour raisonSociale.
     * @return Le raisonSociale
     */
    public String getRaisonSociale() {
        return this.raisonSociale;
    }

    /**
     * Setter pour raisonSociale.
     * @param raisonSociale Le raisonSociale à écrire.
     */
    public void setRaisonSociale(final String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    /**
     * Getter pour dateDispensation.
     * @return Le dateDispensation
     */
    public Calendar getDateDispensation() {
        return this.dateDispensation;
    }

    /**
     * Setter pour dateDispensation.
     * @param dateDispensation Le dateDispensation à écrire.
     */
    public void setDateDispensation(final Calendar dateDispensation) {
        this.dateDispensation = dateDispensation;
    }

    /**
     * Getter pour descriptionProduits.
     * @return Le descriptionProduits
     */
    public String getDescriptionProduits() {
        return this.descriptionProduits;
    }

    /**
     * Setter pour descriptionProduits.
     * @param descriptionProduits Le descriptionProduits à écrire.
     */
    public void setDescriptionProduits(final String descriptionProduits) {
        this.descriptionProduits = descriptionProduits;
    }

    /**
     * Getter pour realisePar.
     * @return Le realisePar
     */
    public String getRealisePar() {
        return this.realisePar;
    }

    /**
     * Setter pour realisePar.
     * @param realisePar Le realisePar à écrire.
     */
    public void setRealisePar(final String realisePar) {
        this.realisePar = realisePar;
    }

    /**
     * Getter pour numOrdonnancier.
     * @return Le numOrdonnancier
     */
    public Integer getNumOrdonnancier() {
        return this.numOrdonnancier;
    }

    /**
     * Setter pour numOrdonnancier.
     * @param numOrdonnancier Le numOrdonnancier à écrire.
     */
    public void setNumOrdonnancier(final Integer numOrdonnancier) {
        this.numOrdonnancier = numOrdonnancier;
    }

}

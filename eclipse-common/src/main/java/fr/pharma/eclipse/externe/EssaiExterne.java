/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.pharma.eclipse.externe;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Transient;

import fr.pharma.eclipse.domain.enums.Thematique;
import fr.pharma.eclipse.domain.enums.UniteTempsPrevision;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;

/**
 *
 * @author sgl
 */
public class EssaiExterne extends EssaiSigrec {

    /**
     * Service porteur de l'essai.
     */
    @Transient
    private String service;
    
    /**
     * Thématique;
     */
    @Transient
    private Thematique thematique;

    /**
     * Date prévisionnelle de fin d'inclusion.
     */
    @Transient
    private Calendar datePrevFinInclusion;

    /**
     * Présence d'une autorité compétente.
     */
    @Transient
    private Boolean presenceAC;

    /**
     * Nom de l'autorité compétente.
     */
    @Transient
    private String nomAC;

    /**
     * Date d'accord de l'autorité compétente.
     */
    @Transient
    private Calendar dateAccordAC;


    /**
     * Présence d'un CPP.
     */
    @Transient
    private Boolean presenceCPP;

    /**
     * Nom du CPP.
     */
    @Transient
    private String nomCPP;

    /**
     * Date d'accord CPP.
     */
    @Transient
    private Calendar dateAccordCPP;
    

    /**
     * N° EUDARCT.
     */
    @Transient
    private String numEudract;
    
    /**
     * Convention signé.
     */
    @Transient
    private Boolean conventionSigne;
    
    /**
     * Date de signature de la convention.
     */
    @Transient
    private Calendar dateConventionSigne;
    
    /**
     * N° d'avenant de l'assurance.
     */
    @Transient
    private String numAvenantAssurance;
    
    /**
     * Nombre de patient prévisionnel (Local).
     */
    @Transient
    private Integer nbPatientPrevLocal;
    
    /**
     * Durée totale prévue de l'essai.
     */
    @Transient
    private BigDecimal dureeTotalPrev;
    
    /**
     * Unitée de la durée totale prévue de l'essai.
     */
    @Transient
    private UniteTempsPrevision uniteeDureeTotalPrev;
    
    /**
     * Nombre de contre prévus.
     */
    @Transient
    private Integer nbCentresPrevus;
    
    /**
     * Nombre de patients prévus (total).
     */
    @Transient
    private Integer nbPatientPrevusTotal;
    
    /**
     * Numéro du centre.
     */
    @Transient
    private String numCentre;

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Calendar getDatePrevFinInclusion() {
        return datePrevFinInclusion;
    }

    public void setDatePrevFinInclusion(Calendar datePrevFinInclusion) {
        this.datePrevFinInclusion = datePrevFinInclusion;
    }

    public Boolean getPresenceAC() {
        return presenceAC;
    }

    public void setPresenceAC(Boolean presenceAC) {
        this.presenceAC = presenceAC;
    }

    public String getNomAC() {
        return nomAC;
    }

    public void setNomAC(String nomAC) {
        this.nomAC = nomAC;
    }

    public Calendar getDateAccordAC() {
        return dateAccordAC;
    }

    public void setDateAccordAC(Calendar dateAccordAC) {
        this.dateAccordAC = dateAccordAC;
    }

    public Boolean getPresenceCPP() {
        return presenceCPP;
    }

    public void setPresenceCPP(Boolean presenceCPP) {
        this.presenceCPP = presenceCPP;
    }

    public String getNomCPP() {
        return nomCPP;
    }

    public void setNomCPP(String nomCPP) {
        this.nomCPP = nomCPP;
    }

    public Calendar getDateAccordCPP() {
        return dateAccordCPP;
    }

    public void setDateAccordCPP(Calendar dateAccordCPP) {
        this.dateAccordCPP = dateAccordCPP;
    }

    public String getNumEudract() {
        return numEudract;
    }

    public void setNumEudract(String numEudract) {
        this.numEudract = numEudract;
    }

    public Boolean getConventionSigne() {
        return conventionSigne;
    }

    public void setConventionSigne(Boolean conventionSigne) {
        this.conventionSigne = conventionSigne;
    }

    public Calendar getDateConventionSigne() {
        return dateConventionSigne;
    }

    public void setDateConventionSigne(Calendar dateConventionSigne) {
        this.dateConventionSigne = dateConventionSigne;
    }

    public String getNumAvenantAssurance() {
        return numAvenantAssurance;
    }

    public void setNumAvenantAssurance(String numAvenantAssurance) {
        this.numAvenantAssurance = numAvenantAssurance;
    }

    public Integer getNbPatientPrevLocal() {
        return nbPatientPrevLocal;
    }

    public void setNbPatientPrevLocal(Integer nbPatientPrevLocal) {
        this.nbPatientPrevLocal = nbPatientPrevLocal;
    }

    public BigDecimal getDureeTotalPrev() {
        return dureeTotalPrev;
    }

    public void setDureeTotalPrev(BigDecimal dureeTotalPrev) {
        this.dureeTotalPrev = dureeTotalPrev;
    }

    public UniteTempsPrevision getUniteeDureeTotalPrev() {
        return uniteeDureeTotalPrev;
    }

    public void setUniteeDureeTotalPrev(UniteTempsPrevision uniteeDureeTotalPrev) {
        this.uniteeDureeTotalPrev = uniteeDureeTotalPrev;
    }

    public Integer getNbCentresPrevus() {
        return nbCentresPrevus;
    }

    public void setNbCentresPrevus(Integer nbCentresPrevus) {
        this.nbCentresPrevus = nbCentresPrevus;
    }

    public Integer getNbPatientPrevusTotal() {
        return nbPatientPrevusTotal;
    }

    public void setNbPatientPrevusTotal(Integer nbPatientPrevusTotal) {
        this.nbPatientPrevusTotal = nbPatientPrevusTotal;
    }

    public String getNumCentre() {
        return numCentre;
    }

    public void setNumCentre(String numCentre) {
        this.numCentre = numCentre;
    }

    public Thematique getThematique() {
        return thematique;
    }

    public void setThematique(Thematique thematique) {
        this.thematique = thematique;
    }
    
}

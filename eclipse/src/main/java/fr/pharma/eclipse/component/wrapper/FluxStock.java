package fr.pharma.eclipse.component.wrapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;

/**
 * Wrapper de Mvt stock permettant de regrouper les mvt stock sur le meme numéro
 * de lot.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class FluxStock implements Serializable {
    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 3143511273469695939L;

    /**
     * Liste de mouvements.
     */
    private List<MvtStock> mvts = new ArrayList<MvtStock>();

    /**
     * Numero de lot.
     */
    private String numLot;

    /**
     * Date du mvt.
     */
    private Calendar date;

    /**
     * Essai.
     */
    private Essai essai;

    /**
     * Produit.
     */
    private Produit produit;

    /**
     * Pharmacie.
     */
    private Pharmacie pharmacie;

    /**
     * Conditionnement.
     */
    private Conditionnement conditionnement;

    /**
     * Quantite.
     */
    private Integer quantite;

    /**
     * Type de mouvement.
     */
    private TypeMvtStock type;

    /**
     * Personne.
     */
    private Personne personne;

    /**
     * Patient.
     */
    private Patient patient;

    /**
     * Numero ordonnancier.
     */
    private Integer numOrdonnancier;

    /**
     * Lieu de stockage du produit
     */
    private Stockage stockage;

    /**
     * Getter sur numLot.
     * @return Retourne le numLot.
     */
    public String getNumLot() {
        return this.numLot;
    }

    /**
     * Getter sur mvts.
     * @return Retourne le mvts.
     */
    public List<MvtStock> getMvts() {
        return this.mvts;
    }

    /**
     * Setter pour mvts.
     * @param mvts le mvts à écrire.
     */
    public void setMvts(final List<MvtStock> mvts) {
        this.mvts = mvts;
    }

    /**
     * Setter pour numLot.
     * @param numLot le numLot à écrire.
     */
    public void setNumLot(final String numLot) {
        this.numLot = numLot;
    }

    /**
     * Getter sur date.
     * @return Retourne le date.
     */
    public Calendar getDate() {
        return this.date;
    }

    /**
     * Getter sur essai.
     * @return Retourne le essai.
     */
    public Essai getEssai() {
        return this.essai;
    }

    /**
     * Getter sur produit.
     * @return Retourne le produit.
     */
    public Produit getProduit() {
        return this.produit;
    }

    /**
     * Getter sur pharmacie.
     * @return Retourne le pharmacie.
     */
    public Pharmacie getPharmacie() {
        return this.pharmacie;
    }

    /**
     * Getter sur conditionnement.
     * @return Retourne le conditionnement.
     */
    public Conditionnement getConditionnement() {
        return this.conditionnement;
    }

    /**
     * Getter sur quantite.
     * @return Retourne le quantite.
     */
    public Integer getQuantite() {
        return this.quantite;
    }

    /**
     * Getter sur type.
     * @return Retourne le type.
     */
    public TypeMvtStock getType() {
        return this.type;
    }

    /**
     * Setter pour date.
     * @param date le date à écrire.
     */
    public void setDate(final Calendar date) {
        this.date = date;
    }

    /**
     * Setter pour essai.
     * @param essai le essai à écrire.
     */
    public void setEssai(final Essai essai) {
        this.essai = essai;
    }

    /**
     * Setter pour produit.
     * @param produit le produit à écrire.
     */
    public void setProduit(final Produit produit) {
        this.produit = produit;
    }

    /**
     * Setter pour pharmacie.
     * @param pharmacie le pharmacie à écrire.
     */
    public void setPharmacie(final Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
    }

    /**
     * Setter pour conditionnement.
     * @param conditionnement le conditionnement à écrire.
     */
    public void setConditionnement(final Conditionnement conditionnement) {
        this.conditionnement = conditionnement;
    }

    /**
     * Setter pour quantite.
     * @param quantite le quantite à écrire.
     */
    public void setQuantite(final Integer quantite) {
        this.quantite = quantite;
    }

    /**
     * Setter pour type.
     * @param type le type à écrire.
     */
    public void setType(final TypeMvtStock type) {
        this.type = type;
    }

    /**
     * Getter sur personne.
     * @return Retourne le personne.
     */
    public Personne getPersonne() {
        return this.personne;
    }

    /**
     * Setter pour personne.
     * @param personne le personne à écrire.
     */
    public void setPersonne(final Personne personne) {
        this.personne = personne;
    }

    /**
     * Retourne <true> si le flux de stock contient des mvts en numero de
     * traitement.
     * @return <true> si le flux de stock contient des mvts en numero de
     * traitement.
     */
    public boolean isNumeroTraitement() {
        return !StringUtils.isEmpty(this.getMvts().get(0).getNumTraitement());
    }

    /**
     * Retourne le premier mouvement de stock.
     * @return Le premier mouvement de stock.
     */
    public MvtStock getfirst() {
        return this.mvts.get(0);
    }

    /**
     * Getter pour patient.
     * @return Le patient
     */
    public Patient getPatient() {
        return this.patient;
    }

    /**
     * Setter pour patient.
     * @param patient Le patient à écrire.
     */
    public void setPatient(final Patient patient) {
        this.patient = patient;
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

    /**
     * Getter pour stockage.
     * @return Le stockage
     */
    public Stockage getStockage() {
        return this.stockage;
    }

    /**
     * Setter pour stockage.
     * @param stockage Le stockage à écrire.
     */
    public void setStockage(final Stockage stockage) {
        this.stockage = stockage;
    }

}

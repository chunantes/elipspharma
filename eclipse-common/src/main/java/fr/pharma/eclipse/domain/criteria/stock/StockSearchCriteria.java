package fr.pharma.eclipse.domain.criteria.stock;

import java.util.Calendar;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.dto.EssaiDTO;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;

/**
 * Critère de recherche sur Stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class StockSearchCriteria extends AbstractSearchCriteria {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1369104241769990392L;

    /**
     * Essai.
     */
    private Essai essai;

    /**
     * Pharmacie.
     */
    private Pharmacie pharmacie;

    /**
     * Stockage.
     */
    private Stockage stockage;

    /**
     * Numéro de lot.
     */
    private String numLot;

    /**
     * Numéro de lot.
     */
    private String numLotStrict;

    /**
     * Numéro de traitement.
     */
    private String numTraitement;

    /**
     * Dénomination du produit.
     */
    private String denominationProduit;

    private Produit produit;

    /**
     * Date de bornage pour la récupération des mouvements.
     */
    private Calendar date;

    /**
     * String contenant les heures/minutes pour la date de bornage de
     * récupération des mouvements.
     */
    private String heuresMinutes;

    /**
     * Conditionnement.
     */
    private Conditionnement conditionnement;

    /**
     * Mode de prescription.
     */
    private ModePrescription modePrescription;

    /**
     * Vrai si l'on veut des stocks dont la quantité en dispensation globale est
     * non null et supérieur à 0.
     */
    private Boolean notNullQteDispensationGlobal;

    /**
     * Essai.
     */
    private EssaiDTO essaiDTO;

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.setEssai(null);
        this.setEssaiDTO(null);
        this.setPharmacie(null);
        this.setStockage(null);
        this.setNumLot(null);
        this.setDenominationProduit(null);
        this.setDate(null);
        this.setHeuresMinutes(null);
        this.setConditionnement(null);
        this.setModePrescription(null);
        this.setProduit(null);
        this.setNumLotStrict(null);
        this.setNumTraitement(null);
    }

    /**
     * Getter pour essai.
     * @return Le essai
     */
    public Essai getEssai() {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai Le essai à écrire.
     */
    public void setEssai(final Essai essai) {
        this.essai = essai;
    }

    /**
     * Getter pour pharmacie.
     * @return Le pharmacie
     */
    public Pharmacie getPharmacie() {
        return this.pharmacie;
    }

    /**
     * Setter pour pharmacie.
     * @param pharmacie Le pharmacie à écrire.
     */
    public void setPharmacie(final Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
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

    /**
     * Getter pour numLot.
     * @return Le numLot
     */
    public String getNumLot() {
        return this.numLot;
    }

    /**
     * Setter pour numLot.
     * @param numLot Le numLot à écrire.
     */
    public void setNumLot(final String numLot) {
        this.numLot = numLot;
    }

    /**
     * Getter pour denominationProduit.
     * @return Le denominationProduit
     */
    public String getDenominationProduit() {
        return this.denominationProduit;
    }

    /**
     * Setter pour denominationProduit.
     * @param denominationProduit Le denominationProduit à écrire.
     */
    public void setDenominationProduit(final String denominationProduit) {
        this.denominationProduit = denominationProduit;
    }

    /**
     * Getter pour date.
     * @return Le date
     */
    public Calendar getDate() {
        return this.date;
    }

    /**
     * Setter pour date.
     * @param date Le date à écrire.
     */
    public void setDate(final Calendar date) {
        this.date = date;
    }

    /**
     * Getter pour heuresMinutes.
     * @return Le heuresMinutes
     */
    public String getHeuresMinutes() {
        return this.heuresMinutes;
    }

    /**
     * Setter pour heuresMinutes.
     * @param heuresMinutes Le heuresMinutes à écrire.
     */
    public void setHeuresMinutes(final String heuresMinutes) {
        this.heuresMinutes = heuresMinutes;
    }

    /**
     * Getter sur conditionnement.
     * @return Retourne le conditionnement.
     */
    public Conditionnement getConditionnement() {
        return this.conditionnement;
    }

    /**
     * Setter pour conditionnement.
     * @param conditionnement le conditionnement à écrire.
     */
    public void setConditionnement(final Conditionnement conditionnement) {
        this.conditionnement = conditionnement;
    }

    /**
     * Getter sur modePrescription.
     * @return Retourne le modePrescription.
     */
    public ModePrescription getModePrescription() {
        return this.modePrescription;
    }

    /**
     * Setter pour modePrescription.
     * @param modePrescription le modePrescription à écrire.
     */
    public void setModePrescription(final ModePrescription modePrescription) {
        this.modePrescription = modePrescription;
    }

    /**
     * Getter pour produit.
     * @return Le produit
     */
    public Produit getProduit() {
        return this.produit;
    }

    /**
     * Setter pour produit.
     * @param produit Le produit à écrire.
     */
    public void setProduit(final Produit produit) {
        this.produit = produit;
    }

    /**
     * Getter pour notNullQteDispensationGlobal.
     * @return Vrai si l'on veut des stocks ayant une quantité en dispensation
     * globale non null et supérieur à 0;
     */
    public Boolean getNotNullQteDispensationGlobal() {
        return this.notNullQteDispensationGlobal;
    }

    /**
     * Setter pour notNullQteDispensationGlobal
     * @param notNullQteDispensationGlobal Vrai si l'on veut des stocks ayant
     * une quantité en dispensation globale non null et supérieur à 0
     */
    public void setNotNullQteDispensationGlobal(final Boolean notNullQteDispensationGlobal) {
        this.notNullQteDispensationGlobal = notNullQteDispensationGlobal;
    }

    /**
     * Getter pour numTraitement.
     * @return Le numTraitement
     */
    public String getNumTraitement() {
        return this.numTraitement;
    }

    /**
     * Setter pour numTraitement.
     * @param numTraitement Le numTraitement à écrire.
     */
    public void setNumTraitement(final String numTraitement) {
        this.numTraitement = numTraitement;
    }

    /**
     * Getter pour numLotStrict.
     * @return Le numLotStrict
     */
    public String getNumLotStrict() {
        return this.numLotStrict;
    }

    /**
     * Setter pour numLotStrict.
     * @param numLotStrict Le numLotStrict à écrire.
     */
    public void setNumLotStrict(final String numLotStrict) {
        this.numLotStrict = numLotStrict;
    }

    /**
     * Getter pour essaiDTO.
     * @return Le essaiDTO
     */
    public EssaiDTO getEssaiDTO() {
        return this.essaiDTO;
    }

    /**
     * Setter pour essaiDTO.
     * @param essaiDTO Le essaiDTO à écrire.
     */
    public void setEssaiDTO(final EssaiDTO essaiDTO) {
        this.essaiDTO = essaiDTO;
    }

}

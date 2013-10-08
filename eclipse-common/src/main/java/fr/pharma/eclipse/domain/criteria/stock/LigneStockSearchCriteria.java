package fr.pharma.eclipse.domain.criteria.stock;

import java.util.Calendar;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Critère de recherche sur les lignes de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class LigneStockSearchCriteria extends AbstractSearchCriteria {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5180490151794392069L;

    /**
     * Essai.
     */
    private Essai essai;

    /**
     * Pharmacie.
     */
    private Pharmacie pharmacie;

    /**
     * Produit.
     */
    private Produit produit;

    /**
     * Conditionnement.
     */
    private Conditionnement conditionnement;

    /**
     * Numéro de lot.
     */
    private String numLot;

    /**
     * Numéro de traitement.
     */
    private String numTraitement;

    /**
     * Date de péremption.
     */
    private Calendar datePeremption;

    /**
     * Boolean indiquant l'approbation (quarantaine).
     */
    private Boolean approApprouve;

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.setEssai(null);
        this.setPharmacie(null);
        this.setProduit(null);
        this.setConditionnement(null);
        this.setDatePeremption(null);
        this.setNumLot(null);
        this.setNumTraitement(null);
        this.setApproApprouve(null);
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
     * Getter pour conditionnement.
     * @return Le conditionnement
     */
    public Conditionnement getConditionnement() {
        return this.conditionnement;
    }

    /**
     * Setter pour conditionnement.
     * @param conditionnement Le conditionnement à écrire.
     */
    public void setConditionnement(final Conditionnement conditionnement) {
        this.conditionnement = conditionnement;
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
     * Getter pour datePeremption.
     * @return Le datePeremption
     */
    public Calendar getDatePeremption() {
        return this.datePeremption;
    }

    /**
     * Setter pour datePeremption.
     * @param datePeremption Le datePeremption à écrire.
     */
    public void setDatePeremption(final Calendar datePeremption) {
        this.datePeremption = datePeremption;
    }

    /**
     * Getter pour approApprouve.
     * @return Le approApprouve
     */
    public Boolean getApproApprouve() {
        return this.approApprouve;
    }

    /**
     * Setter pour approApprouve.
     * @param approApprouve Le approApprouve à écrire.
     */
    public void setApproApprouve(final Boolean approApprouve) {
        this.approApprouve = approApprouve;
    }

}

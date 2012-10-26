package fr.pharma.eclipse.domain.model.stock;

import java.io.Serializable;
import java.util.Calendar;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Bean métier représentant une ligne de stock pour une pharmacie, un produit et un
 * conditionnement.
 
 * @version $Revision$ $Date$
 */
public class LigneStock
    implements Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3468944772484934726L;

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
     * Stockage du produit dans la pharmacie.
     */
    private String stockage;

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
     * Quantité réelle en stock.
     */
    private Integer qteEnStock;

    /**
     * Quantité à sortir.
     */
    private Integer qteASortir;

    /**
     * Booléen indiquant si l'approvisionnement a été approuvé.
     */
    private Boolean approApprouve;

    /**
     * Dispensation globale.
     */
    private DispensationGlobale dispensationGlobale;

    /**
     * Constructeur d'une ligne de stock.
     * @param essai Essai.
     * @param pharmacie Pharmacie.
     * @param produit Produit.
     * @param conditionnement Conditionnement.
     * @param approApprouve Booléen indiquant si l'approvisionnement en relation avec la ligne de
     * stock a été approuvé (mise ou non en quanrantaine).
     */
    public LigneStock(
                      final Essai essai,
                      final Pharmacie pharmacie,
                      final Produit produit,
                      final Conditionnement conditionnement,
                      final Boolean approApprouve)
    {
        this.setEssai(essai);
        this.setPharmacie(pharmacie);
        this.setProduit(produit);
        this.setConditionnement(conditionnement);
        this.setApproApprouve(approApprouve);
    }

    /**
     * Getter pour pharmacie.
     * @return Le pharmacie
     */
    public Pharmacie getPharmacie()
    {
        return this.pharmacie;
    }

    /**
     * Setter pour pharmacie.
     * @param pharmacie Le pharmacie à écrire.
     */
    public void setPharmacie(final Pharmacie pharmacie)
    {
        this.pharmacie = pharmacie;
    }

    /**
     * Getter pour produit.
     * @return Le produit
     */
    public Produit getProduit()
    {
        return this.produit;
    }

    /**
     * Setter pour produit.
     * @param produit Le produit à écrire.
     */
    public void setProduit(final Produit produit)
    {
        this.produit = produit;
    }

    /**
     * Getter pour conditionnement.
     * @return Le conditionnement
     */
    public Conditionnement getConditionnement()
    {
        return this.conditionnement;
    }

    /**
     * Setter pour conditionnement.
     * @param conditionnement Le conditionnement à écrire.
     */
    public void setConditionnement(final Conditionnement conditionnement)
    {
        this.conditionnement = conditionnement;
    }

    /**
     * Getter pour qteEnStock.
     * @return Le qteEnStock
     */
    public Integer getQteEnStock()
    {
        return this.qteEnStock;
    }

    /**
     * Setter pour qteEnStock.
     * @param qteEnStock Le qteEnStock à écrire.
     */
    public void setQteEnStock(final Integer qteEnStock)
    {
        this.qteEnStock = qteEnStock;
    }

    /**
     * Getter pour qteASortir.
     * @return Le qteASortir
     */
    public Integer getQteASortir()
    {
        return this.qteASortir;
    }

    /**
     * Setter pour qteASortir.
     * @param qteASortir Le qteASortir à écrire.
     */
    public void setQteASortir(final Integer qteASortir)
    {
        this.qteASortir = qteASortir;
    }

    /**
     * Getter pour numLot.
     * @return Le numLot
     */
    public String getNumLot()
    {
        return this.numLot;
    }

    /**
     * Setter pour numLot.
     * @param numLot Le numLot à écrire.
     */
    public void setNumLot(final String numLot)
    {
        this.numLot = numLot;
    }

    /**
     * Getter pour numTraitement.
     * @return Le numTraitement
     */
    public String getNumTraitement()
    {
        return this.numTraitement;
    }

    /**
     * Setter pour numTraitement.
     * @param numTraitement Le numTraitement à écrire.
     */
    public void setNumTraitement(final String numTraitement)
    {
        this.numTraitement = numTraitement;
    }

    /**
     * Getter pour essai.
     * @return Le essai
     */
    public Essai getEssai()
    {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai Le essai à écrire.
     */
    public void setEssai(final Essai essai)
    {
        this.essai = essai;
    }

    /**
     * Getter pour datePeremption.
     * @return Le datePeremption
     */
    public Calendar getDatePeremption()
    {
        return this.datePeremption;
    }

    /**
     * Setter pour datePeremption.
     * @param datePeremption Le datePeremption à écrire.
     */
    public void setDatePeremption(final Calendar datePeremption)
    {
        this.datePeremption = datePeremption;
    }

    /**
     * Getter pour stockage.
     * @return Le stockage
     */
    public String getStockage()
    {
        return this.stockage;
    }

    /**
     * Setter pour stockage.
     * @param stockage Le stockage à écrire.
     */
    public void setStockage(final String stockage)
    {
        this.stockage = stockage;
    }

    /**
     * Getter pour approApprouve.
     * @return Le approApprouve
     */
    public Boolean getApproApprouve()
    {
        return this.approApprouve;
    }

    /**
     * Setter pour approApprouve.
     * @param approApprouve Le approApprouve à écrire.
     */
    public void setApproApprouve(final Boolean approApprouve)
    {
        this.approApprouve = approApprouve;
    }

    /**
     * Getter pour dispensationGlobale.
     * @return Le dispensationGlobale
     */
    public DispensationGlobale getDispensationGlobale()
    {
        return this.dispensationGlobale;
    }

    /**
     * Setter pour dispensationGlobale.
     * @param dispensationGlobale Le dispensationGlobale à écrire.
     */
    public void setDispensationGlobale(final DispensationGlobale dispensationGlobale)
    {
        this.dispensationGlobale = dispensationGlobale;
    }

}

package fr.pharma.eclipse.domain.model.stock;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.common.BeanParentDocument;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Bean métier représentant une ligne de stock pour une pharmacie, un produit et
 * un conditionnement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "lignestock")
public class LigneStock extends BeanObject implements BeanParentDocument, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3468944772484934726L;

    /**
     * Constante de stockage en quarantaine.
     */
    public static final String EN_QUARANTAINE = "En quarantaine";

    /**
     * Essai.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_essai", nullable = false)
    @Index(name = "idx_lignestock_essai")
    @NotNull
    private Essai essai;

    /**
     * Pharmacie.
     */
    @ManyToOne
    @JoinColumn(name = "id_pharmacie", nullable = false)
    @Index(name = "idx_lignestock_pharmacie")
    @NotNull
    private Pharmacie pharmacie;

    /**
     * Produit.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produit", nullable = false)
    @Index(name = "idx_lignestock_produit")
    @NotNull
    private Produit produit;

    /**
     * Stockage du produit dans la pharmacie.
     */
    private String stockage;

    /**
     * Conditionnement.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conditionnement", nullable = false)
    @Index(name = "idx_lignestock_conditionnement")
    @NotNull
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
    @Column(name = "datePeremption")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar datePeremption;

    /**
     * Quantité en dispensation Global (doté à un service mais on dispensé).
     */
    @Column(name = "quantite_dispensation_en_stock")
    @NotNull
    private Integer qteDispensationGlobal = 0;

    /**
     * Quantité hors dispensation Global.
     */
    @Column(name = "quantite_global")
    @NotNull
    private Integer qteGlobalStock = 0;

    @Transient
    private Boolean dotation = false;

    /**
     * Quantité à sortir.
     */
    @Transient
    private Integer qteASortir;

    /**
     * Booléen indiquant si l'approvisionnement a été approuvé.
     */
    private Boolean approApprouve;

    /**
     * Dispensation globale.
     */
    @Transient
    private DispensationGlobale dispensationGlobale;

    /**
     *
     */
    public LigneStock() {
        super();
    }

    /**
     * Constructeur d'une ligne de stock.
     * @param essai Essai.
     * @param pharmacie Pharmacie.
     * @param produit Produit.
     * @param conditionnement Conditionnement.
     * @param approApprouve Booléen indiquant si l'approvisionnement en relation
     * avec la ligne de stock a été approuvé (mise ou non en quanrantaine).
     */
    public LigneStock(final Essai essai, final Pharmacie pharmacie, final Produit produit, final Conditionnement conditionnement, final Boolean approApprouve) {
        this.setEssai(essai);
        this.setPharmacie(pharmacie);
        this.setProduit(produit);
        this.setConditionnement(conditionnement);
        this.setApproApprouve(approApprouve);
    }

    /**
     * Constructeur d'une ligne de stock.
     * @param essai Essai.
     * @param pharmacie Pharmacie.
     * @param produit Produit.
     * @param conditionnement Conditionnement.
     * @param approApprouve Booléen indiquant si l'approvisionnement en relation
     * avec la ligne de stock a été approuvé (mise ou non en quanrantaine).
     * @param datePeremption Date de péremption.
     * @param numLot Numéto de lot.
     * @param numTraitement Numéro de traitement.
     */
    public LigneStock(final Essai essai, final Pharmacie pharmacie, final Produit produit, final Conditionnement conditionnement, final Boolean approApprouve,
                      final Calendar datePeremption, final String numLot, final String numTraitement) {
        this(essai, pharmacie, produit, conditionnement, approApprouve);
        this.setDatePeremption(datePeremption);
        this.setNumLot(numLot);
        this.setNumTraitement(numTraitement);
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
     * Getter pour qteEnStock.
     * @return Le qteEnStock
     */
    public Integer getQteEnStock() {
        if (Boolean.TRUE.equals(this.dotation)) {
            return this.qteDispensationGlobal;
        } else {
            return this.qteGlobalStock;
        }
    }

    /**
     * Getter pour qteASortir.
     * @return Le qteASortir
     */
    public Integer getQteASortir() {
        return this.qteASortir;
    }

    /**
     * Setter pour qteASortir.
     * @param qteASortir Le qteASortir à écrire.
     */
    public void setQteASortir(final Integer qteASortir) {
        this.qteASortir = qteASortir;
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
     * Getter pour stockage.
     * @return Le stockage
     */
    public String getStockage() {
        return this.stockage;
    }

    /**
     * Setter pour stockage.
     * @param stockage Le stockage à écrire.
     */
    public void setStockage(final String stockage) {
        this.stockage = stockage;
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

    /**
     * Getter pour dispensationGlobale.
     * @return Le dispensationGlobale
     */
    public DispensationGlobale getDispensationGlobale() {
        return this.dispensationGlobale;
    }

    /**
     * Setter pour dispensationGlobale.
     * @param dispensationGlobale Le dispensationGlobale à écrire.
     */
    public void setDispensationGlobale(final DispensationGlobale dispensationGlobale) {
        this.dispensationGlobale = dispensationGlobale;
    }

    /**
     * Getter pour qteDispensationGlobal.
     * @return Le qteDispensationGlobal
     */
    public Integer getQteDispensationGlobal() {
        return this.qteDispensationGlobal;
    }

    /**
     * Setter pour qteDispensationGlobal.
     * @param qteDispensationGlobal Le qteDispensationGlobal à écrire.
     */
    public void setQteDispensationGlobal(final Integer qteDispensationGlobal) {
        this.qteDispensationGlobal = qteDispensationGlobal;
    }

    /**
     * Getter pour qteGlobalStock.
     * @return Le qteGlobalStock
     */
    public Integer getQteGlobalStock() {
        return this.qteGlobalStock;
    }

    /**
     * Setter pour qteGlobalStock.
     * @param qteGlobalStock Le qteGlobalStock à écrire.
     */
    public void setQteGlobalStock(final Integer qteGlobalStock) {
        this.qteGlobalStock = qteGlobalStock;
    }

    /**
     * Getter pour dotation.
     * @return Le dotation
     */
    public Boolean getDotation() {
        return this.dotation;
    }

    /**
     * Setter pour dotation.
     * @param dotation Le dotation à écrire.
     */
    public void setDotation(final Boolean dotation) {
        this.dotation = dotation;
    }

    /**
     * Méthode en charge de retourner la clé d'une ligne de stock. <br />
     * La clé est la concaténation de essai + pharmacie + produit +
     * conditionnement + numLot + numTraitement.
     * @return Clé.
     */
    public String getKeyLigneStock(final boolean datesPeremptionFusionnees) {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getEssai().getId()).append(this.getPharmacie().getId()).append(this.getProduit().getId()).append(this.getConditionnement().getId()).append(this.getNumLot())
                .append(this.getNumTraitement()).append(this.getApproApprouve());
        if (!datesPeremptionFusionnees && this.getDatePeremption() != null) {
            final StringBuilder datePeremptionAsString = new StringBuilder();

            datePeremptionAsString.append(this.getDatePeremption().get(Calendar.YEAR)).append(this.getDatePeremption().get(Calendar.MONTH))
                    .append(this.getDatePeremption().get(Calendar.DAY_OF_MONTH));

            sb.append(datePeremptionAsString);
        }
        return sb.toString();
    }

    /**
     * Méthode en charge de retourner la clé d'un mouvement de stock côté état. <br />
     * La clé est la concaténation de essai + pharmacie + produit +
     * conditionnement.
     * @return Clé.
     */
    public String getKeyStock() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getEssai().getId()).append(this.getPharmacie().getId()).append(this.getProduit().getId()).append(this.getConditionnement().getId())
                .append(this.getApproApprouve());
        return sb.toString();
    }
}

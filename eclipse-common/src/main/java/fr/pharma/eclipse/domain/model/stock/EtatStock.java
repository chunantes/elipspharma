package fr.pharma.eclipse.domain.model.stock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Bean métier représentant un état de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EtatStock implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4263391020972179018L;

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
     * Stockage du produit dans la pharmacie.
     */
    private String stockage;

    /**
     * Quantité en stock.
     */
    private Integer qteEnStock;

    /**
     * Quarantaine.
     */
    private Boolean enQuarantaine;

    /**
     * Map contenant les différentes lignes / clé = n° de lot.
     */
    private Map<String, EtatLigneStock> etatsLignesStock;

    /**
     * Constructeur d'un état de stock.
     * @param essai Essai.
     * @param pharmacie Pharmacie.
     * @param produit Produit.
     * @param conditionnement Conditionnement.
     */
    public EtatStock(final Essai essai, final Pharmacie pharmacie, final Produit produit, final Conditionnement conditionnement, final Boolean enQuarantaine) {
        this.setEssai(essai);
        this.setPharmacie(pharmacie);
        this.setProduit(produit);
        this.setConditionnement(conditionnement);
        this.setQteEnStock(0);
        this.enQuarantaine = enQuarantaine;
        this.etatsLignesStock = new HashMap<String, EtatLigneStock>();

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
     * Getter pour qteEnStock.
     * @return Le qteEnStock
     */
    public Integer getQteEnStock() {
        return this.qteEnStock;
    }

    /**
     * Setter pour qteEnStock.
     * @param qteEnStock Le qteEnStock à écrire.
     */
    public void setQteEnStock(final Integer qteEnStock) {
        this.qteEnStock = qteEnStock;
    }

    /**
     * Getter sur etatsLignesStock. Filtre les stocks vide.
     * @return Retourne le etatsLignesStock.
     */
    @SuppressWarnings("unchecked")
    public List<EtatLigneStock> getEtatsLignesStockAsList() {
        final List<EtatLigneStock> lignes = new ArrayList<EtatLigneStock>(CollectionUtils.select(this.etatsLignesStock.values(), new Predicate() {

            @Override
            public boolean evaluate(final Object object) {
                return ((EtatLigneStock) object).getQteEnStock() > 0;
            }
        }));

        Collections.sort(lignes, new Comparator<EtatLigneStock>() {

            @Override
            public int compare(final EtatLigneStock o1,
                               final EtatLigneStock o2) {
                return (o1.getNumLot() + o1.getNumTraitement()).compareTo(o2.getNumLot() + o2.getNumTraitement());
            }

        });
        return lignes;
    }

    /**
     * Getter sur etatsLignesStock.
     * @return Retourne le etatsLignesStock.
     */
    public Map<String, EtatLigneStock> getEtatsLignesStock() {
        return this.etatsLignesStock;
    }

    /**
     * Setter pour etatsLignesStock.
     * @param etatsLignesStock le etatsLignesStock à écrire.
     */
    public void setEtatsLignesStock(final Map<String, EtatLigneStock> etatsLignesStock) {
        this.etatsLignesStock = etatsLignesStock;
    }

    /**
     * Getter sur enQuarantaine.
     * @return Retourne le enQuarantaine.
     */
    public Boolean getEnQuarantaine() {
        return this.enQuarantaine;
    }

    /**
     * Setter pour enQuarantaine.
     * @param enQuarantaine le enQuarantaine à écrire.
     */
    public void setEnQuarantaine(final Boolean enQuarantaine) {
        this.enQuarantaine = enQuarantaine;
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
                .append(!this.getEnQuarantaine());
        return sb.toString();
    }

}

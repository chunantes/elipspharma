package fr.pharma.eclipse.domain.model.stock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.predicate.stock.LigneStockCompletPredicate;

/**
 * Bean représentant le détail d'une sortie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class Sortie implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6905747802374211519L;

    /**
     * Mouvement de stock associé à la sortie.
     */
    private MvtStock mvtSortie;

    /**
     * Liste des conditionnements.
     */
    private List<Conditionnement> conditionnements;

    /**
     * Liste des lignes de stock correspondant à la sortie.
     */
    private List<LigneStock> lignesStock;

    /**
     * Liste des lignes de stock complétées.
     */
    private List<LigneStock> lignesStockCompletees;

    /**
     * Quantité cumulée des sorties.
     */
    private Integer qteCumulSortie;

    /**
     * Méthode en charge d'annuler les informations de saisie d'un détail.
     */
    public void reset() {
        this.mvtSortie.setProduit(null);
        this.mvtSortie.setConditionnement(null);
        this.setLignesStock(null);
    }

    /**
     * Getter pour mvtSortie.
     * @return Le mvtSortie
     */
    public MvtStock getMvtSortie() {
        return this.mvtSortie;
    }

    /**
     * Setter pour mvtSortie.
     * @param mvtSortie Le mvtSortie à écrire.
     */
    public void setMvtSortie(final MvtStock mvtSortie) {
        this.mvtSortie = mvtSortie;
    }

    /**
     * Getter pour conditionnements.
     * @return Le conditionnements
     */
    public List<Conditionnement> getConditionnements() {
        return this.conditionnements;
    }

    /**
     * Setter pour conditionnements.
     * @param conditionnements Le conditionnements à écrire.
     */
    public void setConditionnements(final List<Conditionnement> conditionnements) {
        this.conditionnements = conditionnements;
    }

    /**
     * Getter pour lignesStock.
     * @return Le lignesStock
     */
    public List<LigneStock> getLignesStock() {
        return this.lignesStock;
    }

    /**
     * Setter pour lignesStock.
     * @param lignesStock Le lignesStock à écrire.
     */
    public void setLignesStock(final List<LigneStock> lignesStock) {
        this.lignesStock = lignesStock;
    }

    /**
     * Getter pour qteCumulSortie.
     * @return Le qteCumulSortie
     */
    public Integer getQteCumulSortie() {
        this.qteCumulSortie = Integer.valueOf(0);

        // Parcours des lignes de stock
        for (final LigneStock ligne : this.getLignesStock()) {
            if ((ligne.getQteASortir() != null) && (ligne.getQteASortir() > 0)) {
                this.qteCumulSortie += ligne.getQteASortir();
            }
        }
        return this.qteCumulSortie;
    }

    /**
     * Getter pour lignesStockCompletees.
     * @return Le lignesStockCompletees
     */
    public List<LigneStock> getLignesStockCompletees() {
        this.lignesStockCompletees = new ArrayList<LigneStock>(this.getLignesStock());

        CollectionUtils.filter(this.lignesStockCompletees, new LigneStockCompletPredicate());
        return this.lignesStockCompletees;
    }

    /**
     * Filtrer les lignes en stock par le numero de traitement
     */
    public void filtrerLignesStockParNumeroTraitement(final String numeroTraitement) {
        if (!StringUtils.isEmpty(numeroTraitement)) {
            // filtrage selon le numéro de traitement
            CollectionUtils.filter(this.lignesStock, new GenericPredicate("numTraitement", numeroTraitement));
        }
    }

    @Override
    public String toString() {
        return "Sortie:" + this.mvtSortie + ":conditionnements=" + (this.conditionnements != null ? this.conditionnements.size() : 0) + ":lignes="
               + (this.lignesStock != null ? this.lignesStock.size() : 0);
    }
}

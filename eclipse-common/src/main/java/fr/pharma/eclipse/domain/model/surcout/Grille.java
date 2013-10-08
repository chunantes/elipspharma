package fr.pharma.eclipse.domain.model.surcout;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import fr.pharma.eclipse.comparator.surcout.ItemComparator;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.essai.detail.surcout.DetailSurcout;

/**
 * Classe du modèle représentant une grille réelle des surcouts.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "grille")
public class Grille extends BeanObject {
    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 4878192094742718006L;

    /**
     * DetailSurcout de l'essai auquel est rattaché le bean DonneesPrevision.
     */
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "id_detail_surcout")
    private DetailSurcout detailSurcout;

    /**
     * Grille modèle à laquelle la grille est rattachée.
     */
    @ManyToOne
    @JoinColumn(name = "id_grille_modele", nullable = false)
    @Index(name = "idx_grille_modele_grille")
    private GrilleModele grilleModele;

    /**
     * Liste des catégories associées à la grille.
     */
    @OneToMany(mappedBy = "grille", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = ItemComparator.class)
    private SortedSet<Item> items = new TreeSet<Item>(new ItemComparator());

    /**
     * Getter sur grilleModele.
     * @return Retourne le grilleModele.
     */
    public GrilleModele getGrilleModele() {
        return this.grilleModele;
    }

    /**
     * Setter pour grilleModele.
     * @param grilleModele le grilleModele à écrire.
     */
    public void setGrilleModele(final GrilleModele grilleModele) {
        this.grilleModele = grilleModele;
    }

    /**
     * Getter sur items.
     * @return Retourne le items.
     */
    public SortedSet<Item> getItems() {
        return this.items;
    }

    /**
     * Setter pour items.
     * @param items le items à écrire.
     */
    public void setItems(final SortedSet<Item> items) {
        this.items = items;
    }

    /**
     * Getter sur detailSurcout.
     * @return Retourne le detailSurcout.
     */
    public DetailSurcout getDetailSurcout() {
        return this.detailSurcout;
    }

    /**
     * Setter pour detailSurcout.
     * @param detailSurcout le detailSurcout à écrire.
     */
    public void setDetailSurcout(final DetailSurcout detailSurcout) {
        this.detailSurcout = detailSurcout;
    }

}

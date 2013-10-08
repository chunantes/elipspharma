package fr.pharma.eclipse.domain.model.stockage;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.hibernate.validator.constraints.NotEmpty;

import fr.pharma.eclipse.comparator.stockage.StockageComparator;
import fr.pharma.eclipse.domain.enums.ConditionConservation;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Bean métier représentant un lieu de stockage.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "stockage")
public class Stockage extends BeanObject {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2451492548066590384L;

    /**
     * Stockage parent.
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name = "id_stockage_parent")
    @Index(name = "idx_stockage_parent")
    private Stockage parent;

    /**
     * Stockage enfants.
     */
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = StockageComparator.class)
    private SortedSet<Stockage> enfants = new TreeSet<Stockage>(new StockageComparator());

    /**
     * Pharmacie.
     */
    @ManyToOne
    @JoinColumn(name = "id_pharmacie", nullable = false)
    @Index(name = "idx_pharmacie_stockage")
    private Pharmacie pharmacie;

    /**
     * Nom.
     */
    @Column(name = "nom")
    @NotNull
    @NotEmpty
    private String nom;

    /**
     * Identifiant de stockage.
     */
    @Column(name = "identifiantStockage")
    private String identifiantStockage;

    /**
     * Identifiant sonde de température.
     */
    @Column(name = "identifiantSondeTemp")
    private String identifiantSondeTemp;

    /**
     * Identifiant enregistreur de température.
     */
    @Column(name = "identifiantEnregistreurTemp")
    private String identifiantEnregistreurTemp;

    /**
     * Condition de conservation.
     */
    @Column(name = "conservation")
    @Enumerated(EnumType.STRING)
    private ConditionConservation conservation;

    /**
     * Getter pour nom.
     * @return Le nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Setter pour nom.
     * @param nom Le nom à écrire.
     */
    public void setNom(final String nom) {
        this.nom = nom;
    }

    /**
     * Getter pour parent.
     * @return Le parent
     */
    public Stockage getParent() {
        return this.parent;
    }

    /**
     * Setter pour parent.
     * @param parent Le parent à écrire.
     */
    public void setParent(final Stockage parent) {
        this.parent = parent;
    }

    /**
     * Getter pour enfants.
     * @return Le enfants
     */
    public SortedSet<Stockage> getEnfants() {
        return this.enfants;
    }

    /**
     * Setter pour enfants.
     * @param enfants Le enfants à écrire.
     */
    public void setEnfants(final SortedSet<Stockage> enfants) {
        this.enfants = enfants;
    }

    /**
     * Getter pour conservation.
     * @return Le conservation
     */
    public ConditionConservation getConservation() {
        return this.conservation;
    }

    /**
     * Setter pour conservation.
     * @param conservation Le conservation à écrire.
     */
    public void setConservation(final ConditionConservation conservation) {
        this.conservation = conservation;
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
     * Méthode en charge de retouner le nom complet d'un stockage.
     * @return Nom complet du stockage (inclusion du nom du parent).
     */
    public String getNomComplet() {
        String result = this.getNom();

        Stockage stockParent = this.getParent();
        while (stockParent != null) {
            result = stockParent.getNom() + EclipseConstants.DASH + result;
            stockParent = stockParent.getParent();
        }
        return result;
    }

    /**
     * Cette méthode permet de contourner un bug du composant Tree de
     * PrimeFaces.<br>
     * En effet, lorsqu'un nom de stockage contient un apostrophe, il se produit
     * une erreur javascript car le délimiteur de propriétés contenu dans le
     * JSON alimentant le p:tree est l'apostrophe. <br>
     * Cette méthode permet donc de recuperer le nom du stockage en remplaçant
     * les apostrophe par leur équivalent unicode.
     * @return le nom du stockage
     */
    public String getNomPourPTree() {
        return this.nom != null ? this.nom.replace("'", "&#39;") : null;
    }
    /**
     * Getter pour identifiantStockage.
     * @return Le identifiantStockage
     */
    public String getIdentifiantStockage() {
        return this.identifiantStockage;
    }

    /**
     * Setter pour identifiantStockage.
     * @param identifiantStockage Le identifiantStockage à écrire.
     */
    public void setIdentifiantStockage(final String identifiantStockage) {
        this.identifiantStockage = identifiantStockage;
    }

    /**
     * Getter pour identifiantSondeTemp.
     * @return Le identifiantSondeTemp
     */
    public String getIdentifiantSondeTemp() {
        return this.identifiantSondeTemp;
    }

    /**
     * Setter pour identifiantSondeTemp.
     * @param identifiantSondeTemp Le identifiantSondeTemp à écrire.
     */
    public void setIdentifiantSondeTemp(final String identifiantSondeTemp) {
        this.identifiantSondeTemp = identifiantSondeTemp;
    }

    /**
     * Getter pour identifiantEnregistreurTemp.
     * @return Le identifiantEnregistreurTemp
     */
    public String getIdentifiantEnregistreurTemp() {
        return this.identifiantEnregistreurTemp;
    }

    /**
     * Setter pour identifiantEnregistreurTemp.
     * @param identifiantEnregistreurTemp Le identifiantEnregistreurTemp à
     * écrire.
     */
    public void setIdentifiantEnregistreurTemp(final String identifiantEnregistreurTemp) {
        this.identifiantEnregistreurTemp = identifiantEnregistreurTemp;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("[");
        builder.append("id: ").append(this.getId());
        builder.append(", pharmacie: ").append(this.pharmacie);
        builder.append(", nom: ").append(this.nom);
        builder.append(", idStockage: ").append(this.identifiantStockage);
        return builder.append("]").toString();
    }

}

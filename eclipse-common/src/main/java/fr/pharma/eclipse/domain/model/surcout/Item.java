package fr.pharma.eclipse.domain.model.surcout;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import fr.pharma.eclipse.comparator.surcout.RegleComparator;
import fr.pharma.eclipse.domain.enums.surcout.Acte;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.common.BeanWithNom;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;

/**
 * Classe du modèle représentant un item d'une grille de surcout.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "item")
public class Item
    extends BeanObject
    implements BeanWithNom
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 2090366602474984084L;

    /**
     * Grille à laquelle l'item est rattachée.
     */
    @ManyToOne
    @JoinColumn(name = "id_grille", nullable = false)
    @Index(name = "idx_grille_item")
    @NotNull
    private Grille grille;

    /**
     * Acte pharmaceutique correspondant.
     */
    @Column(name = "acte")
    @Enumerated(EnumType.STRING)
    private Acte acte;

    /**
     * Thème.
     */
    @Column(name = "theme")
    private String theme;

    /**
     * Catégorie.
     */
    @Column(name = "categorie")
    private String categorie;

    /**
     * Liste des règles associées à l'item.
     */
    @ManyToMany(targetEntity = Regle.class)
    @JoinTable(name = "item_regle", joinColumns = @JoinColumn(name = "id_item"), inverseJoinColumns = @JoinColumn(name = "id_regle"))
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = RegleComparator.class)
    private SortedSet<Regle> regles = new TreeSet<Regle>(new RegleComparator());

    /**
     * Getter sur grille.
     * @return Retourne le grille.
     */
    public Grille getGrille()
    {
        return this.grille;
    }

    /**
     * Setter pour grille.
     * @param grille le grille à écrire.
     */
    public void setGrille(final Grille grille)
    {
        this.grille = grille;
    }

    /**
     * Getter sur categorie.
     * @return Retourne le categorie.
     */
    public String getCategorie()
    {
        return this.categorie;
    }

    /**
     * Getter sur regles.
     * @return Retourne le regles.
     */
    public SortedSet<Regle> getRegles()
    {
        return this.regles;
    }

    /**
     * Setter pour regles.
     * @param regles le regles à écrire.
     */
    public void setRegles(final SortedSet<Regle> regles)
    {
        this.regles = regles;
    }

    /**
     * Getter sur theme.
     * @return Retourne le theme.
     */
    public String getTheme()
    {
        return this.theme;
    }

    /**
     * Setter pour theme.
     * @param theme le theme à écrire.
     */
    public void setTheme(final String theme)
    {
        this.theme = theme;
    }

    /**
     * Setter pour categorie.
     * @param categorie le categorie à écrire.
     */
    public void setCategorie(final String categorie)
    {
        this.categorie = categorie;
    }

    /**
     * Getter sur acte.
     * @return Retourne le acte.
     */
    public Acte getActe()
    {
        return this.acte;
    }

    /**
     * Setter pour acte.
     * @param acte le acte à écrire.
     */
    public void setActe(final Acte acte)
    {
        this.acte = acte;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNom()
    {
        String s = "";
        if (this.theme != null)
        {
            s += this.theme;
        }
        if (this.categorie != null)
        {
            s += this.categorie;
        }
        return s;
    }

}

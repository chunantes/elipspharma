package fr.pharma.eclipse.domain.model.surcout;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.comparator.surcout.RegleComparator;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.common.BeanWithNom;
import fr.pharma.eclipse.domain.model.common.Clonable;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;

/**
 * Classe du modèle représentant un thème dans la grille des surcouts.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "theme")
public class Theme
    extends BeanObject
    implements BeanWithNom, Clonable<Theme>
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -7658479183102326800L;

    /**
     * Grille modèle à laquelle la catégorie est rattachée.
     */
    @ManyToOne
    @JoinColumn(name = "id_grille_modele", nullable = false)
    @Index(name = "idx_grille_modele_theme")
    @NotNull
    private GrilleModele grilleModele;

    /**
     * Libellé de la catégorie.
     */
    @Column(name = "libelle")
    private String libelle;

    /**
     * Catégories.
     */
    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = BeanWithNomComparator.class)
    private SortedSet<Categorie> categories = new TreeSet<Categorie>(new BeanWithNomComparator());

    /**
     * Liste des règles associées au thème.
     */
    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = RegleComparator.class)
    private SortedSet<Regle> regles = new TreeSet<Regle>(new RegleComparator());

    /**
     * Retourne <true> si le thème ne contient aucune catégorie et peut donc être considéré comme
     * une catégorie.
     * @return <true> si le thème ne contient aucune catégorie et peut donc être considéré comme
     * une catégorie.
     */
    public boolean isCategorie()
    {
        return this.getCategories().isEmpty();
    }

    /**
     * Getter sur categories.
     * @return Retourne le categories.
     */
    public SortedSet<Categorie> getCategories()
    {
        return this.categories;
    }

    /**
     * Setter pour categories.
     * @param categories le categories à écrire.
     */
    public void setCategories(final SortedSet<Categorie> categories)
    {
        this.categories = categories;
    }

    /**
     * Getter sur grilleModele.
     * @return Retourne le grilleModele.
     */
    public GrilleModele getGrilleModele()
    {
        return this.grilleModele;
    }

    /**
     * Setter pour grilleModele.
     * @param grilleModele le grilleModele à écrire.
     */
    public void setGrilleModele(final GrilleModele grilleModele)
    {
        this.grilleModele = grilleModele;
    }

    /**
     * Getter sur libelle.
     * @return Retourne le libelle.
     */
    public String getLibelle()
    {
        return this.libelle;
    }

    /**
     * Setter pour libelle.
     * @param libelle le libelle à écrire.
     */
    public void setLibelle(final String libelle)
    {
        this.libelle = libelle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNom()
    {
        return this.getLibelle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Theme cloneMe()
    {
        final Theme theme = new Theme();
        theme.setLibelle(this.getLibelle());
        for (final Categorie cat : this.getCategories())
        {
            final Categorie copie = cat.cloneMe();
            copie.setTheme(theme);
            theme.getCategories().add(copie);
        }
        return theme;
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

}

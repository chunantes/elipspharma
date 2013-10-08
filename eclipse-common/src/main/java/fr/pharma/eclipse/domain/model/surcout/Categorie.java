package fr.pharma.eclipse.domain.model.surcout;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
import fr.pharma.eclipse.domain.model.common.Clonable;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;

/**
 * Catégorie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "categorie")
public class Categorie extends BeanObject implements BeanWithNom, Clonable<Categorie> {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -188094534255871987L;

    /**
     * Thème de la catégorie.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_theme", nullable = false)
    @Index(name = "idx_theme_categorie")
    @NotNull
    private Theme theme;

    /**
     * Libellé de la catégorie.
     */
    @Column(name = "libelle")
    private String libelle;

    /**
     * Acte pharmaceutique correspondant.
     */
    @Column(name = "acte")
    @Enumerated(EnumType.STRING)
    private Acte acte;

    /**
     * Liste des règles associées à la catégorie.
     */
    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = RegleComparator.class)
    private SortedSet<Regle> regles = new TreeSet<Regle>(new RegleComparator());

    /**
     * Getter sur theme.
     * @return Retourne le theme.
     */
    public Theme getTheme() {
        return this.theme;
    }

    /**
     * Setter pour theme.
     * @param theme le theme à écrire.
     */
    public void setTheme(final Theme theme) {
        this.theme = theme;
    }

    /**
     * Getter sur libelle.
     * @return Retourne le libelle.
     */
    public String getLibelle() {
        return this.libelle;
    }

    /**
     * Setter pour libelle.
     * @param libelle le libelle à écrire.
     */
    public void setLibelle(final String libelle) {
        this.libelle = libelle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNom() {
        return this.getLibelle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Categorie cloneMe() {
        final Categorie categorie = new Categorie();
        categorie.setLibelle(this.getLibelle());
        categorie.setActe(this.getActe());
        for (final Regle regle : this.regles) {
            final Regle r = regle.cloneMe();
            r.setCategorie(categorie);
            categorie.getRegles().add(r);
        }
        return categorie;
    }

    /**
     * Getter sur acte.
     * @return Retourne le acte.
     */
    public Acte getActe() {
        return this.acte;
    }

    /**
     * Setter pour acte.
     * @param acte le acte à écrire.
     */
    public void setActe(final Acte acte) {
        this.acte = acte;
    }

    /**
     * Getter sur regles.
     * @return Retourne le regles.
     */
    public SortedSet<Regle> getRegles() {
        return this.regles;
    }

    /**
     * Setter pour regles.
     * @param regles le regles à écrire.
     */
    public void setRegles(final SortedSet<Regle> regles) {
        this.regles = regles;
    }

}

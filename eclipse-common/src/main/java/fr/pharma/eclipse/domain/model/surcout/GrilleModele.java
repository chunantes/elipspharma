package fr.pharma.eclipse.domain.model.surcout;

import java.util.Calendar;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.comparator.common.EclipseListComparator;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.common.Clonable;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Modèle de grille des surcouts.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "grille_modele")
public class GrilleModele extends BeanObject implements Clonable<GrilleModele> {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -1791948248320781742L;

    @Column(name = "nom")
    private String nom;

    /**
     * Date de création.
     */
    @Column(name = "dateCreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateCreation;

    /**
     * Date de début de validite.
     */
    @Column(name = "dateDebut")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateDebutValidite;

    /**
     * Date de fin de validite.
     */
    @Column(name = "dateFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateFinValidite;

    /**
     * Liste des Grilles utilisant cette grille modèle.
     */
    @OneToMany(mappedBy = "grilleModele")
    @Sort(type = SortType.COMPARATOR, comparator = EclipseListComparator.class)
    private SortedSet<Grille> grilles = new TreeSet<Grille>(new EclipseListComparator());

    /**
     * Liste des thèmes associées à la grille.
     */
    @OneToMany(mappedBy = "grilleModele", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = BeanWithNomComparator.class)
    private SortedSet<Theme> themes = new TreeSet<Theme>(new BeanWithNomComparator());

    /**
     * Getter sur nom.
     * @return Retourne le nom.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Setter pour nom.
     * @param nom le nom à écrire.
     */
    public void setNom(final String nom) {
        this.nom = nom;
    }

    /**
     * Getter sur dateDebutValidite.
     * @return Retourne le dateDebutValidite.
     */
    public Calendar getDateDebutValidite() {
        return this.dateDebutValidite;
    }

    /**
     * Setter pour dateDebutValidite.
     * @param dateDebutValidite le dateDebutValidite à écrire.
     */
    public void setDateDebutValidite(final Calendar dateDebutValidite) {
        this.dateDebutValidite = dateDebutValidite;
    }

    /**
     * Getter sur dateFinValidite.
     * @return Retourne le dateFinValidite.
     */
    public Calendar getDateFinValidite() {
        return this.dateFinValidite;
    }

    /**
     * Setter pour dateFinValidite.
     * @param dateFinValidite le dateFinValidite à écrire.
     */
    public void setDateFinValidite(final Calendar dateFinValidite) {
        this.dateFinValidite = dateFinValidite;
    }

    /**
     * Getter sur themes.
     * @return Retourne le themes.
     */
    public SortedSet<Theme> getThemes() {
        return this.themes;
    }

    /**
     * Setter pour themes.
     * @param themes le themes à écrire.
     */
    public void setThemes(final SortedSet<Theme> themes) {
        this.themes = themes;
    }

    /**
     * Getter sur dateCreation.
     * @return Retourne le dateCreation.
     */
    public Calendar getDateCreation() {
        return this.dateCreation;
    }

    /**
     * Setter pour dateCreation.
     * @param dateCreation le dateCreation à écrire.
     */
    public void setDateCreation(final Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GrilleModele cloneMe() {
        final GrilleModele grille = new GrilleModele();
        grille.setDateCreation(Calendar.getInstance(EclipseConstants.LOCALE));
        grille.setNom(this.getNom() + " - copie");
        for (final Theme theme : this.getThemes()) {
            final Theme copie = theme.cloneMe();
            copie.setGrilleModele(grille);
            grille.getThemes().add(copie);
        }
        return grille;
    }

    /**
     * Getter sur grilles.
     * @return Retourne le grilles.
     */
    public SortedSet<Grille> getGrilles() {
        return this.grilles;
    }

    /**
     * Setter pour grilles.
     * @param grilles le grilles à écrire.
     */
    public void setGrilles(final SortedSet<Grille> grilles) {
        this.grilles = grilles;
    }

}

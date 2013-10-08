package fr.pharma.eclipse.domain.model.localisation;

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
import org.hibernate.validator.constraints.NotEmpty;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.model.common.BeanObjectSuivi;
import fr.pharma.eclipse.domain.model.suivi.localisation.PoleSuivi;

/**
 * Classe métier représentant un pôle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "pole")
public class Pole extends BeanObjectSuivi {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 382696611843678344L;

    /**
     * Nom.
     */
    @Column(name = "nom")
    @NotNull
    @NotEmpty
    private String nom;

    /**
     * Etablissement.
     */
    @ManyToOne
    @JoinColumn(name = "id_etablissement", nullable = false)
    @Index(name = "idx_etab_pole")
    @NotNull
    private Etablissement etablissement;

    /**
     * Liste des services.
     */
    @OneToMany(mappedBy = "pole", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = BeanWithNomComparator.class)
    private SortedSet<Service> services = new TreeSet<Service>(new BeanWithNomComparator());

    /**
     * Liste des modifications du pole.
     */
    @OneToMany(mappedBy = "pole", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private final SortedSet<PoleSuivi> modifs = new TreeSet<PoleSuivi>(new SuiviComparator());

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
     * Getter sur modifs.
     * @return Retourne le modifs.
     */
    @Override
    public SortedSet<PoleSuivi> getModifs() {
        return this.modifs;
    }

    /**
     * Getter sur etablissement.
     * @return Retourne le etablissement.
     */
    public Etablissement getEtablissement() {
        return this.etablissement;
    }

    /**
     * Setter pour etablissement.
     * @param etablissement le etablissement à écrire.
     */
    public void setEtablissement(final Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    /**
     * Getter pour services.
     * @return Le services
     */
    public SortedSet<Service> getServices() {
        return this.services;
    }

    /**
     * Setter pour services.
     * @param services Le services à écrire.
     */
    public void setServices(final SortedSet<Service> services) {
        this.services = services;
    }

}

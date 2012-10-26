package fr.pharma.eclipse.domain.model.acteur;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.domain.model.localisation.Service;

/**
 * Classe métier représentant un investigateur.
 
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("INVESTIGATEUR")
public class Investigateur
    extends Personne
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3042823403906888158L;

    /**
     * Titre.
     */
    @Column(name = "titre")
    private String titre;

    /**
     * Services.
     */
    @ManyToMany(targetEntity = Service.class)
    @JoinTable(name = "investigateur_service", joinColumns = @JoinColumn(name = "id_investigateur"), inverseJoinColumns = @JoinColumn(name = "id_service"))
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = BeanWithNomComparator.class)
    private SortedSet<Service> services = new TreeSet<Service>(new BeanWithNomComparator());

    /**
     * Getter sur titre.
     * @return Retourne le titre.
     */
    public String getTitre()
    {
        return this.titre;
    }

    /**
     * Setter pour titre.
     * @param titre le titre à écrire.
     */
    public void setTitre(final String titre)
    {
        this.titre = titre;
    }

    /**
     * Getter sur services.
     * @return Retourne le services.
     */
    public SortedSet<Service> getServices()
    {
        return this.services;
    }

    /**
     * Setter pour services.
     * @param services le services à écrire.
     */
    public void setServices(final SortedSet<Service> services)
    {
        this.services = services;
    }

}

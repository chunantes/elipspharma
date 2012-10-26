package fr.pharma.eclipse.domain.model.acteur;

import java.util.SortedSet;
import java.util.TreeSet;

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
 * Classe métier représentant un ARC Investigateur.
 
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("ARC_INVESTIGATEUR")
public class ArcInvestigateur
    extends Personne
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1269749991455831963L;

    /**
     * Services.
     */
    @ManyToMany(targetEntity = Service.class)
    @JoinTable(name = "arcinvestigateur_service", joinColumns = @JoinColumn(name = "id_arcinvestigateur"), inverseJoinColumns = @JoinColumn(name = "id_service"))
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = BeanWithNomComparator.class)
    private SortedSet<Service> services = new TreeSet<Service>(new BeanWithNomComparator());

    /**
     * Getter pour services.
     * @return Le services
     */
    public SortedSet<Service> getServices()
    {
        return this.services;
    }

    /**
     * Setter pour services.
     * @param services Le services à écrire.
     */
    public void setServices(final SortedSet<Service> services)
    {
        this.services = services;
    }

}

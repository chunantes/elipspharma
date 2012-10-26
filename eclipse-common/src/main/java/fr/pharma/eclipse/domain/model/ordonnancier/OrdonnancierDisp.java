package fr.pharma.eclipse.domain.model.ordonnancier;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import fr.pharma.eclipse.comparator.ordonnancier.OrdonnancierDispComparator;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;

/**
 * Bean métier représentant des ordonnanciers de type Dispensation.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "ordonnancier_dispensation")
public class OrdonnancierDisp
    extends Ordonnancier
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4716390853130442696L;

    /**
     * Liste des dispensations associées à l'ordonnancier.
     */
    @OneToMany(mappedBy = "ordonnancier", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = OrdonnancierDispComparator.class)
    private final SortedSet<Dispensation> dispensations =
        new TreeSet<Dispensation>(new OrdonnancierDispComparator());

    /**
     * Getter pour dispensations.
     * @return Le dispensations
     */
    public SortedSet<Dispensation> getDispensations()
    {
        return this.dispensations;
    }

}

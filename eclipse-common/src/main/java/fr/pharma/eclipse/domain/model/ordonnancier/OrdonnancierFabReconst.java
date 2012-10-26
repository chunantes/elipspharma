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

import fr.pharma.eclipse.comparator.ordonnancier.OrdonnancierFabReconstComparator;
import fr.pharma.eclipse.domain.model.stock.PreparationEntree;

/**
 * Bean métier représentant des ordonnanciers de type Fabrication / Reconstitution.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "ordonnancier_fab_reconst")
public class OrdonnancierFabReconst
    extends Ordonnancier
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1010844696343374149L;

    /**
     * Liste des préparations associés à l'ordonnancier.
     */
    @OneToMany(mappedBy = "ordonnancier", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = OrdonnancierFabReconstComparator.class)
    private final SortedSet<PreparationEntree> elementsToCheck =
        new TreeSet<PreparationEntree>(new OrdonnancierFabReconstComparator());

    /**
     * Getter pour elementsToCheck.
     * @return Le elementsToCheck
     */
    public SortedSet<PreparationEntree> getElementsToCheck()
    {
        return this.elementsToCheck;
    }

}

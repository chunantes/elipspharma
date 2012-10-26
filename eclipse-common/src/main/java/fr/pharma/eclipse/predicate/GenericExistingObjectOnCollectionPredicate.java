package fr.pharma.eclipse.predicate;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.util.Assert;

import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Ce prédicat parcourt une liste d'un objet,<br>
 * et renvoie true si et seulement si un des objets satisfait un sous-prédicat.
 
 * @version $Revision$ $Date$
 */
public class GenericExistingObjectOnCollectionPredicate
    implements Predicate
{
    /**
     * Propriété de l'objet évalué, représentant la collection d'objets à évaluer.
     */
    private String collectionProperty;

    /**
     * Prédicat à appliquer sur la collection de l'objet évalué.
     */
    private final Predicate collectionObjectsPredicate;

    /**
     * Constructeur.
     * @param collectionProperty Propriété de l'objet évalué, représentant la collection d'objets
     * à évaluer (non nul).
     * @param collectionObjectsPredicate Prédicat à appliquer sur la collection de l'objet évalué
     * (non nul).
     */
    public GenericExistingObjectOnCollectionPredicate(
                                                      final String collectionProperty,
                                                      final Predicate collectionObjectsPredicate)
    {
        Assert.notNull(collectionProperty,
                       "La propriété 'collectionProperty' ne doit pas être nulle.");
        Assert.notNull(collectionObjectsPredicate,
                       "La propriété 'collectionObjectsPredicate' ne doit pas être nulle.");
        this.collectionProperty = collectionProperty;
        this.collectionObjectsPredicate = collectionObjectsPredicate;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean evaluate(final Object object)
    {
        final Object propertyAsObject = BeanTool.getPropriete(object,
                                                              this.collectionProperty);
        if (propertyAsObject == null
            || !(propertyAsObject instanceof Collection))
        {
            return false;
        }
        final Collection propertyAsCollection = (Collection) propertyAsObject;
        return CollectionUtils.exists(propertyAsCollection,
                                      this.collectionObjectsPredicate);
    }

    /**
     * Getter sur collectionObjectsPredicate.
     * @return Retourne le collectionObjectsPredicate.
     */
    Predicate getCollectionObjectsPredicate()
    {
        return this.collectionObjectsPredicate;
    }

    /**
     * Getter sur collectionProperty.
     * @return Retourne le collectionProperty.
     */
    String getCollectionProperty()
    {
        return this.collectionProperty;
    }

    /**
     * Setter pour collectionProperty.
     * @param collectionProperty le collectionProperty à écrire.
     */
    void setCollectionProperty(final String collectionProperty)
    {
        this.collectionProperty = collectionProperty;
    }

}

package fr.pharma.eclipse.predicate;

import org.apache.commons.collections.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.exception.common.CommonException;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Classe en charge de fournir une implémentation particulière des prédicates. L'utilisation
 * simple est d'instancier cette classe avec le nom d'une propriété + une valeur et de passer
 * cette instance à une méthode de la classe utilitaire CollectionUtils (countMatches, select,
 * exists, filter, find, select)
 * @author Sébastien ROUL
 * @version $Revision$ $Date$
 */
public class GenericPredicate
    implements Predicate
{
    /**
     * Logger par défaut.
     */
    private static final Logger LOG = LoggerFactory.getLogger(GenericPredicate.class);

    /**
     * Caractère JOKER.
     */
    private static final String WILDCARD = "*";

    /**
     * Propriété a comparer.
     */
    private final String propriete;

    /**
     * Valeur de comparaison.
     */
    private final Object value;

    /**
     * Si true : alors renvoie true lorsque le caractere "*" est trouvé.
     */
    private boolean allValue;

    /**
     * Constructeur.
     * @param propriete Propriété à compararer
     * @param value : objet de référence (doit implémenter Comparable)
     */
    public GenericPredicate(final String propriete, final Object value)
    {
        this.propriete = propriete;
        this.value = value;
    }

    /**
     * Constructeur.
     * @param propriete Propriété à compararer
     * @param value Objet de référence (doit implémenter Comparable)
     * @param allValue Si true : alors renvoie true lorsque le caractere "*" est trouvé.
     */
    public GenericPredicate(final String propriete, final Object value, final boolean allValue)
    {
        this(propriete,
             value);
        this.allValue = allValue;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public boolean evaluate(final Object objectToCompare)
    {
        try
        {
            final Object propertyValueAsObject = BeanTool.getPropriete(objectToCompare,
                                                                       this.propriete);
            if (propertyValueAsObject == null)
            {
                return false;
            }

            // Gestion du allValue si actif
            if (this.allValue
                && (propertyValueAsObject instanceof String)
                && (GenericPredicate.WILDCARD.equals(propertyValueAsObject)))
            {
                return true;
            }
            // La propriété est un bean du modèle métier
            if (propertyValueAsObject instanceof BeanObject)
            {
                return this.handleBeanObject(propertyValueAsObject);
            }
            else
            {
                // Préparation des valeurs
                final Comparable compObject = (Comparable) propertyValueAsObject;
                Comparable refObject = null;
                if (this.value instanceof BeanObject)
                {
                    refObject = (Comparable) BeanTool.getPropriete(this.value,
                                                                   this.propriete);
                }
                else
                {
                    refObject = (Comparable) this.value;
                }
                if (compObject.compareTo(refObject) == 0)
                {
                    return true;
                }
            }
        }
        catch (final CommonException e)
        {
            GenericPredicate.LOG.error(e.getMessage(),
                                 e);
        }
        return false;
    }

    /**
     * Méthode en charge de traiter le predicator sur un BeanObject.
     * @param propertyValueAsObject Valeur propriété.
     * @return Résultat de comparaison.
     */
    public boolean handleBeanObject(final Object propertyValueAsObject)
    {
        final BeanObject valueAsBeanObject = (BeanObject) this.value;
        final BeanObject propertyValueAsBeanObject = (BeanObject) propertyValueAsObject;

        // On compare les ids.
        final Long propertyValueId = propertyValueAsBeanObject.getId();
        final Long valueId = valueAsBeanObject.getId();

        if (propertyValueId == null)
        {
            return false;
        }
        return propertyValueId.equals(valueId);
    }

}

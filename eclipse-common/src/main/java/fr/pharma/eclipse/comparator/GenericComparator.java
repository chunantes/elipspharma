package fr.pharma.eclipse.comparator;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Comparateur générique.
 * @param <BEAN> bean.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GenericComparator<BEAN extends BeanObject> implements Comparator<BEAN>, Serializable {

    /**
     * Serial id.
     */
    private static final long serialVersionUID = 4574987235322605074L;

    /**
     * Nom de la propriété à comparer.
     */
    private final String propertyName;

    /**
     * Constructeur.
     * @param propertyName Propriété à comparer.
     */
    public GenericComparator(final String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public int compare(final BEAN o1,
                       final BEAN o2) {
        return ((Comparable) BeanTool.getPropriete(o1, this.getPropertyName())).compareTo(BeanTool.getPropriete(o2, this.getPropertyName()));
    }

    /**
     * Retourne le propertyName.
     * @return Retourne le propertyName.
     */
    public String getPropertyName() {
        return this.propertyName;
    }
}

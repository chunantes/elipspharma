package fr.pharma.eclipse.service.helper.common;

import java.io.Serializable;
import java.util.Collection;

import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Classe en charge de réinitialiser des propriétés d'un bean.
 * @param <BEAN> Type d'objet Eclipse à réinitialiser.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BeanPropertyReinitializer<BEAN extends Object> implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1622700147331357831L;

    /**
     * Méthode de remise à null d'une propriété dans un bean.
     * @param bean Bean à modifier.
     * @param propertyPathFromBean Accès à la propriété à réinitialiser pour
     * instrospection depuis le bean.
     */
    public void resetPropertyToNull(final BEAN bean,
                                    final String propertyPathFromBean) {
        BeanTool.setPropriete(bean, propertyPathFromBean, null);
    }

    /**
     * Méthode qui vide une collection dans un bean. Ne fait rien si la
     * propriété est nulle.
     * @param bean Bean à modifier.
     * @param propertyPathFromBean Accès à la collection à réinitialiser pour
     * instrospection depuis le bean.
     */
    @SuppressWarnings("unchecked")
    public void resetCollection(final BEAN bean,
                                final String propertyPathFromBean) {
        final Collection<? extends Object> collection = (Collection<? extends Object>) BeanTool.getPropriete(bean, propertyPathFromBean);
        if (collection == null) {
            return;
        }
        collection.clear();
    }
}

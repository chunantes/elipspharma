package fr.pharma.eclipse.utils.converter;

import fr.pharma.eclipse.domain.model.common.BeanObject;

/**
 * Interface des converter en charge de convertir un bean en un autre beanobject.
 * @param <BEANTECH> Type du bean source.
 * @param <BEAN> Type du bean métier destination.
 
 * @version $Revision$ $Date$
 */
public interface BeanConverter<BEANTECH extends Object, BEAN extends BeanObject>
{
    /**
     * Méthode en charge de convertir un bean en un autre bean métier.
     * @param source Bean source.
     * @return Le bean destination.
     */
    BEAN convert(final BEANTECH source);
}

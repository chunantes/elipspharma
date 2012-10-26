package fr.pharma.eclipse.domain.model.common;

import java.io.Serializable;

/**
 * Objet avec une propriété nom.
 
 * @version $Revision$ $Date$
 */
public interface BeanWithNom
    extends Serializable
{
    /**
     * Accesseur en lecture sur le nom du bean.
     * @return Le nom du bean.
     */
    String getNom();
}

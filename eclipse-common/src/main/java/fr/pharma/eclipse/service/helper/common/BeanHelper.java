package fr.pharma.eclipse.service.helper.common;

import java.io.Serializable;
import java.util.Collection;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Helper générique pour la manipulation des beans par les classes de services.
 * @param <BEAN> Type générique d'objets Eclipse.
 
 * @version $Revision$ $Date$
 */
public class BeanHelper<BEAN extends BeanObject>
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4262086395093018836L;

    /**
     * Méthode en charge d'ajouter un objet dans une liste d'objets d'un bean.
     * @param <CHILD> Type générique des objets de la liste.
     * @param bean Objet principal.
     * @param beanCollectionProperty Propriété du bean contenant la collection à mettre à jour.
     * @param childToAdd Elément à ajouter à la liste du bean principal.
     */
    @SuppressWarnings("unchecked")
    public <CHILD extends BeanObject> void addToCollection(final BEAN bean,
                                                           final String beanCollectionProperty,
                                                           final CHILD childToAdd)
    {
        final Collection<CHILD> collection =
            (Collection<CHILD>) BeanTool.getPropriete(bean,
                                                      beanCollectionProperty);
        collection.add(childToAdd);
    }

}

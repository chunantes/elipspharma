package fr.pharma.eclipse.factory.common;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Fabrique des objets métiers qui stockent leur créateur.
 * @author Netapsys
 * @version $Revision$ $Date$
 * @param <BEAN> Type de l'objet métier créé.
 * @param <PARENT> Type de l'objet métier parent.
 */
public class BeanObjectWithParentFactory<BEAN extends BeanObject, PARENT extends BeanObject> implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4732124974209651375L;

    /**
     * Fabrique de base servant à instancier l'objet.
     */
    private BeanObjectFactory<BEAN> simpleFactory;

    /**
     * Propriété du bean créé où stocker le bean parent.
     */
    private String propertyToStoreParent;

    /**
     * Méthode en charge de retourner un BeanObject initialisé.
     * @param parent Objet métier parent du bean créé.
     * @return Retourne un BeanObject initialisé.
     */
    public BEAN getInitializedObject(final PARENT parent) {
        final BEAN bean = this.simpleFactory.getInitializedObject();
        if (!StringUtils.hasText(this.propertyToStoreParent)) {
            return bean;
        }
        // Stockage du parent sur le bean.
        BeanTool.setPropriete(bean, this.propertyToStoreParent, parent);
        return bean;
    }

    /**
     * Setter pour simpleFactory.
     * @param simpleFactory le simpleFactory à écrire.
     */
    public void setSimpleFactory(final BeanObjectFactory<BEAN> simpleFactory) {
        this.simpleFactory = simpleFactory;
    }

    /**
     * Setter pour propertyToStoreParent.
     * @param propertyToStoreParent le propertyToStoreParent à écrire.
     */
    public void setPropertyToStoreParent(final String propertyToStoreParent) {
        this.propertyToStoreParent = propertyToStoreParent;
    }
}

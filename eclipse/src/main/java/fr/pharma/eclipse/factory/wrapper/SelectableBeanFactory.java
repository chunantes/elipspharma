package fr.pharma.eclipse.factory.wrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.pharma.eclipse.component.wrapper.SelectableBean;
import fr.pharma.eclipse.domain.model.common.BeanObject;

/**
 * Fabrique d'objets sélectionnables pour l'IHM.
 * @param <BEAN> Type des objets rattachés au wrapper créé.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SelectableBeanFactory<BEAN extends BeanObject> {
    /**
     * Méthode de fabrication d'objet SelectableBean.
     * @param bean Bean métier à wrapper.
     * @return Un objet SelectableBean.
     */
    public SelectableBean<BEAN> getInitializedObject(final BEAN bean) {
        return new SelectableBean<BEAN>(bean);
    }

    /**
     * Méthode de fabrication d'une liste d'objets SelectableBean.
     * @param beans Collection de beans métiers à wrapper.
     * @return Un objet SelectableBean.
     */
    public List<SelectableBean<BEAN>> getInitializedObjects(final Collection<BEAN> beans) {
        final List<SelectableBean<BEAN>> wrappers = new ArrayList<SelectableBean<BEAN>>();
        for (final BEAN bean : beans) {
            wrappers.add(this.getInitializedObject(bean));
        }
        return wrappers;
    }
}

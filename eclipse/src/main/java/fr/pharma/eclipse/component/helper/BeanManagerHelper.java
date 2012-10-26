package fr.pharma.eclipse.component.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;

import javax.faces.model.DataModel;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.faces.model.SerializableListDataModel;

import fr.pharma.eclipse.component.wrapper.SelectableBean;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.service.helper.common.BeanHelper;

/**
 * Classe de helper pour les traitements génériques nécessaires au management des beans métier.
 * @param <BEAN> Type d'objets générique.
 
 * @version $Revision$ $Date$
 */
public class BeanManagerHelper<BEAN extends BeanObject>
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3750664472385578902L;

    /**
     * Helper commun aux services.
     */
    private BeanHelper<BEAN> beanHelper;

    /**
     * Méthode en charge de créer un objet DataModel à partir d'une collection.
     * @param <OBJ> Type des objets métier de la collection.
     * @param beans Liste de beans.
     * @return Le DataModel correspondant.
     */
    @SuppressWarnings("unchecked")
    public <OBJ extends BeanObject> DataModel<OBJ> returnAsDataModel(final Collection<OBJ> beans)
    {
        final List<OBJ> list = new ArrayList<OBJ>(beans);
        return new SerializableListDataModel(list);
    }

    /**
     * Méthode en charge de marquer comme sélectionnés les beans de selectable qui apparaissent
     * dans selected.
     * @param <CHILD> Type d'objet générique des éléments de la liste à mettre à jour.
     * @param selected Liste des éléments de référence.
     * @param selectable Liste des éléments sélectionnables à mettre à jour.
     */
    public <CHILD extends BeanObject> void updateSelectable(final Collection<CHILD> selected,
                                                            final List<CHILD> selectable)
    {
        for (final CHILD child : selectable)
        {
            child.setSelected(selected.contains(child));
        }
    }

    /**
     * Méthode en charge de mettre à jour une liste du bean <br>
     * avec les éléments sélectionnés dans la liste des éléments <br>
     * sélectionnables.
     * @param <CHILD> Type d'objet générique des éléments de la liste à mettre à jour.
     * @param selected Liste des éléments à mettre à jour.
     * @param selectable Liste des éléments sélectionnables de reférence.
     */
    public <CHILD extends BeanObject> void updateSelected(final SortedSet<CHILD> selected,
                                                          final List<CHILD> selectable)
    {
        selected.clear();
        final List<CHILD> newSelected = new ArrayList<CHILD>();
        for (final CHILD beanSelectable : selectable)
        {
            if (beanSelectable.getSelected())
            {
                newSelected.add(beanSelectable);
            }
        }
        selected.addAll(newSelected);
    }

    /**
     * Méthode en charge de marquer comme sélectionnés les beans wrappés de selectable qui
     * apparaissent dans selected.
     * @param <CHILD> Type d'objet générique des éléments de la liste à mettre à jour.
     * @param selected Liste des éléments de référence.
     * @param selectableBeans Liste des éléments sélectionnables (wrappés) à mettre à jour.
     */
    public <CHILD extends BeanObject> void updateSelectableWrapped(final Collection<CHILD> selected,
                                                                   final List<SelectableBean<CHILD>> selectableBeans)
    {
        for (final SelectableBean<CHILD> child : selectableBeans)
        {
            child.setSelected(selected.contains(child.getBean()));
        }
    }

    /**
     * Méthode en charge de mettre à jour une liste du bean <br>
     * avec les éléments (wrappés) sélectionnés dans la liste des éléments <br>
     * sélectionnables.
     * @param <CHILD> Type d'objet générique des éléments de la liste à mettre à jour.
     * @param selected Liste des éléments à mettre à jour.
     * @param selectableBeans selectableBeans Liste des éléments sélectionnables (wrappés) de
     * reférence.
     */
    public <CHILD extends BeanObject> void updateSelectedWrapped(final SortedSet<CHILD> selected,
                                                                 final List<SelectableBean<CHILD>> selectableBeans)
    {
        selected.clear();
        final List<CHILD> newSelected = new ArrayList<CHILD>();
        for (final SelectableBean<CHILD> beanSelectable : selectableBeans)
        {
            if (beanSelectable.getSelected())
            {
                newSelected.add(beanSelectable.getBean());
            }
        }
        selected.addAll(newSelected);
    }

    /**
     * Méthode en charge d'ajouter un objet dans une liste d'objets d'un bean.
     * @param <CHILD> Type générique des objets de la liste.
     * @param bean Objet principal.
     * @param beanCollectionProperty Propriété du bean contenant la collection à mettre à jour.
     * @param childToAdd Elément à ajouter à la liste du bean principal.
     */
    public <CHILD extends BeanObject> void addToCollection(final BEAN bean,
                                                           final String beanCollectionProperty,
                                                           final CHILD childToAdd)
    {
        this.beanHelper.addToCollection(bean,
                                        beanCollectionProperty,
                                        childToAdd);
    }

    /**
     * Méthode en charge de retourner le premier élément d'une collection.
     * @param <OBJ> Type des éléments de la collection.
     * @param col Collection.
     * @return Le premier élément de col. Nul si col est nulle ou vide.
     */
    public <OBJ extends Object> OBJ getFirstOfCollection(final Collection<OBJ> col)
    {
        if (col == null
            || col.isEmpty())
        {
            return null;
        }
        return col.iterator().next();
    }

    /**
     * Méthode en charge de retourner la liste des objets métier sélectionnés dans une liste.
     * @param <OBJ> Type d'objet métier géré.
     * @param beans Liste des objets métier à inspecter.
     * @return La liste des objets sélectionnés dans beans.
     */
    public <OBJ extends BeanObject> List<OBJ> getBeansSelected(final Collection<OBJ> beans)
    {
        final List<OBJ> selected = new ArrayList<OBJ>(beans);
        CollectionUtils.filter(selected,
                               new Predicate() {
                                   /**
                                    * {@inheritDoc}
                                    */
                                   @SuppressWarnings("unchecked")
                                   @Override
                                   public boolean evaluate(final Object object)
                                   {
                                       return ((OBJ) object).getSelected();
                                   }
                               });
        return selected;
    }

    /**
     * Setter pour beanHelper.
     * @param beanHelper le beanHelper à écrire.
     */
    public void setBeanHelper(final BeanHelper<BEAN> beanHelper)
    {
        this.beanHelper = beanHelper;
    }
}

package fr.pharma.eclipse.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.model.DataModel;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.faces.model.SerializableListDataModel;

import fr.pharma.eclipse.component.wrapper.SelectableBean;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.redirect.RedirectHandler;

/**
 * Manager générique sur un ensemble de BEAN wrappés.
 
 * @version $Revision$ $Date$
 * @param <BEAN> Bean Objet Métier.
 */
public class SelectableBeansManager<BEAN extends BeanObject>
    implements Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1963474388008306930L;

    /**
     * Critère de recherche.
     */
    private final SearchCriteria searchCriteria;

    /**
     * Handler de redirection.
     */
    @Resource(name = "redirectHandler")
    private RedirectHandler redirectHandler;

    /**
     * Constructeur.
     * @param searchCriteria Critère de recherche.
     */
    public SelectableBeansManager(final SearchCriteria searchCriteria)
    {
        this.searchCriteria = searchCriteria;
    }

    /**
     * Liste des beans du tableau.
     */
    private List<SelectableBean<BEAN>> beans = new ArrayList<SelectableBean<BEAN>>();

    /**
     * BEAN sélectionné.
     */
    private SelectableBean<BEAN> beanSelected;

    /**
     * Getter sur searchCriteria.
     * @return Retourne le searchCriteria.
     */
    public SearchCriteria getSearchCriteria()
    {
        return this.searchCriteria;
    }

    /**
     * Méthode de récupération des beans en fonction d'un critère de recherche.
     * @return Retourne le beans.
     */
    public List<SelectableBean<BEAN>> getBeans()
    {
        return this.beans;
    }

    /**
     * Setter pour beans.
     * @param beans le beans à écrire.
     */
    public void setBeans(final List<SelectableBean<BEAN>> beans)
    {
        this.beans = beans;
    }

    /**
     * Retourne le model correspondant à la liste de beans.
     * @return DataModel
     */
    @SuppressWarnings("unchecked")
    public DataModel<BEAN> getModel()
    {
        return new SerializableListDataModel(this.getBeans());
    }

    /**
     * Getter sur beansSelected.
     * @return Retourne le beansSelected.
     */
    public List<SelectableBean<BEAN>> getBeansSelected()
    {
        final List<SelectableBean<BEAN>> beansSelected =
            new ArrayList<SelectableBean<BEAN>>(this.getBeans());

        // Retourne tous les beans qui ont la property selected à true
        CollectionUtils.filter(beansSelected,
                               new Predicate() {
                                   /**
                                    * {@inheritDoc}
                                    */
                                   @SuppressWarnings("unchecked")
                                   @Override
                                   public boolean evaluate(final Object object)
                                   {
                                       return ((SelectableBean<BEAN>) object).getSelected();
                                   }
                               });
        return beansSelected;
    }

    /**
     * Méthode en charge de gérer la redirection vers la page d'édition d'un BEAN métier <br />
     * à partir de la sélection d'une ligne dans le tableau de résultats de BEAN métiers.
     * @param viewName Nom de la vue cible (sans le .xhtml).
     * @param idName Nom de l'identifiant du BEAN métier passé en paramètre <br />
     * (var input de la vue).
     * @deprecated Supprimer les appels à cette méthode par la suite,<br>
     * car la redirection effectuée casse les flots parents.
     */
    @Deprecated
    public void redirect(final String viewName,
                         final String idName)
    {
        final SelectableBean<BEAN> selected = this.getBeanSelected();
        Long id = null;
        String urlRedirect = viewName;
        if (selected != null)
        {
            id = selected.getBean().getId();
            urlRedirect += "?"
                           + idName
                           + "="
                           + id;
        }
        this.resetBeanSelected();
        this.redirectHandler.redirect(urlRedirect);
    }

    /**
     * Méthode en charge de réinitialiser l'objet sélectionné.
     */
    public void resetBeanSelected()
    {
        this.setBeanSelected(null);
    }

    /**
     * Getter sur beanSelected.
     * @return Retourne le beanSelected.
     */
    public SelectableBean<BEAN> getBeanSelected()
    {
        return this.beanSelected;
    }

    /**
     * Setter pour beanSelected.
     * @param beanSelected le beanSelected à écrire.
     */
    public void setBeanSelected(final SelectableBean<BEAN> beanSelected)
    {
        this.beanSelected = beanSelected;
    }

    /**
     * Setter pour redirectHandler.
     * @param redirectHandler Le redirectHandler à écrire.
     */
    public void setRedirectHandler(final RedirectHandler redirectHandler)
    {
        this.redirectHandler = redirectHandler;
    }

}

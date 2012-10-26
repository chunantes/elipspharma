package fr.pharma.eclipse.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.model.DataModel;

import org.springframework.faces.model.SerializableListDataModel;

import fr.pharma.eclipse.component.helper.BeanManagerHelper;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.redirect.RedirectHandler;
import fr.pharma.eclipse.utils.EclipseDocumentProcessor;

/**
 * Manager générique sur un ensemble de BEAN.<br />
 * ATTENTION : si la même instance d'objet métier est partagée par deux<br>
 * listes de deux instances de cette classe utilisées en même temps,<br>
 * la sélection dans une liste peut impacter la deuxième !<br>
 * Pour résoudre ce problème, utiliser {@link SelectableBeansManager}.
 
 * @version $Revision$ $Date$
 * @param <BEAN> Bean Objet Métier.
 */
public class BeansManager<BEAN extends BeanObject>
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
     * Helper.
     */
    @Resource(name = "beanManagerHelper")
    private BeanManagerHelper<BEAN> helper;

    /**
     * Processor de document permettant d'enrichir le rendu de primefaces.
     */
    @Resource(name = "eclipseDocProcessor")
    private EclipseDocumentProcessor docProcessor;

    /**
     * Constructeur.
     * @param searchCriteria Critère de recherche.
     */
    public BeansManager(final SearchCriteria searchCriteria)
    {
        this.searchCriteria = searchCriteria;
    }

    /**
     * Liste des beans du tableau.
     */
    private List<BEAN> beans = new ArrayList<BEAN>();

    /**
     * Liste des beans sélectionnés.
     */
    private List<BEAN> beansSelected;

    /**
     * BEAN sélectionné.
     */
    private BEAN beanSelected;

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
    public List<BEAN> getBeans()
    {
        return this.beans;
    }

    /**
     * Setter pour beans.
     * @param beans le beans à écrire.
     */
    public void setBeans(final List<BEAN> beans)
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
    public List<BEAN> getBeansSelected()
    {
        this.beansSelected = this.helper.getBeansSelected(this.getBeans());
        return this.beansSelected;
    }

    /**
     * Méthode en charge de gérer la redirection vers la page d'édition d'un BEAN métier <br />
     * à partir de la sélection d'une ligne dans le tableau de résultats de BEAN métiers.
     * @param viewName Nom de la vue cible (sans le .xhtml).
     * @param idName Nom de l'identifiant du BEAN métier passé en paramètre <br />
     * (var input de la vue).
     * @deprecated Supprimer les appels à cette méthode par la suite,<br />
     * car la redirection effectuée casse les flots parents.
     */
    @Deprecated
    public void redirect(final String viewName,
                         final String idName)
    {
        final BEAN selected = this.getBeanSelected();
        Long id = null;
        String urlRedirect = viewName;
        if (selected != null)
        {
            id = selected.getId();
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
    public BEAN getBeanSelected()
    {
        return this.beanSelected;
    }

    /**
     * Setter pour beanSelected.
     * @param beanSelected le beanSelected à écrire.
     */
    public void setBeanSelected(final BEAN beanSelected)
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

    /**
     * Getter sur redirectHandler.
     * @return Retourne le redirectHandler.
     */
    public RedirectHandler getRedirectHandler()
    {
        return this.redirectHandler;
    }

    /**
     * Getter sur helper.
     * @return Retourne le helper.
     */
    public BeanManagerHelper<BEAN> getHelper()
    {
        return this.helper;
    }

    /**
     * Setter pour helper.
     * @param helper le helper à écrire.
     */
    public void setHelper(final BeanManagerHelper<BEAN> helper)
    {
        this.helper = helper;
    }

    /**
     * Setter pour docProcessor.
     * @param docProcessor Le docProcessor à écrire.
     */
    public void setDocProcessor(final EclipseDocumentProcessor docProcessor)
    {
        this.docProcessor = docProcessor;
    }

    /**
     * Getter pour docProcessor.
     * @return Le docProcessor
     */
    public EclipseDocumentProcessor getDocProcessor()
    {
        return this.docProcessor;
    }

}

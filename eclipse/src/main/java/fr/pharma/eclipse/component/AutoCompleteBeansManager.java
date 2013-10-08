package fr.pharma.eclipse.component;

import java.io.Serializable;
import java.util.List;

import org.springframework.util.Assert;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Manager générique pour gérer l'aute-complétion.
 * @param <BEAN> Type d'objet métier.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AutoCompleteBeansManager<BEAN extends BeanObject> implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4993622123751182909L;

    /**
     * Critère de recherche.
     */
    private SearchCriteria searchCriteria;

    /**
     * Propriété du critère de recherche à valoriser lors de la saisie du champs
     * dans l'IHM.
     */
    private String searchCriteriaProperty;

    /**
     * Service à appeler pour récupérer les objets métier répondant au critère.
     */
    private GenericService<BEAN> service;

    /**
     * Méthode à appeler pour effectuer l'auto-complétion à partir des données
     * saisies par l'utilisateur.
     * @param requete Chaîne de caractères saisie par l'utilisateur.
     * @return La liste des objets métier pour l'auto-complétion.
     */
    public List<BEAN> complete(final String requete) {
        Assert.notNull(this.searchCriteria, "Critère de recherche non valorisé.");
        Assert.notNull(this.searchCriteriaProperty, "Propriété du critère de recherche non valorisée.");
        Assert.notNull(this.service, "Service non valorisé.");

        // Valorisation du critère.
        BeanTool.setPropriete(this.searchCriteria, this.searchCriteriaProperty, requete);

        // Récupération des résultats.
        return this.service.getAll(this.searchCriteria);
    }

    /**
     * Setter pour searchCriteria.
     * @param searchCriteria le searchCriteria à écrire.
     */
    public void setSearchCriteria(final SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    /**
     * Setter pour searchCriteriaProperty.
     * @param searchCriteriaProperty le searchCriteriaProperty à écrire.
     */
    public void setSearchCriteriaProperty(final String searchCriteriaProperty) {
        this.searchCriteriaProperty = searchCriteriaProperty;
    }

    /**
     * Setter pour service.
     * @param service le service à écrire.
     */
    public void setService(final GenericService<BEAN> service) {
        this.service = service;
    }

    /**
     * Getter pour searchCriteria.
     * @return Le searchCriteria
     */
    public SearchCriteria getSearchCriteria() {
        return this.searchCriteria;
    }

}

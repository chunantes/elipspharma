package fr.pharma.eclipse.component.sir;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.DataModel;

import org.springframework.faces.model.SerializableListDataModel;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.sir.PersonneSir;

/**
 * Manager sur les beans de gestion de Personne SIR.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PersonnesSirManager implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6792335203353001173L;

    /**
     * Critère de recherche.
     */
    private final SearchCriteria searchCriteria;

    /**
     * Constructeur.
     * @param searchCriteria Critère de recherche.
     */
    public PersonnesSirManager(final SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    /**
     * Liste des personnes SIR du tableau.
     */
    private List<PersonneSir> beans = new ArrayList<PersonneSir>();

    /**
     * Personne SIR sélectionnée.
     */
    private PersonneSir beanSelected;

    /**
     * Retourne le model correspondant à la liste de beans.
     * @return DataModel
     */
    @SuppressWarnings("unchecked")
    public DataModel<PersonneSir> getModel() {
        return new SerializableListDataModel(this.getBeans());
    }

    /**
     * Getter pour beans.
     * @return Le beans
     */
    public List<PersonneSir> getBeans() {
        return this.beans;
    }

    /**
     * Setter pour beans.
     * @param beans Le beans à écrire.
     */
    public void setBeans(final List<PersonneSir> beans) {
        this.beans = beans;
    }

    /**
     * Getter pour searchCriteria.
     * @return Le searchCriteria
     */
    public SearchCriteria getSearchCriteria() {
        return this.searchCriteria;
    }

    /**
     * Getter pour beanSelected.
     * @return Le beanSelected
     */
    public PersonneSir getBeanSelected() {
        return this.beanSelected;
    }

    /**
     * Setter pour beanSelected.
     * @param beanSelected Le beanSelected à écrire.
     */
    public void setBeanSelected(final PersonneSir beanSelected) {
        this.beanSelected = beanSelected;
    }

}

package fr.pharma.eclipse.component.prescription;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.dto.PrescriptionDTO;
import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.exception.ValidationException;

/**
 * Manager de prescriptions.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PrescriptionDTOManager implements Serializable {
    /**
     * Critère de recherche.
     */
    private final SearchCriteria searchCriteria;

    /**
     * Liste des beans du tableau.
     */
    private List<PrescriptionDTO> beans = new ArrayList<PrescriptionDTO>();

    /**
     * BEAN sélectionné.
     */
    private PrescriptionDTO beanSelected;

    /**
     * Méthode en charge de vérifier que la prescription courante est
     * dispensable.
     */
    public void validForDispensation() {
        if (this.getBeanSelected().getTypeDispensation() == null) {
            this.setBeanSelected(null);
            throw new ValidationException("prescription.typeDispensation", new String[]{"notEmpty" });
        }
        if (!this.getBeanSelected().getEtatEssai().equals(EtatEssai.EN_COURS)) {
            this.setBeanSelected(null);
            throw new ValidationException("prescription.etatEssai", new String[]{"notValid" });
        }

        if (!this.getBeanSelected().getInclusionActive()) {
            throw new ValidationException("prescription.inclusion.patient", new String[]{"notEmpty" });
        }
    }

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 7618116404545225666L;

    /**
     * Constructeur.
     * @param searchCriteria Critère de recherche.
     */
    public PrescriptionDTOManager(final SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    /**
     * Getter pour beans.
     * @return Le beans
     */
    public List<PrescriptionDTO> getBeans() {
        return this.beans;
    }

    /**
     * Setter pour beans.
     * @param beans Le beans à écrire.
     */
    public void setBeans(final List<PrescriptionDTO> beans) {
        this.beans = beans;
    }

    /**
     * Getter pour beanSelected.
     * @return Le beanSelected
     */
    public PrescriptionDTO getBeanSelected() {
        return this.beanSelected;
    }

    /**
     * Setter pour beanSelected.
     * @param beanSelected Le beanSelected à écrire.
     */
    public void setBeanSelected(final PrescriptionDTO beanSelected) {
        this.beanSelected = beanSelected;
    }

    /**
     * Getter pour searchCriteria.
     * @return Le searchCriteria
     */
    public SearchCriteria getSearchCriteria() {
        return this.searchCriteria;
    }

}

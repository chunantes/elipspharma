package fr.pharma.eclipse.component.dispensation;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.dto.DispensationDTO;
import fr.pharma.eclipse.utils.EclipseDocumentProcessor;

/**
 * Manager de dispensations.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationDTOManager implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5619248899412352536L;

    /**
     * Critère de recherche.
     */
    private final SearchCriteria searchCriteria;

    /**
     * Liste des beans du tableau.
     */
    private List<DispensationDTO> beans = new ArrayList<DispensationDTO>();

    /**
     * BEAN sélectionné.
     */
    private DispensationDTO beanSelected;

    /**
     * Processor de document permettant d'enrichir le rendu de primefaces.
     */
    @Resource(name = "eclipseDocProcessor")
    private EclipseDocumentProcessor docProcessor;

    /**
     * Constructeur.
     * @param searchCriteria Critère de recherche.
     */
    public DispensationDTOManager(final SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    /**
     * Applique un pre processor avant le génération des documents dataExporter.
     * @param document Document.
     * @throws IOException en cas d'erreur.
     * @throws BadElementException en cas d'erreur .
     * @throws DocumentException en cas d'erreur.
     */
    public void preProcessPDF(final Object document) throws IOException, BadElementException, DocumentException {
        this.docProcessor.preProcessPDF(document, "Liste des dispensations");
    }

    /**
     * Getter pour searchCriteria.
     * @return Le searchCriteria
     */
    public SearchCriteria getSearchCriteria() {
        return this.searchCriteria;
    }

    /**
     * Getter pour beans.
     * @return Le beans
     */
    public List<DispensationDTO> getBeans() {
        return this.beans;
    }

    /**
     * Setter pour beans.
     * @param beans Le beans à écrire.
     */
    public void setBeans(final List<DispensationDTO> beans) {
        this.beans = beans;
    }

    /**
     * Getter pour beanSelected.
     * @return Le beanSelected
     */
    public DispensationDTO getBeanSelected() {
        return this.beanSelected;
    }

    /**
     * Setter pour beanSelected.
     * @param beanSelected Le beanSelected à écrire.
     */
    public void setBeanSelected(final DispensationDTO beanSelected) {
        this.beanSelected = beanSelected;
    }

}

package fr.pharma.eclipse.component.patient;

import java.io.IOException;

import javax.annotation.Resource;

import org.primefaces.event.SelectEvent;

import com.lowagie.text.BadElementException;
import com.lowagie.text.DocumentException;

import fr.pharma.eclipse.component.BeanListManager;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.patient.PatientSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.service.patient.PatientService;
import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Manager des liste de patients.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PatientListManager extends BeanListManager<Patient> {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -603628221685155672L;

    /**
     * Roles ne voyant pas tous les patients à l'arrivée sur la page.
     */
    private final String[] roles = {"ROLE_ARC_INVESTIGATEUR", "ROLE_ARC_PROMOTEUR" };

    /**
     * Service patient.
     */
    @Resource(name = "patientService")
    private PatientService service;

    /**
     * Constructeur.
     * @param searchCriteria Critere de recherche.
     */
    public PatientListManager(final SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

    /**
     * Méthode appelé lorsqu'un essai est sélectionné sur l'écran de
     * prescription. Il ajoute au criteria l'essai.
     * @param event Evenement JSF.
     */
    public void handleSelectEssai(final SelectEvent event) {
        final Essai essai = (Essai) event.getObject();
        ((PatientSearchCriteria) this.getSearchCriteria()).setEssai(essai);
    }

    /**
     * Applique un pre processor avant le génération des documents dataExporter.
     * @param document Document.
     * @throws IOException en cas d'erreur.
     * @throws BadElementException en cas d'erreur .
     * @throws DocumentException en cas d'erreur.
     */
    public void preProcessPDF(final Object document) throws IOException, BadElementException, DocumentException {
        this.getDocProcessor().preProcessPDF(document, "Liste des patients");
    }

    /**
     * Pour l'export : retourne le numero d'inclusion et la date d'inclusion du
     * patient en paramètre.
     * @param patient Le patient.
     * @return La date et le numero d'inclusion.
     */
    public String getNumDateInclusion(final Patient patient) {
        final Patient patientReattached = this.service.reattach(patient);
        final Inclusion inclusion = this.service.getInclusionCourante(patientReattached);
        if (inclusion != null) {
            return inclusion.getNumInclusion() + " - " + Utils.formatDate(inclusion.getDateInclusion().getTime(), EclipseConstants.PATTERN_SIMPLE);
        } else {
            return EclipseConstants.NON_APPLICABLE;
        }

    }

    /**
     * Retourne La date d'inclusion du patient en paramètre.
     * @param patient Le patient.
     * @return La date et le numero d'inclusion.
     */
    public String getDateInclusion(final Patient patient) {
        final Patient patientReattached = this.service.reattach(patient);
        final Inclusion inclusion = this.service.getInclusionCourante(patientReattached);
        if (inclusion != null) {
            return Utils.formatDate(inclusion.getDateInclusion().getTime(), EclipseConstants.PATTERN_SIMPLE);
        } else {
            return EclipseConstants.NON_APPLICABLE;
        }

    }

    /**
     * Retourne l'essai dans lequel est inclu le patient paramètre. N/A sinon.
     * @param patient Le patient.
     * @return L'essai dans lequel est inclu le patient ou n/a.
     */
    public String getEssai(final Patient patient) {
        final Patient patientReattached = this.service.reattach(patient);
        final Inclusion inclusion = this.service.getInclusionCourante(patientReattached);
        if (inclusion != null) {
            return inclusion.getEssai().getPromoteur().getRaisonSociale() + " - " + inclusion.getEssai().getCodePromoteur() + " - " + inclusion.getEssai().getNom();
        } else {
            return EclipseConstants.NON_APPLICABLE;
        }
    }

    /**
     * Setter pour service.
     * @param service Le service à écrire.
     */
    public void setService(final PatientService service) {
        this.service = service;
    }

    /**
     * Getter pour roles.
     * @return Le roles
     */
    public String[] getRoles() {
        return this.roles;
    }
}

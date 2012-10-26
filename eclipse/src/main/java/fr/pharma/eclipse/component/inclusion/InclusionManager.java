package fr.pharma.eclipse.component.inclusion;

import java.io.Serializable;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;

import org.primefaces.event.SelectEvent;

import fr.pharma.eclipse.component.BeanManager;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.patient.PatientService;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Manager de Inclusion.
 
 * @version $Revision$ $Date$
 */
public class InclusionManager
    extends BeanManager<Inclusion>
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4663265156154298604L;

    /**
     * Essai sélectionné.
     */
    private Essai essaiSelected;

    /**
     * Patient sélectionné.
     */
    private Patient patientSelected;

    /**
     * FacesUtils.
     */
    @Resource(name = "facesUtils")
    private FacesUtils facesUtils;

    /**
     * Service patient.
     */
    @Resource(name = "patientService")
    private PatientService patientService;

    /**
     * Flag permettant de savoir si les données peuvent être validées.
     */
    private Boolean valid;

    /**
     * Constructeur.
     * @param service Service.
     */
    public InclusionManager(final GenericService<Inclusion> service)
    {
        super(service);
    }

    /**
     * Méthode d'initialisation.
     */
    public void init()
    {
        this.setEssaiSelected(null);
        this.setPatientSelected(null);
        this.setValid(false);
    }

    /**
     * Méthode en charge de vérifier que le patient n'est pas déjà inclu dans un essai
     * actuellement. Sinon il affiche une erreur.
     * @param event Evenement JSF.
     */
    public void handleSelectPatient(final SelectEvent event)
    {
        if (null != this.patientService.getInclusionCourante((Patient) event.getObject()))
        {
            this.valid = false;
            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR,
                                       "patient.inclu.error");
        }
        else
        {
            this.valid = true;
        }
    }

    public void handleSelectNewPatient(final Patient patient)
    {
        if (null != patient
            && null != this.patientService.getInclusionCourante(patient))
        {
            this.valid = false;
            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR,
                                       "patient.inclu.error");
        }
        else
        {
            this.valid = true;
        }
    }

    /**
     * Méthode en charge d'afficher un message de confirmation d'inclusion.
     */
    public void confirmInclusion()
    {
        this.facesUtils.addMessage(FacesMessage.SEVERITY_INFO,
                                   "patient.inclu.ok");
        this.init();
    }

    /**
     * Getter pour essaiSelected.
     * @return Le essaiSelected
     */
    public Essai getEssaiSelected()
    {
        return this.essaiSelected;
    }

    /**
     * Setter pour essaiSelected.
     * @param essaiSelected Le essaiSelected à écrire.
     */
    public void setEssaiSelected(final Essai essaiSelected)
    {
        this.essaiSelected = essaiSelected;
    }

    /**
     * Getter sur patientSelected.
     * @return Retourne le patientSelected.
     */
    public Patient getPatientSelected()
    {
        return this.patientSelected;
    }

    /**
     * Setter pour patientSelected.
     * @param patientSelected le patientSelected à écrire.
     */
    public void setPatientSelected(final Patient patientSelected)
    {
        this.patientSelected = patientSelected;
    }

    /**
     * Setter pour patientService.
     * @param patientService le patientService à écrire.
     */
    public void setPatientService(final PatientService patientService)
    {
        this.patientService = patientService;
    }

    /**
     * Setter pour facesUtils.
     * @param facesUtils le facesUtils à écrire.
     */
    public void setFacesUtils(final FacesUtils facesUtils)
    {
        this.facesUtils = facesUtils;
    }

    /**
     * Getter sur valid.
     * @return Retourne le valid.
     */
    public Boolean getValid()
    {
        return this.valid;
    }

    /**
     * Setter pour valid.
     * @param valid le valid à écrire.
     */
    public void setValid(final Boolean valid)
    {
        this.valid = valid;
    }

}

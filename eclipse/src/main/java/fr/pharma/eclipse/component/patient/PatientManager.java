package fr.pharma.eclipse.component.patient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.TabChangeEvent;

import fr.pharma.eclipse.component.BeanManager;
import fr.pharma.eclipse.domain.enums.patient.FormuleSurfaceCorporelle;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.patient.HistoriquePatient;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.factory.patient.HistoriquePatientFactory;
import fr.pharma.eclipse.service.patient.PatientService;
import fr.pharma.eclipse.utils.FacesUtils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Manager de Patient.
 
 * @version $Revision$ $Date$
 */
public class PatientManager
    extends BeanManager<Patient>
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3145494941459001312L;

    @Resource(name = "facesUtils")
    private FacesUtils facesUtils;

    /**
     * Index de l'onglet courant.
     */
    private int indexOngletCourant;

    /**
     * Historique patient.
     */
    private HistoriquePatient historique;

    /**
     * Factory historiquePatient.
     */
    @Resource(name = "historiquePatientFactory")
    private HistoriquePatientFactory factory;

    /**
     * Flag d'edition d'un historique.
     */
    private boolean editHistorique;

    /**
     * Formule de calcul de la surface corporelle utilisée.
     */
    private String formuleUtilisee;

    /**
     * Inclusion courante.
     */
    private Inclusion inclusionCourante;

    /**
     * Prescriptions.
     */
    private List<Prescription> prescriptions = new ArrayList<Prescription>();

    /**
     * Dispensation.
     */
    private List<Dispensation> dispensations = new ArrayList<Dispensation>();

    /**
     * Map de description des onglets de Patient.
     */
    protected static final Map<String, Integer> INFOS_ONGLETS = new HashMap<String, Integer>();
    {
        PatientManager.INFOS_ONGLETS.put("ONG_ESSAI",
                                         0);
        PatientManager.INFOS_ONGLETS.put("ONG_PRESCRIPTION",
                                         1);
        PatientManager.INFOS_ONGLETS.put("ONG_DISPENSATION",
                                         2);
        PatientManager.INFOS_ONGLETS.put("ONG_RETOUR",
                                         3);
    };

    /**
     * Constructeur.
     * @param service Service de gestion de patient.
     */
    public PatientManager(final PatientService service)
    {
        super(service);
    }

    /**
     * Listener appelé lorsque l'utilisateur change d'onglet.
     * @param event Evénement remonté par le composant primeFaces.
     */
    public void onOngletChange(final TabChangeEvent event)
    {
        final String tabId = event.getTab().getId();
        this.setIndexOngletCourant(PatientManager.INFOS_ONGLETS.get(tabId));
    }

    /**
     * Méthode en charge d'intiialiser l'historique.
     */
    public void initHistorique()
    {
        this.historique = this.factory.getInitializedObject(this.getBean());
        this.editHistorique = true;
    }

    /**
     * Ajout de l'historique au patient courant.
     */
    public void addHistorique()
    {
        final Calendar courant = Calendar.getInstance(EclipseConstants.LOCALE);
        this.historique.getDate().set(Calendar.MINUTE,
                                      courant.get(Calendar.MINUTE));
        this.historique.getDate().set(Calendar.SECOND,
                                      courant.get(Calendar.SECOND));
        this.historique.getDate().set(Calendar.HOUR_OF_DAY,
                                      courant.get(Calendar.HOUR_OF_DAY));
        this.getBean().getHistoriquePatient().add(this.historique);
        this.reinit();
    }

    /**
     * Méthode en charge de réinitialiser les élément du manager.
     */
    public void reinit()
    {
        this.historique = null;
        this.editHistorique = false;
        this.inclusionCourante = null;
        this.dispensations.clear();
        this.indexOngletCourant = 0;
        this.prescriptions.clear();
        this.setFormuleUtilisee("");
    }

    /**
     * Méthode en charge de mettre à jour la surface corporelle de l'objet HistoriquePatient.
     * @param event Evenement JSF.
     */
    public void updateSurfaceCorporelle()
    {
        if (this.getHistorique().getTaille() != null
            && this.getHistorique().getPoid() != null
            && this.getHistorique().getPatient().getDateNaissance() != null)
        {
            final FormuleSurfaceCorporelle formule =
                ((PatientService) this.getService()).updateSurfaceCorporelle(this.historique);
            if (formule != null)
            {
                this.setFormuleUtilisee(formule.getLibelle());
            }
        }
        else
        {
            this.facesUtils
                    .addMessage(FacesMessage.SEVERITY_ERROR,
                                "gestionPatient.surface.taille.poids.et.date.naissance.requis");
            this.setFormuleUtilisee("");
            this.getHistorique().setSurfaceCorporelle(null);
        }
    }

    /**
     * Méthode en charge de construire les initiales en fonction du patient.
     * @return Les initiales.
     */
    private String getMakeInitiales()
    {
        String result = "";
        if (StringUtils.isNotBlank(StringUtils.strip(this.getBean().getNom()))
            && StringUtils.isNotBlank(StringUtils.strip(this.getBean().getPrenom())))
        {
            result += StringUtils.upperCase(StringUtils.substring(this.getBean().getNom(),
                                                                  0,
                                                                  3));
            result += "-";
            result += StringUtils.upperCase(StringUtils.substring(this.getBean().getPrenom(),
                                                                  0,
                                                                  2));
        }
        return result;
    }
    /**
     * Méthode en charge de mettre à jour les initiales de l'objet Patient.
     * @param event Evenement JSF.
     */
    public void updateInitiales(final ValueChangeEvent event)
    {
        if (event.getComponent().getId().equals("nom"))
        {
            this.getBean().setNom((String) event.getNewValue());
        }
        else if (event.getComponent().getId().equals("prenom"))
        {
            this.getBean().setPrenom((String) event.getNewValue());
        }
        this.getBean().setInitiales(this.getMakeInitiales());
    }

    /**
     * Getter pour indexOngletCourant.
     * @return Le indexOngletCourant
     */
    public int getIndexOngletCourant()
    {
        return this.indexOngletCourant;
    }

    /**
     * Setter pour indexOngletCourant.
     * @param indexOngletCourant Le indexOngletCourant à écrire.
     */
    public void setIndexOngletCourant(final int indexOngletCourant)
    {
        this.indexOngletCourant = indexOngletCourant;
    }

    /**
     * Getter sur historique.
     * @return Retourne le historique.
     */
    public HistoriquePatient getHistorique()
    {
        return this.historique;
    }

    /**
     * Setter pour historique.
     * @param historique le historique à écrire.
     */
    public void setHistorique(final HistoriquePatient historique)
    {
        this.historique = historique;
    }

    /**
     * Setter pour factory.
     * @param factory le factory à écrire.
     */
    public void setFactory(final HistoriquePatientFactory factory)
    {
        this.factory = factory;
    }

    /**
     * Getter sur editHistorique.
     * @return Retourne le editHistorique.
     */
    public boolean isEditHistorique()
    {
        return this.editHistorique;
    }

    /**
     * Setter pour editHistorique.
     * @param editHistorique le editHistorique à écrire.
     */
    public void setEditHistorique(final boolean editHistorique)
    {
        this.editHistorique = editHistorique;
    }

    /**
     * Getter sur formuleUtilisee.
     * @return Retourne le formuleUtilisee.
     */
    public String getFormuleUtilisee()
    {
        return this.formuleUtilisee;
    }

    /**
     * Setter pour formuleUtilisee.
     * @param formuleUtilisee le formuleUtilisee à écrire.
     */
    public void setFormuleUtilisee(final String formuleUtilisee)
    {
        this.formuleUtilisee = formuleUtilisee;
    }

    /**
     * Getter sur inclusionCourante.
     * @return Retourne le inclusionCourante.
     */
    public Inclusion getInclusionCourante()
    {
        return this.inclusionCourante;
    }

    /**
     * Setter pour inclusionCourante.
     * @param inclusionCourante le inclusionCourante à écrire.
     */
    public void setInclusionCourante(final Inclusion inclusionCourante)
    {
        this.inclusionCourante = inclusionCourante;
    }

    /**
     * Getter sur prescriptions.
     * @return Retourne le prescriptions.
     */
    public List<Prescription> getPrescriptions()
    {
        return this.prescriptions;
    }

    /**
     * Setter pour prescriptions.
     * @param prescriptions le prescriptions à écrire.
     */
    public void setPrescriptions(final List<Prescription> prescriptions)
    {
        this.prescriptions = prescriptions;
    }

    /**
     * Getter sur dispensations.
     * @return Retourne le dispensations.
     */
    public List<Dispensation> getDispensations()
    {
        return this.dispensations;
    }

    /**
     * Setter pour dispensations.
     * @param dispensations le dispensations à écrire.
     */
    public void setDispensations(final List<Dispensation> dispensations)
    {
        this.dispensations = dispensations;
    }

    /**
     * Getter pour facesUtils.
     * @return Le facesUtils
     */
    public FacesUtils getFacesUtils()
    {
        return this.facesUtils;
    }

    /**
     * Setter pour facesUtils.
     * @param facesUtils Le facesUtils à écrire.
     */
    public void setFacesUtils(final FacesUtils facesUtils)
    {
        this.facesUtils = facesUtils;
    }

}

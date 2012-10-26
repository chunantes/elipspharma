package fr.pharma.eclipse.component.prescription;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.TreeNode;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.component.BeanManager;
import fr.pharma.eclipse.component.design.helper.TreeDesignHelper;
import fr.pharma.eclipse.component.prescription.helper.PrescriptionManagerHelper;
import fr.pharma.eclipse.domain.criteria.prescription.PrescriptionSearchCriteria;
import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.enums.TypeDispensation;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.design.Designable;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.factory.prescription.PrescriptionFactory;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.habilitation.helper.HabilitationsHelper;
import fr.pharma.eclipse.service.patient.PatientService;
import fr.pharma.eclipse.transformer.design.GenericTransformer;
import fr.pharma.eclipse.utils.FacesUtils;
import fr.pharma.eclipse.validator.prescription.ProduitPrescritValidator;

/**
 * Manager de Prescription.
 
 * @version $Revision$ $Date$
 */
public class PrescriptionManager
    extends BeanManager<Prescription>
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
     * Flag d'édition d'une prescription.
     */
    private Boolean editPrescription;

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
     * Factory de prescription.
     */
    @Resource(name = "prescriptionFactory")
    private PrescriptionFactory factory;

    /**
     * Helper gérant les habilitations.
     */
    @Resource(name = "habilitationsHelper")
    private HabilitationsHelper habilitationHelper;

    /**
     * Arbre des designables sélectionnables pour le design.
     */
    private TreeNode designablesSelectable;

    /**
     * Service essai.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * Noeud sélectionné.
     */
    private TreeNode nodeSelected;

    /**
     * Classe de helper de construction d'arbre de stockage.
     */
    @Resource(name = "treeDesignHelper")
    private TreeDesignHelper treeDesignHelper;

    /**
     * Liste de produits.
     */
    private SortedSet<Produit> produits = new TreeSet<Produit>(new BeanWithNomComparator());

    /**
     * Helper du PrescriptionManager.
     */
    @Resource(name = "prescriptionManagerHelper")
    private PrescriptionManagerHelper prescriptionManagerHelper;

    /**
     * Flag déterminant si les données saisies permettent de saisir une prescription. (patient
     * inclu dans un essai).
     */
    private Boolean valid;

    /**
     * ReadOnly : consultation via patient.
     */
    private Boolean readOnly;

    /**
     * Validator de l'ajout d'un produit prescrit à la prescription.
     */
    @Resource(name = "produitPrescritValidator")
    private ProduitPrescritValidator validator;

    /**
     * Service.
     */
    private Collection<Service> services = new ArrayList<Service>();

    /**
     * Constructeur.
     * @param service Service.
     */
    public PrescriptionManager(final GenericService<Prescription> service)
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
        this.setReadOnly(false);
        this.setBean(null);
        this.services = new ArrayList<Service>();
    }

    /**
     * Méthode en charge de vérifier que le patient n'est pas déjà inclu dans un essai
     * actuellement. Sinon il affiche une erreur.
     * @param event Evenement JSF.
     */
    public void handleSelectPatient(final SelectEvent event)
    {
        final Inclusion inclusion =
            this.patientService.getInclusionCourante((Patient) event.getObject());
        this.handleInclusion(inclusion);
    }

    public void handleInclusion(final Inclusion inclusion)
    {
        if (null == inclusion)
        {
            this.valid = false;
            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR,
                                       "patient.non.inclu.error");
        }
        else if (this.getInvestigateur(inclusion.getEssai()).size() == 0)
        {
            this.valid = false;
            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR,
                                       "investigateur.non.present.error");
        }
        else
        {
            final TypeDispensation typeDispensation =
                inclusion
                        .getEssai()
                        .getDetailDonneesPharma()
                        .getInfosDispensations()
                        .getTypeDispensation();
            if (typeDispensation != null)
            {
                // recherche des prescriptions
                final PrescriptionSearchCriteria crit = new PrescriptionSearchCriteria();
                crit.setInclusion(inclusion);
                crit.setActiveOrder("numPrescription");
                crit.setAscending(false);

                final List<Prescription> liste = this.getService().getAll(crit);
                if (liste.isEmpty())
                {
                    this.setBean(this.factory.getInitializedObject(inclusion));
                }
                else
                {
                    this.setBean(this.factory.getInitializedObject(liste.get(0)));
                }
                this.produits.addAll(inclusion.getEssai().getDetailProduit().getProduits());
                this.services.addAll(inclusion.getEssai().getServices());
                this.valid = true;
            }
            else
            {
                this.valid = false;
                this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR,
                                           "essai.dispensation.null.error");
            }
        }
    }

    /**
     * Initialisation des services.
     */
    public void initServices()
    {
        this.services.addAll(this.getBean().getEssai().getServices());
    }

    /**
     * Méthode en charge de récupérer la liste des investigateurs liés à l'essai de la
     * prescription.
     * @return a liste des investigateurs liés à un essai.
     */
    @SuppressWarnings("unchecked")
    public Collection<Personne> getInvestigateurs()
    {
        return this.getInvestigateur(this.getBean().getInclusion().getEssai());
    }
    /**
     * Méthode en charge de retrouner les investigateurs pour l'essai en paramètre.
     * @param essai L'essai
     * @return La collection d'investigateur.
     */
    private Collection<Personne> getInvestigateur(final Essai essai)
    {
        final List<Droit> droits = new ArrayList<Droit>();
        droits.add(Droit.INVESTIGATEUR_CO);
        droits.add(Droit.INVESTIGATEUR_PRINCIPAL);

        final Collection habilitations =
            new ArrayList(this.habilitationHelper.getHabilitations(essai,
                                                                   droits,
                                                                   true));

        CollectionUtils.transform(habilitations,
                                  new GenericTransformer("personne"));

        return habilitations;
    }

    /**
     * Méthode en charge de mettre à jour le design.
     */
    public void updateDesign()
    {
        if (this.getNodeSelected() != null)
        {
            final Designable designable = (Designable) this.getNodeSelected().getData();

            // si ce n'est pas une sequence alors on affiche une erreur.
            if (designable instanceof Sequence)
            {
                this.getBean().setSequence((Sequence) designable);
                this.prescriptionManagerHelper.initProduitsPrescrits(this.getBean());
            }
            else
            {
                this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR,
                                           "prescription.designable.sequence");
                this.nodeSelected = null;
            }
        }
    }

    /**
     * Getter pour designablesSelectable.
     * @return Le designablesSelectable.
     */
    public TreeNode getDesignablesSelectable()
    {
        // Récupération de la pharmacie sélectionnée
        if (this.getBean() != null
            && this.getBean().getInclusion() != null
            && this.getBean().getInclusion().getEssai() != null)
        {
            this.designablesSelectable =
                this.treeDesignHelper.buildTree(this.essaiService.get(this
                        .getBean()
                        .getInclusion()
                        .getEssai()
                        .getId()));
            return this.designablesSelectable;
        }
        else
        {
            return null;
        }
    }

    /**
     * Méthode en charge de supprimer un ProduitPrescrit.
     * @param event Evénement.
     */
    public void delProduitPrescrit(final ActionEvent event)
    {
        final ProduitPrescrit produit =
            (ProduitPrescrit) event.getComponent().getAttributes().get("produitToDelete");
        this.getBean().getProduitsPrescrits().remove(produit);
    }

    /**
     * Méthode en charge d'afficher un message de confirmation.
     */
    public void confirm()
    {
        this.facesUtils.addMessage(FacesMessage.SEVERITY_INFO,
                                   "prescription.ok");
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

    /**
     * Setter pour facesUtils.
     * @param facesUtils le facesUtils à écrire.
     */
    public void setFacesUtils(final FacesUtils facesUtils)
    {
        this.facesUtils = facesUtils;
    }

    /**
     * Setter pour factory.
     * @param factory le factory à écrire.
     */
    public void setFactory(final PrescriptionFactory factory)
    {
        this.factory = factory;
    }

    /**
     * Setter pour habilitationHelper.
     * @param habilitationHelper le habilitationHelper à écrire.
     */
    public void setHabilitationHelper(final HabilitationsHelper habilitationHelper)
    {
        this.habilitationHelper = habilitationHelper;
    }

    /**
     * Getter sur nodeSelected.
     * @return Retourne le nodeSelected.
     */
    public TreeNode getNodeSelected()
    {
        return this.nodeSelected;
    }

    /**
     * Setter pour nodeSelected.
     * @param nodeSelected le nodeSelected à écrire.
     */
    public void setNodeSelected(final TreeNode nodeSelected)
    {
        this.nodeSelected = nodeSelected;
    }

    /**
     * Setter pour treeDesignHelper.
     * @param treeDesignHelper le treeDesignHelper à écrire.
     */
    public void setTreeDesignHelper(final TreeDesignHelper treeDesignHelper)
    {
        this.treeDesignHelper = treeDesignHelper;
    }

    /**
     * Setter pour prescriptionManagerHelper.
     * @param prescriptionManagerHelper le prescriptionManagerHelper à écrire.
     */
    public void setPrescriptionManagerHelper(final PrescriptionManagerHelper prescriptionManagerHelper)
    {
        this.prescriptionManagerHelper = prescriptionManagerHelper;
    }

    /**
     * Getter sur produits.
     * @return Retourne le produits.
     */
    public SortedSet<Produit> getProduits()
    {
        return this.produits;
    }

    /**
     * Setter pour produits.
     * @param produits le produits à écrire.
     */
    public void setProduits(final SortedSet<Produit> produits)
    {
        this.produits = produits;
    }

    /**
     * Getter sur readOnly.
     * @return Retourne le readOnly.
     */
    public Boolean getReadOnly()
    {
        return this.readOnly;
    }

    /**
     * Setter pour readOnly.
     * @param readOnly le readOnly à écrire.
     */
    public void setReadOnly(final Boolean readOnly)
    {
        this.readOnly = readOnly;
    }

    /**
     * Setter pour validator.
     * @param validator le validator à écrire.
     */
    public void setValidator(final ProduitPrescritValidator validator)
    {
        this.validator = validator;
    }

    /**
     * Setter pour essaiService.
     * @param essaiService le essaiService à écrire.
     */
    public void setEssaiService(final EssaiService essaiService)
    {
        this.essaiService = essaiService;
    }

    /**
     * Getter pour services.
     * @return Le services
     */
    public Collection<Service> getServices()
    {
        return this.services;
    }

    /**
     * Setter pour services.
     * @param services Le services à écrire.
     */
    public void setServices(final Collection<Service> services)
    {
        this.services = services;
    }

}

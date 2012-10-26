package fr.pharma.eclipse.component.prescription;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import fr.pharma.eclipse.component.BeanManager;
import fr.pharma.eclipse.component.prescription.helper.ProduitPrescritHelper;
import fr.pharma.eclipse.component.produit.helper.ConditionnementHelper;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.factory.prescription.ProduitPrescritFactory;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Manager de la classe métier ProduitPrescrit.
 
 * @version $Revision$ $Date$
 */
public class ProduitPrescritManager
    extends BeanManager<ProduitPrescrit>
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -4956671275459412049L;

    /**
     * Factory de produitPrescrit.
     */
    @Resource(name = "produitPrescritFactory")
    private ProduitPrescritFactory produitPrescritFactory;

    /**
     * Helper de conditionnement.
     */
    @Resource(name = "conditionnementHelper")
    private ConditionnementHelper conditionnementHelper;

    /**
     * Helper de prescription.
     */
    @Resource(name = "produitPrescritHelper")
    private ProduitPrescritHelper produitPrescritHelper;

    /**
     * Résumé de la prescription du produit.
     */
    private String resume;

    /**
     * Résumé du conditionnement.
     */
    private String resumeConditionnement;

    /**
     * ACtion.
     */
    private String action;

    /**
     * FacesUtils.
     */
    @Resource(name = "facesUtils")
    private FacesUtils facesUtils;

    /**
     * Constructeur.
     * @param service Service.
     */
    public ProduitPrescritManager(final GenericService<ProduitPrescrit> service)
    {
        super(service);
    }

    /**
     * Methode d'initialisation du manager.
     */
    public void init()
    {
        this.setBean(null);
        this.action = "";
        this.setResume(null);
        this.setResumeConditionnement(null);
    }

    /**
     * Méthode en charge d'ajouter un nouveau produit prescrit.
     * @param prescription La prescription.
     */
    public void createProduitPrescrit(final Prescription prescription)
    {
        this.setBean(this.produitPrescritFactory.getInitializedObject(prescription));
        this.action = "ADD";
    }
    /**
     * Méthode en charge d'initialiser le bean.
     */
    public void initProduitPrescrit()
    {
        this.setBean(this.produitPrescritFactory.getInitializedObject());
        this.action = "ADD";
    }

    /**
     * Methode en charge d'intialiser le bean avec le produit contenu dans l'evenement.
     * @param event Evenement.
     */
    public void editProduitPrescrit(final ActionEvent event)
    {
        final ProduitPrescrit produit =
            (ProduitPrescrit) event.getComponent().getAttributes().get("produitCurrent");
        this.setBean(produit);
        this.buildResume();
        this.setResumeConditionnement(this.conditionnementHelper.buildResume(produit
                .getConditionnement()));
        this.action = "EDIT";

    }

    /**
     * Méthode en charge de construire le résumé de la prescription du produit.
     */
    public void buildResume()
    {
        this.setResume(this.produitPrescritHelper.buildResume(this.getBean()));
    }

    /**
     * Méthode en charge d'ajouter un produitPrescrit à la prescription courante.
     * @param prod Le produit prescrit à ajouter.
     */
    public void addProduitPrescrit(final Prescription prescription)
    {
        if (this.action.equals("ADD"))
        {
            this.getBean().setPrescription(prescription);
            try
            {
                if (!prescription.getProduitsPrescrits().contains(this.getBean()))
                {
                    prescription.getProduitsPrescrits().add(this.getBean());
                }
                else
                {

                    this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR,
                                               "dispensation.produitPrescrit.exist");
                }
            }
            catch (final ValidationException e)
            {
                this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR,
                                           e.getErrorCode()
                                                   + e.getValues()[0]);
            }
        }
    }

    /**
     * Retourne <true> si le mode de prescription du conditionnement sélectionné est du type de
     * celui en paramètre.
     * @param mode Mode de prescription.
     * @return <true> si le mode de prescription du conditionnement sélectionné est du type de
     * celui en paramètre.
     */
    public boolean isModePrescription(final ModePrescription mode)
    {
        return this.getBean() != null
               && this.getBean().getConditionnement() != null
               && this.getBean().getConditionnement().getModePrescription() != null
               && this.getBean().getConditionnement().getModePrescription().equals(mode);
    }

    /**
     * Méthode appelée par l'IHM lors de la sélection d'un conditionnement.
     * @param event Evenement JSF.
     */
    public void handleConditionnement(final AjaxBehaviorEvent event)
    {
        final HtmlSelectOneMenu menu = (HtmlSelectOneMenu) event.getSource();
        final Conditionnement conditionnement = (Conditionnement) menu.getLocalValue();
        this.getBean().setConditionnement(conditionnement);
        this.setResumeConditionnement(this.conditionnementHelper.buildResume(conditionnement));
    }

    /**
     * Setter pour produitPrescritFactory.
     * @param produitPrescritFactory le produitPrescritFactory à écrire.
     */
    public void setProduitPrescritFactory(final ProduitPrescritFactory produitPrescritFactory)
    {
        this.produitPrescritFactory = produitPrescritFactory;
    }

    /**
     * Getter sur resume.
     * @return Retourne le resume.
     */
    public String getResume()
    {
        return this.resume;
    }

    /**
     * Getter sur resumeConditionnement.
     * @return Retourne le resumeConditionnement.
     */
    public String getResumeConditionnement()
    {
        return this.resumeConditionnement;
    }

    /**
     * Setter pour resume.
     * @param resume le resume à écrire.
     */
    public void setResume(final String resume)
    {
        this.resume = resume;
    }

    /**
     * Setter pour resumeConditionnement.
     * @param resumeConditionnement le resumeConditionnement à écrire.
     */
    public void setResumeConditionnement(final String resumeConditionnement)
    {
        this.resumeConditionnement = resumeConditionnement;
    }

    /**
     * Setter pour conditionnementHelper.
     * @param conditionnementHelper le conditionnementHelper à écrire.
     */
    public void setConditionnementHelper(final ConditionnementHelper conditionnementHelper)
    {
        this.conditionnementHelper = conditionnementHelper;
    }

    /**
     * Setter pour produitPrescritHelper.
     * @param produitPrescritHelper le produitPrescritHelper à écrire.
     */
    public void setProduitPrescritHelper(final ProduitPrescritHelper produitPrescritHelper)
    {
        this.produitPrescritHelper = produitPrescritHelper;
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

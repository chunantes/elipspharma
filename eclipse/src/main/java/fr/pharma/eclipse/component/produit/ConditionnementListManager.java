package fr.pharma.eclipse.component.produit;

import java.util.Map;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;

import fr.pharma.eclipse.component.BeanListManager;
import fr.pharma.eclipse.component.produit.validator.ConditionnementRemoveValidator;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.enums.produit.TypeProduit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Manager sur les beans de gestion de Conditionnement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConditionnementListManager extends BeanListManager<Conditionnement> {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -7548336078323446495L;

    /**
     * Dictionnaire de chaine de redirection en fonction du type de produit.
     */
    private Map<TypeProduit, String> redirectDictionary;

    /**
     * Manager de conditionnement.
     */
    @Resource(name = "conditionnementManager")
    private ConditionnementManager conditionnementManager;

    /**
     * Action.
     */
    private String action = "";

    /**
     * FacesUtils.
     */
    @Resource(name = "facesUtils")
    private FacesUtils facesUtils;

    /**
     * Service Produit.
     */
    @Resource(name = "produitService")
    private ProduitService<Produit> produitService;

    /**
     * Constructeur.
     * @param searchCriteria Critère de recherche.
     */
    public ConditionnementListManager(final SearchCriteria searchCriteria) {
        super(searchCriteria);
    }

    /**
     * Validator de suppression.
     */
    @Resource(name = "conditionnementRemoveValidator")
    private ConditionnementRemoveValidator removeValidator;

    /**********************
     * Suppression
     **********************/

    /**
     * Méthode en charge d'ajouter du produit le bean Conditionnement.
     * @param produit Le produit.
     * @param conditionnement Le conditionnement.
     */
    public void add(final Produit produit,
                    final Conditionnement conditionnement) {
        if (produit.getConditionnements().contains(conditionnement)) {
            if (this.action.equals("ADD")) {
                this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR, "conditionnement.alreadyExist");
                this.facesUtils.putCallbackValidityParam(false);
                return;
            }
            produit.getConditionnements().remove(conditionnement);
        }
        this.facesUtils.putCallbackValidityParam(true);
        produit.getConditionnements().add(conditionnement);
        this.reinit();
    }

    public void addAndSave(final Produit produit,
                           final Conditionnement conditionnement) {
        this.add(produit, conditionnement);
        this.produitService.save(produit);
    }
    /**
     * Méthode en charge de supprimer du produit les beans sélectionnés.
     * @param produit Le produit.
     */
    public void remove(final Produit produit) {
        this.getBeans().clear();
        this.getBeans().addAll(produit.getConditionnements());
        for (final Conditionnement p : this.getBeansSelected()) {

            if (this.removeValidator.validate(p)) {
                produit.getConditionnements().remove(p);
            }
        }
        this.reinit();
    }

    public void initAjout(final Conditionnement conditionnement) {
        this.action = "ADD";
        this.conditionnementManager.setBean(conditionnement);
    }

    /**
     * Réinitialisation des beans selected.
     */
    public void reinit() {
        this.resetBeanSelected();
        this.conditionnementManager.init();
    }

    /**
     * Setter pour removeValidator.
     * @param removeValidator le removeValidator à écrire.
     */
    public void setRemoveValidator(final ConditionnementRemoveValidator removeValidator) {
        this.removeValidator = removeValidator;
    }

    /**
     * Getter sur redirectDictionary.
     * @return Retourne le redirectDictionary.
     */
    public Map<TypeProduit, String> getRedirectDictionary() {
        return this.redirectDictionary;
    }
    /**
     * Setter pour redirectDictionary.
     * @param redirectDictionary le redirectDictionary à écrire.
     */
    public void setRedirectDictionary(final Map<TypeProduit, String> redirectDictionary) {
        this.redirectDictionary = redirectDictionary;
    }
    /**
     * Setter pour conditionnementManager.
     * @param conditionnementManager le conditionnementManager à écrire.
     */
    public void setConditionnementManager(final ConditionnementManager conditionnementManager) {
        this.conditionnementManager = conditionnementManager;
    }
    /**
     * Getter pour action.
     * @return Le action
     */
    public String getAction() {
        return this.action;
    }
    /**
     * Setter pour action.
     * @param action Le action à écrire.
     */
    public void setAction(final String action) {
        this.action = action;
    }
    /**
     * Setter pour facesUtils.
     * @param facesUtils Le facesUtils à écrire.
     */
    public void setFacesUtils(final FacesUtils facesUtils) {
        this.facesUtils = facesUtils;
    }

    /**
     * Setter pour produitService.
     * @param produitService Le produitService à écrire.
     */
    public void setProduitService(final ProduitService<Produit> produitService) {
        this.produitService = produitService;
    }

}

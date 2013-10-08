package fr.pharma.eclipse.component.dotation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import fr.pharma.eclipse.domain.model.dotation.Dotation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.factory.dotation.DotationFactory;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Manager en charge de la gestion des demandes de dotations.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DemandeDotationManager implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6348701043785915214L;

    /**
     * Essai sélectionné.
     */
    private Essai essaiSelected;

    /**
     * Liste des services de l'essai.
     */
    private List<Service> services;

    /**
     * Service sélectionné.
     */
    private Service serviceSelected;

    /**
     * Liste des produits de l'essai.
     */
    private List<Produit> produits;

    /**
     * Liste des conditionnements du produit.
     */
    private List<Conditionnement> conditionnements;

    /**
     * Liste des dotations.
     */
    private List<Dotation> dotations;

    /**
     * Service de gestion des produits.
     */
    @Resource(name = "produitService")
    private ProduitService<Produit> produitService;

    /**
     * Factory de dotation.
     */
    @Resource(name = "dotationFactory")
    private DotationFactory dotationFactory;

    /**
     * FacesUtils.
     */
    @Resource(name = "facesUtils")
    private FacesUtils facesUtils;

    /**
     * Dotation courante.
     */
    private Dotation dotationCurrent;

    /**
     * Action sur la dotation courante (ADD ou EDIT).
     */
    private String actionDotationCurrent;

    /**
     * Dotation à supprimer.
     */
    private Dotation dotationToDelete;

    /**
     * Méthode d'initialisation.
     */
    public void init() {
        this.setServices(null);
        this.setProduits(null);
        this.setEssaiSelected(null);
        this.setDotations(new ArrayList<Dotation>());
        this.setServiceSelected(null);
        this.setDotationCurrent(null);
        this.setConditionnements(null);
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'un essai est sélectionné.
     * @param event Evénement remonté via la couche IHM.
     */
    public void handleSelectEssai(final SelectEvent event) {
        // Récupération de l'essai sélectionné
        final Essai essai = (Essai) event.getObject();

        // Calcul des produits de l'essai + pharmacie
        this.setProduits(this.produitService.getProduits(essai, essai.getPharmaciePrincipale()));

        // Récupération des services de l'essai
        this.setServices(new ArrayList<Service>(essai.getServices()));

        this.setServiceSelected(null);
    }

    /**
     * Méthode appelée pour ajouter une nouvelle dotation.
     */
    public void addDotation() {
        final Dotation dotation = this.dotationFactory.getInitializedObject(this.getEssaiSelected(), this.getServiceSelected());
        this.dotationCurrent = dotation;
        this.actionDotationCurrent = "ADD";
    }

    /**
     * Méthode en charge d'ajouter dans la liste des dotations la dotation
     * courante.
     */
    public void addDotationToDotations() {
        if ("ADD".equals(this.actionDotationCurrent)) {
            this.dotations.add(this.dotationCurrent);
        }
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'un produit est sélectionné.
     * @param event Event.
     */
    public void handleSelectProduit(final AjaxBehaviorEvent event) {
        final HtmlSelectOneMenu select = (HtmlSelectOneMenu) event.getSource();
        final Produit produit = this.produitService.reattach((Produit) select.getLocalValue());

        // Calcul des conditionnements sélectionnables
        final List<Conditionnement> listConditionnements = new ArrayList<Conditionnement>();
        if (produit != null) {
            listConditionnements.addAll(produit.getConditionnements());
        }
        this.setConditionnements(listConditionnements);

        // Suppression du conditionnement
        this.dotationCurrent.setConditionnement(null);
    }

    /**
     * Méthode appelée lors de la modification d'une dotation.
     */
    public void modifyDotation() {
        this.actionDotationCurrent = "EDIT";
    }

    /**
     * Méthode en charge de supprimer une dotation de la liste.
     */
    public void delDotation() {
        final Iterator<Dotation> it = this.getDotations().iterator();
        while (it.hasNext()) {
            if (it.next() == this.getDotationToDelete()) {
                it.remove();
            }
        }
    }

    /**
     * Méthode en charge de valider les informations d'une dotation.
     */
    public void validate() {
        // Récupération de la quantité autorisée en dotation
        final Integer quantiteAutorise = this.getDotationCurrent().getProduit().getDetailLogistique().getQuantiteAutorise();

        if ((quantiteAutorise != null) && (quantiteAutorise < this.getDotationCurrent().getQuantite())) {
            throw new ValidationException("quantiteDotation.autorisee", new String[]{"superieure" }, this.getDotationCurrent());
        }
    }

    /**
     * Méthode en charge d'afficher un message de confirmation sur
     * l'enregistrement de la demande de dotation.
     */
    public void addMessageConfirm() {
        this.facesUtils.addMessage(FacesMessage.SEVERITY_INFO, "demandeDotation.confirmMsg");
    }

    /**
     * Getter pour essaiSelected.
     * @return Le essaiSelected
     */
    public Essai getEssaiSelected() {
        return this.essaiSelected;
    }

    /**
     * Setter pour essaiSelected.
     * @param essaiSelected Le essaiSelected à écrire.
     */
    public void setEssaiSelected(final Essai essaiSelected) {
        this.essaiSelected = essaiSelected;
    }

    /**
     * Getter pour produits.
     * @return Le produits
     */
    public List<Produit> getProduits() {
        return this.produits;
    }

    /**
     * Setter pour produits.
     * @param produits Le produits à écrire.
     */
    public void setProduits(final List<Produit> produits) {
        this.produits = produits;
    }

    /**
     * Getter pour dotations.
     * @return Le dotations
     */
    public List<Dotation> getDotations() {
        return this.dotations;
    }

    /**
     * Setter pour dotations.
     * @param dotations Le dotations à écrire.
     */
    public void setDotations(final List<Dotation> dotations) {
        this.dotations = dotations;
    }

    /**
     * Setter pour produitService.
     * @param produitService Le produitService à écrire.
     */
    public void setProduitService(final ProduitService<Produit> produitService) {
        this.produitService = produitService;
    }

    /**
     * Getter pour serviceSelected.
     * @return Le serviceSelected
     */
    public Service getServiceSelected() {
        return this.serviceSelected;
    }

    /**
     * Setter pour serviceSelected.
     * @param serviceSelected Le serviceSelected à écrire.
     */
    public void setServiceSelected(final Service serviceSelected) {
        this.serviceSelected = serviceSelected;
    }

    /**
     * Setter pour dotationFactory.
     * @param dotationFactory Le dotationFactory à écrire.
     */
    public void setDotationFactory(final DotationFactory dotationFactory) {
        this.dotationFactory = dotationFactory;
    }

    /**
     * Getter pour dotationCurrent.
     * @return Le dotationCurrent
     */
    public Dotation getDotationCurrent() {
        return this.dotationCurrent;
    }

    /**
     * Setter pour dotationCurrent.
     * @param dotationCurrent Le dotationCurrent à écrire.
     */
    public void setDotationCurrent(final Dotation dotationCurrent) {
        this.dotationCurrent = dotationCurrent;
    }

    /**
     * Getter pour actionDotationCurrent.
     * @return Le actionDotationCurrent
     */
    public String getActionDotationCurrent() {
        return this.actionDotationCurrent;
    }

    /**
     * Setter pour actionDotationCurrent.
     * @param actionDotationCurrent Le actionDotationCurrent à écrire.
     */
    public void setActionDotationCurrent(final String actionDotationCurrent) {
        this.actionDotationCurrent = actionDotationCurrent;
    }

    /**
     * Getter pour conditionnements.
     * @return Le conditionnements
     */
    public List<Conditionnement> getConditionnements() {
        return this.conditionnements;
    }

    /**
     * Setter pour conditionnements.
     * @param conditionnements Le conditionnements à écrire.
     */
    public void setConditionnements(final List<Conditionnement> conditionnements) {
        this.conditionnements = conditionnements;
    }

    /**
     * Getter pour services.
     * @return Le services
     */
    public List<Service> getServices() {
        return this.services;
    }

    /**
     * Setter pour services.
     * @param services Le services à écrire.
     */
    public void setServices(final List<Service> services) {
        this.services = services;
    }

    /**
     * Getter pour dotationToDelete.
     * @return Le dotationToDelete
     */
    public Dotation getDotationToDelete() {
        return this.dotationToDelete;
    }

    /**
     * Setter pour dotationToDelete.
     * @param dotationToDelete Le dotationToDelete à écrire.
     */
    public void setDotationToDelete(final Dotation dotationToDelete) {
        this.dotationToDelete = dotationToDelete;
    }

    /**
     * Setter pour facesUtils.
     * @param facesUtils Le facesUtils à écrire.
     */
    public void setFacesUtils(final FacesUtils facesUtils) {
        this.facesUtils = facesUtils;
    }

}

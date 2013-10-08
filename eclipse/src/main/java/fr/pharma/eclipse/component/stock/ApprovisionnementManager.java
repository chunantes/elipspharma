package fr.pharma.eclipse.component.stock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import fr.pharma.eclipse.component.stock.helper.AutoSaisieNumTraitement;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.PreparationEntree;
import fr.pharma.eclipse.domain.model.stock.ReceptionLot;
import fr.pharma.eclipse.domain.model.stock.ResultApprovisionnement;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.factory.stock.ApproFactory;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Manager de Approvisionnement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ApprovisionnementManager implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4663265156154298604L;

    /**
     * Service de gestion des essais.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * Factrory.
     */
    private Map<TypeMvtStock, ApproFactory<Approvisionnement>> approFactorys;

    /**
     * Service de gestion des produits.
     */
    @Resource(name = "produitService")
    private ProduitService<Produit> produitService;

    /**
     * Saisie automatique des numéros de traitement.
     */
    @Resource(name = "autoSaisieNumTraitement")
    private AutoSaisieNumTraitement autoSaisieNumTraitement;

    /**
     * Service pharmacie.
     */
    @Resource(name = "pharmacieService")
    private PharmacieService pharmacieService;

    /**
     * Type appro.
     */
    private TypeMvtStock typeAppro;

    /**
     * Essai sélectionné.
     */
    private Essai essaiSelected;

    /**
     * Liste des produits de l'essai.
     */
    private List<Produit> produits;

    /**
     * Liste des pharmacies de l'utilisateur.
     */
    private List<Pharmacie> pharmacies;

    /**
     * Pharmacie sélectionnée.
     */
    private Pharmacie pharmacieSelected;

    /**
     * Liste de réceptions de lots.
     */
    private List<ReceptionLot> receptionLots;

    /**
     * Réception de lot courante.
     */
    private ReceptionLot receptionCurrent;

    /**
     * Réception de lot à supprimer.
     */
    private ReceptionLot receptionToDelete;

    /**
     * Action sur la réception courante (ADD ou EDIT).
     */
    private String actionReceptionCurrent;

    /**
     * Résultat de l'approvisionnement.
     */
    private ResultApprovisionnement result;

    /**
     * Date de réception.
     */
    private Calendar dateReception;

    /**
     * Date d'arrivée du colis.
     */
    private Calendar dateArriveeColis;

    /**
     * Date de Fabrication.
     */
    private Calendar dateFabrication;

    /**
     * Composition.
     */
    private String composition;

    /**
     * Numero d'ordonnancier.
     */
    private Integer numOrdonnancier;

    /**
     * Méthode d'initialisation.
     */
    public void init() {
        this.setProduits(null);
        this.setEssaiSelected(null);
        this.setPharmacies(new ArrayList<Pharmacie>());
        this.setPharmacieSelected(null);
        this.setReceptionCurrent(null);
        this.setComposition(null);
        this.setReceptionLots(new ArrayList<ReceptionLot>());
        this.setDateArriveeColis(Calendar.getInstance(EclipseConstants.LOCALE));
        this.setDateReception(Calendar.getInstance(EclipseConstants.LOCALE));
        this.setDateFabrication(Calendar.getInstance(EclipseConstants.LOCALE));
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'un essai est sélectionné.
     * @param event Evénement remonté via la couche IHM.
     */
    public void handleSelectEssai(final SelectEvent event) {
        // // Récupération de l'essai sélectionné
        // final Essai essai = (Essai) event.getObject();

        final List<Pharmacie> pharmaciesDisplay = this.essaiService.getAllPharmaciesOfUser(this.getEssaiSelected());
        this.setPharmacies(pharmaciesDisplay);

        if (pharmaciesDisplay.size() == 1) {
            this.setPharmacieSelected(pharmaciesDisplay.get(0));

        } else {
            this.setPharmacieSelected(null);
        }

        // Calcul des produits de l'essai + pharmacie
        this.setProduits(this.produitService.getProduits(this.getEssaiSelected(), this.pharmacieSelected));
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'une pharmacie est sélectionnée.
     * @param event Evénement remonté via la couche IHM.
     */
    public void handleSelectPharmacie(final AjaxBehaviorEvent event) {
        // Récupération de la pharmacie sélectionnée
        final HtmlSelectOneMenu select = (HtmlSelectOneMenu) event.getSource();
        final Pharmacie pharmacie = (Pharmacie) select.getLocalValue();
        this.setPharmacieSelected(pharmacie);

        // Calcul des produits de l'essai + pharmacie
        this.setProduits(this.produitService.getProduits(this.essaiSelected, this.pharmacieSelected));
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
        this.receptionCurrent.setConditionnements(listConditionnements);

        // Suppression du conditionnement
        this.receptionCurrent.getAppro().setConditionnement(null);

        // Suppression des numéros de traitement
        this.receptionCurrent.setNbNumerosTraitement(null);
        this.receptionCurrent.handleSaisieNbNumsTraitements();
    }

    /**
     * Méthode appelée via la couche IHM lorsque le premier numéro de traitement
     * est renseigné.
     */
    public void handleSaisieAutoNumsTraitements() {
        this.autoSaisieNumTraitement.handle(this.receptionCurrent.getNumsTraitements());
    }

    /**
     * Méthode appelée pour ajouter un nouveau lot de réception.
     */
    public void addReceptionLot() {
        final ReceptionLot saisie = new ReceptionLot();
        final Approvisionnement appro =
            this.approFactorys.get(this.typeAppro).getInitializedObject(this.getEssaiSelected(), this.getPharmacieSelected(), this.getDateReception(), this.getDateArriveeColis());
        saisie.setAppro(appro);
        this.receptionCurrent = saisie;
        this.actionReceptionCurrent = "ADD";
    }

    /**
     * Ajout d'un lot à l'entrée de préparation.
     */
    public void addReceptionLotPreparation() {
        this.addReceptionLot();
        final PreparationEntree p = (PreparationEntree) this.receptionCurrent.getAppro();

        p.setNumOrdonnancier(this.calculNumOrdonnancierPreparationEntree());
        p.setNumLot(p.getNumOrdonnancier().toString());
    }

    /**
     * Calcul du numero d'ordonnancier de l'entrée de préparation .
     * @return le numero d'ordonnancier de l'entrée de préparation.
     */
    private int calculNumOrdonnancierPreparationEntree() {

        if (this.receptionLots.isEmpty()) {
            // s'il n'y a pas eu de réception de lot pour cette entrée, alors on
            // se base sur
            // l'attribut numOrdonnancier
            if (this.numOrdonnancier == null) {
                this.numOrdonnancier = this.receptionCurrent.getAppro().getPharmacie().getNumOrdonnancierFab();
            }
            return this.numOrdonnancier + 1;

        } else {
            // si des lots on deja été saisis pour cette entrée, alors on se
            // base sur le numero du
            // lot saisi en dernier
            return ((PreparationEntree) this.receptionLots.get(this.receptionLots.size() - 1).getAppro()).getNumOrdonnancier() + 1;
        }
    }
    /**
     * Mise à jour du numéro d'ordonnancier.
     */
    public void majNumOrdonnancier() {
        // MAJ de l'attribut numOrdonnancier.
        this.numOrdonnancier = ((PreparationEntree) this.receptionLots.get(this.receptionLots.size() - 1).getAppro()).getNumOrdonnancier();

        // MAJ en base du numero d'ordonnancier max de la pharmacie.
        this.getPharmacieSelected().setNumOrdonnancierFab(this.numOrdonnancier);
        this.pharmacieService.save(this.pharmacieSelected);
    }

    /**
     * Méthode appelée lors de la modification d'un lot de réception.
     */
    public void modifyReceptionLot() {
        this.actionReceptionCurrent = "EDIT";
    }

    /**
     * Méthode en charge d'ajouter dans la liste des réceptions de lot la
     * réception courante.
     */
    public void addReceptionToReceptions() {
        if ("ADD".equals(this.actionReceptionCurrent)) {
            this.receptionLots.add(this.receptionCurrent);
        }
    }

    /**
     * Méthode en charge de supprimer une réception de lot de la liste.
     */
    public void delReception() {
        this.getReceptionLots().remove(this.getReceptionToDelete());
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
     * Getter pour pharmacies.
     * @return Le pharmacies
     */
    public List<Pharmacie> getPharmacies() {
        return this.pharmacies;
    }

    /**
     * Setter pour pharmacies.
     * @param pharmacies Le pharmacies à écrire.
     */
    public void setPharmacies(final List<Pharmacie> pharmacies) {
        this.pharmacies = pharmacies;
    }

    /**
     * Getter pour result.
     * @return Le result
     */
    public ResultApprovisionnement getResult() {
        return this.result;
    }

    /**
     * Setter pour result.
     * @param result Le result à écrire.
     */
    public void setResult(final ResultApprovisionnement result) {
        this.result = result;
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
     * Getter pour pharmacieSelected.
     * @return Le pharmacieSelected
     */
    public Pharmacie getPharmacieSelected() {
        return this.pharmacieSelected;
    }

    /**
     * Setter pour pharmacieSelected.
     * @param pharmacieSelected Le pharmacieSelected à écrire.
     */
    public void setPharmacieSelected(final Pharmacie pharmacieSelected) {
        this.pharmacieSelected = pharmacieSelected;
    }

    /**
     * Setter pour autoSaisieNumTraitement.
     * @param autoSaisieNumTraitement Le autoSaisieNumTraitement à écrire.
     */
    public void setAutoSaisieNumTraitement(final AutoSaisieNumTraitement autoSaisieNumTraitement) {
        this.autoSaisieNumTraitement = autoSaisieNumTraitement;
    }

    /**
     * Getter pour receptionLots.
     * @return Le receptionLots
     */
    public List<ReceptionLot> getReceptionLots() {
        return this.receptionLots;
    }

    /**
     * Setter pour receptionLots.
     * @param receptionLots Le receptionLots à écrire.
     */
    public void setReceptionLots(final List<ReceptionLot> receptionLots) {
        this.receptionLots = receptionLots;
    }

    /**
     * Getter pour receptionCurrent.
     * @return Le receptionCurrent
     */
    public ReceptionLot getReceptionCurrent() {
        return this.receptionCurrent;
    }

    /**
     * Setter pour receptionCurrent.
     * @param receptionCurrent Le receptionCurrent à écrire.
     */
    public void setReceptionCurrent(final ReceptionLot receptionCurrent) {
        this.receptionCurrent = receptionCurrent;
    }

    /**
     * Getter pour receptionToDelete.
     * @return Le receptionToDelete
     */
    public ReceptionLot getReceptionToDelete() {
        return this.receptionToDelete;
    }

    /**
     * Setter pour receptionToDelete.
     * @param receptionToDelete Le receptionToDelete à écrire.
     */
    public void setReceptionToDelete(final ReceptionLot receptionToDelete) {
        this.receptionToDelete = receptionToDelete;
    }

    /**
     * Setter pour essaiService.
     * @param essaiService Le essaiService à écrire.
     */
    public void setEssaiService(final EssaiService essaiService) {
        this.essaiService = essaiService;
    }

    /**
     * Setter pour produitService.
     * @param produitService Le produitService à écrire.
     */
    public void setProduitService(final ProduitService<Produit> produitService) {
        this.produitService = produitService;
    }

    /**
     * Getter pour actionReceptionCurrent.
     * @return Le actionReceptionCurrent
     */
    public String getActionReceptionCurrent() {
        return this.actionReceptionCurrent;
    }

    /**
     * Setter pour actionReceptionCurrent.
     * @param actionReceptionCurrent Le actionReceptionCurrent à écrire.
     */
    public void setActionReceptionCurrent(final String actionReceptionCurrent) {
        this.actionReceptionCurrent = actionReceptionCurrent;
    }

    /**
     * Getter pour dateReception.
     * @return Le dateReception
     */
    public Calendar getDateReception() {
        return this.dateReception;
    }

    /**
     * Setter pour dateReception.
     * @param dateReception Le dateReception à écrire.
     */
    public void setDateReception(final Calendar dateReception) {
        this.dateReception = dateReception;
    }

    /**
     * Getter pour dateArriveeColis.
     * @return Le dateArriveeColis
     */
    public Calendar getDateArriveeColis() {
        return this.dateArriveeColis;
    }

    /**
     * Setter pour dateArriveeColis.
     * @param dateArriveeColis Le dateArriveeColis à écrire.
     */
    public void setDateArriveeColis(final Calendar dateArriveeColis) {
        this.dateArriveeColis = dateArriveeColis;
    }

    /**
     * Setter pour approFactorys.
     * @param approFactorys Le approFactorys à écrire.
     */
    public void setApproFactorys(final Map<TypeMvtStock, ApproFactory<Approvisionnement>> approFactorys) {
        this.approFactorys = approFactorys;
    }

    /**
     * Getter pour typeAppro.
     * @return Le typeAppro
     */
    public TypeMvtStock getTypeAppro() {
        return this.typeAppro;
    }

    /**
     * Setter pour typeAppro.
     * @param typeAppro Le typeAppro à écrire.
     */
    public void setTypeAppro(final TypeMvtStock typeAppro) {
        this.typeAppro = typeAppro;
    }

    /**
     * Getter pour dateFabrication.
     * @return Le dateFabrication
     */
    public Calendar getDateFabrication() {
        return this.dateFabrication;
    }

    /**
     * Setter pour dateFabrication.
     * @param dateFabrication Le dateFabrication à écrire.
     */
    public void setDateFabrication(final Calendar dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    /**
     * Getter pour composition.
     * @return Le composition
     */
    public String getComposition() {
        return this.composition;
    }

    /**
     * Setter pour composition.
     * @param composition Le composition à écrire.
     */
    public void setComposition(final String composition) {
        this.composition = composition;
    }

    /**
     * Getter pour pharmacieService.
     * @return Le pharmacieService
     */
    public PharmacieService getPharmacieService() {
        return this.pharmacieService;
    }

    /**
     * Setter pour pharmacieService.
     * @para)m pharmacieService Le pharmacieService à écrire.
     */
    public void setPharmacieService(final PharmacieService pharmacieService) {
        this.pharmacieService = pharmacieService;
    }

    /**
     * Getter pour numOrdonnancier.
     * @return Le numOrdonnancier
     */
    public Integer getNumOrdonnancier() {
        return this.numOrdonnancier;
    }

    /**
     * Setter pour numOrdonnancier.
     * @param numOrdonnancier Le numOrdonnancier à écrire.
     */
    public void setNumOrdonnancier(final Integer numOrdonnancier) {
        this.numOrdonnancier = numOrdonnancier;
    }

}

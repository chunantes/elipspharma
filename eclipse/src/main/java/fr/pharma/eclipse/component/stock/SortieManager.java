package fr.pharma.eclipse.component.stock;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.primefaces.event.SelectEvent;

import fr.pharma.eclipse.domain.enums.stock.RaisonSortie;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.localisation.Etablissement;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stock.ResultSortie;
import fr.pharma.eclipse.domain.model.stock.Sortie;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.factory.stock.MvtStockFactory;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.localisation.EtablissementService;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stock.StockService;
import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Manager de sortie de stock (mouvement de stock de type Sortie).
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SortieManager implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -634054951013915238L;

    /**
     * Service de gestion des essais.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * Service de gestion des produits.
     */
    @Resource(name = "produitService")
    private ProduitService<Produit> produitService;

    /**
     * Service de gestion des stocks.
     */
    @Resource(name = "stockService")
    private StockService stockService;

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
     * Liste des pharmacies destination.
     */
    private List<Pharmacie> pharmaciesDest;

    /**
     * Liste des etablissements destination.
     */
    private List<Etablissement> etablissementsDest;

    /**
     * Pharmacie sélectionnée.
     */
    private Pharmacie pharmacieSelected;

    /**
     * Type de sortie.
     */
    private TypeMvtStock typeSortie;

    /**
     * Raison de la sortie.
     */
    private RaisonSortie raisonSortie;

    /**
     * Commentaire : Raison de la sortie.
     */
    private String commentaireRaison;

    /**
     * Liste de sortie.
     */
    private List<Sortie> sorties;

    /**
     * Sortie courante.
     */
    private Sortie sortieCurrent;

    /**
     * Action sur la sortie courante (ADD ou EDIT).
     */
    private String actionSortieCurrent;

    /**
     * Commentaire de la sortie.
     */
    private String commentaire;

    /**
     * Nom de la société de transport.
     */
    private String nomSocieteTransport;

    /**
     * Référence de l'envoi.
     */
    private String referenceEnvoi;

    /**
     * Sortie à supprimer.
     */
    private Sortie sortieToDelete;

    /**
     * Résultat de la sortie.
     */
    private ResultSortie result;

    /**
     * Fichier de destruction.
     */
    private UploadedFile fileDestruction;

    /**
     * Fichier de retour promoteur.
     */
    private UploadedFile fileRetourPromoteur;

    /**
     * Pharmacie destiation.
     */
    private Pharmacie pharmacieDest;

    /**
     * Etablissement destiation.
     */
    private Etablissement etablissementDest;

    /**
     * Etablissement service.
     */
    @Resource(name = "etablissementService")
    private EtablissementService etablissementService;

    /**
     * Map contenant les factories de création de mouvement en fonction du type.
     */
    @Resource(name = "mapFactoriesByTypeMouvement")
    private Map<TypeMvtStock, MvtStockFactory<MvtStock>> mapFactories;

    /**
     * Méthode d'initialisation.
     */
    public void init() {
        this.setProduits(null);
        this.pharmacieDest = null;
        this.setEssaiSelected(null);
        this.setPharmacies(new ArrayList<Pharmacie>());
        this.setPharmaciesDest(new ArrayList<Pharmacie>());
        this.setEtablissementsDest(new ArrayList<Etablissement>());
        this.setPharmacieSelected(null);
        this.setPharmacieDest(null);
        this.setEtablissementDest(null);
        this.setTypeSortie(null);
        this.setRaisonSortie(null);
        this.setCommentaireRaison(null);
        this.setSortieCurrent(null);
        this.setSorties(new ArrayList<Sortie>());
        this.setCommentaire(null);
        this.setReferenceEnvoi(null);
        this.setNomSocieteTransport(null);
        this.setFileDestruction(null);
        this.setFileRetourPromoteur(null);
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'un essai est sélectionné.
     * @param event Evénement remonté via la couche IHM.
     */
    public void handleSelectEssai(final SelectEvent event) {
        // Récupération de l'essai sélectionné
        // final Essai essai = (Essai) event.getObject();

        final List<Pharmacie> pharmaciesDisplay = this.essaiService.getAllPharmaciesOfUser(this.getEssaiSelected());
        this.setPharmacies(pharmaciesDisplay);

        if (pharmaciesDisplay.size() == 1) {
            this.setPharmacieSelected(pharmaciesDisplay.get(0));

        } else {
            this.setPharmacieSelected(null);
        }

        // Calcul des produits de l'essai + pharmacie
        this.setProduits(this.produitService.getProduitsWithPreparations(this.getEssaiSelected(), this.pharmacieSelected));
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
        this.setProduits(this.produitService.getProduitsWithPreparations(this.essaiSelected, this.pharmacieSelected));
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'un type de sortie est
     * sélectionné.
     * @param event Evénement remonté via la couche IHM.
     */
    public void handleSelectTypeSortie(final AjaxBehaviorEvent event) {
        // Récupération du type de sortie
        final HtmlSelectOneMenu select = (HtmlSelectOneMenu) event.getSource();
        final TypeMvtStock type = (TypeMvtStock) select.getLocalValue();
        this.setTypeSortie(type);
        if (!TypeMvtStock.RETOUR_PROMOTEUR.equals(type)) {
            this.setReferenceEnvoi(null);
            this.setNomSocieteTransport(null);
            this.setFileRetourPromoteur(null);
        }
        if (!TypeMvtStock.DESTRUCTION.equals(type)) {
            this.setFileDestruction(null);
        }
        if (!TypeMvtStock.CESSION_PUI.equals(type)) {
            this.setPharmacieDest(null);
            this.setEtablissementDest(null);
            this.setPharmaciesDest(null);
            this.setEtablissementsDest(null);
        } else if (this.essaiSelected != null) {
            this.etablissementsDest = new ArrayList<Etablissement>(this.essaiService.reattach(this.getEssaiSelected()).getDetailDonneesPharma().getEtablissements());
        }
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'une raison de sortie est
     * sélectionné.
     * @param event Evénement remonté via la couche IHM.
     */
    public void handleSelectRaisonSortie(final AjaxBehaviorEvent event) {
        // Récupération du type de sortie
        final HtmlSelectOneMenu select = (HtmlSelectOneMenu) event.getSource();
        select.getLocalValue();
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
        this.sortieCurrent.setConditionnements(listConditionnements);

        // Suppression du conditionnement
        this.sortieCurrent.getMvtSortie().setConditionnement(null);
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'un conditionnement est
     * sélectionné.
     * @param event Event.
     */
    public void handleSelectConditionnement(final AjaxBehaviorEvent event) {
        final HtmlSelectOneMenu select = (HtmlSelectOneMenu) event.getSource();
        final Conditionnement conditionnement = (Conditionnement) select.getLocalValue();

        final MvtStock mvt = this.sortieCurrent.getMvtSortie();

        // Valorisation des lignes de stock possibles pour une sortie
        final List<LigneStock> lignesStock = this.stockService.getAllLignesStock(mvt.getEssai(), mvt.getPharmacie(), mvt.getProduit(), conditionnement, false);
        this.sortieCurrent.setLignesStock(lignesStock);
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'un etablissement est
     * sélectionné.
     * @param event Event.
     */
    public void handleSelectEtablissement(final AjaxBehaviorEvent event) {
        final HtmlSelectOneMenu select = (HtmlSelectOneMenu) event.getSource();
        final Etablissement etablissement = (Etablissement) select.getLocalValue();

        if (etablissement == null) {
            this.pharmaciesDest.clear();
        } else {
            this.pharmaciesDest = new ArrayList<Pharmacie>(etablissement.getPharmacies());
        }
    }
    /**
     * Méthode appelée pour ajouter une nouvelle sortie.
     */
    public void addSortie() {
        if (this.getTypeSortie() != null) {
            final Sortie sortie = new Sortie();
            // Création du bon mouvement par rapport au type de sortie
            final MvtStockFactory<MvtStock> factory = this.mapFactories.get(this.getTypeSortie());
            final MvtStock mvt = factory.getInitializedObject();
            mvt.setEssai(this.getEssaiSelected());
            mvt.setPharmacie(this.getPharmacieSelected());
            sortie.setMvtSortie(mvt);
            this.sortieCurrent = sortie;
            this.actionSortieCurrent = "ADD";
        }
    }

    /**
     * Méthode en charge de construire la composition.
     * @return La composition.
     */
    public String buildComposition() {
        final StringBuffer sb = new StringBuffer();

        // pour chaque ligne.
        for (final Sortie sortie : this.sorties) {
            for (final LigneStock ligne : sortie.getLignesStockCompletees()) {
                // nom du produit
                sb.append(ligne.getProduit().getNom()).append(EclipseConstants.SPACE).append(":").append(EclipseConstants.SPACE);
                sb.append(ligne.getNumLot());
                if (ligne.getDatePeremption() != null) {
                    sb.append(EclipseConstants.SPACE).append(EclipseConstants.DASH).append(EclipseConstants.SPACE);

                    sb.append(Utils.formatDate(ligne.getDatePeremption().getTime(), EclipseConstants.PATTERN_SIMPLE));
                }
                sb.append(EclipseConstants.SPACE).append(EclipseConstants.DASH).append(EclipseConstants.SPACE).append("Quantité : ").append(ligne.getQteASortir());

                // saut de ligne
                sb.append(EclipseConstants.SAUT_LIGNE);
            }
        }
        return sb.toString();
    }
    /**
     * Méthode appelée lors de la modification d'une sortie.
     */
    public void modifySortie() {
        this.actionSortieCurrent = "EDIT";
    }

    /**
     * Méthode en charge de supprimer une sortie de la liste.
     */
    public void delSortie() {
        this.getSorties().remove(this.getSortieToDelete());
    }

    /**
     * Méthode en charge d'ajouter dans la liste des sorties la sortie courante.
     */
    public void addSortieToSorties() {
        if ("ADD".equals(this.actionSortieCurrent)) {
            this.sorties.add(this.sortieCurrent);
        }
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
     * Getter pour typeSortie.
     * @return Le typeSortie
     */
    public TypeMvtStock getTypeSortie() {
        return this.typeSortie;
    }

    /**
     * Setter pour typeSortie.
     * @param typeSortie Le typeSortie à écrire.
     */
    public void setTypeSortie(final TypeMvtStock typeSortie) {
        this.typeSortie = typeSortie;
    }

    /**
     * Getter pour sorties.
     * @return Le sorties
     */
    public List<Sortie> getSorties() {
        return this.sorties;
    }

    /**
     * Setter pour sorties.
     * @param sorties Le sorties à écrire.
     */
    public void setSorties(final List<Sortie> sorties) {
        this.sorties = sorties;
    }

    /**
     * Getter pour sortieCurrent.
     * @return Le sortieCurrent
     */
    public Sortie getSortieCurrent() {
        return this.sortieCurrent;
    }

    /**
     * Setter pour sortieCurrent.
     * @param sortieCurrent Le sortieCurrent à écrire.
     */
    public void setSortieCurrent(final Sortie sortieCurrent) {
        this.sortieCurrent = sortieCurrent;
    }

    /**
     * Getter pour commentaire.
     * @return Le commentaire
     */
    public String getCommentaire() {
        return this.commentaire;
    }

    /**
     * Setter pour commentaire.
     * @param commentaire Le commentaire à écrire.
     */
    public void setCommentaire(final String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * Setter pour stockService.
     * @param stockService Le stockService à écrire.
     */
    public void setStockService(final StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * Getter pour sortieToDelete.
     * @return Le sortieToDelete
     */
    public Sortie getSortieToDelete() {
        return this.sortieToDelete;
    }

    /**
     * Setter pour sortieToDelete.
     * @param sortieToDelete Le sortieToDelete à écrire.
     */
    public void setSortieToDelete(final Sortie sortieToDelete) {
        this.sortieToDelete = sortieToDelete;
    }

    /**
     * Getter pour nomSocieteTransport.
     * @return Le nomSocieteTransport
     */
    public String getNomSocieteTransport() {
        return this.nomSocieteTransport;
    }

    /**
     * Setter pour nomSocieteTransport.
     * @param nomSocieteTransport Le nomSocieteTransport à écrire.
     */
    public void setNomSocieteTransport(final String nomSocieteTransport) {
        this.nomSocieteTransport = nomSocieteTransport;
    }

    /**
     * Getter pour referenceEnvoi.
     * @return Le referenceEnvoi
     */
    public String getReferenceEnvoi() {
        return this.referenceEnvoi;
    }

    /**
     * Setter pour referenceEnvoi.
     * @param referenceEnvoi Le referenceEnvoi à écrire.
     */
    public void setReferenceEnvoi(final String referenceEnvoi) {
        this.referenceEnvoi = referenceEnvoi;
    }

    /**
     * Getter pour result.
     * @return Le result
     */
    public ResultSortie getResult() {
        return this.result;
    }

    /**
     * Setter pour result.
     * @param result Le result à écrire.
     */
    public void setResult(final ResultSortie result) {
        this.result = result;
    }

    /**
     * Setter pour mapFactories.
     * @param mapFactories Le mapFactories à écrire.
     */
    public void setMapFactories(final Map<TypeMvtStock, MvtStockFactory<MvtStock>> mapFactories) {
        this.mapFactories = mapFactories;
    }

    /**
     * Getter pour actionSortieCurrent.
     * @return Le actionSortieCurrent
     */
    public String getActionSortieCurrent() {
        return this.actionSortieCurrent;
    }

    /**
     * Setter pour actionSortieCurrent.
     * @param actionSortieCurrent Le actionSortieCurrent à écrire.
     */
    public void setActionSortieCurrent(final String actionSortieCurrent) {
        this.actionSortieCurrent = actionSortieCurrent;
    }

    /**
     * Getter sur produitService.
     * @return Retourne le produitService.
     */
    public ProduitService<Produit> getProduitService() {
        return this.produitService;
    }

    /**
     * Getter sur stockService.
     * @return Retourne le stockService.
     */
    public StockService getStockService() {
        return this.stockService;
    }

    /**
     * Getter pour fileDestruction.
     * @return Le fileDestruction
     */
    public UploadedFile getFileDestruction() {
        return this.fileDestruction;
    }

    /**
     * Setter pour fileDestruction.
     * @param fileDestruction Le fileDestruction à écrire.
     */
    public void setFileDestruction(final UploadedFile fileDestruction) {
        this.fileDestruction = fileDestruction;
    }

    /**
     * Getter pour fileRetourPromoteur.
     * @return Le fileRetourPromoteur
     */
    public UploadedFile getFileRetourPromoteur() {
        return this.fileRetourPromoteur;
    }

    /**
     * Setter pour fileRetourPromoteur.
     * @param fileRetourPromoteur Le fileRetourPromoteur à écrire.
     */
    public void setFileRetourPromoteur(final UploadedFile fileRetourPromoteur) {
        this.fileRetourPromoteur = fileRetourPromoteur;
    }

    /**
     * Getter pour pharmacieDest.
     * @return Le pharmacieDest
     */
    public Pharmacie getPharmacieDest() {
        return this.pharmacieDest;
    }

    /**
     * Setter pour pharmacieDest.
     * @param pharmacieDest Le pharmacieDest à écrire.
     */
    public void setPharmacieDest(final Pharmacie pharmacieDest) {
        this.pharmacieDest = pharmacieDest;
    }

    /**
     * Getter pour pharmaciesDest.
     * @return Le pharmaciesDest
     */
    public List<Pharmacie> getPharmaciesDest() {
        return this.pharmaciesDest;
    }

    /**
     * Setter pour pharmaciesDest.
     * @param pharmaciesDest Le pharmaciesDest à écrire.
     */
    public void setPharmaciesDest(final List<Pharmacie> pharmaciesDest) {
        this.pharmaciesDest = pharmaciesDest;
    }

    /**
     * Getter pour raisonSortie.
     * @return Le raisonSortie
     */
    public RaisonSortie getRaisonSortie() {
        return this.raisonSortie;
    }

    /**
     * Setter pour raisonSortie.
     * @param raisonSortie Le raisonSortie à écrire.
     */
    public void setRaisonSortie(final RaisonSortie raisonSortie) {
        this.raisonSortie = raisonSortie;
    }

    /**
     * Getter pour commentaireRaison.
     * @return Le commentaireRaison
     */
    public String getCommentaireRaison() {
        return this.commentaireRaison;
    }

    /**
     * Setter pour commentaireRaison.
     * @param commentaireRaison Le commentaireRaison à écrire.
     */
    public void setCommentaireRaison(final String commentaireRaison) {
        this.commentaireRaison = commentaireRaison;
    }

    /**
     * Getter pour etablissementsDest.
     * @return Le etablissementsDest
     */
    public List<Etablissement> getEtablissementsDest() {
        return this.etablissementsDest;
    }

    /**
     * Setter pour etablissementsDest.
     * @param etablissementsDest Le etablissementsDest à écrire.
     */
    public void setEtablissementsDest(final List<Etablissement> etablissementsDest) {
        this.etablissementsDest = etablissementsDest;
    }

    /**
     * Getter pour etablissementDest.
     * @return Le etablissementDest
     */
    public Etablissement getEtablissementDest() {
        return this.etablissementDest;
    }

    /**
     * Setter pour etablissementDest.
     * @param etablissementDest Le etablissementDest à écrire.
     */
    public void setEtablissementDest(final Etablissement etablissementDest) {
        this.etablissementDest = etablissementDest;
    }

    /**
     * Getter pour etablissementService.
     * @return Le etablissementService
     */
    public EtablissementService getEtablissementService() {
        return this.etablissementService;
    }

    /**
     * Setter pour etablissementService.
     * @param etablissementService Le etablissementService à écrire.
     */
    public void setEtablissementService(final EtablissementService etablissementService) {
        this.etablissementService = etablissementService;
    }

}

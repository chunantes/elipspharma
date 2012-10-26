package fr.pharma.eclipse.component.dotation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import fr.pharma.eclipse.component.BeansManager;
import fr.pharma.eclipse.domain.criteria.dotation.DotationSearchCriteria;
import fr.pharma.eclipse.domain.model.dotation.Dotation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.produit.ProduitService;

/**
 * Manager de dispensation globale (traitement des demandes de dotation).
 
 * @version $Revision$ $Date$
 */
public class DispensationGlobaleManager
    extends BeansManager<Dotation>
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -7611226006572518006L;

    /**
     * Critère de recherche.
     */
    private final DotationSearchCriteria criteria;

    /**
     * Service de gestion des produits.
     */
    @Resource(name = "produitService")
    private ProduitService<Produit> produitService;

    /**
     * Liste des produits de l'essai.
     */
    private List<Produit> produits;

    /**
     * Liste des lignes de stock pouvant traiter la demande de dotation.
     */
    private List<LigneStock> lignesStockForDotation;

    /**
     * Constructeur.
     * @param criteria Critère de recherche.
     */
    public DispensationGlobaleManager(final DotationSearchCriteria criteria)
    {
        super(criteria);
        this.criteria = criteria;
    }

    /**
     * Méthode d'initialisation du manager.
     */
    public void init()
    {
        this.setBeanSelected(null);
        this.criteria.setTraitee(Boolean.FALSE);
        this.setProduits(null);
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'un essai est sélectionné.
     * @param event Evénement remonté via la couche IHM.
     */
    public void handleSelectEssai(final SelectEvent event)
    {
        // Récupération de l'essai sélectionné
        final Essai essai = (Essai) event.getObject();

        // Récupération de la pharmacie sélectionnée
        final Pharmacie pharmacie = this.criteria.getPharmacie();

        // Calcul des produits de l'essai + pharmacie
        this.setProduits(this.produitService.getProduits(essai,
                                                         pharmacie));
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'une pharmacie est sélectionnée.
     * @param event Evénement remonté via la couche IHM.
     */
    public void handleSelectPharmacie(final AjaxBehaviorEvent event)
    {
        // Récupération de la pharmacie sélectionnée
        final HtmlSelectOneMenu select = (HtmlSelectOneMenu) event.getSource();
        final Pharmacie pharmacie = (Pharmacie) select.getLocalValue();
        this.criteria.setPharmacie(pharmacie);

        // Calcul des produits de l'essai + pharmacie
        if (this.criteria.getEssai() != null)
        {
            this.setProduits(this.produitService.getProduits(this.criteria.getEssai(),
                                                             this.criteria.getPharmacie()));
        }
        else
        {
            this.setProduits(new ArrayList<Produit>());
        }
    }

    /**
     * Getter pour produits.
     * @return Le produits
     */
    public List<Produit> getProduits()
    {
        return this.produits;
    }

    /**
     * Setter pour produits.
     * @param produits Le produits à écrire.
     */
    public void setProduits(final List<Produit> produits)
    {
        this.produits = produits;
    }

    /**
     * Setter pour produitService.
     * @param produitService Le produitService à écrire.
     */
    public void setProduitService(final ProduitService<Produit> produitService)
    {
        this.produitService = produitService;
    }

    /**
     * Getter pour lignesStockForDotation.
     * @return Le lignesStockForDotation
     */
    public List<LigneStock> getLignesStockForDotation()
    {
        return this.lignesStockForDotation;
    }

    /**
     * Setter pour lignesStockForDotation.
     * @param lignesStockForDotation Le lignesStockForDotation à écrire.
     */
    public void setLignesStockForDotation(final List<LigneStock> lignesStockForDotation)
    {
        this.lignesStockForDotation = lignesStockForDotation;
    }

}

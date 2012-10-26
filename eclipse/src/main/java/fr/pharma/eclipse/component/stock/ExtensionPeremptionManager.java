package fr.pharma.eclipse.component.stock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

import fr.pharma.eclipse.component.BeansManager;
import fr.pharma.eclipse.domain.criteria.stock.ExtensionPeremptionSearchCriteria;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentStock;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.DocumentStock;
import fr.pharma.eclipse.domain.model.stock.document.DocumentAppro;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.document.DocumentService;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Manager d'extension de péremption.
 
 * @version $Revision$ $Date$
 */
public class ExtensionPeremptionManager
    extends BeansManager<Approvisionnement>
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 847424540429589354L;

    /**
     * Critère de recherche d'extension de péremption.
     */
    private final ExtensionPeremptionSearchCriteria searchCriteria;

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
     * Liste des conditionnements du produit.
     */
    private List<Conditionnement> conditionnements;

    /**
     * Date de peremption d'origine.
     */
    private Calendar ancienneDatePeremption;

    /**
     * Service document.
     */
    @Resource(name = "documentService")
    private DocumentService documentService;

    /**
     * Manager des documents d'approvisionnements.
     */
    @Resource(name = "documentApproStockManager")
    private GenericStockManager<DocumentAppro> managerDocAppro;

    /**
     * Constructeur.
     * @param searchCriteria Critère de recherche.
     */
    public ExtensionPeremptionManager(final ExtensionPeremptionSearchCriteria searchCriteria)
    {
        super(searchCriteria);
        this.searchCriteria = searchCriteria;
    }

    /**
     * Méthode d'initialisation.
     */
    public void init()
    {
        this.setProduits(null);
        this.setConditionnements(null);
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
        final Pharmacie pharmacie = this.searchCriteria.getPharmacie();

        // Calcul des produits de l'essai + pharmacie
        this.setProduits(this.produitService.getProduits(essai,
                                                         pharmacie));

        // Effacement du conditionnement
        this.searchCriteria.setConditionnement(null);
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
        this.searchCriteria.setPharmacie(pharmacie);

        // Calcul des produits de l'essai + pharmacie
        if (this.searchCriteria.getEssai() != null)
        {
            this.setProduits(this.produitService.getProduits(this.searchCriteria.getEssai(),
                                                             this.searchCriteria.getPharmacie()));
        }
        else
        {
            this.setProduits(new ArrayList<Produit>());
        }

        // Effacement du conditionnement
        this.searchCriteria.setConditionnement(null);
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'un produit est sélectionné.
     * @param event Event.
     */
    public void handleSelectProduit(final AjaxBehaviorEvent event)
    {
        final HtmlSelectOneMenu select = (HtmlSelectOneMenu) event.getSource();
        final Produit produit = this.produitService.reattach((Produit) select.getLocalValue());

        // Calcul des conditionnements sélectionnables
        final List<Conditionnement> listConditionnements = new ArrayList<Conditionnement>();
        if (produit != null)
        {
            listConditionnements.addAll(produit.getConditionnements());
        }
        this.setConditionnements(listConditionnements);

        // Suppression du conditionnement
        this.searchCriteria.setConditionnement(null);
    }

    /**
     * Méthode en charge de déterminer si le délai d'alerte avant la date de péremption est
     * atteint pour le produit.
     * @param appro Approvisionnement à tester.
     * @return Résultat de l'atteinte de l'alerte.
     */
    public Boolean delaiAlerteAvantExpirationAtteint(final Approvisionnement appro)
    {
        Boolean result = Boolean.FALSE;

        final Calendar datePeremption = appro.getDatePeremption();

        if (datePeremption != null)
        {
            // Récupération du délai d'alerte avant la date de péremption pour un produit
            // Délai en jours
            final Produit produit = appro.getProduit();
            final Integer delaiAlerte =
                produit.getDetailLogistique().getDelaiAlerteAvtDateExpiration();

            final Calendar calendar = Calendar.getInstance(EclipseConstants.LOCALE);
            calendar.setTime(datePeremption.getTime());
            if (delaiAlerte != null)
            {
                calendar.add(Calendar.DAY_OF_MONTH,
                             -delaiAlerte);
            }
            final Calendar now = Calendar.getInstance(EclipseConstants.LOCALE);

            if (calendar.before(now))
            {
                result = Boolean.TRUE;
            }
        }
        return result;
    }

    /**
     * Méthode en charge d'ajouter un document d'appro.
     */
    public void addDocAppro()
    {
        if (!this.managerDocAppro.canCreateDocument())
        {
            this.managerDocAppro.resetFormDatas();
            return;
        }
        final DocumentStock doc = this.managerDocAppro.createDocument(this.getBeanSelected());

        BeanTool.setPropriete(this.getBeanSelected(),
                              TypeDocumentStock.APPRO.getPropriete(),
                              doc);
    }

    /**
     * Méthode en charge de supprimer un document d'appro.
     * @param event Evenement.
     */
    public void delDocAppro(final ActionEvent event)
    {
        final DocumentStock doc =
            (DocumentStock) BeanTool.getPropriete(this.getBeanSelected(),
                                                  TypeDocumentStock.APPRO.getPropriete());

        BeanTool.setPropriete(this.getBeanSelected(),
                              TypeDocumentStock.APPRO.getPropriete(),
                              null);

        // Pour supprimer le document en BDD car le orphan removal de fonctionne pas...
        if (doc.getId() != null)
        {
            this.documentService.remove(doc);
        }
    }

    public void addHistorique()
    {
        String s = "";
        if (this.getBeanSelected().getHistoriqueExtensionPeremption() != null)
        {
            s += this.getBeanSelected().getHistoriqueExtensionPeremption()
                 + EclipseConstants.SAUT_LIGNE;
        }
        s += "Péremption changée le "
             + Utils.formatDate(Calendar.getInstance(EclipseConstants.LOCALE).getTime(),
                                EclipseConstants.PATTERN_SIMPLE)
             + " : de "
             + Utils.formatDate(this.ancienneDatePeremption.getTime(),
                                EclipseConstants.PATTERN_SIMPLE)
             + " à "
             + Utils.formatDate(this.getBeanSelected().getDatePeremption().getTime(),
                                EclipseConstants.PATTERN_SIMPLE);
        this.getBeanSelected().setHistoriqueExtensionPeremption(s);
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
     * Getter pour conditionnements.
     * @return Le conditionnements
     */
    public List<Conditionnement> getConditionnements()
    {
        return this.conditionnements;
    }

    /**
     * Setter pour conditionnements.
     * @param conditionnements Le conditionnements à écrire.
     */
    public void setConditionnements(final List<Conditionnement> conditionnements)
    {
        this.conditionnements = conditionnements;
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
     * Getter pour managerDocAppro.
     * @return Le managerDocAppro
     */
    public GenericStockManager<DocumentAppro> getManagerDocAppro()
    {
        return this.managerDocAppro;
    }

    /**
     * Setter pour documentService.
     * @param documentService Le documentService à écrire.
     */
    public void setDocumentService(final DocumentService documentService)
    {
        this.documentService = documentService;
    }

    /**
     * Setter pour managerDocAppro.
     * @param managerDocAppro Le managerDocAppro à écrire.
     */
    public void setManagerDocAppro(final GenericStockManager<DocumentAppro> managerDocAppro)
    {
        this.managerDocAppro = managerDocAppro;
    }

    /**
     * Getter pour ancienneDatePeremption.
     * @return Le ancienneDatePeremption
     */
    public Calendar getAncienneDatePeremption()
    {
        return this.ancienneDatePeremption;
    }

    /**
     * Setter pour ancienneDatePeremption.
     * @param ancienneDatePeremption Le ancienneDatePeremption à écrire.
     */
    public void setAncienneDatePeremption(final Calendar ancienneDatePeremption)
    {
        this.ancienneDatePeremption = ancienneDatePeremption;
    }

}

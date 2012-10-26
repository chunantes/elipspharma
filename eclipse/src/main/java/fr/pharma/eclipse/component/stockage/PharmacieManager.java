package fr.pharma.eclipse.component.stockage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.TreeNode;

import fr.pharma.eclipse.component.BeanManager;
import fr.pharma.eclipse.component.stockage.helper.TreeStockageHelper;
import fr.pharma.eclipse.component.stockage.validator.StockageValidator;
import fr.pharma.eclipse.domain.criteria.localisation.SiteSearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Site;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.localisation.SiteService;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.service.stockage.StockageService;
import fr.pharma.eclipse.utils.FacesUtils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Manager de Pharmacie.
 
 * @version $Revision$ $Date$
 */
public class PharmacieManager
    extends BeanManager<Pharmacie>
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3145494941459001312L;

    /**
     * Service de gestion des pharmacies.
     */
    private final PharmacieService pharmacieService;

    /**
     * Service de gestion des sites.
     */
    @Resource(name = "siteService")
    private SiteService siteService;

    /**
     * Service de gestion des stockages.
     */
    @Resource(name = "stockageService")
    private StockageService stockageService;

    /**
     * Validator de stockage.
     */
    @Resource(name = "stockageValidator")
    private StockageValidator stockageValidator;

    /**
     * Helper de gestion de stockage sous forme d'arbre.
     */
    @Resource(name = "treeStockageHelper")
    private TreeStockageHelper stockageHelper;

    /**
     * Liste des sites sélectionnables.
     */
    private List<Site> sitesSelectable;

    /**
     * Object TreeNode pour les stockages.
     */
    private TreeNode root;

    /**
     * Index de l'onglet courant.
     */
    private int indexOngletCourant;

    /**
     * Stockage courant (ajout / modif de stockage).
     */
    private Stockage stockageCurrent;

    /**
     * Action sur le stockage courant (ADD ou EDIT).
     */
    private String actionStockageCurrent;

    /**
     * Identifiant des nodes à expanded.
     */
    private String idsNodesToExpand;

    /**
     * Stockage servant de base pour la gestion de l'ouverture des noeuds.
     */
    private Stockage stockageDisplay;

    /**
     * Faces Utils.
     */
    @Resource(name = "facesUtils")
    private FacesUtils facesUtils;

    /**
     * Map de description des onglets de Pharmacie.
     */
    protected static final Map<String, Integer> INFOS_ONGLETS = new HashMap<String, Integer>();
    {
        PharmacieManager.INFOS_ONGLETS.put("ONG_DESCRIPTION",
                                           0);
        PharmacieManager.INFOS_ONGLETS.put("ONG_STOCKAGE",
                                           1);
        PharmacieManager.INFOS_ONGLETS.put("ONG_PHARMACIEN",
                                           2);
    };

    /**
     * Constructeur.
     * @param pharmacieService Service de gestion de pharmacie.
     */
    public PharmacieManager(final PharmacieService pharmacieService)
    {
        super(pharmacieService);
        this.pharmacieService = pharmacieService;
    }

    /**
     * Getter sur sitesSelectable.
     * @return Retourne le sitesSelectable.
     */
    public List<Site> getSitesSelectable()
    {
        // Récupération de l'établissement
        final Pharmacie pharmacie = this.getBean();
        final SiteSearchCriteria siteCriteria = new SiteSearchCriteria();
        siteCriteria.setActiveOrder("nom");
        if (pharmacie.getEtablissement() != null)
        {
            siteCriteria.setEtablissement(pharmacie.getEtablissement());
        }
        this.sitesSelectable = this.siteService.getAll(siteCriteria);
        final SortedSet<Site> sitesPharma = pharmacie.getSites();

        // Valorisation du champ Selected en fonction des sites actuels associés à la pharmacie
        this.getHelper().updateSelectable(sitesPharma,
                                          this.sitesSelectable);
        return this.sitesSelectable;
    }

    /**
     * Méthode en charge de mettre à jour les sites de la pharmacie.
     */
    public void updateSites()
    {
        final Pharmacie pharmacie = this.getBean();
        this.getHelper().updateSelected(pharmacie.getSites(),
                                        this.sitesSelectable);
    }

    /**
     * Méthode en charge de vider la liste des sites de la pharmacie.
     */
    public void clearSites()
    {
        this.getBean().getSites().clear();
    }

    /**
     * Getter pour root.
     * @return Le root
     */
    public TreeNode getRoot()
    {
        // Construction des données de l'arbre
        this.root = this.stockageHelper.buildTree(this.getBean());

        // Construction de la liste des noeuds à expanded pour l'affichage
        if (this.stockageDisplay != null)
        {
            this.idsNodesToExpand =
                this.stockageHelper.calculateNodesToExpand(this.root,
                                                           this.stockageDisplay);
            this.reverseIdsNodesToExpand();
        }
        else
        {
            this.idsNodesToExpand = null;
        }

        return this.root;
    }

    /**
     * Listener appelé lorsque l'utilisateur change d'onglet.
     * @param event Evénement remonté par le composant primeFaces.
     */
    public void onOngletChange(final TabChangeEvent event)
    {
        final String tabId = event.getTab().getId();
        this.setIndexOngletCourant(PharmacieManager.INFOS_ONGLETS.get(tabId));
    }

    /**
     * Méthode en charge d'ajouter un stockage avec parent.
     * @param event Evénement.
     */
    public void addStockage(final ActionEvent event)
    {
        final Stockage stockageParent =
            (Stockage) event.getComponent().getAttributes().get("stockageParent");
        final Stockage stockage = new Stockage();
        stockage.setPharmacie(this.getBean());
        stockage.setParent(stockageParent);
        this.stockageCurrent = stockage;
        this.actionStockageCurrent = "ADD";
        if (stockageParent == null)
        {
            this.stockageDisplay = null;
        }
        else
        {
            stockage.setConservation(stockageParent.getConservation());
            this.stockageDisplay = this.stockageCurrent;
        }
    }

    /**
     * Méthode en charge d'éditer un stockage.
     * @param event Evénement.
     */
    public void editStockage(final ActionEvent event)
    {
        final Stockage stockage =
            (Stockage) event.getComponent().getAttributes().get("stockageCurrent");
        this.stockageCurrent = stockage;
        this.actionStockageCurrent = "EDIT";
        this.stockageDisplay = this.stockageCurrent;
    }

    /**
     * Méthode en charge de supprimer un stockage.
     * @param event Evénement.
     */
    public void delStockage(final ActionEvent event)
    {
        final Stockage stockage =
            (Stockage) event.getComponent().getAttributes().get("stockageToDelete");
        try
        {
            this.pharmacieService.removeStockage(this.getBean(),
                                                 stockage);
            if (stockage.getParent() != null)
            {
                this.stockageDisplay = stockage.getParent();
            }
            else
            {
                this.stockageDisplay = null;
            }
        }
        catch (final ValidationException e)
        {
            this.facesUtils.addMessage(FacesMessage.SEVERITY_ERROR,
                                       e.getErrorCode()
                                               + "."
                                               + e.getValues()[0]);
        }
    }

    /**
     * Méthode en charge de mettre à jour la liste des stockages de la pharmacie.
     */
    public void updateStockages()
    {
        // Validation du stockage
        final boolean valid =
            this.stockageValidator.validateStockage(this.stockageCurrent);

        if (valid)
        {
            final SortedSet<Stockage> stockagesRoot = this.getBean().getStockages();

            // Dans le cas d'un ajout de stockage => ajout à la liste des stockages de la
            // pharmacie
            if ("ADD".equals(this.actionStockageCurrent))
            {
                if (this.stockageCurrent.getParent() == null)
                {
                    stockagesRoot.add(this.stockageCurrent);
                }
                else
                {
                    final Stockage stockageParent = this.stockageCurrent.getParent();
                    stockageParent.getEnfants().add(this.stockageCurrent);
                    stockagesRoot.add(this.stockageCurrent);
                }

                this.setStockageCurrent(this.stockageService.save(this.stockageCurrent));
            }
            else
            {
                // MAJ du stockage
                this.setStockageCurrent(this.stockageService.save(this.stockageCurrent));

                // NB : nous somme obligés de setter les valeurs manuellement, car pour une raison
                // qui nous échappe (sans doute un bug du composant primefaces "treeTable"), nous
                // perdons la référence sur le stockage courant.
                this.updateStockageEdit(stockagesRoot,
                                        this.stockageCurrent);
            }
        }
    }

    /**
     * MAJ du stockage (EDIT)
     * @param stockages
     * @param stockageCurrent
     */
    protected void updateStockageEdit(final SortedSet<Stockage> stockages,
                                      final Stockage stockageCurrent)
    {
        for (final Stockage stockage : stockages)
        {
            if (stockage.getId() == null
                || stockage.getId().longValue() == stockageCurrent.getId().longValue())
            {
                stockage.setId(stockageCurrent.getId());
                stockage.setNom(stockageCurrent.getNom());
                stockage.setConservation(stockageCurrent.getConservation());
                stockage.setIdentifiantStockage(stockageCurrent.getIdentifiantStockage());
                stockage.setIdentifiantSondeTemp(stockageCurrent.getIdentifiantSondeTemp());
                stockage.setIdentifiantEnregistreurTemp(stockageCurrent
                        .getIdentifiantEnregistreurTemp());

                break;
            }
            else
            {
                if (stockage.getEnfants().size() > 0)
                {
                    this.updateStockageEdit(stockage.getEnfants(),
                                            stockageCurrent);
                }
            }
        }
    }

    /**
     * Méthode en charge d'afficher un message de confirmation d'inclusion.
     */
    public void confirmEnregistrement()
    {
        this.facesUtils.addMessage(FacesMessage.SEVERITY_INFO,
                                   "pharmacie.enregistrement.ok");
    }

    /**
     * Getter pour stockageCurrent.
     * @return Le stockageCurrent
     */
    public Stockage getStockageCurrent()
    {
        return this.stockageCurrent;
    }

    /**
     * Setter pour stockageCurrent.
     * @param stockageCurrent Le stockageCurrent à écrire.
     */
    public void setStockageCurrent(final Stockage stockageCurrent)
    {
        this.stockageCurrent = stockageCurrent;
    }

    /**
     * Getter pour idsNodesToExpand.
     * @return Le idsNodesToExpand
     */
    public String getIdsNodesToExpand()
    {
        return this.idsNodesToExpand;
    }

    /**
     * Méthode en charge de faire le reverse des noeuds à ouvrir.
     */
    public void reverseIdsNodesToExpand()
    {
        if (StringUtils.isNotEmpty(this.idsNodesToExpand))
        {
            final String[] tab = this.idsNodesToExpand.split(EclipseConstants.COMMA);
            String newString = StringUtils.EMPTY;

            for (int i = tab.length - 1; i >= 0; i--)
            {
                newString += tab[i]
                             + EclipseConstants.COMMA;
            }

            if (newString.length() > 0)
            {
                newString = newString.substring(0,
                                                newString.length() - 1);
            }

            this.idsNodesToExpand = newString;
        }
    }

    /**
     * Setter pour idsNodesToExpand.
     * @param idsNodesToExpand Le idsNodesToExpand à écrire.
     */
    public void setIdsNodesToExpand(final String idsNodesToExpand)
    {
        //
    }

    /**
     * Setter pour stockageDisplay.
     * @param stockageDisplay Le stockageDisplay à écrire.
     */
    public void setStockageDisplay(final Stockage stockageDisplay)
    {
        this.stockageDisplay = stockageDisplay;
    }

    /**
     * Setter pour stockageValidator.
     * @param stockageValidator Le stockageValidator à écrire.
     */
    public void setStockageValidator(final StockageValidator stockageValidator)
    {
        this.stockageValidator = stockageValidator;
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
     * Setter pour siteService.
     * @param siteService le siteService à écrire.
     */
    public void setSiteService(final SiteService siteService)
    {
        this.siteService = siteService;
    }

    /**
     * Setter pour sitesSelectable.
     * @param sitesSelectable le sitesSelectable à écrire.
     */
    public void setSitesSelectable(final List<Site> sitesSelectable)
    {
        this.sitesSelectable = sitesSelectable;
    }

    /**
     * Setter pour stockageHelper.
     * @param stockageHelper Le stockageHelper à écrire.
     */
    public void setStockageHelper(final TreeStockageHelper stockageHelper)
    {
        this.stockageHelper = stockageHelper;
    }

    /**
     * Setter pour actionStockageCurrent.
     * @param actionStockageCurrent Le actionStockageCurrent à écrire.
     */
    public void setActionStockageCurrent(final String actionStockageCurrent)
    {
        this.actionStockageCurrent = actionStockageCurrent;
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
     * Setter pour stockageService.
     * @param stockageService Le stockageService à écrire.
     */
    public void setStockageService(final StockageService stockageService)
    {
        this.stockageService = stockageService;
    }

}

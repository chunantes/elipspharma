package fr.pharma.eclipse.component.dispensation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.event.TabChangeEvent;

import fr.pharma.eclipse.component.BeanManager;
import fr.pharma.eclipse.component.dispensation.helper.DispensationManagerHelper;
import fr.pharma.eclipse.component.stock.SortieManager;
import fr.pharma.eclipse.domain.enums.TypeDispensation;
import fr.pharma.eclipse.domain.enums.TypeElementToCheck;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.dispensation.ElementToCheck;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.ResultSortie;
import fr.pharma.eclipse.domain.model.stock.Sortie;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.user.UserService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Manager pour l'écran de dispensation.
 
 * @version $Revision$ $Date$
 */
public class DispensationManager
    extends BeanManager<Dispensation>
    implements Serializable
{
    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 2613094893022115520L;

    /**
     * Nombre de jours avant la péremtpion.
     */
    private static final int NB_JOURS_PEREMPTION = 15;

    /**
     * ReadOnly.
     */
    private Boolean readOnly;

    /**
     * Index de l'onglet courant.
     */
    private int indexOngletCourant;

    /**
     * Produit prescrit.
     */
    private ProduitPrescrit produitPrescritCurrent;

    /**
     * Sortie Manager.
     */
    @Resource(name = "sortieManager")
    private SortieManager sortieManager;

    /**
     * Helper du manager DispensationManager.
     */
    @Resource(name = "dispensationManagerHelper")
    private DispensationManagerHelper dispensationManagerHelper;

    /**
     * Liste des pharmacies de l'utilisateur.
     */
    private List<Pharmacie> pharmacies;

    /**
     * Service de gestion des essais.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * Service de gestion des utilisateurs.
     */
    @Resource(name = "userService")
    private UserService userService;

    /**
     * Sorties liées à un produit prescrit. Cette map permet d'afficher les sorties liées à un
     * produit prescrit.
     */
    private final Map<Long, List<Sortie>> sortiesParProduit = new HashMap<Long, List<Sortie>>();

    /**
     * Sorties courantes.
     */
    private List<Sortie> sortiesCurrent = new ArrayList<Sortie>();

    /**
     * Map de description des onglets de dispensation.
     */
    protected static final Map<String, Integer> INFOS_ONGLETS = new HashMap<String, Integer>();
    {
        DispensationManager.INFOS_ONGLETS.put("ONG_INFORMATION",
                                              0);
        DispensationManager.INFOS_ONGLETS.put("ONG_PRODUITS_PRESCRITS",
                                              1);
    };

    /**
     * Constructeur.
     * @param service Service dispensation.
     */
    public DispensationManager(final GenericService<Dispensation> service)
    {
        super(service);
    }

    /**
     * Méthode d'intitialisation.
     */
    public void init()
    {
        this.sortieManager.init();
        this.setProduitPrescritCurrent(null);
        this.setBean(null);
        this.sortiesParProduit.clear();
        this.sortiesCurrent.clear();
        this.setReadOnly(false);
    }

    /**
     * Méthode en charge d'initialiser les pharmacies d'une dispensation.
     */
    public void initPharmacies()
    {
        final Essai essai = this.getBean().getPrescription().getInclusion().getEssai();
        // Initialisation des pharmacies
        final List<Pharmacie> results =
            this.essaiService.getAllPharmaciesOfUser(this.essaiService.get(essai.getId()));
        this.setPharmacies(results);
        if (results.size() == 1)
        {
            this.getBean().setPharmacie(results.get(0));
        }
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
        this.getBean().setPharmacie(pharmacie);
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'un utilisateur coche/décoche la case à cocher de
     * reconstitution simple.
     * @param event Evénement.
     */
    public void handleCaseCheckedReconstitutionSimple(final ValueChangeEvent event)
    {
        final HtmlSelectBooleanCheckbox caseChecked =
            (HtmlSelectBooleanCheckbox) event.getSource();

        final boolean checked = (Boolean) caseChecked.getLocalValue();

        final String idRacine = "caseReconstitutionSimple_";
        final Long idProduitPrescrit =
            Long.valueOf(caseChecked.getId().substring(idRacine.length()));

        final ElementToCheck elt =
            this.getElementToCheck(idProduitPrescrit,
                                   TypeElementToCheck.RECONSTITUTION_SIMPLE.getLibelle());

        this.handleCaseChecked(elt,
                               checked);
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'un utilisateur coche/décoche la case à cocher de
     * reconstitution PSM.
     * @param event Evénement.
     */
    public void handleCaseCheckedReconstitutionPSM(final ValueChangeEvent event)
    {
        final HtmlSelectBooleanCheckbox caseChecked =
            (HtmlSelectBooleanCheckbox) event.getSource();

        final boolean checked = (Boolean) caseChecked.getLocalValue();

        final String idRacine = "caseReconstitutionPSM_";
        final Long idProduitPrescrit =
            Long.valueOf(caseChecked.getId().substring(idRacine.length()));

        final ElementToCheck elt =
            this.getElementToCheck(idProduitPrescrit,
                                   TypeElementToCheck.RECONSTITUTION_PSM.getLibelle());

        this.handleCaseChecked(elt,
                               checked);
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'un utilisateur coche/décoche la case à cocher de
     * fabrication.
     * @param event Evénement.
     */
    public void handleCaseCheckedFabrication(final ValueChangeEvent event)
    {
        final HtmlSelectBooleanCheckbox caseChecked =
            (HtmlSelectBooleanCheckbox) event.getSource();

        final boolean checked = (Boolean) caseChecked.getLocalValue();

        final String idRacine = "caseFabrication_";
        final Long idProduitPrescrit =
            Long.valueOf(caseChecked.getId().substring(idRacine.length()));

        final ElementToCheck elt =
            this.getElementToCheck(idProduitPrescrit,
                                   TypeElementToCheck.FABRICATION.getLibelle());

        this.handleCaseChecked(elt,
                               checked);
    }

    /**
     * Méthode en charge de récupérer un ElementToCheck à partir de l'identifiant d'un produit
     * prescrit et du nom du champ.
     * @param idProduitPrescrit Identifiant du produit prescrit.
     * @param nomChamp Nom du champ.
     * @return ElementToCheck.
     */
    public ElementToCheck getElementToCheck(final Long idProduitPrescrit,
                                            final String nomChamp)
    {
        @SuppressWarnings("unchecked")
        final List<ElementToCheck> liste =
            (List<ElementToCheck>) CollectionUtils
                    .select(this.getBean().getElementsToCheck(),
                            new GenericPredicate("produitPrescrit.id",
                                                 idProduitPrescrit));

        // Récupération de ElementToCheck
        return (ElementToCheck) CollectionUtils.find(liste,
                                                     new GenericPredicate("nomChamps",
                                                                          nomChamp));
    }

    /**
     * Méthode en charge de gérer les opérations à effectuer sur une case à cocher pour un
     * élement.
     * @param elt ElementToCheck.
     * @param checked Coché/Décoché sur la case à cocher.
     */
    private void handleCaseChecked(final ElementToCheck elt,
                                   final Boolean checked)
    {
        if (checked)
        {
            elt.setDateChecked(Calendar.getInstance(EclipseConstants.LOCALE));
            elt.setCheckedBy(this.userService.getPersonne());
        }
        else
        {
            elt.setDateChecked(null);
            elt.setCheckedBy(null);
            elt.setCommentaire(null);
        }
    }

    /**
     * Méthode en charge d'intialiser le manager de sortie avec les données du produitPrescrit.
     * @param produit ProduitPrescrit.
     */
    public void initSortieManager(final ProduitPrescrit produit)
    {
        this.setProduitPrescritCurrent(produit);
        this.sortieManager.setEssaiSelected(this
                .getBean()
                .getPrescription()
                .getInclusion()
                .getEssai());
        this.sortieManager.setPharmacieSelected(this.getBean().getPharmacie());
        this.sortieManager.addSortie();
        // initialisaton du produitpresctit dans le mouvement pour le récupérer à
        // l'enregistrement.
        ((DispensationProduit) this.sortieManager.getSortieCurrent().getMvtSortie())
                .setProduitPrescrit(produit);
        this.sortieManager.getSortieCurrent().getMvtSortie().setProduit(produit.getProduit());

        this.dispensationManagerHelper.initConditionnements(this.sortieManager,
                                                            this.produitPrescritCurrent);
    }

    public boolean isDispensationGlobale()
    {
        return this
                .getBean()
                .getEssai()
                .getDetailDonneesPharma()
                .getInfosDispensations()
                .getTypeDispensation()
                .equals(TypeDispensation.GLOBALE);
    }

    /**
     * Méthode en charge de copier les sorties dans le sortieManager avant l'enregistrement des
     * sorties.
     */
    public void setSortieManagerBeforeSave()
    {
        this.sortieManager.getSorties().clear();
        for (final Long key : this.sortiesParProduit.keySet())
        {
            this.sortieManager.getSorties().addAll(this.sortiesParProduit.get(key));
            this.getBean().setProduitDispense(key);
        }
    }
    /**
     * {@inheritDoc}
     */
    public void handleSelectConditionnement(final AjaxBehaviorEvent event)
    {
        this.sortieManager.handleSelectConditionnement(event);
        this.sortieManager
                .getSortieCurrent()
                .filtrerLignesStockParNumeroTraitement(this.produitPrescritCurrent.getNumTraitement());
    }

    /**
     * Méthode en charge d'ajouter la sortie courante a la liste des sorties. Elle set également
     * le produitPrescrit comme dispensé.
     */
    public void addSortieToSorties()
    {
        this.sortieManager.addSortieToSorties();
        final ProduitPrescrit current = this.getProduitPrescritCurrent();
        current.setDispense(true);
        if (!this.sortiesParProduit.containsKey(this.produitPrescritCurrent.getId()))
        {
            this.sortiesParProduit.put(this.produitPrescritCurrent.getId(),
                                       new ArrayList<Sortie>());
        }
        this.sortiesParProduit.get(current.getId()).add(this.sortieManager.getSortieCurrent());
        this.setProduitPrescritCurrent(null);
    }

    /**
     * Retourne <true> si le produit courant a déjà été dispensé, il est donc dans une des deux
     * map de gestion des mouvements.
     * @param produit Le produit.
     * @return <true> si le produit courant a déjà été dispensé.
     */
    public boolean isProduitDispense(final ProduitPrescrit produit)
    {
        if (produit != null
            && this.sortiesParProduit.containsKey(produit.getId()))
        {
            if (!this.sortiesParProduit.get(produit.getId()).isEmpty())
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode en charge d'initialiser la map de dispensations produit pour l'affichage par
     * produit. Elle est appelée par le flow lors de la consultation en readOnly d'une
     * dispensation.
     */
    public void initProduitDispenses()
    {
        if (this.getBean().getId() != null)
        {
            this.dispensationManagerHelper
                    .initProduitsDispensesForConsult(this.getBean(),
                                                     this.sortiesParProduit);
        }
    }

    /**
     * Méthode appelée par l'IHM afin de supprimer la sortie contenu dans la variable
     * sortieToDelete.
     */
    public void delSortie()
    {
        this.sortiesParProduit
                .get(((DispensationProduit) this.getSortieToDelete().getMvtSortie())
                        .getProduitPrescrit()
                        .getId()).remove(this.getSortieToDelete());
        this.sortieManager.delSortie();
    }

    /**
     * Méthode en charge de vérifier que la prescription correspondante à la dispensation en cours
     * ne contient aucun dispensations.
     */
    public boolean modifyProduitsPrescrit()
    {
        return this.getBean().getPrescription().getDispensations().isEmpty();

    }

    /** ******************************* **/
    /** GESTION DE L'ONGLET COURANT **/
    /** ******************************* **/

    /**
     * Listener appelé lorsque l'utilisateur change d'onglet.
     * @param event Evénement remonté par le composant primeFaces.
     */
    public void onOngletChange(final TabChangeEvent event)
    {
        final String tabId = event.getTab().getId();
        this.setIndexOngletCourant(DispensationManager.INFOS_ONGLETS.get(tabId));
    }

    /**
     * Méthode en charge de fournir un élément à vérifier en fonction du produit prescrit et du
     * nom du champs. S'il n'est pas à vérifier alors la méthode retourne null.
     * @param produitPrescrit ProduitPrescrit.
     * @param toCheck Nom de l'élément à vérifier.
     * @return La liste des éléments à vérifier.
     */
    public ElementToCheck getElementToCheck(final ProduitPrescrit produitPrescrit,
                                            final String toCheck)
    {
        @SuppressWarnings("unchecked")
        final List<ElementToCheck> liste =
            (List<ElementToCheck>) CollectionUtils
                    .select(this.getBean().getElementsToCheck(),
                            new GenericPredicate("produitPrescrit.id",
                                                 produitPrescrit.getId()));
        if (!liste.isEmpty())
        {
            return (ElementToCheck) CollectionUtils.find(liste,
                                                         new GenericPredicate("nomChamps",
                                                                              toCheck));
        }
        return null;
    }

    /**
     * Retourne <true> lorsque tous les elements à vérifier sont checké.
     * @return <true> lorsque tous les elements à vérifier sont checké.
     */
    public boolean isDispensable()
    {
        for (final ElementToCheck element : this.getBean().getElementsToCheck())
        {
            if (!element.getChecked())
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Méthode en charge de préprarer les resultats de sorties pour le récapitulatif.
     */
    public void prepareRecapFromDispensation()
    {
        // On recopie les infos de la dispensation dans le bean récapitulatif.
        final ResultSortie result = new ResultSortie();
        result.setEssai(this.getBean().getPrescription().getInclusion().getEssai());
        result.setPharmacie(this.getBean().getPharmacie());
        result.setPromoteur(this
                .getBean()
                .getPrescription()
                .getInclusion()
                .getEssai()
                .getPromoteur());
        result.setMvts(new ArrayList<DispensationProduit>(this
                .getBean()
                .getDispensationsProduit()));
        result.setDateSortie(this.getBean().getDateDispensation());
        if (!this.getBean().getDispensationsProduit().isEmpty())
        {
            result.setPersonne(this.getBean().getDispensationsProduit().first().getPersonne());
        }

        // On recopie les sorties initialisé dans la map par produit dans le result.
        for (final List<Sortie> sorties : this.sortiesParProduit.values())
        {
            result.getSorties().addAll(sorties);
        }
        this.sortieManager.setResult(result);
    }

    public void initSortiesForProduitPrescrit(final ProduitPrescrit produit)
    {
        this.setProduitPrescritCurrent(produit);
        this.sortiesCurrent = this.sortiesParProduit.get(produit.getId());
    }

    // Méthodes liés à la gestion des produits prescrit :

    /**
     * Méthode en charge d'ajouter un produit prescrit à la liste existante.
     * @param Le produit prescrit.
     */
    public void addProduitPrescrit(final ProduitPrescrit produitPrescrit)
    {
        final List<ProduitPrescrit> produits =
            new ArrayList<ProduitPrescrit>(this
                    .getBean()
                    .getPrescription()
                    .getProduitsPrescrits());

        if (produits.contains(produitPrescrit))
        {
            throw new ValidationException("dispensation.produitPrescrit",
                                          new String[]
                                          {"exist" },
                                          produitPrescrit);
        }
        this.getBean().getPrescription().getProduitsPrescrits().add(produitPrescrit);
    }

    /**
     * Retourne true si la ligne est périmée.
     * @param ligne Ligne de stock.
     * @return true si la ligne est périmée.
     */
    public boolean isPerime(final LigneStock ligne)
    {
        // la date de péremption doit être saisie.
        if (ligne.getDatePeremption() != null)
        {
            final Calendar c = Calendar.getInstance(EclipseConstants.LOCALE);
            if (this.produitPrescritCurrent != null
                && this.produitPrescritCurrent.getDuree() != null
                && this.produitPrescritCurrent.getDuree().getNb() != null
                && this.produitPrescritCurrent.getDuree().getUnite() != null)
            {
                c.add(this.produitPrescritCurrent
                              .getDuree()
                              .getUnite()
                              .getCalendarProperty()
                              .intValue(),
                      this.produitPrescritCurrent.getDuree().getNb());
            }
            c.add(Calendar.DAY_OF_YEAR,
                  DispensationManager.NB_JOURS_PEREMPTION);
            return ligne.getDatePeremption().before(c);
        }
        return false;
    }
    /**
     * Retourne <true> si la dispensation est enregistrable, c'est à dire si aucune sortie de
     * stock n'a été définie.
     * @return <true> si la dispensation est enregistrable, c'est à dire si aucune sortie de stock
     * n'a été définie.
     */
    public boolean isEnregistrable()
    {
        return this.sortiesParProduit.isEmpty();
    }

    /**
     * Setter sortieToDelete.
     * @param sortieToDelete Sortie à supprimer.
     */
    public void setSortieToDelete(final Sortie sortieToDelete)
    {
        this.sortieManager.setSortieToDelete(sortieToDelete);
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
     * Getter sur produitPrescritCurrent.
     * @return Retourne le produitPrescritCurrent.
     */
    public ProduitPrescrit getProduitPrescritCurrent()
    {
        return this.produitPrescritCurrent;
    }

    /**
     * Setter pour produitPrescritCurrent.
     * @param produitPrescritCurrent le produitPrescritCurrent à écrire.
     */
    public void setProduitPrescritCurrent(final ProduitPrescrit produitPrescritCurrent)
    {
        this.produitPrescritCurrent = produitPrescritCurrent;
    }

    /**
     * Setter pour sortieManager.
     * @param sortieManager le sortieManager à écrire.
     */
    public void setSortieManager(final SortieManager sortieManager)
    {
        this.sortieManager = sortieManager;
    }

    /**
     * Setter pour dispensationManagerHelper.
     * @param dispensationManagerHelper le dispensationManagerHelper à écrire.
     */
    public void setDispensationManagerHelper(final DispensationManagerHelper dispensationManagerHelper)
    {
        this.dispensationManagerHelper = dispensationManagerHelper;
    }

    /**
     * @return Retourne le map "sortiesParProduit"
     */
    public Map<Long, List<Sortie>> getSortiesParProduit()
    {
        return this.sortiesParProduit;
    }

    /**
     * Getter sur sortieToDelete.
     * @return Retourne le sortieToDelete.
     */
    public Sortie getSortieToDelete()
    {
        return this.sortieManager.getSortieToDelete();
    }

    /**
     * Getter sur indexOngletCourant.
     * @return Retourne le indexOngletCourant.
     */
    public int getIndexOngletCourant()
    {
        return this.indexOngletCourant;
    }

    /**
     * Setter pour indexOngletCourant.
     * @param indexOngletCourant le indexOngletCourant à écrire.
     */
    public void setIndexOngletCourant(final int indexOngletCourant)
    {
        this.indexOngletCourant = indexOngletCourant;
    }

    /**
     * Getter pour pharmacies.
     * @return Le pharmacies
     */
    public List<Pharmacie> getPharmacies()
    {
        return this.pharmacies;
    }

    /**
     * Setter pour pharmacies.
     * @param pharmacies Le pharmacies à écrire.
     */
    public void setPharmacies(final List<Pharmacie> pharmacies)
    {
        this.pharmacies = pharmacies;
    }

    /**
     * Setter pour essaiService.
     * @param essaiService Le essaiService à écrire.
     */
    public void setEssaiService(final EssaiService essaiService)
    {
        this.essaiService = essaiService;
    }

    /**
     * Setter pour userService.
     * @param userService Le userService à écrire.
     */
    public void setUserService(final UserService userService)
    {
        this.userService = userService;
    }

    /**
     * Getter sur sortiesCurrent.
     * @return Retourne le sortiesCurrent.
     */
    public List<Sortie> getSortiesCurrent()
    {
        return this.sortiesCurrent;
    }

    /**
     * Setter pour sortiesCurrent.
     * @param sortiesCurrent le sortiesCurrent à écrire.
     */
    public void setSortiesCurrent(final List<Sortie> sortiesCurrent)
    {
        this.sortiesCurrent = sortiesCurrent;
    }

}

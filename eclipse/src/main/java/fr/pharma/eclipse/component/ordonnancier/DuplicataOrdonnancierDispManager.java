package fr.pharma.eclipse.component.ordonnancier;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import fr.pharma.eclipse.component.BeansManager;
import fr.pharma.eclipse.domain.criteria.ordonnancier.OrdonnancierSearchCriteria;
import fr.pharma.eclipse.domain.criteria.stockage.PharmacieSearchCriteria;
import fr.pharma.eclipse.domain.enums.TypeAnonymisation;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.ordonnancier.OrdonnancierDisp;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.dispensation.DispensationService;
import fr.pharma.eclipse.service.ordonnancier.OrdonnancierService;
import fr.pharma.eclipse.service.stockage.PharmacieService;

/**
 * Manager de duplicata d'ordonnancier de dispensation.
 
 * @version $Revision$ $Date$
 */
public class DuplicataOrdonnancierDispManager
    extends BeansManager<OrdonnancierDisp>
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 8971955482392007865L;

    /**
     * Liste des pharmacies de l'utilisateur.
     */
    private List<Pharmacie> pharmacies;

    /**
     * Critère de recherche sur Ordonnancier.
     */
    private final OrdonnancierSearchCriteria criteria;

    /**
     * Service de gestion des pharmacies.
     */
    @Resource(name = "pharmacieService")
    private PharmacieService pharmacieService;

    /**
     * Service de gestion des ordonnanciers de dispensation.
     */
    @Resource(name = "ordonnancierDispService")
    private OrdonnancierService<OrdonnancierDisp> ordoService;

    /**
     * Service de gestion des dispensations.
     */
    @Resource(name = "dispensationService")
    private DispensationService dispensationService;

    /**
     * Ordonnancier choisi pour le duplicata.
     */
    private OrdonnancierDisp ordonnancierSelected;

    /**
     * Type d'anonymisation.
     */
    private TypeAnonymisation typeAnonymisation;

    /**
     * Constructeur.
     * @param criteria Critère de recherche.
     */
    public DuplicataOrdonnancierDispManager(final OrdonnancierSearchCriteria criteria)
    {
        super(criteria);
        this.criteria = criteria;
    }

    /**
     * Méthode en charge d'initialiser les données de pharmacies.
     */
    public void init()
    {
        final PharmacieSearchCriteria critPharma = new PharmacieSearchCriteria();
        critPharma.setActiveOrder("nom");
        this.pharmacies = this.pharmacieService.getAll(critPharma);

        // Si une seule pharmacie => récupération des ordonnanciers de cette pharmacie
        if (this.pharmacies.size() == 1)
        {
            final Pharmacie pharmacie = this.pharmacies.get(0);
            this.criteria.setPharmacie(pharmacie);
            this.setBeans(this.ordoService.getAll(this.criteria));
        }
        this.setOrdonnancierSelected(null);
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
        if (pharmacie != null)
        {
            this.setBeans(this.ordoService.getAll(this.criteria));
        }
        else
        {
            this.setBeans(null);
        }
        this.setOrdonnancierSelected(null);
    }

    /**
     * Getter pour ordonnancierSelected.
     * @return Le ordonnancierSelected
     */
    public OrdonnancierDisp getOrdonnancierSelected()
    {
        return this.ordonnancierSelected;
    }

    /**
     * Setter pour ordonnancierSelected.
     * @param ordonnancierSelected Le ordonnancierSelected à écrire.
     */
    public void setOrdonnancierSelected(final OrdonnancierDisp ordonnancierSelected)
    {
        this.ordonnancierSelected = ordonnancierSelected;
    }

    /**
     * Getter pour criteria.
     * @return Le criteria
     */
    public OrdonnancierSearchCriteria getCriteria()
    {
        return this.criteria;
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
     * Méthode en charge de retourner les dispensations de l'ordonnancier.
     * @return Liste des dispensations.
     */
    public List<Dispensation> getListDispensations()
    {
        final List<Dispensation> result = new ArrayList<Dispensation>();
        if (this.getOrdonnancierSelected() != null)
        {
            result.addAll(this.getOrdonnancierSelected().getDispensations());
        }
        return result;
    }

    /**
     * Méthode en charge de retourner les dispensations de produit pour une dispensation.
     * @param dispensation Dispensation.
     * @return Liste des dispensations de produit.
     */
    public List<DispensationProduit> getListDispensationsProduit(final Dispensation dispensation)
    {
        final Dispensation disp = this.dispensationService.reattach(dispensation);
        final List<DispensationProduit> result = new ArrayList<DispensationProduit>();
        result.addAll(disp.getDispensationsProduit());
        return result;
    }

    /**
     * Setter pour dispensationService.
     * @param dispensationService Le dispensationService à écrire.
     */
    public void setDispensationService(final DispensationService dispensationService)
    {
        this.dispensationService = dispensationService;
    }

    /**
     * Setter pour pharmacieService.
     * @param pharmacieService Le pharmacieService à écrire.
     */
    public void setPharmacieService(final PharmacieService pharmacieService)
    {
        this.pharmacieService = pharmacieService;
    }

    /**
     * Setter pour ordoService.
     * @param ordoService Le ordoService à écrire.
     */
    public void setOrdoService(final OrdonnancierService<OrdonnancierDisp> ordoService)
    {
        this.ordoService = ordoService;
    }

    /**
     * Getter pour typeAnonymisation.
     * @return Le typeAnonymisation
     */
    public TypeAnonymisation getTypeAnonymisation()
    {
        return this.typeAnonymisation;
    }

    /**
     * Setter pour typeAnonymisation.
     * @param typeAnonymisation Le typeAnonymisation à écrire.
     */
    public void setTypeAnonymisation(final TypeAnonymisation typeAnonymisation)
    {
        this.typeAnonymisation = typeAnonymisation;
    }

}

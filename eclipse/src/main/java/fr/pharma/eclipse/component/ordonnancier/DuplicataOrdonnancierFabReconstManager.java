package fr.pharma.eclipse.component.ordonnancier;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import fr.pharma.eclipse.comparator.GenericComparator;
import fr.pharma.eclipse.component.BeanListManager;
import fr.pharma.eclipse.domain.criteria.ordonnancier.OrdonnancierSearchCriteria;
import fr.pharma.eclipse.domain.criteria.stockage.PharmacieSearchCriteria;
import fr.pharma.eclipse.domain.model.ordonnancier.OrdonnancierFabReconst;
import fr.pharma.eclipse.domain.model.stock.PreparationEntree;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.ordonnancier.OrdonnancierService;
import fr.pharma.eclipse.service.stockage.PharmacieService;

/**
 * Manager de duplicata d'ordonnancier de fabrication/reconstitution.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DuplicataOrdonnancierFabReconstManager extends BeanListManager<OrdonnancierFabReconst> {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -7342039212115036167L;

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
     * Service de gestion des ordonnanciers de fabrication/reconstitution.
     */
    @Resource(name = "ordonnancierFabReconstService")
    private OrdonnancierService<OrdonnancierFabReconst> ordoService;

    /**
     * Ordonnancier choisi pour le duplicata.
     */
    private OrdonnancierFabReconst ordonnancierSelected;

    /**
     * Constructeur.
     * @param criteria Critère de recherche.
     */
    public DuplicataOrdonnancierFabReconstManager(final OrdonnancierSearchCriteria criteria) {
        super(criteria);
        this.criteria = criteria;
    }

    /**
     * Méthode en charge d'initialiser les données de pharmacies.
     */
    public void init() {
        final PharmacieSearchCriteria critPharma = new PharmacieSearchCriteria();
        critPharma.setActiveOrder("nom");
        this.pharmacies = this.pharmacieService.getAll(critPharma);

        // Si une seule pharmacie => récupération des ordonnanciers de cette
        // pharmacie
        if (this.pharmacies.size() == 1) {
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
    public void handleSelectPharmacie(final AjaxBehaviorEvent event) {
        // Récupération de la pharmacie sélectionnée
        final HtmlSelectOneMenu select = (HtmlSelectOneMenu) event.getSource();
        final Pharmacie pharmacie = (Pharmacie) select.getLocalValue();
        this.criteria.setPharmacie(pharmacie);
        if (pharmacie != null) {
            this.setBeans(this.ordoService.getAll(this.criteria));
        } else {
            this.setBeans(null);
        }
        this.setOrdonnancierSelected(null);
    }

    /**
     * Getter pour criteria.
     * @return Le criteria
     */
    public OrdonnancierSearchCriteria getCriteria() {
        return this.criteria;
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
     * Méthode en charge de retourner les elementsToCheck de l'ordonnancier.
     * @return Liste des elementsToCheck.
     */
    public List<PreparationEntree> getListElementsToCheck() {
        final SortedSet<PreparationEntree> result = new TreeSet<PreparationEntree>(new GenericComparator<PreparationEntree>("numOrdonnancier"));
        if (this.getOrdonnancierSelected() != null) {
            result.addAll(this.getOrdonnancierSelected().getElementsToCheck());
        }
        return new ArrayList<PreparationEntree>(result);
    }

    /**
     * Setter pour pharmacieService.
     * @param pharmacieService Le pharmacieService à écrire.
     */
    public void setPharmacieService(final PharmacieService pharmacieService) {
        this.pharmacieService = pharmacieService;
    }

    /**
     * Setter pour ordoService.
     * @param ordoService Le ordoService à écrire.
     */
    public void setOrdoService(final OrdonnancierService<OrdonnancierFabReconst> ordoService) {
        this.ordoService = ordoService;
    }

    /**
     * Getter pour ordonnancierSelected.
     * @return Le ordonnancierSelected
     */
    public OrdonnancierFabReconst getOrdonnancierSelected() {
        return this.ordonnancierSelected;
    }

    /**
     * Setter pour ordonnancierSelected.
     * @param ordonnancierSelected Le ordonnancierSelected à écrire.
     */
    public void setOrdonnancierSelected(final OrdonnancierFabReconst ordonnancierSelected) {
        this.ordonnancierSelected = ordonnancierSelected;
    }

}

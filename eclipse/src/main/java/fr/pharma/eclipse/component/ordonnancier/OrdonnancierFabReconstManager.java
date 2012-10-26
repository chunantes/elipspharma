package fr.pharma.eclipse.component.ordonnancier;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.comparator.GenericComparator;
import fr.pharma.eclipse.component.BeansManager;
import fr.pharma.eclipse.domain.criteria.ordonnancier.OrdonnancierSearchCriteria;
import fr.pharma.eclipse.domain.criteria.stockage.PharmacieSearchCriteria;
import fr.pharma.eclipse.domain.model.dispensation.ElementToCheck;
import fr.pharma.eclipse.domain.model.ordonnancier.OrdonnancierFabReconst;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.stock.PreparationEntree;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.ordonnancier.OrdonnancierService;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Manager de gestion des ordonnanciers de fabrications/reconstitutions.
 
 * @version $Revision$ $Date$
 */
public class OrdonnancierFabReconstManager
    extends BeansManager<OrdonnancierFabReconst>
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 986944434286092098L;

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
     * Service de gestion des ordonnanciers de fabrications / reconstitutions.
     */
    @Resource(name = "ordonnancierFabReconstService")
    private OrdonnancierService<OrdonnancierFabReconst> ordoService;

    /**
     * Ordonnancier de fabrication / reconstitution (résultat de la génération de l'ordonnancier).
     */
    private OrdonnancierFabReconst ordonnancier;

    /**
     * Constructeur.
     * @param criteria Critère de recherche.
     */
    public OrdonnancierFabReconstManager(final OrdonnancierSearchCriteria criteria)
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

        if (this.pharmacies.size() >= 1)
        {
            final Pharmacie pharmacie = this.pharmacies.get(0);
            this.criteria.setPharmacie(pharmacie);
            this.getCriteria().setDateDebut(this.ordoService.getDateDebut(pharmacie));
        }

        // Valorisation des dates de début et des dates de fin
        this.getCriteria().setDateFin(this.ordoService.getDateFin());

        this.setOrdonnancier(null);
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
        this.getCriteria().setDateDebut(this.ordoService.getDateDebut(pharmacie));
    }

    /**
     * Méthode en charge de retourner les initiales du patient associées à un ElementToCheck.
     * @param elementToCheck ElementToCheck.
     * @return Initiales du patient.
     */
    public String getInitialesPatient(final ElementToCheck elementToCheck)
    {
        String result = StringUtils.EMPTY;

        final Patient patient =
            elementToCheck.getDispensation().getPrescription().getInclusion().getPatient();
        result += patient.getPrenom().substring(0,
                                                1).toUpperCase()
                  + EclipseConstants.SPACE
                  + patient.getNom().substring(0,
                                               1).toUpperCase();

        return result;
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
     * Setter pour pharmacieService.
     * @param pharmacieService Le pharmacieService à écrire.
     */
    public void setPharmacieService(final PharmacieService pharmacieService)
    {
        this.pharmacieService = pharmacieService;
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
     * Getter pour ordonnancier.
     * @return Le ordonnancier
     */
    public OrdonnancierFabReconst getOrdonnancier()
    {
        return this.ordonnancier;
    }

    /**
     * Setter pour ordonnancier.
     * @param ordonnancier Le ordonnancier à écrire.
     */
    public void setOrdonnancier(final OrdonnancierFabReconst ordonnancier)
    {
        this.ordonnancier = ordonnancier;
    }

    /**
     * Setter pour ordoService.
     * @param ordoService Le ordoService à écrire.
     */
    public void setOrdoService(final OrdonnancierService<OrdonnancierFabReconst> ordoService)
    {
        this.ordoService = ordoService;
    }

    /**
     * Méthode en charge de retourner les elementsToCheck de l'ordonnancier.
     * @return Liste des elementsToCheck.
     */
    public List<PreparationEntree> getListElementsToCheck()
    {
        final SortedSet<PreparationEntree> result =
            new TreeSet<PreparationEntree>(new GenericComparator<PreparationEntree>("numOrdonnancier"));
        if (this.getOrdonnancier() != null)
        {
            result.addAll(this.ordoService
                    .get(this.getOrdonnancier().getId())
                    .getElementsToCheck());
        }
        return new ArrayList<PreparationEntree>(result);
    }

}

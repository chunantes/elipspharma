package fr.pharma.eclipse.component.ordonnancier;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.component.BeansManager;
import fr.pharma.eclipse.domain.criteria.ordonnancier.OrdonnancierSearchCriteria;
import fr.pharma.eclipse.domain.criteria.stockage.PharmacieSearchCriteria;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.ordonnancier.OrdonnancierDisp;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.ordonnancier.OrdonnancierService;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Manager de gestion des ordonnanciers de dispensations.
 
 * @version $Revision$ $Date$
 */
public class OrdonnancierDispManager
    extends BeansManager<OrdonnancierDisp>
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -7163842128084021006L;

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
     * Ordonnancier de dispensation (résultat de la génération de l'ordonnancier).
     */
    private OrdonnancierDisp ordonnancier;

    /**
     * Constructeur.
     * @param criteria Critère de recherche.
     */
    public OrdonnancierDispManager(final OrdonnancierSearchCriteria criteria)
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
     * Méthode en charge de retourner les initiales du patient associées à une dispensation.
     * @param dispensation Dispensation.
     * @return Initiales du patient.
     */
    public String getInitialesPatient(final Dispensation dispensation)
    {
        String result = StringUtils.EMPTY;

        final Patient patient = dispensation.getPrescription().getInclusion().getPatient();
        result += patient.getPrenom().substring(0,
                                                1).toUpperCase()
                  + EclipseConstants.SPACE
                  + patient.getNom().substring(0,
                                               1).toUpperCase();

        return result;
    }

    /**
     * Méthode en charge de retourner les infos du patient associées à une dispensation.
     * @param dispensation Dispensation.
     * @return Initiales du patient.
     */
    public String getFullPatient(final Dispensation dispensation)
    {
        String result = StringUtils.EMPTY;

        final Patient patient = dispensation.getPrescription().getInclusion().getPatient();

        result += patient.getNom()
                  + EclipseConstants.SPACE
                  + patient.getPrenom()
                  + EclipseConstants.DASH
                  + patient.getNumeroIpp();

        if (StringUtils.isNotBlank(patient.getNomJeuneFille()))
        {
            result += EclipseConstants.DASH
                      + patient.getNomJeuneFille();
        }

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
    public OrdonnancierDisp getOrdonnancier()
    {
        return this.ordonnancier;
    }

    /**
     * Setter pour ordonnancier.
     * @param ordonnancier Le ordonnancier à écrire.
     */
    public void setOrdonnancier(final OrdonnancierDisp ordonnancier)
    {
        this.ordonnancier = ordonnancier;
    }

    /**
     * Méthode en charge de retourner les dispensations de l'ordonnancier.
     * @return Liste des dispensations.
     */
    public List<Dispensation> getListDispensations()
    {
        final List<Dispensation> result = new ArrayList<Dispensation>();
        if (this.getOrdonnancier() != null)
        {
            result.addAll(this.getOrdonnancier().getDispensations());
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
        final List<DispensationProduit> result = new ArrayList<DispensationProduit>();
        result.addAll(dispensation.getDispensationsProduit());
        return result;
    }

    /**
     * Setter pour ordoService.
     * @param ordoService Le ordoService à écrire.
     */
    public void setOrdoService(final OrdonnancierService<OrdonnancierDisp> ordoService)
    {
        this.ordoService = ordoService;
    }

}

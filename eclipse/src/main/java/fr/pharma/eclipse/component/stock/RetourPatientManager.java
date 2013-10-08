package fr.pharma.eclipse.component.stock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.collections.CollectionUtils;
import org.primefaces.event.SelectEvent;

import fr.pharma.eclipse.component.BeanManager;
import fr.pharma.eclipse.domain.criteria.dispensation.DispensationSearchCriteria;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.domain.model.stock.RetourPatient;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.dispensation.DispensationService;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.patient.PatientService;
import fr.pharma.eclipse.service.produit.ProduitService;

/**
 * Description de la classe.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class RetourPatientManager extends BeanManager<RetourPatient> {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 4195451369572296260L;

    /**
     * Service Patient.
     */
    @Resource(name = "patientService")
    private PatientService patientService;

    /**
     * RetourPatient après enregistrement.
     */
    private RetourPatient result;

    /**
     * Service produit.
     */
    @Resource(name = "produitService")
    private ProduitService<Produit> produitService;

    /**
     * Essai service.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * Service dispensation.
     */
    @Resource(name = "dispensationService")
    private DispensationService dispensationService;

    /**
     * Essai sélectionné.
     */
    private Essai essaiSelected;

    /**
     * Patient sélectionné.
     */
    private Patient patientSelected;

    /**
     * Produit sélectionné.
     */
    private Produit produitSelected;

    /**
     * Pharmacie sélecitonnée.
     */
    private Pharmacie pharmacieSelected;

    /**
     * Conditionnement selectionné.
     */
    private Conditionnement conditionnementSelected;

    /**
     * Patients.
     */
    private List<Patient> patients;

    /**
     * Produits.
     */
    private List<Produit> produits;

    /**
     * Stockages de retours.
     */
    private List<DetailStockage> stockages;

    /**
     * Pharmacies.
     */
    private List<Pharmacie> pharmacies;

    /**
     * Conditionnement.
     */
    private List<Conditionnement> conditionnements;

    /**
     * Dispensation sélectionnée.
     */
    private DispensationProduit dispensationSelected;

    /**
     * Dispensations.
     */
    private List<Dispensation> dispensations;

    /**
     * Action
     */
    private String action;

    /**
     * Constructeur.
     * @param service Service.
     */
    public RetourPatientManager(final GenericService<RetourPatient> service) {
        super(service);
    }

    /**
     * Méthode en charge de réinitialiser le manager.
     */
    public void init() {
        this.setEssaiSelected(null);
        this.setPatients(null);
        this.setBean(null);
        this.setPatientSelected(null);
        this.setPharmacieSelected(null);
        this.setPharmacies(null);
        this.setProduits(null);
        this.setProduitSelected(null);
        this.conditionnements = null;
        this.setConditionnementSelected(null);
        this.setResult(null);
        this.setStockages(null);
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'un essai est sélectionné.
     * @param event Evénement remonté via la couche IHM.
     */
    public void handleSelectEssai(final SelectEvent event) {

        final List<Patient> patientsDisplay = this.patientService.getAllPatientsForEssai(this.essaiSelected);
        this.setPatients(patientsDisplay);

        this.getBean().setEssai(this.getEssaiSelected());

        final List<Pharmacie> pharmaciesDisplay = this.essaiService.getAllPharmaciesOfUser(this.getEssaiSelected());
        this.setPharmacies(pharmaciesDisplay);

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
     * Méthode appelée via la couche IHM lorsqu'un produit est sélectionné.
     * @param event Event.
     */
    public void handleSelectProduit(final AjaxBehaviorEvent event) {
        final HtmlSelectOneMenu select = (HtmlSelectOneMenu) event.getSource();
        final Produit produit = this.produitService.reattach((Produit) select.getLocalValue());

        this.getBean().setProduit(produit);
        // Calcul des conditionnements sélectionnables
        this.conditionnements = new ArrayList<Conditionnement>();
        if (produit != null) {
            this.conditionnements.addAll(produit.getConditionnements());
        }
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'un patient est sélectionné.
     * @param event Evénement remonté via la couche IHM.
     */
    public void handleSelectPatient(final AjaxBehaviorEvent event) {
        final Patient patient = (Patient) ((HtmlSelectOneMenu) event.getSource()).getLocalValue();
        this.getBean().setPatient(patient);
        this.setPatientSelected(patient);
        this.initDispensations();
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'un conditionnement est
     * sélectionné.
     * @param event Evenement JSF.
     */
    public void handleSelectConditionnement(final AjaxBehaviorEvent event) {
        final HtmlSelectOneMenu select = (HtmlSelectOneMenu) event.getSource();
        final Conditionnement conditionnement = (Conditionnement) select.getLocalValue();
        this.getBean().setConditionnement(conditionnement);
        this.stockages =
            new ArrayList<DetailStockage>(CollectionUtils.select(this.produitService.reattach(this.getProduitSelected()).getDetailLogistique().getStockagesRetours(),
                                                                 new GenericPredicate("pharmacie", this.pharmacieSelected)));

    }

    /**
     * Méthode en charge d'initialiser la liste des dispensations.
     */
    public void initDispensations() {
        final DispensationSearchCriteria crit = new DispensationSearchCriteria();
        crit.setPatient(this.getBean().getPatient());
        crit.setEssai(this.getBean().getEssai());
        crit.setDispense(true);

        final List<Dispensation> result = this.dispensationService.getAll(crit);

        // On trie les dispensations dans l'ordre des décroissant des numéros
        // d'ordonnancier et
        // des dates.
        Collections.sort(result, new Comparator<Dispensation>() {

            @Override
            public int compare(final Dispensation o1,
                               final Dispensation o2) {
                return this.buildKey(o2).compareTo(this.buildKey(o1));
            }

            private String buildKey(final Dispensation dispensation) {
                final StringBuffer sb = new StringBuffer();
                sb.append(dispensation.getNumOrdonnancier());
                if (dispensation.getDateDispensation() != null) {
                    sb.append(dispensation.getDateDispensation().getTimeInMillis());
                }
                return sb.toString();
            }

        });
        this.setDispensations(result);
    }
    /**
     * Méthode en charge d'affecter le numéro d'ordonnancier de la dispenstion
     * sélectionnée.
     */
    public void handleSelectDispensation() {
        if (this.getDispensationSelected() != null) {
            this.getBean().setNumOrdonnancier(this.getDispensationSelected().getDispensation().getNumOrdonnancier());
            this.getBean().setNumLot(this.getDispensationSelected().getNumLot());
            this.getBean().setNumTraitement(this.getDispensationSelected().getNumTraitement());
        }
        this.setDispensationSelected(null);
    }
    /**
     * Setter pour patientService.
     * @param patientService le patientService à écrire.
     */
    public void setPatientService(final PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Getter sur essaiSelected.
     * @return Retourne le essaiSelected.
     */
    public Essai getEssaiSelected() {
        return this.essaiSelected;
    }

    /**
     * Setter pour essaiSelected.
     * @param essaiSelected le essaiSelected à écrire.
     */
    public void setEssaiSelected(final Essai essaiSelected) {
        this.essaiSelected = essaiSelected;
    }

    /**
     * Getter sur patients.
     * @return Retourne le patients.
     */
    public List<Patient> getPatients() {
        return this.patients;
    }

    /**
     * Setter pour patients.
     * @param patients le patients à écrire.
     */
    public void setPatients(final List<Patient> patients) {
        this.patients = patients;
    }

    /**
     * Getter sur patientSelected.
     * @return Retourne le patientSelected.
     */
    public Patient getPatientSelected() {
        return this.patientSelected;
    }

    /**
     * Setter pour patientSelected.
     * @param patientSelected le patientSelected à écrire.
     */
    public void setPatientSelected(final Patient patientSelected) {
        this.patientSelected = patientSelected;
    }

    /**
     * Getter sur produitSelected.
     * @return Retourne le produitSelected.
     */
    public Produit getProduitSelected() {
        return this.produitSelected;
    }

    /**
     * Getter sur produits.
     * @return Retourne le produits.
     */
    public List<Produit> getProduits() {
        return this.produits;
    }

    /**
     * Setter pour produitSelected.
     * @param produitSelected le produitSelected à écrire.
     */
    public void setProduitSelected(final Produit produitSelected) {
        this.produitSelected = produitSelected;
    }

    /**
     * Setter pour produits.
     * @param produits le produits à écrire.
     */
    public void setProduits(final List<Produit> produits) {
        this.produits = produits;
    }

    /**
     * Getter sur stockages.
     * @return Retourne le stockages.
     */
    public List<DetailStockage> getStockages() {
        return this.stockages;
    }

    /**
     * Setter pour stockages.
     * @param stockages le stockages à écrire.
     */
    public void setStockages(final List<DetailStockage> stockages) {
        this.stockages = stockages;
    }

    /**
     * Getter sur pharmacies.
     * @return Retourne le pharmacies.
     */
    public List<Pharmacie> getPharmacies() {
        return this.pharmacies;
    }

    /**
     * Setter pour pharmacies.
     * @param pharmacies le pharmacies à écrire.
     */
    public void setPharmacies(final List<Pharmacie> pharmacies) {
        this.pharmacies = pharmacies;
    }

    /**
     * Getter sur pharmacieSelected.
     * @return Retourne le pharmacieSelected.
     */
    public Pharmacie getPharmacieSelected() {
        return this.pharmacieSelected;
    }

    /**
     * Setter pour pharmacieSelected.
     * @param pharmacieSelected le pharmacieSelected à écrire.
     */
    public void setPharmacieSelected(final Pharmacie pharmacieSelected) {
        this.pharmacieSelected = pharmacieSelected;
    }

    /**
     * Getter sur conditionnements.
     * @return Retourne le conditionnements.
     */
    public List<Conditionnement> getConditionnements() {
        return this.conditionnements;
    }

    /**
     * Setter pour conditionnements.
     * @param conditionnements le conditionnements à écrire.
     */
    public void setConditionnements(final List<Conditionnement> conditionnements) {
        this.conditionnements = conditionnements;
    }

    /**
     * Getter sur conditionnementSelected.
     * @return Retourne le conditionnementSelected.
     */
    public Conditionnement getConditionnementSelected() {
        return this.conditionnementSelected;
    }

    /**
     * Setter pour conditionnementSelected.
     * @param conditionnementSelected le conditionnementSelected à écrire.
     */
    public void setConditionnementSelected(final Conditionnement conditionnementSelected) {
        this.conditionnementSelected = conditionnementSelected;
    }

    /**
     * Getter sur result.
     * @return Retourne le result.
     */
    public RetourPatient getResult() {
        return this.result;
    }

    /**
     * Setter pour result.
     * @param result le result à écrire.
     */
    public void setResult(final RetourPatient result) {
        this.result = result;

        // Forcer le chargement du nom de pharmacie pour eviter un
        // LazyLoadingException dans l'IHM
        if ((result != null) && (result.getDetailStockage() != null) && (result.getDetailStockage().getStockage() != null)
            && (result.getDetailStockage().getStockage().getPharmacie() != null)) {
            result.getDetailStockage().getStockage().getPharmacie().getNom();
        }

    }

    /**
     * Setter pour produitService.
     * @param produitService le produitService à écrire.
     */
    public void setProduitService(final ProduitService<Produit> produitService) {
        this.produitService = produitService;
    }

    /**
     * Setter pour essaiService.
     * @param essaiService le essaiService à écrire.
     */
    public void setEssaiService(final EssaiService essaiService) {
        this.essaiService = essaiService;
    }

    /**
     * Getter pour dispensationSelected.
     * @return Le dispensationSelected
     */
    public DispensationProduit getDispensationSelected() {
        return this.dispensationSelected;
    }

    /**
     * Setter pour dispensationSelected.
     * @param dispensationSelected Le dispensationSelected à écrire.
     */
    public void setDispensationSelected(final DispensationProduit dispensationSelected) {
        this.dispensationSelected = dispensationSelected;
    }

    /**
     * Getter pour dispensations.
     * @return Le dispensations
     */
    public List<Dispensation> getDispensations() {
        return this.dispensations;
    }

    /**
     * Setter pour dispensations.
     * @param dispensations Le dispensations à écrire.
     */
    public void setDispensations(final List<Dispensation> dispensations) {
        this.dispensations = dispensations;
    }

    /**
     * Setter pour dispensationService.
     * @param dispensationService Le dispensationService à écrire.
     */
    public void setDispensationService(final DispensationService dispensationService) {
        this.dispensationService = dispensationService;
    }

    /**
     * Getter pour action.
     * @return Le action
     */
    public String getAction() {
        return this.action;
    }

    /**
     * Setter pour action.
     * @param action Le action à écrire.
     */
    public void setAction(final String action) {
        this.action = action;
    }

}

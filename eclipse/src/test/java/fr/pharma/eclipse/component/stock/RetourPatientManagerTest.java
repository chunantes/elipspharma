package fr.pharma.eclipse.component.stock;

import java.util.ArrayList;
import java.util.TreeSet;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.primefaces.event.SelectEvent;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.detail.DetailLogistique;
import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;
import fr.pharma.eclipse.domain.model.stock.RetourPatient;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.dispensation.DispensationService;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.patient.PatientService;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stock.RetourPatientService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du manager RetourPatientManager.
 
 * @version $Revision$ $Date$
 */
public class RetourPatientManagerTest
    extends AbstractEclipseJUnitTest
{

    /**
     * Manager.
     */
    private RetourPatientManager manager;

    /**
     * Service produit.
     */
    private ProduitService<Produit> produitService;

    /**
     * Service Essai.
     */
    private EssaiService essaiService;

    /**
     * Service patient.
     */
    private PatientService patientService;

    /**
     * Service retour patient.
     */
    private RetourPatientService retourPatientService;

    /**
     * Setvice dispensatio.
     */
    private DispensationService dispensationService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp()
    {
        this.dispensationService = Mockito.mock(DispensationService.class);
        this.patientService = Mockito.mock(PatientService.class);
        this.essaiService = Mockito.mock(EssaiService.class);
        this.produitService = Mockito.mock(ProduitService.class);
        this.retourPatientService = Mockito.mock(RetourPatientService.class);
        this.manager = new RetourPatientManager(this.retourPatientService);
        this.manager.setPatientService(this.patientService);
        this.manager.setProduitService(this.produitService);
        this.manager.setEssaiService(this.essaiService);
        this.manager.setBean(new RetourPatient());
        this.manager.setDispensationService(this.dispensationService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown()
    {
        this.patientService = null;
        this.essaiService = null;
        this.produitService = null;
        this.manager = null;
        this.dispensationService = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.essaiService);
        Assert.assertNotNull(this.patientService);
        Assert.assertNotNull(this.produitService);
        Assert.assertNotNull(this.dispensationService);
        Assert.assertNotNull(this.manager);
    }

    /**
     * Test dela méthode init().
     */
    @Test
    public void testInitMethod()
    {
        this.manager.setBean(new RetourPatient());
        this.manager.setConditionnements(new ArrayList<Conditionnement>());
        this.manager.setConditionnementSelected(new Conditionnement());
        this.manager.setEssaiSelected(new Essai());
        this.manager.setPatients(new ArrayList<Patient>());
        this.manager.setPatientSelected(new Patient());
        this.manager.setPharmacieSelected(new Pharmacie());
        this.manager.setPharmacieSelected(new Pharmacie());
        this.manager.setProduits(new ArrayList<Produit>());
        this.manager.setProduitSelected(new Medicament());
        this.manager.setResult(new RetourPatient());
        this.manager.setStockages(new ArrayList<DetailStockage>());

        this.manager.init();

        Assert.assertNull(this.manager.getBean());
        Assert.assertNull(this.manager.getConditionnements());
        Assert.assertNull(this.manager.getConditionnementSelected());
        Assert.assertNull(this.manager.getEssaiSelected());
        Assert.assertNull(this.manager.getPatients());
        Assert.assertNull(this.manager.getPatientSelected());
        Assert.assertNull(this.manager.getPharmacies());
        Assert.assertNull(this.manager.getPharmacieSelected());
        Assert.assertNull(this.manager.getProduits());
        Assert.assertNull(this.manager.getProduitSelected());
        Assert.assertNull(this.manager.getResult());
        Assert.assertNull(this.manager.getStockages());
    }

    /**
     * Test de la méthode handleSelectEssai.
     */
    @Test
    public void testHandleSelectEssai()
    {
        Mockito.when(this.patientService.getAllPatientsForEssai(Matchers.any(Essai.class)))
                .thenReturn(new ArrayList<Patient>());

        Mockito.when(this.essaiService.getAllPharmacies(Matchers.any(Essai.class)))
                .thenReturn(new ArrayList<Pharmacie>());
        this.manager.setEssaiSelected(new Essai());
        this.manager.handleSelectEssai(Mockito.mock(SelectEvent.class));

        Assert.assertNotNull(this.manager.getBean().getEssai());
        Assert.assertNotNull(this.manager.getPatients());
        Assert.assertNotNull(this.manager.getPharmacies());
    }

    /**
     * Test de la méthode handleSelectPharmacie.
     */
    @Test
    public void testHandleSelectPharmacie()
    {

        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu component = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(component);
        Mockito.when(component.getLocalValue()).thenReturn(new Pharmacie());
        Mockito.when(this.produitService.getProduits(Matchers.any(Essai.class),
                                                     Matchers.any(Pharmacie.class)))
                .thenReturn(new ArrayList<Produit>());

        this.manager.handleSelectPharmacie(event);

        Assert.assertNotNull(this.manager.getPharmacieSelected());
        Assert.assertNotNull(this.manager.getProduits());
    }

    /**
     * Test de la méthode handleSelectProduit.
     */
    @Test
    public void testHandleSelectProduit()
    {

        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu component = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(component);
        Mockito.when(component.getLocalValue()).thenReturn(new Medicament());
        Mockito.when(this.produitService.reattach(Matchers.any(Produit.class)))
                .thenReturn(new Medicament());

        Mockito.when(this.produitService.getProduits(Matchers.any(Essai.class),
                                                     Matchers.any(Pharmacie.class)))
                .thenReturn(new ArrayList<Produit>());

        this.manager.handleSelectProduit(event);

        Assert.assertNotNull(this.manager.getBean().getProduit());
        Assert.assertNotNull(this.manager.getConditionnements());
    }

    /**
     * Test de la méthode handleSelectPatient.
     */
    @Test
    public void testHandleSelectPatient()
    {

        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu component = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(component);
        Mockito.when(component.getLocalValue()).thenReturn(new Patient());
        Mockito.when(this.dispensationService.getAll(Matchers.any(SearchCriteria.class)))
                .thenReturn(new ArrayList<Dispensation>());

        this.manager.handleSelectPatient(event);

        Assert.assertNotNull(this.manager.getBean().getPatient());
    }

    /**
     * Test de la méthode handleSelectConditionnementy.
     */
    @Test
    public void testHandleSelectConditionnement()
    {
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu component = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(component);
        Mockito.when(component.getLocalValue()).thenReturn(new Conditionnement());

        this.manager.setProduitSelected(new Medicament());
        final Produit medicament = new Medicament();
        medicament.setDetailLogistique(new DetailLogistique());
        medicament.getDetailLogistique().setStockagesRetours(new TreeSet<DetailStockage>());
        Mockito.when(this.produitService.reattach(Matchers.any(Produit.class)))
                .thenReturn(medicament);

        this.manager.handleSelectConditionnement(event);

        Assert.assertNotNull(this.manager.getBean().getConditionnement());
        Assert.assertNotNull(this.manager.getStockages());
    }

}

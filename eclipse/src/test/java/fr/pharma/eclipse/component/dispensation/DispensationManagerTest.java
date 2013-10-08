package fr.pharma.eclipse.component.dispensation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.component.stock.SortieManager;
import fr.pharma.eclipse.domain.enums.TypeElementToCheck;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.dispensation.ElementToCheck;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.Sortie;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.user.UserService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du manager DispensationManager.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationManagerTest extends AbstractEclipseJUnitTest {

    /**
     * Manager.
     */
    private DispensationManager manager;

    /**
     * SortieManager mocké.
     */
    private SortieManager sortieManager;

    /**
     * Service de gestion des essais mocké.
     */
    private EssaiService mockEssaiService;

    /**
     * Service de gestion des utilisateurs mocké.
     */
    private UserService mockUserService;

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public void setUp() {
        this.sortieManager = Mockito.mock(SortieManager.class);
        this.mockEssaiService = Mockito.mock(EssaiService.class);
        this.manager = new DispensationManager(Mockito.mock(GenericService.class));
        this.manager.setSortieManager(this.sortieManager);
        this.manager.setEssaiService(this.mockEssaiService);
        this.mockUserService = Mockito.mock(UserService.class);
        this.manager.setUserService(this.mockUserService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.sortieManager = null;
        this.manager = null;
        this.mockEssaiService = null;
        this.mockUserService = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.sortieManager);
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.mockEssaiService);
        Assert.assertNotNull(this.mockUserService);
    }

    /**
     * Test de la méthode init.
     */
    @Test
    public void testInitMethod() {
        this.manager.setProduitPrescritCurrent(new ProduitPrescrit());
        this.manager.setBean(new Dispensation());
        this.manager.setReadOnly(true);
        this.manager.init();
        Mockito.verify(this.sortieManager).init();
        Assert.assertNull(this.manager.getBean());
        Assert.assertFalse(this.manager.getReadOnly());
        Assert.assertNull(this.manager.getProduitPrescritCurrent());
    }

    /**
     * Méthode en charge de tester la méthode d'initialisation du calcul des
     * pharmacies.
     */
    @Test
    public void testInitPharmacies() {
        final Dispensation dispensation = new Dispensation();
        final Prescription prescription = new Prescription();
        final Inclusion inclusion = new Inclusion();
        final Essai essai = Mockito.mock(Essai.class);
        inclusion.setEssai(essai);
        prescription.setInclusion(inclusion);
        dispensation.setPrescription(prescription);
        Mockito.when(this.mockEssaiService.get(Matchers.anyLong())).thenReturn(essai);
        this.manager.setBean(dispensation);
        this.manager.initPharmacies();
        Mockito.verify(this.mockEssaiService).getAllPharmaciesOfUser(essai);
    }

    /**
     * Méthode en charge de tester la méthode d'initialisation du calcul des
     * pharmacies.
     */
    @Test
    public void testInitPharmaciesWithSizeOne() {
        final Dispensation dispensation = new Dispensation();
        final Prescription prescription = new Prescription();
        final Inclusion inclusion = new Inclusion();
        final Essai essai = Mockito.mock(Essai.class);
        inclusion.setEssai(essai);
        prescription.setInclusion(inclusion);
        dispensation.setPrescription(prescription);
        Mockito.when(this.mockEssaiService.get(Matchers.anyLong())).thenReturn(essai);

        final List<Pharmacie> pharmacies = new ArrayList<Pharmacie>();
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        pharmacies.add(pharmacie);

        this.manager.setBean(dispensation);
        Mockito.when(this.mockEssaiService.getAllPharmaciesOfUser(essai)).thenReturn(pharmacies);
        this.manager.initPharmacies();
        Mockito.verify(this.mockEssaiService).getAllPharmaciesOfUser(essai);
        Assert.assertNotNull(this.manager.getBean().getPharmacie());
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM lors de la
     * sélection d'une pharmacie.
     */
    @Test
    public void testHandleSelectPharmacie() {
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        Mockito.when(select.getLocalValue()).thenReturn(pharmacie);

        final Dispensation dispensation = new Dispensation();
        this.manager.setBean(dispensation);
        this.manager.handleSelectPharmacie(event);
        Assert.assertNotNull(this.manager.getBean().getPharmacie());
    }

    @Test
    public void testSetSortieManagerBeforeSave() {
        // Preparer
        this.manager.getSortiesParProduit().put(1L, new ArrayList<Sortie>());
        this.manager.getSortiesParProduit().get(1L).add(new Sortie());

        this.manager.setBean(Mockito.mock(Dispensation.class));

        // Tester
        this.manager.setSortieManagerBeforeSave();

        // Verifier
        Mockito.verify(this.sortieManager, Mockito.times(2)).getSorties();
        Mockito.verify(this.manager.getBean()).setProduitDispense(1L);
    }

    /**
     * Test de la méthode handleSelectConditionnement.
     */
    @Test
    public void testHandleSelectConditionnement() {
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);

        final ProduitPrescrit p = new ProduitPrescrit();
        p.setNumTraitement("");
        this.manager.setProduitPrescritCurrent(p);

        final Sortie sortie = Mockito.mock(Sortie.class);
        sortie.setLignesStock(new ArrayList<LigneStock>());
        Mockito.when(this.sortieManager.getSortieCurrent()).thenReturn(sortie);

        this.manager.handleSelectConditionnement(event);
        Mockito.verify(this.sortieManager).handleSelectConditionnement(event);
        Mockito.verify(sortie).filtrerLignesStockParNumeroTraitement(Matchers.anyString());
    }

    /**
     * Test de la méthode addSortieToSorties.
     */
    @Test
    public void testAddSortieToSorties() {
        final Produit prod = new Medicament();
        prod.setDenomination("test");
        final ProduitPrescrit p1 = new ProduitPrescrit();
        p1.setId(1L);
        final Conditionnement c1 = new Conditionnement();
        c1.setModePrescription(ModePrescription.DOSE);
        p1.setConditionnement(c1);
        p1.setProduit(prod);
        final ProduitPrescrit p2 = new ProduitPrescrit();
        p2.setId(2L);
        p2.setProduit(prod);
        final Conditionnement c2 = new Conditionnement();
        c2.setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        p2.setConditionnement(c2);
        final Dispensation d = new Dispensation();
        d.setPrescription(new Prescription());
        d.getPrescription().getProduitsPrescrits().add(p1);
        d.getPrescription().getProduitsPrescrits().add(p2);

        this.manager.setBean(d);
        this.manager.setProduitPrescritCurrent(p1);

        this.manager.addSortieToSorties();
        Mockito.verify(this.sortieManager).addSortieToSorties();
        Assert.assertTrue(p1.getDispense());
    }

    /**
     * Test de la méthode isProduitDispense.
     */
    @Test
    public void testIsProduitDispenseNull() {
        final ProduitPrescrit produit = null;
        Assert.assertFalse(this.manager.isProduitDispense(produit));
    }

    /**
     * Test de la méthode isProduitDispense.
     */
    @Test
    public void testIsProduitDispenseNotNull() {
        final ProduitPrescrit produit = new ProduitPrescrit();
        produit.setId(1L);
        Assert.assertFalse(this.manager.isProduitDispense(produit));
    }

    /**
     * Test de la méthode isProduitDispense.
     */
    @Test
    public void testIsProduitDispenseNotNullCreate() {
        final ProduitPrescrit produit = new ProduitPrescrit();
        produit.setId(1L);
        this.manager.getSortiesParProduit().put(1L, new ArrayList<Sortie>());
        this.manager.getSortiesParProduit().get(1L).add(new Sortie());

        Assert.assertTrue(this.manager.isProduitDispense(produit));
    }

    /**
     * Test de la méthode isProduitDispense.
     */
    @Test
    public void testIsProduitDispenseNotNullEdit() {
        final ProduitPrescrit produit = new ProduitPrescrit();
        produit.setId(1L);
        this.manager.getSortiesParProduit().put(1L, new ArrayList<Sortie>());
        this.manager.getSortiesParProduit().get(1L).add(new Sortie());

        Assert.assertTrue(this.manager.isProduitDispense(produit));
    }

    /**
     * Test de la méthode initProduitsDispensesForConsult.
     */
    @Test
    public void testInitProduitsDispensesForConsult() {

        final DispensationProduit d1 = new DispensationProduit();
        d1.setId(1L);
        d1.setNumLot("1");
        final DispensationProduit d2 = new DispensationProduit();
        d2.setId(2L);
        d2.setNumLot("2");
        final DispensationProduit d3 = new DispensationProduit();
        d3.setId(3L);
        d3.setNumLot("3");

        final ProduitPrescrit p1 = new ProduitPrescrit();
        p1.setId(1L);
        final ProduitPrescrit p2 = new ProduitPrescrit();
        p2.setId(2L);

        d1.setProduitPrescrit(p1);
        d2.setProduitPrescrit(p2);
        d3.setProduitPrescrit(p2);

        final Conditionnement c1 = new Conditionnement();
        c1.setId(1L);
        final Conditionnement c2 = new Conditionnement();
        c2.setId(2L);
        d1.setConditionnement(c1);
        d2.setConditionnement(c2);
        d3.setConditionnement(c2);

        final Dispensation dispensation = new Dispensation();
        dispensation.getDispensationsProduit().add(d1);
        dispensation.getDispensationsProduit().add(d2);
        dispensation.getDispensationsProduit().add(d3);

        this.manager.setBean(dispensation);

        this.manager.initProduitDispenses();
        Assert.assertEquals("Aucune sortie si la dispensation n'a pas d'id défini", 0, this.manager.getSortiesParProduit().size());

        dispensation.setId(1L);
        this.manager.initProduitDispenses();
        Assert.assertEquals(2, this.manager.getSortiesParProduit().size());
        Assert.assertEquals(1, this.manager.getSortiesParProduit().get(p1.getId()).size());
        Assert.assertEquals(1, this.manager.getSortiesParProduit().get(p2.getId()).size());
        Assert.assertEquals(2, this.manager.getSortiesParProduit().get(p2.getId()).get(0).getLignesStock().size());
    }
    /**
     * Test de la méthode appelée depuis l'IHM lors de la sélection de la case à
     * cocher reconstitution simple.
     */
    @Test
    public void testHandleCaseCheckedReconstitutionSimpleFalse() {
        final ValueChangeEvent event = Mockito.mock(ValueChangeEvent.class);
        final HtmlSelectBooleanCheckbox select = Mockito.mock(HtmlSelectBooleanCheckbox.class);
        Mockito.when(event.getSource()).thenReturn(select);
        Mockito.when(select.getLocalValue()).thenReturn(Boolean.FALSE);
        Mockito.when(select.getId()).thenReturn("caseReconstitutionSimple_1");

        final Dispensation dispensation = new Dispensation();
        final Prescription prescription = new Prescription();
        final ProduitPrescrit produitPrescrit = new ProduitPrescrit();
        produitPrescrit.setId(1L);
        final ElementToCheck elementToCheck = new ElementToCheck();
        elementToCheck.setProduitPrescrit(produitPrescrit);
        elementToCheck.setNomChamps(TypeElementToCheck.RECONSTITUTION_SIMPLE.getLibelle());
        prescription.getProduitsPrescrits().add(produitPrescrit);
        dispensation.setPrescription(prescription);
        dispensation.getElementsToCheck().add(elementToCheck);
        this.manager.setBean(dispensation);

        this.manager.handleCaseCheckedReconstitutionSimple(event);
        Assert.assertNull(elementToCheck.getDateChecked());
        Assert.assertNull(elementToCheck.getCheckedBy());
    }

    /**
     * Test de la méthode appelée depuis l'IHM lors de la sélection de la case à
     * cocher reconstitution simple.
     */
    @Test
    public void testHandleCaseCheckedReconstitutionSimpleTrue() {
        final ValueChangeEvent event = Mockito.mock(ValueChangeEvent.class);
        final HtmlSelectBooleanCheckbox select = Mockito.mock(HtmlSelectBooleanCheckbox.class);
        Mockito.when(event.getSource()).thenReturn(select);
        Mockito.when(select.getLocalValue()).thenReturn(Boolean.TRUE);
        Mockito.when(select.getId()).thenReturn("caseReconstitutionSimple_1");

        final Dispensation dispensation = new Dispensation();
        final Prescription prescription = new Prescription();
        final ProduitPrescrit produitPrescrit = new ProduitPrescrit();
        produitPrescrit.setId(1L);
        final ElementToCheck elementToCheck = new ElementToCheck();
        elementToCheck.setProduitPrescrit(produitPrescrit);
        elementToCheck.setNomChamps(TypeElementToCheck.RECONSTITUTION_SIMPLE.getLibelle());
        prescription.getProduitsPrescrits().add(produitPrescrit);
        dispensation.setPrescription(prescription);
        dispensation.getElementsToCheck().add(elementToCheck);
        this.manager.setBean(dispensation);
        final Personne personne = Mockito.mock(Personne.class);
        Mockito.when(this.mockUserService.getPersonne()).thenReturn(personne);
        this.manager.handleCaseCheckedReconstitutionSimple(event);

        Assert.assertNotNull(elementToCheck.getDateChecked());
        Assert.assertNotNull(elementToCheck.getCheckedBy());
        Mockito.verify(this.mockUserService).getPersonne();
    }

    /**
     * Test de la méthode appelée depuis l'IHM lors de la sélection de la case à
     * cocher reconstitution PSM.
     */
    @Test
    public void testHandleCaseCheckedReconstitutionPSMFalse() {
        final ValueChangeEvent event = Mockito.mock(ValueChangeEvent.class);
        final HtmlSelectBooleanCheckbox select = Mockito.mock(HtmlSelectBooleanCheckbox.class);
        Mockito.when(event.getSource()).thenReturn(select);
        Mockito.when(select.getLocalValue()).thenReturn(Boolean.FALSE);
        Mockito.when(select.getId()).thenReturn("caseReconstitutionPSM_1");

        final Dispensation dispensation = new Dispensation();
        final Prescription prescription = new Prescription();
        final ProduitPrescrit produitPrescrit = new ProduitPrescrit();
        produitPrescrit.setId(1L);
        final ElementToCheck elementToCheck = new ElementToCheck();
        elementToCheck.setProduitPrescrit(produitPrescrit);
        elementToCheck.setNomChamps(TypeElementToCheck.RECONSTITUTION_PSM.getLibelle());
        prescription.getProduitsPrescrits().add(produitPrescrit);
        dispensation.setPrescription(prescription);
        dispensation.getElementsToCheck().add(elementToCheck);
        this.manager.setBean(dispensation);

        this.manager.handleCaseCheckedReconstitutionPSM(event);
        Assert.assertNull(elementToCheck.getDateChecked());
        Assert.assertNull(elementToCheck.getCheckedBy());
    }

    /**
     * Test de la méthode appelée depuis l'IHM lors de la sélection de la case à
     * cocher reconstitution PSM.
     */
    @Test
    public void testHandleCaseCheckedReconstitutionPSMTrue() {
        final ValueChangeEvent event = Mockito.mock(ValueChangeEvent.class);
        final HtmlSelectBooleanCheckbox select = Mockito.mock(HtmlSelectBooleanCheckbox.class);
        Mockito.when(event.getSource()).thenReturn(select);
        Mockito.when(select.getLocalValue()).thenReturn(Boolean.TRUE);
        Mockito.when(select.getId()).thenReturn("caseReconstitutionPSM_1");

        final Dispensation dispensation = new Dispensation();
        final Prescription prescription = new Prescription();
        final ProduitPrescrit produitPrescrit = new ProduitPrescrit();
        produitPrescrit.setId(1L);
        final ElementToCheck elementToCheck = new ElementToCheck();
        elementToCheck.setProduitPrescrit(produitPrescrit);
        elementToCheck.setNomChamps(TypeElementToCheck.RECONSTITUTION_PSM.getLibelle());
        prescription.getProduitsPrescrits().add(produitPrescrit);
        dispensation.setPrescription(prescription);
        dispensation.getElementsToCheck().add(elementToCheck);
        this.manager.setBean(dispensation);
        final Personne personne = Mockito.mock(Personne.class);
        Mockito.when(this.mockUserService.getPersonne()).thenReturn(personne);
        this.manager.handleCaseCheckedReconstitutionPSM(event);

        Assert.assertNotNull(elementToCheck.getDateChecked());
        Assert.assertNotNull(elementToCheck.getCheckedBy());
        Mockito.verify(this.mockUserService).getPersonne();
    }

    /**
     * Test de la méthode appelée depuis l'IHM lors de la sélection de la case à
     * cocher Fabrication.
     */
    @Test
    public void testHandleCaseCheckedFabricationFalse() {
        final ValueChangeEvent event = Mockito.mock(ValueChangeEvent.class);
        final HtmlSelectBooleanCheckbox select = Mockito.mock(HtmlSelectBooleanCheckbox.class);
        Mockito.when(event.getSource()).thenReturn(select);
        Mockito.when(select.getLocalValue()).thenReturn(Boolean.FALSE);
        Mockito.when(select.getId()).thenReturn("caseFabrication_1");

        final Dispensation dispensation = new Dispensation();
        final Prescription prescription = new Prescription();
        final ProduitPrescrit produitPrescrit = new ProduitPrescrit();
        produitPrescrit.setId(1L);
        final ElementToCheck elementToCheck = new ElementToCheck();
        elementToCheck.setProduitPrescrit(produitPrescrit);
        elementToCheck.setNomChamps(TypeElementToCheck.FABRICATION.getLibelle());
        prescription.getProduitsPrescrits().add(produitPrescrit);
        dispensation.setPrescription(prescription);
        dispensation.getElementsToCheck().add(elementToCheck);
        this.manager.setBean(dispensation);

        this.manager.handleCaseCheckedFabrication(event);
        Assert.assertNull(elementToCheck.getDateChecked());
        Assert.assertNull(elementToCheck.getCheckedBy());
    }

    /**
     * Test de la méthode appelée depuis l'IHM lors de la sélection de la case à
     * cocher Fabrication.
     */
    @Test
    public void testHandleCaseCheckedFabricationTrue() {
        final ValueChangeEvent event = Mockito.mock(ValueChangeEvent.class);
        final HtmlSelectBooleanCheckbox select = Mockito.mock(HtmlSelectBooleanCheckbox.class);
        Mockito.when(event.getSource()).thenReturn(select);
        Mockito.when(select.getLocalValue()).thenReturn(Boolean.TRUE);
        Mockito.when(select.getId()).thenReturn("caseFabrication_1");

        final Dispensation dispensation = new Dispensation();
        final Prescription prescription = new Prescription();
        final ProduitPrescrit produitPrescrit = new ProduitPrescrit();
        produitPrescrit.setId(1L);
        final ElementToCheck elementToCheck = new ElementToCheck();
        elementToCheck.setProduitPrescrit(produitPrescrit);
        elementToCheck.setNomChamps(TypeElementToCheck.FABRICATION.getLibelle());
        prescription.getProduitsPrescrits().add(produitPrescrit);
        dispensation.setPrescription(prescription);
        dispensation.getElementsToCheck().add(elementToCheck);
        this.manager.setBean(dispensation);
        final Personne personne = Mockito.mock(Personne.class);
        Mockito.when(this.mockUserService.getPersonne()).thenReturn(personne);
        this.manager.handleCaseCheckedFabrication(event);

        Assert.assertNotNull(elementToCheck.getDateChecked());
        Assert.assertNotNull(elementToCheck.getCheckedBy());
        Mockito.verify(this.mockUserService).getPersonne();
    }

    public void testGetSortiesParProduit() {

    }

}

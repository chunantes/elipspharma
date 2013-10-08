package fr.pharma.eclipse.component.dotation;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.primefaces.event.SelectEvent;

import fr.pharma.eclipse.comparator.common.EclipseListComparator;
import fr.pharma.eclipse.domain.model.dotation.Dotation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.detail.DetailLogistique;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.factory.dotation.DotationFactory;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Classe en charge de tester le manager de DemandeDotation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DemandeDotationManagerTest {
    /**
     * DemandeDotationManager à tester.
     */
    private DemandeDotationManager manager;

    /**
     * Mock du service de gestion des produits.
     */
    private ProduitService<Produit> mockProduitService;

    /**
     * Mock de la factory de dotation.
     */
    private DotationFactory mockDotationFactory;

    /**
     * Mock de l'utilitaire Faces.
     */
    private FacesUtils mockFacesUtils;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.manager = new DemandeDotationManager();
        this.mockProduitService = Mockito.mock(ProduitService.class);
        this.manager.setProduitService(this.mockProduitService);
        this.mockDotationFactory = Mockito.mock(DotationFactory.class);
        this.manager.setDotationFactory(this.mockDotationFactory);
        this.mockFacesUtils = Mockito.mock(FacesUtils.class);
        this.manager.setFacesUtils(this.mockFacesUtils);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.manager = null;
        this.mockProduitService = null;
        this.mockDotationFactory = null;
        this.mockFacesUtils = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInitData() {
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.mockProduitService);
        Assert.assertNotNull(this.mockDotationFactory);
        Assert.assertNotNull(this.mockFacesUtils);
    }

    /**
     * Méthode en charge de tester la méthode d'initialisation.
     */
    @Test
    public void testInit() {
        this.manager.init();
        Assert.assertNull(this.manager.getEssaiSelected());
        Assert.assertNull(this.manager.getServices());
        Assert.assertNull(this.manager.getProduits());
        Assert.assertNull(this.manager.getServiceSelected());
        Assert.assertNull(this.manager.getDotationCurrent());
        Assert.assertNull(this.manager.getConditionnements());
        Assert.assertEquals(0, this.manager.getDotations().size());
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM suite à la
     * sélection d'un essai.
     */
    @Test
    public void testHandleSelectEssai() {
        final SelectEvent mockSelectEvent = Mockito.mock(SelectEvent.class);
        final Essai essai = Mockito.mock(Essai.class);
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        Mockito.when(essai.getPharmaciePrincipale()).thenReturn(pharmacie);
        Mockito.when(mockSelectEvent.getObject()).thenReturn(essai);
        this.manager.handleSelectEssai(mockSelectEvent);
        Mockito.verify(this.mockProduitService).getProduits(essai, pharmacie);
        Assert.assertNull(this.manager.getServiceSelected());
    }

    /**
     * Méthode en charge de tester la méthode d'ajout de dotation.
     */
    @Test
    public void testAddDotation() {
        final Essai essai = Mockito.mock(Essai.class);
        final Service service = Mockito.mock(Service.class);
        this.manager.setEssaiSelected(essai);
        this.manager.setServiceSelected(service);
        final Dotation dotation = new Dotation();
        Mockito.when(this.mockDotationFactory.getInitializedObject(essai, service)).thenReturn(dotation);
        this.manager.addDotation();
        Assert.assertNotNull(this.manager.getDotationCurrent());
        Assert.assertEquals("ADD", this.manager.getActionDotationCurrent());
    }

    /**
     * Méthode en charge de tester l'ajout de dotation dans la liste des
     * dotations.
     */
    @Test
    public void testAddDotationToDotationsKO() {
        this.manager.setActionDotationCurrent("EDIT");
        this.manager.addDotationToDotations();
        Assert.assertNull(this.manager.getDotations());
    }

    /**
     * Méthode en charge de tester l'ajout de dotation dans la liste des
     * dotations.
     */
    @Test
    public void testAddDotationToDotations() {
        this.manager.setActionDotationCurrent("ADD");
        this.manager.init();
        this.manager.addDotationToDotations();
        Assert.assertEquals(1, this.manager.getDotations().size());
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM suite à la
     * sélection d'un produit.
     */
    @Test
    public void testHandleSelectProduitNotNull() {
        final Produit produit = Mockito.mock(Produit.class);
        final SortedSet<Conditionnement> conditionnements = new TreeSet<Conditionnement>(new EclipseListComparator());
        produit.getConditionnements().addAll(conditionnements);
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        Mockito.when(select.getLocalValue()).thenReturn(produit);
        Mockito.when(this.mockProduitService.reattach(produit)).thenReturn(produit);

        final Dotation dotation = new Dotation();
        this.manager.setDotationCurrent(dotation);

        this.manager.handleSelectProduit(event);

        Assert.assertNotNull(this.manager.getConditionnements());
        Assert.assertNull(this.manager.getDotationCurrent().getConditionnement());

    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM suite à la
     * sélection d'un produit.
     */
    @Test
    public void testHandleSelectProduitNull() {
        final Produit produit = Mockito.mock(Produit.class);
        final SortedSet<Conditionnement> conditionnements = new TreeSet<Conditionnement>(new EclipseListComparator());
        produit.getConditionnements().addAll(conditionnements);
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        Mockito.when(select.getLocalValue()).thenReturn(produit);
        Mockito.when(this.mockProduitService.reattach(produit)).thenReturn(null);

        final Dotation dotation = new Dotation();
        this.manager.setDotationCurrent(dotation);

        this.manager.handleSelectProduit(event);

        Assert.assertEquals(0, this.manager.getConditionnements().size());
        Assert.assertNull(this.manager.getDotationCurrent().getConditionnement());

    }

    /**
     * Méthode en charge de tester la modification de dotation.
     */
    @Test
    public void testModifyDotation() {
        this.manager.modifyDotation();
        Assert.assertEquals("EDIT", this.manager.getActionDotationCurrent());
    }

    /**
     * Méthode en charge de tester la suppression de dotation.
     */
    @Test
    public void testDelDotationOK() {
        this.manager.init();
        final Dotation dotation = new Dotation();
        this.manager.setDotationToDelete(dotation);
        this.manager.getDotations().add(dotation);

        this.manager.delDotation();
        Assert.assertEquals(0, this.manager.getDotations().size());
    }

    /**
     * Méthode en charge de tester la suppression de dotation.
     */
    @Test
    public void testDelDotation() {
        this.manager.init();
        final Dotation dotation = new Dotation();
        this.manager.setDotationToDelete(dotation);

        final Dotation dotationOther = new Dotation();
        this.manager.getDotations().add(dotationOther);

        this.manager.delDotation();
        Assert.assertEquals(1, this.manager.getDotations().size());
    }

    /**
     * Méthode en charge de tester la méthode de validation d'une dotation.
     */
    @Test
    public void testValidateQteAutoriseeEnDotationNull() {
        final Dotation dotation = new Dotation();
        final Produit produit = new Medicament();
        final DetailLogistique detailLogistique = new DetailLogistique();
        produit.setDetailLogistique(detailLogistique);
        dotation.setProduit(produit);

        dotation.setQuantite(2);

        try {
            this.manager.setDotationCurrent(dotation);
            this.manager.validate();
        } catch (final ValidationException e) {
            Assert.fail("Exception not expected");
        }
    }

    /**
     * Méthode en charge de tester la méthode de validation d'une dotation.
     */
    @Test
    public void testValidateQteAutoriseeOK() {
        final Dotation dotation = new Dotation();
        final Produit produit = new Medicament();
        final DetailLogistique detailLogistique = new DetailLogistique();
        detailLogistique.setQuantiteAutorise(3);
        produit.setDetailLogistique(detailLogistique);
        dotation.setProduit(produit);

        dotation.setQuantite(2);

        try {
            this.manager.setDotationCurrent(dotation);
            this.manager.validate();
        } catch (final ValidationException e) {
            Assert.fail("Exception not expected");
        }
    }

    /**
     * Méthode en charge de tester la méthode de validation d'une dotation.
     */
    @Test(expected = ValidationException.class)
    public void testValidateQteAutoriseeKO() {
        final Dotation dotation = new Dotation();
        final Produit produit = new Medicament();
        final DetailLogistique detailLogistique = new DetailLogistique();
        detailLogistique.setQuantiteAutorise(1);
        produit.setDetailLogistique(detailLogistique);
        dotation.setProduit(produit);
        dotation.setQuantite(2);

        this.manager.setDotationCurrent(dotation);
        this.manager.validate();
    }

    /**
     * Méthode en charge de tester la méthode d'ajout d'un message de
     * confirmation.
     */
    @Test
    public void testAddMessageConfirm() {
        this.manager.addMessageConfirm();
        Mockito.verify(this.mockFacesUtils).addMessage(FacesMessage.SEVERITY_INFO, "demandeDotation.confirmMsg");
    }

}

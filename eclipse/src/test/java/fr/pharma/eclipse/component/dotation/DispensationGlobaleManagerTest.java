package fr.pharma.eclipse.component.dotation;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.primefaces.event.SelectEvent;

import fr.pharma.eclipse.domain.criteria.dotation.DotationSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.produit.ProduitService;

/**
 * Méthode en charge de tester le manager de gestion des dispensations globales
 * (traitement des demandes de dotation).
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationGlobaleManagerTest {
    /**
     * DispensationGlobaleManager à tester.
     */
    private DispensationGlobaleManager manager;

    /**
     * Mock du service de gestion des produits.
     */
    private ProduitService<Produit> mockProduitService;

    /**
     * Critère de recherche.
     */
    private DotationSearchCriteria criteria;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.criteria = new DotationSearchCriteria();
        this.manager = new DispensationGlobaleManager(this.criteria);
        this.mockProduitService = Mockito.mock(ProduitService.class);
        this.manager.setProduitService(this.mockProduitService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.manager = null;
        this.mockProduitService = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInitData() {
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.mockProduitService);
    }

    /**
     * Méthode en charge de tester la méthode d'initialisation.
     */
    @Test
    public void testInit() {
        this.manager.init();
        Assert.assertNull(this.manager.getBeanSelected());
        Assert.assertNull(this.manager.getProduits());
        Assert.assertEquals(Boolean.FALSE, this.criteria.getTraitee());
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM suite à la
     * sélection d'un essai.
     */
    @Test
    public void testHandleSelectEssai() {
        final SelectEvent mockSelectEvent = Mockito.mock(SelectEvent.class);
        final Essai essai = Mockito.mock(Essai.class);
        Mockito.when(mockSelectEvent.getObject()).thenReturn(essai);
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        this.criteria.setPharmacie(pharmacie);
        this.manager.handleSelectEssai(mockSelectEvent);
        Mockito.verify(this.mockProduitService).getProduits(essai, pharmacie);
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM suite à la
     * sélection d'une pharmacie.
     */
    @Test
    public void testHandleSelectPharmacieWithEssaiNull() {
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        Mockito.when(select.getLocalValue()).thenReturn(pharmacie);
        this.criteria.setEssai(null);
        this.manager.handleSelectPharmacie(event);
        Assert.assertEquals(0, this.manager.getProduits().size());
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM suite à la
     * sélection d'une pharmacie.
     */
    @Test
    public void testHandleSelectPharmacieWithEssaiNotNull() {
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        Mockito.when(select.getLocalValue()).thenReturn(pharmacie);
        final Essai essai = Mockito.mock(Essai.class);
        this.criteria.setEssai(essai);
        this.manager.handleSelectPharmacie(event);
        Mockito.verify(this.mockProduitService).getProduits(essai, pharmacie);
    }

    /**
     * Méthode en charge de tester l'affectation des lignes de stock pour un
     * traitement de demande de dotation.
     */
    @Test
    public void testSetLignesStockForDotation() {
        final List<LigneStock> lignesStock = new ArrayList<LigneStock>();
        this.manager.setLignesStockForDotation(lignesStock);
        Assert.assertNotNull(this.manager.getLignesStockForDotation());
    }

}

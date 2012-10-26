package fr.pharma.eclipse.component.stock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.primefaces.event.SelectEvent;

import fr.pharma.eclipse.domain.criteria.stock.ExtensionPeremptionSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.detail.DetailLogistique;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.DocumentStock;
import fr.pharma.eclipse.domain.model.stock.document.DocumentAppro;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.document.DocumentService;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester le manager d'extension de péremption.
 
 * @version $Revision$ $Date$
 */
public class ExtensionPeremptionManagerTest
{
    /**
     * ExtensionPeremptionManager à tester.
     */
    private ExtensionPeremptionManager manager;

    /**
     * Mock du service de gestion des produits.
     */
    private ProduitService<Produit> mockProduitService;

    /**
     * Mock du service document.
     */
    private DocumentService mockDocumentService;

    /**
     * Mock du manager des documents d'approvisionnements.
     */
    private GenericStockManager<DocumentAppro> mockManagerDocAppro;

    /**
     * Critère de recherche mocké.
     */
    private ExtensionPeremptionSearchCriteria criteria;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init()
    {
        this.criteria = Mockito.mock(ExtensionPeremptionSearchCriteria.class);
        this.manager = new ExtensionPeremptionManager(this.criteria);
        this.mockProduitService = Mockito.mock(ProduitService.class);
        this.manager.setProduitService(this.mockProduitService);
        this.mockDocumentService = Mockito.mock(DocumentService.class);
        this.manager.setDocumentService(this.mockDocumentService);
        this.mockManagerDocAppro = Mockito.mock(GenericStockManager.class);
        this.manager.setManagerDocAppro(this.mockManagerDocAppro);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end()
    {
        this.manager = null;
        this.mockProduitService = null;
        this.mockDocumentService = null;
        this.mockManagerDocAppro = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInitData()
    {
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.mockProduitService);
        Assert.assertNotNull(this.mockDocumentService);
        Assert.assertNotNull(this.mockManagerDocAppro);
        Assert.assertNotNull(this.manager.getManagerDocAppro());
    }

    /**
     * Méthode en charge de tester la méthode init.
     */
    @Test
    public void testInit()
    {
        this.manager.init();
        Assert.assertNull(this.manager.getProduits());
        Assert.assertNull(this.manager.getConditionnements());
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM quand un essai est sélectionné..
     */
    @Test
    public void testHandleSelectEssai()
    {
        final SelectEvent mockSelectEvent = Mockito.mock(SelectEvent.class);
        final Essai essai = Mockito.mock(Essai.class);
        Mockito.when(mockSelectEvent.getObject()).thenReturn(essai);

        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        Mockito.when(this.criteria.getPharmacie()).thenReturn(pharmacie);

        this.manager.handleSelectEssai(mockSelectEvent);

        Mockito.verify(this.mockProduitService).getProduits(essai,
                                                            pharmacie);
        Assert.assertNull(this.criteria.getConditionnement());
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM quand une pharmacie est
     * sélectionnée.
     */
    @Test
    public void testHandleSelectPharmacieWithEssaiNull()
    {
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        Mockito.when(select.getLocalValue()).thenReturn(pharmacie);

        Mockito.when(this.criteria.getEssai()).thenReturn(null);

        this.manager.handleSelectPharmacie(event);
        Assert.assertEquals(0,
                            this.manager.getProduits().size());
        Assert.assertNull(this.criteria.getConditionnement());
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM quand une pharmacie est
     * sélectionnée.
     */
    @Test
    public void testHandleSelectPharmacieWithEssaiNotNull()
    {
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        Mockito.when(select.getLocalValue()).thenReturn(pharmacie);

        final Essai essai = Mockito.mock(Essai.class);
        Mockito.when(this.criteria.getEssai()).thenReturn(essai);
        Mockito.when(this.criteria.getPharmacie()).thenReturn(pharmacie);

        this.manager.handleSelectPharmacie(event);

        Mockito.verify(this.mockProduitService).getProduits(essai,
                                                            pharmacie);

        Assert.assertNull(this.criteria.getConditionnement());
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM quand un produit est sélectionné.
     */
    @Test
    public void testHandleSelectProduitNull()
    {
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        final Produit produit = Mockito.mock(Produit.class);
        Mockito.when(select.getLocalValue()).thenReturn(produit);

        Mockito.when(this.mockProduitService.reattach(produit)).thenReturn(null);

        this.manager.handleSelectProduit(event);

        Assert.assertEquals(0,
                            this.manager.getConditionnements().size());
        Assert.assertNull(this.criteria.getConditionnement());
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM quand un produit est sélectionné.
     */
    @Test
    public void testHandleSelectProduitNotNull()
    {
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        final Produit produit = Mockito.mock(Produit.class);
        Mockito.when(select.getLocalValue()).thenReturn(produit);

        Mockito.when(this.mockProduitService.reattach(produit)).thenReturn(produit);

        this.manager.handleSelectProduit(event);

        Assert.assertNull(this.criteria.getConditionnement());
    }

    /**
     * Méthode en charge de tester le calcul de l'atteinte du délai d'alerte avant la date
     * d'expiration d'un produit.
     */
    @Test
    public void testDelaiAlerteAvantExpirationAtteintWithDatePeremptionNull()
    {
        final Approvisionnement appro = new Approvisionnement();
        final Produit produit = new Medicament();
        final DetailLogistique detailLogistique = new DetailLogistique();
        produit.setDetailLogistique(detailLogistique);
        appro.setProduit(produit);

        Assert.assertFalse(this.manager.delaiAlerteAvantExpirationAtteint(appro));
    }

    /**
     * Méthode en charge de tester le calcul de l'atteinte du délai d'alerte avant la date
     * d'expiration d'un produit.
     * @throws ParseException Exception de parsing.
     */
    @Test
    public void testDelaiAlerteAvantExpirationAtteintWithDelaiNull()
        throws ParseException
    {
        final Approvisionnement appro = new Approvisionnement();
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        final Date datePeremption = sdf.parse("30/12/2010");
        final Calendar calDatePeremption = Calendar.getInstance();
        calDatePeremption.setTime(datePeremption);
        appro.setDatePeremption(calDatePeremption);

        final Produit produit = new Medicament();
        final DetailLogistique detailLogistique = new DetailLogistique();
        produit.setDetailLogistique(detailLogistique);
        appro.setProduit(produit);

        Assert.assertTrue(this.manager.delaiAlerteAvantExpirationAtteint(appro));
    }

    /**
     * Méthode en charge de tester le calcul de l'atteinte du délai d'alerte avant la date
     * d'expiration d'un produit.
     * @throws ParseException Exception de parsing.
     */
    @Test
    public void testDelaiAlerteAvantExpirationAtteintTrue()
        throws ParseException
    {
        final Approvisionnement appro = new Approvisionnement();
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        final Date datePeremption = sdf.parse("30/12/2010");
        final Calendar calDatePeremption = Calendar.getInstance(EclipseConstants.LOCALE);
        calDatePeremption.setTime(datePeremption);
        appro.setDatePeremption(calDatePeremption);

        final Produit produit = new Medicament();
        final DetailLogistique detailLogistique = new DetailLogistique();
        detailLogistique.setDelaiAlerteAvtDateExpiration(2);
        produit.setDetailLogistique(detailLogistique);
        appro.setProduit(produit);

        Assert.assertTrue(this.manager.delaiAlerteAvantExpirationAtteint(appro));
    }

    /**
     * Méthode en charge de tester le calcul de l'atteinte du délai d'alerte avant la date
     * d'expiration d'un produit.
     * @throws ParseException Exception de parsing.
     */
    @Test
    public void testDelaiAlerteAvantExpirationAtteintFalse()
        throws ParseException
    {
        final Approvisionnement appro = new Approvisionnement();
        final Calendar calDatePeremption = Calendar.getInstance(EclipseConstants.LOCALE);
        calDatePeremption.add(Calendar.DAY_OF_MONTH,
                              2 + 1);
        appro.setDatePeremption(calDatePeremption);

        final Produit produit = new Medicament();
        final DetailLogistique detailLogistique = new DetailLogistique();
        detailLogistique.setDelaiAlerteAvtDateExpiration(2);
        produit.setDetailLogistique(detailLogistique);
        appro.setProduit(produit);

        Assert.assertFalse(this.manager.delaiAlerteAvantExpirationAtteint(appro));
    }

    /**
     * Méthode en charge de tester la méthode d'ajout de document d'appro.
     */
    @Test
    public void testAddDocApproKO()
    {
        Mockito.when(this.mockManagerDocAppro.canCreateDocument()).thenReturn(Boolean.FALSE);
        this.manager.addDocAppro();
        Mockito.verify(this.mockManagerDocAppro).resetFormDatas();
    }

    /**
     * Méthode en charge de tester la méthode d'ajout de document d'appro.
     */
    @Test
    public void testAddDocApproOK()
    {
        Mockito.when(this.mockManagerDocAppro.canCreateDocument()).thenReturn(Boolean.TRUE);
        final Approvisionnement approvisionnement = Mockito.mock(Approvisionnement.class);
        this.manager.setBeanSelected(approvisionnement);
        final DocumentStock documentStock = new DocumentAppro();
        Mockito.when(this.mockManagerDocAppro.createDocument(approvisionnement))
                .thenReturn(documentStock);
        this.manager.addDocAppro();
    }

    /**
     * Méthode en charge de tester la méthode de suppression de document d'appro.
     */
    @Test
    public void testDelDocApproSansId()
    {
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final DocumentAppro documentAppro = new DocumentAppro();
        final Approvisionnement approvisionnement = new Approvisionnement();
        approvisionnement.setDocumentAppro(documentAppro);
        this.manager.setBeanSelected(approvisionnement);
        this.manager.delDocAppro(event);
        Assert.assertNull(approvisionnement.getDocumentAppro());
    }

    /**
     * Méthode en charge de tester la méthode de suppression de document d'appro.
     */
    @Test
    public void testDelDocApproWithId()
    {
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final DocumentAppro documentAppro = new DocumentAppro();
        final Approvisionnement approvisionnement = new Approvisionnement();
        approvisionnement.setDocumentAppro(documentAppro);
        documentAppro.setId(1L);
        this.manager.setBeanSelected(approvisionnement);
        this.manager.delDocAppro(event);
        Assert.assertNull(approvisionnement.getDocumentAppro());
        Mockito.verify(this.mockDocumentService).remove(documentAppro);
    }

}

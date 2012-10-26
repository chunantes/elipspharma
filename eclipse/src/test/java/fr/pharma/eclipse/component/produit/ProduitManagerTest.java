package fr.pharma.eclipse.component.produit;

import java.util.TreeMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.primefaces.component.tabview.Tab;
import org.primefaces.event.TabChangeEvent;

import fr.pharma.eclipse.domain.enums.TypeHistoriqueProduit;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentProduit;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.document.DocumentConditionnement;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Classe en charge de tester le manager de produit.
 
 * @version $Revision$ $Date$
 */
public class ProduitManagerTest
{
    /**
     * ProduitManager à tester.
     */
    private ProduitManager produitManager;

    /**
     * Mock du service de gestion des produits.
     */
    private GenericService<Produit> mockProduitService;

    /**
     * Document manager.
     */
    private GenericDocumentManager<DocumentConditionnement> documentManager;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init()
    {
        this.mockProduitService = Mockito.mock(GenericService.class);
        this.documentManager = Mockito.mock(GenericDocumentManager.class);
        this.produitManager = new ProduitManager(this.mockProduitService);
        this.produitManager.setDocumentsManagers(new TreeMap<String, GenericDocumentManager>());
        this.produitManager
                .getDocumentsManagers()
                .put(TypeDocumentProduit.CONDITIONNEMENT.name(),
                     this.documentManager);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end()
    {
        this.mockProduitService = null;
        this.produitManager = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.produitManager);
        Assert.assertNotNull(this.mockProduitService);
    }

    /**
     * Méthode en charge de tester le changement d'onglet.
     */
    @Test
    public void testOnOngletChangeWithException()
    {
        final TabChangeEvent mockEvent = Mockito.mock(TabChangeEvent.class);
        final Tab tab = Mockito.mock(Tab.class);
        Mockito.when(mockEvent.getTab()).thenReturn(tab);
        Mockito.when(tab.getId()).thenReturn("0");
        this.produitManager.onOngletChange(mockEvent);
        Assert.assertNotNull(this.produitManager.getIndexOngletCourant());
    }

    /**
     * Méthode en charge de tester le changement d'onglet.
     */
    @Test
    public void testOnOngletChange()
    {
        final TabChangeEvent mockEvent = Mockito.mock(TabChangeEvent.class);
        final Tab tab = Mockito.mock(Tab.class);
        Mockito.when(mockEvent.getTab()).thenReturn(tab);
        Mockito.when(tab.getId()).thenReturn(TypeHistoriqueProduit.ONG_PRESCRIPTION.name());
        this.produitManager.onOngletChange(mockEvent);
        Assert.assertNotNull(this.produitManager.getIndexOngletCourant());
    }

    /**
     * Test de la méthode getDocmentManager.
     */
    @Test
    public void testGetDocumentManager()
    {
        Assert.assertNull(this.produitManager.getDocumentManager(TypeDocumentProduit.ETIQUETAGE));
        Assert.assertNotNull(this.produitManager
                .getDocumentManager(TypeDocumentProduit.CONDITIONNEMENT));
    }

}

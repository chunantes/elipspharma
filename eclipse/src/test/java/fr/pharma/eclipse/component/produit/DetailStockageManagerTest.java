package fr.pharma.eclipse.component.produit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.primefaces.model.TreeNode;

import fr.pharma.eclipse.component.stockage.helper.TreeStockageHelper;
import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Classe en charge de tester le manager de detailStockage.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DetailStockageManagerTest {
    /**
     * ProduitManager à tester.
     */
    private DetailStockageManager manager;

    /**
     * Mock du service de gestion des detailsStockage.
     */
    private GenericService<DetailStockage> mockDetailStockageService;

    /**
     * Helper de construction d'arbre de stockage.
     */
    private TreeStockageHelper mockTreeStockageHelper;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.mockDetailStockageService = Mockito.mock(GenericService.class);
        this.manager = new DetailStockageManager(this.mockDetailStockageService);
        this.mockTreeStockageHelper = Mockito.mock(TreeStockageHelper.class);
        this.manager.setTreeStockageHelper(this.mockTreeStockageHelper);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.mockDetailStockageService = null;
        this.manager = null;
        this.mockTreeStockageHelper = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.mockDetailStockageService);
        Assert.assertNotNull(this.mockTreeStockageHelper);
    }

    /**
     * Méthode en charge de tester la méthode de mise à jour de sélection de
     * pharmacie.
     */
    @Test
    public void testMajPharmacie() {
        final DetailStockage stock = new DetailStockage();
        this.manager.setBean(stock);
        this.manager.majPharmacie();
        Assert.assertNull(stock.getStockage());
        Assert.assertNull(this.manager.getNodeSelected());
    }

    /**
     * Méthode en charge de tester la mise à jour du stockage.
     */
    @Test
    public void testUpdateStockageNodeSelectedNull() {
        final DetailStockage stock = new DetailStockage();
        this.manager.setBean(stock);
        this.manager.setNodeSelected(null);
        this.manager.updateStockage();
        Assert.assertNull(stock.getStockage());

    }

    /**
     * Méthode en charge de tester la mise à jour du stockage.
     */
    @Test
    public void testUpdateStockageNodeSelectedNotNull() {
        final DetailStockage stock = new DetailStockage();
        this.manager.setBean(stock);
        final TreeNode nodeSelected = Mockito.mock(TreeNode.class);
        final Stockage stockage = new Stockage();
        stockage.setId(1L);
        Mockito.when(nodeSelected.getData()).thenReturn(stockage);
        this.manager.setNodeSelected(nodeSelected);
        this.manager.updateStockage();
        Assert.assertNotNull(stock.getStockage());
        Assert.assertEquals(stockage, stock.getStockage());
    }

    /**
     * Méthode en charge de tester la récupération des stockages.
     */
    @Test
    public void testGetStockagesSelectableWithPharmaNull() {
        final DetailStockage stock = new DetailStockage();
        this.manager.setBean(stock);
        Assert.assertNull(this.manager.getStockagesSelectable());
    }

    /**
     * Méthode en charge de tester la récupération des stockages.
     */
    @Test
    public void testGetStockagesSelectableWithPharmaNotNull() {
        final DetailStockage stock = Mockito.mock(DetailStockage.class);
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        Mockito.when(stock.getPharmacie()).thenReturn(pharmacie);
        this.manager.setBean(stock);
        this.manager.getStockagesSelectable();
    }

}

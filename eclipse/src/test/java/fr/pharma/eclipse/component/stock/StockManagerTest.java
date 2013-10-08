package fr.pharma.eclipse.component.stock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.primefaces.model.TreeNode;

import fr.pharma.eclipse.component.stockage.helper.TreeStockageHelper;
import fr.pharma.eclipse.domain.criteria.stock.StockSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.detail.DetailLogistique;
import fr.pharma.eclipse.domain.model.stock.EtatLigneStock;
import fr.pharma.eclipse.domain.model.stock.EtatStock;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;

/**
 * Classe en charge de tester le manager de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class StockManagerTest {
    /**
     * Manager de stock à tester.
     */
    private StockManager manager;

    /**
     * Critère de recherche de stock.
     */
    private StockSearchCriteria searchCriteria;

    /**
     * Classe de helper de construction d'arbre de stockage mocké.
     */
    private TreeStockageHelper mockTreeStockageHelper;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.mockTreeStockageHelper = Mockito.mock(TreeStockageHelper.class);
        this.searchCriteria = new StockSearchCriteria();
        this.manager = new StockManager(this.searchCriteria);
        this.manager.setTreeStockageHelper(this.mockTreeStockageHelper);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.searchCriteria = null;
        this.manager = null;
        this.mockTreeStockageHelper = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.searchCriteria);
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.mockTreeStockageHelper);
    }

    /**
     * Méthode en charge de tester la récupération des stockages sélectionnables
     * .
     */
    @Test
    public void testGetStockagesSelectableWithPharmaNull() {
        this.manager.getSearchCriteria().setPharmacie(null);
        Assert.assertNull(this.manager.getStockagesSelectable());
    }

    /**
     * Méthode en charge de tester la récupération des stockages sélectionnables
     * .
     */
    @Test
    public void testGetStockagesSelectableWithPharmaNotNull() {
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        this.manager.getSearchCriteria().setPharmacie(pharmacie);
        final TreeNode stockage = Mockito.mock(TreeNode.class);
        Mockito.when(this.mockTreeStockageHelper.buildTree(pharmacie)).thenReturn(stockage);
        Assert.assertEquals(stockage, this.manager.getStockagesSelectable());
        Mockito.verify(this.mockTreeStockageHelper).buildTree(pharmacie);
    }

    /**
     * Méthode en charge de tester la mise à jour du stockage de recherche.
     */
    @Test
    public void testUpdateStockage() {
        final Stockage stockage = new Stockage();
        stockage.setId(1L);

        final TreeNode nodeSelected = Mockito.mock(TreeNode.class);
        Mockito.when(nodeSelected.getData()).thenReturn(stockage);
        this.manager.setNodeSelected(nodeSelected);

        this.manager.updateStockage();

        Assert.assertEquals(stockage, this.manager.getSearchCriteria().getStockage());
    }

    /**
     * Méthode en charge de tester la suppression du stockage.
     */
    @Test
    public void testDelStockage() {
        this.manager.delStockage();
        Assert.assertNull(this.manager.getSearchCriteria().getStockage());
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM suite à la
     * sélection d'une pharmacie.
     */
    @Test
    public void testHandleSelectPharmacie() {
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        Mockito.when(select.getLocalValue()).thenReturn(pharmacie);

        this.manager.handleSelectPharmacie(event);

        Assert.assertEquals(pharmacie, this.manager.getSearchCriteria().getPharmacie());
        Assert.assertNull(this.manager.getSearchCriteria().getStockage());
    }

    /**
     * Méthode en charge de tester l'affectation de résultats.
     */
    @Test
    public void testSetBeans() {
        final List<EtatStock> beans = new ArrayList<EtatStock>();
        this.manager.setBeans(beans);
        Assert.assertNotNull(this.manager.getBeans());
    }

    /**
     * Méthode en charge de tester l'atteinte du seuil plancher.
     */
    @Test
    public void testSeuilPlancherAtteintKOSeuilNull() {
        final Produit produit = new Medicament();
        final DetailLogistique detailLogistique = new DetailLogistique();
        produit.setDetailLogistique(detailLogistique);
        final EtatStock etatStock = new EtatStock(null, null, produit, null, true);
        Assert.assertFalse(this.manager.seuilPlancherAtteint(etatStock));
    }

    /**
     * Méthode en charge de tester l'atteinte du seuil plancher.
     */
    @Test
    public void testSeuilPlancherAtteintKOSeuil() {
        final Produit produit = new Medicament();
        final DetailLogistique detailLogistique = new DetailLogistique();
        detailLogistique.setStockSeuil(Integer.valueOf(2));
        produit.setDetailLogistique(detailLogistique);
        final EtatStock etatStock = new EtatStock(null, null, produit, null, true);
        etatStock.setQteEnStock(Integer.valueOf(2));
        Assert.assertFalse(this.manager.seuilPlancherAtteint(etatStock));
    }

    /**
     * Méthode en charge de tester l'atteinte du seuil plancher.
     */
    @Test
    public void testSeuilPlancherAtteintOK() {
        final Produit produit = new Medicament();
        final DetailLogistique detailLogistique = new DetailLogistique();
        detailLogistique.setStockSeuil(Integer.valueOf(2));
        produit.setDetailLogistique(detailLogistique);
        final EtatStock etatStock = new EtatStock(null, null, produit, null, true);
        etatStock.setQteEnStock(Integer.valueOf(1));
        Assert.assertTrue(this.manager.seuilPlancherAtteint(etatStock));
    }

    /**
     * Test getter setter ligne.
     */
    @Test
    public void testGetSetBeanSelected() {
        this.manager.setBeanSelected(new EtatStock(new Essai(), new Pharmacie(), new Medicament(), new Conditionnement(), true));
        Assert.assertNotNull(this.manager.getBeanSelected());
    }
    /**
     * Test getter setter ligne.
     */
    @Test
    public void testGetSetLigne() {
        this.manager.setLigne(new EtatLigneStock("", "", Calendar.getInstance(), 2));
        Assert.assertNotNull(this.manager.getLigne());
    }
}

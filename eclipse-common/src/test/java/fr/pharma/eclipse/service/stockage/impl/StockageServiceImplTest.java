package fr.pharma.eclipse.service.stockage.impl;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.comparator.stockage.StockageComparator;
import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.stockage.Stockage;

/**
 * Classe en charge de tester le service de gestion des stockages.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class StockageServiceImplTest {

    /**
     * Service de gestion de stockages à tester.
     */
    private StockageServiceImpl service;

    /**
     * Dao mocké.
     */
    private GenericDao<Stockage> mockDao;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.mockDao = Mockito.mock(GenericDao.class);
        this.service = new StockageServiceImpl(this.mockDao);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.mockDao = null;
        this.service = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.mockDao);
    }

    /**
     * Méthode en charge de tester la récupération du nom d'un stockage sans
     * parent.
     */
    @Test
    public void testGetNomStockageSansParent() {
        final Stockage stockage = new Stockage();
        stockage.setNom("stockage");
        Assert.assertEquals("stockage", this.service.getNomComplet(stockage));
    }

    /**
     * Méthode en charge de tester la récupération du nom d'un stockage avec
     * parent.
     */
    @Test
    public void testGetNomStockageAvecParent() {
        final Stockage parent = new Stockage();
        parent.setNom("parent");
        final Stockage enfant = new Stockage();
        enfant.setNom("enfant");
        enfant.setParent(parent);
        parent.getEnfants().add(enfant);
        Assert.assertEquals("parentenfant", this.service.getNomComplet(enfant));
    }

    /**
     * Méthode en charge de tester la présence d'un stockage dans une liste de
     * stockage.
     */
    @Test
    public void testIsStockageAlreadyPresentKO() {
        final Stockage parent = new Stockage();
        parent.setNom("parent");
        final Stockage enfant = new Stockage();
        enfant.setNom("enfant");
        enfant.setParent(parent);
        parent.getEnfants().add(enfant);

        final SortedSet<Stockage> stockages = new TreeSet<Stockage>(new StockageComparator());
        stockages.add(parent);
        stockages.add(enfant);

        Assert.assertFalse(this.service.isStockageAlreadyPresent(parent, stockages));
    }

    /**
     * Méthode en charge de tester la présence d'un stockage dans une liste de
     * stockage.
     */
    @Test
    public void testIsStockageAlreadyPresentOK() {
        final Stockage parent = new Stockage();
        parent.setNom("parent");
        final Stockage enfant = new Stockage();
        enfant.setNom("enfant");
        enfant.setParent(parent);
        parent.getEnfants().add(enfant);

        final Stockage parentDouble = new Stockage();
        parentDouble.setNom("parent");

        final SortedSet<Stockage> stockages = new TreeSet<Stockage>(new StockageComparator());
        stockages.add(parent);
        stockages.add(enfant);

        Assert.assertTrue(this.service.isStockageAlreadyPresent(parentDouble, stockages));
    }

}

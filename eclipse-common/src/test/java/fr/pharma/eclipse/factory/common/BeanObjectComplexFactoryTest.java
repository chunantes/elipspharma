package fr.pharma.eclipse.factory.common;

import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.recherche.DetailRecherche;

/**
 * Test de la classe BeanObjectComplexFactory.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BeanObjectComplexFactoryTest {
    /**
     * Fabrique à tester.
     */
    private BeanObjectComplexFactory<Essai> factory;

    /**
     * Factory Spring mockée.
     */
    private BeanFactory mockBeanFactory;

    /**
     * Fabrique avec parent mockée pour le détail de la recherche de l'essai.
     */
    private BeanObjectWithParentFactory<DetailRecherche, Essai> mockedSubFactory;

    /**
     * Fabrique sans parent mockée pour le promoteur de l'essai.
     */
    private BeanObjectFactory<Promoteur> mockedSubFactoryWithoutParent;

    /**
     * Détail de recherche mocké.
     */
    private DetailRecherche mockedDetailRecherche;

    /**
     * Promoteur mocké.
     */
    private Promoteur mockedPromoteur;

    /**
     * Propriété dans Essai, de DetailRecherche.
     */
    private final static String DETAIL_IN_ESSAI = "detailRecherche";

    /**
     * Propriété dans Essai, de DetailRecherche.
     */
    private final static String PROMOTEUR_IN_ESSAI = "promoteur";

    /**
     * Méthode d'initialisation.
     */
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        this.mockBeanFactory = Mockito.mock(BeanFactory.class);
        this.mockedDetailRecherche = Mockito.mock(DetailRecherche.class);
        this.mockedPromoteur = Mockito.mock(Promoteur.class);
        this.mockedSubFactory = Mockito.mock(BeanObjectWithParentFactory.class);
        this.mockedSubFactoryWithoutParent = Mockito.mock(BeanObjectFactory.class);
        this.factory = new BeanObjectComplexFactory<Essai>(Essai.class);
        this.factory.setBeanFactory(this.mockBeanFactory);
        final SortedMap<String, BeanObjectWithParentFactory<? extends BeanObject, Essai>> mapFactories =
            new TreeMap<String, BeanObjectWithParentFactory<? extends BeanObject, Essai>>();
        mapFactories.put(BeanObjectComplexFactoryTest.DETAIL_IN_ESSAI, this.mockedSubFactory);

        final SortedMap<String, BeanObjectFactory<? extends BeanObject>> mapFactoriesWithoutParent = new TreeMap<String, BeanObjectFactory<? extends BeanObject>>();
        mapFactoriesWithoutParent.put(BeanObjectComplexFactoryTest.PROMOTEUR_IN_ESSAI, this.mockedSubFactoryWithoutParent);
        this.factory.setMapFactories(mapFactories);
        this.factory.setMapFactoriesWithoutParent(mapFactoriesWithoutParent);
        Mockito.when(this.mockedSubFactory.getInitializedObject(Matchers.any(Essai.class))).thenReturn(this.mockedDetailRecherche);
        Mockito.when(this.mockedSubFactoryWithoutParent.getInitializedObject()).thenReturn(this.mockedPromoteur);
    }
    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown() {
        this.factory = null;
        this.mockBeanFactory = null;
        this.mockedDetailRecherche = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.factory);
        Assert.assertNotNull(this.mockBeanFactory);
        Assert.assertNotNull(this.mockedDetailRecherche);
        Assert.assertNotNull(this.mockedSubFactory);
        Assert.assertNotNull(this.mockedSubFactoryWithoutParent);
    }

    /**
     * Test de la méthode getInitializedObject() - pas de map.
     */
    @Test
    public void testGetInitializedObjectNoMap() {
        final String expectedBeanName = Essai.class.getSimpleName();
        final Essai expectedEssai = new Essai();
        expectedEssai.setId(1L);
        Mockito.when(this.mockBeanFactory.getBean(expectedBeanName)).thenReturn(expectedEssai);

        this.factory.setMapFactories(null);
        final Essai created = this.factory.getInitializedObject();

        Mockito.verify(this.mockBeanFactory).getBean(expectedBeanName);
        Assert.assertEquals(expectedEssai, created);
        Assert.assertNull(created.getDetailRecherche());
        Assert.assertNull(created.getPromoteur());
    }

    /**
     * Test de la méthode getInitializedObject().
     */
    @Test
    public void testGetInitializedObject() {
        final String expectedBeanName = Essai.class.getSimpleName();
        final Essai expectedEssai = new Essai();
        expectedEssai.setId(1L);
        Mockito.when(this.mockBeanFactory.getBean(expectedBeanName)).thenReturn(expectedEssai);

        final Essai created = this.factory.getInitializedObject();

        Mockito.verify(this.mockBeanFactory).getBean(expectedBeanName);
        Mockito.verify(this.mockedSubFactory).getInitializedObject(expectedEssai);
        Mockito.verify(this.mockedSubFactoryWithoutParent).getInitializedObject();
        Assert.assertEquals(expectedEssai, created);
        Assert.assertEquals(this.mockedDetailRecherche, created.getDetailRecherche());
        Assert.assertEquals(this.mockedPromoteur, created.getPromoteur());
    }
}

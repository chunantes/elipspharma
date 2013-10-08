package fr.pharma.eclipse.factory.common;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.recherche.DetailRecherche;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Test de la classe BeanObjectWithParentFactory.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BeanObjectWithParentFactoryTest {
    /**
     * Fabrique testée.
     */
    private BeanObjectWithParentFactory<DetailRecherche, Essai> factory;

    /**
     * Fabrique simple mockée.
     */
    private BeanObjectFactory<DetailRecherche> mockedSimpleFactory;

    /**
     * Mock de l'objet parent.
     */
    private Essai mockedEssai;

    /**
     * Constante pour la prorpiété de la fabrique.
     */
    private static final String ESSAI_IN_DETAIL = "essai";

    /**
     * Initialisation.
     */
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        this.mockedEssai = Mockito.mock(Essai.class);
        this.mockedSimpleFactory = Mockito.mock(BeanObjectFactory.class);

        this.factory = new BeanObjectWithParentFactory<DetailRecherche, Essai>();
        this.factory.setSimpleFactory(this.mockedSimpleFactory);
    }

    /**
     * Finalisation.
     */
    @After
    public void tearDown() {
        this.factory = null;
        this.mockedSimpleFactory = null;
    }

    /**
     * Test de la méthode getInitializedObject - sans propriété.
     */
    @Test
    public void testInitializeObjectNoProperty() {
        final DetailRecherche expected = this.getExpectedDetail();
        this.initMockDetail(expected);
        final DetailRecherche bean = this.factory.getInitializedObject(this.mockedEssai);
        Mockito.verify(this.mockedSimpleFactory).getInitializedObject();
        Assert.assertEquals(expected, bean);
    }

    /**
     * Test de la méthode getInitializedObject - avec propriété.
     */
    @Test
    public void testInitializeObjectWithProperty() {
        this.factory.setPropertyToStoreParent(BeanObjectWithParentFactoryTest.ESSAI_IN_DETAIL);
        final DetailRecherche expected = this.getExpectedDetail();
        this.initMockDetail(expected);
        final DetailRecherche bean = this.factory.getInitializedObject(this.mockedEssai);
        Mockito.verify(this.mockedSimpleFactory).getInitializedObject();
        Assert.assertEquals(expected, bean);
        Assert.assertEquals(this.mockedEssai, BeanTool.getPropriete(bean, BeanObjectWithParentFactoryTest.ESSAI_IN_DETAIL));
    }

    /**
     * Initialise le mock de la fabrique simple.
     * @param detailRecherche Detail recherche à retourner.
     */
    private void initMockDetail(final DetailRecherche detailRecherche) {
        Mockito.when(this.mockedSimpleFactory.getInitializedObject()).thenReturn(detailRecherche);
    }

    /**
     * Crée un détail test.
     * @return DetailRecherche.
     */
    private DetailRecherche getExpectedDetail() {
        final DetailRecherche detail = new DetailRecherche();
        detail.setId(1L);
        return detail;
    }

}

package fr.pharma.eclipse.factory.stock;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.stock.RetourPromoteur;

/**
 * Test de la fabrique de Retour Promoteur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class RetourPromoteurFactoryTest {
    /**
     * Fabrique testée.
     */
    private MvtStockFactory<RetourPromoteur> factory;

    /**
     * Factory Spring mockée.
     */
    private BeanFactory mockedBeanFactory;

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void init() {
        this.mockedBeanFactory = Mockito.mock(BeanFactory.class);
        this.factory = new MvtStockFactory<RetourPromoteur>(RetourPromoteur.class, TypeMvtStock.RETOUR_PROMOTEUR);
        this.factory.setBeanFactory(this.mockedBeanFactory);
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void end() {
        this.factory = null;
        this.mockedBeanFactory = null;
    }

    /**
     * Test de la méthode getInitializedObject.
     */
    @Test
    public void testGetInitializedObject() {
        final RetourPromoteur expectedRetourPromoteur = Mockito.mock(RetourPromoteur.class);
        final String expectedClassName = RetourPromoteur.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(expectedRetourPromoteur);

        final RetourPromoteur created = this.factory.getInitializedObject();
        Mockito.verify(this.mockedBeanFactory).getBean(expectedClassName);
        Assert.assertEquals(expectedRetourPromoteur, created);
        Mockito.verify(expectedRetourPromoteur).setType(TypeMvtStock.RETOUR_PROMOTEUR);
    }

}

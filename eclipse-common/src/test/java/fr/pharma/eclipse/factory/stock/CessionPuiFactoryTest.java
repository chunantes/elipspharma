package fr.pharma.eclipse.factory.stock;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.stock.CessionPui;

/**
 * Test de la fabrique de Cession PUI.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class CessionPuiFactoryTest {
    /**
     * Fabrique testée.
     */
    private MvtStockFactory<CessionPui> factory;

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
        this.factory = new MvtStockFactory<CessionPui>(CessionPui.class, TypeMvtStock.CESSION_PUI);
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
        final CessionPui expectedCessionPui = Mockito.mock(CessionPui.class);
        final String expectedClassName = CessionPui.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(expectedCessionPui);

        final CessionPui created = this.factory.getInitializedObject();
        Mockito.verify(this.mockedBeanFactory).getBean(expectedClassName);
        Assert.assertEquals(expectedCessionPui, created);
        Mockito.verify(expectedCessionPui).setType(TypeMvtStock.CESSION_PUI);
    }

}

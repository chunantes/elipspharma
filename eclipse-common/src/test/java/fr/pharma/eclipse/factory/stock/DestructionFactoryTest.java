package fr.pharma.eclipse.factory.stock;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.stock.Destruction;

/**
 * Test de la fabrique de Destruction.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DestructionFactoryTest {
    /**
     * Fabrique testée.
     */
    private MvtStockFactory<Destruction> factory;

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
        this.factory = new MvtStockFactory<Destruction>(Destruction.class, TypeMvtStock.DESTRUCTION);
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
        final Destruction expectedDestruction = Mockito.mock(Destruction.class);
        final String expectedClassName = Destruction.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(expectedDestruction);

        final Destruction created = this.factory.getInitializedObject();
        Mockito.verify(this.mockedBeanFactory).getBean(expectedClassName);
        Assert.assertEquals(expectedDestruction, created);
        Mockito.verify(expectedDestruction).setType(TypeMvtStock.DESTRUCTION);
    }

}

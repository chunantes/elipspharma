package fr.pharma.eclipse.factory.stock;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.stock.DispensationGlobale;

/**
 * Test de la fabrique de DispensationGlobale.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationGlobaleFactoryTest {
    /**
     * Fabrique testée.
     */
    private MvtStockFactory<DispensationGlobale> factory;

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
        this.factory = new MvtStockFactory<DispensationGlobale>(DispensationGlobale.class, TypeMvtStock.DOTATION);
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
        final DispensationGlobale expectedDispensationGlobale = Mockito.mock(DispensationGlobale.class);
        final String expectedClassName = DispensationGlobale.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(expectedDispensationGlobale);

        final DispensationGlobale created = this.factory.getInitializedObject();
        Mockito.verify(this.mockedBeanFactory).getBean(expectedClassName);
        Assert.assertEquals(expectedDispensationGlobale, created);
        Mockito.verify(expectedDispensationGlobale).setType(TypeMvtStock.DOTATION);
    }

}

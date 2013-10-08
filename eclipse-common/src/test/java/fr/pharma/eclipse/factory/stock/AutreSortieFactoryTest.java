package fr.pharma.eclipse.factory.stock;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.stock.AutreSortie;

/**
 * Test de la fabrique de AutreSortie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AutreSortieFactoryTest {
    /**
     * Fabrique testée.
     */
    private MvtStockFactory<AutreSortie> factory;

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
        this.factory = new MvtStockFactory<AutreSortie>(AutreSortie.class, TypeMvtStock.AUTRE_SORTIE);
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
        final AutreSortie expectedAutreSortie = Mockito.mock(AutreSortie.class);
        final String expectedClassName = AutreSortie.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(expectedAutreSortie);

        final AutreSortie created = this.factory.getInitializedObject();
        Mockito.verify(this.mockedBeanFactory).getBean(expectedClassName);
        Assert.assertEquals(expectedAutreSortie, created);
        Mockito.verify(expectedAutreSortie).setType(TypeMvtStock.AUTRE_SORTIE);
    }

}

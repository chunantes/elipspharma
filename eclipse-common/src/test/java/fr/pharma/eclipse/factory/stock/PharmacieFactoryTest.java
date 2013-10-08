package fr.pharma.eclipse.factory.stock;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Test de la fabrique de Pharmacie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PharmacieFactoryTest {

    /**
     * Fabrique testée.
     */
    private PharmacieFactory factory;

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
        this.factory = new PharmacieFactory(Pharmacie.class);
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
        final Pharmacie expectedPharmacie = Mockito.mock(Pharmacie.class);
        final String expectedClassName = Pharmacie.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(expectedPharmacie);

        final Pharmacie created = this.factory.getInitializedObject();
        Mockito.verify(this.mockedBeanFactory).getBean(expectedClassName);
        Assert.assertEquals(expectedPharmacie, created);
        Mockito.verify(expectedPharmacie).setNumOrdonnancierDisp(0);
        Mockito.verify(expectedPharmacie).setNumOrdonnancierFab(0);
    }
}

package fr.pharma.eclipse.factory.acteur;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.Cro;

/**
 * Test de la fabrique de Cro.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class CroFactoryTest {
    /**
     * Fabrique testée.
     */
    private PersonneFactory<Cro> factory;

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
        this.factory = new PersonneFactory<Cro>(Cro.class, TypePersonne.CRO);
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
        final Cro expectedCro = Mockito.mock(Cro.class);
        final String expectedClassName = Cro.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(expectedCro);

        final Cro created = this.factory.getInitializedObject();
        Mockito.verify(this.mockedBeanFactory).getBean(expectedClassName);
        Assert.assertEquals(expectedCro, created);
        Mockito.verify(expectedCro).setType(TypePersonne.CRO);
    }

}

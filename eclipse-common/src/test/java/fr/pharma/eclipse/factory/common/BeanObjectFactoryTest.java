package fr.pharma.eclipse.factory.common;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Classe en charge de tester la factory de BeanObject.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BeanObjectFactoryTest {

    /**
     * BeanObjectFactory à tester.
     */
    private BeanObjectFactory<Pharmacie> factory;

    /**
     * Factory Spring mockée.
     */
    private BeanFactory mockBeanFactory;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.factory = new BeanObjectFactory<Pharmacie>(Pharmacie.class);
        this.mockBeanFactory = Mockito.mock(BeanFactory.class);
        this.factory.setBeanFactory(this.mockBeanFactory);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.factory = null;
        this.mockBeanFactory = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.factory);
        Assert.assertNotNull(this.mockBeanFactory);
    }

    /**
     * Méthode en charge tester l'initialisation de l'objet.
     */
    @Test
    public void testGetInitializedObject() {
        final Pharmacie pharmacieExpected = new Pharmacie();
        pharmacieExpected.setId(1L);
        Mockito.when(this.mockBeanFactory.getBean(Pharmacie.class.getSimpleName())).thenReturn(pharmacieExpected);
        final Pharmacie pharmacie = this.factory.getInitializedObject();
        Mockito.verify(this.mockBeanFactory).getBean(Pharmacie.class.getSimpleName());
        Assert.assertEquals(pharmacieExpected, pharmacie);
    }

}

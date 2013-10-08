package fr.pharma.eclipse.factory.acteur;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.ContactPromoteur;

/**
 * Test de la fabrique de ContactPromoteur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ContactPromoteurFactoryTest {
    /**
     * Fabrique testée.
     */
    private PersonneFactory<ContactPromoteur> factory;

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
        this.factory = new PersonneFactory<ContactPromoteur>(ContactPromoteur.class, TypePersonne.PROMOTEUR);
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
        final ContactPromoteur expectedContactPromoteur = Mockito.mock(ContactPromoteur.class);
        final String expectedClassName = ContactPromoteur.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(expectedContactPromoteur);

        final ContactPromoteur created = this.factory.getInitializedObject();
        Mockito.verify(this.mockedBeanFactory).getBean(expectedClassName);
        Assert.assertEquals(expectedContactPromoteur, created);
        Mockito.verify(expectedContactPromoteur).setType(TypePersonne.PROMOTEUR);
    }

}

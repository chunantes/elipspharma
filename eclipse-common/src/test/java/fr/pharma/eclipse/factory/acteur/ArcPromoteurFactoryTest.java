package fr.pharma.eclipse.factory.acteur;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.ArcPromoteur;

/**
 * Test de la fabrique de ArcPromoteur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ArcPromoteurFactoryTest {
    /**
     * Fabrique testée.
     */
    private PersonneFactory<ArcPromoteur> factory;

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
        this.factory = new PersonneFactory<ArcPromoteur>(ArcPromoteur.class, TypePersonne.ARC_PROMOTEUR);
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
        final ArcPromoteur expectedArcPromoteur = Mockito.mock(ArcPromoteur.class);
        final String expectedClassName = ArcPromoteur.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(expectedArcPromoteur);

        final ArcPromoteur created = this.factory.getInitializedObject();
        Mockito.verify(this.mockedBeanFactory).getBean(expectedClassName);
        Assert.assertEquals(expectedArcPromoteur, created);
        Mockito.verify(expectedArcPromoteur).setType(TypePersonne.ARC_PROMOTEUR);
    }

}

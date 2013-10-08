package fr.pharma.eclipse.factory.essai;

import java.util.Calendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Test de la fabrique EssaiFactory.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiFactoryTest {
    /**
     * Fabrique testée.
     */
    private EssaiFactory factory;

    /**
     * Factory Spring mockée.
     */
    private BeanFactory mockedBeanFactory;

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void setUp() {
        this.mockedBeanFactory = Mockito.mock(BeanFactory.class);
        this.factory = new EssaiFactory(Essai.class);
        this.factory.setBeanFactory(this.mockedBeanFactory);
    }
    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown() {
        this.factory = null;
        this.mockedBeanFactory = null;
    }

    /**
     * Test de la méthode getInitializedObject.
     */
    @Test
    public void testGetInitializedObject() {
        final Essai expectedEssai = Mockito.mock(Essai.class);
        final String expectedClassName = Essai.class.getSimpleName();
        final int expectedAnnee = Calendar.getInstance(EclipseConstants.LOCALE).get(Calendar.YEAR);
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(expectedEssai);

        final Essai created = this.factory.getInitializedObject();
        Mockito.verify(this.mockedBeanFactory).getBean(expectedClassName);
        Assert.assertEquals(expectedEssai, created);
        Mockito.verify(expectedEssai).setEtat(EtatEssai.EN_EVALUATION);
        Mockito.verify(expectedEssai).setAnneeCreation(expectedAnnee);
        Mockito.verify(expectedEssai).setAlerteActive(Boolean.TRUE);
    }

}

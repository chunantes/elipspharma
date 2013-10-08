package fr.pharma.eclipse.factory.evenement;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import fr.pharma.eclipse.domain.model.suivi.evenement.EvenementSuivi;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;

/**
 * Classe en charge de tester la factory de bean de suivi de modifications.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EvenementSuiviFactoryTest {
    /**
     * SuiviFactory à tester.
     */
    private SuiviFactory<EvenementSuivi> factory;

    /**
     * Factory Spring mockée.
     */
    private BeanFactory mockBeanFactory;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.factory = new SuiviFactory<EvenementSuivi>(EvenementSuivi.class);
        this.mockBeanFactory = Mockito.mock(BeanFactory.class);
        this.factory.setBeanFactory(this.mockBeanFactory);
        final SecurityContext mockSecurityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(mockSecurityContext);
        final Authentication mockAuthentication = Mockito.mock(Authentication.class);
        Mockito.when(mockSecurityContext.getAuthentication()).thenReturn(mockAuthentication);
        Mockito.when(mockAuthentication.getName()).thenReturn("admin");
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
     * Méthode en charge de tester la récupération du bean EvenementSuivi.
     */
    @Test
    public void testGetInitializedObject() {
        final EvenementSuivi evtSuiviExpected = new EvenementSuivi();
        evtSuiviExpected.setId(1L);
        Mockito.when(this.mockBeanFactory.getBean(EvenementSuivi.class.getSimpleName())).thenReturn(evtSuiviExpected);
        final EvenementSuivi evtSuivi = this.factory.getInitializedObject();
        Mockito.verify(this.mockBeanFactory).getBean(EvenementSuivi.class.getSimpleName());
        Assert.assertEquals(evtSuiviExpected, evtSuivi);
        Assert.assertNotNull(evtSuivi.getDateMaj());
        Assert.assertEquals("admin", evtSuivi.getMajPar());
        Assert.assertNull(evtSuivi.getEvenement());
    }

}

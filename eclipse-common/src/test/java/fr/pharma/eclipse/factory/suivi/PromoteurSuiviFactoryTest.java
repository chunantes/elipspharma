package fr.pharma.eclipse.factory.suivi;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import fr.pharma.eclipse.domain.model.suivi.acteur.PromoteurSuivi;

/**
 * Classe en charge de tester la factory de bean de suivi de modifications.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PromoteurSuiviFactoryTest {
    /**
     * SuiviFactory à tester.
     */
    private SuiviFactory<PromoteurSuivi> factory;

    /**
     * Factory Spring mockée.
     */
    private BeanFactory mockBeanFactory;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.factory = new SuiviFactory<PromoteurSuivi>(PromoteurSuivi.class);
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
     * Méthode en charge de tester la récupération du bean PromoteurSuivi.
     */
    @Test
    public void testGetInitializedObject() {
        final PromoteurSuivi promoteurSuiviExpected = new PromoteurSuivi();
        promoteurSuiviExpected.setId(1L);
        Mockito.when(this.mockBeanFactory.getBean(PromoteurSuivi.class.getSimpleName())).thenReturn(promoteurSuiviExpected);
        final PromoteurSuivi promoteurSuivi = this.factory.getInitializedObject();
        Mockito.verify(this.mockBeanFactory).getBean(PromoteurSuivi.class.getSimpleName());
        Assert.assertEquals(promoteurSuiviExpected, promoteurSuivi);
        Assert.assertNotNull(promoteurSuivi.getDateMaj());
        Assert.assertEquals("admin", promoteurSuivi.getMajPar());
        Assert.assertNull(promoteurSuivi.getPromoteur());
    }

}

package fr.pharma.eclipse.factory.habilitation;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.service.user.UserService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Test de la classe HabilitationFactory.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class HabilitationFactoryTest extends AbstractEclipseJUnitTest {
    /**
     * Fabrique testée.
     */
    private HabilitationFactory factory;

    /**
     * Factory Spring mockée.
     */
    private BeanFactory mockedBeanFactory;

    /**
     * Mock du service des utilisateurs.
     */
    private UserService mockedUserService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedUserService = Mockito.mock(UserService.class);
        this.mockedBeanFactory = Mockito.mock(BeanFactory.class);
        this.factory = new HabilitationFactory(Habilitation.class);
        this.factory.setBeanFactory(this.mockedBeanFactory);
        this.factory.setUserService(this.mockedUserService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.factory = null;
        this.mockedUserService = null;
        this.mockedBeanFactory = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.factory);
        Assert.assertNotNull(this.mockedBeanFactory);
        Assert.assertEquals(this.mockedUserService, this.factory.getUserService());
    }

    /**
     * Test de la méthode getInitializedObject.
     */
    @Test
    public void testGetInitializedObject() {
        final Habilitation expectedHabilitation = Mockito.mock(Habilitation.class);
        final String expectedClassName = Habilitation.class.getSimpleName();
        final String expectedLogin = "SRM";
        final Personne expectedPersonne = new Pharmacien();
        expectedPersonne.setId(1L);
        expectedPersonne.setLogin(expectedLogin);

        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(expectedHabilitation);
        final Answer<Object> answerCalendar = new Answer<Object>() {

            @Override
            public Object answer(final InvocationOnMock invocation) throws Throwable {
                final Calendar arg = (Calendar) invocation.getArguments()[0];
                final String patternToCheck = "yyyyMMddHH";
                Assert.assertEquals(Utils.formatDate(Calendar.getInstance(EclipseConstants.LOCALE).getTime(), patternToCheck), Utils.formatDate(arg.getTime(), patternToCheck));
                return null;
            }
        };
        Mockito.doAnswer(answerCalendar).when(expectedHabilitation).setDateCreation(Matchers.any(Calendar.class));
        Mockito.when(this.mockedUserService.getPersonne()).thenReturn(expectedPersonne);

        final Habilitation actualHabilitation = this.factory.getInitializedObject();
        Mockito.verify(this.mockedBeanFactory).getBean(expectedClassName);
        Mockito.verify(expectedHabilitation).setActive(true);
        Mockito.verify(expectedHabilitation).setDesactivable(true);
        Mockito.verify(expectedHabilitation).setDateCreation(Matchers.any(Calendar.class));
        Mockito.verify(expectedHabilitation).setAuteurCreation(expectedLogin);
        Assert.assertEquals(expectedHabilitation, actualHabilitation);

    }

}

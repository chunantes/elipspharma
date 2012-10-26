package fr.pharma.eclipse.component.essai.seeker.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.component.essai.TypeContact;
import fr.pharma.eclipse.domain.criteria.acteur.ArcInvestigateurSearchCriteria;
import fr.pharma.eclipse.domain.model.acteur.ArcInvestigateur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.ServiceUtils;

/**
 * Test de la classe ArcInvestigateurSeeker.
 
 * @version $Revision$ $Date$
 */
public class ArcInvestigateurSeekerTest
    extends AbstractEclipseJUnitTest
{
    /**
     * Elément testé.
     */
    private ArcInvestigateurSeeker seeker;

    /**
     * Fabrique Spring mockée.
     */
    private BeanFactory mockedBeanFactory;

    /**
     * Service mocké.
     */
    private GenericService<ArcInvestigateur> mockedService;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setUp()
    {
        this.mockedBeanFactory = Mockito.mock(BeanFactory.class);
        this.mockedService = Mockito.mock(GenericService.class);
        this.seeker = new ArcInvestigateurSeeker();
        this.seeker.setBeanFactory(this.mockedBeanFactory);
        this.seeker.setService(this.mockedService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown()
    {
        this.seeker = null;
        this.mockedBeanFactory = null;
        this.mockedService = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit()
    {
        Assert.assertNotNull(this.seeker);
        Assert.assertEquals(this.mockedBeanFactory,
                            this.seeker.getBeanFactory());
        Assert.assertEquals(this.mockedService,
                            this.seeker.getService());
    }
    /**
     * Test de la méthode supports.
     */
    @Test
    public void testSupports()
    {
        this.verifySupports(Arrays.asList(TypeContact.ARC_INVESTIGATEUR));
    }

    /**
     * Méthode de vérification du support.
     * @param expectedSupports Liste des supports attendus.
     */
    private void verifySupports(final List<TypeContact> expectedSupports)
    {
        for (final TypeContact typeContact : TypeContact.values())
        {
            Assert.assertEquals(expectedSupports.contains(typeContact),
                                this.seeker.supports(typeContact));
        }
    }

    /**
     * Test de la méthode getContacts.
     */
    @Test
    public void testGetContacts()
    {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final String expectedBeanName = "arcInvestigateurCriteria";

        final ArcInvestigateur personne1 = Mockito.mock(ArcInvestigateur.class);
        final ArcInvestigateur personne2 = Mockito.mock(ArcInvestigateur.class);
        final List<ArcInvestigateur> expectedList = Arrays.asList(personne1,
                                                                  personne2);

        final Service service1 = ServiceUtils.makeServiceTest(id++);
        final Service service2 = ServiceUtils.makeServiceTest(id++);
        essai.getServices().add(service1);
        essai.getServices().add(service2);

        final ArcInvestigateurSearchCriteria expectedCriteria =
            Mockito.mock(ArcInvestigateurSearchCriteria.class);
        Mockito
                .when(this.mockedBeanFactory.getBean(expectedBeanName))
                .thenReturn(expectedCriteria);
        Mockito.doAnswer(new Answer<Object>() {

            @SuppressWarnings("unchecked")
            @Override
            public Object answer(final InvocationOnMock invocation)
                throws Throwable
            {
                final List<Service> argServices = (List<Service>) invocation.getArguments()[0];
                Assert.assertEquals(2,
                                    argServices.size());
                Assert.assertTrue(argServices.contains(service1));
                Assert.assertTrue(argServices.contains(service2));
                return null;
            }
        }).when(expectedCriteria).setServices(Matchers.anyListOf(Service.class));
        Mockito.when(this.mockedService.getAll(expectedCriteria)).thenReturn(expectedList);

        final List<Personne> res = this.seeker.getContacts(essai);
        Mockito.verify(this.mockedBeanFactory).getBean(expectedBeanName);
        Mockito.verify(expectedCriteria).setServices(Matchers.anyListOf(Service.class));
        Mockito.verify(this.mockedService).getAll(expectedCriteria);
        Assert.assertEquals(expectedList.size(),
                            res.size());
        for (final Personne expectedPersonne : expectedList)
        {
            Assert.assertTrue(res.contains(expectedPersonne));
        }

    }
}

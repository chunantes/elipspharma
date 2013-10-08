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
import fr.pharma.eclipse.domain.criteria.acteur.PharmacienSearchCriteria;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.PharmacieUtils;

/**
 * Test de la classe PharmaciensSeeker.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PharmaciensSeekerTest extends AbstractEclipseJUnitTest {
    /**
     * Elément testé.
     */
    private PharmaciensSeeker seeker;

    /**
     * Fabrique Spring mockée.
     */
    private BeanFactory mockedBeanFactory;

    /**
     * Service mocké.
     */
    private GenericService<Pharmacien> mockedService;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setUp() {
        this.mockedBeanFactory = Mockito.mock(BeanFactory.class);
        this.mockedService = Mockito.mock(GenericService.class);
        this.seeker = new PharmaciensSeeker();
        this.seeker.setBeanFactory(this.mockedBeanFactory);
        this.seeker.setService(this.mockedService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.seeker = null;
        this.mockedBeanFactory = null;
        this.mockedService = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.seeker);
        Assert.assertEquals(this.mockedBeanFactory, this.seeker.getBeanFactory());
        Assert.assertEquals(this.mockedService, this.seeker.getService());
    }

    /**
     * Test de la méthode supports.
     */
    @Test
    public void testSupports() {
        this.verifySupports(Arrays.asList(TypeContact.PHARMACIEN));
    }

    /**
     * Méthode de vérification du support.
     * @param expectedSupports Liste des supports attendus.
     */
    private void verifySupports(final List<TypeContact> expectedSupports) {
        for (final TypeContact typeContact : TypeContact.values()) {
            Assert.assertEquals(expectedSupports.contains(typeContact), this.seeker.supports(typeContact));
        }
    }

    /**
     * Test de la méthode getSearchCriteria.
     */
    @Test
    public void testGetSearchCriteria() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final String expectedBeanName = "pharmacienCriteria";

        final Pharmacien personne1 = Mockito.mock(Pharmacien.class);
        final Pharmacien personne2 = Mockito.mock(Pharmacien.class);
        final List<Pharmacien> expectedList = Arrays.asList(personne1, personne2);

        final Pharmacie pharmaPrincipale = PharmacieUtils.makePharmacieTest(id++);
        final Pharmacie pharmaCo1 = PharmacieUtils.makePharmacieTest(id++);
        final Pharmacie pharmaCo2 = PharmacieUtils.makePharmacieTest(id++);
        essai.setPharmaciePrincipale(pharmaPrincipale);
        essai.getDetailDonneesPharma().getPharmacies().addAll(Arrays.asList(pharmaCo1, pharmaCo2));

        final PharmacienSearchCriteria expectedCriteria = Mockito.mock(PharmacienSearchCriteria.class);
        Mockito.when(this.mockedBeanFactory.getBean(expectedBeanName)).thenReturn(expectedCriteria);
        Mockito.doAnswer(new Answer<Object>() {

            @SuppressWarnings("unchecked")
            @Override
            public Object answer(final InvocationOnMock invocation) throws Throwable {
                final List<Pharmacie> argPharmas = (List<Pharmacie>) invocation.getArguments()[0];
                Assert.assertEquals(3, argPharmas.size());
                Assert.assertTrue(argPharmas.contains(pharmaPrincipale));
                Assert.assertTrue(argPharmas.contains(pharmaCo1));
                Assert.assertTrue(argPharmas.contains(pharmaCo2));
                return null;
            }
        }).when(expectedCriteria).setPharmacies(Matchers.anyListOf(Pharmacie.class));
        Mockito.when(this.mockedService.getAll(expectedCriteria)).thenReturn(expectedList);

        final List<Personne> res = this.seeker.getContacts(essai);
        Mockito.verify(this.mockedBeanFactory).getBean(expectedBeanName);
        Mockito.verify(expectedCriteria).setPharmacies(Matchers.anyListOf(Pharmacie.class));
        Mockito.verify(this.mockedService).getAll(expectedCriteria);
        Assert.assertEquals(expectedList.size(), res.size());
        for (final Personne expectedPersonne : expectedList) {
            Assert.assertTrue(res.contains(expectedPersonne));
        }
    }
}

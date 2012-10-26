package fr.pharma.eclipse.component.essai.seeker.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.component.essai.TypeContact;
import fr.pharma.eclipse.domain.criteria.acteur.PersonneSearchCriteria;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.Cro;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe CroSeeker.
 
 * @version $Revision$ $Date$
 */
public class CroSeekerTest
    extends AbstractEclipseJUnitTest
{
    /**
     * Elément testé.
     */
    private CroSeeker seeker;

    /**
     * Fabrique Spring mockée.
     */
    private BeanFactory mockedBeanFactory;

    /**
     * Service mocké.
     */
    private GenericService<Cro> mockedService;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setUp()
    {
        this.mockedBeanFactory = Mockito.mock(BeanFactory.class);
        this.mockedService = Mockito.mock(GenericService.class);
        this.seeker = new CroSeeker();
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
        this.verifySupports(Arrays.asList(TypeContact.CRO));
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
        final String expectedBeanName = "personneCriteria";

        final Cro personne1 = Mockito.mock(Cro.class);
        final Cro personne2 = Mockito.mock(Cro.class);
        final List<Cro> expectedList = Arrays.asList(personne1,
                                                     personne2);

        final PersonneSearchCriteria expectedCriteria =
            Mockito.mock(PersonneSearchCriteria.class);
        Mockito
                .when(this.mockedBeanFactory.getBean(expectedBeanName))
                .thenReturn(expectedCriteria);
        Mockito.when(this.mockedService.getAll(expectedCriteria)).thenReturn(expectedList);

        final List<Personne> res = this.seeker.getContacts(essai);
        Mockito.verify(this.mockedBeanFactory).getBean(expectedBeanName);
        Mockito.verify(expectedCriteria).setTypePersonne(TypePersonne.CRO);
        Mockito.verify(this.mockedService).getAll(expectedCriteria);
        Assert.assertEquals(expectedList.size(),
                            res.size());
        for (final Personne expectedPersonne : expectedList)
        {
            Assert.assertTrue(res.contains(expectedPersonne));
        }
    }

}

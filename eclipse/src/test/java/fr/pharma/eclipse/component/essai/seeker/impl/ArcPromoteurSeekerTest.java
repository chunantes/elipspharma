package fr.pharma.eclipse.component.essai.seeker.impl;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.component.essai.TypeContact;
import fr.pharma.eclipse.domain.criteria.acteur.ArcPromoteurSearchCriteria;
import fr.pharma.eclipse.domain.model.acteur.ArcPromoteur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe ArcPromoteurSeeker.
 
 * @version $Revision$ $Date$
 */
public class ArcPromoteurSeekerTest
    extends AbstractEclipseJUnitTest
{
    /**
     * Elément testé.
     */
    private ArcPromoteurSeeker seeker;

    /**
     * Fabrique Spring mockée.
     */
    private BeanFactory mockedBeanFactory;

    /**
     * Service mocké.
     */
    private GenericService<ArcPromoteur> mockedService;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setUp()
    {
        this.mockedBeanFactory = Mockito.mock(BeanFactory.class);
        this.mockedService = Mockito.mock(GenericService.class);
        this.seeker = new ArcPromoteurSeeker();
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
        this.verifySupports(Arrays.asList(TypeContact.ARC_PROMOTEUR));
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
        final String expectedBeanName = "arcPromoteurCriteria";
        final Promoteur expectedPromoteur = Mockito.mock(Promoteur.class);
        essai.setPromoteur(expectedPromoteur);
        final ArcPromoteurSearchCriteria expectedCriteria =
            Mockito.mock(ArcPromoteurSearchCriteria.class);

        final ArcPromoteur personne1 = Mockito.mock(ArcPromoteur.class);
        final ArcPromoteur personne2 = Mockito.mock(ArcPromoteur.class);
        final List<ArcPromoteur> expectedList = Arrays.asList(personne1,
                                                              personne2);

        Mockito
                .when(this.mockedBeanFactory.getBean(expectedBeanName))
                .thenReturn(expectedCriteria);
        Mockito.when(this.mockedService.getAll(expectedCriteria)).thenReturn(expectedList);

        final List<Personne> res = this.seeker.getContacts(essai);
        Mockito.verify(this.mockedBeanFactory).getBean(expectedBeanName);
        Mockito.verify(expectedCriteria).setPromoteur(expectedPromoteur);
        Mockito.verify(this.mockedService).getAll(expectedCriteria);
        Assert.assertEquals(expectedList.size(),
                            res.size());
        for (final Personne expectedPersonne : expectedList)
        {
            Assert.assertTrue(res.contains(expectedPersonne));
        }
    }

}

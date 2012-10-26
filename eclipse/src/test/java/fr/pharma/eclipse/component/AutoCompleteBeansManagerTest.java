package fr.pharma.eclipse.component;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import fr.pharma.eclipse.domain.criteria.essai.EssaiSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Test de la classe AutoCompleteBeansManager.
 
 * @version $Revision$ $Date$
 */
public class AutoCompleteBeansManagerTest
{
    /**
     * Manager testé.
     */
    private AutoCompleteBeansManager<Essai> manager;

    /**
     * Critère de recherche.
     */
    private EssaiSearchCriteria essaiSearchCriteria;

    /**
     * Mock du service.
     */
    private GenericService<Essai> mockedService;

    /**
     * Propriété pour les tests.
     */
    private static final String SEARCH_CRIT_PROPERTY = "nom";

    /**
     * Méthode d'initialisation.
     */
    @SuppressWarnings("unchecked")
    @Before
    public void setUp()
    {
        this.mockedService = Mockito.mock(GenericService.class);
        this.essaiSearchCriteria = new EssaiSearchCriteria();
        this.manager = new AutoCompleteBeansManager<Essai>();
        this.manager.setSearchCriteria(this.essaiSearchCriteria);
        this.manager.setSearchCriteriaProperty(AutoCompleteBeansManagerTest.SEARCH_CRIT_PROPERTY);
        this.manager.setService(this.mockedService);
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown()
    {
        this.manager = null;
        this.essaiSearchCriteria = null;
        this.mockedService = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.manager.getSearchCriteria());
    }

    /**
     * Test de la méthode complete.
     */
    @Test
    public void testComplete()
    {
        final Essai essai1 = new Essai();
        final Essai essai2 = new Essai();
        essai1.setId(1L);
        essai2.setId(2L);
        final List<Essai> expectedResults = Arrays.asList(essai1,
                                                          essai2);

        final String requete = "no";
        final Answer<List<Essai>> answerGetAll = new Answer<List<Essai>>() {

            @Override
            public List<Essai> answer(final InvocationOnMock invocation)
                throws Throwable
            {
                final EssaiSearchCriteria criteria =
                    (EssaiSearchCriteria) invocation.getArguments()[0];
                Assert.assertEquals(AutoCompleteBeansManagerTest.this.essaiSearchCriteria,
                                    criteria);
                Assert.assertEquals(requete,
                                    criteria.getNom());
                return expectedResults;
            }
        };
        Mockito.when(this.mockedService.getAll(this.essaiSearchCriteria))
                .thenAnswer(answerGetAll);

        Assert.assertNull(this.essaiSearchCriteria.getNom());
        final List<Essai> results = this.manager.complete(requete);

        Mockito.verify(this.mockedService).getAll(this.essaiSearchCriteria);
        Assert.assertEquals(expectedResults,
                            results);
    }

}

package fr.pharma.eclipse.utils.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.DetailRechercheSigrec;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.converter.filler.Filler;
import fr.pharma.eclipse.utils.introspection.GenericFetcher;

/**
 * Classe de test de EssaiBeanConverter.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiBeanConverterTest {
    /**
     * Converter.
     */
    private EssaiBeanConverter converter;

    /**
     * Factory
     */
    private BeanObjectFactory<Essai> factory;

    /**
     * fetcher.
     */
    private GenericFetcher<EssaiSigrec, Essai> fetcher;

    /**
     * Filler.
     */
    private Filler<EssaiSigrec, Essai> filler;

    /**
     * Service Essai.
     */
    private GenericService<Essai> essaiService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp() {
        this.fetcher = Mockito.mock(GenericFetcher.class);
        this.filler = Mockito.mock(Filler.class);
        this.factory = Mockito.mock(BeanObjectFactory.class);
        this.essaiService = Mockito.mock(GenericService.class);
        this.converter = new EssaiBeanConverter(this.fetcher, this.factory);
        this.converter.setEssaiService(this.essaiService);
        final List<Filler<EssaiSigrec, Essai>> fillers = new ArrayList<Filler<EssaiSigrec, Essai>>();
        fillers.add(this.filler);
        this.converter.setFillers(fillers);
    }
    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown() {
        this.fetcher = null;
        this.factory = null;
        this.filler = null;
        this.converter = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInitialisation() {
        Assert.assertNotNull(this.fetcher);
        Assert.assertNotNull(this.converter);
        Assert.assertTrue(this.converter.getFillers().contains(this.filler));
    }
    /**
     * Test de la méthode convert() avec un essai trouvé.
     */
    @Test
    public void convertFound() {
        final EssaiSigrec essaiSigrec = new EssaiSigrec();
        essaiSigrec.setDetailRecherche(new DetailRechercheSigrec());
        final Essai essai = new Essai();
        essai.setId(1L);
        final List<Essai> results = new ArrayList<Essai>();
        results.add(essai);
        Mockito.when(this.essaiService.getAll(Matchers.any(SearchCriteria.class))).thenReturn(results);
        final Essai result = this.converter.convert(essaiSigrec);

        Mockito.verify(this.essaiService).getAll(Matchers.any(SearchCriteria.class));
        Assert.assertEquals(essai.getId(), result.getId());

    }

    /**
     * Test de la méthode convert() sans essai trouvé.
     */
    @Test
    public void convert() {
        final EssaiSigrec bean = new EssaiSigrec();
        bean.setDetailRecherche(new DetailRechercheSigrec());
        final Essai dest = Mockito.mock(Essai.class);
        Mockito.when(this.filler.support(Matchers.any(EssaiSigrec.class))).thenReturn(true);
        Mockito.when(this.factory.getInitializedObject()).thenReturn(dest);
        final List<Essai> results = new ArrayList<Essai>();
        Mockito.when(this.essaiService.getAll(Matchers.any(SearchCriteria.class))).thenReturn(results);
        this.converter.convert(bean);

        Mockito.verify(this.factory, Mockito.times(1)).getInitializedObject();
        Mockito.verify(this.fetcher, Mockito.times(1)).fetch(Matchers.any(EssaiSigrec.class), Matchers.any(Essai.class));
        Mockito.verify(this.filler, Mockito.times(1)).fill(Matchers.any(EssaiSigrec.class), Matchers.any(Essai.class));
    }
}

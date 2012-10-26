package fr.pharma.eclipse.processor.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.sigrec.acteur.PromoteurSigrec;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.converter.BeanConverter;

/**
 * Test du processor : PromoteurEclipseProcessor.
 
 * @version $Revision$ $Date$
 */
public class PromoteurEclipseProcessorTest
{
    /**
     * Processor à tester.
     */
    private PromoteurEclipseProcessor processor;

    /**
     * Service des promoteurs Sigrec mocké.
     */
    private GenericService<PromoteurSigrec> promoteurSigrecService;

    /**
     * Service des promoteurs mocké.
     */
    private GenericService<Promoteur> promoteurService;

    /**
     * Converter.
     */
    private BeanConverter<PromoteurSigrec, Promoteur> converter;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp()
    {
        this.promoteurService = Mockito.mock(GenericService.class);
        this.promoteurSigrecService = Mockito.mock(GenericService.class);
        this.converter = Mockito.mock(BeanConverter.class);
        this.processor = new PromoteurEclipseProcessor();
        this.processor.setPromoteurService(this.promoteurService);
        this.processor.setPromoteurSigrecService(this.promoteurSigrecService);
        this.processor.setConverter(this.converter);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown()
    {
        this.converter = null;
        this.promoteurService = null;
        this.promoteurSigrecService = null;
        this.processor = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.processor);
    }

    /**
     * Test de la méthode process().
     */
    @Test
    public void testProcess()
    {
        final List<PromoteurSigrec> promoteursSigrec = new ArrayList<PromoteurSigrec>();
        final PromoteurSigrec ps1 = new PromoteurSigrec();
        ps1.setIdentifiant("p1");
        final PromoteurSigrec ps2 = new PromoteurSigrec();
        ps2.setIdentifiant("p2");
        final PromoteurSigrec ps3 = new PromoteurSigrec();
        ps3.setIdentifiant("p3");
        promoteursSigrec.add(ps1);
        promoteursSigrec.add(ps2);
        promoteursSigrec.add(ps3);

        final List<Promoteur> promoteurs = new ArrayList<Promoteur>();
        final Promoteur p1 = new Promoteur();
        p1.setIdentifiant("p1");
        promoteurs.add(p1);

        Mockito.when(this.promoteurSigrecService.getAll()).thenReturn(promoteursSigrec);
        Mockito.when(this.promoteurService.getAll()).thenReturn(promoteurs);
        Mockito
                .when(this.converter.convert(Matchers.any(PromoteurSigrec.class)))
                .thenReturn(new Promoteur());
        this.processor.process();
        Mockito.verify(this.promoteurSigrecService,
                       Mockito.times(1)).getAll();
        Mockito.verify(this.promoteurService,
                       Mockito.times(1)).getAll();
        Mockito.verify(this.promoteurService,
                       Mockito.times(2)).save(Matchers.any(Promoteur.class));
    }
}

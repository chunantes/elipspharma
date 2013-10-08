package fr.pharma.eclipse.service.surcout.processor.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.surcout.DetailSurcout;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.domain.model.surcout.Grille;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Resultat;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.surcout.impl.GrilleServiceImpl;
import fr.pharma.eclipse.service.surcout.processor.SurcoutProcessor;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.validator.save.impl.GrilleSaveValidator;
import fr.pharma.eclipse.validator.save.impl.PrevisionValidator;

/**
 * Test du service de gestion SurcoutServiceImpl.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GrilleServiceImplTest extends AbstractEclipseJUnitTest {

    /**
     * Service.
     */
    private GrilleServiceImpl service;

    /**
     * Dao.
     */
    private GenericDao<Grille> grilleDao;

    /**
     * Validator.
     */
    private GrilleSaveValidator validator;
    /**
     * Service essai.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * Processors.
     */
    private SurcoutProcessor processor;

    /**
     * Validateur de prévision.
     */
    @Resource(name = "previsionValidator")
    private PrevisionValidator previsionValidator;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.grilleDao = Mockito.mock(GenericDao.class);
        this.previsionValidator = Mockito.mock(PrevisionValidator.class);
        this.essaiService = Mockito.mock(EssaiService.class);
        this.processor = Mockito.mock(SurcoutProcessor.class);
        this.validator = Mockito.mock(GrilleSaveValidator.class);
        this.service = new GrilleServiceImpl(this.grilleDao);
        this.service.setPrevisionValidator(this.previsionValidator);
        final List<SurcoutProcessor> processors = new ArrayList<SurcoutProcessor>();
        processors.add(this.processor);
        this.service.setProcessors(processors);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.grilleDao = null;
        this.processor = null;
        this.essaiService = null;
        this.previsionValidator = null;
        this.validator = null;
        this.service = null;
    }

    /**
     * {@inheritDoc}
     */
    @Test
    @Override
    public void testInit() {
        Assert.assertNotNull(this.previsionValidator);
        Assert.assertNotNull(this.grilleDao);
        Assert.assertNotNull(this.essaiService);
        Assert.assertNotNull(this.validator);
        Assert.assertNotNull(this.processor);
        Assert.assertNotNull(this.service);

    }

    /**
     * TEst de la méthode processReel.
     */
    @Test(expected = ValidationException.class)
    public void testProcessReelDateNull() {
        final Grille grille = Mockito.mock(Grille.class);
        Mockito.mock(Calendar.class);
        Mockito.mock(Calendar.class);

        this.service.processReel(grille, null, null);

    }

    /**
     * TEst de la méthode processReel.
     */
    @Test(expected = ValidationException.class)
    public void testProcessReelDateFinNull() {
        final Grille grille = Mockito.mock(Grille.class);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        Mockito.mock(Calendar.class);

        this.service.processReel(grille, dateDebut, null);

    }

    /**
     * TEst de la méthode processReel.
     */
    @Test(expected = ValidationException.class)
    public void testProcessReelDateDebutNull() {
        final Grille grille = Mockito.mock(Grille.class);
        Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        this.service.processReel(grille, null, dateFin);
    }

    /**
     * TEst de la méthode processReel.
     */
    @Test(expected = ValidationException.class)
    public void testProcessReelGrilleValidationFail() {
        final Essai essai = Mockito.mock(Essai.class);
        final DetailSurcout detail = Mockito.mock(DetailSurcout.class);
        final Grille grille = Mockito.mock(Grille.class);
        Mockito.when(grille.getDetailSurcout()).thenReturn(detail);
        Mockito.when(detail.getEssai()).thenReturn(essai);

        Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        this.service.processReel(grille, null, dateFin);
    }

    /**
     * TEst de la méthode processReel.
     */
    @Test
    public void testProcessReel() {
        final Essai essai = Mockito.mock(Essai.class);
        final DetailSurcout detail = Mockito.mock(DetailSurcout.class);
        final Grille grille = Mockito.mock(Grille.class);
        Mockito.when(grille.getDetailSurcout()).thenReturn(detail);
        Mockito.when(detail.getEssai()).thenReturn(essai);
        final Calendar dateDebut = Mockito.mock(Calendar.class);
        final Calendar dateFin = Mockito.mock(Calendar.class);

        final Item i1 = Mockito.mock(Item.class);
        final Item i2 = Mockito.mock(Item.class);
        Mockito.when(i1.getTheme()).thenReturn("a");
        Mockito.when(i2.getTheme()).thenReturn("b");

        final SortedSet<Item> items = new TreeSet<Item>();
        items.add(i1);
        Mockito.when(grille.getItems()).thenReturn(items);

        final Resultat r = Mockito.mock(Resultat.class);
        Mockito.when(r.getMontant()).thenReturn(new BigDecimal(5));

        Mockito.when(this.processor.process(i1, essai, dateDebut, dateFin)).thenReturn(r);
        Mockito.when(this.processor.process(i2, essai, dateDebut, dateFin)).thenReturn(r);

        final Map<Item, Resultat> result = this.service.processReel(grille, dateDebut, dateFin);

        Assert.assertEquals(1, result.size());
        Assert.assertTrue(result.get(i1).getMontant().equals(new BigDecimal(5)));
    }

    /**
     * TEst de la méthode processPrevisionel.
     */
    @Test
    public void testProcessPrevisionnel() {
        final Essai essai = Mockito.mock(Essai.class);
        final DetailSurcout detail = Mockito.mock(DetailSurcout.class);
        final Grille grille = Mockito.mock(Grille.class);
        Mockito.when(grille.getDetailSurcout()).thenReturn(detail);
        Mockito.when(detail.getEssai()).thenReturn(essai);

        final Item i1 = Mockito.mock(Item.class);
        final Item i2 = Mockito.mock(Item.class);
        Mockito.when(i1.getTheme()).thenReturn("a");
        Mockito.when(i2.getTheme()).thenReturn("b");

        final SortedSet<Item> items = new TreeSet<Item>();
        items.add(i1);
        Mockito.when(grille.getItems()).thenReturn(items);

        final DonneesPrevision prevision = Mockito.mock(DonneesPrevision.class);

        final Resultat r = Mockito.mock(Resultat.class);
        Mockito.when(r.getMontant()).thenReturn(new BigDecimal(5));

        Mockito.when(this.processor.process(i1, essai, prevision)).thenReturn(r);

        final Map<Item, Resultat> result = this.service.processPrevision(grille, prevision);

        Assert.assertEquals(1, result.size());
        Assert.assertTrue(result.get(i1).getMontant().equals(new BigDecimal(5)));
    }
}

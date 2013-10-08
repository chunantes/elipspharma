package fr.pharma.eclipse.utils.converter.filler.impl.essai;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.acteur.Cro;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.domain.model.sigrec.acteur.CROSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ContactSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.factory.habilitation.HabilitationFactory;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.converter.BeanConverter;

/**
 * Test du filler : CroFiller.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class CroFillerTest {
    /**
     * Filler à tester.
     */
    private CroFiller filler;

    /**
     * Service cro mocké.
     */
    private GenericService<Cro> mockedService;

    /**
     * Converter cro mocké.
     */
    private BeanConverter<CROSigrec, Cro> croConverter;

    /**
     * Fabrique d'habilitations mockée.
     */
    private HabilitationFactory mockedFactory;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        this.croConverter = Mockito.mock(BeanConverter.class);
        this.mockedService = Mockito.mock(GenericService.class);
        this.mockedFactory = Mockito.mock(HabilitationFactory.class);
        this.filler = new CroFiller();
        this.filler.setCroService(this.mockedService);
        this.filler.setConverter(this.croConverter);
        this.filler.setHabilitationFactory(this.mockedFactory);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown() {
        this.mockedService = null;
        this.mockedFactory = null;
        this.filler = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.filler);
        Assert.assertNotNull(this.mockedService);
        Assert.assertNotNull(this.mockedFactory);
    }

    /**
     * Test de la méthode fill() avec 0 cro.
     */
    @Test
    public void testFillEmpty() {
        final EssaiSigrec source = this.initTrial();
        final Essai destination = EssaiUtils.makeEssaiTest(1);
        this.filler.fill(source, destination);
        Assert.assertEquals(0, destination.getDetailContacts().getHabilitations().size());
    }

    /**
     * Test de la méthode fill() avec un cro déjà présent.
     */
    @Test
    public void testFillFound() {
        final EssaiSigrec source = this.initTrial();
        final CROSigrec c = new CROSigrec();
        c.setContact(new ContactSigrec());
        c.getContact().setRaisonSociale("nomSociete");
        source.getCros().add(c);
        final Essai destination = EssaiUtils.makeEssaiTest(1);
        final Cro cro = new Cro();
        cro.setNomSociete("nomSociete");
        final List<Cro> value = new ArrayList<Cro>();
        value.add(cro);
        Mockito.when(this.mockedService.getAll(Matchers.any(SearchCriteria.class))).thenReturn(value);
        Mockito.when(this.mockedFactory.getInitializedObject()).thenReturn(new Habilitation());

        this.filler.fill(source, destination);
        Mockito.verify(this.croConverter, Mockito.times(0)).convert(Matchers.any(CROSigrec.class));
        Mockito.verify(this.mockedService, Mockito.times(0)).save(Matchers.any(Cro.class));
        Mockito.when(this.mockedFactory.getInitializedObject()).thenReturn(new Habilitation());
        Assert.assertEquals(1, destination.getDetailContacts().getHabilitations().size());

    }

    /**
     * Test de la méthode fill() dans cro déjà présent.
     */
    @Test
    public void testFillNotFound() {
        final EssaiSigrec source = this.initTrial();
        final CROSigrec c = new CROSigrec();
        c.setContact(new ContactSigrec());
        c.getContact().setRaisonSociale("nomSociete");
        source.getCros().add(c);
        final Essai destination = EssaiUtils.makeEssaiTest(1);
        final List<Cro> value = new ArrayList<Cro>();
        Mockito.when(this.mockedService.getAll(Matchers.any(SearchCriteria.class))).thenReturn(value);
        Mockito.when(this.croConverter.convert(Matchers.any(CROSigrec.class))).thenReturn(new Cro());
        Mockito.when(this.mockedFactory.getInitializedObject()).thenReturn(new Habilitation());

        this.filler.fill(source, destination);
        Mockito.verify(this.croConverter, Mockito.times(1)).convert(Matchers.any(CROSigrec.class));
        Mockito.verify(this.mockedService, Mockito.times(1)).save(Matchers.any(Cro.class));
        Mockito.when(this.mockedFactory.getInitializedObject()).thenReturn(new Habilitation());
        Assert.assertEquals(1, destination.getDetailContacts().getHabilitations().size());

    }

    /**
     * Test de la méthode support().
     */
    @Test
    public void testSupport() {
        final EssaiSigrec e = new EssaiSigrec();
        Assert.assertTrue(this.filler.support(e));
    }

    /**
     * Initialisation du TrialType.
     * @return le trialtype.
     */
    private EssaiSigrec initTrial() {
        final EssaiSigrec essai = new EssaiSigrec();

        return essai;
    }
}

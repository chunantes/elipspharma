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
import fr.pharma.eclipse.domain.model.acteur.ArcInvestigateur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ARCInvestigateurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.acteur.ContactSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.factory.habilitation.HabilitationFactory;
import fr.pharma.eclipse.service.acteur.PersonneService;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.converter.BeanConverter;

/**
 * Test du filler : ArcInvestigateurFiller.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ArcInvestigateurFillerTest {
    /**
     * Filler à tester.
     */
    private ArcInvestigateurFiller filler;

    /**
     * Service investigateur mocké.
     */
    private PersonneService<ArcInvestigateur> mockedService;

    /**
     * Converter cro mocké.
     */
    private BeanConverter<ARCInvestigateurSigrec, ArcInvestigateur> converter;

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
        this.converter = Mockito.mock(BeanConverter.class);
        this.mockedService = Mockito.mock(PersonneService.class);
        this.mockedFactory = Mockito.mock(HabilitationFactory.class);
        this.filler = new ArcInvestigateurFiller();
        this.filler.setService(this.mockedService);
        this.filler.setConverter(this.converter);
        this.filler.setHabilitationFactory(this.mockedFactory);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown() {
        this.converter = null;
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
        Assert.assertNotNull(this.converter);
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
        final ARCInvestigateurSigrec c = new ARCInvestigateurSigrec();
        c.setContact(new ContactSigrec());
        c.getContact().setNom("nom");
        c.getContact().setPrenom("prenom");
        source.getArcInvestigateurs().add(c);
        final Essai destination = EssaiUtils.makeEssaiTest(1);
        final ArcInvestigateur investigateur = new ArcInvestigateur();
        investigateur.setNom("nom");
        investigateur.setPrenom("prenom");
        final List<ArcInvestigateur> value = new ArrayList<ArcInvestigateur>();
        value.add(investigateur);
        Mockito.when(this.mockedService.getAll(Matchers.any(SearchCriteria.class))).thenReturn(value);
        Mockito.when(this.mockedFactory.getInitializedObject()).thenReturn(new Habilitation());

        this.filler.fill(source, destination);
        Mockito.verify(this.converter, Mockito.times(0)).convert(Matchers.any(ARCInvestigateurSigrec.class));
        Mockito.verify(this.mockedService, Mockito.times(0)).saveForSigrec(Matchers.any(ArcInvestigateur.class));
        Mockito.verify(this.mockedFactory).getInitializedObject();
        Assert.assertEquals(1, destination.getDetailContacts().getHabilitations().size());

    }

    /**
     * Test de la méthode fill() dans cro déjà présent.
     */
    @Test
    public void testFillNotFound() {
        final EssaiSigrec source = this.initTrial();
        final ARCInvestigateurSigrec c = new ARCInvestigateurSigrec();
        c.setContact(new ContactSigrec());
        c.getContact().setNom("nom");
        c.getContact().setPrenom("prenom");
        source.getArcInvestigateurs().add(c);
        final Essai destination = EssaiUtils.makeEssaiTest(1);
        final List<ArcInvestigateur> value = new ArrayList<ArcInvestigateur>();
        Mockito.when(this.mockedService.getAll(Matchers.any(SearchCriteria.class))).thenReturn(value);
        Mockito.when(this.converter.convert(Matchers.any(ARCInvestigateurSigrec.class))).thenReturn(new ArcInvestigateur());
        Mockito.when(this.mockedFactory.getInitializedObject()).thenReturn(new Habilitation());

        this.filler.fill(source, destination);
        Mockito.verify(this.converter, Mockito.times(1)).convert(Matchers.any(ARCInvestigateurSigrec.class));
        Mockito.verify(this.mockedService, Mockito.times(1)).saveForSigrec(Matchers.any(ArcInvestigateur.class));
        Mockito.verify(this.mockedFactory).getInitializedObject();
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

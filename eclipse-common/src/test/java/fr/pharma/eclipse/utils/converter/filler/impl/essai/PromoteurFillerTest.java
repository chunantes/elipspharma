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
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.sigrec.acteur.PromoteurSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Test du filler : PromoteurFiller.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PromoteurFillerTest {
    /**
     * Filler à tester.
     */
    private PromoteurFiller filler;

    /**
     * Service promoteur mocké.
     */
    private GenericService<Promoteur> mockedService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp() {
        this.mockedService = Mockito.mock(GenericService.class);
        this.filler = new PromoteurFiller();
        this.filler.setPromoteurService(this.mockedService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown() {
        this.mockedService = null;
        this.filler = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.filler);
        Assert.assertNotNull(this.mockedService);
    }
    /**
     * Test de la méthode fill().
     */
    @Test
    public void testFill() {
        final EssaiSigrec source = this.initTrial();
        final Essai destination = this.initEssai();
        final Promoteur promoteur = new Promoteur();
        promoteur.setIdentifiant("id");
        final List<Promoteur> value = new ArrayList<Promoteur>();
        value.add(promoteur);
        Mockito.when(this.mockedService.getAll(Matchers.any(SearchCriteria.class))).thenReturn(value);

        this.filler.fill(source, destination);
        Assert.assertEquals(promoteur.getIdentifiant(), destination.getPromoteur().getIdentifiant());

    }

    /**
     * Test de la méthode support().
     */
    @Test
    public void testSupport() {
        final EssaiSigrec e = new EssaiSigrec();
        Assert.assertFalse(this.filler.support(e));
        e.setPromoteur(new PromoteurSigrec());
        Assert.assertTrue(this.filler.support(e));
    }

    /**
     * Initialisation du TrialType.
     * @return le trialtype.
     */
    private EssaiSigrec initTrial() {
        final EssaiSigrec essai = new EssaiSigrec();
        final PromoteurSigrec promoteur = new PromoteurSigrec();
        promoteur.setIdentifiant("id");
        essai.setPromoteur(promoteur);
        return essai;
    }

    private Essai initEssai() {
        final Essai essai = new Essai();
        return essai;
    }

}

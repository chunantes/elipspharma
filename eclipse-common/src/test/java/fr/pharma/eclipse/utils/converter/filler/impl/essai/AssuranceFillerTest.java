package fr.pharma.eclipse.utils.converter.filler.impl.essai;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.DetailAdministratif;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.embedded.InfosAssurance;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.AssuranceSigrec;
import fr.pharma.eclipse.utils.introspection.GenericFetcher;

/**
 * Test du filler : AssuranceFiller.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AssuranceFillerTest {
    /**
     * Filler à tester.
     */
    private AssuranceFiller filler;

    /**
     * Fetcher mocké.
     */
    private GenericFetcher<AssuranceSigrec, InfosAssurance> fetcher;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        this.fetcher = Mockito.mock(GenericFetcher.class);
        this.filler = new AssuranceFiller();
        this.filler.setAssuranceFetcher(this.fetcher);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown() {
        this.fetcher = null;
        this.filler = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.filler);
        Assert.assertNotNull(this.fetcher);
    }

    /**
     * Test de la méthode fill() avec 0 assurance.
     */
    @Test
    public void testFillEmpty() {
        final EssaiSigrec source = this.initTrial();
        final Essai destination = this.initDestination();
        this.filler.fill(source, destination);
        Mockito.verify(this.fetcher, Mockito.never()).fetch(Matchers.any(AssuranceSigrec.class), Matchers.any(InfosAssurance.class));
    }

    /**
     * Test de la méthode fill() dans cro déjà présent.
     */
    @Test
    public void testFillNotFound() {
        final EssaiSigrec source = this.initTrial();
        final AssuranceSigrec c = new AssuranceSigrec();
        source.getAssurances().add(c);
        final Essai destination = this.initDestination();

        this.filler.fill(source, destination);
        Mockito.verify(this.fetcher, Mockito.times(1)).fetch(Matchers.any(AssuranceSigrec.class), Matchers.any(InfosAssurance.class));

        Assert.assertNotNull(destination.getDetailAdministratif().getInfosAssurance());

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
        final EssaiSigrec essaiSigrec = new EssaiSigrec();

        return essaiSigrec;
    }

    private Essai initDestination() {
        final Essai essai = new Essai();
        essai.setDetailAdministratif(new DetailAdministratif());
        return essai;
    }

}

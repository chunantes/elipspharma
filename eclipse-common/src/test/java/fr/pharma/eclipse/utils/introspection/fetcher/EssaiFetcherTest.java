package fr.pharma.eclipse.utils.introspection.fetcher;

import java.util.Calendar;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.pharma.eclipse.domain.enums.NatureRecherche;
import fr.pharma.eclipse.domain.enums.ObjetRecherche;
import fr.pharma.eclipse.domain.enums.PhaseRecherche;
import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.DetailAdministratif;
import fr.pharma.eclipse.domain.model.essai.detail.dates.DetailDates;
import fr.pharma.eclipse.domain.model.essai.detail.faisabilite.DetailFaisabilite;
import fr.pharma.eclipse.domain.model.essai.detail.recherche.DetailRecherche;
import fr.pharma.eclipse.domain.model.sigrec.essai.EssaiSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.DetailRechercheSigrec;
import fr.pharma.eclipse.domain.model.sigrec.essai.detail.PrevisionSigrec;
import fr.pharma.eclipse.externe.EssaiExterne;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.introspection.GenericFetcher;

/**
 * Classe de test de GenericFetcher pour le bean Assurance.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
public class EssaiFetcherTest {
    /**
     * Fetcher à tester.
     */
    @Resource(name = "essaiFetcher")
    private GenericFetcher<EssaiSigrec, Essai> fetcher;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void setUp() {
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void tearDown() {
        this.fetcher = null;
    }

    /**
     * Test de la méthode Fetch().
     */
    @Test
    public void testFetch() {
        final EssaiSigrec source = this.initSource();
        final Essai destination = this.initDestination();

        this.fetcher.fetch(source, destination);
        Assert.assertEquals(source.getCodePromoteur(), destination.getCodePromoteur());
        Assert.assertEquals(source.getNom(), destination.getNom());
        Assert.assertEquals(source.getNumIdentificationAC(), destination.getDetailAdministratif().getInfosAC().getNumIdentAC());
        Assert.assertEquals(source.getTypePromoteur(), destination.getTypePromoteur());
        Assert.assertEquals(source.getDetailRecherche().getTitreProtocole(), destination.getDetailRecherche().getTitreProtocole());
        Assert.assertEquals(source.getDetailRecherche().getNatureRecherche(), destination.getDetailRecherche().getNatureRecherche());
        Assert.assertEquals(source.getDetailRecherche().getObjetRecherche(), destination.getDetailRecherche().getObjetRecherche());
        Assert.assertEquals(source.getDetailRecherche().getTypeRecherche(), destination.getDetailRecherche().getTypeRecherche());
        Assert.assertEquals(source.getDetailRecherche().getNumEnregistrement(), destination.getDetailRecherche().getNumEnregistrement());
        Assert.assertEquals(source.getDetailRecherche().getPhaseRecherche(), destination.getDetailRecherche().getPhaseRecherche());
        Assert.assertEquals(source.getPrevision().getDateDebut(), destination.getDetailDates().getDebutEtudePrev());
        Assert.assertEquals(source.getPrevision().getDateFin(), destination.getDetailDates().getFinEtudePrev());

    }

    /**
     * Méthode en charge d'initialiser le bean source.
     * @return
     */
    private EssaiSigrec initSource() {
        final EssaiSigrec source = new EssaiExterne();

        source.setCodePromoteur("code");
        source.setNom("nom");
        source.setNumIdentificationAC("numac");
        source.setTypePromoteur(TypePromoteur.ACADEMIQUE);

        final DetailRechercheSigrec detail = new DetailRechercheSigrec();
        detail.setNatureRecherche(NatureRecherche.AUTRE);
        detail.setNumEnregistrement("num enregistre");
        detail.setObjetRecherche(ObjetRecherche.ALICAMENT);
        detail.setPhaseRecherche(PhaseRecherche.I);
        detail.setNumEnregistrement("num");
        source.setDetailRecherche(detail);

        final PrevisionSigrec prevision = new PrevisionSigrec();
        prevision.setDateDebut(Calendar.getInstance(EclipseConstants.LOCALE));
        prevision.setDateFin(Calendar.getInstance(EclipseConstants.LOCALE));
        source.setPrevision(prevision);

        return source;
    }

    /**
     * Méthode en charge d'initialiser le bean destination.
     * @return le bean destination
     */
    private Essai initDestination() {
        final Essai destination = new Essai();
        destination.setDetailAdministratif(new DetailAdministratif());
        destination.setDetailDates(new DetailDates());
        destination.setDetailFaisabilite(new DetailFaisabilite());
        destination.setDetailRecherche(new DetailRecherche());
        return destination;
    }
}

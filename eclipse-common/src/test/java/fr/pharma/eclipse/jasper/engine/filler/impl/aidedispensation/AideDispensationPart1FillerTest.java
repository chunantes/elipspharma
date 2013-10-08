package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import java.text.ParseException;
import java.util.Arrays;

import net.sf.jasperreports.engine.JRDataSource;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.util.StringUtils;

import fr.pharma.eclipse.domain.enums.PhaseRecherche;
import fr.pharma.eclipse.domain.enums.QualiteInsu;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensation;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart1;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.jasper.engine.factory.JRDataSourceFactory;
import fr.pharma.eclipse.jasper.engine.filler.JasperReportBeanFiller;
import fr.pharma.eclipse.service.habilitation.helper.HabilitationsHelper;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.PersonneUtils;
import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Test de la classe AideDispensationPart1Filler.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart1FillerTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private AideDispensationPart1Filler filler;

    /**
     * Fabrique mockée de JRDataSource.
     */
    private JRDataSourceFactory mockedDSFactory;

    /**
     * Helper mocké pour la gestion des habilitations.
     */
    private HabilitationsHelper mockedHelper;

    /**
     * SubFiller mocké.
     */
    private JasperReportBeanFiller mockedSubFiller;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedDSFactory = Mockito.mock(JRDataSourceFactory.class);
        this.mockedHelper = Mockito.mock(HabilitationsHelper.class);
        this.mockedSubFiller = Mockito.mock(JasperReportBeanFiller.class);
        this.filler = new AideDispensationPart1Filler();
        this.filler.setHabilitationsHelper(this.mockedHelper);
        this.filler.setJrDataSourceFactory(this.mockedDSFactory);
        this.filler.setSubFillers(Arrays.asList(this.mockedSubFiller));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.filler = null;
        this.mockedDSFactory = null;
        this.mockedHelper = null;
        this.mockedSubFiller = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.filler);
        Assert.assertEquals(this.mockedDSFactory, this.filler.getJrDataSourceFactory());
        Assert.assertEquals(this.mockedHelper, this.filler.getHabilitationsHelper());
        Assert.assertEquals(1, this.filler.getSubFillers().size());
        Assert.assertEquals(this.mockedSubFiller, this.filler.getSubFillers().get(0));
    }
    /**
     * Test de la méthode fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.
     * JRBeanFicheAideDispensation).
     */
    @Test
    public void testFill() {
        long id = 1;
        final Promoteur promoteur = new Promoteur();
        promoteur.setId(id++);
        promoteur.setRaisonSociale("Nom promoteur");
        final Service service1 = new Service();
        final Service service2 = new Service();
        service1.setId(id++);
        service2.setId(id++);
        service1.setNom("Nom service 1");
        service2.setNom("Nom service 2");
        final Bras bras1 = new Bras();
        final Bras bras2 = new Bras();
        bras1.setId(id++);
        bras2.setId(id++);
        final Investigateur investigateur = PersonneUtils.makeInvestigateur(id++, "professeur", "jean", "valjean");
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        essai.setPromoteur(promoteur);
        essai.setNumInterne("essai-2010-01");
        essai.setNom("Nom essai");
        essai.getServices().addAll(Arrays.asList(service1, service2));
        essai.getDetailRecherche().setTitreProtocole("titre protocole");
        essai.getDetailRecherche().setPhaseRecherche(PhaseRecherche.IIb);
        essai.getDetailDonneesPharma().getInfosGenerales().setNbCentresPrevus(1);
        essai.getDetailDonneesPharma().getInfosGenerales().setNbPatientsPrevus(2);
        essai.getDetailDonneesPharma().getInfosGenerales().setQualiteInsu(QualiteInsu.ESSAI_SIMPLE_AVEUGLE);
        essai.getDetailDesign().getBras().addAll(Arrays.asList(bras1, bras2));
        try {
            essai.getDetailDates().setActivation(Utils.parseDate("03/12/2010", EclipseConstants.PATTERN_SIMPLE));
            essai.getDetailDates().setDebutEtude(Utils.parseDate("01/12/2010", EclipseConstants.PATTERN_SIMPLE));
            essai.getDetailDates().setFinEtudePrev(Utils.parseDate("01/12/2011", EclipseConstants.PATTERN_SIMPLE));
            essai.getDetailDates().setFinInclusionPrev(Utils.parseDate("01/10/2011", EclipseConstants.PATTERN_SIMPLE));
        } catch (final ParseException e) {
            Assert.fail("Erreur de création des données de test: " + e.getMessage());
        }

        final JRDataSource expectedRes = Mockito.mock(JRDataSource.class);
        Mockito.when(this.mockedHelper.getInvestigateurPrincipal(essai)).thenReturn(investigateur);
        Mockito.when(this.mockedDSFactory.getInitializedObject(Matchers.any(JRBeanFicheAideDispensationPart1.class))).thenAnswer(this.prepareAnswer(essai, expectedRes));

        final JRBeanFicheAideDispensation dataSource = Mockito.mock(JRBeanFicheAideDispensation.class);
        this.filler.fill(essai, dataSource);

        Mockito.verify(this.mockedHelper).getInvestigateurPrincipal(essai);
        Mockito.verify(this.mockedSubFiller).fill(Matchers.any(Essai.class), Matchers.any(JRBeanFicheAideDispensationPart1.class));
        Mockito.verify(this.mockedDSFactory).getInitializedObject(Matchers.any(JRBeanFicheAideDispensationPart1.class));
        Mockito.verify(dataSource).setPartie1(expectedRes);
    }
    /**
     * Méthode de préparation de l'objet Answer pour l'appel à
     * this.mockedDSFactory.getInitializedObject.
     * @param essai Essai.
     * @param expectedRes Retour attendu.
     * @return Answer.
     */
    private Answer<Object> prepareAnswer(final Essai essai,
                                         final JRDataSource expectedRes) {
        return new Answer<Object>() {

            @Override
            public Object answer(final InvocationOnMock invocation) throws Throwable {
                final JRBeanFicheAideDispensationPart1 arg1 = (JRBeanFicheAideDispensationPart1) invocation.getArguments()[0];
                AideDispensationPart1FillerTest.this.verify(arg1);
                return expectedRes;
            }
        };
    }

    /**
     * Méthode de vérification de JRBeanFicheAideDispensationPart1 créé.
     * @param part1 Partie 1.
     */
    private void verify(final JRBeanFicheAideDispensationPart1 part1) {
        Assert.assertTrue(StringUtils.hasText(part1.getPromoteur()));
        Assert.assertTrue(StringUtils.hasText(part1.getCodeProtocole()));
        Assert.assertTrue(StringUtils.hasText(part1.getNomUsuel()));
        Assert.assertTrue(StringUtils.hasText(part1.getTitreProtocole()));
        Assert.assertTrue(StringUtils.hasText(part1.getInvestigateur()));
        Assert.assertTrue(StringUtils.hasText(part1.getServiceInvestigateur()));
        Assert.assertTrue(StringUtils.hasText(part1.getPhase()));
        Assert.assertTrue(StringUtils.hasText(part1.getMulticentrique()));
        Assert.assertTrue(StringUtils.hasText(part1.getNbCentres()));
        Assert.assertTrue(StringUtils.hasText(part1.getNbPatients()));
        Assert.assertTrue(StringUtils.hasText(part1.getNbGroupes()));
        Assert.assertTrue(StringUtils.hasText(part1.getQualiteInsu()));
        Assert.assertTrue(StringUtils.hasText(part1.getDateMiseEnPlace()));
        Assert.assertTrue(StringUtils.hasText(part1.getDateActivation()));
        Assert.assertTrue(StringUtils.hasText(part1.getDatePrevueFinInclusions()));
        Assert.assertTrue(StringUtils.hasText(part1.getDatePrevueFinEssai()));
    }
}

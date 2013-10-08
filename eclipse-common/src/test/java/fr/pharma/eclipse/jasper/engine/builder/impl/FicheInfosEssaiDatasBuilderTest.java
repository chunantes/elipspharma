package fr.pharma.eclipse.jasper.engine.builder.impl;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.domain.jasper.model.common.JRBeanHeader;
import fr.pharma.eclipse.domain.jasper.model.fiche.essai.JRBeanFicheEssai;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.jasper.engine.builder.helper.common.JRBeanHeaderBuilder;
import fr.pharma.eclipse.jasper.engine.factory.JRDataSourceFactory;
import fr.pharma.eclipse.jasper.engine.helper.ReportNameBuildHelper;
import fr.pharma.eclipse.jasper.engine.helper.SourceCheckingHandler;
import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;
import fr.pharma.eclipse.service.habilitation.helper.HabilitationsHelper;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.PersonneUtils;

/**
 * Test de la classe FicheInfosEssaiDatasBuilder.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class FicheInfosEssaiDatasBuilderTest extends AbstractEclipseJUnitTest {
    /**
     * Builder testé.
     */
    private FicheInfosEssaiDatasBuilder builder;

    /**
     * Helper mocké.
     */
    private ReportNameBuildHelper mockedHelper;

    /**
     * Handler mocké.
     */
    private SourceCheckingHandler mockedHandler;

    /**
     * Fabrique de JRDataSource mockée.
     */
    private JRDataSourceFactory mockedJrDsFactory;

    /**
     * Helper pour la gestion des habilitations mocké.
     */
    private HabilitationsHelper mockedHabilitationsHelper;

    /**
     * Helper mocké pour la construction de l'en-tête.
     */
    private JRBeanHeaderBuilder mockedHeaderBuilder;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedHelper = Mockito.mock(ReportNameBuildHelper.class);
        this.mockedHabilitationsHelper = Mockito.mock(HabilitationsHelper.class);
        this.mockedHandler = Mockito.mock(SourceCheckingHandler.class);
        this.mockedJrDsFactory = Mockito.mock(JRDataSourceFactory.class);
        this.mockedHeaderBuilder = Mockito.mock(JRBeanHeaderBuilder.class);
        this.builder = new FicheInfosEssaiDatasBuilder();
        this.builder.setReportNameHelper(this.mockedHelper);
        this.builder.setCheckHandler(this.mockedHandler);
        this.builder.setHabilitationsHelper(this.mockedHabilitationsHelper);
        this.builder.setJrDataSourceFactory(this.mockedJrDsFactory);
        this.builder.setHeaderBuilder(this.mockedHeaderBuilder);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.builder = null;
        this.mockedHelper = null;
        this.mockedHandler = null;
        this.mockedJrDsFactory = null;
        this.mockedHabilitationsHelper = null;
        this.mockedHeaderBuilder = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.builder);
        Assert.assertEquals(this.mockedHelper, this.builder.getReportNameHelper());
        Assert.assertEquals(this.mockedHandler, this.builder.getCheckHandler());
        Assert.assertEquals(this.mockedJrDsFactory, this.builder.getJrDataSourceFactory());
        Assert.assertEquals(this.mockedHabilitationsHelper, this.builder.getHabilitationsHelper());
        Assert.assertEquals(this.mockedHeaderBuilder, this.builder.getHeaderBuilder());
    }

    /**
     * Test de la méthode checkSource.
     */
    @Test
    public void testCheckSource() {
        final Essai source = EssaiUtils.makeEssaiTest(1);
        try {
            this.builder.checkSource(source);
        } catch (final JasperReportBuildException e) {
            Assert.fail("Exception inattendue : " + e.getMessage());
        }
        try {
            Mockito.verify(this.mockedHandler, Mockito.times(2)).handleCheck(Matchers.anyBoolean(), Matchers.anyString());
        } catch (final JasperReportBuildException e) {
            Assert.fail("Erreur de paramétrage des mocks : " + e.getMessage());
        }
    }

    /**
     * Test de la méthode buildReportName.
     */
    @Test
    public void testBuildReportName() {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        final TypeRapportJasper typeRapport = TypeRapportJasper.FICHE_INFO_ESSAI;

        Mockito.doAnswer(new Answer<Object>() {

            @Override
            public Object answer(final InvocationOnMock invocation) throws Throwable {
                int i = 0;
                final StringBuilder argBuilder = (StringBuilder) invocation.getArguments()[i++];
                final TypeRapportJasper argTypeRapport = (TypeRapportJasper) invocation.getArguments()[i++];
                Assert.assertEquals(typeRapport, argTypeRapport);
                argBuilder.append("addCommonNamePart");
                return null;
            }
        }).when(this.mockedHelper).addCommonNamePart(Matchers.any(StringBuilder.class), Matchers.any(TypeRapportJasper.class));
        Mockito.doAnswer(new Answer<Object>() {

            @Override
            public Object answer(final InvocationOnMock invocation) throws Throwable {
                int i = 0;
                final StringBuilder argBuilder = (StringBuilder) invocation.getArguments()[i++];
                final Essai argEssai = (Essai) invocation.getArguments()[i++];
                Assert.assertEquals(essai, argEssai);
                argBuilder.append("addIdEssaiPart");
                return null;
            }
        }).when(this.mockedHelper).addIdEssaiPart(Matchers.any(StringBuilder.class), Matchers.any(Essai.class));
        Mockito.doAnswer(new Answer<Object>() {

            @Override
            public Object answer(final InvocationOnMock invocation) throws Throwable {
                int i = 0;
                final StringBuilder argBuilder = (StringBuilder) invocation.getArguments()[i++];
                argBuilder.append("addDatePart");
                return null;
            }
        }).when(this.mockedHelper).addDatePart(Matchers.any(StringBuilder.class));
        Mockito.doAnswer(new Answer<Object>() {

            @Override
            public Object answer(final InvocationOnMock invocation) throws Throwable {
                int i = 0;
                final StringBuilder argBuilder = (StringBuilder) invocation.getArguments()[i++];
                final TypeRapportJasper argTypeRapport = (TypeRapportJasper) invocation.getArguments()[i++];
                Assert.assertEquals(typeRapport, argTypeRapport);
                argBuilder.append("addCommonExtensionPart");
                return null;
            }
        }).when(this.mockedHelper).addCommonExtensionPart(Matchers.any(StringBuilder.class), Matchers.any(TypeRapportJasper.class));

        final String res = this.builder.buildReportName(essai, typeRapport);
        Assert.assertEquals("addCommonNamePartaddIdEssaiPartaddDatePartaddCommonExtensionPart", res);
    }

    /**
     * Test de la classe FicheInfosEssaiDatasBuilder#buildDataSource().
     */
    @Test
    public void testBuildDataSource() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        essai.setNumInterne("essai-002");
        essai.setNom("Son nom usuel");
        final Promoteur promoteur = new Promoteur();
        promoteur.setId(id++);
        promoteur.setRaisonSociale("Promoteur 158");
        essai.setPromoteur(promoteur);
        final Investigateur expectedInv = PersonneUtils.makeInvestigateur(id++);
        expectedInv.setTitre("professeur");
        expectedInv.setPrenom("jean");
        expectedInv.setNom("valjean");
        final JRBeanHeader expectedHeader = Mockito.mock(JRBeanHeader.class);
        final JRDataSource expectedRes = Mockito.mock(JRDataSource.class);
        final String expectedSousTitre = "Fiche d'information 'essais cliniques'";
        final String expectedProcessus = "Management";
        final String expectedThemes = "Essais cliniques";
        final String expectedDiffuseur = "Pharmacie";

        Mockito.when(this.mockedHabilitationsHelper.getInvestigateurPrincipal(essai)).thenReturn(expectedInv);
        Mockito.when(this.mockedJrDsFactory.getInitializedObject(Matchers.any(Object[].class))).thenAnswer(new Answer<JRDataSource>() {

            @Override
            public JRDataSource answer(final InvocationOnMock invocation) throws Throwable {
                final Object arg0 = invocation.getArguments()[0];
                final JRBeanFicheEssai bean = (JRBeanFicheEssai) arg0;
                Assert.assertNotNull(bean);
                Assert.assertEquals(promoteur.getRaisonSociale(), bean.getPromoteur());
                Assert.assertEquals(essai.getNumInterne(), bean.getCodeProtocole());
                Assert.assertEquals(essai.getNom(), bean.getNomUsuel());
                Assert.assertEquals("professeur jean valjean", bean.getInvestigateur());
                Assert.assertEquals(expectedHeader, bean.getHeader());
                return expectedRes;
            }
        });

        Mockito.when(this.mockedHeaderBuilder.build(Matchers.anyString(), Matchers.anyString(), Matchers.anyString(), Matchers.anyString())).thenReturn(expectedHeader);

        final JRDataSource res = this.builder.buildDataSource(essai);

        Mockito.verify(this.mockedHabilitationsHelper).getInvestigateurPrincipal(essai);
        Mockito.verify(this.mockedHeaderBuilder).build(expectedSousTitre, expectedProcessus, expectedThemes, expectedDiffuseur);
        Mockito.verify(this.mockedJrDsFactory).getInitializedObject(Matchers.any(Object[].class));
        Assert.assertNotNull(res);
        Assert.assertEquals(expectedRes, res);
    }
    /**
     * Test de la classe FicheInfosEssaiDatasBuilder#buildParameters().
     */
    @Test
    public void testBuildParameters() {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        final Map<String, Object> res = this.builder.buildParameters(essai);
        Assert.assertNotNull(res);
    }
}

package fr.pharma.eclipse.jasper.engine.builder.impl;

import java.util.Arrays;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.domain.jasper.model.common.JRBeanHeader;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.jasper.engine.builder.helper.common.JRBeanHeaderBuilder;
import fr.pharma.eclipse.jasper.engine.factory.JRDataSourceFactory;
import fr.pharma.eclipse.jasper.engine.filler.JasperReportBeanFiller;
import fr.pharma.eclipse.jasper.engine.helper.ReportNameBuildHelper;
import fr.pharma.eclipse.jasper.engine.helper.SourceCheckingHandler;
import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Test de la classe {@link FicheAideDispensationDatasBuilder}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class FicheAideDispensationDatasBuilderTest extends AbstractEclipseJUnitTest {
    /**
     * Builder testé.
     */
    private FicheAideDispensationDatasBuilder builder;

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
     * Helper mocké pour la construction de l'en-tête.
     */
    private JRBeanHeaderBuilder mockedHeaderBuilder;

    /**
     * Filler mocké.
     */
    private JasperReportBeanFiller mockedFiller;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedHelper = Mockito.mock(ReportNameBuildHelper.class);
        this.mockedHandler = Mockito.mock(SourceCheckingHandler.class);
        this.mockedJrDsFactory = Mockito.mock(JRDataSourceFactory.class);
        this.mockedHeaderBuilder = Mockito.mock(JRBeanHeaderBuilder.class);
        this.mockedFiller = Mockito.mock(JasperReportBeanFiller.class);
        this.builder = new FicheAideDispensationDatasBuilder();
        this.builder.setReportNameHelper(this.mockedHelper);
        this.builder.setCheckHandler(this.mockedHandler);
        this.builder.setJrDataSourceFactory(this.mockedJrDsFactory);
        this.builder.setHeaderBuilder(this.mockedHeaderBuilder);
        this.builder.setDataSourceFillers(Arrays.asList(this.mockedFiller));
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
        this.mockedHeaderBuilder = null;
        this.mockedFiller = null;
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
        Assert.assertEquals(this.mockedHeaderBuilder, this.builder.getHeaderBuilder());
        Assert.assertFalse(this.builder.getDataSourceFillers().isEmpty());
        Assert.assertEquals(this.mockedFiller, this.builder.getDataSourceFillers().get(0));
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
        final TypeRapportJasper typeRapport = TypeRapportJasper.MODELE_PRESCRIPTION_NOMINATIVE;

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
        final JRBeanHeader expectedHeader = Mockito.mock(JRBeanHeader.class);
        final JRDataSource expectedRes = Mockito.mock(JRDataSource.class);
        final String expectedSousTitre = "Fiche de gestion et d'aide à la dispensation d'un médicament en essai clinique";
        final String expectedProcessus = "Management";
        final String expectedThemes = "Essais cliniques";
        final String expectedDiffuseur = "Pharmacie";

        Mockito.when(this.mockedJrDsFactory.getInitializedObject(Matchers.any(Object[].class))).thenAnswer(new Answer<JRDataSource>() {

            @Override
            public JRDataSource answer(final InvocationOnMock invocation) throws Throwable {
                final Object arg0 = invocation.getArguments()[0];
                final JRBeanFicheAideDispensation bean = (JRBeanFicheAideDispensation) arg0;
                Assert.assertNotNull(bean);
                Assert.assertEquals(expectedHeader, bean.getHeader());
                return expectedRes;
            }
        });
        Mockito.doAnswer(new Answer<Object>() {

            @Override
            public Object answer(final InvocationOnMock invocation) throws Throwable {
                Assert.assertEquals(essai, invocation.getArguments()[0]);
                Assert.assertNotNull(invocation.getArguments()[1]);
                return null;
            }
        }).when(this.mockedFiller).fill(Matchers.any(Essai.class), Matchers.any(JRBeanFicheAideDispensation.class));

        Mockito.when(this.mockedHeaderBuilder.build(expectedSousTitre, expectedProcessus, expectedThemes, expectedDiffuseur)).thenReturn(expectedHeader);

        final JRDataSource res = this.builder.buildDataSource(essai);

        Mockito.verify(this.mockedFiller).fill(Matchers.any(Essai.class), Matchers.any(JRBeanFicheAideDispensation.class));
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

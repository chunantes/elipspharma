package fr.pharma.eclipse.jasper.engine.manager;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.jasper.engine.builder.JasperReportDatasBuilder;
import fr.pharma.eclipse.jasper.engine.exporter.JasperPrintExporter;
import fr.pharma.eclipse.jasper.engine.factory.JasperPrintFactory;
import fr.pharma.eclipse.jasper.engine.helper.CommonParametersHelper;
import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * Test de la classe {@link JasperReportBuildManager}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JasperReportBuildManagerTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private JasperReportBuildManager manager;

    /**
     * Builder de données mocké.
     */
    private JasperReportDatasBuilder mockedDatasBuilder;

    /**
     * Fabrique d'objets JasperPrint mocké.
     */
    private JasperPrintFactory mockedJpFactory;

    /**
     * Exporter d'objet JasperPrint mocké.
     */
    private JasperPrintExporter mockedJpExporter;

    /**
     * Helper mocké pour la gestion des paramètres communs des rapports.
     */
    private CommonParametersHelper mockedCommonParametersHelper;

    /**
     * Type du rapport Jasper supporté.
     */
    private final static TypeRapportJasper TYPE_RAPPORT = TypeRapportJasper.FICHE_INFO_ESSAI;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedDatasBuilder = Mockito.mock(JasperReportDatasBuilder.class);
        this.mockedJpFactory = Mockito.mock(JasperPrintFactory.class);
        this.mockedJpExporter = Mockito.mock(JasperPrintExporter.class);
        this.mockedCommonParametersHelper = Mockito.mock(CommonParametersHelper.class);
        this.manager = new JasperReportBuildManager(JasperReportBuildManagerTest.TYPE_RAPPORT.name());
        this.manager.setDatasBuilder(this.mockedDatasBuilder);
        this.manager.setJpExporter(this.mockedJpExporter);
        this.manager.setJpFactory(this.mockedJpFactory);
        this.manager.setCommonParametersHelper(this.mockedCommonParametersHelper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.manager = null;
        this.mockedDatasBuilder = null;
        this.mockedJpFactory = null;
        this.mockedJpExporter = null;
        this.mockedCommonParametersHelper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.manager);
        Assert.assertEquals(JasperReportBuildManagerTest.TYPE_RAPPORT, this.manager.getTypeRapport());
        Assert.assertEquals(this.mockedDatasBuilder, this.manager.getDatasBuilder());
        Assert.assertEquals(this.mockedJpExporter, this.manager.getJpExporter());
        Assert.assertEquals(this.mockedJpFactory, this.manager.getJpFactory());
        Assert.assertEquals(this.mockedCommonParametersHelper, this.manager.getCommonParametersHelper());
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.jasper.engine.manager.JasperReportBuildManager#build()}
     * .
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testBuild() {
        try {
            final Essai source = EssaiUtils.makeEssaiTest(1);
            final JRDataSource expectedDataSource = Mockito.mock(JRDataSource.class);
            final JasperPrint expectedJasperPrint = Mockito.mock(JasperPrint.class);
            final ByteArrayOutputStream expectedOutStream = Mockito.mock(ByteArrayOutputStream.class);
            final Map expectedParameters = new HashMap<String, Object>();
            expectedParameters.put("key1", "val1");
            final byte[] expectedBytes = new byte[]{0, 1, 3, 6 };
            Mockito.when(this.mockedDatasBuilder.buildDataSource(source)).thenReturn(expectedDataSource);
            Mockito.when(this.mockedDatasBuilder.buildParameters(source)).thenReturn(expectedParameters);
            Mockito.when(this.mockedJpFactory.getInitializedObject(expectedDataSource, expectedParameters)).thenReturn(expectedJasperPrint);
            Mockito.when(this.mockedJpExporter.export(JasperReportBuildManagerTest.TYPE_RAPPORT, expectedJasperPrint)).thenReturn(expectedOutStream);
            Mockito.when(expectedOutStream.toByteArray()).thenReturn(expectedBytes);

            final byte[] actualBytes = this.manager.build(source);
            Mockito.verify(this.mockedDatasBuilder).checkSource(source);
            Mockito.verify(this.mockedDatasBuilder).buildDataSource(source);
            Mockito.verify(this.mockedDatasBuilder).buildParameters(source);
            Mockito.verify(this.mockedCommonParametersHelper).addCommonParameters(expectedParameters);
            Mockito.verify(this.mockedJpFactory).getInitializedObject(expectedDataSource, expectedParameters);
            Mockito.verify(this.mockedJpExporter).export(JasperReportBuildManagerTest.TYPE_RAPPORT, expectedJasperPrint);
            Mockito.verify(expectedOutStream).toByteArray();
            Assert.assertEquals(expectedBytes, actualBytes);
        } catch (final JasperReportBuildException e) {
            Assert.fail("Exception non attendue : " + e.getMessage());
        }
    }

    /**
     * Test de la méthode buildFileName.
     */
    @Test
    public void testBuildFileName() {
        try {
            final Essai source = EssaiUtils.makeEssaiTest(1);
            final String expectedName = "source.pdf";
            Mockito.when(this.mockedDatasBuilder.buildReportName(source, JasperReportBuildManagerTest.TYPE_RAPPORT)).thenReturn(expectedName);
            final String actualName = this.manager.buildFileName(source);
            Mockito.verify(this.mockedDatasBuilder).checkSource(source);
            Mockito.verify(this.mockedDatasBuilder).buildReportName(source, JasperReportBuildManagerTest.TYPE_RAPPORT);
            Assert.assertEquals(expectedName, actualName);
        } catch (final JasperReportBuildException e) {
            Assert.fail("Exception non attendue : " + e.getMessage());
        }
    }

}

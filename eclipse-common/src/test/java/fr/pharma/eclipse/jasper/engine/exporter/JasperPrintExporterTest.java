package fr.pharma.eclipse.jasper.engine.exporter;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.factory.utils.IOStreamsFactory;
import fr.pharma.eclipse.jasper.engine.factory.exporter.JRExporterFactory;
import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * Test de la classe JasperPrintExporter.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JasperPrintExporterTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private JasperPrintExporter exporter;

    /**
     * Mock de la fabrique.
     */
    private JRExporterFactory mockedFactory;

    /**
     * Mock de la fabrique de fux I/O.
     */
    private IOStreamsFactory mockedIoFactory;

    /**
     * TypeRapportJasper pris en charge.
     */
    private static final TypeRapportJasper TYPE_RAPPORT = TypeRapportJasper.FICHE_INFO_ESSAI;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedFactory = Mockito.mock(JRExporterFactory.class);
        this.mockedIoFactory = Mockito.mock(IOStreamsFactory.class);
        this.exporter = new JasperPrintExporter();
        this.exporter.setJrExporterFactories(new TreeMap<String, JRExporterFactory>());
        this.exporter.setIoFactory(this.mockedIoFactory);
        this.exporter.getJrExporterFactories().put(JasperPrintExporterTest.TYPE_RAPPORT.getTypeExport().name(), this.mockedFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.exporter = null;
        this.mockedFactory = null;
        this.mockedIoFactory = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.exporter);
        Assert.assertEquals(this.mockedIoFactory, this.exporter.getIoFactory());
        Assert.assertEquals(this.mockedFactory, this.exporter.getJrExporterFactories().get(JasperPrintExporterTest.TYPE_RAPPORT.getTypeExport().name()));
    }

    /**
     * Test de la méthode
     * JasperPrintExporter#export(fr.pharma.eclipse.domain.enums
     * .jasper.TypeRapportJasper, net.sf.jasperreports.engine.JasperPrint).
     */
    @Test
    public void testExportOk() {
        final TypeRapportJasper typeRapport = JasperPrintExporterTest.TYPE_RAPPORT;
        final JasperPrint jasperPrint = Mockito.mock(JasperPrint.class);

        final ByteArrayOutputStream expectedStream = Mockito.mock(ByteArrayOutputStream.class);
        final JRExporter expectedExporter = Mockito.mock(JRExporter.class);
        Mockito.when(this.mockedFactory.getInitializedObject()).thenReturn(expectedExporter);
        Mockito.when(this.mockedIoFactory.getInitializedByteArrayOutStream()).thenReturn(expectedStream);
        final Answer<Object> answerSetParameter = new Answer<Object>() {

            @SuppressWarnings("unchecked")
            @Override
            public Object answer(final InvocationOnMock invocation) throws Throwable {
                int i = 0;
                final JRExporterParameter arg1 = (JRExporterParameter) invocation.getArguments()[i++];
                final Object arg2 = invocation.getArguments()[i++];

                if (JRExporterParameter.OUTPUT_STREAM.equals(arg1)) {
                    Assert.assertEquals(expectedStream, arg2);
                } else if (JRExporterParameter.JASPER_PRINT_LIST.equals(arg1)) {
                    final List<JasperPrint> argJP = (List<JasperPrint>) arg2;
                    Assert.assertNotNull(argJP);
                    Assert.assertEquals(1, argJP.size());
                    Assert.assertEquals(jasperPrint, argJP.get(0));
                } else {
                    Assert.fail("Mauvais argument passé: " + arg1);
                }

                return null;
            }
        };
        Mockito.doAnswer(answerSetParameter).when(expectedExporter).setParameter(Matchers.any(JRExporterParameter.class), Matchers.anyObject());

        try {
            final ByteArrayOutputStream res = this.exporter.export(typeRapport, jasperPrint);

            Mockito.verify(this.mockedFactory).getInitializedObject();
            Mockito.verify(expectedExporter, Mockito.times(2)).setParameter(Matchers.any(JRExporterParameter.class), Matchers.anyObject());
            Mockito.verify(expectedExporter).exportReport();
            Assert.assertEquals(expectedStream, res);
        } catch (final JasperReportBuildException e) {
            Assert.fail("Aucune exception attendue.");
        } catch (final JRException e) {
            Assert.fail("Erreur de paramétrage des mocks.");
        }
    }

    /**
     * Test de la méthode
     * JasperPrintExporter#export(fr.pharma.eclipse.domain.enums
     * .jasper.TypeRapportJasper, net.sf.jasperreports.engine.JasperPrint) - Ko.
     */
    @Test
    public void testExportKo() {
        final TypeRapportJasper typeRapport = JasperPrintExporterTest.TYPE_RAPPORT;
        final JasperPrint jasperPrint = Mockito.mock(JasperPrint.class);

        try {
            final ByteArrayOutputStream expectedStream = Mockito.mock(ByteArrayOutputStream.class);
            final JRExporter expectedExporter = Mockito.mock(JRExporter.class);
            Mockito.when(this.mockedFactory.getInitializedObject()).thenReturn(expectedExporter);
            Mockito.when(this.mockedIoFactory.getInitializedByteArrayOutStream()).thenReturn(expectedStream);
            final Answer<Object> answerSetParameter = new Answer<Object>() {

                @SuppressWarnings("unchecked")
                @Override
                public Object answer(final InvocationOnMock invocation) throws Throwable {
                    int i = 0;
                    final JRExporterParameter arg1 = (JRExporterParameter) invocation.getArguments()[i++];
                    final Object arg2 = invocation.getArguments()[i++];

                    if (JRExporterParameter.OUTPUT_STREAM.equals(arg1)) {
                        Assert.assertEquals(expectedStream, arg2);
                    } else if (JRExporterParameter.JASPER_PRINT_LIST.equals(arg1)) {
                        final List<JasperPrint> argJP = (List<JasperPrint>) arg2;
                        Assert.assertNotNull(argJP);
                        Assert.assertEquals(1, argJP.size());
                        Assert.assertEquals(jasperPrint, argJP.get(0));
                    } else {
                        Assert.fail("Mauvais argument passé: " + arg1);
                    }

                    return null;
                }
            };
            Mockito.doAnswer(answerSetParameter).when(expectedExporter).setParameter(Matchers.any(JRExporterParameter.class), Matchers.anyObject());
            final Throwable expectedException = Mockito.mock(JRException.class);
            Mockito.doThrow(expectedException).when(expectedExporter).exportReport();

            try {
                this.exporter.export(typeRapport, jasperPrint);
                Assert.fail("Exception attendue.");

            } catch (final JasperReportBuildException e) {
                Assert.assertEquals(expectedException, e.getCause());
            }

            Mockito.verify(this.mockedFactory).getInitializedObject();
            Mockito.verify(expectedExporter, Mockito.times(2)).setParameter(Matchers.any(JRExporterParameter.class), Matchers.anyObject());
            Mockito.verify(expectedExporter).exportReport();
        } catch (final JRException e) {
            Assert.fail("Erreur de paramétrage des mocks.");
        }
    }

}

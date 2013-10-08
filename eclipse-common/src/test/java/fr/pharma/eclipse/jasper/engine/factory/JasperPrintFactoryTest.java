package fr.pharma.eclipse.jasper.engine.factory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import fr.pharma.eclipse.jasper.engine.helper.JasperPrintHelper;
import fr.pharma.eclipse.jasper.exception.JasperReportBuildException;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe JasperPrintFactory.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JasperPrintFactoryTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private JasperPrintFactory factory;

    /**
     * Helper mocké.
     */
    private JasperPrintHelper mockedHelper;

    /**
     * Mock de resource.
     */
    private Resource mockedResource;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedResource = Mockito.mock(Resource.class);
        this.mockedHelper = Mockito.mock(JasperPrintHelper.class);
        this.factory = new JasperPrintFactory(this.mockedResource);
        this.factory.setHelper(this.mockedHelper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.factory = null;
        this.mockedHelper = null;
        this.mockedResource = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.factory);
        Assert.assertEquals(this.mockedResource, this.factory.getTemplate());
        Assert.assertEquals(this.mockedHelper, this.factory.getHelper());
    }

    /**
     * Test de la méthode getInitializedObject(java.util.Map)}.
     */
    @Test
    public void testGetInitializedObjectMap() {
        try {
            final Map<String, Object> mapParameters = new HashMap<String, Object>();
            mapParameters.put("key1", "value 1");

            final JasperReport expectedJasperReport = Mockito.mock(JasperReport.class);
            final JasperPrint expectedJasperPrint = Mockito.mock(JasperPrint.class);
            final InputStream expectedInputStream = this.getInputStreamTest();
            Mockito.when(this.mockedResource.getInputStream()).thenReturn(expectedInputStream);
            Mockito.when(this.mockedHelper.loadObject(expectedInputStream)).thenReturn(expectedJasperReport);
            Mockito.when(this.mockedHelper.fillReport(expectedJasperReport, mapParameters)).thenReturn(expectedJasperPrint);

            final JasperPrint res = this.factory.getInitializedObject(mapParameters);

            Mockito.verify(this.mockedResource).getInputStream();
            Mockito.verify(this.mockedHelper).loadObject(expectedInputStream);
            Mockito.verify(this.mockedHelper).fillReport(expectedJasperReport, mapParameters);
            Assert.assertEquals(expectedJasperPrint, res);
        } catch (final IOException e) {
            Assert.fail("Erreur de paramétrage des mocks. " + e.getMessage());
        } catch (final JasperReportBuildException e) {
            Assert.assertTrue(StringUtils.hasText(e.getMessage()));
        } catch (final JRException e) {
            Assert.fail("Erreur de paramétrage des mocks. " + e.getMessage());
        }
    }

    /**
     * Test de la méthode
     * getInitializedObject(net.sf.jasperreports.engine.JRDataSource,
     * java.util.Map)}.
     */
    @Test
    public void testGetInitializedObjectJRDataSourceMapOk() {
        try {
            final Collection<String> collDataSource = Arrays.asList("a", "b", "c");
            final JRDataSource datasource = new JRBeanCollectionDataSource(collDataSource);
            final Map<String, Object> mapParameters = new HashMap<String, Object>();
            mapParameters.put("key1", "value 1");

            final JasperReport expectedJasperReport = Mockito.mock(JasperReport.class);
            final JasperPrint expectedJasperPrint = Mockito.mock(JasperPrint.class);
            final InputStream expectedInputStream = this.getInputStreamTest();
            Mockito.when(this.mockedResource.getInputStream()).thenReturn(expectedInputStream);
            Mockito.when(this.mockedHelper.loadObject(expectedInputStream)).thenReturn(expectedJasperReport);
            Mockito.when(this.mockedHelper.fillReport(expectedJasperReport, mapParameters, datasource)).thenReturn(expectedJasperPrint);

            final JasperPrint res = this.factory.getInitializedObject(datasource, mapParameters);

            Mockito.verify(this.mockedResource).getInputStream();
            Mockito.verify(this.mockedHelper).loadObject(expectedInputStream);
            Mockito.verify(this.mockedHelper).fillReport(expectedJasperReport, mapParameters, datasource);
            Assert.assertEquals(expectedJasperPrint, res);
        } catch (final IOException e) {
            Assert.fail("Erreur de paramétrage des mocks. " + e.getMessage());
        } catch (final JasperReportBuildException e) {
            Assert.fail("Aucune exception n'est attendue.");
        } catch (final JRException e) {
            Assert.fail("Erreur de paramétrage des mocks. " + e.getMessage());
        }
    }

    /**
     * Test de la méthode
     * getInitializedObject(net.sf.jasperreports.engine.JRDataSource,
     * java.util.Map)} - JRException.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetInitializedObjectJRDataSourceMapJRException() {
        try {
            final Collection<String> collDataSource = Arrays.asList("a", "b", "c");
            final JRDataSource datasource = new JRBeanCollectionDataSource(collDataSource);
            final Map<String, Object> mapParameters = new HashMap<String, Object>();
            mapParameters.put("key1", "value 1");
            final InputStream expectedInputStream = this.getInputStreamTest();

            final Throwable expectedException = Mockito.mock(JRException.class);
            Mockito.when(this.mockedResource.getInputStream()).thenReturn(expectedInputStream);
            Mockito.when(this.mockedHelper.loadObject(expectedInputStream)).thenThrow(expectedException);

            try {
                this.factory.getInitializedObject(datasource, mapParameters);
                Assert.fail("Exeption attendue.");
            } catch (final JasperReportBuildException e) {
                Assert.assertTrue(StringUtils.hasText(e.getMessage()));
                Assert.assertEquals(expectedException, e.getCause());
            }
            Mockito.verify(this.mockedResource).getInputStream();
            Mockito.verify(this.mockedHelper).loadObject(expectedInputStream);
            Mockito.verify(this.mockedHelper, Mockito.never()).fillReport(Matchers.any(JasperReport.class), Matchers.anyMap(), Matchers.any(JRDataSource.class));
            Mockito.verify(this.mockedHelper, Mockito.never()).fillReport(Matchers.any(JasperReport.class), Matchers.anyMap());
        } catch (final IOException e) {
            Assert.fail("Erreur de paramétrage des mocks. " + e.getMessage());
        } catch (final JRException e) {
            Assert.fail("Erreur de paramétrage des mocks. " + e.getMessage());
        }
    }
    /**
     * Test de la méthode
     * getInitializedObject(net.sf.jasperreports.engine.JRDataSource,
     * java.util.Map)} - IOException.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetInitializedObjectJRDataSourceMapIOException() {
        try {
            final Collection<String> collDataSource = Arrays.asList("a", "b", "c");
            final JRDataSource datasource = new JRBeanCollectionDataSource(collDataSource);
            final Map<String, Object> mapParameters = new HashMap<String, Object>();
            mapParameters.put("key1", "value 1");

            final Throwable expectedException = Mockito.mock(IOException.class);
            Mockito.when(this.mockedResource.getInputStream()).thenThrow(expectedException);

            try {
                this.factory.getInitializedObject(datasource, mapParameters);
                Assert.fail("Exeption attendue.");
            } catch (final JasperReportBuildException e) {
                Assert.assertTrue(StringUtils.hasText(e.getMessage()));
                Assert.assertEquals(expectedException, e.getCause());
            }
            Mockito.verify(this.mockedResource).getInputStream();
            Mockito.verify(this.mockedHelper, Mockito.never()).loadObject(Matchers.any(InputStream.class));
            Mockito.verify(this.mockedHelper, Mockito.never()).fillReport(Matchers.any(JasperReport.class), Matchers.anyMap(), Matchers.any(JRDataSource.class));
            Mockito.verify(this.mockedHelper, Mockito.never()).fillReport(Matchers.any(JasperReport.class), Matchers.anyMap());
        } catch (final IOException e) {
            Assert.fail("Erreur de paramétrage des mocks. " + e.getMessage());
        } catch (final JRException e) {
            Assert.fail("Erreur de paramétrage des mocks. " + e.getMessage());
        }
    }

    /**
     * Méthode de création d'un InputStream pour les tests.
     * @return Un InputStream (non mocké).
     */
    private InputStream getInputStreamTest() {
        return new ByteArrayInputStream(new byte[]{0, 1, 3 });
    }

}

package fr.pharma.eclipse.jasper.engine.factory.exporter.impl;

import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe {@link JRExporterPDFFactory}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRExporterPDFFactoryTest extends AbstractEclipseJUnitTest {
    /**
     * Fabrique testée.
     */
    private JRExporterPDFFactory factory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.factory = new JRExporterPDFFactory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.factory = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.factory);
    }

    /**
     * Test de la méthode getInitializedObject.
     */
    @Test
    public void testGetInitializedObject() {
        final JRExporter exporter = this.factory.getInitializedObject();
        Assert.assertTrue(exporter instanceof JRPdfExporter);
    }
}

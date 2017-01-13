package fr.pharma.eclipse.jasper.engine.factory.exporter.impl;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;

/**
 * Test de la classe JRExporterRTFFactory.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRExporterRTFFactoryTest extends AbstractEclipseJUnitTest {
    /**
     * Fabrique testée.
     */
    private JRExporterRTFFactory factory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.factory = new JRExporterRTFFactory();
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
        Assert.assertTrue(exporter instanceof JRRtfExporter);
    }

}

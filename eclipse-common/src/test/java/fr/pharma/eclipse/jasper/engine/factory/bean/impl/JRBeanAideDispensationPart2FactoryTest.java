package fr.pharma.eclipse.jasper.engine.factory.bean.impl;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart2;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe {@link JRBeanAideDispensationPart2Factory}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanAideDispensationPart2FactoryTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private JRBeanAideDispensationPart2Factory factory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.factory = new JRBeanAideDispensationPart2Factory();
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
     * Test de la méthode getInitializedObject().
     */
    @Test
    public void testGetInitializedObject() {
        final JasperReportBean jrBean = this.factory.getInitializedObject();
        Assert.assertNotNull(jrBean);
        Assert.assertTrue(jrBean instanceof JRBeanFicheAideDispensationPart2);
        final JRBeanFicheAideDispensationPart2 jrBeanPart2 = (JRBeanFicheAideDispensationPart2) jrBean;
        Assert.assertNull(jrBeanPart2.getMedicaments());
        Assert.assertNull(jrBeanPart2.getDispoMedicaux());
        Assert.assertNull(jrBeanPart2.getPduitsTherapeutiques());
    }
}

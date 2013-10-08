package fr.pharma.eclipse.jasper.engine.factory.bean.impl;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart6;
import fr.pharma.eclipse.jasper.constants.JasperConstants;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe {@link JRBeanAideDispensationPart6Factory}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanAideDispensationPart6FactoryTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private JRBeanAideDispensationPart6Factory factory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.factory = new JRBeanAideDispensationPart6Factory();
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
        Assert.assertTrue(jrBean instanceof JRBeanFicheAideDispensationPart6);
        final JRBeanFicheAideDispensationPart6 jrBeanPart6 = (JRBeanFicheAideDispensationPart6) jrBean;
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, jrBeanPart6.getRespRetour());
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, jrBeanPart6.getTypeRetour());
        Assert.assertFalse(jrBeanPart6.getShowTypeRetour());
    }

}

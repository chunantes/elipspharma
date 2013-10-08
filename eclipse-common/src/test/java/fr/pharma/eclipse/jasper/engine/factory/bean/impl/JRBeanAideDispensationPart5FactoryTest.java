package fr.pharma.eclipse.jasper.engine.factory.bean.impl;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart5;
import fr.pharma.eclipse.jasper.constants.JasperConstants;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe {@link JRBeanAideDispensationPart5Factory}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanAideDispensationPart5FactoryTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private JRBeanAideDispensationPart5Factory factory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.factory = new JRBeanAideDispensationPart5Factory();
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
        Assert.assertTrue(jrBean instanceof JRBeanFicheAideDispensationPart5);
        final JRBeanFicheAideDispensationPart5 jrBeanPart5 = (JRBeanFicheAideDispensationPart5) jrBean;
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, jrBeanPart5.getRespCommande());
        Assert.assertNull(jrBeanPart5.getModalitesReception());
    }

}

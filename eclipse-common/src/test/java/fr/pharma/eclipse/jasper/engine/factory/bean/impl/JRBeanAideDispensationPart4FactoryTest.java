package fr.pharma.eclipse.jasper.engine.factory.bean.impl;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart4;
import fr.pharma.eclipse.jasper.constants.JasperConstants;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe {@link JRBeanAideDispensationPart4Factory}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanAideDispensationPart4FactoryTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private JRBeanAideDispensationPart4Factory factory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.factory = new JRBeanAideDispensationPart4Factory();
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
        Assert.assertTrue(jrBean instanceof JRBeanFicheAideDispensationPart4);
        final JRBeanFicheAideDispensationPart4 jrBeanPart4 = (JRBeanFicheAideDispensationPart4) jrBean;
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, jrBeanPart4.getInvestigateurPrincipal());
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, jrBeanPart4.getCoInvestigateurs());
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, jrBeanPart4.getDestinatairesDispensation());
        Assert.assertNull(jrBeanPart4.getTracabiliteObligatoire());
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, jrBeanPart4.getTypeDispensation());
        Assert.assertFalse(jrBeanPart4.getHasTracabilite());
        Assert.assertNull(jrBeanPart4.getHasNumeroTraitement());
        Assert.assertNull(jrBeanPart4.getInfosPatient());
        Assert.assertFalse(jrBeanPart4.getHasGestionRetoursPharma());
        Assert.assertNull(jrBeanPart4.getAideDispensation());
    }

}

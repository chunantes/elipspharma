package fr.pharma.eclipse.jasper.engine.helper;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.jasper.constants.JasperConstants;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe {@link CommonParametersHelper}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class CommonParametersHelperTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private CommonParametersHelper helper;

    /**
     * Répertoire des rapports pour les tests.
     */
    private static final String REPORTS_DIR = "C:";
    /**
     * Répertoire des rapports pour les tests.
     */
    private static final String CHECKBOX_DIR = "C:";

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.helper = new CommonParametersHelper(CommonParametersHelperTest.REPORTS_DIR, CommonParametersHelperTest.CHECKBOX_DIR);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.helper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.helper);
        Assert.assertEquals(CommonParametersHelperTest.REPORTS_DIR, this.helper.getReportsDirectory());
    }

    /**
     * Test de la méthode
     * CommonParametersHelper#addCommonParameters(java.util.Map).
     */
    @Test
    public void testAddCommonParametersOk() {
        final Map<String, Object> mapParameters = new HashMap<String, Object>();
        this.helper.addCommonParameters(mapParameters);
        Assert.assertEquals(CommonParametersHelperTest.REPORTS_DIR, mapParameters.get(JasperConstants.SUBREPORT_DIRECTORY));
    }

    /**
     * Test de la méthode
     * CommonParametersHelper#addCommonParameters(java.util.Map) - la clé existe
     * déjà.
     */
    @Test
    public void testAddCommonParametersKo() {
        final Map<String, Object> mapParameters = new HashMap<String, Object>();
        final String key = JasperConstants.SUBREPORT_DIRECTORY;
        final String otherValue = "D:";
        mapParameters.put(key, otherValue);
        this.helper.addCommonParameters(mapParameters);
        Assert.assertEquals(otherValue, mapParameters.get(key));
    }

}

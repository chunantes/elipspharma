package fr.pharma.eclipse.jasper.engine.factory;

import java.util.Arrays;

import net.sf.jasperreports.engine.JRDataSource;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe JRDataSourceFactory.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRDataSourceFactoryTest extends AbstractEclipseJUnitTest {
    /**
     * Fabrique testée.
     */
    private JRDataSourceFactory factory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.factory = new JRDataSourceFactory();
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
     * Test de la méthode getInitializedObject(java.lang.Object[]) - 0 élément.
     */
    @Test
    public void testGetInitializedObject0() {
        final JRDataSource res = this.factory.getInitializedObject(null);
        Assert.assertNotNull(res);
    }

    /**
     * Test de la méthode getInitializedObject(java.lang.Object[]) - 1 élément.
     */
    @Test
    public void testGetInitializedObject1() {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        final JRDataSource res = this.factory.getInitializedObject(essai);
        Assert.assertNotNull(res);
    }

    /**
     * Test de la méthode getInitializedObject(java.lang.Object[]) - plusieurs
     * éléments.
     */
    @Test
    public void testGetInitializedObjectPlusieurs() {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        final JRDataSource res = this.factory.getInitializedObject(Arrays.asList(essai, essai));
        Assert.assertNotNull(res);
    }
}

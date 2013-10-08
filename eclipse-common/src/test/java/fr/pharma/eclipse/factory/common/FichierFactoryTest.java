package fr.pharma.eclipse.factory.common;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe {@link FichierFactory}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class FichierFactoryTest extends AbstractEclipseJUnitTest {
    /**
     * Fabrique testée.
     */
    private FichierFactory factory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.factory = new FichierFactory();

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
        final Fichier fichier = this.factory.getInitializedObject();
        Assert.assertNotNull(fichier);
        Assert.assertNotNull(fichier.getContenu());
        Assert.assertNotNull(fichier.getNom());
        Assert.assertNotNull(fichier.getTypeFichier());
        Assert.assertNull(fichier.getFile());
        Assert.assertEquals(StringUtils.EMPTY, fichier.getNom());
        Assert.assertEquals(StringUtils.EMPTY, fichier.getTypeFichier());
        Assert.assertEquals(0, fichier.getContenu().length);
    }

}

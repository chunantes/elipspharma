package fr.pharma.eclipse.factory.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test de IOStreamsFactory.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class IOStreamsFactoryTest {
    /**
     * Classe testée.
     */
    private IOStreamsFactory factory;

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void setUp() {
        this.factory = new IOStreamsFactory();
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown() {
        this.factory = null;
    }

    /**
     * Test de la méthode getInitializedInputStream(File).
     */
    @Test
    public void testGetInitializedInputStream() {
        try {
            this.factory.getInitializedInputStream(new File(""));
            Assert.fail("Exception attendue.");
        } catch (final FileNotFoundException e) {
            Assert.assertTrue(true);
        }
    }

    /**
     * Test de la méthode getInitializedByteArrayOutStream.
     */
    @Test
    public void testGetInitializedByteArrayOutStream() {
        final ByteArrayOutputStream res = this.factory.getInitializedByteArrayOutStream();
        Assert.assertNotNull(res);
        Assert.assertEquals(0, res.toByteArray().length);
    }
}

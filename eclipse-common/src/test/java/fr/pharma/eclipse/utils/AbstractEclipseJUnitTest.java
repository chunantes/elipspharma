package fr.pharma.eclipse.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Interface des tests unitaires Eclipse.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public abstract class AbstractEclipseJUnitTest {
    /**
     * Méthode d'initialisation.
     */
    @Before
    public abstract void setUp();

    /**
     * Méthode de finalisation.
     */
    @After
    public abstract void tearDown();

    /**
     * Test d'initialisation.
     */
    @Test
    public abstract void testInit();
}

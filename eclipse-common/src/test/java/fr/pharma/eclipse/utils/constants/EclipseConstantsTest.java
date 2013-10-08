package fr.pharma.eclipse.utils.constants;

import java.util.Locale;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de test des constantes du projet ECLIPSE.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EclipseConstantsTest {

    /**
     * EclipseConstants à tester.
     */
    private EclipseConstants cstes;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.cstes = new EclipseConstants();
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.cstes = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.cstes);
    }

    /**
     * Méthode en charge de tester la valeur des constantes.
     */
    @Test
    public void testConstantes() {
        Assert.assertEquals("\n", EclipseConstants.SAUT_LIGNE);
        Assert.assertEquals(Locale.FRANCE, EclipseConstants.LOCALE);
    }

}

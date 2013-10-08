package fr.pharma.eclipse.domain.model.common.constants;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de test des constantes associées à la gestion communes des beans
 * métier.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BeanCstesTest {

    /**
     * BeanCstes à tester.
     */
    private BeanCstes beanCstes;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.beanCstes = new BeanCstes();
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.beanCstes = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.beanCstes);
    }

    /**
     * Méthode en charge de tester la valeur des constantes.
     */
    @Test
    public void testConstantes() {
        Assert.assertEquals(Integer.valueOf("37").intValue(), BeanCstes.NB_PREMIER);
        Assert.assertEquals("mandatory.", BeanCstes.MANDATORY);
        Assert.assertEquals("unique.", BeanCstes.UNIQUE);
    }

}

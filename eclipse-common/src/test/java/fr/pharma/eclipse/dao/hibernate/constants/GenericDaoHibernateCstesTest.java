package fr.pharma.eclipse.dao.hibernate.constants;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de test des constantes associées à la gestion du DAO générique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GenericDaoHibernateCstesTest {
    /**
     * GenericDaoHibernateCstes à tester.
     */
    private GenericDaoHibernateCstes cstes;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.cstes = new GenericDaoHibernateCstes();
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
        Assert.assertEquals("L'entity manager a été fermé", GenericDaoHibernateCstes.ENTITY_MANAGER_CLOSED);
        Assert.assertEquals("Le bean n'est pas managé par l'entity manager", GenericDaoHibernateCstes.BEAN_UNMANAGED);
        Assert.assertEquals("Aucune transaction disponible", GenericDaoHibernateCstes.NO_TRANSACTION_AVAILABLE);
        Assert.assertEquals("L'entité n'est plus présente en base", GenericDaoHibernateCstes.ENTITY_NOT_FOUND_DB);
        Assert.assertEquals("L'entité ne peut pas être persistée", GenericDaoHibernateCstes.ENTITY_ERROR_PERSIST);
    }
}

package fr.pharma.eclipse.service.helper.common;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.suivi.essai.EssaiSuivi;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe BeanHelper.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BeanHelperTest {
    /**
     * Helper testé.
     */
    private BeanHelper helper;

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void setUp() {
        this.helper = new BeanHelper();
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown() {
        this.helper = null;
    }

    /**
     * Test de la méthode addToCollection.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testAddToCollection() {
        long ids = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(ids++, null);
        final EssaiSuivi suivi = Mockito.mock(EssaiSuivi.class);
        Assert.assertTrue(essai.getModifs().isEmpty());
        this.helper.addToCollection(essai, "modifs", suivi);
        Assert.assertEquals(1, essai.getModifs().size());
        Assert.assertEquals(suivi, essai.getModifs().first());
    }

}

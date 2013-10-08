package fr.pharma.eclipse.service.helper.common;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.recherche.CommentaireEssaiRecherche;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe BeanPropertyReinitializer.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BeanPropertyReinitializerTest {
    /**
     * Helper testé.
     */
    private BeanPropertyReinitializer<Essai> helper;

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void setUp() {
        this.helper = new BeanPropertyReinitializer<Essai>();
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown() {
        this.helper = null;
    }

    /**
     * Test de la méthode resetPropertyToNull.
     */
    @Test
    public void testResetPropertyToNull() {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        Assert.assertNotNull(essai.getId());
        this.helper.resetPropertyToNull(essai, "id");
        Assert.assertNull(essai.getId());
    }

    /**
     * Test de la méthode resetCollection : collection nulle.
     */
    @Test
    public void testResetCollectionKo() {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        essai.getDetailRecherche().setCommentaires(null);
        this.helper.resetCollection(essai, "detailRecherche.commentaires");
        Assert.assertNull(essai.getDetailRecherche().getCommentaires());
    }

    /**
     * Test de la méthode resetCollection.
     */
    @Test
    public void testResetCollectionOk() {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        essai.getDetailRecherche().getCommentaires().add(Mockito.mock(CommentaireEssaiRecherche.class));
        Assert.assertNotNull(essai.getDetailRecherche().getCommentaires());
        Assert.assertFalse(essai.getDetailRecherche().getCommentaires().isEmpty());
        this.helper.resetCollection(essai, "detailRecherche.commentaires");
        Assert.assertNotNull(essai.getDetailRecherche().getCommentaires());
        Assert.assertTrue(essai.getDetailRecherche().getCommentaires().isEmpty());
    }

}

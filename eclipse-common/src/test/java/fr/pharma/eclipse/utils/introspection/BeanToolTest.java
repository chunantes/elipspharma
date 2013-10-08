package fr.pharma.eclipse.utils.introspection;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.exception.common.CommonException;

/**
 * Test de la classe BeanTool.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BeanToolTest {
    /**
     * Logger par défaut.
     */
    private static final Logger LOG = LoggerFactory.getLogger(BeanToolTest.class);

    /**
     * Teste la récupération du nom de la classe du bean.
     */
    @Test
    public void getClassNameWithNull() {
        final String className = BeanTool.getClassName(null);
        Assert.assertEquals(className, BeanTool.NULL);
    }

    /**
     * Teste la récupération du nom de la classe du bean.
     */
    @Test
    public void getClassNameWithNotNull() {
        final String className = BeanTool.getClassName(StringUtils.EMPTY);
        Assert.assertEquals(className, "java.lang.String");
    }

    /**
     * Méthode en charge de tester l'introspection.
     */
    @Test
    public void hasProprieteOk() {
        final Essai essai = new Essai();
        final EtatEssai etat = EtatEssai.CLOTURE;
        essai.setNom("nom");
        essai.setEtat(etat);
        Assert.assertTrue(BeanTool.hasProperty(essai, "nom"));
        Assert.assertTrue(BeanTool.hasProperty(essai, "etat"));
    }

    /**
     * Méthode en charge de tester l'introspection.
     */
    @Test
    public void hasProprieteNotExists() {
        final Essai essai = new Essai();
        Assert.assertFalse(BeanTool.hasProperty(essai, "lifeInfo.creePxxxxar"));
    }

    /**
     * Méthode en charge de tester l'introspection.
     */
    @Test
    public void hasProprieteNullBean() {
        final Essai essai = null;

        Assert.assertFalse(BeanTool.hasProperty(essai, "nom"));
    }

    /**
     * Méthode en charge de tester l'introspection.
     * @throws CommonException Erreur d'introspection.
     */
    @Test
    public void getProprieteOk() throws CommonException {
        final Essai essai = this.prepareEssai();
        Assert.assertEquals("nom", BeanTool.getPropriete(essai, "nom"));
        Assert.assertEquals(1L, BeanTool.getPropriete(essai, "id"));
    }

    /**
     * Méthode en charge de tester l'introspection.
     * @throws CommonException Erreur d'introspection.
     */
    @Test
    public void getProprieteNestedNull() throws CommonException {
        final Essai essai = new Essai();
        essai.setId(1L);
        Assert.assertEquals(null, BeanTool.getPropriete(essai, "nom"));

    }

    /**
     * Méthode en charge de tester l'introspection.
     */
    @Test
    public void getProprieteKo() {
        final Essai essai = new Essai();

        try {
            BeanTool.getPropriete(essai, "lifeInfo.creePxxxxar");
            Assert.fail("Une erreur devrait apparaitre");
        } catch (final CommonException e) {
            BeanToolTest.LOG.debug(e.getMessage(), e);
        }
    }

    /**
     * Méthode en charge de tester l'introspection.
     */
    @Test
    public void setProprieteOk() {
        final Essai essai = new Essai();

        try {
            BeanTool.setPropriete(essai, "id", 1L);
            Assert.assertEquals((Long) 1L, essai.getId());
        } catch (final CommonException e) {
            Assert.fail(" Exception : " + e.getClass().getName() + " - " + e.getMessage());
        }
    }

    /**
     * Méthode en charge de tester la définition d'une valeur null.
     */
    @Test
    public void setProprieteBeanNull() {
        try {
            BeanTool.setPropriete(null, "login", "login");
            Assert.fail("Ce point ne devrait pas être atteint.");
        } catch (final CommonException e) {
            Assert.assertTrue(StringUtils.contains(e.getMessage(), "Le bean ou la propriété n'est pas défini : No bean specified"));
        }
    }

    /**
     * Méthode en charge de tester l'introspection.
     * @throws CommonException Erreur d'introspection.
     */
    @Test
    public void copyProprieteOk() throws CommonException {
        final Essai essai = this.prepareEssai();

        final Essai essai2 = new Essai();
        essai2.setId(1L);

        BeanTool.copyProperties(essai2, essai);
        Assert.assertEquals("nom", essai2.getNom());
        Assert.assertEquals(EtatEssai.EN_ATTENTE_LETTRE_CLOTURE, essai2.getEtat());
        Assert.assertEquals((Long) 1L, essai2.getId());
    }

    /**
     * Méthode en charge de tester l'introspection avec un null comme cible.
     * @throws CommonException Erreur d'introspection.
     */
    @Test
    public void copyProprieteWithCibleNull() throws CommonException {
        final Essai essai = this.prepareEssai();

        final Essai essai2 = null;

        BeanTool.copyProperties(essai2, essai);

    }

    /**
     * Méthode en charge de tester l'introspection avec un null comme cible.
     * @throws CommonException Erreur d'introspection.
     */
    @Test
    public void copyProprieteWithOrigineNull() throws CommonException {
        final Essai essai = null;

        final Essai essai2 = new Essai();

        BeanTool.copyProperties(essai2, essai);

    }

    /**
     * Méthode en charge de créer un utilisateur.
     * @return utilisateur créé.
     */
    private Essai prepareEssai() {
        final Essai essai = new Essai();
        essai.setId(1L);
        essai.setNom("nom");
        essai.setEtat(EtatEssai.EN_ATTENTE_LETTRE_CLOTURE);
        essai.setDateSignature(Calendar.getInstance());
        return essai;
    }
}

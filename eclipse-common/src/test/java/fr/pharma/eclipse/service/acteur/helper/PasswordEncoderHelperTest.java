package fr.pharma.eclipse.service.acteur.helper;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Personne;

/**
 * Classe en charge de tester la classe helper d'encodage de password.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PasswordEncoderHelperTest {
    /**
     * PasswordEncoderHelper à tester.
     */
    private PasswordEncoderHelper encoderHelper;

    /**
     * Md5PasswordEncoder mocké.
     */
    private Md5PasswordEncoder mockMd5PasswordEncoder;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.encoderHelper = new PasswordEncoderHelper();
        this.mockMd5PasswordEncoder = Mockito.mock(Md5PasswordEncoder.class);
        this.encoderHelper.setPasswordEncoder(this.mockMd5PasswordEncoder);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.encoderHelper = null;
        this.mockMd5PasswordEncoder = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.encoderHelper);
        Assert.assertNotNull(this.mockMd5PasswordEncoder);
    }

    /**
     * Méthode en charge de tester l'encodage de password.
     */
    @Test
    public void testEncodePasswordNull() {
        final Personne personne = new Investigateur();
        this.encoderHelper.encodePassword(personne);
        Assert.assertNull(personne.getPassword());
    }

    /**
     * Méthode en charge de tester l'encodage de password.
     */
    @Test
    public void testEncodePasswordEmpty() {
        final Personne personne = new Investigateur();
        personne.setPassword(StringUtils.EMPTY);
        this.encoderHelper.encodePassword(personne);
        Assert.assertEquals(StringUtils.EMPTY, personne.getPassword());
    }

    /**
     * Méthode en charge de tester l'encodage de password.
     */
    @Test
    public void testEncodePasswordOK() {
        final Personne personne = new Investigateur();
        personne.setPassword("test");
        this.encoderHelper.encodePassword(personne);
        Mockito.verify(this.mockMd5PasswordEncoder).encodePassword("test", null);
    }

    /**
     * Méthode en charge de tester l'encodage de password. Pas d'appel à
     * l'encodage. Password déjà encodé.
     */
    @Test
    public void testEncodePasswordKO() {
        final Personne personne = new Investigateur();
        personne.setPassword("c4ca4238a0b923820dcc509a6f75849b");
        this.encoderHelper.encodePassword(personne);
        Assert.assertEquals("c4ca4238a0b923820dcc509a6f75849b", personne.getPassword());
    }

}

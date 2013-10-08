package fr.pharma.eclipse.utils.message;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Classe en charge de tester le builder de messages pour Eclipse.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
public class MessageBuilderTest {
    /** Classe de test */
    @Resource(name = "messageBuilder")
    private MessageBuilder messageBuilder;

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.messageBuilder);
    }

    /**
     * Méthode en charge de tester la récupération d'un message à partir d'une
     * String.
     */
    @Test
    public void testGetMessageString() {
        Assert.assertEquals("Le nom est obligatoire.", this.messageBuilder.getMessage("stockage.nom.notEmpty"));
    }

    /**
     * Méthode en charge de tester la récupération d'un message à partir d'une
     * String et d'un tableau d'args.
     */
    @Test
    public void testGetMessageStringObjectArray() {
        Assert.assertEquals("Le nom est obligatoire.", this.messageBuilder.getMessage("stockage.nom.notEmpty", new Object[]{}));
    }

}

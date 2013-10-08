package fr.pharma.eclipse.integration.factory.acteur;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.factory.acteur.PersonneFactory;

/**
 * Test d'intégration de la fabrique d'objets Investigateur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class InvestigateurFactoryIntegTest {

    /**
     * Fabrique d'investigateurs.
     */
    @Resource(name = "investigateurFactory")
    private PersonneFactory<Investigateur> investigateurFactory;

    /**
     * Test d'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.investigateurFactory);
    }

    /**
     * Test de la création d'un bean Investigateur.
     */
    @Test
    public void testCreation() {
        final Investigateur investigateur = this.investigateurFactory.getInitializedObject();
        Assert.assertEquals(TypePersonne.INVESTIGATEUR, investigateur.getType());
    }

}

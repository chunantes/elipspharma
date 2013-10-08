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
import fr.pharma.eclipse.domain.model.acteur.ArcPromoteur;
import fr.pharma.eclipse.factory.acteur.PersonneFactory;

/**
 * Test d'intégration de la fabrique d'objets ArcPromoteur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class ArcPromoteurFactoryIntegTest {

    /**
     * Fabrique d'arcPromoteurs.
     */
    @Resource(name = "arcPromoteurFactory")
    private PersonneFactory<ArcPromoteur> arcPromoteurFactory;

    /**
     * Test d'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.arcPromoteurFactory);
    }

    /**
     * Test de la création d'un bean ArcPromoteur.
     */
    @Test
    public void testCreation() {
        final ArcPromoteur arcPromoteur = this.arcPromoteurFactory.getInitializedObject();
        Assert.assertEquals(TypePersonne.ARC_PROMOTEUR, arcPromoteur.getType());
    }

}

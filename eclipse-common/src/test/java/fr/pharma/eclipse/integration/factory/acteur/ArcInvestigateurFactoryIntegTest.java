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
import fr.pharma.eclipse.domain.model.acteur.ArcInvestigateur;
import fr.pharma.eclipse.factory.acteur.PersonneFactory;

/**
 * Test d'intégration de la fabrique d'objets ArcInvestigateur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class ArcInvestigateurFactoryIntegTest {

    /**
     * Fabrique d'arcInvestigateurs.
     */
    @Resource(name = "arcInvestigateurFactory")
    private PersonneFactory<ArcInvestigateur> arcInvestigateurFactory;

    /**
     * Test d'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.arcInvestigateurFactory);
    }

    /**
     * Test de la création d'un bean ArcInvestigateur.
     */
    @Test
    public void testCreation() {
        final ArcInvestigateur arcInvestigateur = this.arcInvestigateurFactory.getInitializedObject();
        Assert.assertEquals(TypePersonne.ARC_INVESTIGATEUR, arcInvestigateur.getType());
    }

}

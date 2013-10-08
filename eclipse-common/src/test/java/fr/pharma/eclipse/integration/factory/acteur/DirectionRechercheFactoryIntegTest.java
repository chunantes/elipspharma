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
import fr.pharma.eclipse.domain.model.acteur.DirectionRecherche;
import fr.pharma.eclipse.factory.acteur.PersonneFactory;

/**
 * Test d'intégration de la fabrique d'objets DirectionRecherche.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class DirectionRechercheFactoryIntegTest {

    /**
     * Fabrique d'directionRecherches.
     */
    @Resource(name = "directionRechercheFactory")
    private PersonneFactory<DirectionRecherche> directionRechercheFactory;

    /**
     * Test d'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.directionRechercheFactory);
    }

    /**
     * Test de la création d'un bean DirectionRecherche.
     */
    @Test
    public void testCreation() {
        final DirectionRecherche directionRecherche = this.directionRechercheFactory.getInitializedObject();
        Assert.assertEquals(TypePersonne.DIRECTION_RECHERCHE, directionRecherche.getType());
    }

}

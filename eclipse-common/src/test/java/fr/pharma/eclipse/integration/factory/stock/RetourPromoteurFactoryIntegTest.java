package fr.pharma.eclipse.integration.factory.stock;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.stock.RetourPromoteur;
import fr.pharma.eclipse.factory.stock.MvtStockFactory;

/**
 * Test d'intégration de la fabrique d'objets RetourPromoteur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class RetourPromoteurFactoryIntegTest {
    /**
     * Fabrique de retourPromoteurs.
     */
    @Resource(name = "retourPromoteurFactory")
    private MvtStockFactory<RetourPromoteur> retourPromoteurFactory;

    /**
     * Test d'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.retourPromoteurFactory);
    }

    /**
     * Test de la création d'un bean RetourPromoteur.
     */
    @Test
    public void testCreation() {
        final RetourPromoteur retourPromoteur = this.retourPromoteurFactory.getInitializedObject();
        Assert.assertEquals(TypeMvtStock.RETOUR_PROMOTEUR, retourPromoteur.getType());
    }

}

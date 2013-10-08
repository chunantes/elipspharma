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
import fr.pharma.eclipse.domain.model.stock.Destruction;
import fr.pharma.eclipse.factory.stock.MvtStockFactory;

/**
 * Test d'intégration de la fabrique d'objets Destruction.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class DestructionFactoryIntegTest {
    /**
     * Fabrique de destructions.
     */
    @Resource(name = "destructionFactory")
    private MvtStockFactory<Destruction> destructionFactory;

    /**
     * Test d'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.destructionFactory);
    }

    /**
     * Test de la création d'un bean Destruction.
     */
    @Test
    public void testCreation() {
        final Destruction destruction = this.destructionFactory.getInitializedObject();
        Assert.assertEquals(TypeMvtStock.DESTRUCTION, destruction.getType());
    }

}

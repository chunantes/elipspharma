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
import fr.pharma.eclipse.domain.model.stock.CessionPui;
import fr.pharma.eclipse.factory.stock.MvtStockFactory;

/**
 * Test d'intégration de la fabrique d'objets CessionPui.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class CessionPuiFactoryIntegTest {
    /**
     * Fabrique de cessionPui.
     */
    @Resource(name = "cessionPuiFactory")
    private MvtStockFactory<CessionPui> cessionPuiFactory;

    /**
     * Test d'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.cessionPuiFactory);
    }

    /**
     * Test de la création d'un bean CessionPui.
     */
    @Test
    public void testCreation() {
        final CessionPui cessionPui = this.cessionPuiFactory.getInitializedObject();
        Assert.assertEquals(TypeMvtStock.CESSION_PUI, cessionPui.getType());
    }

}

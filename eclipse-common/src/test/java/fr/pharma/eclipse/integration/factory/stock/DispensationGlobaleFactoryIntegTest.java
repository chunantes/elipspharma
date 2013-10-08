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
import fr.pharma.eclipse.domain.model.stock.DispensationGlobale;
import fr.pharma.eclipse.factory.stock.MvtStockFactory;

/**
 * Test d'intégration de la fabrique d'objets DispensationGlobale.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class DispensationGlobaleFactoryIntegTest {
    /**
     * Fabrique de dispensationGlobales.
     */
    @Resource(name = "dispensationGlobaleFactory")
    private MvtStockFactory<DispensationGlobale> dispensationGlobaleFactory;

    /**
     * Test d'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.dispensationGlobaleFactory);
    }

    /**
     * Test de la création d'un bean DispensationGlobale.
     */
    @Test
    public void testCreation() {
        final DispensationGlobale dispensationGlobale = this.dispensationGlobaleFactory.getInitializedObject();
        Assert.assertEquals(TypeMvtStock.DOTATION, dispensationGlobale.getType());
    }

}

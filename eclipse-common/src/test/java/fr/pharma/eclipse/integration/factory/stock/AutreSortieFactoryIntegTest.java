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
import fr.pharma.eclipse.domain.model.stock.AutreSortie;
import fr.pharma.eclipse.factory.stock.MvtStockFactory;

/**
 * Test d'intégration de la fabrique d'objets AutreSortie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class AutreSortieFactoryIntegTest {
    /**
     * Fabrique de autreSorties.
     */
    @Resource(name = "autreSortieFactory")
    private MvtStockFactory<AutreSortie> autreSortieFactory;

    /**
     * Test d'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.autreSortieFactory);
    }

    /**
     * Test de la création d'un bean AutreSortie.
     */
    @Test
    public void testCreation() {
        final AutreSortie autreSortie = this.autreSortieFactory.getInitializedObject();
        Assert.assertEquals(TypeMvtStock.AUTRE_SORTIE, autreSortie.getType());
    }

}

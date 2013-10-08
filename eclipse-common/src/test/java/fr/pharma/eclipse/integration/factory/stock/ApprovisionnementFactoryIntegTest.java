package fr.pharma.eclipse.integration.factory.stock;

import java.util.Calendar;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.factory.stock.ApprovisionnementFactory;

/**
 * Test d'intégration de la fabrique d'objets Approvisionnement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class ApprovisionnementFactoryIntegTest {
    /**
     * Fabrique d'approvisionnements.
     */
    @Resource(name = "approvisionnementFactory")
    private ApprovisionnementFactory approvisionnementFactory;

    /**
     * Test d'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.approvisionnementFactory);
    }

    /**
     * Test de la création d'un bean Approvisionnement.
     */
    @Test
    public void testCreation() {
        final Approvisionnement approvisionnement = this.approvisionnementFactory.getInitializedObject();
        Assert.assertEquals(TypeMvtStock.APPROVISIONNEMENT, approvisionnement.getType());
        Assert.assertTrue(approvisionnement.getApproApprouve());
    }

    /**
     * Test de la création d'un bean Approvisionnement.
     */
    @Test
    public void testCreationWithParams() {
        final Essai essai = Mockito.mock(Essai.class);
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        final Calendar dateReception = Mockito.mock(Calendar.class);
        final Calendar dateArriveeColis = Mockito.mock(Calendar.class);

        final Approvisionnement approvisionnement = this.approvisionnementFactory.getInitializedObject(essai, pharmacie, dateReception, dateArriveeColis);
        Assert.assertEquals(TypeMvtStock.APPROVISIONNEMENT, approvisionnement.getType());
        Assert.assertTrue(approvisionnement.getApproApprouve());
        Assert.assertNotNull(approvisionnement.getEssai());
        Assert.assertNotNull(approvisionnement.getPharmacie());
        Assert.assertNotNull(approvisionnement.getDateReception());
        Assert.assertNotNull(approvisionnement.getDateArriveeColis());
    }

}

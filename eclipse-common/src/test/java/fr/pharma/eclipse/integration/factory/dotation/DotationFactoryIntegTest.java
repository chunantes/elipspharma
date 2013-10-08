package fr.pharma.eclipse.integration.factory.dotation;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.domain.model.dotation.Dotation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.factory.dotation.DotationFactory;

/**
 * Test d'intégration de la fabrique d'objets Dotation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class DotationFactoryIntegTest {
    /**
     * Fabrique de dotation.
     */
    @Resource(name = "dotationFactory")
    private DotationFactory dotationFactory;

    /**
     * Test d'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.dotationFactory);
    }

    /**
     * Test de la création d'un bean Dotation.
     */
    @Test
    public void testCreation() {
        final Dotation dotation = this.dotationFactory.getInitializedObject();
        Assert.assertNotNull(dotation);
    }

    /**
     * Test de la création d'un bean Dotation.
     */
    @Test
    public void testCreationWithParams() {
        final Service service = Mockito.mock(Service.class);
        final Essai essai = Mockito.mock(Essai.class);

        final Dotation dotation = this.dotationFactory.getInitializedObject(essai, service);
        Assert.assertNotNull(dotation.getService());
        Assert.assertEquals(Boolean.FALSE, dotation.getTraitee());
    }

}

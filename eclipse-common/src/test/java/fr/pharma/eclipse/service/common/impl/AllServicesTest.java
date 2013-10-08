package fr.pharma.eclipse.service.common.impl;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.domain.model.localisation.Etablissement;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.localisation.EtablissementService;
import fr.pharma.eclipse.service.localisation.impl.EtablissementServiceImpl;

/**
 * Classe en charge de tester les services (déclaration spring).
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class AllServicesTest {
    /**
     * Service de gestion des établissements.
     */
    @Resource(name = "etablissementService")
    private GenericService<Etablissement> etabService1;
    /**
     * Service de gestion des établissements.
     */
    @Resource(name = "etablissementService")
    private GenericServiceImpl<Etablissement> etabService2;
    /**
     * Service de gestion des établissements.
     */
    @Resource(name = "etablissementService")
    private EtablissementService etabService3;
    /**
     * Service de gestion des établissements.
     */
    @Resource(name = "etablissementService")
    private EtablissementServiceImpl etabService4;

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.etabService1);
        Assert.assertNotNull(this.etabService2);
        Assert.assertNotNull(this.etabService3);
        Assert.assertNotNull(this.etabService4);
    }

}

package fr.pharma.eclipse.service.alerte.builder.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.essai.EssaiAlerte;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stock.impl.ApprovisionnementServiceImpl;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.message.MessageBuilder;

/**
 * Classe en charge de tester le builder d'alertes concernant les produits /
 * conditionnements / lots / numTraitements dont le délai d'alerte avant la date
 * de péremption est atteint.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AlerteProduitPeremptionBuilderTest {
    /**
     * AlerteProduitPeremptionBuilder à tester.
     */
    private AlerteProduitPeremptionBuilder builder;

    /**
     * Service de gestion des approvisionnements mocké.
     */
    private ApprovisionnementServiceImpl<Approvisionnement> mockApproService;

    /**
     * Service de gestion des produits mocké.
     */
    private ProduitService<Produit> mockProduitService;

    /**
     * Builder de message mocké.
     */
    private MessageBuilder mockMessageBuilder;

    /**
     * Méthode en charge d'initialiser les données.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.builder = new AlerteProduitPeremptionBuilder();
        final ApprovisionnementServiceImpl<Approvisionnement> uncheckedTempVar = Mockito.mock(ApprovisionnementServiceImpl.class);
        this.mockApproService = uncheckedTempVar;
        this.mockMessageBuilder = Mockito.mock(MessageBuilder.class);
        this.builder.setMessageBuilder(this.mockMessageBuilder);
        this.mockProduitService = Mockito.mock(ProduitService.class);
        this.builder.setProduitService(this.mockProduitService);
        this.mockApproService.setProduitService(this.mockProduitService);

    }
    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.builder = null;
        this.mockApproService = null;
        this.mockMessageBuilder = null;
        this.mockProduitService = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.builder);
        Assert.assertNotNull(this.mockApproService);
        Assert.assertNotNull(this.mockMessageBuilder);
        Assert.assertNotNull(this.mockProduitService);
    }

    /**
     * Méthode en charge de tester la méthode de construction des alertes.
     */
    @Test
    public void testBuild() {

        final Object[] res = new Object[15];
        res[0] = 85;
        res[1] = 1;
        res[2] = 163;
        res[3] = 222;
        res[4] = "11F0522";
        res[5] = "00013";
        res[6] = "12_0204";
        res[7] = "POEMS";
        res[8] = "Lenalidomide REVLIMID";
        res[9] = "Pharmacie Hôtel-Dieu RDJ";
        res[10] = "Lenalidomide 5 mg; 21 gélules";
        res[11] = 30;
        res[12] = "2013-05-31 00:00:00";
        res[13] = "2013-05-01 00:00:00";
        res[14] = 1;

        final List<Object[]> results = new ArrayList<Object[]>();
        results.add(res);

        Mockito.when(this.mockProduitService.executeSQLQueryTabObject(AlerteProduitPeremptionBuilder.REQ_PROD_PEREMPTION.replace("{0}", "").replace("{1}", ""), null))
                .thenReturn(results);
        Mockito.when(this.mockMessageBuilder.getMessage("alerte.libDatePeremption",
                                                        new Object[]{"Lenalidomide REVLIMID", "Lenalidomide 5 mg; 21 gélules",
                                                                     StringUtils.defaultIfEmpty("11F0522", EclipseConstants.NON_APPLICABLE),
                                                                     StringUtils.defaultIfEmpty("00013", EclipseConstants.NON_APPLICABLE) }))
                .thenReturn("La date de péremption du produit Lenalidomide REVLIMID est atteinte. [Conditionnement : Lenalidomide 5 mg; 21 gélules / Numéro de lot : 11F0522 / Numéro de traitement : 00013]");

        final List<Alerte> alertes = new ArrayList<Alerte>();
        this.builder.build(new ArrayList<EssaiAlerte>(), new ArrayList<Pharmacie>(), alertes);

        Assert.assertEquals(1, alertes.size());
        final Alerte alerte = alertes.get(0);
        Assert.assertEquals("12_0204", alerte.getNumInterne());
        Assert.assertEquals("POEMS", alerte.getNom());
        Assert.assertEquals("Pharmacie Hôtel-Dieu RDJ", alerte.getNomPharmacie());
        Assert.assertEquals("La date de péremption du produit Lenalidomide REVLIMID est atteinte. [Conditionnement : Lenalidomide 5 mg; 21 gélules / Numéro de lot : 11F0522 / Numéro de traitement : 00013]",
                            alerte.getLibelle());

    }

}

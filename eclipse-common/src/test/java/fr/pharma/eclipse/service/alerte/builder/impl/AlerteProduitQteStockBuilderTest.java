package fr.pharma.eclipse.service.alerte.builder.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.essai.EssaiAlerte;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.utils.message.MessageBuilder;

/**
 * Classe en charge de tester le builder des alertes concernant les produits /
 * conditionnements dont la quantité en stock est inférieure à la quantité seuil
 * définie pour le produit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AlerteProduitQteStockBuilderTest {

    /**
     * AlerteProduitQteStockBuilder à tester.
     */
    private AlerteProduitQteStockBuilder builder;

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
        this.builder = new AlerteProduitQteStockBuilder();
        this.mockProduitService = Mockito.mock(ProduitService.class);
        this.builder.setProduitService(this.mockProduitService);
        this.mockMessageBuilder = Mockito.mock(MessageBuilder.class);
        this.builder.setMessageBuilder(this.mockMessageBuilder);

    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.builder = null;
        this.mockProduitService = null;
        this.mockMessageBuilder = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.builder);
        Assert.assertNotNull(this.mockProduitService);
        Assert.assertNotNull(this.mockMessageBuilder);
    }

    /**
     * Méthode en charge de tester la méthode de construction des alertes.
     */
    @Test
    public void testBuild() {

        final Object[] res = new Object[11];
        res[0] = 92;
        res[1] = 1;
        res[2] = 173;
        res[3] = 232;
        res[4] = "12_0250";
        res[5] = "NN8226-3613";
        res[6] = "NNC109-0012 ou placebo";
        res[7] = "Pharmacie Hôtel-Dieu RDJ";
        res[8] = "NNC109-0012 60 mg ou placebo cartouche";
        res[9] = 3;
        res[10] = 4;

        final List<Object[]> results = new ArrayList<Object[]>();
        results.add(res);

        Mockito.when(this.mockProduitService.executeSQLQueryTabObject(AlerteProduitQteStockBuilder.REQ_PROD_QTE_STOCK.replace("{0}", "").replace("{1}", ""), null))
                .thenReturn(results);
        Mockito.when(this.mockMessageBuilder.getMessage("alerte.libStockSeuil", new Object[]{"NNC109-0012 ou placebo", "NNC109-0012 60 mg ou placebo cartouche" }))
                .thenReturn("Le stock seuil pour le produit NNC109-0012 ou placebo est atteint. [Conditionnement : NNC109-0012 60 mg ou placebo cartouche]");

        final List<Alerte> alertes = new ArrayList<Alerte>();
        this.builder.build(new ArrayList<EssaiAlerte>(), new ArrayList<Pharmacie>(), alertes);

        Assert.assertEquals(1, alertes.size());
        final Alerte alerte = alertes.get(0);
        Assert.assertEquals("12_0250", alerte.getNumInterne());
        Assert.assertEquals("NN8226-3613", alerte.getNom());
        Assert.assertEquals("Pharmacie Hôtel-Dieu RDJ", alerte.getNomPharmacie());
        Assert.assertEquals("Le stock seuil pour le produit NNC109-0012 ou placebo est atteint. [Conditionnement : NNC109-0012 60 mg ou placebo cartouche]", alerte.getLibelle());
    }
}

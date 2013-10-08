package fr.pharma.eclipse.service.alerte.builder.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.dotation.Dotation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.EssaiAlerte;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.dotation.DotationService;
import fr.pharma.eclipse.utils.message.MessageBuilder;

/**
 * Classe en charge de tester le builder d'alertes pour les demandes de
 * dotations à traiter.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AlerteDdeDotationBuilderTest {
    /**
     * AlerteDdeDotationBuilder à tester.
     */
    private AlerteDdeDotationBuilder builder;

    /**
     * Service de gestion des dotations mocké.
     */
    private DotationService mockDotationService;

    /**
     * Builder de message mocké.
     */
    private MessageBuilder mockMessageBuilder;

    /**
     * Méthode en charge d'initialiser les données.
     */
    @Before
    public void init() {
        this.builder = new AlerteDdeDotationBuilder();
        this.mockDotationService = Mockito.mock(DotationService.class);
        this.builder.setDotationService(this.mockDotationService);
        this.mockMessageBuilder = Mockito.mock(MessageBuilder.class);
        this.builder.setMessageBuilder(this.mockMessageBuilder);

    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.builder = null;
        this.mockDotationService = null;
        this.mockMessageBuilder = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.builder);
        Assert.assertNotNull(this.mockDotationService);
        Assert.assertNotNull(this.mockMessageBuilder);
    }

    /**
     * Méthode en charge de tester la méthode de construction des alertes.
     */
    @Test
    public void testBuild() {
        final List<EssaiAlerte> essais = new ArrayList<EssaiAlerte>();
        final EssaiAlerte essai = new EssaiAlerte();
        essai.setId(1L);
        essais.add(essai);

        final List<Pharmacie> pharmacies = new ArrayList<Pharmacie>();
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        pharmacies.add(pharmacie);

        final Dotation dotation1 = new Dotation();
        dotation1.setService(this.getService());
        dotation1.setEssai(this.getEssai1());
        dotation1.setPharmacie(this.getPharmacie1());
        dotation1.setDateDemande(Calendar.getInstance());
        dotation1.setProduit(this.getProduit());

        final Dotation dotation2 = new Dotation();
        dotation2.setService(this.getService());
        dotation2.setEssai(this.getEssai2());
        dotation2.setPharmacie(this.getPharmacie2());
        dotation2.setDateDemande(Calendar.getInstance());
        dotation2.setProduit(this.getProduit());

        final List<Dotation> dotations = new ArrayList<Dotation>();
        dotations.add(dotation1);
        dotations.add(dotation2);

        Mockito.when(this.mockDotationService.getAll((SearchCriteria) Matchers.any())).thenReturn(dotations);

        final List<Alerte> alertes = new ArrayList<Alerte>();

        this.builder.build(essais, pharmacies, alertes);

        Assert.assertEquals(2, alertes.size());
    }

    /**
     * Méthode en charge de retourner l'essai 1.
     * @return Essai.
     */
    private Essai getEssai1() {
        final Essai essai1 = new Essai();
        essai1.setId(1L);
        return essai1;
    }

    /**
     * Méthode en charge de retourner l'essai 2.
     * @return Essai.
     */
    private Essai getEssai2() {
        final Essai essai2 = new Essai();
        essai2.setId(2L);
        return essai2;
    }

    /**
     * Méthode en charge de retourner la pharmacie 1.
     * @return Pharmacie.
     */
    private Pharmacie getPharmacie1() {
        final Pharmacie pharmacie1 = new Pharmacie();
        pharmacie1.setId(1L);
        return pharmacie1;
    }

    /**
     * Méthode en charge de retourner la pharmacie 2.
     * @return Pharmacie.
     */
    private Pharmacie getPharmacie2() {
        final Pharmacie pharmacie2 = new Pharmacie();
        pharmacie2.setId(2L);
        return pharmacie2;
    }

    /**
     * Méthode en charge de retourner un service.
     * @return Service.
     */
    private Service getService() {
        final Service serv = new Service();
        serv.setNom("nom");
        return serv;
    }

    /**
     * Méthode en charge de retourner un produit.
     * @return Produit.
     */
    private Produit getProduit() {
        final Produit produit = new Medicament();
        produit.setAlerteActive(Boolean.TRUE);
        return produit;
    }

}

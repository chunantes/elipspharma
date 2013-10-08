package fr.pharma.eclipse.domain.model.stock;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateMidnight;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.builder.ApprovisionnementBuilder;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.stock.ApprovisionnementService;
import fr.pharma.eclipse.service.stock.StockService;
import fr.pharma.eclipse.utils.ContextSecurityHelper;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester le mouvement d'approvisionnement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class E1_ApprovisionnementTest {

    /**
     * Service de gestion des approvisionnements.
     */
    @Resource(name = "approvisionnementService")
    private ApprovisionnementService<Approvisionnement> approvisionnementService;

    /**
     * Service de gestion des essais.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * Service de gestion de stock.
     */
    @Resource(name = "stockService")
    private StockService stockService;

    /**
     * Réception de lot 1.
     */
    ReceptionLot r1;

    /**
     * Réception de lot 2.
     */
    ReceptionLot r2;

    /**
     * Initialisation du jeu de tests.
     */
    @Before
    @Transactional
    public void initialize() {

        ContextSecurityHelper.createSecurityContextMock();

        // Données de test récupérées dans le fichier import.sql
        final Essai essai = this.essaiService.get(1L);
        final Pharmacie pharmacie = essai.getPharmaciePrincipale();
        final Medicament medicament = (Medicament) essai.getDetailProduit().getMedicaments().first();
        final Conditionnement conditionnement = medicament.getConditionnements().first();

        Approvisionnement a1 =
            new ApprovisionnementBuilder().withEssai(essai).withPharmacie(pharmacie).withType(TypeMvtStock.APPROVISIONNEMENT).withProduit(medicament)
                    .withConditionnement(conditionnement).withNumLot("AAAA").withDatePeremption(new DateMidnight(2013, 5, 24).toCalendar(EclipseConstants.LOCALE))
                    .withApproApprouve(Boolean.TRUE).withQuantite(10).withDateReception(new DateMidnight().toCalendar(EclipseConstants.LOCALE)).build();

        Approvisionnement a2 =
            new ApprovisionnementBuilder().withEssai(essai).withPharmacie(pharmacie).withType(TypeMvtStock.APPROVISIONNEMENT).withProduit(medicament)
                    .withConditionnement(conditionnement).withNumLot("BBBB").withDatePeremption(new DateMidnight(2013, 5, 25).toCalendar(EclipseConstants.LOCALE))
                    .withApproApprouve(Boolean.TRUE).withQuantite(5).withDateReception(new DateMidnight().toCalendar(EclipseConstants.LOCALE)).build();

        a1 = this.approvisionnementService.save(a1);
        a2 = this.approvisionnementService.save(a2);

        final List<Conditionnement> conditionnements = new ArrayList<Conditionnement>();
        conditionnements.add(conditionnement);
        this.r1 = new ReceptionLot();
        this.r1.setAppro(a1);
        this.r1.setConditionnements(conditionnements);

        this.r2 = new ReceptionLot();
        this.r2.setAppro(a2);
        this.r2.setConditionnements(conditionnements);
    }

    /**
     * Purge des données de test.
     */
    @After
    public void end() {
        this.approvisionnementService.remove(this.r1.getAppro());
        this.approvisionnementService.remove(this.r2.getAppro());
        this.r1 = null;
        this.r2 = null;
    }

    /**
     * Méthode en charge de tester un approvisionnement.
     */
    @Test
    public void testEntreeApppro() {

        final List<ReceptionLot> receptionLots = new ArrayList<ReceptionLot>();
        receptionLots.add(this.r1);
        receptionLots.add(this.r2);
        final ResultApprovisionnement resultApprovisionnement = this.approvisionnementService.save(receptionLots);
        Assert.assertNotNull(resultApprovisionnement);

        final Essai essai = this.r1.getAppro().getEssai();
        final Medicament medicament = (Medicament) essai.getDetailProduit().getMedicaments().first();
        final Conditionnement conditionnement = medicament.getConditionnements().first();
        final Pharmacie pharmacie = this.r1.getAppro().getPharmacie();

        // Vérification de ligneStock
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        criteria.setEssai(this.r1.getAppro().getEssai());
        criteria.setPharmacie(this.r1.getAppro().getPharmacie());
        criteria.setProduit(this.r1.getAppro().getProduit());
        criteria.setConditionnement(this.r1.getAppro().getConditionnement());

        // 2 lignes de stock attendues
        final List<LigneStock> result = this.stockService.getAll(criteria);
        Assert.assertEquals(2, result.size());

        final LigneStock l1 = result.get(0);
        Assert.assertEquals(essai, l1.getEssai());
        Assert.assertEquals(pharmacie, l1.getPharmacie());
        Assert.assertEquals(medicament, l1.getProduit());
        Assert.assertEquals(conditionnement, l1.getConditionnement());
        Assert.assertEquals(true, l1.getApproApprouve());
        Assert.assertEquals(new DateMidnight(2013, 5, 24), new DateMidnight(l1.getDatePeremption().getTime()));
        Assert.assertEquals("AAAA", l1.getNumLot());
        Assert.assertNull(l1.getNumTraitement());
        Assert.assertEquals(10, l1.getQteEnStock().intValue());
        Assert.assertEquals("Armoire A", l1.getStockage());

        final LigneStock l2 = result.get(1);
        Assert.assertEquals(essai, l2.getEssai());
        Assert.assertEquals(medicament, l2.getProduit());
        Assert.assertEquals(pharmacie, l2.getPharmacie());
        Assert.assertEquals(conditionnement, l2.getConditionnement());
        Assert.assertEquals(true, l2.getApproApprouve());
        Assert.assertEquals("BBBB", l2.getNumLot());
        Assert.assertEquals(new DateMidnight(2013, 5, 25), new DateMidnight(l2.getDatePeremption().getTime()));
        Assert.assertNull(l2.getNumTraitement());
        Assert.assertEquals(5, l2.getQteEnStock().intValue());
        Assert.assertEquals("Armoire A", l2.getStockage());
    }

}

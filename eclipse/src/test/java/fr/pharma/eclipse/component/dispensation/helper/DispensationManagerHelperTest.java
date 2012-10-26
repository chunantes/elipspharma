package fr.pharma.eclipse.component.dispensation.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.component.stock.SortieManager;
import fr.pharma.eclipse.domain.enums.TypeDispensation;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.DetailDonneesPharma;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.Sortie;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stock.StockService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du helper DispensationManagerHelper.
 
 * @version $Revision$ $Date$
 */
public class DispensationManagerHelperTest
    extends AbstractEclipseJUnitTest
{

    /**
     * Helper.
     */
    private DispensationManagerHelper helper;

    /**
     * Finder.
     */
    private LigneStockFinder finder;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp()
    {
        this.finder = Mockito.mock(LigneStockFinder.class);
        this.helper = new DispensationManagerHelper();
        final Map<TypeDispensation, LigneStockFinder> strategy =
            new HashMap<TypeDispensation, LigneStockFinder>();
        strategy.put(TypeDispensation.GLOBALE,
                     this.finder);
        this.helper.setStrategy(strategy);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown()
    {
        this.helper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.helper);
    }

    /**
     * Test de la méthode initConditionnements.
     */
    @Test
    public void testInitConditionnementsMulti()
    {
        final Produit produit = new Medicament();
        final Conditionnement c1 = new Conditionnement();
        c1.setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        c1.setId(1L);

        final Conditionnement c2 = new Conditionnement();
        c2.setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        c2.setId(2L);

        final ProduitPrescrit produitPrescrit = new ProduitPrescrit();
        final Conditionnement c = Mockito.mock(Conditionnement.class);
        Mockito.when(c.getModePrescription())
                .thenReturn(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        produitPrescrit.setConditionnement(c);
        produitPrescrit.setNumTraitement("num");
        produitPrescrit.setProduit(produit);
        final Prescription prescription = Mockito.mock(Prescription.class);
        final Essai essai = Mockito.mock(Essai.class);
        final DetailDonneesPharma detail = new DetailDonneesPharma();
        detail.getInfosDispensations().setTypeDispensation(TypeDispensation.GLOBALE);
        Mockito.when(essai.getDetailDonneesPharma()).thenReturn(detail);
        Mockito.when(prescription.getEssai()).thenReturn(essai);
        produitPrescrit.setPrescription(prescription);

        final ProduitService<Produit> produitService = Mockito.mock(ProduitService.class);

        final Sortie sortie = new Sortie();
        final DispensationProduit dispensationProduit = Mockito.mock(DispensationProduit.class);
        sortie.setMvtSortie(dispensationProduit);
        final SortieManager sortieManager = Mockito.mock(SortieManager.class);
        Mockito.when(sortieManager.getSortieCurrent()).thenReturn(sortie);
        Mockito.when(sortieManager.getProduitService()).thenReturn(produitService);
        Mockito.when(produitService.reattach(Matchers.any(Produit.class))).thenReturn(produit);

        this.helper.initConditionnements(sortieManager,
                                         produitPrescrit);
        Mockito.verify(this.finder).initLignesStocks(sortieManager);
        Mockito.verify(dispensationProduit).setConditionnement(c);
    }

    /**
     * Test de la méthode initConditionnements.
     */
    @Test
    public void testInitConditionnements()
    {
        final Produit produit = new Medicament();
        final Conditionnement c1 = new Conditionnement();
        c1.setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        c1.setId(1L);

        final Conditionnement c2 = new Conditionnement();
        c2.setModePrescription(ModePrescription.DOSE);
        c2.setId(2L);
        produit.getConditionnements().add(c1);
        produit.getConditionnements().add(c2);

        final ProduitPrescrit produitPrescrit = new ProduitPrescrit();
        final Conditionnement c = Mockito.mock(Conditionnement.class);
        Mockito.when(c.getModePrescription())
                .thenReturn(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        produitPrescrit.setConditionnement(c);
        produitPrescrit.setNumTraitement("num");
        produitPrescrit.setProduit(produit);

        final ProduitService<Produit> produitService = Mockito.mock(ProduitService.class);

        final Prescription prescription = Mockito.mock(Prescription.class);
        final Essai essai = Mockito.mock(Essai.class);
        final DetailDonneesPharma detail = new DetailDonneesPharma();
        detail.getInfosDispensations().setTypeDispensation(TypeDispensation.GLOBALE);
        Mockito.when(essai.getDetailDonneesPharma()).thenReturn(detail);
        Mockito.when(prescription.getEssai()).thenReturn(essai);
        produitPrescrit.setPrescription(prescription);
        final Sortie sortie = new Sortie();
        final DispensationProduit dispensationProduit = Mockito.mock(DispensationProduit.class);
        sortie.setMvtSortie(dispensationProduit);
        final SortieManager sortieManager = Mockito.mock(SortieManager.class);
        Mockito.when(sortieManager.getSortieCurrent()).thenReturn(sortie);
        Mockito.when(sortieManager.getProduitService()).thenReturn(produitService);
        Mockito.when(produitService.reattach(Matchers.any(Produit.class))).thenReturn(produit);
        final StockService stockService = Mockito.mock(StockService.class);
        Mockito.when(sortieManager.getStockService()).thenReturn(stockService);

        Mockito.when(stockService.getLinesStock(Matchers.any(Essai.class),
                                                Matchers.any(Pharmacie.class),
                                                Matchers.any(Produit.class),
                                                Matchers.any(Conditionnement.class),
                                                Matchers.anyBoolean()))
                .thenReturn(new ArrayList<LigneStock>());

        this.helper.initConditionnements(sortieManager,
                                         produitPrescrit);

        Mockito.verify(this.finder).initLignesStocks(sortieManager);
        Mockito.verify(dispensationProduit).setConditionnement(c);

    }

    /**
     * Test de la méthode initProduitsDispensesForConsult.
     */
    @Test
    public void testInitProduitsDispensesForConsult()
    {

        final DispensationProduit d1 = new DispensationProduit();
        d1.setId(1L);
        d1.setNumLot("1");
        final DispensationProduit d2 = new DispensationProduit();
        d2.setId(2L);
        d2.setNumLot("2");
        final DispensationProduit d3 = new DispensationProduit();
        d3.setId(3L);
        d3.setNumLot("3");

        final ProduitPrescrit p1 = new ProduitPrescrit();
        p1.setId(1L);
        final ProduitPrescrit p2 = new ProduitPrescrit();
        p2.setId(2L);

        d1.setProduitPrescrit(p1);
        d2.setProduitPrescrit(p2);
        d3.setProduitPrescrit(p2);

        final Conditionnement c1 = new Conditionnement();
        c1.setId(1L);
        final Conditionnement c2 = new Conditionnement();
        c2.setId(2L);
        d1.setConditionnement(c1);
        d2.setConditionnement(c2);
        d3.setConditionnement(c2);

        final Dispensation dispensation = new Dispensation();
        dispensation.getDispensationsProduit().add(d1);
        dispensation.getDispensationsProduit().add(d2);
        dispensation.getDispensationsProduit().add(d3);

        final Map<Long, List<Sortie>> map = new HashMap<Long, List<Sortie>>();

        this.helper.initProduitsDispensesForConsult(dispensation,
                                                    map);

        Assert.assertEquals(2,
                            map.size());
        Assert.assertEquals(1,
                            map.get(p1.getId()).size());
        Assert.assertEquals(1,
                            map.get(p2.getId()).size());
        Assert.assertEquals(2,
                            map.get(p2.getId()).get(0).getLignesStock().size());
    }
}

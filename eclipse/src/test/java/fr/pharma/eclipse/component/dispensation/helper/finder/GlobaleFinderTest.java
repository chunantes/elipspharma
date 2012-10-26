package fr.pharma.eclipse.component.dispensation.helper.finder;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.component.stock.SortieManager;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.DispensationGlobale;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.Sortie;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.stock.MvtStockService;
import fr.pharma.eclipse.service.stock.StockService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test GlobaleFinder.
 
 * @version $Revision$ $Date$
 */
public class GlobaleFinderTest
    extends AbstractEclipseJUnitTest
{

    /**
     * Finder.
     */
    private GlobaleFinder finder;

    /**
     * Service.
     */
    private MvtStockService<DispensationGlobale> mvtService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp()
    {
        this.finder = new GlobaleFinder();
        this.mvtService = Mockito.mock(MvtStockService.class);
        this.finder.setMvtStockService(this.mvtService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown()
    {
        this.finder = null;
        this.mvtService = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit()
    {
        Assert.assertNotNull(this.finder);
        Assert.assertNotNull(this.mvtService);
    }

    /**
     * Test de la méthode initLignesStocks.
     */
    @Test
    public void testInitLignesStocks()
    {
        final SortieManager sortieManager = new SortieManager();
        final Sortie sortieCurrent = new Sortie();
        sortieCurrent.setMvtSortie(new DispensationGlobale());
        sortieManager.setSortieCurrent(sortieCurrent);
        sortieCurrent.setLignesStock(new ArrayList<LigneStock>());
        final StockService stockService = Mockito.mock(StockService.class);

        sortieManager.setStockService(stockService);

        final Produit produit = new Medicament();
        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);

        final List<DispensationGlobale> dispensations = new ArrayList<DispensationGlobale>();
        final DispensationGlobale d = new DispensationGlobale();
        d.setNumLot("numLot");
        d.setNumTraitement("numTraitement");
        d.setQuantite(2);
        d.setQuantiteDispensee(1);
        d.setPharmacie(new Pharmacie());
        d.setEssai(new Essai());
        d.setProduit(produit);
        d.setConditionnement(conditionnement);
        dispensations.add(d);

        Mockito.when(this.mvtService.getAll(Matchers.any(SearchCriteria.class)))
                .thenReturn(dispensations);

        this.finder.initLignesStocks(sortieManager);

        Assert.assertEquals("numLot",
                            sortieManager.getSortieCurrent().getLignesStock().get(0).getNumLot());
        Assert.assertEquals("numTraitement",
                            sortieManager
                                    .getSortieCurrent()
                                    .getLignesStock()
                                    .get(0)
                                    .getNumTraitement());
        Assert.assertEquals(new Integer(1),
                            sortieManager
                                    .getSortieCurrent()
                                    .getLignesStock()
                                    .get(0)
                                    .getQteEnStock());

    }
}

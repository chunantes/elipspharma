package fr.pharma.eclipse.component.dispensation.helper.finder;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.component.stock.SortieManager;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.DispensationGlobale;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stock.Sortie;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.stock.StockService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test NominativeFinder.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class NominativeFinderTest extends AbstractEclipseJUnitTest {

    /**
     * Finder.
     */
    private NominativeFinder finder;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.finder = new NominativeFinder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.finder = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.finder);
    }

    /**
     * Test de la méthode initLignesStocks.
     */
    @Test
    public void testInitLignesStocks() {
        final SortieManager sortieManager = new SortieManager();
        final Sortie sortieCurrent = new Sortie();
        sortieCurrent.setMvtSortie(new DispensationGlobale());
        sortieManager.setSortieCurrent(sortieCurrent);

        sortieCurrent.setLignesStock(new ArrayList<LigneStock>());
        final StockService stockService = Mockito.mock(StockService.class);
        final MvtStock mvtSortie = new DispensationGlobale();
        mvtSortie.setEssai(new Essai());
        sortieCurrent.setMvtSortie(mvtSortie);

        sortieManager.setStockService(stockService);

        final Conditionnement conditionnement = new Conditionnement();
        conditionnement.setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);

        mvtSortie.setConditionnement(conditionnement);
        final Medicament medicament = new Medicament();
        medicament.getConditionnements().add(conditionnement);
        mvtSortie.setProduit(medicament);

        final List<LigneStock> liste = new ArrayList<LigneStock>();

        Mockito.when(stockService.getAllLignesStock(Matchers.any(Essai.class), Matchers.any(Pharmacie.class), Matchers.any(Produit.class), Matchers.any(Conditionnement.class),
                                                Matchers.anyBoolean())).thenReturn(liste);

        this.finder.initLignesStocks(sortieManager);

        Mockito.verify(stockService, Mockito.times(1)).getAllLignesStock(Matchers.any(Essai.class), Matchers.any(Pharmacie.class), Matchers.any(Produit.class),
                                                                     Matchers.any(Conditionnement.class), Matchers.anyBoolean());

    }
}

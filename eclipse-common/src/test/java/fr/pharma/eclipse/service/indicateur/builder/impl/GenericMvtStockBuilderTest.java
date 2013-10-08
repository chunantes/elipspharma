package fr.pharma.eclipse.service.indicateur.builder.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.stock.MvtStockService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe MvtStockBuilder.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GenericMvtStockBuilderTest extends AbstractEclipseJUnitTest {

    /**
     * Builder.
     */
    private GenericMvtStockBuilder builder;

    /**
     * Service MvtStock.
     */
    private MvtStockService service;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.service = Mockito.mock(MvtStockService.class);
        this.builder = new GenericMvtStockBuilder();
        this.builder.setMvtService(this.service);
        this.builder.setLibelle("Libelle");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.service = null;
        this.builder = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.builder);
        Assert.assertNotNull(this.service);
    }

    /**
     * Test de la méthode build.
     */
    @Test
    public void testBuild() {
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        final Calendar dateDebut = Calendar.getInstance();
        final Calendar dateFin = Calendar.getInstance();

        final List<MvtStock> liste = new ArrayList<MvtStock>();
        liste.add(new DispensationProduit());

        Mockito.when(this.service.getAll(Matchers.any(SearchCriteria.class))).thenReturn(liste);

        final Indicateur indicateur = this.builder.build(pharmacie, dateDebut, dateFin);

        Assert.assertEquals("Libelle", indicateur.getLibelle());
        Assert.assertEquals(new BigDecimal(1), indicateur.getValeur());
    }

}

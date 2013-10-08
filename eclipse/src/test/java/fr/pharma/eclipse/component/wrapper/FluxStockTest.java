package fr.pharma.eclipse.component.wrapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe FluxStock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class FluxStockTest extends AbstractEclipseJUnitTest {
    /**
     * FluxStock.
     */
    private FluxStock fluxStock;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.fluxStock = new FluxStock();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.fluxStock = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.fluxStock);
    }

    /**
     * Test numlot.
     */
    @Test
    public void testGetSetNumLot() {
        this.fluxStock.setNumLot("dfg");
        Assert.assertEquals("dfg", this.fluxStock.getNumLot());
    }

    /**
     * Test date.
     */
    @Test
    public void testGetSetDate() {
        final Calendar date = Calendar.getInstance();
        this.fluxStock.setDate(date);
        Assert.assertEquals(date, this.fluxStock.getDate());
    }

    /**
     * Test essai.
     */
    @Test
    public void testGetSetEssai() {

        final Essai essai = Mockito.mock(Essai.class);
        this.fluxStock.setEssai(essai);
        Assert.assertEquals(essai, this.fluxStock.getEssai());
    }

    /**
     * Test mvt.
     */
    @Test
    public void testGetSetMvt() {

        final List<MvtStock> mvts = new ArrayList<MvtStock>();
        this.fluxStock.setMvts(mvts);
        Assert.assertEquals(mvts, this.fluxStock.getMvts());
    }

    /**
     * Test personne.
     */
    @Test
    public void testGetSetPersonne() {

        final Personne personne = Mockito.mock(Personne.class);
        this.fluxStock.setPersonne(personne);
        Assert.assertEquals(personne, this.fluxStock.getPersonne());
    }

    /**
     * Test pharmacie.
     */
    @Test
    public void testGetSetPharmacie() {

        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        this.fluxStock.setPharmacie(pharmacie);
        Assert.assertEquals(pharmacie, this.fluxStock.getPharmacie());
    }

    /**
     * Test produit.
     */
    @Test
    public void testGetSetProduit() {

        final Produit produit = Mockito.mock(Produit.class);
        this.fluxStock.setProduit(produit);
        Assert.assertEquals(produit, this.fluxStock.getProduit());
    }

    /**
     * Test produit.
     */
    @Test
    public void testGetSetQuantite() {

        this.fluxStock.setQuantite(5);
        Assert.assertEquals(new Integer(5), this.fluxStock.getQuantite());
    }

    /**
     * Test produit.
     */
    @Test
    public void testGetSetType() {

        this.fluxStock.setType(TypeMvtStock.APPROVISIONNEMENT);
        Assert.assertEquals(TypeMvtStock.APPROVISIONNEMENT, this.fluxStock.getType());
    }
}

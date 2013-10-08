package fr.pharma.eclipse.service.essai.updator.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.produit.NatureProduit;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.produit.DetailProduit;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.service.essai.EssaiService;

/**
 * Test de la classe DciBeforeSaveUpdator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DciProduitEvalueBeforeSaveUpdatorTest {
    /**
     * Classe testée.
     */
    private DciProduitEvalueBeforeSaveUpdator updator;

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void setUp() {
        this.updator = new DciProduitEvalueBeforeSaveUpdator();
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown() {
        this.updator = null;
    }

    /**
     * Test de la méthode update : aucun médicament liés à l'essai.
     */
    @Test
    public void updateWhenNoMedicament() {
        final Essai essai = this.getEssai();
        essai.getDetailProduit().getMedicaments().clear();
    }
    /**
     * Test de la méthode update : avec un médicament avec un dci.
     */
    @Test
    public void updateWhenDciOk() {
        final Essai essai = this.getEssai();
        ((Medicament) essai.getDetailProduit().getMedicaments().first()).setDci("dci");
        ((Medicament) essai.getDetailProduit().getMedicaments().first()).setDenomination("denomination");
        this.updator.update(essai, Mockito.mock(EssaiService.class));
        Assert.assertEquals("dci", essai.getDci());
    }

    /**
     * Test de la méthode update : avec un médicament sans dci.
     */
    @Test
    public void updateWhenDciNull() {
        final Essai essai = this.getEssai();
        ((Medicament) essai.getDetailProduit().getMedicaments().first()).setDci(null);
        this.updator.update(essai, Mockito.mock(EssaiService.class));
        Assert.assertEquals("n/a", essai.getDci());
    }

    /**
     * Test de la méthode update : avec un médicament avec un dci à vide.
     */
    @Test
    public void updateWhenDciEmpty() {
        final Essai essai = this.getEssai();
        ((Medicament) essai.getDetailProduit().getMedicaments().first()).setDci("");
        this.updator.update(essai, Mockito.mock(EssaiService.class));
        Assert.assertEquals("n/a", essai.getDci());
    }

    /**
     * Test de la méthode update : avec un médicament avec un dci avec un
     * esapce.
     */
    @Test
    public void updateWhenDciBlank() {
        final Essai essai = this.getEssai();
        ((Medicament) essai.getDetailProduit().getMedicaments().first()).setDci(" ");
        this.updator.update(essai, Mockito.mock(EssaiService.class));
        Assert.assertEquals("n/a", essai.getDci());
    }

    private Essai getEssai() {

        final Essai e = new Essai();
        e.setDci("n/a");
        e.setDetailProduit(new DetailProduit());
        final Medicament m1 = new Medicament();
        m1.setNature(NatureProduit.PRODUIT_EVALUE);
        final Medicament m2 = new Medicament();
        m2.setNature(NatureProduit.PRODUIT_COMPARATEUR);
        e.getDetailProduit().getMedicaments().add(m1);
        e.getDetailProduit().getMedicaments().add(m2);
        return e;
    }
}

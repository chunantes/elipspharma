package fr.pharma.eclipse.factory.stock;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Test de DispensationProduitFactory.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationProduitFactoryTest {
    /**
     * Fabrique testée.
     */
    private DispensationProduitFactory factory;

    /**
     * Factory Spring mockée.
     */
    private BeanFactory mockedBeanFactory;

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void init() {
        this.mockedBeanFactory = Mockito.mock(BeanFactory.class);
        this.factory = new DispensationProduitFactory(DispensationProduit.class);
        this.factory.setBeanFactory(this.mockedBeanFactory);
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void end() {
        this.factory = null;
        this.mockedBeanFactory = null;
    }

    /**
     * Test de la méthode getInitializedObject.
     */
    @Test
    public void testGetInitializedObject() {
        final DispensationProduit dispensation = new DispensationProduit();
        final ProduitPrescrit prod = new ProduitPrescrit();
        prod.setNumTraitement("");
        prod.setProduit(new Medicament());
        prod.setPrescription(new Prescription());
        prod.getPrescription().setInclusion(new Inclusion());
        final Dispensation d = new Dispensation();
        final Essai essai = new Essai();
        essai.setPharmaciePrincipale(new Pharmacie());
        prod.getPrescription().getInclusion().setEssai(essai);
        Mockito.when(this.mockedBeanFactory.getBean(DispensationProduit.class.getSimpleName())).thenReturn(dispensation);

        final DispensationProduit created = this.factory.getInitializedObject(prod, d);
        Mockito.verify(this.mockedBeanFactory).getBean(DispensationProduit.class.getSimpleName());
        Assert.assertSame(essai.getPharmaciePrincipale(), created.getPharmacie());
        Assert.assertSame(prod, created.getProduitPrescrit());
        Assert.assertSame(prod.getProduit(), created.getProduit());
        Assert.assertEquals(TypeMvtStock.DISPENSATION, created.getType());
        Assert.assertEquals(prod.getNumTraitement(), created.getNumTraitement());
    }
}

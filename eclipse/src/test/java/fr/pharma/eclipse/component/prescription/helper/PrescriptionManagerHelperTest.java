package fr.pharma.eclipse.component.prescription.helper;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.faces.model.DataModel;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.comparator.common.EclipseListComparator;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.factory.prescription.ProduitPrescritFactory;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du helper PrescriptionManagerHelper.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PrescriptionManagerHelperTest extends AbstractEclipseJUnitTest {
    /**
     * Helper.
     */
    private PrescriptionManagerHelper helper;

    /**
     * Factory mockée.
     */
    private ProduitPrescritFactory factory;

    /**
     * Service Produit.
     */
    private GenericService<Produit> produitService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.produitService = Mockito.mock(GenericService.class);
        this.factory = Mockito.mock(ProduitPrescritFactory.class);
        this.helper = new PrescriptionManagerHelper();
        this.helper.setProduitService(this.produitService);
        this.helper.setFactory(this.factory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.factory = null;
        this.helper = null;
        this.produitService = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.produitService);
        Assert.assertNotNull(this.factory);
        Assert.assertNotNull(this.helper);
    }

    /**
     * Test de la méthode initProduitsPrescrits.
     */
    @Test
    public void testInitProduitsPrescrits() {
        final Sequence sequence = new Sequence();
        final Conditionnement c = new Conditionnement();
        final PrescriptionType p1 = new PrescriptionType();
        p1.setId(1L);
        p1.setConditionnement(c);
        final PrescriptionType p2 = new PrescriptionType();
        p2.setId(2L);
        p2.setConditionnement(c);
        final Produit med = new Medicament();
        med.setDenomination("mo");
        p1.setProduit(med);
        p2.setProduit(med);
        sequence.getPrescriptions().add(p1);
        sequence.getPrescriptions().add(p2);

        final Prescription p = new Prescription();
        p.setSequence(sequence);
        final ProduitPrescrit pp1 = new ProduitPrescrit();
        pp1.setId(1L);
        final Conditionnement c1 = new Conditionnement();
        c1.setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        pp1.setConditionnement(c1);
        final ProduitPrescrit pp2 = new ProduitPrescrit();
        pp2.setId(2L);
        final Conditionnement c2 = new Conditionnement();
        c2.setModePrescription(ModePrescription.DOSE_KG);
        pp2.setConditionnement(c2);
        pp1.setProduit(med);
        pp2.setProduit(med);

        Mockito.when(this.factory.getInitializedObject(Matchers.any(PrescriptionType.class), Matchers.any(Prescription.class))).thenReturn(pp1).thenReturn(pp2);

        this.helper.initProduitsPrescrits(p);

        Mockito.verify(this.factory, Mockito.atLeast(2)).getInitializedObject(Matchers.any(PrescriptionType.class), Matchers.any(Prescription.class));

        Assert.assertEquals(2, p.getProduitsPrescrits().size());
    }

    /**
     * Test de la méthode getModesPrescription.
     */
    @Test
    public void testGetModesPrescriptionNull() {
        final DataModel<Conditionnement> result = this.helper.getConditionnements(null);
        Assert.assertEquals(0, result.getRowCount());

    }

    /**
     * Test de la méthode getModesPrescription.
     */
    @Test
    public void testGetModesPrescriptionNotNull() {

        final Produit p = Mockito.mock(Produit.class);
        Mockito.when(this.produitService.reattach(Matchers.any(Produit.class))).thenReturn(p);
        Mockito.when(p.getConditionnements()).thenReturn(new TreeSet<Conditionnement>());
        final DataModel<Conditionnement> result = this.helper.getConditionnements(p);
        Assert.assertEquals(0, result.getRowCount());

    }

    /**
     * Test de la méthode getModesPrescription.
     */
    @Test
    public void testGetModesPrescriptionNotNullWithConditionnements() {

        final Produit p = Mockito.mock(Produit.class);
        final SortedSet<Conditionnement> conditionnements = new TreeSet<Conditionnement>(new EclipseListComparator());
        final Conditionnement c = new Conditionnement();
        conditionnements.add(c);
        c.setModePrescription(ModePrescription.DOSE);
        final Conditionnement c2 = new Conditionnement();
        c2.setId(2L);
        conditionnements.add(c2);
        c2.setModePrescription(ModePrescription.DOSE);

        Mockito.when(this.produitService.reattach(Matchers.any(Produit.class))).thenReturn(p);
        Mockito.when(p.getConditionnements()).thenReturn(conditionnements);
        final DataModel<Conditionnement> result = this.helper.getConditionnements(p);
        Assert.assertEquals(2, result.getRowCount());

    }
}

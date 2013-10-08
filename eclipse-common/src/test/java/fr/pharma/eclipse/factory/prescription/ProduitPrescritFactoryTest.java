package fr.pharma.eclipse.factory.prescription;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.enums.design.TypeRegularite;
import fr.pharma.eclipse.domain.enums.design.UniteTemps;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.domain.model.design.embedded.Frequence;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la factory ProduitPrescritFactory.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ProduitPrescritFactoryTest extends AbstractEclipseJUnitTest {

    /**
     * PrescriptionFactory.
     */
    private ProduitPrescritFactory factory;

    /**
     * Factory Spring mockée.
     */
    private BeanFactory mockedBeanFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedBeanFactory = Mockito.mock(BeanFactory.class);
        this.factory = new ProduitPrescritFactory(ProduitPrescrit.class);
        this.factory.setBeanFactory(this.mockedBeanFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.factory = null;
        this.mockedBeanFactory = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.factory);
        Assert.assertNotNull(this.mockedBeanFactory);
    }

    /**
     * Test de la méthode getInitializedObject().
     */
    @Test
    public void testGetInitializedObject() {
        Mockito.when(this.mockedBeanFactory.getBean(ProduitPrescrit.class.getSimpleName())).thenReturn(new ProduitPrescrit());
        final PrescriptionType type = new PrescriptionType();
        final TempsPrescription t = new TempsPrescription();
        t.setNb(5);
        t.setUnite(UniteTemps.JOUR);
        final Frequence frequence = new Frequence();
        frequence.setNbFrequence(5);
        frequence.setNbUniteTempsFrequence(5);
        frequence.setTypeRegularite(TypeRegularite.PAR);
        frequence.setUniteFrequence(UniteTemps.MOIS);
        type.setDebut(t);
        type.setDuree(t);
        type.setDescription("description");
        type.setFrequence(frequence);

        final Conditionnement c = new Conditionnement();
        c.setDosage(new BigDecimal(5));
        c.setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        type.setConditionnement(c);
        type.setNbUniteDosage(new BigDecimal(5));
        final Medicament m = new Medicament();
        m.setId(1L);
        type.setProduit(m);

        final Prescription prescription = new Prescription();
        prescription.setId(1L);

        final ProduitPrescrit result = this.factory.getInitializedObject(type, prescription);
        Assert.assertNotNull(result);
        Assert.assertEquals(type.getConditionnement().getUniteDosage(), result.getConditionnement().getUniteDosage());
        Assert.assertEquals(type.getDescription(), result.getDescription());
        Assert.assertEquals(type.getDebut().getNb(), result.getDebut().getNb());
        Assert.assertEquals(type.getDebut().getUnite(), result.getDebut().getUnite());
        Assert.assertEquals(type.getDuree().getNb(), result.getDuree().getNb());
        Assert.assertEquals(type.getDuree().getUnite(), result.getDuree().getUnite());
        Assert.assertEquals(type.getFrequence().getNbFrequence(), result.getFrequence().getNbFrequence());
        Assert.assertEquals(type.getFrequence().getNbUniteTempsFrequence(), result.getFrequence().getNbUniteTempsFrequence());
        Assert.assertEquals(type.getFrequence().getTypeRegularite(), result.getFrequence().getTypeRegularite());
        Assert.assertEquals(type.getFrequence().getUniteFrequence(), result.getFrequence().getUniteFrequence());
        Assert.assertEquals(type.getConditionnement().getModePrescription(), result.getConditionnement().getModePrescription());
        Assert.assertEquals(type.getConditionnement().getDosage(), result.getConditionnement().getDosage());
        Assert.assertEquals(type.getNbUniteDosage(), result.getNbUniteDosage());
        Assert.assertEquals(type.getProduit(), result.getProduit());
        Assert.assertEquals(prescription, result.getPrescription());

    }
}

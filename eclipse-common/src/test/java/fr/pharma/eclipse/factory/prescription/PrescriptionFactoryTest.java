package fr.pharma.eclipse.factory.prescription;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la factory PrescriptionFactory.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PrescriptionFactoryTest extends AbstractEclipseJUnitTest {

    /**
     * PrescriptionFactory.
     */
    private PrescriptionFactory factory;

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
        this.factory = new PrescriptionFactory(Prescription.class);
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
        Mockito.when(this.mockedBeanFactory.getBean(Prescription.class.getSimpleName())).thenReturn(new Prescription());
        final Essai essai = new Essai();
        essai.setId(1L);
        final Patient patient = new Patient();
        patient.setId(1L);
        final Inclusion inclusion = new Inclusion();
        inclusion.setPatient(patient);
        inclusion.setEssai(essai);
        final Prescription h = this.factory.getInitializedObject(inclusion);
        Assert.assertNotNull(h);
        Assert.assertEquals(essai.getId(), h.getInclusion().getEssai().getId());
        Assert.assertEquals(patient.getId(), h.getInclusion().getPatient().getId());
        Assert.assertNotNull(h.getDatePrescription());

    }

    /**
     * Test de la méthode getInitializedObject().
     */
    @Test
    public void testGetInitializedObjectWithPrescription() {
        Mockito.when(this.mockedBeanFactory.getBean(Prescription.class.getSimpleName())).thenReturn(new Prescription());
        final Essai essai = new Essai();
        essai.setId(1L);
        final Patient patient = new Patient();
        patient.setId(1L);
        final Inclusion inclusion = new Inclusion();
        inclusion.setPatient(patient);
        inclusion.setEssai(essai);
        final Prescription p = new Prescription();
        p.setDateDebutTraitement(Calendar.getInstance());
        p.setDatePrescription(Calendar.getInstance());
        p.getDatePrescription().add(Calendar.DAY_OF_YEAR, -5);
        p.setInclusion(inclusion);
        p.setInvestigateur(new Investigateur());
        p.setNumPrescription(4);
        p.setSequence(new Sequence());
        p.setService(new Service());

        final Prescription result = this.factory.getInitializedObject(p);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getDateDebutTraitement(), p.getDateDebutTraitement());
        Assert.assertEquals(1, result.getDatePrescription().compareTo(p.getDatePrescription()));
        Assert.assertSame(p.getInclusion(), result.getInclusion());
        Assert.assertSame(p.getInvestigateur(), result.getInvestigateur());
        Assert.assertSame(p.getService(), result.getService());
        Assert.assertEquals(new Integer(5), result.getNumPrescription());
        Assert.assertEquals(p.getProduitsPrescrits().size(), result.getProduitsPrescrits().size());
        Assert.assertSame(p.getSequence(), result.getSequence());

    }
}

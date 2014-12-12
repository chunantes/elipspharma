package fr.pharma.eclipse.component.design.validator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.design.UniteTemps;
import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.service.helper.design.TimeHelper;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.FacesUtils;

/**
 * Test de SequenceValidator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class SequenceValidatorTest extends AbstractEclipseJUnitTest {

    private SequenceValidator validator;
    private TimeHelper helper;
    private FacesUtils facesUtils;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.facesUtils = Mockito.mock(FacesUtils.class);
        this.helper = Mockito.mock(TimeHelper.class);
        this.validator = new SequenceValidator();
        this.validator.setTimeHelper(this.helper);
        this.validator.setFacesUtils(this.facesUtils);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.facesUtils = null;
        this.validator = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.facesUtils);
        Assert.assertNotNull(this.validator);
    }

    /**
     * Test de validateSequence.
     */
    @Test
    public void testValidateSequenceFalse() {
    	// Prepare
        final TempsPrescription t = new TempsPrescription();
        t.setNb(1);
        t.setUnite(UniteTemps.JOUR);

        final Bras bras = new Bras();
        bras.setNom("nomBras");
        final TempsPrescription t1 = new TempsPrescription();
        t1.setNb(2);
        t1.setUnite(UniteTemps.JOUR);
        final PrescriptionType prescription = new PrescriptionType();
        prescription.setDebut(t);
        final Sequence sequence = new Sequence();
        sequence.setDebut(t);
        sequence.setNom("seq1");
        sequence.setId(2L);
        final Sequence sequence2 = new Sequence();
        sequence2.setDebut(t);
        sequence2.setNom("seq2");
        final Sequence sequence3 = new Sequence();
        sequence3.setDebut(t);
        sequence3.setNom("seq3");
        sequence3.setId(1L);

        sequence.setParent(bras);
        sequence2.setParent(bras);
        sequence3.setParent(bras);
        sequence3.setFin(t1);

        bras.getSequences().add(sequence);
        bras.getSequences().add(sequence2);
        bras.getSequences().add(sequence3);
        Mockito.when(this.helper.getFin(Matchers.anyCollection())).thenReturn(t1);
        
        FacesContextMock.mockFacesContext();
                
        // Test & Verify
        Assert.assertFalse(this.validator.validateSequence(prescription, sequence));
        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR, "sequence.chevauchement");
    }

    /**
     * Test de validateSequence.
     */
    @Test
    public void testValidateSequenceTrue() {
        final TempsPrescription t = new TempsPrescription();
        t.setNb(1);
        t.setUnite(UniteTemps.JOUR);

        final Bras bras = new Bras();
        bras.setNom("nomBras");
        final TempsPrescription t1 = new TempsPrescription();
        t1.setNb(2);
        t1.setUnite(UniteTemps.JOUR);
        final PrescriptionType prescription = new PrescriptionType();
        prescription.setDebut(t);
        final Sequence sequence = new Sequence();
        sequence.setDebut(t);
        sequence.setNom("seq1");
        sequence.setId(2L);
        final Sequence sequence2 = new Sequence();
        sequence2.setDebut(t);
        sequence2.setNom("seq2");
        final Sequence sequence3 = new Sequence();
        sequence3.setDebut(t);
        sequence3.setNom("seq3");
        sequence3.setId(1L);

        sequence3.setFin(t);
        sequence.setParent(bras);
        sequence2.setParent(bras);
        sequence3.setParent(bras);

        bras.getSequences().add(sequence);
        bras.getSequences().add(sequence2);
        bras.getSequences().add(sequence3);
        Assert.assertTrue(this.validator.validateSequence(prescription, sequence));
        Mockito.verify(this.facesUtils, Mockito.never()).addMessage(FacesMessage.SEVERITY_ERROR, "sequence.chevauchement");
    }
}

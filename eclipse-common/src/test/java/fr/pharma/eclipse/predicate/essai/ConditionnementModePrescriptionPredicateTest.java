package fr.pharma.eclipse.predicate.essai;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe {@link ConditionnementModePrescriptionPredicate}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConditionnementModePrescriptionPredicateTest extends AbstractEclipseJUnitTest

{
    /**
     * Prédicat testé.
     */
    private ConditionnementModePrescriptionPredicate predicate;

    /**
     * Mode de prescription attendu.
     */
    private static final ModePrescription MODE_PRESCRIPTION_ATTENDU = ModePrescription.DOSE;

    /**
     * Autre mode de prescription.
     */
    private static final ModePrescription MODE_PRESCRIPTION_AUTRE = ModePrescription.CONDITIONNEMENT_PRIMAIRE;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.predicate = new ConditionnementModePrescriptionPredicate(ConditionnementModePrescriptionPredicateTest.MODE_PRESCRIPTION_ATTENDU.name());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.predicate = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.predicate);
    }

    /**
     * Test de la méthode evaluate(java.lang.Object) - TRUE.
     */
    @Test
    public void testEvaluateTRUE() {
        final Conditionnement cond = new Conditionnement();
        cond.setId(2L);
        cond.setModePrescription(ConditionnementModePrescriptionPredicateTest.MODE_PRESCRIPTION_ATTENDU);
        Assert.assertTrue(this.predicate.evaluate(cond));
    }

    /**
     * Test de la méthode evaluate(java.lang.Object) - FALSE.
     */
    @Test
    public void testEvaluateFALSE() {
        final Conditionnement cond = new Conditionnement();
        cond.setId(2L);
        cond.setModePrescription(ConditionnementModePrescriptionPredicateTest.MODE_PRESCRIPTION_AUTRE);
        Assert.assertFalse(this.predicate.evaluate(cond));
    }
}

package fr.pharma.eclipse.predicate.essai;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.etat.DetailEtatEssai;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du prédicat EssaiActifPredicat.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiActifPredicateTest extends AbstractEclipseJUnitTest {

    /**
     * Predicat.
     */
    private EssaiActifPredicate predicat;

    /**
     * Essai.
     */
    private Essai essai;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.essai = new Essai();
        final Calendar dateFin = Calendar.getInstance();
        this.predicat = new EssaiActifPredicate(dateFin);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.essai = null;
        this.predicat = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.predicat);
        Assert.assertNotNull(this.essai);
    }

    /**
     * Test de la méthode evaluate.
     */
    @Test
    public void testEvaluateFalse1() {
        this.essai.setEtat(EtatEssai.EN_EVALUATION);
        Assert.assertFalse(this.predicat.evaluate(this.essai));
    }

    /**
     * Test de la méthode evaluate.
     */
    @Test
    public void testEvaluateFalse2() {
        this.essai.setEtat(EtatEssai.EN_EVALUATION);
        this.essai.setEtat(EtatEssai.EN_ATTENTE_MISE_EN_PLACE);
        final DetailEtatEssai detail = new DetailEtatEssai();
        detail.setEtatEssai(EtatEssai.EN_ATTENTE_MISE_EN_PLACE);
        detail.setDateMaj(Calendar.getInstance());
        this.essai.getDetailsEtatEssai().add(detail);
        Assert.assertFalse(this.predicat.evaluate(this.essai));
    }

    /**
     * Test de la méthode evaluate.
     */
    @Test
    public void testEvaluateFalse11() {
        final DetailEtatEssai detail = new DetailEtatEssai();
        detail.setEtatEssai(EtatEssai.EN_COURS);
        detail.setDateMaj(Calendar.getInstance());
        this.essai.getDetailsEtatEssai().add(detail);
        Assert.assertFalse(this.predicat.evaluate(this.essai));
    }

    /**
     * Test de la méthode evaluate.
     * @throws InterruptedException
     */
    @Test
    public void testEvaluateTrue1() throws InterruptedException {
        final DetailEtatEssai detail = new DetailEtatEssai();
        detail.setEtatEssai(EtatEssai.EN_COURS);
        detail.setDateMaj(Calendar.getInstance());
        this.essai.getDetailsEtatEssai().add(detail);
        Thread.sleep(100);
        this.predicat = new EssaiActifPredicate(Calendar.getInstance());
        Assert.assertTrue(this.predicat.evaluate(this.essai));
    }

    /**
     * Test de la méthode evaluate.
     * @throws InterruptedException
     */
    @Test
    public void testEvaluateTrue2() throws InterruptedException {
        // detail en cours
        DetailEtatEssai detail = new DetailEtatEssai();
        detail.setEtatEssai(EtatEssai.EN_COURS);
        detail.setDateMaj(Calendar.getInstance());
        this.essai.getDetailsEtatEssai().add(detail);
        Thread.sleep(100);
        this.predicat = new EssaiActifPredicate(Calendar.getInstance());

        Thread.sleep(100);
        // detail cloturé.
        this.essai.setEtat(EtatEssai.CLOTURE);
        detail = new DetailEtatEssai();
        detail.setEtatEssai(EtatEssai.CLOTURE);
        detail.setDateMaj(Calendar.getInstance());
        this.essai.getDetailsEtatEssai().add(detail);
        Assert.assertTrue(this.predicat.evaluate(this.essai));
    }

    /**
     * Test de la méthode evaluate.
     * @throws InterruptedException
     */
    @Test
    public void testEvaluateFalse3() throws InterruptedException {
        // detail en cours
        DetailEtatEssai detail = new DetailEtatEssai();
        detail.setEtatEssai(EtatEssai.EN_COURS);
        detail.setDateMaj(Calendar.getInstance());
        this.essai.getDetailsEtatEssai().add(detail);

        Thread.sleep(100);
        // detail cloturé.
        this.essai.setEtat(EtatEssai.CLOTURE);
        detail = new DetailEtatEssai();
        detail.setEtatEssai(EtatEssai.CLOTURE);
        detail.setDateMaj(Calendar.getInstance());
        this.essai.getDetailsEtatEssai().add(detail);
        Thread.sleep(100);
        this.predicat = new EssaiActifPredicate(Calendar.getInstance());
        Assert.assertFalse(this.predicat.evaluate(this.essai));
    }

    /**
     * Test de la méthode evaluate.
     * @throws InterruptedException
     */
    @Test
    public void testEvaluateTrue3() throws InterruptedException {
        // detail en cours
        DetailEtatEssai detail = new DetailEtatEssai();
        detail.setEtatEssai(EtatEssai.EN_COURS);
        detail.setDateMaj(Calendar.getInstance());
        this.essai.getDetailsEtatEssai().add(detail);
        Thread.sleep(100);
        this.predicat = new EssaiActifPredicate(Calendar.getInstance());

        Thread.sleep(100);
        // detail cloturé.
        this.essai.setEtat(EtatEssai.ARCHIVE);
        detail = new DetailEtatEssai();
        detail.setEtatEssai(EtatEssai.ARCHIVE);
        detail.setDateMaj(Calendar.getInstance());
        this.essai.getDetailsEtatEssai().add(detail);
        Assert.assertTrue(this.predicat.evaluate(this.essai));
    }

    /**
     * Test de la méthode evaluate.
     * @throws InterruptedException
     */
    @Test
    public void testEvaluateFalse4() throws InterruptedException {
        // detail en cours
        DetailEtatEssai detail = new DetailEtatEssai();
        detail.setEtatEssai(EtatEssai.EN_COURS);
        detail.setDateMaj(Calendar.getInstance());
        this.essai.getDetailsEtatEssai().add(detail);

        Thread.sleep(100);
        // detail cloturé.
        this.essai.setEtat(EtatEssai.ARCHIVE);
        detail = new DetailEtatEssai();
        detail.setEtatEssai(EtatEssai.ARCHIVE);
        detail.setDateMaj(Calendar.getInstance());
        this.essai.getDetailsEtatEssai().add(detail);
        Thread.sleep(100);
        this.predicat = new EssaiActifPredicate(Calendar.getInstance());
        Assert.assertFalse(this.predicat.evaluate(this.essai));
    }

}

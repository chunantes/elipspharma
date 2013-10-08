package fr.pharma.eclipse.predicate.alerte;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Classe en charge de tester le prédicat sur la visibilité d'un essai dans les
 * alertes.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AlerteEssaiVisiblePredicateTest {

    /**
     * AlerteEssaiVisiblePredicate à tester.
     */
    private AlerteEssaiVisiblePredicate predicat;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.predicat = new AlerteEssaiVisiblePredicate();
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.predicat = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.predicat);
    }

    /**
     * Test de la méthode d'évaluation.
     */
    @Test
    public void testEvaluateKOAlerteDisabled() {
        final Essai essai = new Essai();
        essai.setAlerteActive(Boolean.FALSE);
        Assert.assertFalse(this.predicat.evaluate(essai));
    }

    /**
     * Test de la méthode d'évaluation.
     */
    @Test
    public void testEvaluateOKEtatEnEvaluation() {
        final Essai essai = new Essai();
        essai.setAlerteActive(Boolean.TRUE);
        essai.setEtat(EtatEssai.EN_EVALUATION);
        Assert.assertTrue(this.predicat.evaluate(essai));
    }

    /**
     * Test de la méthode d'évaluation.
     */
    @Test
    public void testEvaluateOKEtatEnCours() {
        final Essai essai = new Essai();
        essai.setAlerteActive(Boolean.TRUE);
        essai.setEtat(EtatEssai.EN_COURS);
        Assert.assertTrue(this.predicat.evaluate(essai));
    }

    /**
     * Test de la méthode d'évaluation.
     */
    @Test
    public void testEvaluateOKEtatEnAttenteMiseEnPlace() {
        final Essai essai = new Essai();
        essai.setAlerteActive(Boolean.TRUE);
        essai.setEtat(EtatEssai.EN_ATTENTE_MISE_EN_PLACE);
        Assert.assertTrue(this.predicat.evaluate(essai));
    }

    /**
     * Test de la méthode d'évaluation.
     */
    @Test
    public void testEvaluateOKEtatEnAttenteLettreCloture() {
        final Essai essai = new Essai();
        essai.setAlerteActive(Boolean.TRUE);
        essai.setEtat(EtatEssai.EN_ATTENTE_LETTRE_CLOTURE);
        Assert.assertTrue(this.predicat.evaluate(essai));
    }

    /**
     * Test de la méthode d'évaluation.
     */
    @Test
    public void testEvaluateOKEtatEnAttenteCloture() {
        final Essai essai = new Essai();
        essai.setAlerteActive(Boolean.TRUE);
        essai.setEtat(EtatEssai.EN_ATTENTE_CLOTURE);
        Assert.assertTrue(this.predicat.evaluate(essai));
    }

    /**
     * Test de la méthode d'évaluation.
     */
    @Test
    public void testEvaluateOKCloture() {
        final Essai essai = new Essai();
        essai.setAlerteActive(Boolean.TRUE);
        essai.setEtat(EtatEssai.CLOTURE);
        Assert.assertTrue(this.predicat.evaluate(essai));
    }

}

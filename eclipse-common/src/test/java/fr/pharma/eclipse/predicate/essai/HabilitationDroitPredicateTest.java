package fr.pharma.eclipse.predicate.essai;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;

/**
 * Classe en charge de tester le prédicat de récupération d'habilitation en
 * fonction d'un droit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class HabilitationDroitPredicateTest {
    /**
     * HabilitationDroitPredicate à tester.
     */
    private HabilitationDroitPredicate predicat;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.predicat = new HabilitationDroitPredicate(Droit.INVESTIGATEUR_PRINCIPAL);
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
     * Méthode en charge de tester le prédicat OK.
     */
    @Test
    public void testEvaluateOK() {
        final Habilitation habilitation = new Habilitation();
        habilitation.setDroit(Droit.INVESTIGATEUR_PRINCIPAL);
        Assert.assertTrue(this.predicat.evaluate(habilitation));
    }

    /**
     * Méthode en charge de tester le prédicat KO.
     */
    @Test
    public void testEvaluateKO() {
        final Habilitation habilitation = new Habilitation();
        habilitation.setDroit(Droit.INVESTIGATEUR_CO);
        Assert.assertFalse(this.predicat.evaluate(habilitation));
    }

}

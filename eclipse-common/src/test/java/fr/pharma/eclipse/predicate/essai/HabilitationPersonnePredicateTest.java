package fr.pharma.eclipse.predicate.essai;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;

/**
 * Classe en charge de tester le prédicat de récupération d'habilitation en
 * fonction d'une personne.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class HabilitationPersonnePredicateTest {
    /**
     * HabilitationPersonnePredicate à tester.
     */
    private HabilitationPersonnePredicate predicat;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        final Personne personne = new Investigateur();
        personne.setId(1L);
        this.predicat = new HabilitationPersonnePredicate(personne);
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
        final Personne personne = new Investigateur();
        personne.setId(1L);
        habilitation.setPersonne(personne);
        Assert.assertTrue(this.predicat.evaluate(habilitation));
    }

    /**
     * Méthode en charge de tester le prédicat KO - habilitation désactivée.
     */
    @Test
    public void testEvaluateKOActive() {
        final Habilitation habilitation = new Habilitation();
        habilitation.setActive(false);
        final Personne personne = new Investigateur();
        personne.setId(1L);
        habilitation.setPersonne(personne);
        Assert.assertFalse(this.predicat.evaluate(habilitation));
    }

    /**
     * Méthode en charge de tester le prédicat KO - pas d'habilitation pour la
     * personne.
     */
    @Test
    public void testEvaluateKOPersonne() {
        final Habilitation habilitation = new Habilitation();
        final Personne personne = new Investigateur();
        personne.setId(2L);
        habilitation.setPersonne(personne);
        Assert.assertFalse(this.predicat.evaluate(habilitation));
    }

}

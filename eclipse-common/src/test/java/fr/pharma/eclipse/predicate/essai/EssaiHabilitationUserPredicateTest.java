package fr.pharma.eclipse.predicate.essai;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.comparator.habilitation.HabilitationComparator;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Classe en charge de tester le prédicat de récupération d'essais en fonction
 * de la définition des habilitations sur l'utilisateur connecté.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiHabilitationUserPredicateTest {
    /**
     * EssaiHabilitationUserPredicate à tester.
     */
    private EssaiHabilitationUserPredicate predicat;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        final Personne personne = new Investigateur();
        personne.setId(1L);
        this.predicat = new EssaiHabilitationUserPredicate(personne);
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
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        final SortedSet<Habilitation> habilitations = new TreeSet<Habilitation>(new HabilitationComparator());
        final Habilitation habilitation = new Habilitation();
        final Personne personne = new Investigateur();
        personne.setId(1L);
        habilitation.setPersonne(personne);
        habilitations.add(habilitation);
        essai.getDetailContacts().setHabilitations(habilitations);
        Assert.assertTrue(this.predicat.evaluate(essai));
    }

    /**
     * Méthode en charge de tester le prédicat KO.
     */
    @Test
    public void testEvaluateKO() {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        final SortedSet<Habilitation> habilitations = new TreeSet<Habilitation>(new HabilitationComparator());
        final Habilitation habilitation = new Habilitation();
        final Personne personne = new Investigateur();
        personne.setId(2L);
        habilitation.setPersonne(personne);
        habilitations.add(habilitation);
        essai.getDetailContacts().setHabilitations(habilitations);
        Assert.assertFalse(this.predicat.evaluate(essai));
    }
}

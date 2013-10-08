package fr.pharma.eclipse.predicate;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.essai.Essai;

/**
 * Test du prédicat générique GenericPredicate.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GenericPredicateTest {

    /**
     * Méthode en charge de tester l'introspection d'égalité.
     */
    @Test
    public void filterWithGenericPredicateEqualsOk() {
        final GenericPredicate predicator = new GenericPredicate("nom", "nom", false);
        final Essai essai = new Essai();
        essai.setNom("nom");
        Assert.assertTrue(predicator.evaluate(essai));

    }

    /**
     * Méthode en charge de tester l'introspection d'égalité sur un BeanObject.
     */
    @Test
    public void filterWithGenericPredicateBeanObjectEqualsOk() {
        final Promoteur promoteur = new Promoteur();
        promoteur.setId(1L);
        final Essai essai = new Essai();
        essai.setId(1L);
        essai.setPromoteur(promoteur);
        final GenericPredicate predicator = new GenericPredicate("promoteur", essai, false);
        Assert.assertTrue(predicator.evaluate(essai));
    }

    /**
     * Méthode en charge de tester l'introspection d'égalité Ko sur un
     * BeanObject.
     */
    @Test
    public void filterWithGenericPredicateBeanObjectEqualsKo() {
        final Promoteur promoteur = new Promoteur();
        promoteur.setId(1L);
        final Essai essai = new Essai();
        essai.setPromoteur(promoteur);
        final GenericPredicate predicator = new GenericPredicate("promoteur", essai, false);
        Assert.assertFalse(predicator.evaluate(essai));
    }

    /**
     * Méthode en charge de tester l'introspection de non egalité.
     */
    @Test
    public void filterWithGenericPredicateEqualsKo()

    {
        final GenericPredicate predicator = new GenericPredicate("nom", "nomX", false);
        final Essai essai = new Essai();
        essai.setNom("nom");
        Assert.assertFalse(predicator.evaluate(essai));
    }

    /**
     * Méthode en charge de tester l'introspection sur une valeur null.
     */
    @Test
    public void filterWithGenericPredicateNullValue()

    {
        final GenericPredicate predicator = new GenericPredicate("nom", "nom", false);
        final Essai essai = new Essai();
        essai.setNom(null);
        Assert.assertFalse(predicator.evaluate(essai));
    }

    /**
     * Méthode en charge de tester l'introspection avec WildCard.
     */
    @Test
    public void filterWithGenericPredicateWithWildCard()

    {
        final GenericPredicate predicator = new GenericPredicate("nom", "nom", true);
        final Essai essai = new Essai();
        essai.setNom("*");
        Assert.assertTrue(predicator.evaluate(essai));
    }

    /**
     * Méthode en charge de tester l'introspection avec erreur de nestedpath. @
     * Erreur d'introspection.
     */
    @Test
    public void filterWithGenericPredicateNestedPathError()

    {
        final GenericPredicate predicator = new GenericPredicate("noms", "nom", true);
        final Essai essai = new Essai();
        essai.setNom("*");
        Assert.assertFalse(predicator.evaluate(essai));
    }

    /**
     * Méthode en charge de tester l'introspection avec erreur de nestedpath. @
     * Erreur d'introspection.
     */
    @Test
    public void filterWithGenericPredicateWithBeanObject() {
        final Essai essai = new Essai();
        essai.setNom("libelles_toto");
        final GenericPredicate predicator = new GenericPredicate("nom", essai, true);
        Assert.assertTrue(predicator.evaluate(essai));
    }

    @Test
    public void testHandleBeanObject() {
        final Essai essai1 = new Essai();
        essai1.setId(1L);
        essai1.setNom("libelles_toto");

        final Essai essai1Copy = new Essai();
        essai1Copy.setId(1L);
        essai1Copy.setNom("libelles_toto");

        final Essai essai2 = new Essai();
        essai2.setId(2L);
        essai2.setNom("libelles_toto");

        final GenericPredicate predicate = new GenericPredicate("nom", essai1, true);

        Assert.assertTrue(predicate.handleBeanObject(essai1));
        Assert.assertTrue(predicate.handleBeanObject(essai1Copy));
        Assert.assertFalse(predicate.handleBeanObject(essai2));
        Assert.assertFalse(predicate.handleBeanObject(null));
    }

}

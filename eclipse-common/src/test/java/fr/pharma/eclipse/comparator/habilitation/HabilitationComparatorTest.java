package fr.pharma.eclipse.comparator.habilitation;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;

/**
 * Classe en charge de tester le comparator de Habilitation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class HabilitationComparatorTest {

    /**
     * Méthode en charge de tester la comparaison.
     */
    @Test
    public void testCompareEquals() {

        final Habilitation habilitation1 = new Habilitation();
        final Personne personne1 = new Investigateur();
        personne1.setType(TypePersonne.INVESTIGATEUR);
        personne1.setNom("nom");
        habilitation1.setDroit(Droit.INVESTIGATEUR_PRINCIPAL);
        habilitation1.setPersonne(personne1);
        habilitation1.setActive(true);

        final Habilitation habilitation2 = new Habilitation();
        final Personne personne2 = new Investigateur();
        personne2.setType(TypePersonne.INVESTIGATEUR);
        personne2.setNom("nom");
        habilitation2.setDroit(Droit.INVESTIGATEUR_PRINCIPAL);
        habilitation2.setPersonne(personne2);
        habilitation2.setActive(false);

        final Habilitation habilitation1Copy = new Habilitation();
        final Personne personne1Copy = new Investigateur();
        personne1Copy.setType(TypePersonne.INVESTIGATEUR);
        personne1Copy.setNom("nom");
        habilitation1Copy.setDroit(Droit.INVESTIGATEUR_PRINCIPAL);
        habilitation1Copy.setPersonne(personne1Copy);

        Assert.assertTrue("Test habilitations identiques", new HabilitationComparator().compare(habilitation1, habilitation1Copy) == 0);
        Assert.assertTrue("Test habilitation1 < habilitation2", new HabilitationComparator().compare(habilitation1, habilitation2) < 0);
        Assert.assertTrue("Test habilitation1 > habilitation2", new HabilitationComparator().compare(habilitation2, habilitation1) > 0);
        Assert.assertTrue("Test comparaison avec habilitation nulle", new HabilitationComparator().compare(null, habilitation1) < 0);
        Assert.assertTrue("Test comparaison avec habilitation nulle", new HabilitationComparator().compare(habilitation1, null) > 0);
        Assert.assertTrue("Test comparaison avec habilitations nulles", new HabilitationComparator().compare(null, null) == 0);

        habilitation1.setPersonne(null);
        Assert.assertTrue("Test comparaison avec habilitation.personne null", new HabilitationComparator().compare(null, habilitation1) < 0);
        habilitation2.setPersonne(null);
        Assert.assertTrue("Test comparaison avec habilitation.personne null", new HabilitationComparator().compare(null, null) == 0);
    }
}

package fr.pharma.eclipse.validator.remove.impl;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.comparator.habilitation.HabilitationComparator;
import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.exception.ValidationException;

/**
 * Classe en charge de tester le validator de suppression de Personne.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PersonneRemoveValidatorTest {
    /**
     * Validator de suppression à tester.
     */
    private PersonneRemoveValidator validator;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.validator = new PersonneRemoveValidator();
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.validator = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.validator);
    }

    /**
     * Méthode en charge de tester la validation OK.
     */
    @Test
    public void testValidateOK() {
        final Personne personne = new Investigateur();
        try {
            this.validator.validate(personne);
        } catch (final ValidationException e) {
            Assert.fail("Exception not expected");
        }
    }

    /**
     * Méthode en charge de tester la validation KO.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKO() {
        final Personne personne = new Investigateur();

        final Habilitation habilitation = new Habilitation();
        final Essai essai = new Essai();
        habilitation.setDetailContacts(essai.getDetailContacts());
        habilitation.setPersonne(personne);
        habilitation.setDroit(Droit.ARC_INVESTIGATEUR);

        final SortedSet<Habilitation> habilitations = new TreeSet<Habilitation>(new HabilitationComparator());
        habilitations.add(habilitation);

        personne.setHabilitations(habilitations);

        this.validator.validate(personne);
    }

}

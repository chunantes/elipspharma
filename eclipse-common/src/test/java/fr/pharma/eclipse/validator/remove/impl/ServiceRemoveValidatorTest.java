package fr.pharma.eclipse.validator.remove.impl;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.domain.model.acteur.ArcInvestigateur;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.exception.ValidationException;

/**
 * Classe en charge de tester le validator sur la suppression de Service.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ServiceRemoveValidatorTest {
    /**
     * Validator de suppression à tester.
     */
    private ServiceRemoveValidator validator;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.validator = new ServiceRemoveValidator();
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
     * Méthode en charge de tester la validation de la suppression de Service.
     */
    @Test
    public void testValidateOK() {
        final Service service = new Service();
        try {
            this.validator.validate(service);
        } catch (final ValidationException e) {
            Assert.fail("ValidationException not expected");
        }
    }

    /**
     * Méthode en charge de tester la validation de la suppression de Service.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKOInvestigateurs() {
        final Service service = new Service();
        final SortedSet<Investigateur> investigateurs = new TreeSet<Investigateur>(new BeanWithNomComparator());
        final Investigateur investigateur = new Investigateur();
        investigateur.setNom("nom");
        investigateurs.add(investigateur);
        service.setInvestigateurs(investigateurs);
        this.validator.validate(service);
    }

    /**
     * Méthode en charge de tester la validation de la suppression de Service.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKOArcInvestigateurs() {
        final Service service = new Service();
        final SortedSet<ArcInvestigateur> arcInvestigateurs = new TreeSet<ArcInvestigateur>(new BeanWithNomComparator());
        final ArcInvestigateur arcInvestigateur = new ArcInvestigateur();
        arcInvestigateur.setNom("nom");
        arcInvestigateurs.add(arcInvestigateur);
        service.setArcInvestigateurs(arcInvestigateurs);
        this.validator.validate(service);
    }

    /**
     * Méthode en charge de tester la validation de la suppression de Service.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKOEssais() {
        final Service service = new Service();
        final SortedSet<Essai> essais = new TreeSet<Essai>(new BeanWithNomComparator());
        final Essai essai = new Essai();
        essai.setNom("nom");
        essais.add(essai);
        service.setEssais(essais);
        this.validator.validate(service);
    }

}

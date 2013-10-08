package fr.pharma.eclipse.validator.save.impl;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.domain.model.localisation.Etablissement;
import fr.pharma.eclipse.domain.model.localisation.Site;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Classe en charge de tester le validator de sauvegarde de Pharmacie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PharmacieSaveValidatorTest {
    /**
     * PharmacieSaveValidator à tester.
     */
    private PharmacieSaveValidator validator;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.validator = new PharmacieSaveValidator();
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
    @SuppressWarnings("unchecked")
    @Test
    public void testValidate() {
        final Pharmacie pharmacie = new Pharmacie();
        final Etablissement etablissement = new Etablissement();
        etablissement.setId(1L);
        pharmacie.setEtablissement(etablissement);

        final SortedSet<Site> sites = new TreeSet<Site>(new BeanWithNomComparator());
        final Site site = new Site();
        site.setNom("site");
        site.setEtablissement(etablissement);
        sites.add(site);
        pharmacie.setSites(sites);

        final GenericService<Pharmacie> mockedService = Mockito.mock(GenericService.class);
        try {
            this.validator.validate(pharmacie, mockedService);
        } catch (final ValidationException e) {
            Assert.fail("Exception not expected");
        }
    }

    /**
     * Méthode en charge de tester la validation KO (pas de sites).
     */
    @SuppressWarnings("unchecked")
    @Test(expected = ValidationException.class)
    public void testValidateKOSites() {
        final Pharmacie pharmacie = new Pharmacie();
        final GenericService<Pharmacie> mockedService = Mockito.mock(GenericService.class);
        this.validator.validate(pharmacie, mockedService);
    }

    /**
     * Méthode en charge de tester la validation KO (pb entre établissement de
     * pharmacie et etablissements des sites de la pharmacie).
     */
    @SuppressWarnings("unchecked")
    @Test(expected = ValidationException.class)
    public void testValidateKOEtablissement() {
        final Pharmacie pharmacie = new Pharmacie();
        final Etablissement etablissement = new Etablissement();
        etablissement.setId(1L);
        pharmacie.setEtablissement(etablissement);

        final SortedSet<Site> sites = new TreeSet<Site>(new BeanWithNomComparator());
        final Site site = new Site();
        site.setNom("site");
        final Etablissement etablissement2 = new Etablissement();
        etablissement2.setId(2L);
        site.setEtablissement(etablissement2);
        sites.add(site);
        pharmacie.setSites(sites);
        final GenericService<Pharmacie> mockedService = Mockito.mock(GenericService.class);
        this.validator.validate(pharmacie, mockedService);
    }

}

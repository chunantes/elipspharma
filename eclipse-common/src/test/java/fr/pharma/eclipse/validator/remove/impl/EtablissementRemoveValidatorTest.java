package fr.pharma.eclipse.validator.remove.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Etablissement;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.localisation.PoleService;
import fr.pharma.eclipse.service.localisation.SiteService;
import fr.pharma.eclipse.service.stockage.PharmacieService;

/**
 * Classe en charge de tester le validator de suppression de etablissement.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EtablissementRemoveValidatorTest {
    /**
     * Validator de suppression à tester.
     */
    private EtablissementRemoveValidator validator;

    /**
     * Service de gestion des poles mocké.
     */
    private PoleService mockPoleService;

    /**
     * Service de gestion des pharmacies mocké.
     */
    private PharmacieService mockPharmacieService;

    /**
     * Service de gestion des sites mocké.
     */
    private SiteService mockSiteService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.validator = new EtablissementRemoveValidator();
        this.mockPharmacieService = Mockito.mock(PharmacieService.class);
        this.validator.setPharmacieService(this.mockPharmacieService);
        this.mockPoleService = Mockito.mock(PoleService.class);
        this.validator.setPoleService(this.mockPoleService);
        this.mockSiteService = Mockito.mock(SiteService.class);
        this.validator.setSiteService(this.mockSiteService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.validator = null;
        this.mockPoleService = null;
        this.mockPharmacieService = null;
        this.mockSiteService = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.validator);
        Assert.assertNotNull(this.mockPharmacieService);
        Assert.assertNotNull(this.mockPoleService);
        Assert.assertNotNull(this.mockSiteService);
    }

    /**
     * Méthode en charge de tester la validation de la suppression
     * d'établissement.
     */
    @Test
    public void testValidateOK() {
        final Etablissement etablissement = new Etablissement();
        Mockito.when(this.mockPharmacieService.count((SearchCriteria) Matchers.any())).thenReturn(0L);
        Mockito.when(this.mockPoleService.count((SearchCriteria) Matchers.any())).thenReturn(0L);
        Mockito.when(this.mockSiteService.count((SearchCriteria) Matchers.any())).thenReturn(0L);
        try {
            this.validator.validate(etablissement);
        } catch (final ValidationException e) {
            Assert.fail("ValidationException not expected");
        }
    }

    /**
     * Méthode en charge de tester la validation de la suppression
     * d'établissement.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKOPharma() {
        final Etablissement etablissement = new Etablissement();
        Mockito.when(this.mockPharmacieService.count((SearchCriteria) Matchers.any())).thenReturn(1L);
        this.validator.validate(etablissement);
    }

    /**
     * Méthode en charge de tester la validation de la suppression
     * d'établissement.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKOPole() {
        final Etablissement etablissement = new Etablissement();
        Mockito.when(this.mockPharmacieService.count((SearchCriteria) Matchers.any())).thenReturn(0L);
        Mockito.when(this.mockPoleService.count((SearchCriteria) Matchers.any())).thenReturn(1L);
        this.validator.validate(etablissement);
    }

    /**
     * Méthode en charge de tester la validation de la suppression
     * d'établissement.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKOSite() {
        final Etablissement etablissement = new Etablissement();
        Mockito.when(this.mockPharmacieService.count((SearchCriteria) Matchers.any())).thenReturn(0L);
        Mockito.when(this.mockPoleService.count((SearchCriteria) Matchers.any())).thenReturn(0L);
        Mockito.when(this.mockSiteService.count((SearchCriteria) Matchers.any())).thenReturn(1L);
        this.validator.validate(etablissement);
    }

}

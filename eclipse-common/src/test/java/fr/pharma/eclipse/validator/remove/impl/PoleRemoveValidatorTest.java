package fr.pharma.eclipse.validator.remove.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Pole;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.localisation.ServiceService;

/**
 * Classe en charge de tester le validator de suppression de pole.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PoleRemoveValidatorTest {
    /**
     * Validator de suppression à tester.
     */
    private PoleRemoveValidator validator;

    /**
     * Service de gestion des services mocké.
     */
    private ServiceService mockService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.validator = new PoleRemoveValidator();
        this.mockService = Mockito.mock(ServiceService.class);
        this.validator.setServiceService(this.mockService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.validator = null;
        this.mockService = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.validator);
        Assert.assertNotNull(this.mockService);
    }

    /**
     * Méthode en charge de tester la validation de la suppression de pole.
     */
    @Test
    public void testValidateOK() {
        final Pole pole = new Pole();
        Mockito.when(this.mockService.count((SearchCriteria) Matchers.any())).thenReturn(0L);
        try {
            this.validator.validate(pole);
        } catch (final ValidationException e) {
            Assert.fail("ValidationException not expected");
        }
    }

    /**
     * Méthode en charge de tester la validation de la suppression de pole.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKO() {
        final Pole pole = new Pole();
        Mockito.when(this.mockService.count((SearchCriteria) Matchers.any())).thenReturn(1L);
        this.validator.validate(pole);
    }

}

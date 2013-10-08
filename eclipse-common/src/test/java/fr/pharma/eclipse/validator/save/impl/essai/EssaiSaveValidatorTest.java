package fr.pharma.eclipse.validator.save.impl.essai;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.ServiceUtils;

/**
 * Test de la classe EssaiGlobalInfosSaveValidator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiSaveValidatorTest {
    /**
     * Validateur testé.
     */
    private EssaiGlobalInfosSaveValidator validator;

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void setUp() {
        this.validator = new EssaiGlobalInfosSaveValidator();
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown() {
        this.validator = null;
    }

    /**
     * Test validate ok.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testValidateOk() {
        final GenericService<Essai> mockedService = Mockito.mock(GenericService.class);
        final Essai essai = EssaiUtils.makeEssaiTest(1L, TypePromoteur.ACADEMIQUE);
        essai.getServices().add(ServiceUtils.makeServiceTest(1));
        try {
            this.validator.validate(essai, mockedService);
        } catch (final ValidationException e) {
            Assert.fail("Exception non attendue");
        }
    }

    /**
     * Test validate ko.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testValidateKo() {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        final GenericService<Essai> mockedService = Mockito.mock(GenericService.class);
        try {
            this.validator.validate(essai, mockedService);
            Assert.fail("Exception attendue");
        } catch (final ValidationException e) {
            Assert.assertEquals("essai.typePromoteur", e.getErrorCode());
            Assert.assertEquals(1, e.getValues().length);
            Assert.assertEquals("notEmpty", e.getValues()[0]);
        }
    }

}

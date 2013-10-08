package fr.pharma.eclipse.validator.save.impl.essai;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.criteria.essai.EssaiSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe UniciteNumeroInterneSaveValidator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class UniciteNumeroInterneSaveValidatorTest {

    /**
     * Validateur testé.
     */
    private UniciteNumeroInterneSaveValidator validator;

    /**
     * Fabrique Spring mockée.
     */
    private BeanFactory mockedBeanFactory;

    /**
     * Mock du service d'essais.
     */
    private EssaiService mockedService;

    /**
     * Mock de critère de recherche.
     */
    private EssaiSearchCriteria mockedSearchCriteria;

    /**
     * Nom du bean pour la récupération du critère de recherche.
     */
    private static final String CRIT_ID = "essaiCriteria";

    /**
     * Numéro interne de test.
     */
    private static final String NUM_INTERNE = "numInt-001";

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void setUp() {
        this.mockedBeanFactory = Mockito.mock(BeanFactory.class);
        this.mockedService = Mockito.mock(EssaiService.class);
        this.mockedSearchCriteria = Mockito.mock(EssaiSearchCriteria.class);
        this.validator = new UniciteNumeroInterneSaveValidator();
        this.validator.setBeanFactory(this.mockedBeanFactory);

        Mockito.when(this.mockedBeanFactory.getBean(UniciteNumeroInterneSaveValidatorTest.CRIT_ID)).thenReturn(this.mockedSearchCriteria);
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown() {
        this.validator = null;
        this.mockedBeanFactory = null;
        this.mockedService = null;
        this.mockedSearchCriteria = null;
    }

    /**
     * Test validate ok - aucun essai ne porte sur le numéro interne dans la
     * base.
     */
    @Test
    public void testValidateOkGetAllSize0() {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        essai.setNumInterne(UniciteNumeroInterneSaveValidatorTest.NUM_INTERNE);

        Mockito.when(this.mockedService.getAll(this.mockedSearchCriteria)).thenReturn(new ArrayList<Essai>());
        try {
            this.validator.validate(essai, this.mockedService);
        } catch (final ValidationException e) {
            Assert.fail("Exception non attendue");
        }
        Mockito.verify(this.mockedBeanFactory).getBean(UniciteNumeroInterneSaveValidatorTest.CRIT_ID);
        Mockito.verify(this.mockedSearchCriteria).setNumInterneStrict(UniciteNumeroInterneSaveValidatorTest.NUM_INTERNE);
        Mockito.verify(this.mockedService).getAll(this.mockedSearchCriteria);
    }

    /**
     * Test validate ok - l'essai existe déjà en base, et c'est le seul avec ce
     * numéro interne.
     */
    @Test
    public void testValidateOkGetAllSize1() {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        essai.setNumInterne(UniciteNumeroInterneSaveValidatorTest.NUM_INTERNE);

        Mockito.when(this.mockedService.getAll(this.mockedSearchCriteria)).thenReturn(Arrays.asList(essai));
        try {
            this.validator.validate(essai, this.mockedService);
        } catch (final ValidationException e) {
            Assert.fail("Exception non attendue");
        }
        Mockito.verify(this.mockedBeanFactory).getBean(UniciteNumeroInterneSaveValidatorTest.CRIT_ID);
        Mockito.verify(this.mockedSearchCriteria).setNumInterneStrict(UniciteNumeroInterneSaveValidatorTest.NUM_INTERNE);
        Mockito.verify(this.mockedService).getAll(this.mockedSearchCriteria);
    }

    /**
     * Test validate ko - un autre bean que moi porte ce numéro en base.
     */
    @Test
    public void testValidateKo() {
        long ids = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(ids++);
        essai.setNumInterne(UniciteNumeroInterneSaveValidatorTest.NUM_INTERNE);

        Mockito.when(this.mockedService.getAll(this.mockedSearchCriteria)).thenReturn(Arrays.asList(EssaiUtils.makeEssaiTest(ids++)));
        try {
            this.validator.validate(essai, this.mockedService);
            Assert.fail("Exception attendue");
        } catch (final ValidationException e) {
            Assert.assertEquals("essai.numInterne", e.getErrorCode());
            Assert.assertEquals(1, e.getValues().length);
            Assert.assertEquals("unique", e.getValues()[0]);
        }
        Mockito.verify(this.mockedBeanFactory).getBean(UniciteNumeroInterneSaveValidatorTest.CRIT_ID);
        Mockito.verify(this.mockedSearchCriteria).setNumInterneStrict(UniciteNumeroInterneSaveValidatorTest.NUM_INTERNE);
        Mockito.verify(this.mockedService).getAll(this.mockedSearchCriteria);
    }

}

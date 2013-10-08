package fr.pharma.eclipse.validator.save.impl.essai;

import java.util.Calendar;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Test de la classe EssaiDetailDatesSaveValidator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiDetailDatesSaveValidatorTest {

    /**
     * Validateur testé.
     */
    private EssaiDetailDatesSaveValidator validator;

    /**
     * Calendrier de test pour une date de début.
     */
    private Calendar debutTest;

    /**
     * Calendrier de test pour une date de finTest.
     */
    private Calendar finTest;

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void setUp() {
        this.validator = new EssaiDetailDatesSaveValidator();

        // Initialisation des données de test.
        this.debutTest = Calendar.getInstance(EclipseConstants.LOCALE);
        this.finTest = Calendar.getInstance(EclipseConstants.LOCALE);
        this.finTest.add(Calendar.DATE, 1);
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown() {
        this.validator = null;
        this.debutTest = null;
        this.finTest = null;
    }

    /**
     * Test validate - début/fin prévisionnelles de l'étude.
     */
    @Test
    public void testValidateDatesEtudePrev() {
        final String propertyDebut = "detailDates.debutEtudePrev";
        final String propertyFin = "detailDates.finEtudePrev";
        this.launchValidateDatesEtudeOk(this.debutTest, this.finTest, propertyDebut, propertyFin);
        this.launchValidateDatesEtudeOk(null, this.finTest, propertyDebut, propertyFin);
        this.launchValidateDatesEtudeOk(this.debutTest, null, propertyDebut, propertyFin);
        this.launchValidateDatesEtudeOk(null, null, propertyDebut, propertyFin);
        this.launchValidateDatesEtudeKo(this.finTest, this.debutTest, propertyDebut, propertyFin, "essai.detailDates.validation.etude_prev");
    }

    /**
     * Test validate - début/fin de l'étude.
     */
    @Test
    public void testValidateDatesEtude() {
        final String propertyDebut = "detailDates.debutEtude";
        final String propertyFin = "detailDates.finEtude";
        this.launchValidateDatesEtudeOk(this.debutTest, this.finTest, propertyDebut, propertyFin);
        this.launchValidateDatesEtudeOk(null, this.finTest, propertyDebut, propertyFin);
        this.launchValidateDatesEtudeOk(this.debutTest, null, propertyDebut, propertyFin);
        this.launchValidateDatesEtudeOk(null, null, propertyDebut, propertyFin);
        this.launchValidateDatesEtudeKo(this.finTest, this.debutTest, propertyDebut, propertyFin, "essai.detailDates.validation.etude_reel");
    }

    /**
     * Test validate - début/fin prévisionnelles de l'inclusion.
     */
    @Test
    public void testValidateDatesInclusionPrev() {
        final String propertyDebut = "detailDates.debutInclusionPrev";
        final String propertyFin = "detailDates.finInclusionPrev";
        this.launchValidateDatesEtudeOk(this.debutTest, this.finTest, propertyDebut, propertyFin);
        this.launchValidateDatesEtudeOk(null, this.finTest, propertyDebut, propertyFin);
        this.launchValidateDatesEtudeOk(this.debutTest, null, propertyDebut, propertyFin);
        this.launchValidateDatesEtudeOk(null, null, propertyDebut, propertyFin);
        this.launchValidateDatesEtudeKo(this.finTest, this.debutTest, propertyDebut, propertyFin, "essai.detailDates.validation.inclusion_prev");
    }

    /**
     * Test validate - début/fin de l'inclusion.
     */
    @Test
    public void testValidateDatesInclusion() {
        final String propertyDebut = "detailDates.debutInclusion";
        final String propertyFin = "detailDates.finInclusion";
        this.launchValidateDatesEtudeOk(this.debutTest, this.finTest, propertyDebut, propertyFin);
        this.launchValidateDatesEtudeOk(null, this.finTest, propertyDebut, propertyFin);
        this.launchValidateDatesEtudeOk(this.debutTest, null, propertyDebut, propertyFin);
        this.launchValidateDatesEtudeOk(null, null, propertyDebut, propertyFin);
        this.launchValidateDatesEtudeKo(this.finTest, this.debutTest, propertyDebut, propertyFin, "essai.detailDates.validation.inclusion_reel");
    }

    /**
     * Test validate - pré-clôture/clôture.
     */
    @Test
    public void testValidateDatesClotures() {
        final String propertyDebut = "detailDates.preCloture";
        final String propertyFin = "detailDates.cloture";
        this.launchValidateDatesEtudeOk(this.debutTest, this.finTest, propertyDebut, propertyFin);
        this.launchValidateDatesEtudeOk(null, this.finTest, propertyDebut, propertyFin);
        this.launchValidateDatesEtudeOk(this.debutTest, null, propertyDebut, propertyFin);
        this.launchValidateDatesEtudeOk(null, null, propertyDebut, propertyFin);
        this.launchValidateDatesEtudeKo(this.finTest, this.debutTest, propertyDebut, propertyFin, "essai.detailDates.validation.cloture");
    }

    /**
     * Méthode pour lancer un test de validation ok.
     * @param debut Date de début.
     * @param fin Date de fin.
     * @param propertyDebut Propriété de l'essai où valoriser la date de début.
     * @param propertyFin Propriété de l'essai où valoriser la date de fin.
     */
    @SuppressWarnings("unchecked")
    private void launchValidateDatesEtudeOk(final Calendar debut,
                                            final Calendar fin,
                                            final String propertyDebut,
                                            final String propertyFin) {
        final Essai essai = this.initTest(debut, fin, propertyDebut, propertyFin);
        final GenericService<Essai> mockedService = Mockito.mock(GenericService.class);
        try {
            this.validator.validate(essai, mockedService);
        } catch (final ValidationException e) {
            Assert.fail("Aucune exception attendue" + e);
        }
    }

    /**
     * Méthode pour lancer un test de validation ko.
     * @param debut Date de début.
     * @param fin Date de fin.
     * @param propertyDebut Propriété de l'essai où valoriser la date de début.
     * @param propertyFin Propriété de l'essai où valoriser la date de fin.
     * @param expectedErrorCode Code d'erreur attendu.
     */
    @SuppressWarnings("unchecked")
    private void launchValidateDatesEtudeKo(final Calendar debut,
                                            final Calendar fin,
                                            final String propertyDebut,
                                            final String propertyFin,
                                            final String expectedErrorCode) {
        final Essai essai = this.initTest(debut, fin, propertyDebut, propertyFin);
        final GenericService<Essai> mockedService = Mockito.mock(GenericService.class);
        try {
            this.validator.validate(essai, mockedService);
            Assert.fail("Exception attendue");
        } catch (final ValidationException e) {
            Assert.assertEquals(expectedErrorCode, e.getErrorCode());
            Assert.assertEquals(1, e.getValues().length);
            Assert.assertEquals("invalid_debut_fin", e.getValues()[0]);
        }
    }

    /**
     * Méthode de création de test pour l'essai.
     * @param debut Date de début.
     * @param fin Date de fin.
     * @param propertyDebut Propriété de l'essai où valoriser la date de début.
     * @param propertyFin Propriété de l'essai où valoriser la date de fin.
     * @return L'essai créé.
     */
    private Essai initTest(final Calendar debut,
                           final Calendar fin,
                           final String propertyDebut,
                           final String propertyFin) {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        BeanTool.setPropriete(essai, propertyDebut, debut);
        BeanTool.setPropriete(essai, propertyFin, fin);
        return essai;
    }

}

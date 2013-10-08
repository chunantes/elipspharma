package fr.pharma.eclipse.validator.save.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Etablissement;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.localisation.EtablissementService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du validator EtablissementSaveValidator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EtablissementSaveValidatorTest extends AbstractEclipseJUnitTest {

    /**
     * Validator testé.
     */
    private EtablissementSaveValidator<Etablissement> validator;

    /**
     * Etablissement.
     */
    private EtablissementService etablissement;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.etablissement = Mockito.mock(EtablissementService.class);
        this.validator = new EtablissementSaveValidator<Etablissement>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.validator = null;
        this.etablissement = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.validator);
        Assert.assertNotNull(this.etablissement);
    }

    /**
     * Test de la méthode Validate.
     */
    @Test
    public void testValidateOk() {
        final Etablissement bean = new Etablissement();

        Mockito.when(this.etablissement.getAll(Matchers.any(SearchCriteria.class))).thenReturn(new ArrayList<Etablissement>());

        this.validator.validate(bean, this.etablissement);
    }

    /**
     * Test de la méthode Validate.
     */
    @Test
    public void testValidateOkModify() {
        final Etablissement etablissement = new Etablissement();
        etablissement.setId(1L);

        final Etablissement exist = new Etablissement();
        exist.setId(1L);

        final List<Etablissement> liste = new ArrayList<Etablissement>();
        liste.add(exist);

        Mockito.when(this.etablissement.getAll(Matchers.any(SearchCriteria.class))).thenReturn(liste);

        this.validator.validate(etablissement, this.etablissement);
    }

    /**
     * Test de la méthode Validate.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKo() {
        final Etablissement etablissement = new Etablissement();
        etablissement.setId(1L);

        final Etablissement exist = new Etablissement();
        exist.setId(2L);

        final List<Etablissement> liste = new ArrayList<Etablissement>();
        liste.add(exist);

        Mockito.when(this.etablissement.getAll(Matchers.any(SearchCriteria.class))).thenReturn(liste);

        this.validator.validate(etablissement, this.etablissement);
    }
}

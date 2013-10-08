package fr.pharma.eclipse.validator.save.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Pole;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.localisation.PoleService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du validator PoleSaveValidator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PoleSaveValidatorTest extends AbstractEclipseJUnitTest {

    /**
     * Validator testé.
     */
    private PoleSaveValidator<Pole> validator;

    /**
     * Pole.
     */
    private PoleService pole;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.pole = Mockito.mock(PoleService.class);
        this.validator = new PoleSaveValidator<Pole>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.validator = null;
        this.pole = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.validator);
        Assert.assertNotNull(this.pole);
    }

    /**
     * Test de la méthode Validate.
     */
    @Test
    public void testValidateOk() {
        final Pole bean = new Pole();

        Mockito.when(this.pole.getAll(Matchers.any(SearchCriteria.class))).thenReturn(new ArrayList<Pole>());

        this.validator.validate(bean, this.pole);
    }

    /**
     * Test de la méthode Validate.
     */
    @Test
    public void testValidateOkModify() {
        final Pole pole = new Pole();
        pole.setId(1L);

        final Pole exist = new Pole();
        exist.setId(1L);

        final List<Pole> liste = new ArrayList<Pole>();
        liste.add(exist);

        Mockito.when(this.pole.getAll(Matchers.any(SearchCriteria.class))).thenReturn(liste);

        this.validator.validate(pole, this.pole);
    }

    /**
     * Test de la méthode Validate.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKo() {
        final Pole pole = new Pole();
        pole.setId(1L);

        final Pole exist = new Pole();
        exist.setId(2L);

        final List<Pole> liste = new ArrayList<Pole>();
        liste.add(exist);

        Mockito.when(this.pole.getAll(Matchers.any(SearchCriteria.class))).thenReturn(liste);

        this.validator.validate(pole, this.pole);
    }
}

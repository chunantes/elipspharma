package fr.pharma.eclipse.validator.save.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Pole;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.localisation.Site;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.localisation.ServiceService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du validator ServiceSaveValidator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ServiceSaveValidatorTest extends AbstractEclipseJUnitTest {

    /**
     * Validator testé.
     */
    private ServiceSaveValidator<Service> validator;

    /**
     * Service.
     */
    private ServiceService service;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.service = Mockito.mock(ServiceService.class);
        this.validator = new ServiceSaveValidator<Service>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.validator = null;
        this.service = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.validator);
        Assert.assertNotNull(this.service);
    }

    /**
     * Test de la méthode Validate.
     */
    @Test
    public void testValidateOk() {
        final Service bean = new Service();

        bean.setPole(new Pole());
        Mockito.when(this.service.getAll(Matchers.any(SearchCriteria.class))).thenReturn(new ArrayList<Service>());

        this.validator.validate(bean, this.service);
    }

    /**
     * Test de la méthode Validate.
     */
    @Test
    public void testValidateOkModify() {
        final Service service = new Service();
        service.setId(1L);

        service.setSite(new Site());
        final Service exist = new Service();
        exist.setId(1L);

        final List<Service> liste = new ArrayList<Service>();
        liste.add(exist);

        Mockito.when(this.service.getAll(Matchers.any(SearchCriteria.class))).thenReturn(liste);

        this.validator.validate(service, this.service);
    }

    /**
     * Test de la méthode Validate.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKo() {
        final Service service = new Service();
        service.setId(1L);

        final Service exist = new Service();
        exist.setId(2L);

        final List<Service> liste = new ArrayList<Service>();
        liste.add(exist);

        Mockito.when(this.service.getAll(Matchers.any(SearchCriteria.class))).thenReturn(liste);

        this.validator.validate(service, this.service);
    }

    /**
     * Test de la méthode Validate.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKo2() {
        final Service service = new Service();
        service.setId(1L);

        final Service exist = new Service();
        exist.setId(1L);

        final List<Service> liste = new ArrayList<Service>();
        liste.add(exist);

        Mockito.when(this.service.getAll(Matchers.any(SearchCriteria.class))).thenReturn(liste);

        this.validator.validate(service, this.service);
    }
}

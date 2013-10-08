package fr.pharma.eclipse.validator.save.impl;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Classe en charge de tester le validator de sauvegarde de Investigateur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class InvestigateurSaveValidatorTest {

    /**
     * InvestigateurSaveValidator à tester.
     */
    private InvestigateurSaveValidator validator;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.validator = new InvestigateurSaveValidator();
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
     * Méthode en charge de tester la validation OK de la sauvegarde d'un
     * investigateur.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testValidate() {
        final Investigateur investigateur = new Investigateur();
        final GenericService<Investigateur> mockedService = Mockito.mock(GenericService.class);
        final SortedSet<Service> services = new TreeSet<Service>(new BeanWithNomComparator());
        final Service service = new Service();
        service.setNom("service");
        services.add(service);
        investigateur.setServices(services);

        try {
            this.validator.validate(investigateur, mockedService);
        } catch (final ValidationException e) {
            Assert.fail("Exception not expected");
        }
    }

    /**
     * Méthode en charge de tester la validation KO de la sauvegarde d'un
     * investigateur.
     */
    @Test(expected = ValidationException.class)
    public void testValidateKO() {
        final Investigateur investigateur = new Investigateur();
        final GenericService<Investigateur> mockedService = Mockito.mock(GenericService.class);
        this.validator.validate(investigateur, mockedService);
    }

}

package fr.pharma.eclipse.component.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.acteur.ArcInvestigateur;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.service.localisation.ServiceService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test de la classe ServiceManager.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ServiceManagerTest extends AbstractEclipseJUnitTest {

    /**
     * Manager.
     */
    private ServiceManager manager;

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
        this.manager = new ServiceManager(this.service);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.manager = null;
        this.service = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.service);
    }

    /**
     * Test de la méthode findPersonnes().
     */
    @Test
    public void findPersonnes() {
        final Service service = new Service();
        final ArcInvestigateur arc = new ArcInvestigateur();
        arc.setNom("nom21");
        service.getArcInvestigateurs().add(arc);
        final Investigateur investigateur = new Investigateur();
        investigateur.setNom("nom");
        service.getInvestigateurs().add(investigateur);
        this.manager.setBean(service);
        final List<Personne> personnes = this.manager.findPersonnes();
        Assert.assertEquals(2, personnes.size());
    }

    /**
     * Test des getter et setter sur Personne.
     */
    @Test
    public void testGetSetPersonnes() {
        this.manager.setPersonnes(new ArrayList<Personne>());
        Assert.assertNotNull(this.manager.getPersonnes());
    }

}

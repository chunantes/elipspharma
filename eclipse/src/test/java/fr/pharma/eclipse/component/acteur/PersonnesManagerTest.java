package fr.pharma.eclipse.component.acteur;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.domain.criteria.acteur.PersonneSearchCriteria;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.ArcInvestigateur;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.redirect.RedirectHandler;
import fr.pharma.eclipse.service.acteur.PersonneService;

/**
 * Classe en charge de tester le manager de Personne.
 
 * @version $Revision$ $Date$
 */
public class PersonnesManagerTest
{

    /**
     * PersonnesManager à tester.
     */
    private PersonnesManager personnesManager;

    /**
     * Critère de recherche de personne.
     */
    private PersonneSearchCriteria criteria;

    /**
     * Mock du handler de redirection.
     */
    private RedirectHandler mockRedirect;

    /**
     * Service de gestion des arcInvestigateurs mocké.
     */
    private PersonneService<ArcInvestigateur> arcInvestServiceMock;

    /**
     * Service de gestion des investigateurs mocké.
     */
    private PersonneService<Investigateur> investServiceMock;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init()
    {
        this.criteria = new PersonneSearchCriteria();
        this.personnesManager = new PersonnesManager(this.criteria);
        this.mockRedirect = Mockito.mock(RedirectHandler.class);
        this.arcInvestServiceMock = Mockito.mock(PersonneService.class);
        this.investServiceMock = Mockito.mock(PersonneService.class);
        this.personnesManager.setArcInvestService(this.arcInvestServiceMock);
        this.personnesManager.setInvestService(this.investServiceMock);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end()
    {
        this.criteria = null;
        this.personnesManager = null;
        this.mockRedirect = null;
        this.arcInvestServiceMock = null;
        this.investServiceMock = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.criteria);
        Assert.assertNotNull(this.personnesManager);
        Assert.assertNotNull(this.mockRedirect);
        Assert.assertNotNull(this.arcInvestServiceMock);
        Assert.assertNotNull(this.investServiceMock);
    }

    /**
     * Méthode en charge de tester le champ de stockage de profilAjout.
     */
    @Test
    public void testProfilAjout()
    {
        this.personnesManager.setProfilAjout(TypePersonne.PHARMACIEN);
        Assert.assertEquals(TypePersonne.PHARMACIEN,
                            this.personnesManager.getProfilAjout());
    }

    /**
     * Méthode en charge de tester la méthode isComplete sur les informations obligatoires d'une
     * personne.
     */
    @Test
    public void testIsCompletePharmacien()
    {
        final Personne personne = new Pharmacien();
        personne.setType(TypePersonne.PHARMACIEN);
        Assert.assertTrue(this.personnesManager.isComplete(personne));
    }

    /**
     * Méthode en charge de tester la méthode isComplete sur les informations obligatoires d'une
     * personne.
     */
    @Test
    public void testIsCompleteArcInvestigateurOK()
    {
        final ArcInvestigateur personne = Mockito.mock(ArcInvestigateur.class);
        Mockito.when(personne.getType()).thenReturn(TypePersonne.ARC_INVESTIGATEUR);
        Mockito.when(personne.getNom()).thenReturn("nom");
        final SortedSet<Service> services = new TreeSet<Service>(new BeanWithNomComparator());
        final Service service = new Service();
        service.setNom("service");
        services.add(service);
        Mockito.when(personne.getServices()).thenReturn(services);
        Mockito.when(this.arcInvestServiceMock.reattach(personne)).thenReturn(personne);
        Assert.assertTrue(this.personnesManager.isComplete(personne));
    }

    /**
     * Méthode en charge de tester la méthode isComplete sur les informations obligatoires d'une
     * personne.
     */
    @Test
    public void testIsCompleteArcInvestigateurKONom()
    {
        final ArcInvestigateur personne = Mockito.mock(ArcInvestigateur.class);
        Mockito.when(personne.getType()).thenReturn(TypePersonne.ARC_INVESTIGATEUR);
        Mockito.when(personne.getNom()).thenReturn(null);
        final SortedSet<Service> services = new TreeSet<Service>(new BeanWithNomComparator());
        final Service service = new Service();
        service.setNom("service");
        services.add(service);
        Mockito.when(personne.getServices()).thenReturn(services);
        Mockito.when(this.arcInvestServiceMock.reattach(personne)).thenReturn(personne);
        Assert.assertFalse(this.personnesManager.isComplete(personne));
    }

    /**
     * Méthode en charge de tester la méthode isComplete sur les informations obligatoires d'une
     * personne.
     */
    @Test
    public void testIsCompleteArcInvestigateurKOServices()
    {
        final ArcInvestigateur personne = Mockito.mock(ArcInvestigateur.class);
        Mockito.when(personne.getType()).thenReturn(TypePersonne.ARC_INVESTIGATEUR);
        Mockito.when(personne.getNom()).thenReturn("nom");
        final SortedSet<Service> services = new TreeSet<Service>(new BeanWithNomComparator());
        Mockito.when(personne.getServices()).thenReturn(services);
        Mockito.when(this.arcInvestServiceMock.reattach(personne)).thenReturn(personne);
        Assert.assertFalse(this.personnesManager.isComplete(personne));
    }

    /**
     * Méthode en charge de tester la méthode isComplete sur les informations obligatoires d'une
     * personne.
     */
    @Test
    public void testIsCompleteArcInvestigateurKOAll()
    {
        final ArcInvestigateur personne = Mockito.mock(ArcInvestigateur.class);
        Mockito.when(personne.getType()).thenReturn(TypePersonne.ARC_INVESTIGATEUR);
        Mockito.when(personne.getNom()).thenReturn(null);
        final SortedSet<Service> services = new TreeSet<Service>(new BeanWithNomComparator());
        Mockito.when(personne.getServices()).thenReturn(services);
        Mockito.when(this.arcInvestServiceMock.reattach(personne)).thenReturn(personne);
        Assert.assertFalse(this.personnesManager.isComplete(personne));
    }

    /**
     * Méthode en charge de tester la méthode isComplete sur les informations obligatoires d'une
     * personne.
     */
    @Test
    public void testIsCompleteInvestigateurOK()
    {
        final Investigateur personne = Mockito.mock(Investigateur.class);
        Mockito.when(personne.getType()).thenReturn(TypePersonne.INVESTIGATEUR);
        Mockito.when(personne.getNom()).thenReturn("nom");
        final SortedSet<Service> services = new TreeSet<Service>(new BeanWithNomComparator());
        final Service service = new Service();
        service.setNom("service");
        services.add(service);
        Mockito.when(personne.getServices()).thenReturn(services);
        Mockito.when(this.investServiceMock.reattach(personne)).thenReturn(personne);
        Assert.assertTrue(this.personnesManager.isComplete(personne));
    }

    /**
     * Méthode en charge de tester la méthode isComplete sur les informations obligatoires d'une
     * personne.
     */
    @Test
    public void testIsCompleteInvestigateurKONom()
    {
        final Investigateur personne = Mockito.mock(Investigateur.class);
        Mockito.when(personne.getType()).thenReturn(TypePersonne.INVESTIGATEUR);
        Mockito.when(personne.getNom()).thenReturn(null);
        final SortedSet<Service> services = new TreeSet<Service>(new BeanWithNomComparator());
        final Service service = new Service();
        service.setNom("service");
        services.add(service);
        Mockito.when(personne.getServices()).thenReturn(services);
        Mockito.when(this.investServiceMock.reattach(personne)).thenReturn(personne);
        Assert.assertFalse(this.personnesManager.isComplete(personne));
    }

    /**
     * Méthode en charge de tester la méthode isComplete sur les informations obligatoires d'une
     * personne.
     */
    @Test
    public void testIsCompleteInvestigateurKOServices()
    {
        final Investigateur personne = Mockito.mock(Investigateur.class);
        Mockito.when(personne.getType()).thenReturn(TypePersonne.INVESTIGATEUR);
        Mockito.when(personne.getNom()).thenReturn("nom");
        final SortedSet<Service> services = new TreeSet<Service>(new BeanWithNomComparator());
        Mockito.when(personne.getServices()).thenReturn(services);
        Mockito.when(this.investServiceMock.reattach(personne)).thenReturn(personne);
        Assert.assertFalse(this.personnesManager.isComplete(personne));
    }

    /**
     * Méthode en charge de tester la méthode isComplete sur les informations obligatoires d'une
     * personne.
     */
    @Test
    public void testIsCompleteInvestigateurKOAll()
    {
        final Investigateur personne = Mockito.mock(Investigateur.class);
        Mockito.when(personne.getType()).thenReturn(TypePersonne.INVESTIGATEUR);
        Mockito.when(personne.getNom()).thenReturn(null);
        final SortedSet<Service> services = new TreeSet<Service>(new BeanWithNomComparator());
        Mockito.when(personne.getServices()).thenReturn(services);
        Mockito.when(this.investServiceMock.reattach(personne)).thenReturn(personne);
        Assert.assertFalse(this.personnesManager.isComplete(personne));
    }

}

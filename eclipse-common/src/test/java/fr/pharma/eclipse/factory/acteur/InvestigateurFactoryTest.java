package fr.pharma.eclipse.factory.acteur;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.sir.PersonneSir;
import fr.pharma.eclipse.service.sir.GenericSirService;

/**
 * Test de la fabrique de Investigateur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class InvestigateurFactoryTest {
    /**
     * Fabrique testée.
     */
    private PersonneFactory<Investigateur> factory;

    /**
     * Factory Spring mockée.
     */
    private BeanFactory mockedBeanFactory;

    /**
     * Service de personne SIR mocké.
     */
    private GenericSirService<PersonneSir> mockPersonneSirService;

    /**
     * Méthode d'initialisation.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.mockedBeanFactory = Mockito.mock(BeanFactory.class);
        this.factory = new PersonneFactory<Investigateur>(Investigateur.class, TypePersonne.INVESTIGATEUR);
        this.factory.setBeanFactory(this.mockedBeanFactory);
        this.mockPersonneSirService = Mockito.mock(GenericSirService.class);
        this.factory.setPersonneSirService(this.mockPersonneSirService);
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void end() {
        this.factory = null;
        this.mockedBeanFactory = null;
        this.mockPersonneSirService = null;
    }

    /**
     * Test de la méthode getInitializedObject.
     */
    @Test
    public void testGetInitializedObject() {
        final Investigateur expectedInvestigateur = Mockito.mock(Investigateur.class);
        final String expectedClassName = Investigateur.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(expectedInvestigateur);

        final Investigateur created = this.factory.getInitializedObject();
        Mockito.verify(this.mockedBeanFactory).getBean(expectedClassName);
        Assert.assertEquals(expectedInvestigateur, created);
        Mockito.verify(expectedInvestigateur).setType(TypePersonne.INVESTIGATEUR);
    }

    /**
     * Test de la méthode getInitializedObject à partir d'un identifiant de
     * personne SIR.
     */
    @Test
    public void testGetInitializedObjectFromSirWithIdNull() {
        final Investigateur investigateur = new Investigateur();
        final String expectedClassName = Investigateur.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(investigateur);
        this.factory.getInitializedObject(null);
        Assert.assertNull(investigateur.getNom());
        Assert.assertNull(investigateur.getPrenom());
        Assert.assertNull(investigateur.getLogin());
        Assert.assertNull(investigateur.getMail());
    }

    /**
     * Test de la méthode getInitializedObject à partir d'un identifiant de
     * personne SIR.
     */
    @Test
    public void testGetInitializedObjectFromSirWithId() {
        final PersonneSir personneSir = new PersonneSir();
        final Integer idPersonneSir = 1;
        personneSir.setId(idPersonneSir);
        personneSir.setLogin("login");
        personneSir.setNom("nom");
        personneSir.setPrenom("prenom");
        personneSir.setMail("mail");
        final Investigateur investigateur = new Investigateur();
        final String expectedClassName = Investigateur.class.getSimpleName();

        Mockito.when(this.mockPersonneSirService.get(idPersonneSir)).thenReturn(personneSir);
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(investigateur);

        this.factory.getInitializedObject(idPersonneSir);
        Assert.assertEquals(personneSir.getNom(), investigateur.getNom());
        Assert.assertEquals(personneSir.getPrenom(), investigateur.getPrenom());
        Assert.assertEquals(personneSir.getLogin(), investigateur.getLogin());
        Assert.assertEquals(personneSir.getMail(), investigateur.getMail());
    }

}

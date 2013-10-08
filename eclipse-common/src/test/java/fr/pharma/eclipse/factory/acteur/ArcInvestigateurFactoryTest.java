package fr.pharma.eclipse.factory.acteur;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.ArcInvestigateur;
import fr.pharma.eclipse.domain.model.sir.PersonneSir;
import fr.pharma.eclipse.service.sir.GenericSirService;

/**
 * Test de la fabrique de ArcInvestigateur.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ArcInvestigateurFactoryTest {
    /**
     * Fabrique testée.
     */
    private PersonneFactory<ArcInvestigateur> factory;

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
        this.factory = new PersonneFactory<ArcInvestigateur>(ArcInvestigateur.class, TypePersonne.ARC_INVESTIGATEUR);
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
        final ArcInvestigateur expectedArcInvestigateur = Mockito.mock(ArcInvestigateur.class);
        final String expectedClassName = ArcInvestigateur.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(expectedArcInvestigateur);

        final ArcInvestigateur created = this.factory.getInitializedObject();
        Mockito.verify(this.mockedBeanFactory).getBean(expectedClassName);
        Assert.assertEquals(expectedArcInvestigateur, created);
        Mockito.verify(expectedArcInvestigateur).setType(TypePersonne.ARC_INVESTIGATEUR);
    }

    /**
     * Test de la méthode getInitializedObject à partir d'un identifiant de
     * personne SIR.
     */
    @Test
    public void testGetInitializedObjectFromSirWithIdNull() {
        final ArcInvestigateur arcInvestigateur = new ArcInvestigateur();
        final String expectedClassName = ArcInvestigateur.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(arcInvestigateur);
        this.factory.getInitializedObject(null);
        Assert.assertNull(arcInvestigateur.getNom());
        Assert.assertNull(arcInvestigateur.getPrenom());
        Assert.assertNull(arcInvestigateur.getLogin());
        Assert.assertNull(arcInvestigateur.getMail());
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
        final ArcInvestigateur arcInvestigateur = new ArcInvestigateur();
        final String expectedClassName = ArcInvestigateur.class.getSimpleName();

        Mockito.when(this.mockPersonneSirService.get(idPersonneSir)).thenReturn(personneSir);
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(arcInvestigateur);

        this.factory.getInitializedObject(idPersonneSir);
        Assert.assertEquals(personneSir.getNom(), arcInvestigateur.getNom());
        Assert.assertEquals(personneSir.getPrenom(), arcInvestigateur.getPrenom());
        Assert.assertEquals(personneSir.getLogin(), arcInvestigateur.getLogin());
        Assert.assertEquals(personneSir.getMail(), arcInvestigateur.getMail());
    }

}

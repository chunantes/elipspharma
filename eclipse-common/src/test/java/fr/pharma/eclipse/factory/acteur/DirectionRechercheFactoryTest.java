package fr.pharma.eclipse.factory.acteur;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.DirectionRecherche;
import fr.pharma.eclipse.domain.model.sir.PersonneSir;
import fr.pharma.eclipse.service.sir.GenericSirService;

/**
 * Test de la fabrique de DirectionRecherche.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DirectionRechercheFactoryTest {
    /**
     * Fabrique testée.
     */
    private PersonneFactory<DirectionRecherche> factory;

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
        this.factory = new PersonneFactory<DirectionRecherche>(DirectionRecherche.class, TypePersonne.DIRECTION_RECHERCHE);
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
        final DirectionRecherche expectedDirectionRecherche = Mockito.mock(DirectionRecherche.class);
        final String expectedClassName = DirectionRecherche.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(expectedDirectionRecherche);

        final DirectionRecherche created = this.factory.getInitializedObject();
        Mockito.verify(this.mockedBeanFactory).getBean(expectedClassName);
        Assert.assertEquals(expectedDirectionRecherche, created);
        Mockito.verify(expectedDirectionRecherche).setType(TypePersonne.DIRECTION_RECHERCHE);
    }

    /**
     * Test de la méthode getInitializedObject à partir d'un identifiant de
     * personne SIR.
     */
    @Test
    public void testGetInitializedObjectFromSirWithIdNull() {
        final DirectionRecherche directionRecherche = new DirectionRecherche();
        final String expectedClassName = DirectionRecherche.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(directionRecherche);
        this.factory.getInitializedObject(null);
        Assert.assertNull(directionRecherche.getNom());
        Assert.assertNull(directionRecherche.getPrenom());
        Assert.assertNull(directionRecherche.getLogin());
        Assert.assertNull(directionRecherche.getMail());
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
        final DirectionRecherche directionRecherche = new DirectionRecherche();
        final String expectedClassName = DirectionRecherche.class.getSimpleName();

        Mockito.when(this.mockPersonneSirService.get(idPersonneSir)).thenReturn(personneSir);
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(directionRecherche);

        this.factory.getInitializedObject(idPersonneSir);
        Assert.assertEquals(personneSir.getNom(), directionRecherche.getNom());
        Assert.assertEquals(personneSir.getPrenom(), directionRecherche.getPrenom());
        Assert.assertEquals(personneSir.getLogin(), directionRecherche.getLogin());
        Assert.assertEquals(personneSir.getMail(), directionRecherche.getMail());
    }

}

package fr.pharma.eclipse.factory.acteur;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.sir.PersonneSir;
import fr.pharma.eclipse.service.sir.GenericSirService;

/**
 * Test de la fabrique de Pharmacien.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PharmacienFactoryTest {
    /**
     * Fabrique testée.
     */
    private PersonneFactory<Pharmacien> factory;

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
        this.factory = new PersonneFactory<Pharmacien>(Pharmacien.class, TypePersonne.PHARMACIEN);
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
        final Pharmacien expectedPharmacien = Mockito.mock(Pharmacien.class);
        final String expectedClassName = Pharmacien.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(expectedPharmacien);

        final Pharmacien created = this.factory.getInitializedObject();
        Mockito.verify(this.mockedBeanFactory).getBean(expectedClassName);
        Assert.assertEquals(expectedPharmacien, created);
        Mockito.verify(expectedPharmacien).setType(TypePersonne.PHARMACIEN);
    }

    /**
     * Test de la méthode getInitializedObject à partir d'un identifiant de
     * personne SIR.
     */
    @Test
    public void testGetInitializedObjectFromSirWithIdNull() {
        final Pharmacien pharmacien = new Pharmacien();
        final String expectedClassName = Pharmacien.class.getSimpleName();
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(pharmacien);
        this.factory.getInitializedObject(null);
        Assert.assertNull(pharmacien.getNom());
        Assert.assertNull(pharmacien.getPrenom());
        Assert.assertNull(pharmacien.getLogin());
        Assert.assertNull(pharmacien.getMail());
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
        final Pharmacien pharmacien = new Pharmacien();
        final String expectedClassName = Pharmacien.class.getSimpleName();

        Mockito.when(this.mockPersonneSirService.get(idPersonneSir)).thenReturn(personneSir);
        Mockito.when(this.mockedBeanFactory.getBean(expectedClassName)).thenReturn(pharmacien);

        this.factory.getInitializedObject(idPersonneSir);
        Assert.assertEquals(personneSir.getNom(), pharmacien.getNom());
        Assert.assertEquals(personneSir.getPrenom(), pharmacien.getPrenom());
        Assert.assertEquals(personneSir.getLogin(), pharmacien.getLogin());
        Assert.assertEquals(personneSir.getMail(), pharmacien.getMail());
    }

}

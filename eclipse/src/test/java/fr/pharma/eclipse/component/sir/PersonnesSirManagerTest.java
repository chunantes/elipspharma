package fr.pharma.eclipse.component.sir;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.DataModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.component.acteur.PersonnesManager;
import fr.pharma.eclipse.domain.criteria.sir.PersonneSirSearchCriteria;
import fr.pharma.eclipse.domain.model.sir.PersonneSir;
import fr.pharma.eclipse.redirect.RedirectHandler;

/**
 * Classe en charge de tester le manager de personnes SIR.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PersonnesSirManagerTest {

    /**
     * PersonnesSirManager à tester.
     */
    private PersonnesSirManager personnesSirManager;

    /**
     * Critère de recherche de personne SIR.
     */
    private PersonneSirSearchCriteria criteria;

    /**
     * Mock du manager de gestion de personnes.
     */
    private PersonnesManager mockPersonnesManager;

    /**
     * Mock du handler de redirection.
     */
    private RedirectHandler mockRedirect;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.criteria = new PersonneSirSearchCriteria();
        this.personnesSirManager = new PersonnesSirManager(this.criteria);
        final PersonneSir personneSir1 = new PersonneSir();
        personneSir1.setId(1);
        personneSir1.setNom("nom1");
        final PersonneSir personneSir2 = new PersonneSir();
        personneSir2.setId(2);
        personneSir2.setNom("nom2");
        final List<PersonneSir> personnesSir = new ArrayList<PersonneSir>();
        personnesSir.add(personneSir1);
        personnesSir.add(personneSir2);
        this.personnesSirManager.setBeans(personnesSir);
        this.mockRedirect = Mockito.mock(RedirectHandler.class);
        this.mockPersonnesManager = Mockito.mock(PersonnesManager.class);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.criteria = null;
        this.personnesSirManager = null;
        this.mockPersonnesManager = null;
        this.mockRedirect = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.personnesSirManager);
        Assert.assertNotNull(this.criteria);
        Assert.assertNotNull(this.mockPersonnesManager);
        Assert.assertNotNull(this.mockRedirect);
        Assert.assertNotNull(this.personnesSirManager.getSearchCriteria());
        Assert.assertNotNull(this.personnesSirManager.getBeans());
    }

    /**
     * Méthode en charge de tester la récupération des beans sous forme de
     * Model.
     */
    @Test
    public void testGetModel() {
        final DataModel<PersonneSir> result = this.personnesSirManager.getModel();
        Assert.assertEquals(result.getRowCount(), this.personnesSirManager.getBeans().size());
    }

    /**
     * Méthode en charge de tester la récupération du bean selected.
     */
    @Test
    public void testBeanSelected() {
        this.personnesSirManager.setBeanSelected(this.personnesSirManager.getBeans().get(0));
        Assert.assertEquals(this.personnesSirManager.getBeans().get(0), this.personnesSirManager.getBeanSelected());
    }

}

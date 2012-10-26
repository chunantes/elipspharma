package fr.pharma.eclipse.component.acteur;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.TypePharmacien;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.service.acteur.PersonneService;

/**
 * Classe en charge de tester le manager de Pharmacien.
 
 * @version $Revision$ $Date$
 */
public class PharmacienManagerTest
{

    /**
     * PharmacienManager à tester.
     */
    private PharmacienManager pharmacienManager;

    /**
     * Service de gestion des pharmaciens mocké.
     */
    private PersonneService<Pharmacien> mockPharmacienService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init()
    {
        this.mockPharmacienService = Mockito.mock(PersonneService.class);
        this.pharmacienManager = new PharmacienManager(this.mockPharmacienService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end()
    {
        this.mockPharmacienService = null;
        this.pharmacienManager = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.mockPharmacienService);
        Assert.assertNotNull(this.pharmacienManager);
    }

    /**
     * Méthode en charge de tester le listener appelé sur le changement de type de pharmacien.
     */
    @Test
    public void testHandleSelectCategPharmacienTitulaire()
    {
        final Pharmacien pharmacien = new Pharmacien();
        pharmacien.setIsAdmin(Boolean.TRUE);

        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        Mockito.when(select.getLocalValue()).thenReturn(TypePharmacien.TITULAIRE);

        this.pharmacienManager.setBean(pharmacien);
        this.pharmacienManager.handleSelectCategPharmacien(event);

        Assert.assertTrue(this.pharmacienManager.getBean().getIsAdmin());
    }

    /**
     * Méthode en charge de tester le listener appelé sur le changement de type de pharmacien.
     */
    @Test
    public void testHandleSelectCategPharmacienNotTitulaire()
    {
        final Pharmacien pharmacien = new Pharmacien();
        pharmacien.setIsAdmin(Boolean.TRUE);

        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        Mockito.when(select.getLocalValue()).thenReturn(TypePharmacien.EXTERNE);

        this.pharmacienManager.setBean(pharmacien);
        this.pharmacienManager.handleSelectCategPharmacien(event);

        Assert.assertFalse(this.pharmacienManager.getBean().getIsAdmin());
    }

}

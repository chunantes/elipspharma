package fr.pharma.eclipse.component.evenement;

import javax.faces.event.ValueChangeEvent;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.evenement.ResultatVisite;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.enums.evenement.TypeVisite;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.service.evenement.EvenementService;

/**
 * Classe en charge de tester le manager de gestion d'un événement.
 
 * @version $Revision$ $Date$
 */
public class EvenementManagerTest
{
    /**
     * EvenementManager à tester.
     */
    private EvenementManager manager;

    /**
     * Service de gestion d'un événement mocké.
     */
    private EvenementService mockEvenementService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init()
    {
        this.mockEvenementService = Mockito.mock(EvenementService.class);
        this.manager = new EvenementManager(this.mockEvenementService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end()
    {
        this.manager = null;
        this.mockEvenementService = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInitData()
    {
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.mockEvenementService);
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM lors de la sélection du type
     * d'événement.
     */
    @Test
    public void testHandleSelectTypeEvenementVisite()
    {
        final ValueChangeEvent event = Mockito.mock(ValueChangeEvent.class);
        final TypeEvenement typeEvenement = TypeEvenement.VISITE;
        Mockito.when(event.getNewValue()).thenReturn(typeEvenement);

        final Evenement evenement = new Evenement();
        this.manager.setBean(evenement);

        this.manager.getBean().setTypeVisite(TypeVisite.AUDIT_EXTERNE);
        this.manager.getBean().setResultatVisite(ResultatVisite.EFFECTUE);

        this.manager.handleSelectTypeEvenement(event);
        Assert.assertEquals(TypeVisite.AUDIT_EXTERNE,
                            this.manager.getBean().getTypeVisite());
        Assert.assertEquals(ResultatVisite.EFFECTUE,
                            this.manager.getBean().getResultatVisite());
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM lors de la sélection du type
     * d'événement.
     */
    @Test
    public void testHandleSelectTypeEvenementNotVisite()
    {
        final ValueChangeEvent event = Mockito.mock(ValueChangeEvent.class);
        final TypeEvenement typeEvenement = TypeEvenement.TACHE;
        Mockito.when(event.getNewValue()).thenReturn(typeEvenement);

        final Evenement evenement = new Evenement();
        this.manager.setBean(evenement);

        this.manager.getBean().setTypeVisite(TypeVisite.AUDIT_EXTERNE);
        this.manager.getBean().setResultatVisite(ResultatVisite.EFFECTUE);

        this.manager.handleSelectTypeEvenement(event);
        Assert.assertNull(this.manager.getBean().getTypeVisite());
        Assert.assertNull(this.manager.getBean().getResultatVisite());
    }

    /**
     * Test de la méthode handleCheckJournee.
     */
    @Test
    public void testHandleCheckJournee()
    {
        final ValueChangeEvent event = Mockito.mock(ValueChangeEvent.class);
        Mockito.when(event.getNewValue()).thenReturn(false);
        this.manager.setBean(new Evenement());
        this.manager.handleCheckJournee(event);
        Assert.assertFalse(this.manager.getBean().getJournee());
    }

}

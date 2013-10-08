package fr.pharma.eclipse.component.evenement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.primefaces.event.ScheduleEntrySelectEvent;

import fr.pharma.eclipse.domain.criteria.evenement.EvenementSearchCriteria;
import fr.pharma.eclipse.domain.enums.evenement.ResultatVisite;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.enums.evenement.TypeVisite;
import fr.pharma.eclipse.domain.model.evenement.Evenement;

/**
 * Classe en charge de tester le manager de gestion des événements.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EvenementListManagerTest {
    /**
     * EvenementsManager à tester.
     */
    private EvenementListManager manager;

    /**
     * Critère de recherche mocké.
     */
    private EvenementSearchCriteria criteria;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.criteria = new EvenementSearchCriteria();
        this.manager = new EvenementListManager(this.criteria);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.manager = null;
        this.criteria = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInitData() {
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.criteria);
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM lors de la
     * sélection du type d'événement.
     */
    @Test
    public void testHandleSelectTypeEvenementVisite() {
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        final TypeEvenement typeEvenement = TypeEvenement.VISITE;
        Mockito.when(select.getLocalValue()).thenReturn(typeEvenement);

        this.criteria.setTypeVisite(TypeVisite.AUDIT_EXTERNE);
        this.criteria.setResultatVisite(ResultatVisite.EFFECTUE);

        this.manager.handleSelectTypeEvenement(event);
        Assert.assertEquals(TypeVisite.AUDIT_EXTERNE, this.criteria.getTypeVisite());
        Assert.assertEquals(ResultatVisite.EFFECTUE, this.criteria.getResultatVisite());
    }

    /**
     * Méthode en charge de tester la méthode appelée via l'IHM lors de la
     * sélection du type d'événement.
     */
    @Test
    public void testHandleSelectTypeEvenementNotVisite() {
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        final TypeEvenement typeEvenement = TypeEvenement.TACHE;
        Mockito.when(select.getLocalValue()).thenReturn(typeEvenement);

        this.criteria.setTypeVisite(TypeVisite.AUDIT_EXTERNE);
        this.criteria.setResultatVisite(ResultatVisite.EFFECTUE);

        this.manager.handleSelectTypeEvenement(event);
        Assert.assertNull(this.criteria.getTypeVisite());
        Assert.assertNull(this.criteria.getResultatVisite());
    }

    /**
     * Test de la méthode initSchedule().
     */
    @Test
    public void testInitSchedule() {
        final List<Evenement> evts = new ArrayList<Evenement>();
        final Evenement evt = new Evenement();
        evt.setDateDebut(Calendar.getInstance());
        evt.setDateFin(Calendar.getInstance());
        evt.setTypeEvenement(TypeEvenement.TACHE);

        evts.add(evt);
        this.manager.setBeans(evts);
        this.manager.initSchedule();
        Assert.assertEquals(1, this.manager.getEventModel().getEventCount());
    }

    /**
     * Test de la méthode onEventSelect().
     */
    @Test
    public void testOnEventSelect() {
        final EclipseEvent eve = Mockito.mock(EclipseEvent.class);
        Mockito.when(eve.getEvenement()).thenReturn(new Evenement());
        final ScheduleEntrySelectEvent selectEvent = Mockito.mock(ScheduleEntrySelectEvent.class);
        Mockito.when(selectEvent.getScheduleEvent()).thenReturn(eve);
        this.manager.onEventSelect(selectEvent);
        Assert.assertNotNull(this.manager.getBeanSelected());
    }

    /**
     * Test getter setter event.
     */
    @Test
    public void getSetEvent() {
        final Evenement evenement = new Evenement();
        evenement.setDateDebut(Calendar.getInstance());
        evenement.setDateFin(Calendar.getInstance());
        evenement.setTypeEvenement(TypeEvenement.TACHE);
        this.manager.setEvent(new EclipseEvent(evenement));
        Assert.assertNotNull(this.manager.getEvent());
    }
}

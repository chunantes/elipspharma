package fr.pharma.eclipse.component.evenement;

import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import fr.pharma.eclipse.component.BeanListManager;
import fr.pharma.eclipse.domain.criteria.evenement.EvenementSearchCriteria;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.model.evenement.Evenement;

/**
 * Manager de gestion des événements.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EvenementListManager extends BeanListManager<Evenement> {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -836495506160229614L;

    /**
     * Critère de recherche.
     */
    private final EvenementSearchCriteria criteria;

    /**
     * Event Model pour le composant scheduler.
     */
    private ScheduleModel eventModel;

    /**
     * Event.
     */
    private EclipseEvent event;

    /**
     * Constructeur.
     * @param searchCriteria Critère de recherche.
     */
    public EvenementListManager(final EvenementSearchCriteria searchCriteria) {
        super(searchCriteria);
        this.criteria = searchCriteria;
        this.setEventModel(new DefaultScheduleModel());
    }

    /**
     * Méthode appelée via la couche IHM lorsqu'un type d'événement est
     * sélectionné.
     * @param event Evénement remonté via la couche IHM.
     */
    public void handleSelectTypeEvenement(final AjaxBehaviorEvent event) {
        // Récupération du type d'événement sélectionné
        final HtmlSelectOneMenu select = (HtmlSelectOneMenu) event.getSource();
        final TypeEvenement typeEvenement = (TypeEvenement) select.getLocalValue();
        this.criteria.setTypeEvenement(typeEvenement);

        // Si le type d'événement est différent de visite
        if (!TypeEvenement.VISITE.equals(typeEvenement)) {
            this.criteria.setTypeVisite(null);
            this.criteria.setResultatVisite(null);
        }
    }

    /**
     * Méthode en charge d'ajouter les events sur l'objet ScheduleModel.
     */
    public void initSchedule() {
        this.eventModel.clear();
        for (final Evenement evenement : this.getBeans()) {
            this.eventModel.addEvent(new EclipseEvent(evenement));
        }
    }

    /**
     * Listener lors de la sélection d'un evenement.
     * @param selectEvent Evenement.
     */
    public void onEventSelect(final ScheduleEntrySelectEvent selectEvent) {
        this.event = (EclipseEvent) selectEvent.getScheduleEvent();
        this.setBeanSelected(this.event.getEvenement());
    }

    /**
     * Getter sur eventModel.
     * @return Retourne le eventModel.
     */
    public ScheduleModel getEventModel() {
        return this.eventModel;
    }

    /**
     * Setter pour eventModel.
     * @param eventModel le eventModel à écrire.
     */
    public void setEventModel(final ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    /**
     * Getter sur event.
     * @return Retourne le event.
     */
    public EclipseEvent getEvent() {
        return this.event;
    }

    /**
     * Setter pour event.
     * @param event le event à écrire.
     */
    public void setEvent(final EclipseEvent event) {
        this.event = event;
    }

}

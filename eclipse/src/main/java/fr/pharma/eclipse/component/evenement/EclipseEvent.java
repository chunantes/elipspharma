package fr.pharma.eclipse.component.evenement;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DefaultScheduleEvent;

import fr.pharma.eclipse.domain.model.evenement.Evenement;

/**
 * Description de la classe.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EclipseEvent extends DefaultScheduleEvent {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -4545750693169995282L;

    /**
     * Evenement.
     */
    private Evenement evenement;

    /**
     * Constructeur.
     * @param dateBut Date début.
     * @param dateFin Date fin.
     * @param isForDay Pour la journée.
     * @param evenement Evenement.
     */
    public EclipseEvent(final Evenement evenement) {
        super(evenement.getLibelle(), evenement.getDateDebut().getTime(), evenement.getDateFin().getTime(), BooleanUtils.isTrue(evenement.getJournee()));
        this.setStyleClass(StringUtils.lowerCase(evenement.getTypeEvenement().getClasseCSS()));
        this.evenement = evenement;
    }

    /**
     * Getter sur evenement.
     * @return Retourne le evenement.
     */
    public Evenement getEvenement() {
        return this.evenement;
    }

    /**
     * Setter pour evenement.
     * @param evenement le evenement à écrire.
     */
    public void setEvenement(final Evenement evenement) {
        this.evenement = evenement;
    }

}

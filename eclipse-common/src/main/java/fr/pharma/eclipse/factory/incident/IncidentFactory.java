package fr.pharma.eclipse.factory.incident;

import java.util.Calendar;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.incident.Incident;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Fabrique des objets Incident.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class IncidentFactory extends BeanObjectFactory<Incident> {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6744160660949866186L;

    /**
     * Constructeur.
     * @param bean Classe à instancier.
     */
    public IncidentFactory(final Class<Incident> bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Incident getInitializedObject() {
        final Incident incident = super.getInitializedObject();

        incident.setDate(Calendar.getInstance(EclipseConstants.LOCALE));

        return incident;
    }

    /**
     * Cette méthode retourne un incident initialisé avec un essai.
     * @param essai Essai.
     * @return L'incident.
     */
    public Incident getInitializedObject(final Essai essai) {
        final Incident incident = this.getInitializedObject();
        incident.setEssai(essai);
        return incident;
    }

}

package fr.pharma.eclipse.factory.patient;

import java.util.Calendar;

import fr.pharma.eclipse.domain.model.patient.HistoriquePatient;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Factory de HistoriquePatient
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class HistoriquePatientFactory extends BeanObjectFactory<HistoriquePatient> {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 1056336391281421691L;

    /**
     * Constructeur.
     * @param bean Classe.
     */
    public HistoriquePatientFactory(final Class<HistoriquePatient> bean) {
        super(bean);
    }

    /**
     * Méthode en charge de construire un HistoriquePatient.
     * @param patient Le patient.
     * @return L'objet HistoriquePatient.
     */
    public HistoriquePatient getInitializedObject(final Patient patient) {
        final HistoriquePatient historique = super.getInitializedObject();
        historique.setDate(Calendar.getInstance(EclipseConstants.LOCALE));
        historique.setPatient(patient);
        return historique;
    }

}

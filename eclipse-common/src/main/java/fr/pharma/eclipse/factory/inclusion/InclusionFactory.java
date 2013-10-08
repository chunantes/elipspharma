package fr.pharma.eclipse.factory.inclusion;

import java.util.Calendar;

import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Factory de Inclusion.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class InclusionFactory extends BeanObjectFactory<Inclusion> {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 1056336391281421691L;

    /**
     * Constructeur.
     * @param bean Classe.
     */
    public InclusionFactory(final Class<Inclusion> bean) {
        super(bean);
    }

    /**
     * Méthode en charge de construire une Inclusion.
     * @return L'objet Inclusion.
     */
    @Override
    public Inclusion getInitializedObject() {
        final Inclusion inclusion = super.getInitializedObject();
        inclusion.setActif(true);
        inclusion.setDateInclusion(Calendar.getInstance(EclipseConstants.LOCALE));
        return inclusion;
    }

}

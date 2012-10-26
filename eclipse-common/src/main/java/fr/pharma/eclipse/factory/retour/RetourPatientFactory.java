package fr.pharma.eclipse.factory.retour;

import java.util.Calendar;

import fr.pharma.eclipse.domain.enums.EtatRetour;
import fr.pharma.eclipse.domain.model.stock.RetourPatient;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Factory de Retour patient.
 
 * @version $Revision$ $Date$
 */
public class RetourPatientFactory
    extends BeanObjectFactory<RetourPatient>
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -3292240614641548997L;

    /**
     * Constructeur.
     * @param bean La classe.
     */
    public RetourPatientFactory(final Class<RetourPatient> bean)
    {
        super(bean);
    }

    /**
     * Méthode en charge de fournir un retour patient initialisé.
     * @return Le retour patient initialisée.
     */
    @Override
    public RetourPatient getInitializedObject()
    {
        final RetourPatient retour = super.getInitializedObject();
        retour.setEtat(EtatRetour.PRESENT);
        retour.setDateEtat(Calendar.getInstance(EclipseConstants.LOCALE));
        retour.setCommentaireEtat("");
        return retour;
    }
}

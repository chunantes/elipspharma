package fr.pharma.eclipse.factory.suivi;

import java.util.Calendar;

import org.springframework.security.core.context.SecurityContextHolder;

import fr.pharma.eclipse.domain.model.suivi.common.Suivi;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe de factory de bean concernant les suivis de modifications.
 * @param <SUIVI> Bean Objet Suivi de modification.
 
 * @version $Revision$ $Date$
 */
public class SuiviFactory<SUIVI extends Suivi>
    extends BeanObjectFactory<SUIVI>
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1525928416715040283L;

    /**
     * Constructeur.
     * @param suivi Classe de SUIVI.
     */
    public SuiviFactory(final Class<SUIVI> suivi)
    {
        super(suivi);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SUIVI getInitializedObject()
    {
        final SUIVI suivi = super.getInitializedObject();
        suivi.setDateMaj(Calendar.getInstance(EclipseConstants.LOCALE));
        suivi.setMajPar(SecurityContextHolder.getContext().getAuthentication().getName());
        return suivi;
    }

}

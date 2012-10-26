package fr.pharma.eclipse.converter;

import java.text.ParseException;
import java.util.Calendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.springframework.util.StringUtils;

import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe de converter pour les objets java Calendar capable d'interprêter le pattern mm/YYYY
 
 * @version $Revision$ $Date$
 */
public class CalendarMoisConverter
    extends CalendarConverter
{

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 3682756783199384595L;
    /**
     * {@inheritDoc}
     */
    @Override
    public Object getAsObject(final FacesContext context,
                              final UIComponent component,
                              final String value)
    {
        if (!StringUtils.hasText(value))
        {
            return null;
        }

        Calendar result = null;

        try
        {
            result = Utils.parseDate(value,
                                     EclipseConstants.PATTERN_SIMPLE_MOIS);
            result.set(Calendar.DAY_OF_MONTH,
                       result.getActualMaximum(Calendar.DAY_OF_MONTH));
            return Utils.parseDate(value,
                                   EclipseConstants.PATTERN_SIMPLE);
        }
        catch (final ParseException e)
        {
            return result;
        }
    }

}

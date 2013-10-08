package fr.pharma.eclipse.converter;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.util.StringUtils;

import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe de converter pour les objets java Calendar.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class CalendarConverter implements Converter, Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4299038554352968650L;

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getAsObject(final FacesContext context,
                              final UIComponent component,
                              final String value) {
        if (!StringUtils.hasText(value)) {
            return null;
        }

        try {
            return Utils.parseDate(value, EclipseConstants.PATTERN_SIMPLE);
        } catch (final ParseException e) {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAsString(final FacesContext context,
                              final UIComponent component,
                              final Object value) {
        if (value == null) {
            return null;
        }

        try {
            final Calendar cal = (Calendar) value;
            return Utils.formatDate(cal.getTime(), EclipseConstants.PATTERN_SIMPLE);
        } catch (final ClassCastException e) {
            return null;
        }
    }

}

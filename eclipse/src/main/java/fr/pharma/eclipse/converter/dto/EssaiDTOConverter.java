package fr.pharma.eclipse.converter.dto;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.service.essai.EssaiService;

/**
 * Classe de converter de EssaiDTO.
 * @author Netapsys
 * @version $Revision$ $Date$
 */

public class EssaiDTOConverter implements Converter {

    /**
     * Service associé au bean.
     */
    private EssaiService service;

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getAsObject(final FacesContext context,
                              final UIComponent component,
                              final String value) {
        if (StringUtils.isNotEmpty(value)) {
            try {
                return this.service.getEssaiDTO(Long.valueOf(value));
            } catch (final NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getAsString(final FacesContext context,
                              final UIComponent component,
                              final Object value) {
        if (value != null) {
            try {
                return value.toString();
            } catch (final ClassCastException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Setter pour service.
     * @param service Le service à écrire.
     */
    public void setService(final EssaiService service) {
        this.service = service;
    }

}

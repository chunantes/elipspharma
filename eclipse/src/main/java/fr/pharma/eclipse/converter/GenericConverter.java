package fr.pharma.eclipse.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Classe de converter générique.
 * @param <BEAN> Bean Objet Métier.
 
 * @version $Revision$ $Date$
 */

public class GenericConverter<BEAN extends BeanObject>
    implements Converter
{
    /**
     * Service associé au bean.
     */
    private final GenericService<BEAN> service;

    /**
     * Constructeur.
     * @param service Service de gestion du BEAN.
     */
    public GenericConverter(final GenericService<BEAN> service)
    {
        this.service = service;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getAsObject(final FacesContext context,
                              final UIComponent component,
                              final String value)
    {
        if (StringUtils.isNotEmpty(value))
        {
            try
            {
                return this.service.get(Long.valueOf(value));
            }
            catch (final NumberFormatException e)
            {
                return null;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public String getAsString(final FacesContext context,
                              final UIComponent component,
                              final Object value)
    {
        if (value != null)
        {
            try
            {
                final BEAN bean = (BEAN) value;
                return bean.getId().toString();
            }
            catch (final ClassCastException e)
            {
                return null;
            }
        }
        return null;
    }

}

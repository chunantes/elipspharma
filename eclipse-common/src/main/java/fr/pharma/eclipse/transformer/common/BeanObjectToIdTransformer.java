package fr.pharma.eclipse.transformer.common;

import org.apache.commons.collections.Transformer;

import fr.pharma.eclipse.domain.model.common.BeanObject;

/**
 * Transformer de BeanObject vers identifiant de BeanObject
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BeanObjectToIdTransformer implements Transformer {
    @Override
    public Object transform(final Object input) {
        return ((BeanObject) input).getId();
    }
}

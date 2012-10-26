package fr.pharma.eclipse.transformer.design;

import org.apache.commons.collections.Transformer;

import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Transformer générique. Retourne une propriété de l'objet.
 
 * @version $Revision$ $Date$
 */
public class GenericTransformer
    implements Transformer
{
    /**
     * Propriété.
     */
    private final String property;

    /**
     * Constructeur.
     * @param property La propriété à récupérer dans l'objet.
     */
    public GenericTransformer(final String property)
    {
        this.property = property;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object transform(final Object input)
    {
        return BeanTool.getPropriete(input,
                                     this.property);
    }

}

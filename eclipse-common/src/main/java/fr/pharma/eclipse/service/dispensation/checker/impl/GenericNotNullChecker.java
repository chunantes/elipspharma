package fr.pharma.eclipse.service.dispensation.checker.impl;

import java.io.Serializable;

import fr.pharma.eclipse.service.dispensation.checker.Checker;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Classe en charge de vérifier qu'un attribut n'est pas null.
 
 * @version $Revision$ $Date$
 */
public class GenericNotNullChecker<BEAN extends Object>
    implements Checker<BEAN>, Serializable
{
    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -1541105616432656776L;

    /**
     * Propriete à verifier.
     */
    private final String propriete;

    /**
     * Constructeur.
     * @param propriete Propriété.
     */
    public GenericNotNullChecker(final String propriete)
    {
        this.propriete = propriete;
    }

    /**
     * {@inheritDoc}
     */
    public boolean check(final BEAN bean)
    {
        return BeanTool.getPropriete(bean,
                                     this.propriete) != null;
    }

}

package fr.pharma.eclipse.dictionary.maker.common;

import java.io.Serializable;

import fr.pharma.eclipse.dictionary.maker.CriteriaMaker;

/**
 * Classe abstraite de base pour les artisans des critères de recherche.
 
 * @version $Revision$ $Date$
 */
public abstract class AbstractCriteriaMaker
    implements CriteriaMaker, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4110449095770174380L;

    /**
     * La classe du critère de recherche supporté.
     */
    @SuppressWarnings("unchecked")
    private final Class supportedClass;

    /**
     * Constructeur unique.
     * @param supportedCriteriaClass La classe du critère de recherche supporté.
     */
    @SuppressWarnings("unchecked")
    public AbstractCriteriaMaker(final Class supportedCriteriaClass)
    {
        this.supportedClass = supportedCriteriaClass;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public boolean supports(final Class clazz)
    {
        return this.supportedClass.isAssignableFrom(clazz);
    }

}

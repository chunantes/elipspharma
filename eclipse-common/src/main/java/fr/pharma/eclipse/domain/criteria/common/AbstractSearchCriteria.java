package fr.pharma.eclipse.domain.criteria.common;

/**
 * Classe de gestion des critères de recherche.
 
 * @version $Revision$ $Date$
 */
public abstract class AbstractSearchCriteria
    implements SearchCriteria
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2969514244109034967L;

    /**
     * Critère d'ordonancement.
     */
    private String activeOrder;

    /**
     * Critère de direction d'ordonnancement.
     */
    private boolean ascending = true;

    /**
     * Critère case sensitive sur l'ordonnancement.
     */
    private boolean caseSensitiveOrder = false;

    /**
     * {@inheritDoc}
     */
    public String getActiveOrder()
    {
        return this.activeOrder;
    }

    /**
     * {@inheritDoc}
     */
    public void setActiveOrder(final String activeOrder)
    {
        this.activeOrder = activeOrder;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isAscending()
    {
        return this.ascending;
    }

    /**
     * {@inheritDoc}
     */
    public void setAscending(final boolean ascending)
    {
        this.ascending = ascending;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isCaseSensitiveOrder()
    {
        return this.caseSensitiveOrder;
    }

    /**
     * {@inheritDoc}
     */
    public void setCaseSensitiveOrder(final boolean caseSensitiveOrder)
    {
        this.caseSensitiveOrder = caseSensitiveOrder;
    }

    /**
     * Méthode en charge de cleaner les attributs d'un critère de recherche.
     */
    public abstract void clear();

}

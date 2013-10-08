package fr.pharma.eclipse.domain.criteria.common;

/**
 * Classe de gestion des critères de recherche.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public abstract class AbstractSearchCriteria implements SearchCriteria {
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
    @Override
    public String getActiveOrder() {
        return this.activeOrder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setActiveOrder(final String activeOrder) {
        this.activeOrder = activeOrder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAscending() {
        return this.ascending;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setAscending(final boolean ascending) {
        this.ascending = ascending;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCaseSensitiveOrder() {
        return this.caseSensitiveOrder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCaseSensitiveOrder(final boolean caseSensitiveOrder) {
        this.caseSensitiveOrder = caseSensitiveOrder;
    }

    /**
     * Méthode en charge de cleaner les attributs d'un critère de recherche.
     */
    public abstract void clear();

}

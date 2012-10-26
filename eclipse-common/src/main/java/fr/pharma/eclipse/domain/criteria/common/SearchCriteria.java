package fr.pharma.eclipse.domain.criteria.common;

import java.io.Serializable;

/**
 * Classe de gestion des critères de recherche.
 
 * @version $Revision$ $Date$
 */
public interface SearchCriteria
    extends Serializable
{
    /**
     * Getter pour activeOrder.
     * @return le critère d'ordonancement.
     */
    String getActiveOrder();

    /**
     * Setter pour activeOrder.
     * @param activeOrder le critère d'ordonancement à définir.
     */
    void setActiveOrder(String activeOrder);

    /**
     * Getter pour ascending.
     * @return Retourne le ascending.
     */
    boolean isAscending();

    /**
     * Setter pour ascending.
     * @param ascending le ascending à écrire.
     */
    void setAscending(boolean ascending);

    /**
     * Récupération de caseSensitiveOrder.
     * @return Retourne le caseSensitiveOrder.
     */
    boolean isCaseSensitiveOrder();

    /**
     * Affectation du champ caseSensitiveOrder.
     * @param caseSensitiveOrder le caseSensitiveOrder à écrire.
     */
    void setCaseSensitiveOrder(final boolean caseSensitiveOrder);

}

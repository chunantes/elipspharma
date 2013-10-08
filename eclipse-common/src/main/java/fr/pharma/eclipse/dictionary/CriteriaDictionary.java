package fr.pharma.eclipse.dictionary;

import org.hibernate.Criteria;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;

/**
 * Interface de dictionnaire de critères de recherche.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public interface CriteriaDictionary {
    /**
     * Méthode d'application d'un critère de recherche.
     * @param criteria Le criteria de recherche.
     * @param searchCrit Le critère de recherche.
     */
    void apply(final Criteria criteria,
               final SearchCriteria searchCrit);
}

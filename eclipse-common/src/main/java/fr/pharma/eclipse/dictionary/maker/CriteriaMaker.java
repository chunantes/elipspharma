package fr.pharma.eclipse.dictionary.maker;

import org.hibernate.Criteria;

import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;

/**
 * Interface des artisans de recherche.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public interface CriteriaMaker {
    /**
     * Méthode de transformation d'un critère de recherche.
     * @param criteria Le critéria de recherche.
     * @param searchCrit Le critère de recherche.
     */
    void transform(final Criteria criteria,
                   final SearchCriteria searchCrit);

    /**
     * Vérifie que le maker peut effectuer la transformation.
     * @param clazz La classe à vérifier.
     * @return <code>true</code> si le maker est adapté, <code>false</code>
     * sinon.
     */
    @SuppressWarnings("unchecked")
    boolean supports(final Class clazz);

}

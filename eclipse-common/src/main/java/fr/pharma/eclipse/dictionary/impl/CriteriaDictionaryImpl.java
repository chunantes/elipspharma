package fr.pharma.eclipse.dictionary.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.springframework.util.Assert;

import fr.pharma.eclipse.dictionary.CriteriaDictionary;
import fr.pharma.eclipse.dictionary.maker.CriteriaMaker;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;

/**
 * Dictionnaire de critères de recherche.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class CriteriaDictionaryImpl implements CriteriaDictionary, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7109461324902291745L;

    /**
     * Dictionnaire d'artisans.
     */
    @SuppressWarnings("unchecked")
    private Map<Class, CriteriaMaker> makers = new HashMap<Class, CriteriaMaker>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(final Criteria criteria,
                      final SearchCriteria searchCrit) {
        Assert.notNull(searchCrit, "SearchCriteria must not be null");
        final CriteriaMaker maker = this.getMakers().get(searchCrit.getClass());
        Assert.notNull(maker, "Maker must not be null");
        if (maker.supports(searchCrit.getClass())) {
            maker.transform(criteria, searchCrit);
        }
    }

    /**
     * Getter pour makers.
     * @return Retourne les artisans.
     */
    @SuppressWarnings("unchecked")
    public Map<Class, CriteriaMaker> getMakers() {
        return this.makers;
    }

    /**
     * Setter pour makers.
     * @param makers les artisans à écrire.
     */
    @SuppressWarnings("unchecked")
    public void setMakers(final Map<Class, CriteriaMaker> makers) {
        this.makers = makers;
    }

}

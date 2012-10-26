package fr.pharma.eclipse.processor.impl;

import java.util.List;

import fr.pharma.eclipse.domain.model.sigrec.common.SigrecElement;
import fr.pharma.eclipse.processor.filter.SigrecFilter;

/**
 * Processor en charge d'appliquer une liste de filtre sur un élément Sigrec.
 
 * @version $Revision$ $Date$
 * @param <BEAN> Classe du bean concerné par le traitement.
 */
public class FilterProcessor<BEAN extends SigrecElement>
{
    /**
     * Liste des filtres à appliquer.
     */
    private List<SigrecFilter<BEAN>> filters;

    /**
     * {@inheritDoc}
     */
    public BEAN process(final BEAN sigrec)
    {
        for (final SigrecFilter<BEAN> filter : this.filters)
        {
            if (filter.support(sigrec))
            {
                filter.filter(sigrec);
            }
        }
        return sigrec;
    }

    /**
     * Getter sur filters.
     * @return Retourne le filters.
     */
    public List<SigrecFilter<BEAN>> getFilters()
    {
        return this.filters;
    }

    /**
     * Setter pour filters.
     * @param filters le filters à écrire.
     */
    public void setFilters(final List<SigrecFilter<BEAN>> filters)
    {
        this.filters = filters;
    }
}

package fr.pharma.eclipse.processor.filter;

import fr.pharma.eclipse.domain.model.sigrec.common.SigrecElement;

/**
 * Interface des filtres à appliquer à la représentation du fichier XML en bean Java.
 
 * @version $Revision$ $Date$
 * @param <BEAN> Element sigrec.
 */
public interface SigrecFilter<BEAN extends SigrecElement>
{
    /**
     * Appliquer le filtre.
     * @param bean Le bean sur lequel appliquer le filtre.
     */
    void filter(final BEAN bean);

    /**
     * Retourne <true> si le filtre supporte le bean passé en paramètre.
     * @param bean Le bean concerné par le traitement.
     * @return <true> si le filtre supporte le bean passé en paramètre.
     */
    boolean support(final BEAN bean);
}

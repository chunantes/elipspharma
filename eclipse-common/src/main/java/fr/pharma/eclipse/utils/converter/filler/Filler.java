package fr.pharma.eclipse.utils.converter.filler;

import fr.pharma.eclipse.domain.model.common.BeanObject;

/**
 * Interface des filler en charge d'insérer les données d'un bean technique
 * SIGREC dans un bean métier.
 * @param <BEANTECH> Type de l'objet technique source.
 * @param <BEAN> Type de l'objet métier destination.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface Filler<BEANTECH extends Object, BEAN extends BeanObject> {
    /**
     * Méthode en charge d'insérer des données d'un bean technique dans un bean
     * métier.
     * @param source Bean technique SIGREC.
     * @param destination Bean métier.
     */
    void fill(final BEANTECH source,
              final BEAN destination);

    /**
     * Retourne <code>true</code> si le filler est capable de traiter l'élément
     * en entrée.
     * @param source Le bean source.
     * @return <code>true</code> si le filler est capable de traiter l'élément
     * en entrée.
     */
    boolean support(BEANTECH source);
}

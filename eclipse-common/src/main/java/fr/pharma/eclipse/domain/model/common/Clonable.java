package fr.pharma.eclipse.domain.model.common;

/**
 * Interface décrivant le comportement des beans clonables.
 
 * @version $Revision$ $Date$
 */
public interface Clonable<BEAN extends BeanObject>
{

    /**
     * Méthode en charge de cloner l'objet en paramètre.
     * @return Le bean cloné.
     */
    BEAN cloneMe();
}

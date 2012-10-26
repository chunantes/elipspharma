package fr.pharma.eclipse.domain.model.essai;

/**
 * Interface commune des éléments attachés à un essai. Elle est utilisé pour gérer les
 * habilitation plus facilement.
 
 * @version $Revision$ $Date$
 */
public interface EssaiElement
{
    /**
     * Retourne l'essai attaché à l'élément.
     * @return L'essai attaché à l'élément.
     */
    public Essai getEssai();
}

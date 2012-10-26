package fr.pharma.eclipse.domain.model.design;

import java.util.SortedSet;

import fr.pharma.eclipse.domain.enums.TypeDesignable;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;

/**
 * Interface commune des éléments liés au design de l'essai : Bras (et sous bras), Sequence.
 
 * @version $Revision$ $Date$
 */
public interface Designable
{
    /**
     * Retourne le nom de l'élément designable.
     * @return le nom de l'élément designable.
     */
    String getNom();

    /**
     * Retourne le debut du traitement.
     * @return le debut du traitement.
     */
    TempsPrescription getDebut();

    /**
     * Retourne la fin du traitement.
     * @return la fin du traitement.
     */
    TempsPrescription getFin();

    /**
     * Retourne les libellés des produits concernés par le design.
     * @return les libellés des produits concernés par le design.
     */
    String getLibelleProduit();

    /**
     * Retourne le type de l'élément designable (bras ou sequence).
     * @return le type de l'élément designable.
     */
    TypeDesignable getType();

    /**
     * Retourne le parent de l'élément designable.
     * @return le parent de l'élément designable.
     */
    Designable getParent();

    /**
     * Retourne les enfants.
     * @return les enfants.
     */
    SortedSet<Designable> getEnfants();

    /**
     * Retourne le nom complet.
     * @return le nom complet.
     */
    String getNomComplet();
}

package fr.pharma.eclipse.service.patient.dictionary;

import java.io.Serializable;

/**
 * Interface des algorithmes de calcul de la surface corporelle d'un patient.
 
 * @version $Revision$ $Date$
 */
public interface SurfaceCorporelleProcessor
    extends Serializable
{
    /**
     * Méthode en charge de calculer la surface corporelle d'un patient en fonction de son poid et
     * de sa taille.
     * @param taille Taille.
     * @param poids Poids.
     * @return la surface corporelle.
     */
    double process(double taille,
                   double poids);
}

package fr.pharma.eclipse.component.produit.builder;

import fr.pharma.eclipse.domain.model.produit.Conditionnement;

/**
 * Builder en charge de construire l'unité de prescription pour un conditionnement.
 
 * @version $Revision$ $Date$
 */
public interface UnitePrescriptionBuilder
{
    /**
     * Méthode en charge de construire l'unité de prescription.
     * @param conditionnement Le conditionnement.
     */
    public void build(Conditionnement conditionnement);
}

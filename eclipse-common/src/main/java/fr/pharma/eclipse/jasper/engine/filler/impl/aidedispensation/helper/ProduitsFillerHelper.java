package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation.helper;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanProduit;
import fr.pharma.eclipse.domain.model.produit.Produit;

/**
 * Interface de helpers pour la création des beans de produits à partir des produits de l'essai.
 
 * @version $Revision$ $Date$
 */
public interface ProduitsFillerHelper
    extends Serializable
{
    /**
     * Méthode en charge de transformer un ensemble de produits en une collection de
     * {@link JRBeanProduit}.
     * @param produits Produits à transformer.
     * @return La collection des {@link JRBeanProduit} correspondante.
     */
    Collection<JRBeanProduit> transform(Set<Produit> produits);
}

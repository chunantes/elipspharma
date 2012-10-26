package fr.pharma.eclipse.component.produit.helper;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Produit;

/**
 * Helper dédié aux produits.
 
 * @version $Revision$ $Date$
 */
public class ProduitHelper
    implements Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 2501244687082763049L;

    /**
     * Retourne une chaine de caractère contenant tous les noms des produtis de l'essai en
     * paramètre.
     * @param essai L'essai.
     * @return une chaine de caractère contenant tous les noms des produtis de l'essai en
     * paramètre.
     */
    public String getProduitsAsString(final Essai essai)
    {
        final StringBuffer sb = new StringBuffer();
        for (final Produit p : essai.getDetailProduit().getProduits())
        {
            sb.append(p.getNom()).append(" / ");
        }
        return StringUtils.substringBeforeLast(sb.toString(),
                                               "/");
    }
}

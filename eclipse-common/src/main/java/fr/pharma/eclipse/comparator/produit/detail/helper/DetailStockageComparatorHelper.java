package fr.pharma.eclipse.comparator.produit.detail.helper;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;

/**
 * Classe de helper pour la constitution de la clé d'un détail de stockage, dans le comparateur de
 * {@link DetailStockage}.
 
 * @version $Revision$ $Date$
 */
public class DetailStockageComparatorHelper
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 1965807748831784923L;

    /**
     * Méthode en charge d'ajouter la partie relative à la pharmacie dans la clé.
     * @param keyBuilder Builder de clé.
     * @param pharmacie Pharmacie.
     * @pre Si la pharmacie est non nulle, elle possède un identifiant non nul.
     * @param defaultValue Valeur par défaut de la clé.
     */
    public void appendPharmaciePart(final StringBuilder keyBuilder,
                                    final Pharmacie pharmacie,
                                    final String defaultValue)
    {
        if (pharmacie == null)
        {
            keyBuilder.append(defaultValue);
            return;
        }
        keyBuilder.append(pharmacie.getId());
    }

    /**
     * Méthode en charge d'ajouter la partie relative au stockage dans la clé.
     * @param keyBuilder Builder de clé.
     * @param stockage Lieu de stockage.
     * @param defaultValue Valeur par défaut de la clé.
     */
    public void appendStockagePart(final StringBuilder keyBuilder,
                                   final Stockage stockage,
                                   final String defaultValue)
    {
        if (stockage == null)
        {
            keyBuilder.append(defaultValue);
            return;
        }
        keyBuilder.append(stockage.getNomComplet());
    }

    /**
     * Méthode en charge d'ajouter la partie relative à l'identifiant de stockage dans la clé.
     * @param keyBuilder Builder de clé.
     * @param identifiantStockage Identifiant de stockage.
     * @param defaultValue Valeur par défaut de la clé.
     */
    public void appendIdStockagePart(final StringBuilder keyBuilder,
                                     final String identifiantStockage,
                                     final String defaultValue)
    {
        if (!StringUtils.hasText(identifiantStockage))
        {
            keyBuilder.append(defaultValue);
            return;
        }
        keyBuilder.append(identifiantStockage.trim());
    }

}

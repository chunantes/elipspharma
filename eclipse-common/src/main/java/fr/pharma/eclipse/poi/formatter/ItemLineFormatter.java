package fr.pharma.eclipse.poi.formatter;

import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Resultat;

/**
 * Permet de transformer un item en un tableau de chaine de caractères.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public interface ItemLineFormatter {
    /**
     * Méthode en charge de formatter l'item en une liste de chaine de
     * caractères.
     * @param item Item.
     * @param resultat Le résultat.
     * @return La liste de chaine de caractère.
     */
    String[] format(Item item,
                    Resultat resultat);
}

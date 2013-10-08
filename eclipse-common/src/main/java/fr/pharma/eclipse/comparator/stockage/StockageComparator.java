package fr.pharma.eclipse.comparator.stockage;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.domain.model.stockage.Stockage;

/**
 * Classe de comparator sur Stockage.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class StockageComparator implements Comparator<Stockage>, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3398493522606374717L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final Stockage o1,
                       final Stockage o2) {
        final String str1 = this.addNomStockage(o1, StringUtils.EMPTY) + o1.getNom();
        final String str2 = this.addNomStockage(o2, StringUtils.EMPTY) + o2.getNom();

        return str1.compareToIgnoreCase(str2);
    }

    /**
     * Méthode en charge de concaténer le nom des parents du stockage.
     * @param stockage Stockage à analyser.
     * @param str String contenant la concaténation des noms de stockages.
     * @return Nom des parents.
     */
    protected String addNomStockage(final Stockage stockage,
                                    final String str) {
        final Stockage parent = stockage.getParent();
        while (parent != null) {
            return this.addNomStockage(parent, parent.getNom().concat(str));
        }
        return str;
    }
}

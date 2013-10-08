package fr.pharma.eclipse.comparator.produit.detail;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.comparator.produit.detail.helper.DetailStockageComparatorHelper;
import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe de comparator de détail de stockage.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DetailStockageComparator implements Serializable, Comparator<DetailStockage> {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5722477524396591726L;

    /**
     * Helper.
     */
    private DetailStockageComparatorHelper helper;

    /**
     * Constructeur.
     */
    public DetailStockageComparator() {
        this.helper = new DetailStockageComparatorHelper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final DetailStockage detailSto1,
                       final DetailStockage detailSto2) {
        final String key1 = this.buildKey(detailSto1);
        final String key2 = this.buildKey(detailSto2);
        return key1.compareTo(key2);
    }

    /**
     * Méthode en charge de construire la clé à comparer d'un détail de
     * stockage.
     * @param detailStockage Détail de stockage.
     * @return La clé du détail de stockage (idPharma + libellé nom stockage +
     * id de stockage).
     */
    private String buildKey(final DetailStockage detailStockage) {
        final StringBuilder keyBuilder = new StringBuilder();
        final String defaultValue = StringUtils.EMPTY;
        final String separateur = EclipseConstants.SEMI_COLON;
        this.helper.appendPharmaciePart(keyBuilder, detailStockage.getPharmacie(), defaultValue);
        keyBuilder.append(separateur);
        this.helper.appendStockagePart(keyBuilder, detailStockage.getStockage(), defaultValue);
        keyBuilder.append(separateur);
        this.helper.appendIdStockagePart(keyBuilder, detailStockage.getIdentifiantStockage(), defaultValue);
        return keyBuilder.toString();
    }

    /**
     * Getter sur helper.
     * @return Retourne le helper.
     */
    DetailStockageComparatorHelper getHelper() {
        return this.helper;
    }

    /**
     * Setter pour helper.
     * @param helper le helper à écrire.
     */
    public void setHelper(final DetailStockageComparatorHelper helper) {
        this.helper = helper;
    }
}

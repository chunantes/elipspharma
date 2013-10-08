package fr.pharma.eclipse.jasper.engine.helper;

import java.io.Serializable;
import java.util.Calendar;

import fr.pharma.eclipse.domain.enums.jasper.TypeRapportJasper;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Helper pour la génération des noms des rapports Jasper produits.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ReportNameBuildHelper implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1446592935236878877L;

    /**
     * Séparateur de niveau 1.
     */
    private static final String SEPARATEUR_1 = EclipseConstants.UNDERSCORE;

    /**
     * Séparateur de niveay 2.
     */
    private static final String SEPARATEUR_2 = EclipseConstants.DASH;

    /**
     * Pattern pour la génération de la date.
     */
    private static final String PATTERN = EclipseConstants.PATTERN_EXPORTED_FILE;

    /**
     * Méthode en charge d'ajouter la partie relative à<br>
     * l'identifiant de l'essai dans le nom du rapport produit.
     * @param strBuilder Builder pour le nom de rapport à produire.
     * @param essai Essai servant de source au rapport.
     */
    public void addIdEssaiPart(final StringBuilder strBuilder,
                               final Essai essai) {
        this.appendSeparateur1(strBuilder);
        strBuilder.append(this.getChaineNormalisee(essai.getNumInterne()));
    }

    /**
     * Méthode en charge d'ajouter la partie relative à<br>
     * la date de génération dans le nom du rapport produit.
     * @param strBuilder Builder pour le nom de rapport à produire.
     */
    public void addDatePart(final StringBuilder strBuilder) {
        this.appendSeparateur1(strBuilder);
        strBuilder.append(Utils.formatDate(Calendar.getInstance(EclipseConstants.LOCALE).getTime(), ReportNameBuildHelper.PATTERN));
    }

    /**
     * Méthode en charge d'ajouter le nom spécifique du rapport<br>
     * dans le nom du rapport produit.
     * @param strBuilder Builder pour le nom de rapport à produire.
     * @param typeRapport Type du rapport généré.
     */
    public void addCommonNamePart(final StringBuilder strBuilder,
                                  final TypeRapportJasper typeRapport) {
        strBuilder.append(typeRapport.getReportName());
    }

    /**
     * Méthode en charge d'ajouter l'extension<br>
     * dans le nom du rapport produit.
     * @param strBuilder Builder pour le nom de rapport à produire.
     * @param typeRapport Type du rapport généré.
     */
    public void addCommonExtensionPart(final StringBuilder strBuilder,
                                       final TypeRapportJasper typeRapport) {
        strBuilder.append(typeRapport.getTypeExport().getExtension());
    }

    /**
     * Méthode en charge de normaliser une chaine de caractères pour l'utiliser
     * dans le nom de fichier.
     * @param chaine Chaîne à normaliser.
     * @return Chaîne normalisée.
     */
    private String getChaineNormalisee(final String chaine) {
        if (chaine == null) {
            return chaine;
        }
        String res = chaine.trim();
        res = res.replace(" ", ReportNameBuildHelper.SEPARATEUR_2);
        return res;
    }

    /**
     * Méthode en charge d'ajouter le séparateur 1 au builder.
     * @param strBuilder Builder pour le nom de rapport à produire.
     */
    private void appendSeparateur1(final StringBuilder strBuilder) {
        strBuilder.append(ReportNameBuildHelper.SEPARATEUR_1);
    }

    /**
     * Getter sur pattern.
     * @return Retourne le pattern.
     */
    static String getPattern() {
        return ReportNameBuildHelper.PATTERN;
    }

}

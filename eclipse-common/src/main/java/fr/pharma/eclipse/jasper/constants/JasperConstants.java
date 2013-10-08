package fr.pharma.eclipse.jasper.constants;

import org.apache.commons.lang.StringUtils;

/**
 * Classe contenant les constantes utilisées pour la génération des rapports
 * Jasper.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public final class JasperConstants {

    /**
     * Constructeur privé.
     */
    private JasperConstants() {
        super();
    }

    /**
     * Préfixe de toutes les clés passées en paramètres.
     */
    public static final String JASPER_PREFIX = "jasper.";

    /**
     * Clé du paramètre contenant le chemin vers le répertoire contenant les
     * sous-rapports.
     */
    public static final String SUBREPORT_DIRECTORY = "SUBREPORT_DIR";

    /**
     * Clé du paramètre contenant le chemin vers le répertoire contenant la
     * webapp.
     */
    public static final String CHECKBOX_DIRECTORY = "CHECKBOX_DIR";

    /**
     * Séparateur entre les libellés présentés sur une même ligne.
     */
    public static final String SEPARATEUR_LIBELLES = "; ";

    /**
     * Valeur par défaut des champs String des dataSources.
     */
    public static final String DEFAULT_FIELD_VALUE = StringUtils.EMPTY;
}

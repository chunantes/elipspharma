package fr.pharma.eclipse.utils.constants;

import java.util.Locale;

/**
 * Classe contenant les constantes globales pour Eclipse.
 
 * @version $Revision$ $Date$
 */
public final class EclipseConstants
{
    /**
     * Saut de ligne.
     */
    public static final String SAUT_LIGNE = "\n";

    /**
     * Locale.
     */
    public static final Locale LOCALE = Locale.FRANCE;

    /**
     * Pattern simple.
     */
    public static final String PATTERN_SIMPLE = "dd/MM/yyyy";

    /**
     * Pattern simple mois.
     */
    public static final String PATTERN_SIMPLE_MOIS = "MM/yyyy";

    /**
     * Pattern avec les heures.
     */
    public static final String PATTERN_AVEC_HEURES = "dd/MM/yyyy HH:mm";

    /**
     * Pattern pour les fichiers sur le disque.
     */
    public static final String PATTERN_FILE_DISK = "yyyyMMddHHmmss";

    /**
     * Pattern pour les fichiers exportés.
     */
    public static final String PATTERN_EXPORTED_FILE = "yyyyMMdd";

    /**
     * Non applicatble.
     */
    public static final String NON_APPLICABLE = "n/a";

    /**
     * Virgule.
     */
    public static final String COMMA = ",";

    /**
     * Tiret.
     */
    public static final String DASH = "-";

    /**
     * Space.
     */
    public static final String SPACE = " ";

    /**
     * Semi colon.
     */
    public static final String SEMI_COLON = ";";

    /**
     * Colon.
     */
    public static final String COLON = ":";

    /**
     * Slash.
     */
    public static final String SLASH = "//";

    /**
     * Constante ROLE.
     */
    public static final String ROLE = "ROLE_";

    /**
     * Nombre minimum de caractères de la partie 'chrono' du numéro Sigrec par défaut.
     */
    public static final int NUM_SIGREC_MIN_DIGITS = 2;

    /**
     * Constante underscore.
     */
    public static final String UNDERSCORE = "_";

    /**
     * Constante point.
     */
    public static final String DOT = ".";

    /**
     * Constante Oui.
     */
    public static final String OUI = "Oui";

    /**
     * Constante Non.
     */
    public static final String NON = "Non";

    /**
     * Constante Du.
     */
    public static final String DU = "Du";

    /**
     * Constante Au.
     */
    public static final String AU = "Au";

    /**
     * Euros.
     */
    public static final String EURO = "€";
    /**
     * Constructeur.
     */
    protected EclipseConstants()
    {
    }
}

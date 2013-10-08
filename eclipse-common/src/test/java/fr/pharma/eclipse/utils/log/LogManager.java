package fr.pharma.eclipse.utils.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe de manager de Log pour les tests.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public final class LogManager {
    /**
     * Log.
     */
    private static final Logger log = LoggerFactory.getLogger("TEST");

    /**
     * Constructeur.
     */
    private LogManager() {
    }

    /**
     * Méthode de log.
     * @param obj Objet.
     */
    public static void log(final Object obj) {
        LogManager.log.debug(obj.toString());
    }

}

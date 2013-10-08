package fr.pharma.eclipse.jasper.exception;

import java.io.Serializable;

/**
 * Exception associée à un problème lors de la génération d'un rapport Jasper.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JasperReportBuildException extends Exception implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3111762226279754499L;

    /**
     * Constructeur.
     * @param message Message.
     */
    public JasperReportBuildException(final String message) {
        super(message);
    }

    /**
     * Constructeur.
     * @param cause Cause.
     */
    public JasperReportBuildException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructeur.
     * @param message Message.
     * @param cause Cause.
     */
    public JasperReportBuildException(final String message, final Throwable cause) {
        super(message, cause);
    }

}

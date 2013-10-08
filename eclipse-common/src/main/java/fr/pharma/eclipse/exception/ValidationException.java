package fr.pharma.eclipse.exception;

import fr.pharma.eclipse.exception.common.CommonException;

/**
 * Exception levée en cas d'erreur de validation.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class ValidationException extends CommonException {

    /**
     * Serial Id.
     */
    private static final long serialVersionUID = -6002176633865827091L;

    /**
     * Erreur de validation.
     */
    public static final String ERROR_VALIDATION = "erreurValidation";

    /**
     * Le code d'erreur de l'exception de validation.
     */
    private String errorCode;

    /**
     * Ensemble de valeurs.
     */
    private String[] values;

    /**
     * L'objet source de problème.
     */
    private Object source;

    /**
     * Constructeur principal.
     * @param errorCode Le code d'erreur de l'exception.
     */
    public ValidationException(final String errorCode) {
        this(errorCode, new String[0]);
    }

    /**
     * Constructeur principal.
     * @param errorCode Le code d'erreur de l'exception.
     * @param value Valeur à définir dans le message d'erreur.
     */
    public ValidationException(final String errorCode, final String value) {
        this(errorCode, new String[]{value });
    }

    /**
     * Constructeur principal.
     * @param errorCode Le code d'erreur de l'exception.
     * @param values Valeurs à définir dans le message d'erreur.
     */
    public ValidationException(final String errorCode, final String[] values) {
        this(errorCode, values, null);
    }

    /**
     * Constructeur principal.
     * @param errorCode Le code d'erreur de l'exception.
     * @param values Valeurs a définir dans le message d'erreur.
     * @param source L'objet source de l'exception.
     */
    public ValidationException(final String errorCode, final String[] values, final Object source) {
        super();
        this.setErrorCode(errorCode);
        this.setValues(values);
        this.setSource(source);
    }

    /**
     * Getter pour errorCode.
     * @return Retourne le errorCode.
     */
    public String getErrorCode() {
        return this.errorCode;
    }

    /**
     * Setter pour errorCode.
     * @param errorCode le errorCode à écrire.
     */
    public void setErrorCode(final String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Getter pour values.
     * @return Retourne le values.
     */
    public String[] getValues() {
        return this.values;
    }

    /**
     * Setter pour values.
     * @param values le values à écrire.
     */
    public void setValues(final String[] values) {
        this.values = values;
    }

    /**
     * Getter pour source.
     * @return Retourne le source.
     */
    public Object getSource() {
        return this.source;
    }

    /**
     * Setter pour source.
     * @param source le source à écrire.
     */
    public void setSource(final Object source) {
        this.source = source;
    }
}

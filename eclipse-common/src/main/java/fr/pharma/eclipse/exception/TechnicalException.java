package fr.pharma.eclipse.exception;

import fr.pharma.eclipse.exception.common.CommonException;

/**
 * Exception levée en cas d'erreur technique.
 
 * @version $Revision$ $Date$
 */
public class TechnicalException
    extends CommonException
{
    /**
     * Serial Id.
     */
    private static final long serialVersionUID = -1271170522329489679L;

    /**
     * Constructeur principal.
     * @param errorMsg Le message de l'exception.
     */
    public TechnicalException(final String errorMsg)
    {
        super(errorMsg);
    }

    /**
     * Constructeur principal.
     * @param errorMsg Le message de l'exception.
     * @param errorThrow La source de l'exception.
     */
    public TechnicalException(final String errorMsg, final Throwable errorThrow)
    {
        super(errorMsg,
              errorThrow);
    }

    /**
     * Constructeur principal.
     * @param errorThrow La source de l'exception.
     */
    public TechnicalException(final Throwable errorThrow)
    {
        super(errorThrow);
    }
}

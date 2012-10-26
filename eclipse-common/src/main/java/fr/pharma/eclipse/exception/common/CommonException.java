package fr.pharma.eclipse.exception.common;


/**
 * Classe de base des exceptions de l'application.
 
 * @version $Revision$ $Date$
 */
public class CommonException
    extends RuntimeException
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8293055360445446517L;

    /**
     * Constructeur par défaut.
     */
    public CommonException()
    {
        super();
    }

    /**
     * Constructeur par défaut.
     * @param error Message d'erreur.
     */
    public CommonException(final String error)
    {
        super(error);
    }

    /**
     * Constructeur par défaut.
     * @param cause Erreur throwable.
     */
    public CommonException(final Throwable cause)
    {
        super(cause);
    }

    /**
     * Constructeur par défaut.
     * @param error Message d'erreur.
     * @param cause Erreur throwable.
     */
    public CommonException(final String error, final Throwable cause)
    {
        super(error,
              cause);
    }

}

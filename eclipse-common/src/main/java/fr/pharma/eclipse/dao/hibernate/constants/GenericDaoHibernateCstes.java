package fr.pharma.eclipse.dao.hibernate.constants;

/**
 * Classe de constantes utilisées dans GenericDaoHibernate.
 
 * @version $Revision$ $Date$
 */
public final class GenericDaoHibernateCstes
{

    /**
     * EntityManager fermé.
     */
    public static final String ENTITY_MANAGER_CLOSED = "L'entity manager a été fermé";

    /**
     * Bean non managé par l'entity manager.
     */
    public static final String BEAN_UNMANAGED = "Le bean n'est pas managé par l'entity manager";

    /**
     * Aucune transaction n'est disponible pour l'entity manager.
     */
    public static final String NO_TRANSACTION_AVAILABLE = "Aucune transaction disponible";

    /**
     * L'entité n'est plus présente en base.
     */
    public static final String ENTITY_NOT_FOUND_DB = "L'entité n'est plus présente en base";

    /**
     * L'entité ne peut pas être persistée.
     */
    public static final String ENTITY_ERROR_PERSIST = "L'entité ne peut pas être persistée";

    /**
     * Constructeur.
     */
    protected GenericDaoHibernateCstes()
    {
    }
}

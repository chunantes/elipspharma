package fr.pharma.eclipse.dao.search;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DAO commune des recherches.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public abstract class CommonSearchDao {

    /**
     * Contexte de persistence.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(CommonSearchDao.class);

    /**
     * Définit un critère de recherche sur la session actuelle.
     * @return Un critère de recherche sur la session actuelle.
     */
    @SuppressWarnings("rawtypes")
    protected Criteria createCriteria(final Class c) {
        final Session session = this.getCurrentSession();
        final Criteria crit = session.createCriteria(c);
        return crit;
    }

    /**
     * Récupération de la session courante.
     * @return Session.
     */
    protected Session getCurrentSession() {
        try {
            return (Session) this.getEntityManager().getDelegate();
        } catch (final IllegalStateException e) {
            this.log.error("Exception dans la méthode getCurrentSession : " + e);
        }
        return null;
    }

    /**
     * Getter pour entityManager.
     * @return Le entityManager
     */
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

}

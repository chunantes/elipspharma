package fr.pharma.eclipse.dao.hibernate.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.dao.hibernate.constants.GenericDaoHibernateCstes;
import fr.pharma.eclipse.dictionary.CriteriaDictionary;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * DAO (Data Access Object) générique sur base Hibernate pour l'utilisation standard des POJOs
 * (CRUD).
 * @param <BEAN> Bean Objet Métier.
 
 * @version $Revision$ $Date$
 */

public class GenericDaoHibernate<BEAN extends BeanObject>
    implements GenericDao<BEAN>, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5348208615045305310L;

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(GenericDaoHibernate.class);

    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * La classe héritée pour la persistance de la DAO.
     */
    private final Class<BEAN> persistentClass;

    /**
     * Le dictionnaire de gestion des critères de recherche.
     */
    private CriteriaDictionary criteriaDictionary;

    /**
     * Noms des listes du bean à prendre en compte pour le detach (par introspection).
     */
    private List<String> nameBeanListsForDetach;

    /**
     * Constructeur de la DAO générique.
     * @param persistentClass La classe dont le type est à gérer par la DAO.
     */
    public GenericDaoHibernate(final Class<BEAN> persistentClass)
    {
        this.persistentClass = persistentClass;
    }

    /**
     * {@inheritDoc}
     */
    public List<BEAN> getAll(final SearchCriteria criteria)
    {
        final Criteria crit = this.createCriteria();
        crit.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        this.applySearchCriteria(crit,
                                 criteria);
        this.applySortCriteria(crit,
                               criteria);

        @SuppressWarnings("unchecked")
        final List<BEAN> resultat = crit.list();
        return resultat;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<BEAN> getAll()
    {
        return this.createCriteria().list();
    }

    /**
     * Méthode de traitement du critère de recherche.
     * @param crit Le critère de recherche.
     * @param criteria Le gestionnaire de critères de recherche.
     */
    private void applySearchCriteria(final Criteria crit,
                                     final SearchCriteria criteria)
    {
        this.getCriteriaDictionary().apply(crit,
                                           criteria);
    }

    /**
     * Méthode en charge d'ajouter les tris sur une requete.
     * @param crit Criteria Hibernate.
     * @param criteria Critère de recherche.
     */
    protected void applySortCriteria(final Criteria crit,
                                     final SearchCriteria criteria)
    {
        if (criteria.getActiveOrder() != null)
        {
            if (criteria.isAscending())
            {
                final Order order = Order.asc(criteria.getActiveOrder());
                if (!criteria.isCaseSensitiveOrder())
                {
                    order.ignoreCase();
                }
                crit.addOrder(order);
            }
            else
            {
                final Order order = Order.desc(criteria.getActiveOrder());
                if (!criteria.isCaseSensitiveOrder())
                {
                    order.ignoreCase();
                }
                crit.addOrder(order);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public BEAN get(final Long id)
    {
        try
        {
            final BEAN entity = this.entityManager.find(this.persistentClass,
                                                        id);
            return entity;
        }
        catch (final IllegalStateException e)
        {
            this.handleException("get",
                                 e,
                                 GenericDaoHibernateCstes.ENTITY_MANAGER_CLOSED);
        }
        catch (final IllegalArgumentException e)
        {
            this.handleException("get",
                                 e,
                                 GenericDaoHibernateCstes.BEAN_UNMANAGED);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public BEAN save(final BEAN object)
    {
        try
        {
            if (object.getId() == null)
            {
                this.entityManager.persist(object);
            }
            else
            {
                return this.entityManager.merge(object);
            }
        }
        catch (final IllegalStateException e)
        {
            this.handleException("save",
                                 e,
                                 GenericDaoHibernateCstes.ENTITY_MANAGER_CLOSED);
        }
        catch (final IllegalArgumentException e)
        {
            this.handleException("save",
                                 e,
                                 GenericDaoHibernateCstes.BEAN_UNMANAGED);
        }
        catch (final TransactionRequiredException e)
        {
            this.handleException("save",
                                 e,
                                 GenericDaoHibernateCstes.NO_TRANSACTION_AVAILABLE);
        }
        catch (final EntityExistsException e)
        {
            this.handleException("save",
                                 e,
                                 GenericDaoHibernateCstes.ENTITY_ERROR_PERSIST);
        }
        return object;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public void remove(final BEAN object)
    {
        try
        {
            this.entityManager.remove(object);
        }
        catch (final IllegalStateException e)
        {
            this.handleException("remove",
                                 e,
                                 GenericDaoHibernateCstes.ENTITY_MANAGER_CLOSED);

        }
        catch (final IllegalArgumentException e)
        {
            this.handleException("remove",
                                 e,
                                 GenericDaoHibernateCstes.BEAN_UNMANAGED);
        }
        catch (final TransactionRequiredException e)
        {
            this.handleException("remove",
                                 e,
                                 GenericDaoHibernateCstes.NO_TRANSACTION_AVAILABLE);
        }
    }

    /**
     * Définit un critère de recherche sur la session actuelle.
     * @return Un critère de recherche sur la session actuelle.
     */
    private Criteria createCriteria()
    {
        final Session session = this.getCurrentSession();
        final Criteria crit = session.createCriteria(this.persistentClass);
        return crit;
    }

    /**
     * {@inheritDoc}
     */
    public BEAN reattach(final BEAN bean)
    {
        BEAN beanResult = bean;
        if (bean == null)
        {
            return null;
        }
        if (bean.getId() == null)
        {
            return bean;
        }
        try
        {
            beanResult = this.entityManager.merge(bean);
        }
        catch (final IllegalStateException e)
        {
            this.handleException("reattach",
                                 e,
                                 GenericDaoHibernateCstes.ENTITY_MANAGER_CLOSED);
        }
        catch (final IllegalArgumentException e)
        {
            this.handleException("reattach",
                                 e,
                                 GenericDaoHibernateCstes.BEAN_UNMANAGED);
        }
        catch (final TransactionRequiredException e)
        {
            this.handleException("reattach",
                                 e,
                                 GenericDaoHibernateCstes.NO_TRANSACTION_AVAILABLE);
        }
        return beanResult;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public void dettach(final BEAN bean)
    {
        try
        {
            // BUG HIBERNATE
            // Purge des listes non persistées qui n'ont pas encore d'id
            // ClassCastException org.hibernate.action.DelayedPostInsertIdentifier sur le refresh
            if (this.nameBeanListsForDetach != null
                && !this.nameBeanListsForDetach.isEmpty())
            {
                for (final String nameList : this.nameBeanListsForDetach)
                {
                    final Collection<BeanObject> liste =
                        (Collection<BeanObject>) BeanTool.getPropriete(bean,
                                                                       nameList);

                    // Suppression de ceux qui n'ont pas encore d'id.
                    CollectionUtils.filter(liste,
                                           new Predicate() {
                                               @Override
                                               public boolean evaluate(final Object object)
                                               {
                                                   final BeanObject bean = (BeanObject) object;
                                                   return bean.getId() != null;
                                               }
                                           });
                }
            }
            this.entityManager.refresh(bean);
        }
        catch (final IllegalStateException e)
        {
            this.handleException("dettach",
                                 e,
                                 GenericDaoHibernateCstes.ENTITY_MANAGER_CLOSED);
        }
        catch (final IllegalArgumentException e)
        {
            this.handleException("dettach",
                                 e,
                                 GenericDaoHibernateCstes.BEAN_UNMANAGED);
        }
        catch (final TransactionRequiredException e)
        {
            this.handleException("dettach",
                                 e,
                                 GenericDaoHibernateCstes.NO_TRANSACTION_AVAILABLE);
        }
        catch (final EntityNotFoundException e)
        {
            this.handleException("dettach",
                                 e,
                                 GenericDaoHibernateCstes.ENTITY_NOT_FOUND_DB);
        }
    }

    /**
     * Obtention de la session courante.
     * @return Session.
     */
    protected Session getCurrentSession()
    {
        try
        {
            return (Session) this.getEntityManager().getDelegate();
        }
        catch (final IllegalStateException e)
        {
            this.handleException("getCurrentSession",
                                 e,
                                 GenericDaoHibernateCstes.ENTITY_MANAGER_CLOSED);
        }
        return null;
    }

    /**
     * Méthode en charge de gérer une exception en affichant un message.
     * @param nameMethod Nom de la méthode.
     * @param throwable Exception.
     * @param detailMessage Détail du message.
     */
    public void handleException(final String nameMethod,
                                final Throwable throwable,
                                final String detailMessage)
    {
        this.log.error("Exception dans la méthode : "
                       + nameMethod
                       + EclipseConstants.SAUT_LIGNE
                       + throwable.getMessage()
                       + EclipseConstants.SAUT_LIGNE
                       + detailMessage);
    }

    /**
     * Getter pour criteriaDictionary.
     * @return Retourne le criteriaDictionary.
     */
    public CriteriaDictionary getCriteriaDictionary()
    {
        return this.criteriaDictionary;
    }

    /**
     * Setter pour criteriaDictionary.
     * @param criteriaDictionary le criteriaDictionary à écrire.
     */
    public void setCriteriaDictionary(final CriteriaDictionary criteriaDictionary)
    {
        this.criteriaDictionary = criteriaDictionary;
    }

    /**
     * Récupération de entityManager.
     * @return Retourne le entityManager.
     */
    public EntityManager getEntityManager()
    {
        return this.entityManager;
    }

    /**
     * Affectation du champ entityManager.
     * @param entityManager le entityManager à écrire.
     */
    public void setEntityManager(final EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    /**
     * Getter pour persistentClass.
     * @return Retourne le persistentClass.
     */
    protected Class<BEAN> getPersistentClass()
    {
        return this.persistentClass;
    }

    /**
     * Setter pour nameBeanListsForDetach.
     * @param nameBeanListsForDetach le nameBeanListsForDetach à écrire.
     */
    public void setNameBeanListsForDetach(final List<String> nameBeanListsForDetach)
    {
        this.nameBeanListsForDetach = nameBeanListsForDetach;
    }

}

package fr.pharma.eclipse.dao.sir.hibernate;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.dao.hibernate.constants.GenericDaoHibernateCstes;
import fr.pharma.eclipse.dao.sir.GenericSirDao;
import fr.pharma.eclipse.dictionary.CriteriaDictionary;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.sir.common.BeanSirObject;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * DAO (Data Access Object) générique sur base Hibernate pour l'utilisation standard des POJOs
 * (CRUD).
 * @param <SIR> Bean Objet Métier SIR.
 
 * @version $Revision$ $Date$
 */

public class GenericSirDaoHibernate<SIR extends BeanSirObject>
    implements GenericSirDao<SIR>, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6182317199770761316L;

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(GenericSirDaoHibernate.class);

    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "sir")
    private EntityManager entityManager;

    /**
     * La classe héritée pour la persistance de la DAO.
     */
    private final Class<SIR> persistentClass;

    /**
     * Le dictionnaire de gestion des critères de recherche.
     */
    private CriteriaDictionary criteriaDictionary;

    /**
     * Constructeur de la DAO générique.
     * @param persistentClass La classe dont le type est à gérer par la DAO.
     */
    public GenericSirDaoHibernate(final Class<SIR> persistentClass)
    {
        this.persistentClass = persistentClass;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<SIR> getAll(final SearchCriteria criteria)
    {
        final Criteria crit = this.createCriteria();
        crit.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        this.applySearchCriteria(crit,
                                 criteria);
        this.applySortCriteria(crit,
                               criteria);
        return crit.list();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<SIR> getAll()
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
    @Transactional(readOnly = true)
    public SIR get(final Integer id)
    {
        try
        {
            final SIR entity = this.entityManager.find(this.persistentClass,
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
    protected Class<SIR> getPersistentClass()
    {
        return this.persistentClass;
    }
}

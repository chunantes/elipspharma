package fr.pharma.eclipse.service.common.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.validator.remove.RemoveValidator;
import fr.pharma.eclipse.validator.save.SaveValidator;

/**
 * Implémentation du manager générique pour l'utilisation standard des POJOs
 * (CRUD).
 * @author Netapsys
 * @version $Revision$ $Date$
 * @param <BEAN> Bean Objet Métier.
 */
public class GenericServiceImpl<BEAN extends BeanObject> implements GenericService<BEAN>, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7232463242497005969L;

    /**
     * Instance de la DAO générique, définie dans le constructeur.
     */
    private GenericDao<BEAN> genericDao;

    /**
     * Validator de suppression.
     */
    private RemoveValidator<BEAN> removeValidator;

    /**
     * Validator d'enregistrement.
     */
    private SaveValidator<BEAN> saveValidator;

    /**
     * Constructeur principal pour la création de nouvelle implémentation de
     * manager, plus spécifique.
     * @param genericDao La DAO à utiliser.
     */
    public GenericServiceImpl(final GenericDao<BEAN> genericDao) {
        this.setGenericDao(genericDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dettach(final BEAN bean) {
        this.getGenericDao().dettach(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BEAN get(final Long id) {
        return this.getGenericDao().get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BEAN> getAll(final SearchCriteria criteria) {
        return this.genericDao.getAll(criteria);
    }

    @Override
    public List<BEAN> getAll(final SearchCriteria criteria,
                             final int maxResults) {
        return this.genericDao.getAll(criteria, maxResults);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean hasResult(final SearchCriteria criteria) {
        return this.getAll(criteria).size() > 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BEAN> getAll() {
        return this.genericDao.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BEAN reattach(final BEAN bean) {
        return this.genericDao.reattach(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BEAN> reattachAll(final List<BEAN> beans) {
        if (beans == null) {
            return beans;
        }

        final List<BEAN> res = new ArrayList<BEAN>();
        for (final BEAN bean : beans) {
            res.add(this.reattach(bean));
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(final BEAN object) {
        final BEAN bean = this.reattach(object);
        if (this.removeValidator != null) {
            this.removeValidator.validate(bean);
        }
        this.genericDao.remove(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(final List<BEAN> objects) {
        boolean launchValidationException = false;
        for (final BEAN bean : objects) {
            try {
                this.remove(bean);
            } catch (final ValidationException e) {
                launchValidationException = true;
            }
        }
        if (launchValidationException) {
            throw new ValidationException("remove", new String[]{"impossible" });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BEAN save(final BEAN object) {
        if (this.saveValidator != null) {
            this.saveValidator.validate(object, this);
        }
        return this.genericDao.save(object);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BEAN> saveAll(final List<BEAN> beans) {
        final List<BEAN> savedBeans = new ArrayList<BEAN>();
        if (beans != null) {
            for (final BEAN bean : beans) {
                final BEAN savedBean = this.save(bean);
                savedBeans.add(savedBean);
            }
        }
        return savedBeans;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<BEAN> executeSQLQuery(final String sql,
                                      final Object[] params) {
        return this.getGenericDao().executeSQLQuery(sql, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<?> executeSQLQuery(final String sql,
                                   final Object[] params,
                                   final String[] columns,
                                   final Class<?> classResult) {
        return this.getGenericDao().executeSQLQuery(sql, params, columns, classResult);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<?> executeSQLQuery(final String sql,
                                   final Object[] params,
                                   final String[] columns,
                                   final Class<?> classResult,
                                   final Map<String, Object> scalarType) {
        return this.getGenericDao().executeSQLQuery(sql, params, columns, classResult, scalarType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Object[]> executeSQLQueryTabObject(final String sql,
                                                   final Object[] params) {
        return this.getGenericDao().executeSQLQueryTabObject(sql, params);
    }

    /**
     * Getter pour genericDao.
     * @return Retourne le genericDao.
     */
    public GenericDao<BEAN> getGenericDao() {
        return this.genericDao;
    }

    /**
     * Setter pour genericDao.
     * @param genericDao le genericDao à écrire.
     */
    public void setGenericDao(final GenericDao<BEAN> genericDao) {
        this.genericDao = genericDao;
    }

    /**
     * Setter pour removeValidator.
     * @param removeValidator le removeValidator à écrire.
     */
    public void setRemoveValidator(final RemoveValidator<BEAN> removeValidator) {
        this.removeValidator = removeValidator;
    }

    /**
     * Setter pour saveValidator.
     * @param saveValidator Le saveValidator à écrire.
     */
    public void setSaveValidator(final SaveValidator<BEAN> saveValidator) {
        this.saveValidator = saveValidator;
    }

    /**
     * Getter pour saveValidator.
     * @return Le saveValidator
     */
    public SaveValidator<BEAN> getSaveValidator() {
        return this.saveValidator;
    }
}

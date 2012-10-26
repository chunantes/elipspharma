package fr.pharma.eclipse.service.sir.impl;

import java.io.Serializable;
import java.util.List;

import fr.pharma.eclipse.dao.sir.GenericSirDao;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.sir.common.BeanSirObject;
import fr.pharma.eclipse.service.sir.GenericSirService;

/**
 * Implémentation du manager générique pour l'utilisation standard des POJOs de SIR.
 
 * @version $Revision$ $Date$
 * @param <SIR> Bean Objet Métier SIR.
 */
public class GenericSirServiceImpl<SIR extends BeanSirObject>
    implements GenericSirService<SIR>, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 8378133576434514284L;

    /**
     * Instance de la DAO générique, définie dans le constructeur.
     */
    private GenericSirDao<SIR> genericSirDao;

    /**
     * Constructeur principal pour la création de nouvelle implémentation de manager, plus
     * spécifique.
     * @param genericSirDao La DAO à utiliser.
     */
    public GenericSirServiceImpl(final GenericSirDao<SIR> genericSirDao)
    {
        this.setGenericSirDao(genericSirDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SIR get(final Integer id)
    {
        return this.genericSirDao.get(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SIR> getAll(final SearchCriteria criteria)
    {
        return this.genericSirDao.getAll(criteria);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<SIR> getAll()
    {
        return this.genericSirDao.getAll();
    }

    /**
     * Setter pour genericSirDao.
     * @param genericSirDao Le genericSirDao à écrire.
     */
    public void setGenericSirDao(final GenericSirDao<SIR> genericSirDao)
    {
        this.genericSirDao = genericSirDao;
    }

}

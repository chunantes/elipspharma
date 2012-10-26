package fr.pharma.eclipse.service.stock.impl;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.handler.habilitation.HabilitationHandler;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.stock.MvtStockService;
import fr.pharma.eclipse.service.user.UserService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe d'implémentation du service de gestion de mouvement de stock.
 
 * @version $Revision$ $Date$
 * @param <MVT> Bean Objet Mouvement de Stock.
 */
public class MvtStockServiceImpl<MVT extends MvtStock>
    extends GenericServiceImpl<MVT>
    implements MvtStockService<MVT>
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2287150233315221811L;

    /**
     * Service de gestion des utilisateurs.
     */
    @Resource(name = "userService")
    private UserService userService;

    /**
     * Gestionnaire des habilitations de mvts sur les pharmacies.
     */
    @Resource(name = "mvtHabilitationHandler")
    private HabilitationHandler<MVT> habilitationHandler;

    /**
     * Constructeur.
     * @param mouvementStockDao Dao de gestion des mouvements de stock.
     */
    public MvtStockServiceImpl(final GenericDao<MVT> mouvementStockDao)
    {
        super(mouvementStockDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MVT save(final MVT mouvementStock)
    {
        if (mouvementStock.getId() == null)
        {
            mouvementStock.setDateCreation(Calendar.getInstance(EclipseConstants.LOCALE));
        }
        mouvementStock.setPersonne(this.userService.getPersonne());
        return super.save(mouvementStock);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MVT> getAll()
    {
        final List<MVT> mvts = super.getAll();
        // Purge des mouvements selon les habilitations
        this.habilitationHandler.purge(mvts);
        return mvts;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MVT> getAll(final SearchCriteria criteria)
    {
        final List<MVT> mvts = super.getAll(criteria);
        // Purge des mouvements selon les habilitations
        this.habilitationHandler.purge(mvts);
        return mvts;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MVT> getAllWithoutPurge(final SearchCriteria criteria)
    {
        final List<MVT> mvts = super.getAll(criteria);
        // Purge des mouvements selon les habilitations
        return mvts;
    }

    /**
     * Setter pour userService.
     * @param userService Le userService à écrire.
     */
    public void setUserService(final UserService userService)
    {
        this.userService = userService;
    }

    /**
     * Getter pour userService.
     * @return Le userService
     */
    public UserService getUserService()
    {
        return this.userService;
    }

    /**
     * Setter pour habilitationHandler.
     * @param habilitationHandler Le habilitationHandler à écrire.
     */
    public void setHabilitationHandler(final HabilitationHandler<MVT> habilitationHandler)
    {
        this.habilitationHandler = habilitationHandler;
    };

}

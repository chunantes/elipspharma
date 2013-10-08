package fr.pharma.eclipse.service.stock.impl;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.stock.MvtStockService;
import fr.pharma.eclipse.service.user.UserService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe d'implémentation du service de gestion de mouvement de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 * @param <MVT> Bean Objet Mouvement de Stock.
 */
public class MvtStockServiceImpl<MVT extends MvtStock> extends GenericServiceImpl<MVT> implements MvtStockService<MVT> {
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
     * Constructeur.
     * @param mouvementStockDao Dao de gestion des mouvements de stock.
     */
    public MvtStockServiceImpl(final GenericDao<MVT> mouvementStockDao) {
        super(mouvementStockDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MVT save(final MVT mouvementStock) {
        if (mouvementStock.getId() == null) {
            mouvementStock.setDateCreation(Calendar.getInstance(EclipseConstants.LOCALE));
        }
        mouvementStock.setPersonne(this.userService.getPersonne());
        return super.save(mouvementStock);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MVT> getAll() {
        return this.getAll(new MvtStockSearchCriteria());
    }

    /**
     * Setter pour userService.
     * @param userService Le userService à écrire.
     */
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Getter pour userService.
     * @return Le userService
     */
    public UserService getUserService() {
        return this.userService;
    }

}

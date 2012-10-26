package fr.pharma.eclipse.service.dotation.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.model.dotation.Dotation;
import fr.pharma.eclipse.domain.model.stock.DispensationGlobale;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.factory.stock.MvtStockFactory;
import fr.pharma.eclipse.handler.habilitation.HabilitationHandler;
import fr.pharma.eclipse.predicate.stock.LigneStockCompletPredicate;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.dotation.DotationService;
import fr.pharma.eclipse.service.stock.MvtStockService;
import fr.pharma.eclipse.service.user.UserService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.validator.stock.StockValidator;

/**
 * Classe d'implémentation du service de gestion de dotation.
 
 * @version $Revision$ $Date$
 */
public class DotationServiceImpl
    extends GenericServiceImpl<Dotation>
    implements DotationService
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7369763460435579416L;

    /**
     * Service de gestion des utilisateurs.
     */
    @Resource(name = "userService")
    private UserService userService;

    /**
     * Validator de stock pour les lignes.
     */
    @Resource(name = "stockValidator")
    private StockValidator stockValidator;

    /**
     * Service de gestion des dispensations globales.
     */
    @Resource(name = "dispensationGlobaleService")
    private MvtStockService<DispensationGlobale> dispGlobService;

    /**
     * Factory des dispensations globales.
     */
    @Resource(name = "dispensationGlobaleFactory")
    private MvtStockFactory<DispensationGlobale> dispGlobFactory;

    /**
     * Gestionnaire d'habilitations sur les dotations.
     */
    @Resource(name = "essaiElementHabilitationHandler")
    private HabilitationHandler<Dotation> habilitationHandler;

    /**
     * Constructeur.
     * @param dotationDao Dao de gestion des dotations.
     */
    public DotationServiceImpl(final GenericDao<Dotation> dotationDao)
    {
        super(dotationDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dotation save(final Dotation dotation)
    {
        dotation.setPersonne(this.userService.getPersonne());
        dotation.setDateDemande(Calendar.getInstance(EclipseConstants.LOCALE));
        return super.save(dotation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveTraitementDotation(final Dotation dotation,
                                       final List<LigneStock> lignesStock)
    {
        // On ne garde que les lignes de stock complétées par l'utilisateur
        final List<LigneStock> lignesCompletees = new ArrayList<LigneStock>(lignesStock);
        CollectionUtils.filter(lignesCompletees,
                               new LigneStockCompletPredicate());

        // Validation des lignes de stock
        this.stockValidator.validateQteLignesStock(lignesCompletees);

        // Les lignes sont OK => enregistrement de n mouvements de dotation
        final List<DispensationGlobale> mvtsDotation = new ArrayList<DispensationGlobale>();
        for (final LigneStock ligneStock : lignesCompletees)
        {
            final DispensationGlobale mvt = this.dispGlobFactory.getInitializedObject();
            mvt.setDotation(dotation);
            mvt.setEssai(ligneStock.getEssai());
            mvt.setPharmacie(ligneStock.getPharmacie());
            mvt.setProduit(ligneStock.getProduit());
            mvt.setDatePeremption(ligneStock.getDatePeremption());
            mvt.setConditionnement(ligneStock.getConditionnement());
            mvt.setNumLot(ligneStock.getNumLot());
            mvt.setNumTraitement(ligneStock.getNumTraitement());
            mvt.setQuantite(ligneStock.getQteASortir());
            mvt.setApproApprouve(ligneStock.getApproApprouve());
            mvtsDotation.add(mvt);
        }

        // Sauvegarde des dispensations globales
        this.dispGlobService.saveAll(mvtsDotation);

        // Passage de la dotation en traitee si des mouvements
        dotation.setTraitee(Boolean.TRUE);
        this.getGenericDao().save(dotation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Dotation> getAll()
    {
        final List<Dotation> essais = super.getAll();
        // Purge des dotations par rapport aux habilitations
        this.habilitationHandler.purge(essais);
        return essais;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Dotation> getAll(final SearchCriteria criteria)
    {
        final List<Dotation> essais = super.getAll(criteria);
        // Purge des dotations par rapport aux habilitations
        this.habilitationHandler.purge(essais);
        return essais;
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
     * Setter pour stockValidator.
     * @param stockValidator Le stockValidator à écrire.
     */
    public void setStockValidator(final StockValidator stockValidator)
    {
        this.stockValidator = stockValidator;
    }

    /**
     * Setter pour dispGlobService.
     * @param dispGlobService Le dispGlobService à écrire.
     */
    public void setDispGlobService(final MvtStockService<DispensationGlobale> dispGlobService)
    {
        this.dispGlobService = dispGlobService;
    }

    /**
     * Setter pour dispGlobFactory.
     * @param dispGlobFactory Le dispGlobFactory à écrire.
     */
    public void setDispGlobFactory(final MvtStockFactory<DispensationGlobale> dispGlobFactory)
    {
        this.dispGlobFactory = dispGlobFactory;
    }

    /**
     * Setter pour habilitationHandler.
     * @param habilitationHandler le habilitationHandler à écrire.
     */
    public void setHabilitationHandler(final HabilitationHandler<Dotation> habilitationHandler)
    {
        this.habilitationHandler = habilitationHandler;
    }

}

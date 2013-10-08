package fr.pharma.eclipse.service.dotation.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.dotation.DotationSearchCriteria;
import fr.pharma.eclipse.domain.model.dotation.Dotation;
import fr.pharma.eclipse.domain.model.stock.DispensationGlobale;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.factory.stock.MvtStockFactory;
import fr.pharma.eclipse.predicate.stock.LigneStockCompletPredicate;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.dotation.DotationService;
import fr.pharma.eclipse.service.stock.MvtStockService;
import fr.pharma.eclipse.service.stock.StockService;
import fr.pharma.eclipse.service.user.UserService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.validator.stock.StockValidator;

/**
 * Classe d'implémentation du service de gestion de dotation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DotationServiceImpl extends GenericServiceImpl<Dotation> implements DotationService {
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

    @Resource(name = "stockService")
    private StockService stockService;

    /**
     * Constructeur.
     * @param dotationDao Dao de gestion des dotations.
     */
    public DotationServiceImpl(final GenericDao<Dotation> dotationDao) {
        super(dotationDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dotation save(final Dotation dotation) {
        dotation.setPersonne(this.userService.getPersonne());
        dotation.setDateDemande(Calendar.getInstance(EclipseConstants.LOCALE));
        return super.save(dotation);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveTraitementDotation(final Dotation dotation,
                                       final List<LigneStock> lignesStock) {
        // On ne garde que les lignes de stock complétées par l'utilisateur
        final List<LigneStock> lignesCompletees = new ArrayList<LigneStock>(lignesStock);
        CollectionUtils.filter(lignesCompletees, new LigneStockCompletPredicate());

        // Validation des lignes de stock
        this.stockValidator.validateQteLignesStock(lignesCompletees);

        // Les lignes sont OK => enregistrement de n mouvements de dotation
        final List<DispensationGlobale> mvtsDotation = new ArrayList<DispensationGlobale>();
        for (final LigneStock ligneStock : lignesCompletees) {
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
            ligneStock.setQteDispensationGlobal(ligneStock.getQteDispensationGlobal() + ligneStock.getQteASortir());
            ligneStock.setQteGlobalStock(ligneStock.getQteGlobalStock() - ligneStock.getQteASortir());
        }

        this.stockService.saveAll(lignesStock);

        // Sauvegarde des dispensations globales
        this.dispGlobService.saveAll(mvtsDotation);

        // Passage de la dotation en traitee si des mouvements
        dotation.setTraitee(Boolean.TRUE);
        this.getGenericDao().save(dotation);
    }

    public void setStockService(final StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Dotation> getAll() {
        return this.getAll(new DotationSearchCriteria());
    }

    /**
     * Setter pour userService.
     * @param userService Le userService à écrire.
     */
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Setter pour stockValidator.
     * @param stockValidator Le stockValidator à écrire.
     */
    public void setStockValidator(final StockValidator stockValidator) {
        this.stockValidator = stockValidator;
    }

    /**
     * Setter pour dispGlobService.
     * @param dispGlobService Le dispGlobService à écrire.
     */
    public void setDispGlobService(final MvtStockService<DispensationGlobale> dispGlobService) {
        this.dispGlobService = dispGlobService;
    }

    /**
     * Setter pour dispGlobFactory.
     * @param dispGlobFactory Le dispGlobFactory à écrire.
     */
    public void setDispGlobFactory(final MvtStockFactory<DispensationGlobale> dispGlobFactory) {
        this.dispGlobFactory = dispGlobFactory;
    }

}

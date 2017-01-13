package fr.pharma.eclipse.service.stock.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;
import org.apache.commons.collections15.functors.NotPredicate;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.stock.LigneStockSearchCriteria;
import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.criteria.stock.StockSearchCriteria;
import fr.pharma.eclipse.domain.enums.TypeDispensation;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.DispensationGlobale;
import fr.pharma.eclipse.domain.model.stock.EtatLigneStock;
import fr.pharma.eclipse.domain.model.stock.EtatStock;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stock.Sortie;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.predicate.stock.LigneStockEnQuarantainePredicate;
import fr.pharma.eclipse.predicate.stock.LigneStockPharmacieNonEpuisePredicate;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stock.ApprovisionnementService;
import fr.pharma.eclipse.service.stock.MvtStockService;
import fr.pharma.eclipse.service.stock.StockService;
import fr.pharma.eclipse.utils.message.MessageBuilder;

/**
 * Classe d'implémentation du service de gestion de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class StockServiceImpl extends GenericServiceImpl<LigneStock> implements StockService {
    public static final LigneStock NO_LIGNESTOCK = new LigneStock();

    private static final long serialVersionUID = 2491613712582659369L;
    private final Logger log = LoggerFactory.getLogger(StockServiceImpl.class);

    @Resource(name = "mouvementStockService")
    private MvtStockService<MvtStock> mvtStockService;

    @Resource(name = "produitService")
    private ProduitService<Produit> produitService;

    @Resource(name = "messageBuilder")
    private MessageBuilder messageBuilder;

    @Resource(name = "approvisionnementService")
    private ApprovisionnementService<Approvisionnement> approService;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void etendrePeremption(final Approvisionnement approNewDate) {

        final Approvisionnement approOldDate = this.approService.get(approNewDate.getId());
        final Calendar oldDatePeremption = approOldDate.getDatePeremption();       
        final Calendar newDatePeremption = approNewDate.getDatePeremption();
        final String commentaire = approNewDate.getCommentaireExtensionPeremption();

        if (!this.datesEqual(oldDatePeremption, newDatePeremption)) {
        	//AG - update de la ligne stock avant l'update du mouvement stock nécessaire sinon impossible de retrouver la ligne
        	//Stock correspondant à l'ancien mouvement stock
        	this.updateLigneStock(approOldDate, approNewDate);
	        this.updateDatePeremptionDesMvtStocks(approOldDate, newDatePeremption, commentaire);
	        
        }
    }

	/**
     * Mettre à jour la date de peremption des MvtStock associés à
     * l'approvisionnement. Si le MvtStock est du type "ENTREE" il faut mettre à
     * jour aussi :
     * <ul>
     * <li>l'historique
     * <li>le commentaire sur l'extension de péremption
     * </ul>
     * @param approOldDate l'approvisionnement choisi par l'utilisateur
     */
    protected void updateDatePeremptionDesMvtStocks(final Approvisionnement approOldDate,
                                                    final Calendar newDatePeremption,
                                                    final String commentaire) {
    
    	// Récupération de tous les types de mouvement associés à la clé
    	// fonctionelle pour mise à jour de la date
    	final LigneStockSearchCriteria criteria = this.buildCriteria(approOldDate);
    	final List<MvtStock> allMvts = this.mvtStockService.getAll(criteria);
    	for (final MvtStock mvtStock : allMvts) {
    		if (Arrays.asList(TypeMvtStock.ENTREES).contains(mvtStock.getType())) {
    			this.approService.updateDatePeremption((Approvisionnement) mvtStock, newDatePeremption, commentaire);
    		} else {
    			mvtStock.setDatePeremption(newDatePeremption);
    		}	
    	}	
    	this.mvtStockService.saveAll(allMvts);
    }
	        

    /**
     * Création ou mise à jour d'une ligne stock après la mise à jour de la date
     * de péremption d'un approvisionnement.
     * @param appro l'approvisionnement avec la date à jour
     */
    protected void updateLigneStock(Approvisionnement approOldDate, Approvisionnement approNewDate) {
    	final LigneStock ligneStockOldDate = this.getOrCreateLigneStock(approOldDate);
    	final LigneStock ligneStockNewDate = this.getLigneStock(approNewDate);
    	
    	if (NO_LIGNESTOCK.equals(ligneStockNewDate)) {
    		ligneStockOldDate.setDatePeremption(approNewDate.getDatePeremption());
    		save(ligneStockOldDate);
    	} else {
    		// Fusion avec la ligne stock qui existe déjà
    		ligneStockOldDate.setQtePharmacie(ligneStockOldDate.getQtePharmacie() + ligneStockNewDate.getQtePharmacie());
    		ligneStockOldDate.setQteDispensationGlobal(ligneStockOldDate.getQteDispensationGlobal() + ligneStockNewDate.getQteDispensationGlobal());
    		ligneStockOldDate.setDatePeremption(approNewDate.getDatePeremption());
    		this.save(ligneStockOldDate);
    		// Suppression de la ligne de stock
    		this.remove(ligneStockNewDate);
    	}
    }
    
    /**
     * @return true si les dates sont égales
     */
    boolean datesEqual(final Calendar date1,
                       final Calendar date2) {
        if ((date1 == null) && (date2 == null)) {
            return true;
        } else if (date1 == null) {
            return false;
        } else {
            return date1.equals(date2);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public <MVT extends MvtStock> void update(final List<MVT> mvtStocks) {

        final List<LigneStock> lignesStocks = new ArrayList<LigneStock>();

        for (final MvtStock mvtStock : mvtStocks) {
            final TypeMvtStock typeMvtStock = mvtStock.getType();
            switch (typeMvtStock) {
                case APPROVISIONNEMENT :
                case ENTREE_CORRECTIVE :
                case PREPARATION_ENTREE :
                    lignesStocks.add(this.updateAppro((Approvisionnement) mvtStock));
                    break;
                case PREPARATION_SORTIE :
                case RETOUR_PROMOTEUR :
                case CESSION_PUI :
                case DESTRUCTION :
                case AUTRE_SORTIE :
                    final LigneStock ligneStock = this.updateSortie(mvtStock);
                    if (!StockServiceImpl.NO_LIGNESTOCK.equals(ligneStock)) {
                        lignesStocks.add(ligneStock);
                    }
                    break;
            }
        }
        this.saveAll(lignesStocks);
    }

    /**
     * Saufgarder ou supprimer si les stocks sont à 0 ou negatifs ; MAJ lieu de
     * stockage aussi
     * @return la valeur enregistrer ou null si les stocks sont à 0
     */
    @Override
    public LigneStock save(final LigneStock ligneStock) {
        LigneStock saved = null;
        if (ligneStock != null) {

            // Correction des quantités négatives
            if ((ligneStock.getQtePharmacie() == null) || (ligneStock.getQtePharmacie() < 0)) {
                this.log.error("Erreur LigneStock id " + ligneStock.getId() + "; quantitite stock negative : " + ligneStock.getQtePharmacie());
                ligneStock.setQtePharmacie(0);
            }
            if ((ligneStock.getQteDispensationGlobal() == null) || (ligneStock.getQteDispensationGlobal() < 0)) {
                this.log.error("Erreur LigneStock id " + ligneStock.getId() + "; quantitite dispensation negative : " + ligneStock.getQteDispensationGlobal());
                ligneStock.setQteDispensationGlobal(0);
            }

            // MAJ lieu de stockage
            // Le lieu de stockage dans lignestock est une copie ; on profite de
            // l'enregistrement
            // pour le mettre à jour s'il a changé
            this.updateLieuStockage(ligneStock);

            saved = super.save(ligneStock);

            // On ne garde pas la ligne de stock si les quantités sont à 0
            if ((saved != null) && (saved.getQtePharmacie() != null) && (saved.getQteDispensationGlobal() != null) && Integer.valueOf(0).equals(saved.getQtePharmacie())
                && Integer.valueOf(0).equals(saved.getQteDispensationGlobal())) {
                this.remove(ligneStock);
                saved = null;
            }
        }
        return saved;
    }

    /**
     * Mise à jour d'un mouvement d'approvisionnement.
     * @param appro Approvisionnement à mettre à jour.
     * @param stockage Lieu de stockage.
     */
    private LigneStock updateAppro(final Approvisionnement appro) {

        // Récupération de la ligne de stock associée au mouvement
        final LigneStock ligneStock = this.getOrCreateLigneStock(appro);

        if (ligneStock != null) {
            // Valorisation du lieu de stockage
            if (Boolean.FALSE.equals(appro.getApproApprouve())) {
                ligneStock.setStockage(LigneStock.EN_QUARANTAINE);
            } else {
                // MAJ du lieu de stockage pour le produit et la
                // pharmacie
                this.updateLieuStockage(ligneStock);
            }
            ligneStock.setQtePharmacie(ligneStock.getQtePharmacie() + appro.getQuantite());
        }
        return ligneStock;
    }

    /**
     * Mise à jour d'un mouvement de sortie.
     * @param sortie Mouvement de sortie.
     * @return Ligne de stock.
     */
    private LigneStock updateSortie(final MvtStock sortie) {
        // Récupération de la ligne de stock associée au mouvement
        final LigneStock ligneStock = this.getLigneStock(sortie);
        if (!StockServiceImpl.NO_LIGNESTOCK.equals(ligneStock) && (ligneStock != null)) {
            ligneStock.setQtePharmacie(ligneStock.getQtePharmacie() - sortie.getQuantite());
        }
        return ligneStock;
    }

    /**
     * Tentative de MAJ de lieu de stockage. Si les entités sont null ou
     * non-initialisées, la MAJ ne se fait pas.
     */
    private void updateLieuStockage(final LigneStock ligneStock) {
        // Pas de MAJ si stock en quarantaine
        if (LigneStock.EN_QUARANTAINE.equals(ligneStock.getStockage())) {
            return;
        }

        if ((ligneStock.getProduit() != null) && Hibernate.isInitialized(ligneStock.getProduit()) 
            && (ligneStock.getPharmacie() != null) && Hibernate.isInitialized(ligneStock.getPharmacie())) {
            final Produit produit = this.produitService.get(ligneStock.getProduit().getId());
            final Stockage stockage = this.produitService.getStockageProduitPharma(produit, ligneStock.getPharmacie());
            ligneStock.setStockage((stockage == null) ? "" : stockage.getNomComplet());
        }
    }

    /**
     * Récupérer une ligne de stock répondant à la clé fonctionnelle
     * @return Ligne de stock; new LigneStock si elle n'existe pas; null s'il y
     * a plusieurs lignes qui correspondent
     */
    protected LigneStock getOrCreateLigneStock(final MvtStock mvt) {
        final LigneStock result = this.getLigneStock(mvt);

        if (StockServiceImpl.NO_LIGNESTOCK.equals(result)) {
            return new LigneStock(mvt.getEssai(), mvt.getPharmacie(), mvt.getProduit(), mvt.getConditionnement(), mvt.getApproApprouve(), mvt.getDatePeremption(), mvt.getNumLot(),
                                  mvt.getNumTraitement());
        } else {
            return result;
        }
    }

    /**
     * @return ligneStock qui corresponde au MvtStock en entrée ; NO_LIGNESTOCK
     * sinon
     */
    @Override
    public LigneStock getLigneStock(final MvtStock mvt) {
        final LigneStockSearchCriteria criteria = this.buildCriteria(mvt);
        final List<LigneStock> results = this.getAll(criteria);
        // 1 résultat sur la clé fonctionnelle
        if (results.size() > 1) {
            return this.fusion(results);
        } else if (results.size() == 1) {
            return results.get(0);
        } else {
            return StockServiceImpl.NO_LIGNESTOCK;
        }
    }

    /**
     * Fusionner les N lignes qui correspondent à un MvtStock
     * @return LigneStock après la fusion
     */
    protected LigneStock fusion(final List<LigneStock> lignesStocks) {
        Integer qtePharmacie = 0;
        Integer qteDispensationGlobal = 0;
        for (final LigneStock ligne : lignesStocks) {
            qtePharmacie += ligne.getQtePharmacie();
            qteDispensationGlobal += ligne.getQteDispensationGlobal();
        }

        final LigneStock result = lignesStocks.get(0);
        result.setQtePharmacie(qtePharmacie);
        result.setQteDispensationGlobal(qteDispensationGlobal);

        return result;
    }

    /**
     * Méthode en charge de construire le critère de recherche fonctionnel sur
     * la clé complète pour la récupération des lignes de stock <br/>
     * . Essai, Pharmacie, Produit, Conditionnement, NumLot, NunTraitement,
     * ApproApprouve, DatePeremption.
     * @param mvt Mouvement de stock.
     * @return Critère de recherche.
     */
    private LigneStockSearchCriteria buildCriteria(final MvtStock mvt) {
        final LigneStockSearchCriteria criteria = new LigneStockSearchCriteria();
        criteria.setEssai(mvt.getEssai());
        criteria.setPharmacie(mvt.getPharmacie());
        criteria.setProduit(mvt.getProduit());
        criteria.setConditionnement(mvt.getConditionnement());
        criteria.setNumLot(mvt.getNumLot());
        criteria.setNumTraitement(mvt.getNumTraitement());
        criteria.setApproApprouve(mvt.getApproApprouve());
        criteria.setDatePeremption(mvt.getDatePeremption());
        return criteria;
    }

    /**
     * @param genericDao
     */
    public StockServiceImpl(final GenericDao<LigneStock> genericDao) {
        super(genericDao);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public List<LigneStock> initialiseTableLigneStock() {

        if (this.getGenericDao().count() <= 0) {
            final Map<String, LigneStock> mapLines = new HashMap<String, LigneStock>();

            final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
            criteria.setWithAcl(Boolean.FALSE);
            
            // Construction des lignes de stock à partir des entrées
            this.buildFromMvtTypeEntree(criteria, mapLines);

            // Mise à jour des lignes de stock à partir des sorties
            this.updateFromMvtTypeSortie(criteria, mapLines);

            // Traitement des dispensations globales (non sorties du stock mais
            // non visible sauf à la dispensation de ces produits).
            this.updateFromDispensationGlobales(criteria, mapLines);

            return this.saveAll(new ArrayList<LigneStock>(mapLines.values()));
        } else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LigneStock> getLignesStockPharmacie(final Essai essai,
                                                    final Pharmacie pharmacie,
                                                    final Produit produit,
                                                    final Conditionnement conditionnement) {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        criteria.setEssai(essai);
        criteria.setPharmacie(pharmacie);
        criteria.setProduit(produit);
        criteria.setConditionnement(conditionnement);
        final List<LigneStock> result = super.getAll(criteria);

        return new ArrayList<LigneStock>(CollectionUtils.select(result, new LigneStockPharmacieNonEpuisePredicate()));
    }

    /**
     * Méthode en charge d'initialiser les lignes de stock à partir des
     * mouvements de stock de type Dispensation globales.
     * @param essai L'essai .
     * @param pharmacie La pharmacie.
     * @param produit Le produit.
     * @param conditionnement Le conditionnement.
     * @param mapLines Les lignes contenants les résultats.
     */
    private void updateFromDispensationGlobales(final MvtStockSearchCriteria critEntree,
                                                final Map<String, LigneStock> mapLines) {
        critEntree.setTypeMouvement(TypeMvtStock.DOTATION);

        final List<MvtStock> dispGlobales = this.mvtStockService.getAll(critEntree);

        for (final MvtStock disp : dispGlobales) {
            final DispensationGlobale dotation = (DispensationGlobale) disp;
            final String key = this.getKeyMvtStock(dotation, false);
            if (mapLines.containsKey(key)) {
                final LigneStock ligneStock = mapLines.get(key);
                // Mise à jour de la quantité réelle en stock
                ligneStock.setQteDispensationGlobal((dotation.getQuantite() - dotation.getQuantiteDispensee()));
            } else {
                this.log.error("Aucun Mvt d'entrée correspondant à la dispensation - idMvt " + disp.getId());
            }
        }
    }

    private LigneStock createLigneStock(final MvtStock mvt) {
        final LigneStock ligneStock = new LigneStock(mvt.getEssai(), mvt.getPharmacie(), mvt.getProduit(), mvt.getConditionnement(), mvt.getApproApprouve());
        ligneStock.setNumLot(mvt.getNumLot());
        ligneStock.setNumTraitement(mvt.getNumTraitement());
        ligneStock.setDatePeremption(mvt.getDatePeremption());
        ligneStock.setQtePharmacie(mvt.getQuantite());
        return ligneStock;
    }

    /**
     * Méthode en charge d'initialiser les lignes de stock à partir des
     * mouvements de stock de type Entree.
     * @param essai Essai.
     * @param pharmacie Pharmacie.
     * @param produit Produit.
     * @param conditionnement Conditionnement.
     * @param mapLines Map des lignes de stock à construire.
     */
    private void buildFromMvtTypeEntree(final MvtStockSearchCriteria critEntree,
                                        final Map<String, LigneStock> mapLines) {
        // Récupération des mouvements de type Entree qui concernent l'essai, la
        // pharmacie, le produit et le conditionnement
        critEntree.setTypesMouvement(TypeMvtStock.ENTREES);
        critEntree.setActiveOrder("datePeremption");

        final List<MvtStock> appros = this.mvtStockService.getAll(critEntree);

        for (final MvtStock mouvementStock : appros) {
            final Approvisionnement appro = (Approvisionnement) mouvementStock;
            final LigneStock ligneStock = this.createLigneStock(mouvementStock);

            if (appro.getApproApprouve()) {
                final Produit prod = this.produitService.reattach(appro.getProduit());
                final Stockage stockage = this.produitService.getStockageProduitPharma(prod, appro.getPharmacie());
                ligneStock.setStockage((stockage == null) ? "??" : stockage.getNomComplet());
            } else {
                ligneStock.setStockage(LigneStock.EN_QUARANTAINE);
            }

            final String key = this.getKeyMvtStock(mouvementStock, false);

            // Une entrée existe déjà => cumul des quantités saisies
            if (mapLines.get(key) != null) {
                final LigneStock l = mapLines.get(key);
                l.setQtePharmacie(l.getQtePharmacie() + ligneStock.getQtePharmacie());
            } else {
                mapLines.put(key, ligneStock);
            }
            this.log.debug("Entrée key: " + key + " object: " + ligneStock + "Mvt Id: " + mouvementStock.getId());
        }
    }

    /**
     * Méthode en charge de mettre à jour les lignes de stock à partir des
     * mouvements de stock de type Sortie. La mise à jour concerne la quantité.
     * @param essai Essai.
     * @param pharmacie Pharmacie.
     * @param produit Produit.
     * @param conditionnement Conditionnement.
     * @param mapLines Map des lignes de stock à mettre à jour.
     */
    private void updateFromMvtTypeSortie(final MvtStockSearchCriteria critSortie,
                                         final Map<String, LigneStock> mapLines) {
        // Récupération des mouvements de type Sortie qui concernant l'essai, la
        // pharmacie, le
        // produit et le conditionnement
        critSortie.setTypesMouvement(TypeMvtStock.ALL_SORTIES);
        critSortie.setActiveOrder("datePeremption");

        final List<MvtStock> sorties = this.mvtStockService.getAll(critSortie);

        // on ne traite pas ici les dispensations globales car elles ne sortent
        // pas du stocks.
        CollectionUtils.filter(sorties, new Predicate<MvtStock>() {
            @Override
            public boolean evaluate(final MvtStock object) {
                return !object.getType().equals(TypeMvtStock.DOTATION);
            }
        });

        for (final MvtStock mvtStock : sorties) {
            final String key = this.getKeyMvtStock(mvtStock, false);
            if (mapLines.containsKey(key)) {
                final LigneStock ligneStock = mapLines.get(key);
                this.log.debug("Sortie Key: " + key + " objec: " + ligneStock);
                // Mise à jour de la quantité réelle en stock
                ligneStock.setQtePharmacie(ligneStock.getQtePharmacie() - mvtStock.getQuantite());
            } else {
                this.log.error("Aucun Mvt d'entrée correspondant à la sortie - idMvt " + mvtStock.getId());
            }

        }
    }

    /**
     * {@inheritDoc}
     */
    public String getKeyMvtStock(final MvtStock mvtStock,
                                 final boolean datesPeremptionFusionnees) {
        final StringBuilder sb = new StringBuilder();
        sb.append(mvtStock.getEssai().getId());
        sb.append(mvtStock.getPharmacie().getId());
        sb.append(mvtStock.getProduit().getId());
        sb.append(mvtStock.getConditionnement().getId());
        sb.append(mvtStock.getNumLot());
        sb.append(mvtStock.getNumTraitement());
        sb.append(mvtStock.getApproApprouve());

        if (!datesPeremptionFusionnees && (mvtStock.getDatePeremption() != null)) {
            final StringBuilder datePeremptionAsString = new StringBuilder();

            datePeremptionAsString.append(mvtStock.getDatePeremption().get(Calendar.YEAR)).append(mvtStock.getDatePeremption().get(Calendar.MONTH))
                    .append(mvtStock.getDatePeremption().get(Calendar.DAY_OF_MONTH));

            sb.append(datePeremptionAsString);
        }
        return sb.toString();
    }

    @Override
    public void addQuarantaineToStock(final EtatStock etat,
                                      final EtatLigneStock ligne) {

        // Récupération des mouvements associés à la ligne
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        criteria.setEssai(etat.getEssai());
        criteria.setPharmacie(etat.getPharmacie());
        criteria.setProduit(etat.getProduit());
        criteria.setConditionnement(etat.getConditionnement());
        criteria.setNumLot(ligne.getNumLot());
        criteria.setNumTraitement(ligne.getNumTraitement());

        for (final MvtStock mvt : this.mvtStockService.getAll(criteria)) {
            mvt.setApproApprouve(true);
            this.mvtStockService.save(mvt);
        }

        // Récupération des lignes de stock pour mettre à jour le lieu de
        // stockage ainsi que l'info de quarantaine
        final StockSearchCriteria stockCriteria = new StockSearchCriteria();
        stockCriteria.setEssai(etat.getEssai());
        stockCriteria.setPharmacie(etat.getPharmacie());
        stockCriteria.setProduit(etat.getProduit());
        stockCriteria.setConditionnement(etat.getConditionnement());
        stockCriteria.setNumLotStrict(ligne.getNumLot());
        stockCriteria.setNumTraitement(ligne.getNumTraitement());

        final List<LigneStock> ligneStocks = this.getAll(stockCriteria);
        for (final LigneStock ligneStock : ligneStocks) {
            final Produit prod = this.produitService.reattach(etat.getProduit());
            ligneStock.setStockage(this.produitService.getStockageProduitPharma(prod, etat.getPharmacie()).getNomComplet());
            ligneStock.setApproApprouve(true);
        }
        this.saveAll(ligneStocks);
    }

    @Override
    public void initLignesStock(final TypeDispensation typeDispensation,
                                final Sortie sortieCurrent) {
        switch (typeDispensation) {
            case NOMINATIVE :
                this.initLignesStockDispensationNominative(sortieCurrent);
                break;
            case GLOBALE :
                this.initLigneStockDispensationGlobale(sortieCurrent);
                break;
            default :
                sortieCurrent.setLignesStock(new ArrayList<LigneStock>());
                break;
        }
    }

    private void initLigneStockDispensationGlobale(final Sortie sortie) {
        final MvtStock mvt = sortie.getMvtSortie();
        final StockSearchCriteria criteria = new StockSearchCriteria();
        criteria.setProduit(mvt.getProduit());
        criteria.setPharmacie(mvt.getPharmacie());
        criteria.setEssai(mvt.getEssai());
        criteria.setConditionnement(mvt.getConditionnement());
        criteria.setNotNullQteDispensationGlobal(Boolean.TRUE);

        final List<LigneStock> lignesStock = this.getAll(criteria);
        for (final LigneStock ligneStock : lignesStock) {
            ligneStock.setDotation(Boolean.TRUE);
        }
        sortie.setLignesStock(lignesStock);
    }

    private void initLignesStockDispensationNominative(final Sortie sortie) {
        final MvtStock mvt = sortie.getMvtSortie();

        final List<LigneStock> lignesStock = this.getLignesStockPharmacie(mvt.getEssai(), mvt.getPharmacie(), mvt.getProduit(), mvt.getConditionnement());

        // Filtre des stocks en quarantaine.
        org.apache.commons.collections15.CollectionUtils.filter(lignesStock, new NotPredicate<LigneStock>(new LigneStockEnQuarantainePredicate()));

        sortie.setLignesStock(lignesStock);
    }

}

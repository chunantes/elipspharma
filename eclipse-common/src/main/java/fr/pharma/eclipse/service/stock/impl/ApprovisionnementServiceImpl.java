package fr.pharma.eclipse.service.stock.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.stock.ExtensionPeremptionSearchCriteria;
import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.LigneStock;
import fr.pharma.eclipse.domain.model.stock.NumTraitement;
import fr.pharma.eclipse.domain.model.stock.PreparationEntree;
import fr.pharma.eclipse.domain.model.stock.ReceptionLot;
import fr.pharma.eclipse.domain.model.stock.ResultApprovisionnement;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.factory.stock.ApproFactory;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stock.ApprovisionnementService;
import fr.pharma.eclipse.service.stock.StockService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.message.MessageBuilder;

/**
 * Classe d'implémentation du service de gestion des approvisionnements.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ApprovisionnementServiceImpl<MVT extends Approvisionnement> extends MvtStockServiceImpl<MVT> implements ApprovisionnementService<MVT> {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -546213603604898298L;

    private Map<TypeMvtStock, ApproFactory<MVT>> approFactories;

    @Resource(name = "produitService")
    private ProduitService<Produit> produitService;

    @Resource(name = "essaiService")
    private EssaiService essaiService;

    @Resource(name = "stockService")
    private StockService stockService;

    @Resource(name = "messageBuilder")
    private MessageBuilder messageBuilder;

    /**
     * Constructeur.
     * @param approvisionnementDao Dao de gestion des approvisionnements.
     */
    public ApprovisionnementServiceImpl(final GenericDao<MVT> approvisionnementDao) {
        super(approvisionnementDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasAlreadyApprovisionnement(final Essai essai) {
        final MvtStockSearchCriteria crit = new MvtStockSearchCriteria();
        crit.setEssai(essai);
        return !this.getAll(crit).isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public ResultApprovisionnement save(final List<ReceptionLot> receptionLots) {

        final ResultApprovisionnement resultAppro = new ResultApprovisionnement();
        final List<MVT> appros = new ArrayList<MVT>();

        for (final ReceptionLot receptionLot : receptionLots) {

            // Récupération du lieu de stockage pour le produit et la pharmacie
            final Produit produit = this.produitService.get(receptionLot.getAppro().getProduit().getId());

            final Stockage stockage = this.produitService.getStockageProduitPharma(produit, receptionLot.getAppro().getPharmacie());
            receptionLot.setStockage(stockage);
            this.handleReceptionLot(receptionLot, appros, resultAppro);
        }

        // mise à jours de la date de réception et date d'activation.
        if (!appros.isEmpty()) {
            Collections.sort(appros, new Comparator<Approvisionnement>() {

                @Override
                public int compare(final Approvisionnement o1,
                                   final Approvisionnement o2) {
                    return o2.getDateReception().compareTo(o1.getDateReception());
                }

            });
            final Approvisionnement appro = appros.get(0);
            final Essai essai = this.essaiService.get(appro.getEssai().getId());
            essai.getDetailDates().setReception(appro.getDateReception());
            if ((essai.getDetailDates().getDebutEtude() == null) || essai.getDetailDates().getDebutEtude().before(appro.getDateReception())) {
                essai.getDetailDates().setActivation(appro.getDateReception());
                this.essaiService.save(essai);
            }
        }

        // Sauvegarde de la liste des approvisionnements
        super.saveAll(appros);

        // Sauvegarde du stock
        this.stockService.update(appros);

        // Méthode en charge de compléter les informations du résultat
        // d'approvisionnement
        this.completeResult(resultAppro);

        // si c'est la première réception on met à jour l'état de l'essai.
        final Essai essai = this.essaiService.get(resultAppro.getEssai().getId());
        if (essai.getEtat().equals(EtatEssai.MISE_EN_PLACE)) {
            essai.getDetailDates().setActivation(Calendar.getInstance(EclipseConstants.LOCALE));
            essai.setEtat(EtatEssai.EN_COURS);
            this.essaiService.addDetailEtatEssai(essai, EtatEssai.EN_COURS, "Première réception.");
            this.essaiService.save(essai);
        }

        return resultAppro;
    }

    /**
     * Méthode en charge de retourner l'instance de MVT fonctionnelle dans une
     * liste.
     * @param result Liste dans laquelle chercher.
     * @param mvt Mouvement.
     * @return Instance de MVT.
     */
    private MVT retrieveMvt(final List<MVT> result,
                            final MVT mvt) {
        for (final MVT res : result) {
            if (res != null) {
                final String keyMvt = this.stockService.getKeyMvtStock(mvt, false);
                final String keyRes = this.stockService.getKeyMvtStock(res, false);
                if (keyMvt.equals(keyRes)) {
                    return res;
                }
            }
        }
        return null;
    }

    /**
     * Méthode en charge de compléter les informations de récapitulatif de
     * l'approvisionnement.
     * @param resultAppro Récapitulatif à compléter.
     */
    protected void completeResult(final ResultApprovisionnement resultAppro) {
        if (resultAppro.getReceptionLots().size() > 0) {
            final ReceptionLot receptionLot = resultAppro.getReceptionLots().get(0);
            final Approvisionnement appro = receptionLot.getAppro();
            resultAppro.setEssai(appro.getEssai());
            resultAppro.setPromoteur(appro.getEssai().getPromoteur());
            resultAppro.setPharmacie(appro.getPharmacie());
            resultAppro.setDateAppro(Calendar.getInstance(EclipseConstants.LOCALE));
            resultAppro.setPersonne(this.getUserService().getPersonne());
        }
    }

    /**
     * Méthode en charge de gérer l'enregistrement d'une réception de lot.
     * @param receptionLot Informations d'une réception de lot.
     * @param appros Liste des approvisionnements à compléter.
     * @param resultAppro Résultat de l'approvisionnement à compléter.
     */
    @SuppressWarnings("unchecked")
    protected void handleReceptionLot(final ReceptionLot receptionLot,
                                      final List<MVT> appros,
                                      final ResultApprovisionnement resultAppro) {

        final MVT appro = (MVT) receptionLot.getAppro();
        final Conditionnement conditionnement = appro.getConditionnement();

        boolean addReception = false;

        // La réception de lot est en mode NUM_TRAITEMENT => n
        // approvisionnements
        if (ModePrescription.NUM_TRAITEMENT.equals(conditionnement.getModePrescription())) {
            final List<NumTraitement> numsTraitements = receptionLot.getNumsTraitements();
            for (final NumTraitement numTraitement : numsTraitements) {
                if (this.isNumTraitementValide(numTraitement)) {
                    final MVT newAppro = this.approFactories.get(appro.getType()).getInitializedObject();
                    newAppro.setEssai(appro.getEssai());
                    newAppro.setProduit(appro.getProduit());
                    newAppro.setConditionnement(appro.getConditionnement());
                    newAppro.setPharmacie(appro.getPharmacie());
                    newAppro.setNumLot(appro.getNumLot());
                    newAppro.setDatePeremption(appro.getDatePeremption());
                    newAppro.setNumTraitement(numTraitement.getNumTraitement());
                    newAppro.setQuantite(numTraitement.getQuantite());
                    newAppro.setApproApprouve(appro.getApproApprouve());
                    newAppro.setDateReception(appro.getDateReception());
                    newAppro.setDateArriveeColis(appro.getDateArriveeColis());
                    newAppro.setMotifRefus(appro.getMotifRefus());
                    if (appro.getType().equals(TypeMvtStock.PREPARATION_ENTREE)) {
                        final PreparationEntree castNewAppro = (PreparationEntree) newAppro;
                        final PreparationEntree casAppro = (PreparationEntree) appro;
                        castNewAppro.setComposition(casAppro.getComposition());
                        castNewAppro.setDateFabrication(casAppro.getDateReception());
                        castNewAppro.setNumOrdonnancier(casAppro.getNumOrdonnancier());
                        castNewAppro.setSterile(casAppro.getSterile());
                    }
                    newAppro.setCommentaireRefus(appro.getCommentaireRefus());

                    final MVT res = this.retrieveMvt(appros, newAppro);
                    if (res == null) {
                        addReception = true;
                        appros.add(newAppro);
                    } else {
                        res.setQuantite(res.getQuantite() + newAppro.getQuantite());
                    }
                }
            }
        } else {
            final MVT res = this.retrieveMvt(appros, appro);
            if (res == null) {
                addReception = true;
                appros.add(appro);
            } else {
                res.setQuantite(res.getQuantite() + appro.getQuantite());
            }
        }
        if (addReception) {
            resultAppro.getReceptionLots().add(receptionLot);
        }
    }

    /**
     * Méthode en charge de déterminer si les informations d'un numéro de
     * traitement sont valides.
     * @param numTraitement Numéro de traitement.
     * @return Résultat du test.
     */
    protected boolean isNumTraitementValide(final NumTraitement numTraitement) {
        return StringUtils.isNotEmpty(numTraitement.getNumTraitement()) && (numTraitement.getQuantite() != null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MVT> getAllApprovisionnementAvecStockPositif(final ExtensionPeremptionSearchCriteria criteria) {

        // Récuperation des approvisionnements.
        final List<MVT> approvisionnements = super.getAll(criteria);

        // Enlever les approvisionnements avec un stock nul.
        CollectionUtils.filter(approvisionnements, new Predicate() {
            @Override
            public boolean evaluate(final Object object) {
                final Approvisionnement appro = (Approvisionnement) object;
                final LigneStock ligneStock = ApprovisionnementServiceImpl.this.stockService.getLigneStock(appro);
                return (ligneStock.getQteGlobalStock() > 0);
            }
        });

        return approvisionnements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDatePeremption(final Approvisionnement appro,
                                     final Calendar newDatePeremption) {
        final StringBuilder historique = new StringBuilder();

        if (appro.getHistoriqueExtensionPeremption() != null) {
            historique.append(appro.getHistoriqueExtensionPeremption());
            historique.append(EclipseConstants.SAUT_LIGNE);
        }

        if (appro.getDatePeremption() == null) {
            final Object[] params = new Object[]{new Date(), newDatePeremption.getTime() };
            historique.append(this.messageBuilder.getMessage("extensionPeremption.historique.creation", params));
        } else {
            final Object[] params = new Object[]{new Date(), appro.getDatePeremption().getTime(), newDatePeremption.getTime() };
            historique.append(this.messageBuilder.getMessage("extensionPeremption.historique.modification", params));
        }

        appro.setHistoriqueExtensionPeremption(historique.toString());

        // Est-ce que nous sommes en train de reporter la date ?
        if (!appro.getExtensionPeremption() && (appro.getDatePeremption().getTimeInMillis() < newDatePeremption.getTimeInMillis())) {
            appro.setExtensionPeremption(true);
        }
        appro.setDatePeremption(newDatePeremption);
    }

    public void setProduitService(final ProduitService<Produit> produitService) {
        this.produitService = produitService;
    }
    public void setEssaiService(final EssaiService essaiService) {
        this.essaiService = essaiService;
    }
    public void setApproFactories(final Map<TypeMvtStock, ApproFactory<MVT>> approFactories) {
        this.approFactories = approFactories;
    }
    public void setStockService(final StockService stockService) {
        this.stockService = stockService;
    }
    public void setMessageBuilder(final MessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
    }

}

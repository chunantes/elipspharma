package fr.pharma.eclipse.service.stock.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.NumTraitement;
import fr.pharma.eclipse.domain.model.stock.PreparationEntree;
import fr.pharma.eclipse.domain.model.stock.ReceptionLot;
import fr.pharma.eclipse.domain.model.stock.ResultApprovisionnement;
import fr.pharma.eclipse.domain.model.stockage.Stockage;
import fr.pharma.eclipse.factory.stock.ApproFactory;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stock.ApprovisionnementService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe d'implémentation du service de gestion des approvisionnements.
 
 * @version $Revision$ $Date$
 */
public class ApprovisionnementServiceImpl<MVT extends Approvisionnement>
    extends MvtStockServiceImpl<MVT>
    implements ApprovisionnementService<MVT>
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -546213603604898298L;

    /**
     * Factrory.
     */
    private Map<TypeMvtStock, ApproFactory<MVT>> approFactorys;

    /**
     * Service de gestion des produits.
     */
    @Resource(name = "produitService")
    private ProduitService<Produit> produitService;

    /**
     * Service essai .
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * Constructeur.
     * @param approvisionnementDao Dao de gestion des approvisionnements.
     */
    public ApprovisionnementServiceImpl(final GenericDao<MVT> approvisionnementDao)
    {
        super(approvisionnementDao);
    }

    /**
     * Retourne true si l'essai en paramètre a déjà fait l'objet d'une réception.
     * @param essai Essai.
     * @return true si l'essai en paramètre a déjà fait l'objet d'une réception.
     */
    public boolean hasAlreadyApprovisionnement(final Essai essai)
    {
        final MvtStockSearchCriteria crit = new MvtStockSearchCriteria();
        crit.setEssai(essai);
        return !this.getAll(crit).isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    public ResultApprovisionnement save(final List<ReceptionLot> receptionLots)
    {

        final ResultApprovisionnement resultAppro = new ResultApprovisionnement();
        final List<MVT> appros = new ArrayList<MVT>();

        for (final ReceptionLot receptionLot : receptionLots)
        {
            this.handleReceptionLot(receptionLot,
                                    appros,
                                    resultAppro);

            // Récupération du lieu de stockage pour le produit et la pharmacie
            final Produit produit =
                this.produitService.get(receptionLot.getAppro().getProduit().getId());

            final Stockage stockage =
                this.produitService.getStockageProduitPharma(produit,
                                                             receptionLot
                                                                     .getAppro()
                                                                     .getPharmacie());
            receptionLot.setStockage(stockage);
        }

        // mise à jours de la date de réception et date d'activation.
        if (!appros.isEmpty())
        {
            Collections.sort(appros,
                             new Comparator<Approvisionnement>() {

                                 @Override
                                 public int compare(final Approvisionnement o1,
                                                    final Approvisionnement o2)
                                 {
                                     return o2
                                             .getDateReception()
                                             .compareTo(o1.getDateReception());
                                 }

                             });
            final Approvisionnement appro = appros.get(0);
            final Essai essai = this.essaiService.get(appro.getEssai().getId());
            essai.getDetailDates().setReception(appro.getDateReception());
            final MvtStockSearchCriteria crit = new MvtStockSearchCriteria();
            crit.setEssai(essai);
            if (essai.getDetailDates().getDebutEtude() == null
                || essai.getDetailDates().getDebutEtude().before(appro.getDateReception()))
            {
                essai.getDetailDates().setActivation(appro.getDateReception());
                this.essaiService.save(essai);
            }
        }

        // Sauvegarde de la liste des approvisionnements
        super.saveAll(appros);

        // Méthode en charge de compléter les informations du résultat d'approvisionnement
        this.completeResult(resultAppro);

        // si c'est la première réception on met à jour l'état de l'essai.
        final Essai essai = this.essaiService.get(resultAppro.getEssai().getId());
        if (essai.getEtat().equals(EtatEssai.MISE_EN_PLACE))
        {
            essai.getDetailDates().setActivation(Calendar.getInstance(EclipseConstants.LOCALE));
            essai.setEtat(EtatEssai.EN_COURS);
            this.essaiService.addDetailEtatEssai(essai,
                                                 EtatEssai.EN_COURS,
                                                 "Première réception.");
            this.essaiService.save(essai);
        }

        return resultAppro;
    }

    /**
     * Méthode en charge de compléter les informations de récapitulatif de l'approvisionnement.
     * @param resultAppro Récapitulatif à compléter.
     */
    protected void completeResult(final ResultApprovisionnement resultAppro)
    {
        if (resultAppro.getReceptionLots().size() > 0)
        {
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
    protected void handleReceptionLot(final ReceptionLot receptionLot,
                                      final List<MVT> appros,
                                      final ResultApprovisionnement resultAppro)
    {
        final MVT appro = (MVT) receptionLot.getAppro();
        final Conditionnement conditionnement = appro.getConditionnement();

        // La réception de lot est en mode NUM_TRAITEMENT => n approvisionnements
        if (ModePrescription.NUM_TRAITEMENT.equals(conditionnement.getModePrescription()))
        {
            final List<NumTraitement> numsTraitements = receptionLot.getNumsTraitements();
            for (final NumTraitement numTraitement : numsTraitements)
            {
                if (this.isNumTraitementValide(numTraitement))
                {
                    final MVT newAppro =
                        this.approFactorys.get(appro.getType()).getInitializedObject();
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
                    if (appro.getType().equals(TypeMvtStock.PREPARATION_ENTREE))
                    {
                        final PreparationEntree castNewAppro = (PreparationEntree) newAppro;
                        final PreparationEntree casAppro = (PreparationEntree) appro;
                        castNewAppro.setComposition(casAppro.getComposition());
                        castNewAppro.setDateFabrication(casAppro.getDateReception());
                        castNewAppro.setNumOrdonnancier(casAppro.getNumOrdonnancier());
                        castNewAppro.setSterile(casAppro.getSterile());
                    }

                    newAppro.setCommentaireRefus(appro.getCommentaireRefus());
                    appros.add(newAppro);
                }
            }
        }
        else
        {
            appros.add(appro);
        }
        resultAppro.getReceptionLots().add(receptionLot);
    }

    /**
     * Méthode en charge de déterminer si les informations d'un numéro de traitement sont valides.
     * @param numTraitement Numéro de traitement.
     * @return Résultat du test.
     */
    protected boolean isNumTraitementValide(final NumTraitement numTraitement)
    {
        return StringUtils.isNotEmpty(numTraitement.getNumTraitement())
               && numTraitement.getQuantite() != null;
    }

    /**
     * Setter pour produitService.
     * @param produitService Le produitService à écrire.
     */
    public void setProduitService(final ProduitService<Produit> produitService)
    {
        this.produitService = produitService;
    }

    /**
     * Setter pour essaiService.
     * @param essaiService Le essaiService à écrire.
     */
    public void setEssaiService(final EssaiService essaiService)
    {
        this.essaiService = essaiService;
    }

    /**
     * Setter pour approFactorys.
     * @param approFactorys Le approFactorys à écrire.
     */
    public void setApproFactorys(final Map<TypeMvtStock, ApproFactory<MVT>> approFactorys)
    {
        this.approFactorys = approFactorys;
    }

}

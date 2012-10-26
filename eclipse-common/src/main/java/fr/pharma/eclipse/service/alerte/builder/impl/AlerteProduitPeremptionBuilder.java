package fr.pharma.eclipse.service.alerte.builder.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.domain.criteria.stock.ExtensionPeremptionSearchCriteria;
import fr.pharma.eclipse.domain.criteria.stock.StockSearchCriteria;
import fr.pharma.eclipse.domain.enums.alerte.TypeAlerte;
import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.EtatLigneStock;
import fr.pharma.eclipse.domain.model.stock.EtatStock;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.alerte.builder.AlerteBuilder;
import fr.pharma.eclipse.service.stock.ApprovisionnementService;
import fr.pharma.eclipse.service.stock.EtatStockService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.message.EclipseMessageBuilder;

/**
 * Classe de builder des alertes concernant les produits / conditionnements / lots /
 * numTraitements dont le délai d'alerte avant la date de péremption est atteint.
 
 * @version $Revision$ $Date$
 */
public class AlerteProduitPeremptionBuilder
    implements AlerteBuilder, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4502803429108358188L;

    /**
     * Service de gestion des approvisionnements.
     */
    @Resource(name = "approvisionnementService")
    private ApprovisionnementService approvisionnementService;

    /**
     * Builder de message.
     */
    @Resource(name = "eclipseMessageBuilder")
    private EclipseMessageBuilder messageBuilder;

    /**
     * Service de gestion de l'état de stock.
     */
    @Resource(name = "etatStockService")
    private EtatStockService etatStockService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void build(final List<Essai> essais,
                      final List<Pharmacie> pharmacies,
                      final List<Alerte> alertes)
    {
        final ExtensionPeremptionSearchCriteria crit = new ExtensionPeremptionSearchCriteria();
        crit.setEssais(essais);
        crit.setPharmacies(pharmacies);

        final List<Approvisionnement> appros = this.approvisionnementService.getAll(crit);

        for (final Approvisionnement appro : appros)
        {
            final Calendar datePeremption = appro.getDatePeremption();
            final Produit produit = appro.getProduit();

            if (datePeremption != null
                && produit.getAlerteActive())
            {
                // Récupération du délai d'alerte avant la date de péremption pour un produit
                // Délai en jours
                final Integer delaiAlerte =
                    produit.getDetailLogistique().getDelaiAlerteAvtDateExpiration();

                final Calendar calendar = Calendar.getInstance(EclipseConstants.LOCALE);
                calendar.setTime(datePeremption.getTime());
                if (delaiAlerte != null)
                {
                    calendar.add(Calendar.DAY_OF_MONTH,
                                 -delaiAlerte);
                }
                final Calendar now = Calendar.getInstance(EclipseConstants.LOCALE);

                if (calendar.before(now))
                {
                    // veification que le stock n'est pas nul pout cet approvisionnement
                    final boolean isStockNonNul = this.isStockNonNul(appro.getEssai(),
                                                                     appro.getPharmacie(),
                                                                     appro.getNumLot(),
                                                                     appro.getNumTraitement());
                    if (isStockNonNul)
                    {
                        final Alerte alerte = new Alerte(TypeAlerte.PRODUIT_PEREMPTION);
                        alerte.setEssai(appro.getEssai());
                        alerte.setPharmacie(appro.getPharmacie());
                        alerte.setLibelle(this.buildMsg(appro));
                        alertes.add(alerte);
                    }
                }
            }
        }
    }

    /**
     * Vérifie que le stock de l'approvisionnement est non nul.
     * @param essai : essai de l'approvisonnement.
     * @param pharmacie : pharmacie de l'approvisonnement.
     * @param numLot : numéro de lot de l'approvisonnement.
     * @param numTraitement : numéro de traitement de l'approvisonnement.
     * @return boolean
     */
    boolean isStockNonNul(final Essai essai,
                          final Pharmacie pharmacie,
                          final String numLot,
                          final String numTraitement)
    {
        boolean stockNonNul = false;

        if (numLot != null
            && numTraitement != null)
        {
            // recuperation des etats stocks
            final StockSearchCriteria criteria = new StockSearchCriteria();
            criteria.setNumLot(numLot);
            criteria.setEssai(essai);
            criteria.setPharmacie(pharmacie);

            final List<EtatStock> lignesEtatStock =
                this.etatStockService.getLinesEtatStock(criteria);

            for (final EtatStock etatStock : lignesEtatStock)
            {
                // on verifie si la qté de stock est non nulle pour le numéro de traitement et
                // l'état de stock.
                stockNonNul = this.isQuantiteEnstockNonNulle(numTraitement,
                                                             etatStock);
                if (stockNonNul)
                {
                    break;
                }
            }

        }

        return stockNonNul;
    }

    /**
     * Vérifie si, pour un numéro de traitement et un etat de stock donné, la quantité de produit
     * en stock est non nulle.
     * @param numTraitement
     * @param etatStock
     */
    boolean isQuantiteEnstockNonNulle(final String numTraitement,
                                      final EtatStock etatStock)
    {
        final Map<String, EtatLigneStock> etatsLignesStock = etatStock.getEtatsLignesStock();
        final List<EtatLigneStock> etatsLignesStockAsList =
            new ArrayList<EtatLigneStock>(etatsLignesStock.values());
        for (final EtatLigneStock etatLigneStock : etatsLignesStockAsList)
        {
            if (numTraitement.equals(etatLigneStock.getNumTraitement()))
            {
                if (etatLigneStock.getQteEnStock() > 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        // si on arrive ici c'est qu'on n'a pas trouvé de ligne de stock correspondant au numéro
        // de traitement
        return false;
    }
    /**
     * Méthode en charge de construire le libellé du message d'alerte.
     * @param appro Approvisionnement.
     * @return Message.
     */
    private String buildMsg(final Approvisionnement appro)
    {
        return this.messageBuilder
                .getMessage("alerte.libDatePeremption",
                            new Object[]
                            {appro.getProduit().getDenomination(),
                             appro.getConditionnement().getLibelle(),
                             StringUtils.defaultIfEmpty(appro.getNumLot(),
                                                        EclipseConstants.NON_APPLICABLE),
                             StringUtils.defaultIfEmpty(appro.getNumTraitement(),
                                                        EclipseConstants.NON_APPLICABLE), });
    }

    /**
     * Setter pour approvisionnementService.
     * @param approvisionnementService Le approvisionnementService à écrire.
     */
    public void setApprovisionnementService(final ApprovisionnementService approvisionnementService)
    {
        this.approvisionnementService = approvisionnementService;
    }

    /**
     * Setter pour messageBuilder.
     * @param messageBuilder Le messageBuilder à écrire.
     */
    public void setMessageBuilder(final EclipseMessageBuilder messageBuilder)
    {
        this.messageBuilder = messageBuilder;
    }

    /**
     * Setter pour etatStockService.
     * @param etatStockService Le etatStockService à écrire.
     */
    public void setEtatStockService(final EtatStockService etatStockService)
    {
        this.etatStockService = etatStockService;
    }
}

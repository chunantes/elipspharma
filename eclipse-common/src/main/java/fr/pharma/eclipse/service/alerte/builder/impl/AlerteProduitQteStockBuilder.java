package fr.pharma.eclipse.service.alerte.builder.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.stock.StockSearchCriteria;
import fr.pharma.eclipse.domain.enums.alerte.TypeAlerte;
import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.EtatStock;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.alerte.builder.AlerteBuilder;
import fr.pharma.eclipse.service.stock.EtatStockService;
import fr.pharma.eclipse.utils.message.EclipseMessageBuilder;

/**
 * Classe de builder des alertes concernant les produits / conditionnements dont la quantité en
 * stock est inférieure à la quantité seuil définie pour le produit.
 
 * @version $Revision$ $Date$
 */
public class AlerteProduitQteStockBuilder
    implements AlerteBuilder, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3147527379868799342L;

    /**
     * Service de gestion de l'état de stock.
     */
    @Resource(name = "etatStockService")
    private EtatStockService etatStockService;

    /**
     * Builder de message.
     */
    @Resource(name = "eclipseMessageBuilder")
    private EclipseMessageBuilder messageBuilder;

    /**
     * {@inheritDoc}
     */
    @Override
    public void build(final List<Essai> essais,
                      final List<Pharmacie> pharmacies,
                      final List<Alerte> alertes)
    {
        final StockSearchCriteria criteria = new StockSearchCriteria();
        criteria.setEssais(essais);
        criteria.setPharmacies(pharmacies);

        final List<EtatStock> etatsStock = this.etatStockService.getLinesEtatStock(criteria);

        for (final EtatStock etatStock : etatsStock)
        {
            // Récupération du stock seuil du produit
            final Produit produit = etatStock.getProduit();
            final Integer seuilPlancher = produit.getDetailLogistique().getStockSeuil();

            if ((seuilPlancher != null)
                && (etatStock.getQteEnStock() < seuilPlancher)
                && (produit.getAlerteActive()))
            {
                final Alerte alerte = new Alerte(TypeAlerte.STOCK_SEUIL);
                alerte.setEssai(etatStock.getEssai());
                alerte.setPharmacie(etatStock.getPharmacie());
                alerte.setLibelle(this.messageBuilder.getMessage("alerte.libStockSeuil",
                                                                 new Object[]
                                                                 {
                                                                  produit.getDenomination(),
                                                                  etatStock
                                                                          .getConditionnement()
                                                                          .getLibelle(), }));
                alertes.add(alerte);
            }
        }
    }

    /**
     * Setter pour etatStockService.
     * @param etatStockService Le etatStockService à écrire.
     */
    public void setEtatStockService(final EtatStockService etatStockService)
    {
        this.etatStockService = etatStockService;
    }

    /**
     * Setter pour messageBuilder.
     * @param messageBuilder Le messageBuilder à écrire.
     */
    public void setMessageBuilder(final EclipseMessageBuilder messageBuilder)
    {
        this.messageBuilder = messageBuilder;
    }
}

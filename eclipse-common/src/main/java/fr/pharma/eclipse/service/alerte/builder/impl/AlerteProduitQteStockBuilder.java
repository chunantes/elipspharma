package fr.pharma.eclipse.service.alerte.builder.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

import fr.pharma.eclipse.domain.enums.alerte.TypeAlerte;
import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.essai.EssaiAlerte;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.alerte.builder.AlerteBuilder;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.utils.message.MessageBuilder;

/**
 * Classe de builder des alertes concernant les produits / conditionnements dont
 * la quantité en stock est inférieure à la quantité seuil définie pour le
 * produit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AlerteProduitQteStockBuilder implements AlerteBuilder, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3147527379868799342L;

    /**
     * Service de gestion des produits.
     */
    @Resource(name = "produitService")
    private ProduitService<Produit> produitService;

    /**
     * Builder de message.
     */
    @Resource(name = "messageBuilder")
    private MessageBuilder messageBuilder;

    /**
     * Requête de récupération des produits dont la quantité en stock a atteint
     * le niveau de stock alerte.
     */
    protected static final String REQ_PROD_QTE_STOCK =
        "select s.id_essai, s.id_pharmacie, s.id_produit, s.id_conditionnement, e.numInterne, e.nom, p.denomination, ph.nom as nomPharma, c.libelle, "
                + " sum(s.quantite_global) as quantiteStock, stockSeuil " + "from lignestock s " + "inner join produit p on p.id = s.id_produit "
                + "inner join produit_detail_logistique pl on pl.id_produit=p.id " + "inner join conditionnement c on c.id = s.id_conditionnement "
                + "inner join essai e on e.id = s.id_essai " + "inner join pharmacie ph on ph.id = s.id_pharmacie " + "where p.alerteactive is true "
                + "and pl.stockSeuil is not null " + "and s.id_essai in ({0}) and s.id_pharmacie in ({1}) "
                + "group by s.id_essai, s.id_pharmacie, s.id_produit, s.id_conditionnement, e.numInterne, e.nom, p.denomination, ph.nom, c.libelle, stockSeuil "
                + "having (sum(s.quantite_global) < stockSeuil)" + "order by e.numInterne, e.nom, p.denomination, c.libelle";

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"rawtypes", "unchecked" })
    @Override
    public void build(final List<EssaiAlerte> essais,
                      final List<Pharmacie> pharmacies,
                      final List<Alerte> alertes) {

        final List idsEssais = new ArrayList(essais);
        CollectionUtils.transform(idsEssais, new Transformer() {
            @Override
            public Object transform(final Object input) {
                return ((EssaiAlerte) input).getId();
            }
        });
        final List idsPharmas = new ArrayList(pharmacies);
        CollectionUtils.transform(idsPharmas, new Transformer() {
            @Override
            public Object transform(final Object input) {
                return ((Pharmacie) input).getId();
            }
        });

        final String strIdsEssais = idsEssais.toString().replace("[", "").replace("]", "");
        final String strIdsPharmas = idsPharmas.toString().replace("[", "").replace("]", "");

        final List<Object[]> results =
            this.produitService.executeSQLQueryTabObject(AlerteProduitQteStockBuilder.REQ_PROD_QTE_STOCK.replace("{0}", strIdsEssais).replace("{1}", strIdsPharmas), null);

        for (final Object[] result : results) {
            final Alerte alerte = new Alerte(TypeAlerte.STOCK_SEUIL, (String) result[4], (String) result[5]);
            alerte.setNomPharmacie((String) result[7]);
            alerte.setLibelle(this.messageBuilder.getMessage("alerte.libStockSeuil", new Object[]{(String) result[6], (String) result[8], }));
            alertes.add(alerte);
        }
    }

    /**
     * Setter pour messageBuilder.
     * @param messageBuilder Le messageBuilder à écrire.
     */
    public void setMessageBuilder(final MessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
    }

    /**
     * Setter pour produitService.
     * @param produitService Le produitService à écrire.
     */
    public void setProduitService(final ProduitService<Produit> produitService) {
        this.produitService = produitService;
    }
}

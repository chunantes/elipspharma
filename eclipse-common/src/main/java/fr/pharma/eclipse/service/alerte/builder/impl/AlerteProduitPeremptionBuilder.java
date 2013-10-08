package fr.pharma.eclipse.service.alerte.builder.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.domain.enums.alerte.TypeAlerte;
import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.essai.EssaiAlerte;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.alerte.builder.AlerteBuilder;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.message.MessageBuilder;

/**
 * Classe de builder des alertes concernant les produits / conditionnements /
 * lots / numTraitements dont le délai d'alerte avant la date de péremption est
 * atteint.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AlerteProduitPeremptionBuilder implements AlerteBuilder, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4502803429108358188L;

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
     * Requête de récupération des produits dont la date de péremption a atteint
     * le délai d'alerte de péremption.
     */
    protected static final String REQ_PROD_PEREMPTION =
        "select s.id_essai, s.id_pharmacie, s.id_produit, s.id_conditionnement, s.numlot, s.numtraitement, e.numInterne, e.nom, p.denomination, ph.nom as nomPharma, c.libelle, "
                + "delaiAlerteAvtDateExpiration, datePeremption, s.datePeremption - (delaiAlerteAvtDateExpiration * interval '1 day'), sum(s.quantite_global) "
                + "from lignestock s " + "inner join produit p on p.id = s.id_produit " + "inner join produit_detail_logistique pl on pl.id_produit=p.id "
                + "inner join conditionnement c on c.id = s.id_conditionnement " + "inner join essai e on e.id = s.id_essai "
                + "inner join pharmacie ph on ph.id = s.id_pharmacie " + "where p.alerteactive is true " + "and pl.delaiAlerteAvtDateExpiration is not null "
                + "and s.datePeremption is not null " + "and s.id_essai in ({0}) and s.id_pharmacie in ({1}) "
                + "group by s.id_essai, s.id_pharmacie, s.id_produit, s.id_conditionnement, s.numlot, s.numtraitement, e.numInterne, e.nom, p.denomination, ph.nom, "
                + "c.libelle, delaiAlerteAvtDateExpiration, s.datePeremption "
                + "having (sum(s.quantite_global)) > 0 and (s.datePeremption - (delaiAlerteAvtDateExpiration * interval '1 day')) < now() "
                + "order by e.numInterne, e.nom, p.denomination, c.libelle, s.numlot, s.numtraitement";

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({"rawtypes", "unchecked" })
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
            this.produitService.executeSQLQueryTabObject(AlerteProduitPeremptionBuilder.REQ_PROD_PEREMPTION.replace("{0}", strIdsEssais).replace("{1}", strIdsPharmas), null);

        for (final Object[] result : results) {

            final Alerte alerte = new Alerte(TypeAlerte.PRODUIT_PEREMPTION, (String) result[6], (String) result[7]);
            alerte.setNomPharmacie((String) result[9]);
            final String message =
                this.messageBuilder.getMessage("alerte.libDatePeremption",
                                               new Object[]{(String) result[8], (String) result[10],
                                                            StringUtils.defaultIfEmpty((String) result[4], EclipseConstants.NON_APPLICABLE),
                                                            StringUtils.defaultIfEmpty((String) result[5], EclipseConstants.NON_APPLICABLE), });
            alerte.setLibelle(message);
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

package fr.pharma.eclipse.service.alerte.builder.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

import fr.pharma.eclipse.domain.criteria.dotation.DotationSearchCriteria;
import fr.pharma.eclipse.domain.enums.alerte.TypeAlerte;
import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.dotation.Dotation;
import fr.pharma.eclipse.domain.model.essai.EssaiAlerte;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.alerte.builder.AlerteBuilder;
import fr.pharma.eclipse.service.dotation.DotationService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.message.MessageBuilder;

/**
 * Classe de builder des alertes concernant les demandes de dotations à traiter.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AlerteDdeDotationBuilder implements AlerteBuilder, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2790664462448280560L;

    /**
     * Service de gestion des dotations.
     */
    @Resource(name = "dotationService")
    private DotationService dotationService;

    /**
     * Builder de message.
     */
    @Resource(name = "messageBuilder")
    private MessageBuilder messageBuilder;

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({"unchecked", "rawtypes" })
    public void build(final List<EssaiAlerte> essais,
                      final List<Pharmacie> pharmacies,
                      final List<Alerte> alertes) {
        // Récupération des demandes de dotations
        final DotationSearchCriteria crit = new DotationSearchCriteria();

        // Etat : à traiter
        crit.setTraitee(Boolean.FALSE);

        // Alerte active sur le produit
        crit.setAlerteActiveProduit(Boolean.TRUE);

        // Restrictions des dotations par rapport aux essais visibles
        final List idsEssais = new ArrayList(essais);
        CollectionUtils.transform(idsEssais, new Transformer() {
            @Override
            public Object transform(final Object input) {
                return ((EssaiAlerte) input).getId();
            }
        });

        crit.setIdsEssais(idsEssais);

        // Restrictions des dotations par rapport aux pharmacies visibles

        final List idsPharmacies = new ArrayList(pharmacies);
        CollectionUtils.transform(idsPharmacies, new Transformer() {
            @Override
            public Object transform(final Object input) {
                return ((Pharmacie) input).getId();
            }
        });

        crit.setIdsPharmacies(idsPharmacies);

        final SimpleDateFormat sdf = new SimpleDateFormat(EclipseConstants.PATTERN_AVEC_HEURES);

        final List<Dotation> dotations = this.dotationService.getAll(crit);

        for (final Dotation dotation : dotations) {
            final Alerte alerte = new Alerte(TypeAlerte.DDES_DOTATIONS_A_TRAITER, dotation.getEssai().getNumInterne(), dotation.getEssai().getNom());
            alerte.setNomPharmacie(dotation.getPharmacie().getNom());
            alerte.setLibelle(this.messageBuilder.getMessage("alerte.libDemandeDotation",
                                                             new Object[]{dotation.getService().getNom(), sdf.format(dotation.getDateDemande().getTime()), }));
            alertes.add(alerte);
        }
    }

    /**
     * Setter pour dotationService.
     * @param dotationService Le dotationService à écrire.
     */
    public void setDotationService(final DotationService dotationService) {
        this.dotationService = dotationService;
    }

    /**
     * Setter pour messageBuilder.
     * @param messageBuilder Le messageBuilder à écrire.
     */
    public void setMessageBuilder(final MessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
    }

}

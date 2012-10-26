package fr.pharma.eclipse.service.alerte.builder.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.criteria.dotation.DotationSearchCriteria;
import fr.pharma.eclipse.domain.enums.alerte.TypeAlerte;
import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.dotation.Dotation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.alerte.builder.AlerteBuilder;
import fr.pharma.eclipse.service.dotation.DotationService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.message.EclipseMessageBuilder;

/**
 * Classe de builder des alertes concernant les demandes de dotations à traiter.
 
 * @version $Revision$ $Date$
 */
public class AlerteDdeDotationBuilder
    implements AlerteBuilder, Serializable
{
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
        // Récupération des demandes de dotations dont l'état est à traiter
        final DotationSearchCriteria crit = new DotationSearchCriteria();
        crit.setTraitee(Boolean.FALSE);

        final SimpleDateFormat sdf = new SimpleDateFormat(EclipseConstants.PATTERN_AVEC_HEURES);

        final List<Dotation> dotations = this.dotationService.getAll(crit);

        // Filtre des dotations par rapport aux essais visibles
        CollectionUtils.filter(dotations,
                               new Predicate() {
                                   @Override
                                   public boolean evaluate(final Object object)
                                   {
                                       final Dotation dot = (Dotation) object;
                                       return essais.contains(dot.getEssai());
                                   }
                               });

        // Filtre sur les dotations par rapport aux pharmacies visibles
        CollectionUtils.filter(dotations,
                               new Predicate() {

                                   @Override
                                   public boolean evaluate(final Object object)
                                   {
                                       final Dotation dot = (Dotation) object;
                                       return pharmacies.contains(dot.getPharmacie());
                                   }
                               });

        for (final Dotation dotation : dotations)
        {
            if (dotation.getProduit().getAlerteActive())
            {
                final Alerte alerte = new Alerte(TypeAlerte.DDES_DOTATIONS_A_TRAITER);
                alerte.setEssai(dotation.getEssai());
                alerte.setPharmacie(dotation.getPharmacie());
                alerte.setLibelle(this.messageBuilder.getMessage("alerte.libDemandeDotation",
                                                                 new Object[]
                                                                 {
                                                                  dotation.getService().getNom(),
                                                                  sdf.format(dotation
                                                                          .getDateDemande()
                                                                          .getTime()), }));
                alertes.add(alerte);
            }
        }
    }

    /**
     * Setter pour dotationService.
     * @param dotationService Le dotationService à écrire.
     */
    public void setDotationService(final DotationService dotationService)
    {
        this.dotationService = dotationService;
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

package fr.pharma.eclipse.service.alerte.builder.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.PredicateUtils;
import org.apache.commons.collections.Transformer;

import fr.pharma.eclipse.domain.criteria.evenement.EvenementSearchCriteria;
import fr.pharma.eclipse.domain.enums.alerte.TypeAlerte;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.EssaiAlerte;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.alerte.builder.AlerteBuilder;
import fr.pharma.eclipse.service.evenement.EvenementService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.message.MessageBuilder;

/**
 * Classe de builder des alertes concernant les destructions des essais 15 ans
 * après la date de visite de cloture.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AlerteCessionPUIBuilder implements AlerteBuilder, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3849959773431357421L;

    /**
     * Builder de message.
     */
    @Resource(name = "messageBuilder")
    private MessageBuilder messageBuilder;

    /**
     * Nombre de jours.
     */
    protected static final int NB_JOURS = 5;

    /**
     * Service evenement.
     */
    @Resource(name = "evenementService")
    private EvenementService evenementService;

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({"rawtypes", "unchecked" })
    public void build(final List<EssaiAlerte> essais,
                      final List<Pharmacie> pharmacies,
                      final List<Alerte> alertes) {
        final EvenementSearchCriteria criteria = new EvenementSearchCriteria();
        criteria.setTypeEvenement(TypeEvenement.CESSION_PUI);
        final List idsEssais = new ArrayList(essais);
        CollectionUtils.transform(idsEssais, new Transformer() {
            @Override
            public Object transform(final Object input) {
                return ((EssaiAlerte) input).getId();
            }
        });
        CollectionUtils.filter(idsEssais, PredicateUtils.notNullPredicate());

        criteria.setIdsEssais(idsEssais);
        criteria.setDateReceptionVide(true);

        final Calendar now = Calendar.getInstance(EclipseConstants.LOCALE);
        now.add(Calendar.DAY_OF_YEAR, -AlerteCessionPUIBuilder.NB_JOURS);
        criteria.setDateFin(now);

        final List<Evenement> evts = this.evenementService.getAll(criteria);

        for (final Evenement evenement : evts) {
            final Essai essai = evenement.getEssai();
            final Alerte alerte = new Alerte(TypeAlerte.DATE_CESSION_PUI, essai.getNumInterne(), essai.getNom());
            alerte.setLibelle(this.messageBuilder.getMessage("alerte.libCession", new Object[]{AlerteCessionPUIBuilder.NB_JOURS }));
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
     * Setter pour evenementService.
     * @param evenementService Le evenementService à écrire.
     */
    public void setEvenementService(final EvenementService evenementService) {
        this.evenementService = evenementService;
    }
}

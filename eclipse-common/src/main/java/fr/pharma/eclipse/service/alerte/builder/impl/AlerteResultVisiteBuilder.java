package fr.pharma.eclipse.service.alerte.builder.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.evenement.EvenementSearchCriteria;
import fr.pharma.eclipse.domain.enums.alerte.TypeAlerte;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.alerte.builder.AlerteBuilder;
import fr.pharma.eclipse.service.evenement.EvenementService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.message.EclipseMessageBuilder;

/**
 * Classe de builder des alertes concernant les résultats de visites non rempli le lendemain de la
 * date de visite.
 
 * @version $Revision$ $Date$
 */
public class AlerteResultVisiteBuilder
    implements AlerteBuilder, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2223026911036455437L;

    /**
     * Service de gestion des événements.
     */
    @Resource(name = "evenementService")
    private EvenementService evenementService;

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
        final Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY,
                0);
        now.set(Calendar.MINUTE,
                0);
        now.set(Calendar.SECOND,
                0);

        final SimpleDateFormat sdf = new SimpleDateFormat(EclipseConstants.PATTERN_AVEC_HEURES);

        for (final Essai essai : essais)
        {
            // Récupération des visites de l'essai dont le résultat est vide
            final EvenementSearchCriteria criteria = new EvenementSearchCriteria();
            criteria.setEssai(essai);
            criteria.setTypeEvenement(TypeEvenement.VISITE);
            criteria.setResultVisiteVide(Boolean.TRUE);

            final List<Evenement> evts = this.evenementService.getAll(criteria);

            for (final Evenement evt : evts)
            {
                // Date de la visite à J+1 sans heure
                final Calendar dateVisite = Calendar.getInstance();
                dateVisite.setTime(evt.getDateDebut().getTime());

                // Reset des heures minutes secondes
                dateVisite.set(Calendar.HOUR_OF_DAY,
                               0);
                dateVisite.set(Calendar.MINUTE,
                               0);
                dateVisite.set(Calendar.SECOND,
                               0);

                dateVisite.add(Calendar.DAY_OF_MONTH,
                               1);

                if (now.compareTo(dateVisite) >= 0)
                {
                    final Alerte alerte = new Alerte(TypeAlerte.RESULT_VISITE);
                    alerte.setEssai(essai);
                    alerte.setLibelle(this.messageBuilder.getMessage("alerte.libResultVisite",
                                                                     new Object[]
                                                                     {
                                                                      evt.getLibelle(),
                                                                      sdf.format(evt
                                                                              .getDateDebut()
                                                                              .getTime()), }));
                    alertes.add(alerte);
                }
            }
        }
    }

    /**
     * Setter pour evenementService.
     * @param evenementService Le evenementService à écrire.
     */
    public void setEvenementService(final EvenementService evenementService)
    {
        this.evenementService = evenementService;
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

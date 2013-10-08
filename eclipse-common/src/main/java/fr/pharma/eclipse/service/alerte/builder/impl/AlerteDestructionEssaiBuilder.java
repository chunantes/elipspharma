package fr.pharma.eclipse.service.alerte.builder.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.enums.alerte.TypeAlerte;
import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.essai.EssaiAlerte;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.alerte.builder.AlerteBuilder;
import fr.pharma.eclipse.utils.message.MessageBuilder;

/**
 * Classe de builder des alertes concernant les destructions des essais 15 ans
 * après la date de visite de cloture.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AlerteDestructionEssaiBuilder implements AlerteBuilder, Serializable {
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
     * Nombre d'années après la date de visite de clotûre pour l'alerte sur la
     * destruction de l'essai.
     */
    protected static final int NB_ANNEES = 15;

    /**
     * {@inheritDoc}
     */
    @Override
    public void build(final List<EssaiAlerte> essais,
                      final List<Pharmacie> pharmacies,
                      final List<Alerte> alertes) {
        final Calendar now = Calendar.getInstance();
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);

        for (final EssaiAlerte essai : essais) {
            final Calendar dateVisiteCloture = essai.getCloture();
            if (dateVisiteCloture != null) {
                // Date visite cloture + 15 ans
                final Calendar cal = Calendar.getInstance();
                cal.setTime(dateVisiteCloture.getTime());
                cal.add(Calendar.YEAR, AlerteDestructionEssaiBuilder.NB_ANNEES);

                if (now.compareTo(cal) > 0) {
                    final Alerte alerte = new Alerte(TypeAlerte.DESTRUCTION_ESSAI, essai.getNumInterne(), essai.getNom());
                    alerte.setLibelle(this.messageBuilder.getMessage("alerte.libDestructionEssai", new Object[]{AlerteDestructionEssaiBuilder.NB_ANNEES }));
                    alertes.add(alerte);
                }
            }
        }
    }

    /**
     * Setter pour messageBuilder.
     * @param messageBuilder Le messageBuilder à écrire.
     */
    public void setMessageBuilder(final MessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
    }
}

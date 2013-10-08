package fr.pharma.eclipse.service.alerte.builder.impl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.enums.alerte.TypeAlerte;
import fr.pharma.eclipse.domain.model.alerte.Alerte;
import fr.pharma.eclipse.domain.model.essai.EssaiAlerte;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.alerte.builder.AlerteBuilder;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.message.MessageBuilder;

/**
 * Classe de builder des alertes concernant la date de fin de l'essai atteinte.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AlerteDateFinEssaiBuilder implements AlerteBuilder, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3575850634150627143L;

    /**
     * Builder de message.
     */
    @Resource(name = "messageBuilder")
    private MessageBuilder messageBuilder;

    /**
     * {@inheritDoc}
     */
    @Override
    public void build(final List<EssaiAlerte> essais,
                      final List<Pharmacie> pharmacies,
                      final List<Alerte> alertes) {
        final Calendar now = Calendar.getInstance();

        final SimpleDateFormat sdf = new SimpleDateFormat(EclipseConstants.PATTERN_SIMPLE);

        for (final EssaiAlerte essai : essais) {
            final Calendar dateFinEssai = essai.getFinEtude();
            if ((dateFinEssai != null) && (now.compareTo(dateFinEssai) > 0)) {
                final Alerte alerte = new Alerte(TypeAlerte.DATE_FIN_ESSAI_ATTEINT, essai.getNumInterne(), essai.getNom());
                alerte.setLibelle(this.messageBuilder.getMessage("alerte.libDateFinEssai", new Object[]{sdf.format(dateFinEssai.getTime()), }));
                alertes.add(alerte);
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

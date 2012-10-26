package fr.pharma.eclipse.service.dispensation.formatter.impl;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.dispensation.ConseilDispensation;
import fr.pharma.eclipse.service.dispensation.formatter.ConseilFormatter;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Description de la classe.
 
 * @version $Revision$ $Date$
 */
public class ConditionnementPrimaireFormatter
    extends GenericConseilFormatter
    implements Serializable, ConseilFormatter
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -760523922330698402L;

    /**
     * {@inheritDoc}
     */
    @Override
    public String format(final ConseilDispensation conseil)
    {
        final StringBuffer buff =
            new StringBuffer(this.messageBuilder.getMessage("conseil.dispensation.aide"));
        buff
                .append(this.formatNbDoses(conseil))
                .append(EclipseConstants.SPACE)
                .append(EclipseConstants.DASH)
                .append(EclipseConstants.SPACE);
        buff.append(this.formatFrequence(conseil));
        buff.append(" => ").append(this.formatTotal(conseil));
        return buff.toString();
    }

    /**
     * Méthode en charge de construire le nombre de doses unitaires.
     * @param conseil ConseilDispensation.
     * @return Le message contenant le nombre de doses unitaires.
     */
    private String formatNbDoses(final ConseilDispensation conseil)
    {
        final Object[] args = new Object[1];
        args[0] = conseil.getProduitPrescrit().getNbUniteDosage();
        return this.messageBuilder.getMessage("conseil.dispensation.primaire",
                                              args);
    }

    /**
     * Méthode en charge de formatter le message contenant le nombre total de doses à sortir.
     * @param conseil Le ConseilDispensation.
     * @return Le message contenant le nombre total de doses.
     */
    private String formatTotal(final ConseilDispensation conseil)
    {
        final Object[] args = new Object[2];
        args[0] = conseil.getNbASortir();
        args[1] = conseil.getProduitPrescrit().getConditionnement().getUniteGestion();
        return this.messageBuilder.getMessage("conseil.dispensation.primaire.total",
                                              args);
    }
}

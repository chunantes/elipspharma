package fr.pharma.eclipse.service.dispensation.formatter.impl;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.dispensation.ConseilDispensation;
import fr.pharma.eclipse.service.dispensation.formatter.ConseilFormatter;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de formatter le conseil pour un conditionnement de type Dosage au SC.
 
 * @version $Revision$ $Date$
 */
public class DosageSCFormatter
    extends GenericConseilFormatter
    implements ConseilFormatter, Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -6332158915353747914L;

    /**
     * {@inheritDoc}
     */
    @Override
    public String format(final ConseilDispensation conseil)
    {
        final StringBuffer buff =
            new StringBuffer(this.messageBuilder.getMessage("conseil.dispensation.aide"));
        buff
                .append(this.formatSC(conseil))
                .append(EclipseConstants.SPACE)
                .append(EclipseConstants.DASH)
                .append(EclipseConstants.SPACE);
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
     * Méthode en charge de formatter le poid.
     * @param conseil ConseilDispensation.
     * @return Le message représentant le poid.
     */
    private String formatSC(final ConseilDispensation conseil)
    {
        final Object[] args = new Object[1];

        args[0] =
            conseil
                    .getProduitPrescrit()
                    .getPrescription()
                    .getInclusion()
                    .getPatient()
                    .getSurface();
        return this.messageBuilder.getMessage("conseil.dispensation.dose.surface",
                                              args);
    }

    /**
     * Methode en charge de formatter le nombre de doses par m².
     * @param conseil Le conseilDispensation.
     * @return Le message représentant le nombre de doses par m².
     */
    private String formatNbDoses(final ConseilDispensation conseil)
    {
        final Object[] args = new Object[1];
        args[0] = conseil.getProduitPrescrit().getNbUniteDosage();
        return this.messageBuilder.getMessage("conseil.dispensation.dose.sc.nbDoses",
                                              args);
    }

    /**
     * DosageKgFormatter.java Méthode en charge de formatter le nombre total de doses.
     * @param conseil Le conseilDispensation.
     * @return Le message contenant le nombre totale de doses.
     */
    private String formatTotal(final ConseilDispensation conseil)
    {
        final Object[] args = new Object[1];
        args[0] = conseil.getNbASortir();
        return this.messageBuilder.getMessage("conseil.dispensation.dose.total",
                                              args);

    }

}

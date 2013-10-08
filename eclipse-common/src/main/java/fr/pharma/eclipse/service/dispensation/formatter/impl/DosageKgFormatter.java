package fr.pharma.eclipse.service.dispensation.formatter.impl;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.dispensation.ConseilDispensation;
import fr.pharma.eclipse.service.dispensation.formatter.ConseilFormatter;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de formatter le conseil pour un conditionnement de type
 * Dosage au kg.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DosageKgFormatter extends GenericConseilFormatter implements ConseilFormatter, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -6332158915353747914L;

    /**
     * {@inheritDoc}
     */
    @Override
    public String format(final ConseilDispensation conseil) {
        final StringBuffer buff = new StringBuffer(this.messageBuilder.getMessage("conseil.dispensation.aide"));
        buff.append(this.formatPoids(conseil)).append(EclipseConstants.SPACE).append(EclipseConstants.DASH).append(EclipseConstants.SPACE);
        buff.append(this.formatNbDoses(conseil)).append(EclipseConstants.SPACE).append(EclipseConstants.DASH).append(EclipseConstants.SPACE);
        buff.append(this.formatFrequence(conseil));
        buff.append(" => ").append(this.formatTotal(conseil));
        return buff.toString();
    }

    /**
     * Méthode en charge de formatter le poid.
     * @param conseil ConseilDispensation.
     * @return Le message représentant le poid.
     */
    private String formatPoids(final ConseilDispensation conseil) {
        final Object[] args = new Object[1];
        args[0] = conseil.getProduitPrescrit().getPrescription().getInclusion().getPatient().getPoid();
        return this.messageBuilder.getMessage("conseil.dispensation.dose.poids", args);
    }

    /**
     * Methode en charge de formatter le nombre de doses par kg.
     * @param conseil Le conseilDispensation.
     * @return Le message représentant le nombre de doses par kg.
     */
    private String formatNbDoses(final ConseilDispensation conseil) {
        final Object[] args = new Object[1];
        args[0] = conseil.getProduitPrescrit().getNbUniteDosage();
        return this.messageBuilder.getMessage("conseil.dispensation.dose.poids.nbDoses", args);
    }

    /**
     * DosageKgFormatter.java Méthode en charge de formatter le nombre total de
     * doses.
     * @param conseil Le conseilDispensation.
     * @return Le message contenant le nombre totale de doses.
     */
    private String formatTotal(final ConseilDispensation conseil) {
        final Object[] args = new Object[1];
        args[0] = conseil.getNbASortir();
        return this.messageBuilder.getMessage("conseil.dispensation.dose.total", args);

    }

}

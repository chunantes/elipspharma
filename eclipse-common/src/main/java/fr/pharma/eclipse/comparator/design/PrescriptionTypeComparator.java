package fr.pharma.eclipse.comparator.design;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe de comparator sur PrescriptionType.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PrescriptionTypeComparator implements Comparator<PrescriptionType>, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 8029618624163862596L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final PrescriptionType p1,
                       final PrescriptionType p2) {
        final String key1 = this.buildKey(p1);
        final String key2 = this.buildKey(p2);

        return key1.compareTo(key2);
    }

    /**
     * Méthode en charge de construire la clé relative à un Designable
     * @param p Designable dont on veut construire la clé.
     * @return La clé du Designable.
     */
    private String buildKey(final PrescriptionType p) {
        // Tri par date + nom du produit + dosage + mode de prescription
        final StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(p.getDebut())).append(EclipseConstants.COMMA);
        builder.append(p.getDuree()).append(EclipseConstants.COMMA);
        builder.append(p.getDescription()).append(EclipseConstants.COMMA);
        builder.append(p.getDosageAsString()).append(EclipseConstants.COMMA);
        if (p.getConditionnement().getUniteDosage() != null) {
            builder.append(p.getConditionnement().getUniteDosage()).append(EclipseConstants.COMMA);
        }
        builder.append(p.getProduit().getNom()).append(EclipseConstants.COMMA);
        builder.append(p.getFrequence().toString()).append(EclipseConstants.COMMA);
        builder.append(p.getConditionnement().getModePrescription()).append(EclipseConstants.COMMA);
        builder.append(p.getNbUniteDosage()).append(EclipseConstants.COMMA);
        if (p.getConditionnement().getDosage() != null) {
            builder.append(p.getConditionnement().getDosage()).append(EclipseConstants.COMMA);
        }
        builder.append(p.getId()).append(EclipseConstants.COMMA);
        return builder.toString();
    }

}

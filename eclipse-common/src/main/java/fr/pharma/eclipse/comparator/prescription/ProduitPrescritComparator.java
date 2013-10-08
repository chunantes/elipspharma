package fr.pharma.eclipse.comparator.prescription;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe de comparator de ProduitPrescrit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ProduitPrescritComparator implements Comparator<ProduitPrescrit>, Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6626775207036341566L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final ProduitPrescrit p1,
                       final ProduitPrescrit p2) {
        final String key1 = this.buildKey(p1);
        final String key2 = this.buildKey(p2);

        return key1.compareTo(key2);
    }

    /**
     * Méthode en charge de construire la clé relative à un ProduitPrescrit.
     * @param p ProduitPrescrit dont on veut construire la clé.
     * @return La clé du ProduitPrescrit.
     */
    private String buildKey(final ProduitPrescrit p) {
        // Tri par date + nom du produit + dosage + mode de prescription
        final StringBuilder builder = new StringBuilder();

        if (p.getProduit() != null) {
            builder.append(p.getProduit().getNom());
        }
        builder.append(EclipseConstants.COMMA);
        if (p.getConditionnement() != null) {
            builder.append(p.getConditionnement().getModePrescription().getLibelle()).append(EclipseConstants.COMMA);
        }
        builder.append(p.getDebut()).append(p.getDuree()).append(EclipseConstants.COMMA);
        return builder.toString();
    }
}

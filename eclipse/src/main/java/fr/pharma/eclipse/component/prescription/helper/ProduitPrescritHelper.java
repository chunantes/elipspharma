package fr.pharma.eclipse.component.prescription.helper;

import fr.pharma.eclipse.domain.enums.design.TypeRegularite;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Helper du manager de PrescriptionType.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ProduitPrescritHelper {

    /**
     * Propriétés non null à vérifier avant de générer le résumé.
     */
    private final String[] proprietes = {"debut", "debut.unite", "debut.nb", "duree", "duree.unite", "duree.nb", "frequence", "frequence.nbFrequence", "frequence.typeRegularite",
                                         "frequence.uniteFrequence", "conditionnement", "conditionnement.modePrescription", };

    /**
     * Méthode en charge de construire le résumé d'une prescription type.
     * @param prescription La prescription type.
     * @return le résumé d'une prescriptionType.
     */
    public String buildResume(final ProduitPrescrit prescription) {
        final StringBuffer buff = new StringBuffer();

        if (this.verifyNotNull(prescription, this.proprietes)) {
            buff.append("Prescription : ");

            // si mode prescription par dose.
            final String[] props = {"conditionnement.unitePrescription", "nbUniteDosage", "dosage" };
            if ((this.verifyNotNull(prescription, props) && prescription.getConditionnement().getModePrescription().equals(ModePrescription.DOSE))
                || prescription.getConditionnement().getModePrescription().equals(ModePrescription.DOSE_KG)
                || prescription.getConditionnement().getModePrescription().equals(ModePrescription.DOSE_SURFACE)) {
                buff.append(prescription.getNbUniteDosage()).append(" fois ").append(prescription.getDosage()).append(EclipseConstants.SPACE)
                        .append(prescription.getConditionnement().getUnitePrescription()).append(EclipseConstants.COMMA).append(EclipseConstants.SPACE);
            }

            // si mode de prescription par num Traitement.
            if (prescription.getConditionnement().getModePrescription().equals(ModePrescription.NUM_TRAITEMENT)) {
                buff.append("Par numéro de traitement, ");
            }

            // Si mode de prescription galénique
            if (prescription.getConditionnement().getModePrescription().equals(ModePrescription.FORME_GALENIQUE) && (prescription.getNbUniteDosage() != null)) {
                buff.append(prescription.getNbUniteDosage()).append(EclipseConstants.SPACE).append("comprimés/gélules, ");
            }

            // Si mode de prescription conditionnement primaire
            if (prescription.getConditionnement().getModePrescription().equals(ModePrescription.CONDITIONNEMENT_PRIMAIRE) && (prescription.getNbUniteDosage() != null)) {
                buff.append(prescription.getNbUniteDosage()).append(EclipseConstants.SPACE).append(prescription.getConditionnement().getUnitePrescription())
                        .append(EclipseConstants.COMMA).append(EclipseConstants.SPACE);
            }

            // fréquence
            buff.append(prescription.getFrequence().getNbFrequence()).append(" fois ");
            if (!prescription.getFrequence().getTypeRegularite().equals(TypeRegularite.FOIS)) {
                buff.append(prescription.getFrequence().getTypeRegularite()).append(EclipseConstants.SPACE);
            };
            if (prescription.getFrequence().getTypeRegularite().equals(TypeRegularite.TOUS_LES)) {
                buff.append(prescription.getFrequence().getNbUniteTempsFrequence()).append(EclipseConstants.SPACE);
            }
            if (!prescription.getFrequence().getTypeRegularite().equals(TypeRegularite.FOIS)) {
                buff.append(prescription.getFrequence().getUniteFrequence().getLibPluriel()).append(EclipseConstants.SPACE);
            }

            // debut
            buff.append("à partir de ").append(prescription.getDebut().getUnite().getLibelleCourt()).append(prescription.getDebut().getNb()).append(EclipseConstants.SPACE);

            // durée
            buff.append("pendant ").append(prescription.getDuree().getNb()).append(EclipseConstants.SPACE).append(prescription.getDuree().getUnite().getLibPluriel());

        }
        return buff.toString();
    }

    /**
     * Méthode en charge de vérifier que les propriétés dans proprerties ne sont
     * pas nulles.
     * @param prescription La prescription
     * @param p Les propriétés.
     * @return <true> si toutes les propriétés properties sont settés.
     */
    private boolean verifyNotNull(final ProduitPrescrit prescription,
                                  final String[] p) {
        for (final String s : p) {
            if (BeanTool.getPropriete(prescription, s) == null) {
                return false;
            }
        }
        return true;
    }
}

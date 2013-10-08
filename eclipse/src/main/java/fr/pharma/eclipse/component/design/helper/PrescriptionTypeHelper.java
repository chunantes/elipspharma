package fr.pharma.eclipse.component.design.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.pharma.eclipse.domain.enums.design.TypeRegularite;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.introspection.BeanTool;

/**
 * Helper du manager de PrescriptionType.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PrescriptionTypeHelper {

    /**
     * Propriétés non null à vérifier avant de générer le résumé.
     */
    private final String[] proprietes = {"debut", "debut.unite", "debut.nb", "duree", "duree.unite", "duree.nb", "frequence", "frequence.nbFrequence", "frequence.typeRegularite",
                                         "conditionnement", "conditionnement.modePrescription" };

    /**
     * Méthode en charge de construire le résumé d'une prescription type.
     * @param prescription La prescription type.
     * @return le résumé d'une prescriptionType.
     */
    public String buildResume(final PrescriptionType prescription) {
        final StringBuffer buff = new StringBuffer();

        String[] proprietesAVerifier;

        // si le type de frequence est different de "une fois" alors, on verifie
        // que l'unite de
        // frequence n'est pas nulle
        proprietesAVerifier = this.majProprietesAverifier(prescription);

        if (this.verifyNotNull(prescription, proprietesAVerifier)) {
            buff.append("Prescription : ");

            // si mode prescription par dose.
            this.buildResumeAjoutDose(prescription, buff);

            // si mode de prescription par num Traitement.
            if (prescription.getConditionnement().getModePrescription().equals(ModePrescription.NUM_TRAITEMENT)) {
                buff.append("Par numéro de traitement, ");
            }

            // Si mode de prescription galénique
            buildResumeAjoutPrescriptionGalenique(prescription, buff);

            // Si mode de prescription conditionnement primaire
            this.buildResumeAjoutConditionnementPrimaire(prescription, buff);

            // fréquence
            this.buildResumeAjoutFrequence(prescription, buff);

            // debut
            buff.append("à partir de ").append(prescription.getDebut().getUnite().getLibelleCourt()).append(prescription.getDebut().getNb()).append(EclipseConstants.SPACE);

            // durée
            buff.append("pendant ").append(prescription.getDuree().getNb()).append(EclipseConstants.SPACE).append(prescription.getDuree().getUnite().getLibPluriel());

        }
        return buff.toString();
    }
    /**
     * @param prescription
     * @param resume
     */
    private void buildResumeAjoutPrescriptionGalenique(final PrescriptionType prescription,
                                                       final StringBuffer resume) {
        if (prescription.getConditionnement().getModePrescription().equals(ModePrescription.FORME_GALENIQUE) && (prescription.getNbUniteDosage() != null)) {
            resume.append(prescription.getNbUniteDosage()).append(EclipseConstants.SPACE).append("comprimés/gélules, ");
        }
    }
    /**
     * Ajout du conditionnement au résumé d'une prescription type
     * @param prescription
     * @param resume
     */
    private void buildResumeAjoutConditionnementPrimaire(final PrescriptionType prescription,
                                                         final StringBuffer resume) {
        if (prescription.getConditionnement().getModePrescription().equals(ModePrescription.CONDITIONNEMENT_PRIMAIRE) && (prescription.getNbUniteDosage() != null)) {
            resume.append(prescription.getNbUniteDosage()).append(EclipseConstants.SPACE).append(prescription.getConditionnement().getUnitePrescription())
                    .append(EclipseConstants.COMMA).append(EclipseConstants.SPACE);
        }
    }
    /**
     * Ajout de la dose au résumé d'une prescription type
     * @param prescription
     * @param resume
     */
    private void buildResumeAjoutDose(final PrescriptionType prescription,
                                      final StringBuffer resume) {
        final String[] props = {"conditionnement.unitePrescription", "nbUniteDosage", "dosage" };

        if ((this.verifyNotNull(prescription, props) && prescription.getConditionnement().getModePrescription().equals(ModePrescription.DOSE))
            || prescription.getConditionnement().getModePrescription().equals(ModePrescription.DOSE_KG)
            || prescription.getConditionnement().getModePrescription().equals(ModePrescription.DOSE_SURFACE)) {
            resume.append(prescription.getNbUniteDosage()).append(" fois ").append(prescription.getDosage()).append(EclipseConstants.SPACE)
                    .append(prescription.getConditionnement().getUnitePrescription()).append(EclipseConstants.COMMA).append(EclipseConstants.SPACE);
        }
    }
    /**
     * Ajout de la frequence au résumé d'une prescription type
     * @param prescription
     * @param resume
     */
    private void buildResumeAjoutFrequence(final PrescriptionType prescription,
                                           final StringBuffer resume) {
        resume.append(prescription.getFrequence().getNbFrequence()).append(" fois").append(EclipseConstants.SPACE);
        if (!prescription.getFrequence().getTypeRegularite().equals(TypeRegularite.FOIS)) {
            resume.append(prescription.getFrequence().getTypeRegularite()).append(EclipseConstants.SPACE);
        }
        if (prescription.getFrequence().getTypeRegularite().equals(TypeRegularite.TOUS_LES)) {
            resume.append(prescription.getFrequence().getNbUniteTempsFrequence()).append(EclipseConstants.SPACE);
        }
        if (!prescription.getFrequence().getTypeRegularite().equals(TypeRegularite.FOIS)) {
            resume.append(prescription.getFrequence().getUniteFrequence().getLibPluriel()).append(EclipseConstants.SPACE);
        }
    }
    /**
     * Met à jour la liste des propriétés à vérifier
     * @param prescription
     * @return
     */
    private String[] majProprietesAverifier(final PrescriptionType prescription) {
        String[] proprietesAVerifier;
        if ((prescription.getFrequence().getTypeRegularite() != null) && !prescription.getFrequence().getTypeRegularite().equals(TypeRegularite.FOIS)) {
            // on ajoute l'unite de frequence à la liste des propriétés à
            // verifier
            final List<String> proprietesAsArrayList = new ArrayList<String>(Arrays.asList(this.proprietes));
            proprietesAsArrayList.add(new String("frequence.uniteFrequence"));
            proprietesAVerifier = proprietesAsArrayList.toArray(new String[proprietesAsArrayList.size()]);
        } else {
            proprietesAVerifier = this.proprietes;
        }
        return proprietesAVerifier;
    }
    /**
     * Méthode en charge de vérifier que les propriétés dans proprerties ne sont
     * pas nulles.
     * @param prescription La prescription
     * @param proprietes Les propriétés.
     * @return <true> si toutes les propriétés properties sont settés.
     */
    private boolean verifyNotNull(final PrescriptionType prescription,
                                  final String[] proprietes) {
        for (final String s : proprietes) {
            if (BeanTool.getPropriete(prescription, s) == null) {
                return false;
            }
        }
        return true;
    }
}

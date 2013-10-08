package fr.pharma.eclipse.factory.prescription;

import java.util.Calendar;

import fr.pharma.eclipse.domain.model.design.embedded.Frequence;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Factory de Prescription.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PrescriptionFactory extends BeanObjectFactory<Prescription> {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -3292240614641548997L;

    /**
     * Constructeur.
     * @param bean La classe.
     */
    public PrescriptionFactory(final Class<Prescription> bean) {
        super(bean);
    }

    /**
     * Méthode en charge de fournir une prescription en initialisant certains
     * champs.
     * @param inclusion L'inclusion.
     * @return La prescription initialisée.
     */
    public Prescription getInitializedObject(final Inclusion inclusion) {
        final Prescription prescription = super.getInitializedObject();
        prescription.setInclusion(inclusion);
        prescription.setDispense(false);
        prescription.setDatePrescription(Calendar.getInstance(EclipseConstants.LOCALE));
        prescription.setNumPrescription(1);
        return prescription;
    }

    /**
     * Méthode en charge de fournir une prescription à partir d'un prescription.
     * Elle incrémente le compteur et met à jour la date de prescription et les
     * produits.
     * @param prescriptionOld La prescription.
     * @return La prescription initialisée.
     */
    public Prescription getInitializedObject(final Prescription prescriptionOld) {
        final Prescription prescription = super.getInitializedObject();
        prescription.setInclusion(prescriptionOld.getInclusion());
        prescription.setDispense(false);
        prescription.setDatePrescription(Calendar.getInstance(EclipseConstants.LOCALE));
        prescription.setNumPrescription(prescriptionOld.getNumPrescription() + 1);
        prescription.setDateDebutTraitement(prescriptionOld.getDateDebutTraitement());
        prescription.setInvestigateur(prescriptionOld.getInvestigateur());
        prescription.setSequence(prescriptionOld.getSequence());
        prescription.setService(prescriptionOld.getService());
        for (final ProduitPrescrit p : prescriptionOld.getProduitsPrescrits()) {
            final ProduitPrescrit clone = p.cloneMe();
            clone.setPrescription(prescription);
            prescription.getProduitsPrescrits().add(clone);
            if (p.getDebut() == null) {
                clone.setDebut(new TempsPrescription());
            }
            if (p.getDuree() == null) {
                clone.setDuree(new TempsPrescription());
            }
            if (p.getFrequence() == null) {
                clone.setFrequence(new Frequence());
            }
        }
        return prescription;
    }
}

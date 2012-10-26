package fr.pharma.eclipse.factory.prescription;

import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.domain.model.design.embedded.Frequence;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;

/**
 * Factory de ProduitPrescrit.
 
 * @version $Revision$ $Date$
 */
public class ProduitPrescritFactory
    extends BeanObjectFactory<ProduitPrescrit>
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -2424299760886066053L;

    /**
     * Constructeur.
     * @param bean La classe.
     */
    public ProduitPrescritFactory(final Class<ProduitPrescrit> bean)
    {
        super(bean);
    }

    /**
     * Méthode en charge d'initialiser un ProduitPrescript avec les informations de la
     * PrescriptionType.
     * @param prescriptionType La prescriptionType.
     * @param prescription L'objet Prescription.
     * @return Le ProduitPrescript initialisé.
     */
    public ProduitPrescrit getInitializedObject(final PrescriptionType prescriptionType,
                                                final Prescription prescription)
    {

        final ProduitPrescrit produitPrescrit = this.getInitializedObject(prescription);
        produitPrescrit.setDebut(prescriptionType.getDebut());
        if (prescriptionType.getDebut() == null)
        {
            produitPrescrit.setDebut(new TempsPrescription());
        }
        produitPrescrit.setDuree(prescriptionType.getDuree());

        if (prescriptionType.getDuree() == null)
        {
            produitPrescrit.setDuree(new TempsPrescription());
        }
        produitPrescrit.setDescription(prescriptionType.getDescription());
        produitPrescrit.setFrequence(prescriptionType.getFrequence());
        if (prescriptionType.getFrequence() == null)
        {
            produitPrescrit.setFrequence(new Frequence());
        }
        produitPrescrit.setDosage(prescriptionType.getDosage());
        produitPrescrit.setNbUniteDosage(prescriptionType.getNbUniteDosage());
        produitPrescrit.setProduit(prescriptionType.getProduit());
        produitPrescrit.setConditionnement(prescriptionType.getConditionnement());
        return produitPrescrit;
    }

    /**
     * Méthode en charge d'initialiser un ProduitPrescript avec les informations de la
     * Prescription.
     * @param prescription L'objet Prescription.
     * @return Le ProduitPrescript initialisé.
     */
    public ProduitPrescrit getInitializedObject(final Prescription prescription)
    {
        final ProduitPrescrit produitPrescrit = super.getInitializedObject();
        produitPrescrit.setPrescription(prescription);
        produitPrescrit.setADispenser(true);
        return produitPrescrit;
    }

}

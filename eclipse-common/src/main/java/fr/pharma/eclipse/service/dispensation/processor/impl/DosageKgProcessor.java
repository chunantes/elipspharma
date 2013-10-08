package fr.pharma.eclipse.service.dispensation.processor.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import fr.pharma.eclipse.domain.model.dispensation.ConseilDispensation;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.service.dispensation.processor.ConseilDispensationProcessor;

/**
 * Processor en charge de calculer les conseils à la dispensation pour les modes
 * de prescription DOSAGE.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DosageKgProcessor extends GenericConseilDispensationProcessor implements ConseilDispensationProcessor, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 6854906395967326745L;

    /**
     * {@inheritDoc}
     */
    @Override
    public ConseilDispensation process(final ProduitPrescrit produitPrescrit) {
        // Si le poid du patient est null alors il n'est pas possible de
        // calculer la quantité.
        if (produitPrescrit.getPrescription().getInclusion().getPatient().getPoid() == null) {
            return null;
        }
        return super.process(produitPrescrit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer processNbASortir(final ProduitPrescrit produitPrescrit) {

        final Integer nb = this.frequenceHelper.convertToInt(produitPrescrit.getDuree(), produitPrescrit.getFrequence());
        if (nb == null) {
            return null;
        }

        final double poid = Math.ceil(produitPrescrit.getPrescription().getInclusion().getPatient().getPoid());

        return new BigDecimal(poid).multiply(new BigDecimal(nb)).multiply(produitPrescrit.getNbUniteDosage()).intValue();
    }
}

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
public class DosageProcessor extends GenericConseilDispensationProcessor implements ConseilDispensationProcessor, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -3518761341678586215L;

    /**
     * {@inheritDoc}
     */
    @Override
    public ConseilDispensation process(final ProduitPrescrit produitPrescrit) {
        return super.process(produitPrescrit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer processNbASortir(final ProduitPrescrit produitPrescrit) {
        return new BigDecimal(this.frequenceHelper.convertToInt(produitPrescrit.getDuree(), produitPrescrit.getFrequence())).multiply(produitPrescrit.getNbUniteDosage())
                .intValue();
    }
}

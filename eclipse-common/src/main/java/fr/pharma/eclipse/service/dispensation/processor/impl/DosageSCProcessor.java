package fr.pharma.eclipse.service.dispensation.processor.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import fr.pharma.eclipse.domain.model.dispensation.ConseilDispensation;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.service.dispensation.processor.ConseilDispensationProcessor;

/**
 * Processor en charge de calculer les conseils à la dispensation pour les modes de prescription
 * DOSAGE par surface corporelle.
 
 * @version $Revision$ $Date$
 */
public class DosageSCProcessor
    extends GenericConseilDispensationProcessor
    implements ConseilDispensationProcessor, Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 6854906395967326745L;

    /**
     * {@inheritDoc}
     */
    @Override
    public ConseilDispensation process(final ProduitPrescrit produitPrescrit)
    {
        if (produitPrescrit.getPrescription().getInclusion().getPatient().getPoid() == null)
        {
            return null;
        }
        return super.process(produitPrescrit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer processNbASortir(final ProduitPrescrit produitPrescrit)
    {

        final Integer nb = this.frequenceHelper.convertToInt(produitPrescrit.getDuree(),
                                                             produitPrescrit.getFrequence());
        if (nb == null)
        {
            return null;
        }
        return (int) Math.ceil(new BigDecimal(produitPrescrit
                .getPrescription()
                .getInclusion()
                .getPatient()
                .getSurface())
                .multiply(produitPrescrit.getNbUniteDosage())
                .multiply(new BigDecimal(nb))
                .doubleValue());

    }

}

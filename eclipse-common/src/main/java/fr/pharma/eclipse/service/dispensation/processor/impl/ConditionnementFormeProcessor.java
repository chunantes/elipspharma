package fr.pharma.eclipse.service.dispensation.processor.impl;

import java.io.Serializable;
import java.math.BigDecimal;

import fr.pharma.eclipse.domain.model.dispensation.ConseilDispensation;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.service.dispensation.processor.ConseilDispensationProcessor;

/**
 * Processor en charge de calculer le conseil à la dispensation pour les produits prescrit qui ont
 * pour mode de prescription CONDITIONNEMENT_PRIMAIRE.
 
 * @version $Revision$ $Date$
 */
public class ConditionnementFormeProcessor
    extends GenericConseilDispensationProcessor
    implements ConseilDispensationProcessor, Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -1796754004076942511L;

    /**
     * {@inheritDoc}
     */
    @Override
    public ConseilDispensation process(final ProduitPrescrit produitPrescrit)
    {
        if (produitPrescrit.getConditionnement().getNbUnitePrescription() == null)
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
        return (int) Math.ceil(produitPrescrit
                .getNbUniteDosage()
                .multiply(new BigDecimal(nb))
                .divide(produitPrescrit.getConditionnement().getNbUnitePrescription(),
                        BigDecimal.ROUND_UP)
                .doubleValue());
    }
}

package fr.pharma.eclipse.service.surcout.processor.impl;

import java.math.BigDecimal;

import fr.pharma.eclipse.domain.model.surcout.Resultat;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;
import fr.pharma.eclipse.service.surcout.processor.VariableSubProcessor;

/**
 * Classe implémentant la méthode contenant l'algorithme de calcul d'un montant
 * au forfait pour un surcout.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ForfaitSubProcessor extends AbstractVariableSubProcessor implements VariableSubProcessor {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -1370667429767092788L;

    /**
     * {@inheritDoc}
     */
    @Override
    protected Resultat processSubProcessor(final Regle regle,
                                           final int nb) {
        final Resultat value = new Resultat();
        if (nb == 0) {
            value.setMontant(new BigDecimal(0));
        } else if (regle.getMin() == null) {
            value.setMontant(value.getMontant().add(regle.getMontant()));
        } else {
            if (nb >= regle.getMin()) {
                value.setMontant(value.getMontant().add(regle.getMontant()));
            }
        }
        value.setNbActes(nb);
        return value;
    }

}

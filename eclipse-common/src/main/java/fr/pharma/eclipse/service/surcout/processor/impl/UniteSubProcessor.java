package fr.pharma.eclipse.service.surcout.processor.impl;

import java.math.BigDecimal;

import fr.pharma.eclipse.domain.model.surcout.Resultat;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;
import fr.pharma.eclipse.service.surcout.processor.VariableSubProcessor;

/**
 * Classe implémentant la méthode contenant l'algorithme de calcul d'un montant à l'unité pour un
 * surcout.
 
 * @version $Revision$ $Date$
 */
public class UniteSubProcessor
    extends AbstractVariableSubProcessor
    implements VariableSubProcessor
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -1370667429767092788L;

    /**
     * {@inheritDoc}
     */
    @Override
    protected Resultat processSubProcessor(final Regle regle,
                                           final int nb)
    {
        int nbComptes = 0;

        // max null
        if (regle.getMax() == null)
        {
            if (nb >= regle.getMin())
            {
                if (regle.getMin() < 1)
                {
                    nbComptes = nb;
                }
                else
                {
                    nbComptes = nb
                                - regle.getMin()
                                + 1;
                }
            }
        }

        // min null
        else if (regle.getMin() == null
                 || regle.getMin() <= 1)
        {
            if (nb < regle.getMax())
            {
                nbComptes = nb;
            }
            else
            {
                nbComptes = regle.getMax();
            }
        }

        // min et max non null
        else if (nb >= regle.getMin())
        {
            if (nb > regle.getMax())
            {
                nbComptes = regle.getMax()
                            - regle.getMin()
                            + 1;
            }
            else
            {
                nbComptes = nb
                            - regle.getMin()
                            + 1;
            }
        }
        final Resultat resultat = new Resultat();
        resultat.setNbActes(nbComptes);
        resultat.setMontant(regle.getMontant().multiply(new BigDecimal(nbComptes)));
        return resultat;
    }

}

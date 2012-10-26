package fr.pharma.eclipse.predicate.essai;

import java.io.Serializable;

import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;

/**
 * Classe en charge de retourner l'habilitation correspondant à un droit.
 
 * @version $Revision$ $Date$
 */
public class HabilitationDroitPredicate
    implements Predicate, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5009525055655887729L;

    /**
     * Droit.
     */
    private final Droit droit;

    /**
     * Constructeur prenant un Droit.
     * @param droit Droit.
     */
    public HabilitationDroitPredicate(final Droit droit)
    {
        this.droit = droit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(final Object object)
    {
        final Habilitation habilitation = (Habilitation) object;
        return this.droit.equals(habilitation.getDroit());
    }
}

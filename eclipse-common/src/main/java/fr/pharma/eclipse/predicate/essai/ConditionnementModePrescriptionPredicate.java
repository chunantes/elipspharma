package fr.pharma.eclipse.predicate.essai;

import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.predicate.GenericPredicate;

/**
 * Prédicat portant sur le mode de prescription d'un conditionnement.
 
 * @version $Revision$ $Date$
 */
public class ConditionnementModePrescriptionPredicate
    implements Predicate
{
    /**
     * Prédicat générique initialisé.
     */
    private final GenericPredicate predicate;

    /**
     * Constructeur.
     * @param modePrescriptionAttendu Mode de prescription attendu.
     */
    public ConditionnementModePrescriptionPredicate(final ModePrescription modePrescriptionAttendu)
    {
        this.predicate = new GenericPredicate("modePrescription",
                                              modePrescriptionAttendu);
    }

    /**
     * Constructeur.
     * @param modePrescriptionAttenduStr Nom du mode de prescription attendu.<br>
     * modePrescriptionAttendu doit correspondre à une valeur de l'énumération ModePrescription.
     */
    public ConditionnementModePrescriptionPredicate(final String modePrescriptionAttenduStr)
    {
        this(ModePrescription.valueOf(modePrescriptionAttenduStr));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(final Object object)
    {
        return this.predicate.evaluate(object);
    }

}

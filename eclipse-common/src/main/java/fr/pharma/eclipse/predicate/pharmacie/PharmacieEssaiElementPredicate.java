package fr.pharma.eclipse.predicate.pharmacie;

import org.apache.commons.collections.Predicate;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.EssaiElement;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Predicate en charge de filtrer les éléments relatif à un essai qui n'est pas associé à la
 * pharmacie de référence. Le prédicat retourne false, cela signifie que la pharmacie n'est pas
 * associé à l'essai relatif à l'élément.
 
 * @version $Revision$ $Date$
 */
public class PharmacieEssaiElementPredicate
    implements Predicate
{

    /**
     * La pharmacie de référence.
     */
    private final Pharmacie pharmacie;

    /**
     * Constructeur .
     * @param pharmacie La pharmacie à rechercher.
     */
    public PharmacieEssaiElementPredicate(final Pharmacie pharmacie)
    {
        this.pharmacie = pharmacie;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean evaluate(final Object object)
    {
        final EssaiElement element = (EssaiElement) object;
        final Essai essai = element.getEssai();
        return essai.getPharmaciePrincipale().equals(this.pharmacie)
               || essai.getDetailDonneesPharma().getPharmacies().contains(this.pharmacie);
    }

}

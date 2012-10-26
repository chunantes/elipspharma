package fr.pharma.eclipse.component.essai.helper.impl;

import org.springframework.util.Assert;

import fr.pharma.eclipse.component.essai.helper.DroitHabilitationInitializer;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;

/**
 * Classe en charge d'initialiser le droit d'une habilitation de pharmacien.
 
 * @version $Revision$ $Date$
 */
public class PharmacienDroitHabilitationInitializer
    implements DroitHabilitationInitializer
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5376309118926335500L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final Habilitation habilitation)
    {
        final Personne personne = habilitation.getPersonne();
        Assert.isTrue(personne != null,
                      "Aucune personne n'est associée à l'habilitation.");
        Assert.isTrue(TypePersonne.PHARMACIEN.equals(personne.getType()),
                      "La personne n'est pas un pharmacien!");
        final Pharmacien pharmacien = (Pharmacien) personne;
        habilitation.setDroit(pharmacien.getTypePharmacien().getDroit());
    }

}

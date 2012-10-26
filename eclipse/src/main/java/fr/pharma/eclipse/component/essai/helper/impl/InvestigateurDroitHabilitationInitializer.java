package fr.pharma.eclipse.component.essai.helper.impl;

import org.springframework.util.Assert;

import fr.pharma.eclipse.component.essai.helper.DroitHabilitationInitializer;
import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;

/**
 * Classe en charge d'initialiser le droit d'une habilitation d'investigateur.
 
 * @version $Revision$ $Date$
 */
public class InvestigateurDroitHabilitationInitializer
    implements DroitHabilitationInitializer
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2191686361546627171L;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final Habilitation habilitation)
    {
        final Personne personne = habilitation.getPersonne();
        Assert.isTrue(personne != null,
                      "Aucune personne n'est associée à l'habilitation.");
        Assert.isTrue(TypePersonne.INVESTIGATEUR.equals(personne.getType()),
                      "La personne n'est pas un pharmacien!");

        Droit droit;
        if (Boolean.TRUE.equals(personne.getSelected()))
        {
            // Investigateur principal
            droit = Droit.INVESTIGATEUR_PRINCIPAL;
        }
        else
        {
            // Co investigateur
            droit = Droit.INVESTIGATEUR_CO;
        }
        habilitation.setDroit(droit);
    }

}

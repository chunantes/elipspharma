package fr.pharma.eclipse.domain.model.acteur;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Classe métier représentant une personne de la Direction de la recherche (DRC).
 
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("DIRECTION_RECHERCHE")
public class DirectionRecherche
    extends Personne
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -400961352063733207L;

}

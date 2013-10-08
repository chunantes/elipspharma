package fr.pharma.eclipse.domain.model.stock;

import javax.persistence.Entity;

/**
 * Bean métier représentant un mouvement de préparation en sortie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "mvt_preparation")
public class PreparationSortie extends MvtStock {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 2427219089581455904L;

}

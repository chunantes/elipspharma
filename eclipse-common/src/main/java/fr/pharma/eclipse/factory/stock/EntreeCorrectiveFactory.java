package fr.pharma.eclipse.factory.stock;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;

/**
 * Factory de Bean Entree Corrective.
 
 * @version $Revision$ $Date$
 */
public class EntreeCorrectiveFactory
    extends ApprovisionnementFactory

{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1206837232672178736L;

    /**
     * Constructeur.
     * @param bean Classe.
     */
    public EntreeCorrectiveFactory(final Class<Approvisionnement> bean)
    {
        super(bean,
              TypeMvtStock.ENTREE_CORRECTIVE);
    }

}

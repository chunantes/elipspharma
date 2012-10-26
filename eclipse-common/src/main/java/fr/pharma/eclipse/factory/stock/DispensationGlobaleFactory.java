package fr.pharma.eclipse.factory.stock;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.stock.DispensationGlobale;

/**
 * Factory de dispensation globale.
 
 * @version $Revision$ $Date$
 */
public class DispensationGlobaleFactory<MVT extends DispensationGlobale>
    extends MvtStockFactory<MVT>
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -2887457893444807935L;

    /**
     * Constructeur.
     * @param bean La classe du mouvement.
     */
    public DispensationGlobaleFactory(final Class<MVT> bean)
    {
        super(bean,
              TypeMvtStock.DOTATION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MVT getInitializedObject()
    {
        final MVT mvt = super.getInitializedObject();
        mvt.setQuantiteDispensee(0);
        return mvt;
    }
}

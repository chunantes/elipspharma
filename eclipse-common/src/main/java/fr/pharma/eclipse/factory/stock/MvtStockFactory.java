package fr.pharma.eclipse.factory.stock;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;

/**
 * Factory de Bean MvtStock.
 * @author Netapsys
 * @version $Revision$ $Date$
 * @param <MVT> Bean Objet MvtStock.
 */
public class MvtStockFactory<MVT extends MvtStock> extends BeanObjectFactory<MVT> {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -7511938338539761791L;

    /**
     * Type du mouvement de stock.
     */
    private final TypeMvtStock typeMouvement;

    /**
     * Constructeur.
     * @param bean Classe.
     * @param typeMouvement Type du mouvement de stock.
     */
    public MvtStockFactory(final Class<MVT> bean, final TypeMvtStock typeMouvement) {
        super(bean);
        this.typeMouvement = typeMouvement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MVT getInitializedObject() {
        final MVT mouvementStock = super.getInitializedObject();
        mouvementStock.setType(this.typeMouvement);
        return mouvementStock;
    }

}

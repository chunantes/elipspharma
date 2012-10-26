package fr.pharma.eclipse.factory.produit;

import fr.pharma.eclipse.domain.enums.produit.TypeDetailStockage;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;

/**
 * Factory de Bean DetailStockage.
 
 * @version $Revision$ $Date$
 * @param <DETAIL> Type DetailStockage.
 */
public class DetailStockageFactory<DETAIL extends DetailStockage>
    extends BeanObjectFactory<DETAIL>
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur.
     * @param bean Classe.
     */
    public DetailStockageFactory(final Class<DETAIL> bean)
    {
        super(bean);
    }

    /**
     * Construction d'un conditionnement à partir d'un dispositif médical.
     * @param produit Le produit.
     * @param type le Type de detail stockage.
     * @return Le detail stockage.
     */
    public DETAIL getInitializedObject(final Produit produit,
                                       final TypeDetailStockage type)
    {
        final DETAIL detailStockage = super.getInitializedObject();
        detailStockage.setType(type);
        detailStockage.setDetailLogistique(produit.getDetailLogistique());
        return detailStockage;
    }
}

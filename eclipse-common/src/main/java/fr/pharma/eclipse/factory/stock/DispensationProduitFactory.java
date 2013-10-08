package fr.pharma.eclipse.factory.stock;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;

/**
 * Factory de Bean DispensationProduit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationProduitFactory extends MvtStockFactory<DispensationProduit> {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -7511938338539761791L;

    /**
     * Constructeur.
     * @param bean Classe.
     */
    public DispensationProduitFactory(final Class<DispensationProduit> bean) {
        super(bean, TypeMvtStock.DISPENSATION);
    }

    /**
     * {@inheritDoc}
     */
    public DispensationProduit getInitializedObject(final ProduitPrescrit produit,
                                                    final Dispensation dispensation) {
        final DispensationProduit mouvementStock = super.getInitializedObject();
        mouvementStock.setProduit(produit.getProduit());
        mouvementStock.setProduitPrescrit(produit);
        mouvementStock.setEssai(produit.getPrescription().getInclusion().getEssai());
        mouvementStock.setNumTraitement(produit.getNumTraitement());
        mouvementStock.setPharmacie(produit.getPrescription().getInclusion().getEssai().getPharmaciePrincipale());
        return mouvementStock;
    }

}

package fr.pharma.eclipse.component.stock;

import javax.annotation.Resource;
import javax.faces.event.ActionEvent;

import fr.pharma.eclipse.component.BeanManager;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.factory.stock.DispensationProduitFactory;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Manager de Dispensation de produit.
 
 * @version $Revision$ $Date$
 */
public class DispensationProduitManager
    extends BeanManager<DispensationProduit>
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 7497144213808343089L;

    /**
     * Factory de DispensationProduit.
     */
    @Resource(name = "dispensationProduitFactory")
    private DispensationProduitFactory factory;

    /**
     * Constructeur.
     * @param service Le service.
     */
    public DispensationProduitManager(final GenericService<DispensationProduit> service)
    {
        super(service);
    }

    /**
     * Méthode appelée par l'IHM en charge d'initialiser une DispensationProduit pour la popup de
     * dispensation d'un produit.
     * @param event Evenemetn JSF.
     */
    public void initDispensation(final ActionEvent event)
    {
        final ProduitPrescrit produit =
            (ProduitPrescrit) event.getComponent().getAttributes().get("produitCurrent");
        final Dispensation dispensation =
            (Dispensation) event.getComponent().getAttributes().get("dispensation");
        this.setBean(this.factory.getInitializedObject(produit,
                                                       dispensation));
    }

    /**
     * Setter pour factory.
     * @param factory le factory à écrire.
     */
    public void setFactory(final DispensationProduitFactory factory)
    {
        this.factory = factory;
    }

}

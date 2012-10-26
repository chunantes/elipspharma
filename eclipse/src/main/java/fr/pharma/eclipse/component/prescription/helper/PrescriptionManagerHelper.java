package fr.pharma.eclipse.component.prescription.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.model.DataModel;

import org.springframework.faces.model.SerializableListDataModel;

import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.factory.prescription.ProduitPrescritFactory;
import fr.pharma.eclipse.service.common.GenericService;

/**
 * Helper du manager PrescriptionManager.
 
 * @version $Revision$ $Date$
 */
public class PrescriptionManagerHelper
    implements Serializable
{

    /**
     * Factory de ProduitPrescrit.
     */
    @Resource(name = "produitPrescritFactory")
    private ProduitPrescritFactory factory;

    /**
     * Service Produt.
     */
    @Resource(name = "produitService")
    private GenericService<Produit> produitService;

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 5433380424627166099L;

    /**
     * Méthode en charge de mettre à jour les produits prescrits de la prescription avec les
     * prescriptions types définies dans la sequence.
     * @param prescription La prescription.
     */
    public void initProduitsPrescrits(final Prescription prescription)
    {
        for (final PrescriptionType p : prescription.getSequence().getPrescriptions())
        {
            prescription.getProduitsPrescrits().add(this.factory
                    .getInitializedObject(p,
                                          prescription));
        }
    }

    /**
     * Retourne les conditionnements d'un produit.
     * @param p Le produit.
     * @return Les conditionnements.
     */
    @SuppressWarnings("unchecked")
    public DataModel<Conditionnement> getConditionnements(final Produit p)
    {
        final List<Conditionnement> list = new ArrayList<Conditionnement>();
        if (p != null)
        {
            list.addAll(this.produitService.reattach(p).getConditionnements());
        }
        return new SerializableListDataModel(list);
    }

    /**
     * Setter pour produitService.
     * @param produitService le produitService à écrire.
     */
    public void setProduitService(final GenericService<Produit> produitService)
    {
        this.produitService = produitService;
    }

    /**
     * Setter pour factory.
     * @param factory le factory à écrire.
     */
    public void setFactory(final ProduitPrescritFactory factory)
    {
        this.factory = factory;
    }

}

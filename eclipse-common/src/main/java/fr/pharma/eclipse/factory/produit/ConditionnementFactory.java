package fr.pharma.eclipse.factory.produit;

import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.DispositifMedical;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Preparation;
import fr.pharma.eclipse.domain.model.produit.ProduitTherapeutique;
import fr.pharma.eclipse.factory.common.BeanObjectFactory;

/**
 * Factory de Bean Conditionnement.
 
 * @version $Revision$ $Date$
 * @param <CONDITIONNEMENT> Type Conditionnement.
 */
public class ConditionnementFactory<CONDITIONNEMENT extends Conditionnement>
    extends BeanObjectFactory<CONDITIONNEMENT>
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur.
     * @param bean Classe.
     */
    public ConditionnementFactory(final Class<CONDITIONNEMENT> bean)
    {
        super(bean);
    }

    /**
     * Construction d'un conditionnement à partir d'un dispositif médical.
     * @param dm Le dispositif Médical.
     * @return Le conditionnement.
     */
    public CONDITIONNEMENT getInitializedObject(final DispositifMedical dm)
    {
        final CONDITIONNEMENT conditionnement = super.getInitializedObject();
        conditionnement.setProduit(dm);
        return conditionnement;
    }

    /**
     * Construction d'un conditionnement à partir d'un médicament.
     * @param medicament Le médicament.
     * @return Le conditionnement.
     */
    public CONDITIONNEMENT getInitializedObject(final Medicament medicament)
    {
        final CONDITIONNEMENT conditionnement = super.getInitializedObject();
        conditionnement.setProduit(medicament);
        return conditionnement;
    }

    /**
     * Construction d'un conditionnement à partir d'un produit thérapeutique.
     * @param produit Le produit thérapeutique.
     * @return Le conditionnement.
     */
    public CONDITIONNEMENT getInitializedObject(final ProduitTherapeutique produit)
    {
        final CONDITIONNEMENT conditionnement = super.getInitializedObject();
        conditionnement.setProduit(produit);
        return conditionnement;
    }

    /**
     * Construction d'un conditionnement à partir d'un préparation.
     * @param produit La préparation.
     * @return Le conditionnement.
     */
    public CONDITIONNEMENT getInitializedObject(final Preparation produit)
    {
        final CONDITIONNEMENT conditionnement = super.getInitializedObject();
        conditionnement.setProduit(produit);
        return conditionnement;
    }

}

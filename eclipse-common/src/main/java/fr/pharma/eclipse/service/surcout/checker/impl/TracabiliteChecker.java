package fr.pharma.eclipse.service.surcout.checker.impl;

import java.io.Serializable;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.ProduitTherapeutique;
import fr.pharma.eclipse.service.surcout.checker.SurcoutChecker;

/**
 * Classe en charge de déterminer si la tracabilite doit être chiffrés ou non. essai.
 
 * @version $Revision$ $Date$
 */
public class TracabiliteChecker
    implements SurcoutChecker, Serializable
{
    /**
     * Serial UID.
     */
    private static final long serialVersionUID = 2253013872072014364L;

    /**
     * {@inheritDoc}
     */
    public boolean check(final Essai essai)
    {

        for (final Produit m : essai.getDetailProduit().getProduits())
        {
            if (m instanceof Medicament)
            {
                if (((Medicament) m).getMds()
                    || ((Medicament) m).getStupefiant())
                {
                    return true;
                }
            }
            if (m instanceof ProduitTherapeutique)
            {
                if (((ProduitTherapeutique) m).getMds()
                    || ((ProduitTherapeutique) m).getStupefiant())
                {
                    return true;
                }
            }
        }

        return false;

    }

}

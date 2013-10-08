package fr.pharma.eclipse.jasper.engine.filler.impl.modelprescription.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import fr.pharma.eclipse.domain.jasper.model.prescnominative.JRBeanProduitPrescrit;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;

/**
 * Helper en charge de transformer le
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ProduitPrescritFillerHelper {
    /**
     * {@inheritDoc}
     */
    public Collection<JRBeanProduitPrescrit> transform(final Set<ProduitPrescrit> produits) {
        final Collection<JRBeanProduitPrescrit> beanProduits = new ArrayList<JRBeanProduitPrescrit>();
        for (final ProduitPrescrit produit : produits) {
            final JRBeanProduitPrescrit jrProduit = new JRBeanProduitPrescrit();
            jrProduit.setDenomination(produit.getProduit().getDenomination());

            if ((produit.getDuree() != null) && (produit.getDuree().getNb() != null) && (produit.getDuree().getUnite() != null)) {
                jrProduit.setDuree(produit.getDuree().getNb() + " " + produit.getDuree().getUnite().getLibelle());
            }
            if ((produit.getFrequence() != null) && (produit.getDosage() != null)) {
                jrProduit.setPosologie(produit.getDosageAsString() + " " + produit.getFrequence().toString());
            }
            if ((produit.getConditionnement() != null) && (produit.getConditionnement().getVoieAdministration() != null)) {
                jrProduit.setVoie(produit.getConditionnement().getVoieAdministration().getLibelle());
            }

            beanProduits.add(jrProduit);
        }

        return beanProduits;
    }
}

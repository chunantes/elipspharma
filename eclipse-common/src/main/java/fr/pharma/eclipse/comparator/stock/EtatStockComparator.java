package fr.pharma.eclipse.comparator.stock;

import java.io.Serializable;
import java.util.Comparator;

import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.stock.EtatStock;

/**
 * Comparator de Bean EtatStock.
 
 * @version $Revision$ $Date$
 */
public class EtatStockComparator
    implements Comparator<EtatStock>, Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5062201276634513665L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final EtatStock etatStock1,
                       final EtatStock etatStock2)
    {
        final Essai essai1 = etatStock1.getEssai();
        final Essai essai2 = etatStock2.getEssai();

        final StringBuilder sb1 = new StringBuilder();
        sb1.append(essai1.getNumInterne())
                .append(essai1.getNom())
                .append(etatStock1.getPharmacie().getNom())
                .append(etatStock1.getProduit().getDenomination())
                .append(etatStock1.getConditionnement().getLibelle());

        final StringBuilder sb2 = new StringBuilder();
        sb2.append(essai2.getNumInterne())
                .append(essai2.getNom())
                .append(etatStock2.getPharmacie().getNom())
                .append(etatStock2.getProduit().getDenomination())
                .append(etatStock2.getConditionnement().getLibelle());

        return sb1.toString().compareTo(sb2.toString());
    }
}

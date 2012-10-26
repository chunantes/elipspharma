package fr.pharma.eclipse.jasper.engine.filler.impl.certificat.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.pharma.eclipse.domain.jasper.model.certificat.JRBeanProduitSorti;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Helper en charge de transformer les produits sortis en JRBean correspondants.
 
 * @version $Revision$ $Date$
 */
public class ProduitSortiFillerHelper
    implements Serializable
{
    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    public Collection<JRBeanProduitSorti> transform(final List<? extends MvtStock> mvts)
    {
        final Collection<JRBeanProduitSorti> beanProduits = new ArrayList<JRBeanProduitSorti>();

        for (final MvtStock mvt : mvts)
        {

            final JRBeanProduitSorti jrProduit = new JRBeanProduitSorti();

            // si la date de péremption est setté on l'utilise.
            if (mvt.getDatePeremption() != null)
            {
                jrProduit.setDate(Utils.formatDate(mvt.getDatePeremption().getTime(),
                                                   EclipseConstants.PATTERN_SIMPLE));
            }

            // les autres valeurs
            jrProduit.setDescriptif(mvt.getProduit().getNom());
            jrProduit.setNumLot(mvt.getNumLot());
            if (mvt.getNumTraitement() != null)
            {
                jrProduit.setNumTraitement(mvt.getNumTraitement());
            }
            jrProduit.setQuantite(mvt.getQuantite().toString());

            beanProduits.add(jrProduit);
        }

        return beanProduits;
    }
}

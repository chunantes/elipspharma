package fr.pharma.eclipse.jasper.engine.filler.impl.accusereception.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import fr.pharma.eclipse.domain.jasper.model.accusereception.JRBeanTraitement;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Helper en charge de transformer les traitements en JRBean correspondants.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TraitementFillerHelper implements Serializable {
    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    public Collection<JRBeanTraitement> transform(final List<? extends MvtStock> mvts) {
        final Collection<JRBeanTraitement> beanProduits = new ArrayList<JRBeanTraitement>();

        for (final MvtStock mvt : mvts) {

            final JRBeanTraitement jrProduit = new JRBeanTraitement();

            // si la date de péremption est setté on l'utilise.
            if (mvt.getDatePeremption() != null) {
                jrProduit.setDatePeremption(Utils.formatDate(mvt.getDatePeremption().getTime(), EclipseConstants.PATTERN_SIMPLE));
            }

            // les autres valeurs
            jrProduit.setDescriptif(mvt.getProduit().getNom());
            jrProduit.setNumLot(mvt.getNumLot());
            if (mvt.getNumTraitement() != null) {
                jrProduit.setNumTraitement(mvt.getNumTraitement());
            }
            jrProduit.setQuantite(mvt.getQuantite().toString());

            beanProduits.add(jrProduit);
        }

        return beanProduits;
    }
}

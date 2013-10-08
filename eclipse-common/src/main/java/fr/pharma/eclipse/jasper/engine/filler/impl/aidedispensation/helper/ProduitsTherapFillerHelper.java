package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation.helper;

import org.springframework.util.StringUtils;

import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanProduit;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.ProduitTherapeutique;

/**
 * Helper pour la création des beans de produits thérapeutiques.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ProduitsTherapFillerHelper extends AbstractProduitsFillerHelper {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8808523193778446337L;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fill(final Produit source,
                        final JRBeanProduit dest) {
        final ProduitTherapeutique pduitTherapeutique = (ProduitTherapeutique) source;
        final String classeTherapeutique = pduitTherapeutique.getClasseTherapeutique();
        if (StringUtils.hasText(classeTherapeutique)) {
            dest.setClasseTherapeuthique(classeTherapeutique);
        }
    }

}

package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation.helper;

import org.springframework.util.StringUtils;

import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanProduit;
import fr.pharma.eclipse.domain.model.produit.DispositifMedical;
import fr.pharma.eclipse.domain.model.produit.Produit;

/**
 * Helper pour la création des beans de dispositifs médicaux.
 
 * @version $Revision$ $Date$
 */
public class DispMedicalFillerHelper
    extends AbstractProduitsFillerHelper
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8808523193778446337L;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fill(final Produit source,
                        final JRBeanProduit dest)
    {
        final DispositifMedical dispMedical = (DispositifMedical) source;
        final String classeTherapeutique = dispMedical.getClasseTherapeutique();
        if (StringUtils.hasText(classeTherapeutique))
        {
            dest.setClasseTherapeuthique(classeTherapeutique);
        }
    }

}

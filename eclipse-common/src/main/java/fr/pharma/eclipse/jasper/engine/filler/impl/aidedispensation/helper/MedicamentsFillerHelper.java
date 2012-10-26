package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation.helper;

import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanProduit;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;

/**
 * Helper pour la création des beans de médicaments.
 
 * @version $Revision$ $Date$
 */
public class MedicamentsFillerHelper
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
        final Medicament medicament = (Medicament) source;
        dest.setDci(medicament.getDci());
        dest.setClasseTherapeuthique(medicament.getClasseTherapeutique());
    }

}

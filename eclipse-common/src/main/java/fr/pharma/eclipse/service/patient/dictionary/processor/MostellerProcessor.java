package fr.pharma.eclipse.service.patient.dictionary.processor;

import fr.pharma.eclipse.service.patient.dictionary.SurfaceCorporelleProcessor;

/**
 * Processor en charge de calculer la surface corporelle avec la formule de
 * Mosteller.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class MostellerProcessor implements SurfaceCorporelleProcessor {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6715723884699096066L;

    /**
     * Constante division formule.
     */
    private static final int DIVISEUR_FORMULE = 3600;

    /**
     * {@inheritDoc}
     */
    @Override
    public double process(final double taille,
                          final double poids) {
        return Math.sqrt((taille * poids) / MostellerProcessor.DIVISEUR_FORMULE);
    }
}

package fr.pharma.eclipse.service.patient.dictionary.processor;

import fr.pharma.eclipse.service.patient.dictionary.SurfaceCorporelleProcessor;

/**
 * Processor en charge de calculer la surface corporelle avec la formule de
 * Dubois.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DuboisProcessor implements SurfaceCorporelleProcessor {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5928594885018058063L;

    /**
     * Base de la formule.
     */
    private static final double FORMULE_BASE = 0.007184;

    /**
     * Puissance pour la taille dans la formule.
     */
    private static final double FORMULE_POW_TAILLE = 0.725;

    /**
     * Puissance pour le poids dans la formule.
     */
    private static final double FORMULE_POW_POIDS = 0.425;

    /**
     * {@inheritDoc}
     */
    @Override
    public double process(final double taille,
                          final double poids) {
        return DuboisProcessor.FORMULE_BASE * Math.pow(taille, DuboisProcessor.FORMULE_POW_TAILLE) * Math.pow(poids, DuboisProcessor.FORMULE_POW_POIDS);
    }
}

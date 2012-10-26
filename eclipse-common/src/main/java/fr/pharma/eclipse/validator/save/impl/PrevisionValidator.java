package fr.pharma.eclipse.validator.save.impl;

import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.exception.ValidationException;

/**
 * Validateur des données de prévisions.
 
 * @version $Revision$ $Date$
 */
public class PrevisionValidator
{

    /**
     * Méthode en charge de valider les données à utiliser pour le calcul des couts prévisionnel.
     * @param prevision DOnnées prévisionnelles.
     */
    public void validate(final DonneesPrevision prevision)
    {
        if (prevision
                .getDetailSurcout()
                .getEssai()
                .getDetailDonneesPharma()
                .getInfosGenerales()
                .getNbPatientsPrevus() == null)
        {
            throw new ValidationException("surcout.prevision.nbPatients",
                                          new String[]
                                          {"notNull" });
        }
        if (prevision.getNbAnnees() == null)
        {
            throw new ValidationException("surcout.prevision.nbAnnees",
                                          new String[]
                                          {"notNull" });
        }
        if (prevision.getNbDestructions() == null)
        {
            throw new ValidationException("surcout.prevision.nbDestructions",
                                          new String[]
                                          {"notNull" });

        }

        if (prevision.getNbReetiquetages() == null)
        {
            throw new ValidationException("surcout.prevision.nbReetiquetages",
                                          new String[]
                                          {"notNull" });

        }

        if (prevision.getNbVisiteMonitoring() == null)
        {
            throw new ValidationException("surcout.prevision.nbVisitesMonitoring",
                                          new String[]
                                          {"notNull" });
        }

        if (prevision.getNbPrescriptions() == null)
        {
            throw new ValidationException("surcout.prevision.nbPrescriptions",
                                          new String[]
                                          {"notNull" });
        }

        if (prevision.getNbDispensationsRenouvellement() == null)
        {
            throw new ValidationException("surcout.prevision.nbDispensationsRenouvelles",
                                          new String[]
                                          {"notNull" });
        }
        if (prevision.getNbDispensations() == null)
        {
            throw new ValidationException("surcout.prevision.nbDispensationsRenouvelles",
                                          new String[]
                                          {"notNull" });
        }

        if (prevision.getNbApprovisionnements() == null)
        {
            throw new ValidationException("surcout.prevision.nbApprovisionnements",
                                          new String[]
                                          {"notNull" });
        }

        if (prevision.getNbAudits() == null)
        {
            throw new ValidationException("surcout.prevision.nbAudits",
                                          new String[]
                                          {"notNull" });
        }
        if (prevision.getNbPreparationsNonSteriles() == null)
        {
            throw new ValidationException("surcout.prevision.nbPreparationsNonSteriles",
                                          new String[]
                                          {"notNull" });
        }
        if (prevision.getNbPreparationsSteriles() == null)
        {
            throw new ValidationException("surcout.prevision.nbPreparationsSteriles",
                                          new String[]
                                          {"notNull" });
        }
    }
}

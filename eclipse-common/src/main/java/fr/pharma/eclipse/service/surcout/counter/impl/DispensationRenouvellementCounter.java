package fr.pharma.eclipse.service.surcout.counter.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.dispensation.DispensationSearchCriteria;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.service.dispensation.DispensationService;
import fr.pharma.eclipse.service.surcout.counter.ActeCounter;

/**
 * Classe en charge de compter le nombre de dispensations pour un essai ou un patient dans un
 * essai.
 
 * @version $Revision$ $Date$
 */
public class DispensationRenouvellementCounter
    implements ActeCounter, Serializable
{
    /**
     * Serial UID.
     */
    private static final long serialVersionUID = 8055001407897228088L;

    /**
     * Service dispensation.
     */
    @Resource(name = "dispensationService")
    private DispensationService dispensationService;

    /**
     * Méthode en charge de compter le nombre de dispensation pour l'essai (ou pour le patient si
     * le patient n'est pas null) dans l'intervalle en paramètre.
     * @param essai L'essai.
     * @param patient Le patient (si null alors le calcul se fait pour l'essai).
     * @param dateDebut Date Début de l'intervalle.
     * @param dateFin Date de fin de l'intervalle.
     * @return Le nombre de dispensations.
     */
    public int process(final Essai essai,
                       final Patient patient,
                       final Calendar dateDebut,
                       final Calendar dateFin)
    {

        // On ne conserve que les dispensations liés à des prescription qui ont été dispensés plus
        // de 1 fois. On ne conserve que les dispensations > 1
        final DispensationSearchCriteria criteria = new DispensationSearchCriteria();
        criteria.setEssai(essai);
        criteria.setDispense(true);
        criteria.setPatient(patient);
        criteria.setDateDebut(dateDebut);
        criteria.setDateFin(dateFin);

        final Map<Long, Integer> disp = new HashMap<Long, Integer>();

        final List<Dispensation> dispensations = this.dispensationService.getAll(criteria);

        // boucle permettant de filtrer les dispensations à conserver.
        for (final Dispensation d : dispensations)
        {
            if (d.getPrescription().getDispensations().size() > 1)
            {
                // première dispensation associée à la prescription, on ne la compte pas.
                if (!disp.containsKey(d.getPrescription().getId()))
                {
                    disp.put(d.getPrescription().getId(),
                             new Integer(0));
                }
                // pour les autres on compte.
                else
                {
                    disp.put(d.getPrescription().getId(),
                             new Integer(disp.get(d.getPrescription().getId()) + 1));
                }
            }
        }

        int result = 0;
        for (final Integer i : disp.values())
        {
            result += i.intValue();
        }

        return result;
    }
    /**
     * Méthode en charge de compter le nombre de dispensation pour l'essai (ou pour le patient si
     * le patient n'est pas null) dans l'intervalle en paramètre.
     * @param essai L'essai.
     * @param patient Le patient (si null alors le calcul se fait pour l'essai).
     * @param prevision Donnees prévisionnelles.
     * @return Le nombre de dispensations.
     */
    public int process(final Essai essai,
                       final Patient patient,
                       final DonneesPrevision prevision)
    {
        return prevision.getNbDispensationsRenouvellement();
    }

    /**
     * Setter pour dispensationService.
     * @param dispensationService le dispensationService à écrire.
     */
    public void setDispensationService(final DispensationService dispensationService)
    {
        this.dispensationService = dispensationService;
    }

}

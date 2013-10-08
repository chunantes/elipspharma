package fr.pharma.eclipse.service.surcout.counter.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.dispensation.DispensationSearchCriteria;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.service.dispensation.DispensationService;
import fr.pharma.eclipse.service.surcout.counter.ActeCounter;

/**
 * Classe en charge de compter le nombre de dispensations pour un essai ou un
 * patient dans un essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationCounter implements ActeCounter, Serializable {
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
     * Méthode en charge de compter le nombre de dispensation pour l'essai (ou
     * pour le patient si le patient n'est pas null) dans l'intervalle en
     * paramètre.
     * @param essai L'essai.
     * @param patient Le patient (si null alors le calcul se fait pour l'essai).
     * @param dateDebut Date Début de l'intervalle.
     * @param dateFin Date de fin de l'intervalle.
     * @return Le nombre de dispensations.
     */
    @Override
    public int process(final Essai essai,
                       final Patient patient,
                       final Calendar dateDebut,
                       final Calendar dateFin) {
        final DispensationSearchCriteria criteria = new DispensationSearchCriteria();
        criteria.setEssai(essai);
        criteria.setDispense(true);
        criteria.setPatient(patient);
        criteria.setDateDebut(dateDebut);
        criteria.setDateFin(dateFin);

        final List<Long> prescriptionsTraites = new ArrayList<Long>();

        final List<Dispensation> dispensations = this.dispensationService.getAll(criteria);

        for (final Dispensation d : dispensations) {
            if (!prescriptionsTraites.contains(d.getPrescription().getId())) {
                prescriptionsTraites.add(d.getPrescription().getId());
            }
        }

        return prescriptionsTraites.size();
    }

    /**
     * Méthode en charge de compter le nombre de dispensation pour l'essai (ou
     * pour le patient si le patient n'est pas null) dans l'intervalle en
     * paramètre.
     * @param essai L'essai.
     * @param patient Le patient (si null alors le calcul se fait pour l'essai).
     * @param prevision Donnees prévisionnelles.
     * @return Le nombre de dispensations.
     */
    @Override
    public int process(final Essai essai,
                       final Patient patient,
                       final DonneesPrevision prevision) {
        return prevision.getNbDispensations();
    }

    /**
     * Setter pour dispensationService.
     * @param dispensationService le dispensationService à écrire.
     */
    public void setDispensationService(final DispensationService dispensationService) {
        this.dispensationService = dispensationService;
    }

}

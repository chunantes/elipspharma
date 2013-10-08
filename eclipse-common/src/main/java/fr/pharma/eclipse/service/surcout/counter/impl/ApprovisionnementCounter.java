package fr.pharma.eclipse.service.surcout.counter.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.stock.MvtStockService;
import fr.pharma.eclipse.service.surcout.counter.ActeCounter;

/**
 * Classe en charge de compter le nombre d'approvisionnements pour un essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ApprovisionnementCounter implements ActeCounter, Serializable {

    /**
     * Serial UID.
     */
    private static final long serialVersionUID = -3426573822073882671L;

    /**
     * Service mouvement stocks.
     */
    @Resource(name = "mouvementStockService")
    private MvtStockService<MvtStock> mouvementStockService;

    /**
     * Méthode en charge de compter le nombre d'approvisionnements pour l'essai
     * dans l'intervalle en paramètre.
     * @param essai L'essai.
     * @param patient Le patient (si null alors le calcul se fait pour l'essai).
     * @param dateDebut Date Début de l'intervalle.
     * @param dateFin Date de fin de l'intervalle.
     * @return Le nombre de destructions.
     */
    @Override
    public int process(final Essai essai,
                       final Patient patient,
                       final Calendar dateDebut,
                       final Calendar dateFin) {
        // on vérifie que la règle est bien de perimètre "par essai"
        this.checkPatient(patient);

        // récupération des mouvements de type approvisionnement de l'essai pour
        // la période donnée
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        criteria.setTypeMouvement(TypeMvtStock.APPROVISIONNEMENT);
        criteria.setEssai(essai);
        criteria.setDateDebut(dateDebut);
        criteria.setDateFin(dateFin);

        final List<MvtStock> listeMouvements = this.mouvementStockService.getAll(criteria);

        // le même jour pour une même étude, ne compter qu'un seul mouvement de
        // stock
        final Map<String, MvtStock> listeMouvementsAsMap = this.filtreApprovisionnements(listeMouvements);

        return listeMouvementsAsMap.size();
    }

    /**
     * Méthode en charge de compter le nombre d'approvisionnements prévisionnels
     * pour l'essai.
     * @param essai L'essai.
     * @param patient Le patient (si null alors le calcul se fait pour l'essai).
     * @param prevision Les données prévisionnelles.
     * @return Le nombre de destructions.
     */
    @Override
    public int process(final Essai essai,
                       final Patient patient,
                       final DonneesPrevision prevision) {
        return prevision.getNbApprovisionnements();
    }

    /**
     * le même jour pour une même étude, ne compter qu'un seul mouvement de
     * stock
     * @param listeMouvements
     */
    private Map<String, MvtStock> filtreApprovisionnements(final List<MvtStock> listeMouvements) {
        final Map<String, MvtStock> listeMouvementsAsMap = new HashMap<String, MvtStock>();
        for (final MvtStock mvtStock : listeMouvements) {
            final String dateMvtAsString =
                "" + mvtStock.getDateCreation().get(Calendar.YEAR) + mvtStock.getDateCreation().get(Calendar.MONTH) + mvtStock.getDateCreation().get(Calendar.DAY_OF_MONTH);
            listeMouvementsAsMap.put(mvtStock.getEssai().getId() + "-" + dateMvtAsString, mvtStock);
        }
        return listeMouvementsAsMap;
    }

    /**
     * Méthode en charge de vérifier que la règle est bien de perimètre
     * "par essai".
     * @param patient
     */
    protected void checkPatient(final Patient patient) {
        if (patient != null) {
            throw new ValidationException("surcout.support.patient", new String[]{"error" });
        }
    }

    /**
     * Setter pour mouvementStockService.
     * @param mouvementStockService Le mouvementStockService à écrire.
     */
    public void setMouvementStockService(final MvtStockService<MvtStock> mouvementStockService) {
        this.mouvementStockService = mouvementStockService;
    }
}

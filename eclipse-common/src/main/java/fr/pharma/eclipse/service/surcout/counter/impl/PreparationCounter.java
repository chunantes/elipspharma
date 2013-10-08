package fr.pharma.eclipse.service.surcout.counter.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.stock.DispensationProduitSearchCriteria;
import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.criteria.stock.PreparationEntreeSearchCriteria;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stock.PreparationEntree;
import fr.pharma.eclipse.service.stock.MvtStockService;
import fr.pharma.eclipse.service.surcout.counter.ActeCounter;

/**
 * Classe en charge de compter le nombre de mouvements de type
 * "preparation entree" pour un essai ou un patient dans un essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public abstract class PreparationCounter implements ActeCounter, Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 9132676764497303892L;
    /**
     * Service mouvement stocks.
     */
    @Resource(name = "mouvementStockService")
    private MvtStockService<MvtStock> mouvementStockService;

    /**
     * Service dispensation.
     */
    @Resource(name = "dispensationProduitService")
    private MvtStockService<DispensationProduit> dispensationProduitService;

    /**
     * Service des preparation entree.
     */
    @Resource(name = "preparationEntreeStockService")
    private MvtStockService<PreparationEntree> preparationEntreeStockService;

    /**
     * Méthode en charge de compter le nombre de preparations pour l'essai (ou
     * pour le patient si le patient n'est pas null) dans l'intervalle en
     * paramètre.
     * @param essai L'essai.
     * @param patient Le patient (si null alors le calcul se fait pour l'essai).
     * @param dateDebut Date Début de l'intervalle.
     * @param dateFin Date de fin de l'intervalle.
     * @param sterile Indique sit la preparation est sterile ou non.
     * @return Le nombre de destructions.
     */
    public int process(final Essai essai,
                       final Patient patient,
                       final Calendar dateDebut,
                       final Calendar dateFin,
                       final boolean sterile) {
        if (patient == null) {
            // récupération des mouvements de type preparation de l'essai pour
            // la période donnée
            final List<MvtStock> listeMouvements = this.listePreparationsParEssai(essai, dateDebut, dateFin, sterile);

            return listeMouvements.size();
        } else {
            // récupération des mvts de type dispensations
            final List<DispensationProduit> listeDispensationProduits = this.listeDispensationProduits(dateDebut, dateFin);

            // on compte le nombre de mvts de type "dispensation" liés au
            // patient
            this.filtreDispensationsPatient(patient, listeDispensationProduits);

            // recuperation des numeros de lots
            final ArrayList<String> numLots = this.getNumLots(listeDispensationProduits);

            if (numLots.size() >= 1) {
                // recuperation des mvts de type "preparation entree" liés aux
                // mvts de type
                // "dispensations" récupérés.
                final List<PreparationEntree> listePreparationsEntrees = this.listePreparationsEntrees(numLots, sterile);

                return listePreparationsEntrees.size();
            } else {
                return 0;
            }
        }

    }

    /**
     * Recuperation des mouvements de type "preparations entree" liés aux
     * numeros de lots.
     * @param numLots
     * @param sterile
     */
    private List<PreparationEntree> listePreparationsEntrees(final ArrayList<String> numLots,
                                                             final boolean sterile) {
        final PreparationEntreeSearchCriteria criteria = new PreparationEntreeSearchCriteria();
        criteria.setTypeMouvement(TypeMvtStock.PREPARATION_ENTREE);
        criteria.setSterile(sterile);
        criteria.setNumLots(numLots);

        return this.preparationEntreeStockService.getAll(criteria);
    }

    /**
     * @param dateDebut
     * @param dateFin
     */
    private List<DispensationProduit> listeDispensationProduits(final Calendar dateDebut,
                                                                final Calendar dateFin) {
        final DispensationProduitSearchCriteria criteria = new DispensationProduitSearchCriteria();
        criteria.setTypeMouvement(TypeMvtStock.DISPENSATION);
        criteria.setDateDebut(dateDebut);
        criteria.setDateFin(dateFin);

        // on recupere les mvts dispensations liées à des mvts de type
        // preparations
        // criteria.setJointureEntreMvtDispensationEtPreparationEntree(true);

        return this.dispensationProduitService.getAll(criteria);
    }

    /**
     * Methode permetant de garder seulement les mouvements "dispensation" liés
     * au patient passé en paramètre
     * @param patient
     * @param listeDispensationProduits
     */
    private void filtreDispensationsPatient(final Patient patient,
                                            final List<DispensationProduit> listeDispensationProduits) {
        for (int i = 0; i < listeDispensationProduits.size(); i++) {
            final DispensationProduit dispensationProduit = listeDispensationProduits.get(i);
            if (dispensationProduit.getDispensation().getPrescription().getInclusion().getPatient().getId().intValue() != patient.getId().intValue()) {
                listeDispensationProduits.remove(i);
                i--;
            }
        }
    }

    /**
     * Recupere les numeros de lots d'une liste de mouvements de type
     * dispensation.
     * @param listeDispensationProduits
     * @return
     */
    private ArrayList<String> getNumLots(final List<DispensationProduit> listeDispensationProduits) {
        final Set<String> numLots = new HashSet<String>();

        for (final DispensationProduit dispensationProduit : listeDispensationProduits) {
            numLots.add(dispensationProduit.getNumLot());
        }
        return new ArrayList<String>(numLots);
    }

    /**
     * @param essai
     * @param dateDebut
     * @param dateFin
     * @param sterile
     * @return
     */
    private List<MvtStock> listePreparationsParEssai(final Essai essai,
                                                     final Calendar dateDebut,
                                                     final Calendar dateFin,
                                                     final boolean sterile) {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        criteria.setTypeMouvement(TypeMvtStock.PREPARATION_ENTREE);
        criteria.setEssai(essai);
        criteria.setDateDebut(dateDebut);
        criteria.setDateFin(dateFin);
        criteria.setSterile(sterile);

        final List<MvtStock> listeMouvements = this.mouvementStockService.getAll(criteria);
        return listeMouvements;
    }

    /**
     * Setter pour mouvementStockService.
     * @param mouvementStockService Le mouvementStockService à écrire.
     */
    public void setMouvementStockService(final MvtStockService<MvtStock> mouvementStockService) {
        this.mouvementStockService = mouvementStockService;
    }
    /**
     * Setter pour dispensationProduitService.
     * @param dispensationProduitService Le dispensationProduitService à écrire.
     */
    public void setDispensationProduitService(final MvtStockService<DispensationProduit> dispensationProduitService) {
        this.dispensationProduitService = dispensationProduitService;
    }

    /**
     * Setter pour preparationEntreeStockService.
     * @param preparationEntreeStockService Le preparationEntreeStockService à
     * écrire.
     */
    public void setPreparationEntreeStockService(final MvtStockService<PreparationEntree> preparationEntreeStockService) {
        this.preparationEntreeStockService = preparationEntreeStockService;
    }

}

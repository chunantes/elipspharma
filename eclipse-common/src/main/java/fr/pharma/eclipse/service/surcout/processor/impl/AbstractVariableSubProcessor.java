package fr.pharma.eclipse.service.surcout.processor.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Map;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.patient.PatientSearchCriteria;
import fr.pharma.eclipse.domain.enums.surcout.Acte;
import fr.pharma.eclipse.domain.enums.surcout.PerimetreCout;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Resultat;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;
import fr.pharma.eclipse.service.patient.PatientService;
import fr.pharma.eclipse.service.surcout.counter.ActeCounter;
import fr.pharma.eclipse.service.surcout.processor.VariableSubProcessor;

/**
 * Classe abstraite de subprocessor de cout variables. TODO REFACTOR !
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public abstract class AbstractVariableSubProcessor implements VariableSubProcessor, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -5361211095149276740L;

    /**
     * Map de compteur d'actes.
     */
    private Map<Acte, ActeCounter> acteCounters;

    /**
     * Service patient.
     */
    @Resource(name = "patientService")
    private PatientService patientService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Resultat process(final Item item,
                            final Regle regle,
                            final Essai essai,
                            final Calendar dateDebut,
                            final Calendar dateFin) {
        final PerimetreCout perimetre = this.findPerimetre(item, regle);
        if (null != perimetre) {
            if (perimetre.equals(PerimetreCout.PATIENT)) {
                return this.processByPatient(item, essai, regle, dateDebut, dateFin);
            }

            // calcul par essai.
            else {
                final int nb = this.acteCounters.get(item.getActe()).process(essai, null, dateDebut, dateFin);
                return this.processSubProcessor(regle, nb);
            }
        }
        return new Resultat();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Resultat process(final Item item,
                            final Regle regle,
                            final Essai essai,
                            final DonneesPrevision prevision) {
        final PerimetreCout perimetre = this.findPerimetre(item, regle);
        if (null != perimetre) {
            if (perimetre.equals(PerimetreCout.PATIENT)) {
                return this.processByPatient(item, essai, regle, prevision);
            }

            // calcul par essai.
            else {
                final int nb = this.acteCounters.get(item.getActe()).process(essai, null, prevision);
                return this.processSubProcessor(regle, nb);
            }
        }
        return new Resultat();
    }

    /**
     * Methode en charge de préparer le calcul en vérifiant qu'un acte est bien
     * affecté.
     * @param La regle.
     * @return Le perimetre si la regle est vérifiée.
     */
    private PerimetreCout findPerimetre(final Item item,
                                        final Regle regle) {
        // si aucun acte n'est attribué, on ne traite pas.
        if ((item.getActe() != null) && this.acteCounters.containsKey(item.getActe())) {
            // on vérifie que l'acte est présent dans le dictionnaire.
            // this.checkActe(regle.getItem().getActe());

            return regle.getPerimetre();

        }
        return null;
    }

    /**
     * Méthode calculant le total du montant induit par la règle en paramètre
     * pour chaque patient.
     * @param essai L'essai.
     * @param regle La règle.
     * @param dateDebut La date de début de l'intervalle.
     * @param dateFin La date de fin de l'intervalle.
     * @return Le montant.
     */
    private Resultat processByPatient(final Item item,
                                      final Essai essai,
                                      final Regle regle,
                                      final Calendar dateDebut,
                                      final Calendar dateFin) {
        final Resultat value = new Resultat();
        final PatientSearchCriteria criteria = new PatientSearchCriteria();
        criteria.setEssai(essai);

        for (final Patient patient : this.patientService.getAll(criteria)) {
            final int nb = this.acteCounters.get(item.getActe()).process(essai, patient, dateDebut, dateFin);
            final Resultat r = this.processSubProcessor(regle, nb);
            value.setMontant(value.getMontant().add(r.getMontant()));
            value.setNbActes(value.getNbActes() + r.getNbActes());

        }
        return value;
    }

    /**
     * Méthode calculant le total du montant induit par la règle en paramètre
     * pour chaque patient.
     * @param essai L'essai.
     * @param regle La règle.
     * @param dateDebut La date de début de l'intervalle.
     * @param dateFin La date de fin de l'intervalle.
     * @return Le montant.
     */
    private Resultat processByPatient(final Item item,
                                      final Essai essai,
                                      final Regle regle,
                                      final DonneesPrevision prevision) {
        final Resultat value = new Resultat();

        final int nb = this.acteCounters.get(item.getActe()).process(essai, null, prevision);
        final Resultat r = this.processSubProcessor(regle, nb);
        value.setMontant(value.getMontant().add(r.getMontant()).multiply(new BigDecimal(essai.getDetailDonneesPharma().getInfosGenerales().getNbPatientsPrevus())));
        value.setNbActes(r.getNbActes());

        return value;
    }

    /**
     * Méthode surchargée par les sous classes en charge d'appliquer
     * l'algorithme spécifique au forfait et à l'unité.
     * @param regle La regle.
     * @param nb Le nombre d'actes.
     * @return Le montant calculée d'après la règle.
     */
    protected abstract Resultat processSubProcessor(Regle regle,
                                                    int nb);

    /**
     * Setter pour acteCounters.
     * @param acteCounters le acteCounters à écrire.
     */
    public void setActeCounters(final Map<Acte, ActeCounter> acteCounters) {
        this.acteCounters = acteCounters;
    }

    /**
     * Setter pour patientService.
     * @param patientService le patientService à écrire.
     */
    public void setPatientService(final PatientService patientService) {
        this.patientService = patientService;
    }
    /**
     * Getter pour acteCounters.
     * @return Le acteCounters
     */
    public Map<Acte, ActeCounter> getActeCounters() {
        return this.acteCounters;
    }

}

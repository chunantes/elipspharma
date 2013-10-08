package fr.pharma.eclipse.service.surcout.processor.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.enums.surcout.PerimetreCout;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Resultat;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.service.evenement.EvenementService;
import fr.pharma.eclipse.service.surcout.processor.VariableSubProcessor;

/**
 * Classe implémentant la méthode contenant l'algorithme de calcul d'un montant
 * au forfait pour un surcout.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class UniteAnSubProcessor extends UniteSubProcessor implements VariableSubProcessor {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -1370667429767092788L;

    /**
     * Service evenement.
     */
    @Resource(name = "evenementService")
    private EvenementService evenementService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Resultat process(final Item item,
                            final Regle regle,
                            final Essai essai,
                            final Calendar dateDebut,
                            final Calendar dateFin) {
        final Evenement visite = this.evenementService.getVisiteMonitoring(essai);

        if (regle.getPerimetre().equals(PerimetreCout.PATIENT)) {

            throw new ValidationException("surcout.support.patient", new String[]{"error" });
        }

        final Resultat result = new Resultat();
        result.setMontant(new BigDecimal(0));
        result.setNbActes(0);

        // on ne le traite que si la visite de mise en place a été faite.
        if ((visite != null) && (this.getActeCounters().get(item.getActe()) != null)) {
            Calendar newDateDebut = null;
            Calendar newDateFin = null;
            int i = 0;
            do {
                newDateDebut = this.findDateDebut(dateDebut, visite, i);
                newDateFin = this.findDateFin(newDateDebut, visite, dateFin, i);

                // Comptage sur l'année.

                final int nb = this.getActeCounters().get(item.getActe()).process(essai, null, newDateDebut, newDateFin);
                if (nb > 0) {
                    final Resultat r = this.processSubProcessor(regle, nb);
                    result.setMontant(result.getMontant().add(r.getMontant()));
                    result.setNbActes(result.getNbActes() + r.getNbActes());
                }
                i++;

            } while (newDateFin.before(dateFin));
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Resultat process(final Item item,
                            final Regle regle,
                            final Essai essai,
                            final DonneesPrevision prevision) {
        final Resultat result = new Resultat();
        result.setMontant(new BigDecimal(0));
        result.setNbActes(0);

        if (this.getActeCounters().get(item.getActe()) != null) {
            for (int i = 0; i < prevision.getNbAnnees(); i++) {

                final int nb = this.getActeCounters().get(item.getActe()).process(essai, null, prevision);
                final Resultat r = this.processSubProcessor(regle, nb);
                result.setMontant(result.getMontant().add(r.getMontant()));
                result.setNbActes(result.getNbActes() + r.getNbActes());
            }
        }
        return result;
    }

    /**
     * Méthode en charge de déterminer la date de début pour le calcul ( 1ere
     * année).
     * @param dateDebut Date début saisie.
     * @param visite VIsite de MEP.
     * @param i Compteur d'années.
     * @return la date de début.
     */
    private Calendar findDateDebut(final Calendar dateDebut,
                                   final Evenement visite,
                                   final int i) {
        final Calendar result = new GregorianCalendar();
        result.setTime(visite.getDateDebut().getTime());

        // SI première année.
        if (i == 0) {
            // si la date de la visite est après la date de début de
            // l'intervalle
            if (dateDebut.after(visite.getDateDebut())) {
                result.setTime(dateDebut.getTime());
            }
        }
        // sinon on incrémente d'un an la date de début.
        else {
            result.add(Calendar.YEAR, i);
        }
        return result;

    }

    /**
     * Méthode en charge de déterminer la date de début pour le calcul ( 1ere
     * année).
     * @param dateDebut Date début saisie.
     * @param visite VIsite de MEP.
     * @param i Compteur d'années.
     * @return la date de début.
     */
    private Calendar findDateFin(final Calendar dateDebut,
                                 final Evenement visite,
                                 final Calendar dateFin,
                                 final int i) {

        final Calendar result = new GregorianCalendar();
        result.setTime(dateDebut.getTime());
        result.add(Calendar.YEAR, 1);
        result.add(Calendar.DAY_OF_YEAR, -1);
        if (i == 0) {
            if (!dateDebut.equals(visite.getDateDebut())) {
                result.setTime(visite.getDateDebut().getTime());
                result.add(Calendar.YEAR, 1);
                result.add(Calendar.DAY_OF_YEAR, -1);
            }
        }
        if (result.before(dateFin)) {
            return result;
        } else {
            return dateFin;
        }

    }

}

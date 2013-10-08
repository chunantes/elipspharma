package fr.pharma.eclipse.service.surcout.processor.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.domain.enums.surcout.Acte;
import fr.pharma.eclipse.domain.enums.surcout.PerimetreCout;
import fr.pharma.eclipse.domain.enums.surcout.TypeCout;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Resultat;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.service.surcout.SurcoutFacade;
import fr.pharma.eclipse.service.surcout.checker.SurcoutChecker;
import fr.pharma.eclipse.service.surcout.processor.SurcoutProcessor;

/**
 * Processor en charge de calculer les frais fixes.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class FixeProcessor implements SurcoutProcessor, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 2037339743235723244L;

    /**
     * Facade contenant les services nécessaires aux processors.
     */
    @Resource(name = "surcoutFacade")
    private SurcoutFacade facade;

    /**
     * Checkers.
     */
    private Map<Acte, SurcoutChecker> checkers;

    /**
     * {@inheritDoc}
     */
    @Override
    public Resultat process(final Item item,
                            final Essai essai,
                            final Calendar dateDebut,
                            final Calendar dateFin) {

        return this.process(item, essai, this.facade.inPremiereAnnee(essai, dateDebut, dateFin), this.facade.countNbAnnees(essai, dateDebut, dateFin),
                            this.facade.countNbPatients(essai, dateDebut, dateFin, true));

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Resultat process(final Item item,
                            final Essai essai,
                            final DonneesPrevision prevision) {
        return this.process(item, essai, true, prevision.getNbAnnees() - 1, essai.getDetailDonneesPharma().getInfosGenerales().getNbPatientsPrevus());

    }
    /**
     * Méthode en charge d'appliquer la règle fixe pour les éléemnts en
     * paramètre.
     * @param item L'item.
     * @param essai L'essai.
     * @param premiereAnnee Imputé la première année.
     * @param nbAnneesSuivantes Nombre d'années suivantes.
     * @param nbPatients Nombre de patients.
     * @return Le montant.
     */
    protected Resultat process(final Item item,
                               final Essai essai,
                               final boolean premiereAnnee,
                               final int nbAnneesSuivantes,
                               final Integer nbPatient) {
        final Resultat total = new Resultat();

        total.setMontant(new BigDecimal(0));

        if (!this.checkers.containsKey(item.getActe()) || (this.checkers.containsKey(item.getActe()) && this.checkers.get(item.getActe()).check(essai))) {
            @SuppressWarnings("unchecked")
            final Collection<Regle> regles = CollectionUtils.select(item.getRegles(), new GenericPredicate("type", TypeCout.FIXE));

            // pour chaque règle fixe.
            for (final Regle regle : regles) {
                BigDecimal value = new BigDecimal(0);

                // calcul le nombre d'année (excepté la première) * par le
                // montant.
                value = value.add(regle.getAnneesSuivantes().multiply(BigDecimal.valueOf(nbAnneesSuivantes)));

                // si la première année est applicable on ajoute le montant de
                // la première année.
                if (premiereAnnee) {
                    value = value.add(regle.getPremiereAnnee());
                }

                // si la règle est définie par patient alors on multiplie par le
                // nombre de
                // patients.
                if (regle.getPerimetre().equals(PerimetreCout.PATIENT)) {
                    value = value.multiply(new BigDecimal(nbPatient));
                }
                total.setMontant(total.getMontant().add(value));
            }
        }

        return total;
    }
    /**
     * Setter pour facade.
     * @param facade le facade à écrire.
     */
    public void setFacade(final SurcoutFacade facade) {
        this.facade = facade;
    }

    /**
     * Setter pour checkers.
     * @param checkers Le checkers à écrire.
     */
    public void setCheckers(final Map<Acte, SurcoutChecker> checkers) {
        this.checkers = checkers;
    }

}

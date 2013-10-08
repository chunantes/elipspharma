package fr.pharma.eclipse.service.surcout.processor.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.domain.enums.surcout.ModeCalcul;
import fr.pharma.eclipse.domain.enums.surcout.TypeCout;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Resultat;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.service.surcout.processor.SurcoutProcessor;
import fr.pharma.eclipse.service.surcout.processor.VariableSubProcessor;

/**
 * Processor en charge de calculer le cout d'un item contenant des regles
 * variables.<br>
 * <br>
 * Le structure du moteur de calcul est : VariableProcessor ->
 * VariableSubProcessor -> Counter.<br>
 * Les composants sont defini dans applicationContext-surcout.xml.<br>
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class VariableProcessor implements SurcoutProcessor, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -573668290869275358L;

    /**
     * Map contenant le subProcessor à appliquer en fonction du mode de calcul
     * de la règle.
     */
    private Map<ModeCalcul, VariableSubProcessor> subProcessors;

    /**
     * {@inheritDoc}
     */
    @Override
    public Resultat process(final Item item,
                            final Essai essai,
                            final Calendar dateDebut,
                            final Calendar dateFin) {
        final Resultat total = new Resultat();

        // Pour chaque règle on applique le subProcessor correspondant au mode
        // de calcul de la
        // règle.
        for (final Regle regle : this.getReglesCoutVariable(item)) {
            final Resultat returned = this.subProcessors.get(regle.getMode()).process(item, regle, essai, dateDebut, dateFin);
            total.setMontant(total.getMontant().add(returned.getMontant()));
            total.setNbActes(returned.getNbActes());
        }

        return total;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Resultat process(final Item item,
                            final Essai essai,
                            final DonneesPrevision prevision) {
        final Resultat total = new Resultat();

        // Pour chaque règle on applique le subProcessor correspondant au mode
        // de calcul de la
        // règle.
        for (final Regle regle : this.getReglesCoutVariable(item)) {
            final Resultat returned = this.subProcessors.get(regle.getMode()).process(item, regle, essai, prevision);
            total.setMontant(total.getMontant().add(returned.getMontant()));
            total.setNbActes(returned.getNbActes());
        }

        return total;
    }

    /**
     * Méthode en charge de filtrer les règles de l'item et de ne retourner que
     * les règles de cout variables.
     * @param item L'item.
     * @return Les regles au cout variables.
     */
    @SuppressWarnings("unchecked")
    private Collection<Regle> getReglesCoutVariable(final Item item) {

        return CollectionUtils.select(item.getRegles(), new GenericPredicate("type", TypeCout.VARIABLE));
    }

    /**
     * Setter pour subProcessors.
     * @param subProcessors le subProcessors à écrire.
     */
    public void setSubProcessors(final Map<ModeCalcul, VariableSubProcessor> subProcessors) {
        this.subProcessors = subProcessors;
    }

}

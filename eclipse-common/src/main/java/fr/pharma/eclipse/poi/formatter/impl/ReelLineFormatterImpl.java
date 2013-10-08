package fr.pharma.eclipse.poi.formatter.impl;

import java.io.Serializable;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.domain.enums.surcout.PerimetreCout;
import fr.pharma.eclipse.domain.enums.surcout.TypeCout;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Resultat;
import fr.pharma.eclipse.domain.model.surcout.regle.Regle;
import fr.pharma.eclipse.poi.formatter.ItemLineFormatter;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Implémentation du formatter d'item pour les couts reels.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ReelLineFormatterImpl implements ItemLineFormatter, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -8457186264767765278L;

    /**
     * {@inheritDoc}
     */
    @Override
    public String[] format(final Item item,
                           final Resultat resultat) {
        final String[] values = new String[8];
        values[0] = item.getTheme();
        values[1] = item.getCategorie();
        this.formatFraisFixes(item, values);

        this.formatFraisVariables(item, values, resultat);
        values[7] = resultat.getMontant().toString() + EclipseConstants.EURO;
        return values;
    }

    /**
     * Ajoute les valeurs dans le tableau de chaines pour les frais fixes.
     * @param item L'item.
     * @param values Les valeurs.
     */
    @SuppressWarnings("unchecked")
    private void formatFraisFixes(final Item item,
                                  final String[] values) {
        values[2] = "";
        values[3] = "";
        // on récupère toutes les règles de cout fixe.
        final Collection<Regle> regles = CollectionUtils.select(item.getRegles(), new GenericPredicate("type", TypeCout.FIXE));
        for (final Regle regle : regles) {
            values[2] += regle.getPremiereAnnee() + EclipseConstants.EURO;
            values[2] += "\n";
            values[3] += regle.getAnneesSuivantes() + EclipseConstants.EURO;;
            values[3] += "\n";
        }

    }

    /**
     * Ajoute les valeurs dans le tableau de chaines pour les frais variables.
     * @param item L'item.
     * @param values Les valeurs.
     */
    @SuppressWarnings("unchecked")
    private void formatFraisVariables(final Item item,
                                      final String[] values,
                                      final Resultat resultat) {
        values[4] = "";
        values[6] = "";
        // on récupère toutes les règles de cout fixe.
        final Collection<Regle> regles = CollectionUtils.select(item.getRegles(), new GenericPredicate("type", TypeCout.VARIABLE));

        final Collection<Regle> reglesPatients = CollectionUtils.select(regles, new GenericPredicate("perimetre", PerimetreCout.PATIENT));

        final Collection<Regle> reglesEssais = CollectionUtils.select(regles, new GenericPredicate("perimetre", PerimetreCout.ESSAI));
        for (final Regle regle : reglesPatients) {
            values[4] += regle.toString();
            values[4] += "\n";
            values[5] = resultat.getNbActes().toString();
        }
        for (final Regle regle : reglesEssais) {
            values[5] = resultat.getNbActes().toString();
            values[6] += regle.toString();
            values[6] += "\n";
        }

    }
}

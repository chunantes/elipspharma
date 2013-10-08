package fr.pharma.eclipse.service.indicateur.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.indicateur.IndicateurService;
import fr.pharma.eclipse.service.indicateur.builder.IndicateurBuilder;

/**
 * Implémentation de IndicateurService.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class IndicateurServiceImpl implements IndicateurService, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -8710757051860079922L;

    /**
     * Builders.
     */
    private List<IndicateurBuilder> builders;

    /**
     * {@inheritDoc}
     */
    @Override
    public SortedSet<Indicateur> generateIndicateur(final Pharmacie pharmacie,
                                                    final Calendar dateDebut,
                                                    final Calendar dateFin) {
        final SortedSet<Indicateur> results = new TreeSet<Indicateur>(new BeanWithNomComparator());

        // on traite tous les builder.
        for (final IndicateurBuilder builder : this.builders) {
            results.add(builder.build(pharmacie, dateDebut, dateFin));
        }

        return results;
    }

    /**
     * Setter pour builders.
     * @param builders Le builders à écrire.
     */
    public void setBuilders(final List<IndicateurBuilder> builders) {
        this.builders = builders;
    }

}

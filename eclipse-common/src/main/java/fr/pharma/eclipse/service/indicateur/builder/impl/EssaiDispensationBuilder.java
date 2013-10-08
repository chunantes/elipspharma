package fr.pharma.eclipse.service.indicateur.builder.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;

import fr.pharma.eclipse.comparator.common.EclipseListComparator;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.predicate.pharmacie.PharmacieEssaiPredicate;
import fr.pharma.eclipse.service.indicateur.builder.IndicateurBuilder;

/**
 * Builder en charge de construire l'indicateur correspondant aux essais avec au
 * moins une dispensation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiDispensationBuilder extends AbstractDispensationBuilder implements IndicateurBuilder, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 6865981658707550298L;

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * {@inheritDoc}
     */
    @Override
    public Indicateur build(final Pharmacie pharmacie,
                            final Calendar dateDebut,
                            final Calendar dateFin) {
        final List results = this.loadDispensations(pharmacie, dateDebut, dateFin, true);
        final SortedSet<Essai> essais = new TreeSet<Essai>(new EclipseListComparator());
        CollectionUtils.transform(results, new Transformer() {

            @Override
            public Object transform(final Object input) {
                return ((Dispensation) input).getEssai();
            }
        });
        essais.addAll(results);

        // On filtre les essais qui ne sont pas associés à la pharmacie.
        CollectionUtils.filter(essais, new PharmacieEssaiPredicate(pharmacie));

        return new Indicateur(this.libelle, new BigDecimal(essais.size()));
    }
    /**
     * Getter pour libelle.
     * @return Le libelle
     */
    public String getLibelle() {
        return this.libelle;
    }

    /**
     * Setter pour libelle.
     * @param libelle Le libelle à écrire.
     */
    public void setLibelle(final String libelle) {
        this.libelle = libelle;
    }

}

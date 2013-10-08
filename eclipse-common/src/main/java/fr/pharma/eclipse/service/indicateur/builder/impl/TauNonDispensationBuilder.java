package fr.pharma.eclipse.service.indicateur.builder.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.indicateur.builder.IndicateurBuilder;
import fr.pharma.eclipse.transformer.dispensation.LigneDispensationTransformer;

/**
 * Classe en charge de construire l'indicateur du tau de non dispensations.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TauNonDispensationBuilder extends AbstractDispensationBuilder implements IndicateurBuilder, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -1400912572793341523L;

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

        final List dispensations = this.loadDispensations(pharmacie, dateDebut, dateFin, false);
        final List total = this.loadDispensations(pharmacie, dateDebut, dateFin, null);

        CollectionUtils.transform(dispensations, new LigneDispensationTransformer());
        CollectionUtils.transform(total, new LigneDispensationTransformer());

        BigDecimal result;
        if (LigneDispensationTransformer.getTotal(total) == 0) {
            result = new BigDecimal(0);
        } else {
            result =
                new BigDecimal(LigneDispensationTransformer.getTotal(dispensations))
                        .divide(new BigDecimal(LigneDispensationTransformer.getTotal(total)), 3, RoundingMode.HALF_DOWN);
        }

        return new Indicateur(this.libelle, result);
    }

    /**
     * Setter pour libelle.
     * @param libelle Le libelle à écrire.
     */
    public void setLibelle(final String libelle) {
        this.libelle = libelle;
    }

}

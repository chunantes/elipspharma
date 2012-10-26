package fr.pharma.eclipse.service.indicateur.builder.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.indicateur.builder.IndicateurBuilder;
import fr.pharma.eclipse.transformer.dispensation.LigneDispensationTransformer;

/**
 * Builder en charge de construire l'indicateur Nombre de ligne de dispensations.
 
 * @version $Revision$ $Date$
 */
public class LigneDispensationBuilder
    extends AbstractDispensationBuilder
    implements Serializable, IndicateurBuilder
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -8270307424993432761L;

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
                            final Calendar dateFin)
    {
        final List dispensations = this.loadDispensations(pharmacie,
                                                          dateDebut,
                                                          dateFin,
                                                          true);
        CollectionUtils.transform(dispensations,
                                  new LigneDispensationTransformer());
        return new Indicateur(this.libelle,
                              new BigDecimal(LigneDispensationTransformer.getTotal(dispensations)));
    }
    /**
     * Setter pour libelle.
     * @param libelle Le libelle à écrire.
     */
    public void setLibelle(final String libelle)
    {
        this.libelle = libelle;
    }

}

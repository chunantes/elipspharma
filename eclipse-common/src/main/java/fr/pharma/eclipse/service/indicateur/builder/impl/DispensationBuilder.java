package fr.pharma.eclipse.service.indicateur.builder.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.indicateur.builder.IndicateurBuilder;

/**
 * Builder en charge de construire l'indicateur relatif aux dispensations
 * nominatives.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DispensationBuilder extends AbstractDispensationBuilder implements Serializable, IndicateurBuilder {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 304795463840886953L;

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

        final List<Dispensation> results = this.loadDispensations(pharmacie, dateDebut, dateFin, true);

        return new Indicateur(this.libelle, new BigDecimal(results.size()));
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

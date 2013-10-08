package fr.pharma.eclipse.service.indicateur.builder.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.indicateur.builder.IndicateurBuilder;

/**
 * Builder en charge de construire l'indicateur relatif aux essais Actifs.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiActifsBuilder implements Serializable, IndicateurBuilder {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 6030696180448546184L;

    /**
     * Service Essai.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

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

        return new Indicateur(this.libelle, new BigDecimal(this.essaiService.getEssaisActifs(dateFin, pharmacie).size()));
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

    /**
     * Setter pour essaiService.
     * @param essaiService Le essaiService à écrire.
     */
    public void setEssaiService(final EssaiService essaiService) {
        this.essaiService = essaiService;
    }

}

package fr.pharma.eclipse.service.indicateur.builder.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import fr.pharma.eclipse.domain.enums.TypeDispensation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.predicate.GenericPredicate;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.indicateur.builder.IndicateurBuilder;

/**
 * Classe en charge de construire l'indicateur taux d'essais actis en
 * dispensation nominatives.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class TauEssaiActifBuilder implements Serializable, IndicateurBuilder {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 2875632456582987144L;

    /**
     * Service essai.
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
        final List<Essai> essais = this.essaiService.getEssaisActifs(dateFin, pharmacie);
        final int nbEssaisActifs = essais.size();
        CollectionUtils.filter(essais, new GenericPredicate("detailDonneesPharma.infosDispensations.typeDispensation", TypeDispensation.NOMINATIVE));
        BigDecimal result;
        if (nbEssaisActifs == 0) {
            result = new BigDecimal(0);
        } else {
            result = new BigDecimal(essais.size()).divide(new BigDecimal(nbEssaisActifs), 3, RoundingMode.HALF_DOWN);
        }
        return new Indicateur(this.libelle, result);

    }
    /**
     * Setter pour essaiService.
     * @param essaiService Le essaiService à écrire.
     */
    public void setEssaiService(final EssaiService essaiService) {
        this.essaiService = essaiService;
    }

    /**
     * Setter pour libelle.
     * @param libelle Le libelle à écrire.
     */
    public void setLibelle(final String libelle) {
        this.libelle = libelle;
    }

}

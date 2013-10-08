package fr.pharma.eclipse.service.indicateur.builder.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.indicateur.builder.IndicateurBuilder;
import fr.pharma.eclipse.service.stock.MvtStockService;

/**
 * Builder de l'indicateur des réceptions.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GenericMvtStockBuilder implements IndicateurBuilder, Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -5709790405002651576L;

    /**
     * Service des mvts de stock.
     */
    @Resource(name = "mouvementStockService")
    private MvtStockService<MvtStock> mvtService;

    /**
     * Libellé de l'indicateur.
     */
    private String libelle;

    /**
     * Type de mouvement de stock.
     */
    private TypeMvtStock type;

    /**
     * {@inheritDoc}
     */
    @Override
    public Indicateur build(final Pharmacie pharmacie,
                            final Calendar dateDebut,
                            final Calendar dateFin) {

        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        criteria.setDateDebut(dateDebut);
        criteria.setDateFin(dateFin);
        criteria.setTypeMouvement(this.type);
        criteria.setPharmacie(pharmacie);
        criteria.setWithAcl(Boolean.FALSE);

        final List<MvtStock> results = this.mvtService.getAll(criteria);

        final List<String> resultWrapped = new ArrayList<String>();
        for (final MvtStock mvt : results) {
            final String key = this.buildKey(mvt);
            if (!resultWrapped.contains(key)) {
                resultWrapped.add(key);
            }
        }

        return new Indicateur(this.libelle, new BigDecimal(resultWrapped.size()));
    }

    /**
     * Construit la clé sur un fluxStock.
     * @return la clé.
     */
    private String buildKey(final MvtStock mvt) {
        final StringBuffer sb = new StringBuffer();

        // On regroupe les mouvements de type "entree de stock" par essai et on
        // ne comptabilise
        // qu'une seule réception quand il y en a plusieurs le même jour (cf
        // PHARMA-355)
        if (this.getType() != null && this.getType().equals(TypeMvtStock.APPROVISIONNEMENT)) {
            sb.append(mvt.getEssai().getId()).append("-").append(mvt.getDateCreation().get(Calendar.MONTH)).append(mvt.getDateCreation().get(Calendar.YEAR))
                    .append(mvt.getDateCreation().get(Calendar.DAY_OF_MONTH));
        } else {
            // si c'est un mvt par numero de traitement alors on créé une clé
            // commune pour les
            // numeros
            // de lots.
            if (!StringUtils.isEmpty(mvt.getNumTraitement())) {
                sb.append(mvt.getEssai().getNom()).append(mvt.getProduit().getCode()).append(mvt.getConditionnement().getLibelle()).append(mvt.getType().getLibelle())
                        .append(mvt.getNumLot()).append(mvt.getDateCreation().get(Calendar.MONTH)).append(mvt.getDateCreation().get(Calendar.YEAR))
                        .append(mvt.getDateCreation().get(Calendar.DAY_OF_MONTH)).append(mvt.getDateCreation().get(Calendar.MINUTE))
                        .append(mvt.getDateCreation().get(Calendar.HOUR_OF_DAY));

                // sinon une clé unique sur l'id.
            } else {
                sb.append(mvt.getId());
            }

        }
        return sb.toString();
    }

    /**
     * Setter pour mvtService.
     * @param mvtService Le mvtService à écrire.
     */
    public void setMvtService(final MvtStockService<MvtStock> mvtService) {
        this.mvtService = mvtService;
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
     * Getter pour type.
     * @return Le type
     */
    public TypeMvtStock getType() {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type Le type à écrire.
     */
    public void setType(final TypeMvtStock type) {
        this.type = type;
    }

}

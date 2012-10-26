package fr.pharma.eclipse.service.indicateur.builder.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.Resource;

import fr.pharma.eclipse.comparator.GenericComparator;
import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stock.PreparationEntree;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.indicateur.builder.IndicateurBuilder;
import fr.pharma.eclipse.service.stock.ApprovisionnementService;

/**
 * Builder en charge de calculer le nombre de préparations stériles.
 
 * @version $Revision$ $Date$
 */
public class PreparationBuilder
    implements IndicateurBuilder
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Service approvisionnement.
     */
    @Resource(name = "approvisionnementService")
    private ApprovisionnementService<PreparationEntree> approService;

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Stérile.
     */
    private Boolean sterile;

    /**
     * {@inheritDoc}
     */
    @Override
    public Indicateur build(final Pharmacie pharmacie,
                            final Calendar dateDebut,
                            final Calendar dateFin)
    {
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        criteria.setPharmacie(pharmacie);
        criteria.setDateDebut(dateDebut);
        criteria.setDateFin(dateFin);
        criteria.setTypeMouvement(TypeMvtStock.PREPARATION_ENTREE);
        criteria.setSterile(this.sterile);

        final SortedSet<PreparationEntree> set =
            new TreeSet<PreparationEntree>(new GenericComparator<PreparationEntree>("numOrdonnancier"));

        set.addAll(this.approService.getAll(criteria));
        return new Indicateur(this.libelle,
                              new BigDecimal(set.size()));
    }

    /**
     * Getter pour approService.
     * @return Le approService
     */
    public ApprovisionnementService<PreparationEntree> getApproService()
    {
        return this.approService;
    }

    /**
     * Setter pour approService.
     * @param approService Le approService à écrire.
     */
    public void setApproService(final ApprovisionnementService<PreparationEntree> approService)
    {
        this.approService = approService;
    }

    /**
     * Getter pour libelle.
     * @return Le libelle
     */
    public String getLibelle()
    {
        return this.libelle;
    }

    /**
     * Setter pour libelle.
     * @param libelle Le libelle à écrire.
     */
    public void setLibelle(final String libelle)
    {
        this.libelle = libelle;
    }

    /**
     * Getter pour sterile.
     * @return Le sterile
     */
    public Boolean getSterile()
    {
        return this.sterile;
    }

    /**
     * Setter pour sterile.
     * @param sterile Le sterile à écrire.
     */
    public void setSterile(final Boolean sterile)
    {
        this.sterile = sterile;
    }

}

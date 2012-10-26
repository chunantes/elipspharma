package fr.pharma.eclipse.service.indicateur.builder.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.dotation.DotationSearchCriteria;
import fr.pharma.eclipse.domain.model.dotation.Dotation;
import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.dotation.DotationService;
import fr.pharma.eclipse.service.indicateur.builder.IndicateurBuilder;

/**
 * Classe en charge de construire l'indicateur Nombre de dotations.
 
 * @version $Revision$ $Date$
 */
public class NbDotationBuilder
    extends AbstractDispensationBuilder
    implements Serializable, IndicateurBuilder
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 8831490388527087734L;

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Service dotations.
     */
    @Resource(name = "dotationService")
    private DotationService dotationService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Indicateur build(final Pharmacie pharmacie,
                            final Calendar dateDebut,
                            final Calendar dateFin)
    {

        final List<Dotation> results = this.loadDotations(pharmacie,
                                                          dateDebut,
                                                          dateFin);

        return new Indicateur(this.libelle,
                              new BigDecimal(results.size()));
    }

    /**
     * Recuperation de la liste des dotations.
     * @param pharmacie
     * @param dateDebut
     * @param dateFin
     */
    protected List<Dotation> loadDotations(final Pharmacie pharmacie,
                                           final Calendar dateDebut,
                                           final Calendar dateFin)
    {
        final DotationSearchCriteria criteria = new DotationSearchCriteria();
        criteria.setDateDebut(dateDebut);
        criteria.setDateFin(dateFin);
        criteria.setPharmacie(pharmacie);

        return this.dotationService.getAll(criteria);

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
     * Setter pour dotationService.
     * @param dotationServiceS Le dotationService à écrire.
     */
    public void setDotationService(final DotationService dotationService)
    {
        this.dotationService = dotationService;
    }
}

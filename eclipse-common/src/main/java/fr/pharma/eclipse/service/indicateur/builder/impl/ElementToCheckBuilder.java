package fr.pharma.eclipse.service.indicateur.builder.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import fr.pharma.eclipse.domain.criteria.dispensation.ElementToCheckSearchCriteria;
import fr.pharma.eclipse.domain.enums.TypeElementToCheck;
import fr.pharma.eclipse.domain.model.dispensation.ElementToCheck;
import fr.pharma.eclipse.domain.model.indicateur.Indicateur;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.dispensation.ElementToCheckService;
import fr.pharma.eclipse.service.indicateur.builder.IndicateurBuilder;

/**
 * Builder en charge de construire les indicateurs relatifs aux ElementsToCheck (Reconstitutions
 * etc.)
 
 * @version $Revision$ $Date$
 */
public class ElementToCheckBuilder
    implements IndicateurBuilder, Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -2682903627236347141L;

    /**
     * Type.
     */
    private TypeElementToCheck type;

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Service.
     */
    @Resource(name = "elementToCheckService")
    private ElementToCheckService service;

    /**
     * {@inheritDoc}
     */
    @Override
    public Indicateur build(final Pharmacie pharmacie,
                            final Calendar dateDebut,
                            final Calendar dateFin)
    {
        final ElementToCheckSearchCriteria criteria = new ElementToCheckSearchCriteria();
        criteria.setDateDebut(dateDebut);
        criteria.setDateFin(dateFin);
        criteria.setType(this.type);
        criteria.setPharmacie(pharmacie);

        final List<ElementToCheck> results = this.service.getAll(criteria);

        return new Indicateur(this.libelle,
                              new BigDecimal(results.size()));
    }

    /**
     * Setter pour service.
     * @param service Le service à écrire.
     */
    public void setService(final ElementToCheckService service)
    {
        this.service = service;
    }

    /**
     * Getter pour type.
     * @return Le type
     */
    public TypeElementToCheck getType()
    {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type Le type à écrire.
     */
    public void setType(final TypeElementToCheck type)
    {
        this.type = type;
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

}

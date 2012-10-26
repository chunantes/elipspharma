package fr.pharma.eclipse.domain.criteria.dotation;

import java.util.Calendar;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Critère de recherche sur Dotation.
 
 * @version $Revision$ $Date$
 */
public class DotationSearchCriteria
    extends AbstractSearchCriteria
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6040837302252798716L;

    /**
     * Essai.
     */
    private Essai essai;

    /**
     * Pharmacie.
     */
    private Pharmacie pharmacie;

    /**
     * Service.
     */
    private Service service;

    /**
     * Produit.
     */
    private Produit produit;

    /**
     * Dotation Traitee.
     */
    private Boolean traitee;

    /**
     * Date début.
     */
    private Calendar dateDebut;

    /**
     * Date fin.
     */
    private Calendar dateFin;

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear()
    {
        this.setEssai(null);
        this.setPharmacie(null);
        this.setProduit(null);
        this.setService(null);
        this.setTraitee(null);
        this.setDateDebut(null);
        this.setDateFin(null);
    }

    /**
     * Getter pour service.
     * @return Le service
     */
    public Service getService()
    {
        return this.service;
    }

    /**
     * Setter pour service.
     * @param service Le service à écrire.
     */
    public void setService(final Service service)
    {
        this.service = service;
    }

    /**
     * Getter pour produit.
     * @return Le produit
     */
    public Produit getProduit()
    {
        return this.produit;
    }

    /**
     * Setter pour produit.
     * @param produit Le produit à écrire.
     */
    public void setProduit(final Produit produit)
    {
        this.produit = produit;
    }

    /**
     * Getter pour traitee.
     * @return Le traitee
     */
    public Boolean getTraitee()
    {
        return this.traitee;
    }

    /**
     * Setter pour traitee.
     * @param traitee Le traitee à écrire.
     */
    public void setTraitee(final Boolean traitee)
    {
        this.traitee = traitee;
    }

    /**
     * Getter pour essai.
     * @return Le essai
     */
    public Essai getEssai()
    {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai Le essai à écrire.
     */
    public void setEssai(final Essai essai)
    {
        this.essai = essai;
    }

    /**
     * Getter pour pharmacie.
     * @return Le pharmacie
     */
    public Pharmacie getPharmacie()
    {
        return this.pharmacie;
    }

    /**
     * Setter pour pharmacie.
     * @param pharmacie Le pharmacie à écrire.
     */
    public void setPharmacie(final Pharmacie pharmacie)
    {
        this.pharmacie = pharmacie;
    }

    /**
     * Getter pour dateDebut.
     * @return Le dateDebut
     */
    public Calendar getDateDebut()
    {
        return this.dateDebut;
    }

    /**
     * Setter pour dateDebut.
     * @param dateDebut Le dateDebut à écrire.
     */
    public void setDateDebut(final Calendar dateDebut)
    {
        this.dateDebut = dateDebut;
    }

    /**
     * Getter pour dateFin.
     * @return Le dateFin
     */
    public Calendar getDateFin()
    {
        return this.dateFin;
    }

    /**
     * Setter pour dateFin.
     * @param dateFin Le dateFin à écrire.
     */
    public void setDateFin(final Calendar dateFin)
    {
        this.dateFin = dateFin;
    }

}

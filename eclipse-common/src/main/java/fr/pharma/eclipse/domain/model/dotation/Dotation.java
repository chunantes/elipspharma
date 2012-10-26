package fr.pharma.eclipse.domain.model.dotation;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.EssaiElement;
import fr.pharma.eclipse.domain.model.localisation.Service;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Bean métier représentant une demande de dotation.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "dotation")
public class Dotation
    extends BeanObject
    implements EssaiElement
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6623887171506578324L;

    /**
     * Essai.
     */
    @ManyToOne
    @JoinColumn(name = "id_essai", nullable = false)
    @Index(name = "idx_dotation_essai")
    @NotNull
    private Essai essai;

    /**
     * Pharmacie.
     */
    @ManyToOne
    @JoinColumn(name = "id_pharmacie", nullable = false)
    @Index(name = "idx_dotation_pharma")
    @NotNull
    private Pharmacie pharmacie;

    /**
     * Service.
     */
    @ManyToOne
    @JoinColumn(name = "id_service", nullable = false)
    @Index(name = "idx_dotation_service")
    @NotNull
    private Service service;

    /**
     * Personne ayant demandé la dotation.
     */
    @ManyToOne
    @JoinColumn(name = "id_personne", nullable = false)
    @Index(name = "idx_dotation_personne")
    @NotNull
    private Personne personne;

    /**
     * Date de la demande de dotation.
     */
    @Column(name = "dateDemande")
    @NotNull
    private Calendar dateDemande;

    /**
     * Produit.
     */
    @ManyToOne
    @JoinColumn(name = "id_produit", nullable = false)
    @Index(name = "idx_dotation_produit")
    @NotNull
    private Produit produit;

    /**
     * Conditionnement.
     */
    @ManyToOne
    @JoinColumn(name = "id_conditionnement", nullable = false)
    @Index(name = "idx_dotation_cond")
    @NotNull
    private Conditionnement conditionnement;

    /**
     * Quantité.
     */
    @Column(name = "quantite")
    @NotNull
    private Integer quantite;

    /**
     * Booléen indiquant si la dotation a été traitée ou non.
     */
    @Column(name = "traitee")
    @NotNull
    private Boolean traitee;

    /**
     * Commentaire sur la dotation.
     */
    @Column(name = "commentaire", columnDefinition = "TEXT")
    private String commentaire;

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
     * Getter pour conditionnement.
     * @return Le conditionnement
     */
    public Conditionnement getConditionnement()
    {
        return this.conditionnement;
    }

    /**
     * Setter pour conditionnement.
     * @param conditionnement Le conditionnement à écrire.
     */
    public void setConditionnement(final Conditionnement conditionnement)
    {
        this.conditionnement = conditionnement;
    }

    /**
     * Getter pour quantite.
     * @return Le quantite
     */
    public Integer getQuantite()
    {
        return this.quantite;
    }

    /**
     * Setter pour quantite.
     * @param quantite Le quantite à écrire.
     */
    public void setQuantite(final Integer quantite)
    {
        this.quantite = quantite;
    }

    /**
     * Getter pour personne.
     * @return Le personne
     */
    public Personne getPersonne()
    {
        return this.personne;
    }

    /**
     * Setter pour personne.
     * @param personne Le personne à écrire.
     */
    public void setPersonne(final Personne personne)
    {
        this.personne = personne;
    }

    /**
     * Getter pour dateDemande.
     * @return Le dateDemande
     */
    public Calendar getDateDemande()
    {
        return this.dateDemande;
    }

    /**
     * Setter pour dateDemande.
     * @param dateDemande Le dateDemande à écrire.
     */
    public void setDateDemande(final Calendar dateDemande)
    {
        this.dateDemande = dateDemande;
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
     * Getter pour commentaire.
     * @return Le commentaire
     */
    public String getCommentaire()
    {
        return this.commentaire;
    }

    /**
     * Setter pour commentaire.
     * @param commentaire Le commentaire à écrire.
     */
    public void setCommentaire(final String commentaire)
    {
        this.commentaire = commentaire;
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

}

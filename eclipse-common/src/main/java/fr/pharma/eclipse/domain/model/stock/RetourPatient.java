package fr.pharma.eclipse.domain.model.stock;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.enums.EtatRetour;
import fr.pharma.eclipse.domain.enums.TypeRetour;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.detail.DetailStockage;

/**
 * Bean métier représentant un retour patient.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "retour_patient")
public class RetourPatient
    extends BeanObject
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -6411351389055276723L;

    /**
     * Essai.
     */
    @ManyToOne
    @JoinColumn(name = "id_essai", nullable = false)
    @Index(name = "idx_essai_retourPatient")
    @NotNull
    private Essai essai;

    /**
     * Patient.
     */
    @ManyToOne
    @JoinColumn(name = "id_patient")
    @Index(name = "idx_patient_retourPatient")
    private Patient patient;

    /**
     * Personne.
     */
    @ManyToOne
    @JoinColumn(name = "id_personne", nullable = false)
    @Index(name = "idx_personne_retourPatient")
    @NotNull
    private Personne personne;

    /**
     * Produit.
     */
    @ManyToOne
    @JoinColumn(name = "id_produit", nullable = false)
    @Index(name = "idx_produit_retourPatient")
    @NotNull
    private Produit produit;

    /**
     * Conditionnement.
     */
    @ManyToOne
    @JoinColumn(name = "id_conditionnement", nullable = false)
    @Index(name = "idx_conditionnement_retourPatient")
    @NotNull
    private Conditionnement conditionnement;

    /**
     * DetailStockage.
     */
    @ManyToOne
    @JoinColumn(name = "id_detailStockage", nullable = false)
    @Index(name = "idx_detailStockage_retourPatient")
    @NotNull
    private DetailStockage detailStockage;

    /**
     * Numéro d'ordonnancier.
     */
    @Column(name = "numOrdonnancier")
    private Integer numOrdonnancier;

    /**
     * Etat.
     */
    @Column(name = "etat")
    @Enumerated(EnumType.STRING)
    private EtatRetour etat;

    /**
     * Date du dernier changement de l'état.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateEtat;

    /**
     * Date du dernier changement de l'état.
     */
    @Column(name = "commentaireEtat", columnDefinition = "TEXT")
    private String commentaireEtat;

    /**
     * Type de retour.
     */
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeRetour type;

    /**
     * Commentaire si entamé.
     */
    @Column(name = "commentaireEntame", columnDefinition = "TEXT")
    private String commentaireEntame;

    /**
     * Quantité.
     */
    @Column(name = "quantite")
    private Integer quantite;

    /**
     * Commentaire.
     */
    @Column(name = "commentaire", columnDefinition = "TEXT")
    private String commentaire;

    /**
     * Date.
     */
    private Calendar date;

    /**
     * Numéro de traitement.
     */
    private String numTraitement;

    /**
     * Numéro de lot.
     */
    private String numLot;

    /**
     * Getter sur essai.
     * @return Retourne le essai.
     */
    public Essai getEssai()
    {
        return this.essai;
    }

    /**
     * Getter sur patient.
     * @return Retourne le patient.
     */
    public Patient getPatient()
    {
        return this.patient;
    }

    /**
     * Getter sur personne.
     * @return Retourne le personne.
     */
    public Personne getPersonne()
    {
        return this.personne;
    }

    /**
     * Getter sur produit.
     * @return Retourne le produit.
     */
    public Produit getProduit()
    {
        return this.produit;
    }

    /**
     * Getter sur conditionnement.
     * @return Retourne le conditionnement.
     */
    public Conditionnement getConditionnement()
    {
        return this.conditionnement;
    }

    /**
     * Setter pour essai.
     * @param essai le essai à écrire.
     */
    public void setEssai(final Essai essai)
    {
        this.essai = essai;
    }

    /**
     * Setter pour patient.
     * @param patient le patient à écrire.
     */
    public void setPatient(final Patient patient)
    {
        this.patient = patient;
    }

    /**
     * Setter pour personne.
     * @param personne le personne à écrire.
     */
    public void setPersonne(final Personne personne)
    {
        this.personne = personne;
    }

    /**
     * Setter pour produit.
     * @param produit le produit à écrire.
     */
    public void setProduit(final Produit produit)
    {
        this.produit = produit;
    }

    /**
     * Setter pour conditionnement.
     * @param conditionnement le conditionnement à écrire.
     */
    public void setConditionnement(final Conditionnement conditionnement)
    {
        this.conditionnement = conditionnement;
    }

    /**
     * Getter sur date.
     * @return Retourne le date.
     */
    public Calendar getDate()
    {
        return this.date;
    }

    /**
     * Setter pour date.
     * @param date le date à écrire.
     */
    public void setDate(final Calendar date)
    {
        this.date = date;
    }

    /**
     * Getter sur detailStockage.
     * @return Retourne le detailStockage.
     */
    public DetailStockage getDetailStockage()
    {
        return this.detailStockage;
    }

    /**
     * Setter pour detailStockage.
     * @param detailStockage le detailStockage à écrire.
     */
    public void setDetailStockage(final DetailStockage detailStockage)
    {
        this.detailStockage = detailStockage;
    }

    /**
     * Getter sur quantite.
     * @return Retourne le quantite.
     */
    public Integer getQuantite()
    {
        return this.quantite;
    }

    /**
     * Getter sur commentaire.
     * @return Retourne le commentaire.
     */
    public String getCommentaire()
    {
        return this.commentaire;
    }

    /**
     * Setter pour quantite.
     * @param quantite le quantite à écrire.
     */
    public void setQuantite(final Integer quantite)
    {
        this.quantite = quantite;
    }

    /**
     * Setter pour commentaire.
     * @param commentaire le commentaire à écrire.
     */
    public void setCommentaire(final String commentaire)
    {
        this.commentaire = commentaire;
    }

    /**
     * Getter pour etat.
     * @return Le etat
     */
    public EtatRetour getEtat()
    {
        return this.etat;
    }

    /**
     * Setter pour etat.
     * @param etat Le etat à écrire.
     */
    public void setEtat(final EtatRetour etat)
    {
        this.etat = etat;
    }

    /**
     * Getter pour dateEtat.
     * @return Le dateEtat
     */
    public Calendar getDateEtat()
    {
        return this.dateEtat;
    }

    /**
     * Setter pour dateEtat.
     * @param dateEtat Le dateEtat à écrire.
     */
    public void setDateEtat(final Calendar dateEtat)
    {
        this.dateEtat = dateEtat;
    }

    /**
     * Getter pour commentaireEtat.
     * @return Le commentaireEtat
     */
    public String getCommentaireEtat()
    {
        return this.commentaireEtat;
    }

    /**
     * Setter pour commentaireEtat.
     * @param commentaireEtat Le commentaireEtat à écrire.
     */
    public void setCommentaireEtat(final String commentaireEtat)
    {
        this.commentaireEtat = commentaireEtat;
    }

    /**
     * Getter pour type.
     * @return Le type
     */
    public TypeRetour getType()
    {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type Le type à écrire.
     */
    public void setType(final TypeRetour type)
    {
        this.type = type;
    }

    /**
     * Getter pour commentaireEntame.
     * @return Le commentaireEntame
     */
    public String getCommentaireEntame()
    {
        return this.commentaireEntame;
    }

    /**
     * Setter pour commentaireEntame.
     * @param commentaireEntame Le commentaireEntame à écrire.
     */
    public void setCommentaireEntame(final String commentaireEntame)
    {
        this.commentaireEntame = commentaireEntame;
    }

    /**
     * Getter pour numOrdonnancier.
     * @return Le numOrdonnancier
     */
    public Integer getNumOrdonnancier()
    {
        return this.numOrdonnancier;
    }

    /**
     * Setter pour numOrdonnancier.
     * @param numOrdonnancier Le numOrdonnancier à écrire.
     */
    public void setNumOrdonnancier(final Integer numOrdonnancier)
    {
        this.numOrdonnancier = numOrdonnancier;
    }

    /**
     * Getter pour numTraitement.
     * @return Le numTraitement
     */
    public String getNumTraitement()
    {
        return this.numTraitement;
    }

    /**
     * Setter pour numTraitement.
     * @param numTraitement Le numTraitement à écrire.
     */
    public void setNumTraitement(final String numTraitement)
    {
        this.numTraitement = numTraitement;
    }

    /**
     * Getter pour numLot.
     * @return Le numLot
     */
    public String getNumLot()
    {
        return this.numLot;
    }

    /**
     * Setter pour numLot.
     * @param numLot Le numLot à écrire.
     */
    public void setNumLot(final String numLot)
    {
        this.numLot = numLot;
    }

}

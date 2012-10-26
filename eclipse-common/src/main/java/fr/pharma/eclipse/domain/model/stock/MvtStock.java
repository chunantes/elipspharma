package fr.pharma.eclipse.domain.model.stock;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.NotEmpty;

import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.common.BeanParentDocument;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.EssaiElement;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Bean métier représentant un mouvement de stock.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "mvtstock")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MvtStock
    extends BeanObject
    implements BeanParentDocument, EssaiElement
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 6984247000097869188L;

    /**
     * Essai.
     */
    @ManyToOne
    @JoinColumn(name = "id_essai", nullable = false)
    @Index(name = "idx_mvtstock_essai")
    @NotNull
    private Essai essai;

    /**
     * Produit.
     */
    @ManyToOne
    @JoinColumn(name = "id_produit", nullable = false)
    @Index(name = "idx_mvtstock_produit")
    @NotNull
    private Produit produit;

    /**
     * Conditionnement.
     */
    @ManyToOne
    @JoinColumn(name = "id_conditionnement", nullable = false)
    @Index(name = "idx_mvtstock_conditionnement")
    @NotNull
    private Conditionnement conditionnement;

    /**
     * Numéro de lot.
     */
    @Column(name = "numLot")
    @NotNull
    @NotEmpty
    private String numLot;

    /**
     * Numéro de traitement.
     */
    @Column(name = "numTraitement")
    private String numTraitement;

    /**
     * Pharmacie.
     */
    @ManyToOne
    @JoinColumn(name = "id_pharmacie", nullable = false)
    @Index(name = "idx_mvtstock_pharmacie")
    @NotNull
    private Pharmacie pharmacie;

    /**
     * Personne à l'origine du mouvement (utilisateur courant).
     */
    @ManyToOne
    @JoinColumn(name = "id_personne", nullable = false)
    @Index(name = "idx_mvtstock_personne")
    @NotNull
    private Personne personne;

    /**
     * Type de mouvement de stock.
     */
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeMvtStock type;

    /**
     * Date de création.
     */
    @Column(name = "dateCreation")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Calendar dateCreation;

    /**
     * Booléen indiquant si l'approvisionnement a été approuvé.
     */
    @Column(name = "approApprouve")
    @NotNull
    private Boolean approApprouve;

    /**
     * Quantité.
     */
    @Column(name = "quantite")
    @NotNull
    private Integer quantite;

    /**
     * Date de péremption.
     */
    @Column(name = "datePeremption")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar datePeremption;

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
     * Getter pour type.
     * @return Le type
     */
    public TypeMvtStock getType()
    {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type Le type à écrire.
     */
    public void setType(final TypeMvtStock type)
    {
        this.type = type;
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
     * Getter pour dateCreation.
     * @return Le dateCreation
     */
    public Calendar getDateCreation()
    {
        return this.dateCreation;
    }

    /**
     * Setter pour dateCreation.
     * @param dateCreation Le dateCreation à écrire.
     */
    public void setDateCreation(final Calendar dateCreation)
    {
        this.dateCreation = dateCreation;
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
     * Getter pour approuve.
     * @return Le approuve
     */
    public Boolean getApproApprouve()
    {
        return this.approApprouve;
    }

    /**
     * Setter pour approuve.
     * @param approApprouve Le approuve à écrire.
     */
    public void setApproApprouve(final Boolean approApprouve)
    {
        this.approApprouve = approApprouve;
    }

    /**
     * Getter pour datePeremption.
     * @return Le datePeremption
     */
    public Calendar getDatePeremption()
    {
        return this.datePeremption;
    }

    /**
     * Setter pour datePeremption.
     * @param datePeremption Le datePeremption à écrire.
     */
    public void setDatePeremption(final Calendar datePeremption)
    {
        this.datePeremption = datePeremption;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder("[");
        builder.append("id: ").append(this.getId());
        builder.append(", essai: ").append(this.essai);
        builder.append(", produit: ").append(this.produit);
        builder.append(", cond: ").append(this.conditionnement);
        return builder.append("]").toString();
    }
}

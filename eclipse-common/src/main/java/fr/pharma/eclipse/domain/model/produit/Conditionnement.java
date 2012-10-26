package fr.pharma.eclipse.domain.model.produit;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.NotEmpty;

import com.sun.istack.NotNull;

import fr.pharma.eclipse.domain.enums.produit.FormeConditionnement;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.enums.produit.UniteDosage;
import fr.pharma.eclipse.domain.enums.produit.UniteGestion;
import fr.pharma.eclipse.domain.enums.produit.VoieAdministration;
import fr.pharma.eclipse.domain.model.common.BeanObject;

/**
 * Classe métier représentant un Conditionnement.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "conditionnement")
public class Conditionnement
    extends BeanObject
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -407402581375331776L;

    /**
     * Libellé.
     */
    @Column(name = "libelle")
    @NotNull
    @NotEmpty
    private String libelle;

    /**
     * Mode de prescription.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "modePrescription")
    @NotNull
    private ModePrescription modePrescription;

    /**
     * Forme.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "forme")
    @NotNull
    private FormeConditionnement forme;

    /**
     * Unité de gestion.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "uniteGestion")
    private UniteGestion uniteGestion;

    /**
     * Unité de prescription.
     */
    @Column(name = "unitePrescription")
    private String unitePrescription;

    /**
     * Voie d'administration.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "voieAdministration")
    private VoieAdministration voieAdministration;

    /**
     * Dosage.
     */
    @Column(name = "dosage")
    private BigDecimal dosage;

    /**
     * Dosage.
     */
    @Column(name = "contenance")
    private BigDecimal contenance;

    /**
     * Unité de contenance.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "uniteContenance")
    private UniteDosage uniteContenance;

    /**
     * Nombre d'unités par conteneur.
     */
    @Column(name = "nbUnitePrescription")
    private BigDecimal nbUnitePrescription;

    /**
     * Unité de dosage.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "uniteDosage")
    private UniteDosage uniteDosage;

    /**
     * Quantité par patient.
     */
    @Column(name = "quantiteParPatient")
    private Integer quantiteParPatient;

    /**
     * Objet auquel est rattaché le conditionnement.
     */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_produit")
    @Index(name = "idx_conditionnement_produit")
    private Produit produit;

    /**
     * Getter sur libelle.
     * @return Retourne le libelle.
     */
    public String getLibelle()
    {
        return this.libelle;
    }

    /**
     * Setter pour libelle.
     * @param libelle le libelle à écrire.
     */
    public void setLibelle(final String libelle)
    {
        this.libelle = libelle;
    }

    /**
     * Getter sur modePrescription.
     * @return Retourne le modePrescription.
     */
    public ModePrescription getModePrescription()
    {
        return this.modePrescription;
    }

    /**
     * Setter pour modePrescription.
     * @param modePrescription le modePrescription à écrire.
     */
    public void setModePrescription(final ModePrescription modePrescription)
    {
        this.modePrescription = modePrescription;
    }

    /**
     * Getter sur uniteGestion.
     * @return Retourne le uniteGestion.
     */
    public UniteGestion getUniteGestion()
    {
        return this.uniteGestion;
    }

    /**
     * Setter pour uniteGestion.
     * @param uniteGestion le uniteGestion à écrire.
     */
    public void setUniteGestion(final UniteGestion uniteGestion)
    {
        this.uniteGestion = uniteGestion;
    }

    /**
     * Getter sur unitePrescription.
     * @return Retourne le unitePrescription.
     */
    public String getUnitePrescription()
    {
        return this.unitePrescription;
    }

    /**
     * Setter pour unitePrescription.
     * @param unitePrescription le unitePrescription à écrire.
     */
    public void setUnitePrescription(final String unitePrescription)
    {
        this.unitePrescription = unitePrescription;
    }

    /**
     * Getter sur voieAdministration.
     * @return Retourne le voieAdministration.
     */
    public VoieAdministration getVoieAdministration()
    {
        return this.voieAdministration;
    }

    /**
     * Setter pour voieAdministration.
     * @param voieAdministration le voieAdministration à écrire.
     */
    public void setVoieAdministration(final VoieAdministration voieAdministration)
    {
        this.voieAdministration = voieAdministration;
    }

    /**
     * Getter sur dosage.
     * @return Retourne le dosage.
     */
    public BigDecimal getDosage()
    {
        return this.dosage;
    }

    /**
     * Getter sur contenance.
     * @return Retourne le contenance.
     */
    public BigDecimal getContenance()
    {
        return this.contenance;
    }

    /**
     * Getter sur uniteContenance.
     * @return Retourne le uniteContenance.
     */
    public UniteDosage getUniteContenance()
    {
        return this.uniteContenance;
    }

    /**
     * Setter pour dosage.
     * @param dosage le dosage à écrire.
     */
    public void setDosage(final BigDecimal dosage)
    {
        this.dosage = dosage;
    }

    /**
     * Setter pour contenance.
     * @param contenance le contenance à écrire.
     */
    public void setContenance(final BigDecimal contenance)
    {
        this.contenance = contenance;
    }

    /**
     * Setter pour uniteContenance.
     * @param uniteContenance le uniteContenance à écrire.
     */
    public void setUniteContenance(final UniteDosage uniteContenance)
    {
        this.uniteContenance = uniteContenance;
    }

    /**
     * Getter sur nbUnitePrescription.
     * @return Retourne le nbUnitePrescription.
     */
    public BigDecimal getNbUnitePrescription()
    {
        return this.nbUnitePrescription;
    }

    /**
     * Setter pour nbUnitePrescription.
     * @param nbUnitePrescription le nbUnitePrescription à écrire.
     */
    public void setNbUnitePrescription(final BigDecimal nbUnitePrescription)
    {
        this.nbUnitePrescription = nbUnitePrescription;
    }

    /**
     * Getter sur uniteDosage.
     * @return Retourne le uniteDosage.
     */
    public UniteDosage getUniteDosage()
    {
        return this.uniteDosage;
    }

    /**
     * Setter pour uniteDosage.
     * @param uniteDosage le uniteDosage à écrire.
     */
    public void setUniteDosage(final UniteDosage uniteDosage)
    {
        this.uniteDosage = uniteDosage;
    }

    /**
     * Getter sur quantiteParPatient.
     * @return Retourne le quantiteParPatient.
     */
    public Integer getQuantiteParPatient()
    {
        return this.quantiteParPatient;
    }

    /**
     * Setter pour quantiteParPatient.
     * @param quantiteParPatient le quantiteParPatient à écrire.
     */
    public void setQuantiteParPatient(final Integer quantiteParPatient)
    {
        this.quantiteParPatient = quantiteParPatient;
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
     * Setter pour produit.
     * @param produit le produit à écrire.
     */
    public void setProduit(final Produit produit)
    {
        this.produit = produit;
    }

    /**
     * Getter sur forme.
     * @return Retourne le forme.
     */
    public FormeConditionnement getForme()
    {
        return this.forme;
    }

    /**
     * Setter pour forme.
     * @param forme le forme à écrire.
     */
    public void setForme(final FormeConditionnement forme)
    {
        this.forme = forme;
    }

    @Override
    public String toString()
    {
        return this.libelle
               + ":"
               + this.modePrescription
               + ":"
               + this.forme;
    }
}

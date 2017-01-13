package fr.pharma.eclipse.domain.model.design;

import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Index;

import com.sun.istack.NotNull;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.design.embedded.Frequence;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;

/**
 * Bean métier représentant une prescription type.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "prescription_type")
public class PrescriptionType extends BeanObject

{

    /**
     * Prescription.
     */
    private static final long serialVersionUID = -7822365111241726648L;

    /**
     * Début.
     */
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "nb", column = @Column(name = "nb_debut")), @AttributeOverride(name = "unite", column = @Column(name = "unite_debut")) })
    private TempsPrescription debut = new TempsPrescription();

    /**
     * Durée.
     */
    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "nb", column = @Column(name = "nb_duree")), @AttributeOverride(name = "unite", column = @Column(name = "unite_duree")) })
    private TempsPrescription duree = new TempsPrescription();

    /**
     * Nombre d'unités.
     */
    @Column(name = "nbUniteDosage")
    private BigDecimal nbUniteDosage;

    /**
     * Dosage.
     */
    @Column(name = "dosage")
    private BigDecimal dosage;

    /**
     * Fréquence.
     */
    @Embedded
    private Frequence frequence = new Frequence();

    /**
     * Description.
     */
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    /**
     * Conditionnement.
     */
    @ManyToOne()
    @JoinColumn(name = "id_conditionnement")
    @Index(name = "idx_prescription_conditionnement")
    private Conditionnement conditionnement;

    /**
     * Sequence.
     */
    @ManyToOne()
    @JoinColumn(name = "id_sequence")
    @Index(name = "idx_prescription_sequence")
    private Sequence sequence;

    /**
     * Produit.
     */
    @ManyToOne()
    @JoinColumn(name = "id_produit")
    @Index(name = "idx_prescription_produit")
    @NotNull
    private Produit produit;

    public String getDosageAsString() {
        final StringBuffer buff = new StringBuffer();
        if (this.nbUniteDosage != null) {
            buff.append(this.nbUniteDosage);
            buff.append(" ");
            buff.append("doses - ");
        }
        if (this.conditionnement!=null) {
            if (this.conditionnement.getDosage() != null) {
                buff.append(this.conditionnement.getDosage());
            }
            if (this.conditionnement.getUniteDosage() != null) {
                buff.append(" ");
                buff.append(this.conditionnement.getUniteDosage());
            }
        }
        if (buff.length() > 0) {
            buff.append(" : ");
        }
        return buff.toString();
    }

    /**
     * Getter sur description.
     * @return Retourne le description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter pour description.
     * @param description le description à écrire.
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Getter sur sequence.
     * @return Retourne le sequence.
     */
    public Sequence getSequence() {
        return this.sequence;
    }

    /**
     * Setter pour sequence.
     * @param sequence le sequence à écrire.
     */
    public void setSequence(final Sequence sequence) {
        this.sequence = sequence;
    }

    /**
     * Getter sur produit.
     * @return Retourne le produit.
     */
    public Produit getProduit() {
        return this.produit;
    }

    /**
     * Setter pour produit.
     * @param produit le produit à écrire.
     */
    public void setProduit(final Produit produit) {
        this.produit = produit;
    }

    /**
     * Getter sur debut.
     * @return Retourne le debut.
     */
    public TempsPrescription getDebut() {
        if (this.debut == null) {
            return new TempsPrescription();
        }
        return this.debut;
    }

    /**
     * Setter pour debut.
     * @param debut le debut à écrire.
     */
    public void setDebut(final TempsPrescription debut) {
        this.debut = debut;
    }

    /**
     * Getter sur duree.
     * @return Retourne le duree.
     */
    public TempsPrescription getDuree() {
        if (this.duree == null) {
            return new TempsPrescription();
        }
        return this.duree;
    }

    /**
     * Setter pour duree.
     * @param duree le duree à écrire.
     */
    public void setDuree(final TempsPrescription duree) {
        this.duree = duree;
    }

    /**
     * Getter sur frequence.
     * @return Retourne le frequence.
     */
    public Frequence getFrequence() {
        if (this.frequence == null) {
            return new Frequence();
        }
        return this.frequence;
    }

    /**
     * Setter pour frequence.
     * @param frequence le frequence à écrire.
     */
    public void setFrequence(final Frequence frequence) {
        this.frequence = frequence;
    }

    /**
     * Getter sur nbUniteDosage.
     * @return Retourne le nbUniteDosage.
     */
    public BigDecimal getNbUniteDosage() {
        return this.nbUniteDosage;
    }

    /**
     * Setter pour nbUniteDosage.
     * @param nbUniteDosage le nbUniteDosage à écrire.
     */
    public void setNbUniteDosage(final BigDecimal nbUniteDosage) {
        this.nbUniteDosage = nbUniteDosage;
    }

    /**
     * Getter sur dosage.
     * @return Retourne le dosage.
     */
    public BigDecimal getDosage() {
        return this.dosage;
    }

    /**
     * Setter pour dosage.
     * @param dosage le dosage à écrire.
     */
    public void setDosage(final BigDecimal dosage) {
        this.dosage = dosage;
    }

    /**
     * Getter sur conditionnement.
     * @return Retourne le conditionnement.
     */
    public Conditionnement getConditionnement() {
        return this.conditionnement;
    }

    /**
     * Setter pour conditionnement.
     * @param conditionnement le conditionnement à écrire.
     */
    public void setConditionnement(final Conditionnement conditionnement) {
        this.conditionnement = conditionnement;
    }

}

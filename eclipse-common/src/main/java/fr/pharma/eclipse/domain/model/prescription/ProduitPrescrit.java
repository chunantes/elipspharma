package fr.pharma.eclipse.domain.model.prescription;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.common.Clonable;
import fr.pharma.eclipse.domain.model.design.embedded.Frequence;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;

/**
 * Classe du modèle représentant un produit prescrit lors d'une prescription.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "produit_prescrit")
public class ProduitPrescrit
    extends BeanObject
    implements Clonable<ProduitPrescrit>
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -4949382555305161294L;

    /**
     * Patient.
     */
    @ManyToOne
    @JoinColumn(name = "id_produit", nullable = false)
    @Index(name = "idx_produit_produit_prescrit")
    @NotNull
    private Produit produit;

    /**
     * Prescription dont dépend le produit prescrit.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prescription", nullable = false)
    @Index(name = "idx_prescription_produit_prescrit")
    @NotNull
    private Prescription prescription;

    /**
     * Conditionnement.
     */
    @ManyToOne()
    @JoinColumn(name = "id_conditionnement", nullable = false)
    @Index(name = "idx_conditionnement_produit_prescrit")
    @NotNull
    private Conditionnement conditionnement;

    /**
     * Dispensé.
     */
    @Column(name = "dispense")
    private Boolean dispense;

    /**
     * Booleen définissant si le produit est à dispenser ou non.
     */
    @Column(name = "aDispenser")
    private Boolean aDispenser;

    /**
     * Début.
     */
    @Embedded
    @AttributeOverrides(
    {@AttributeOverride(name = "nb", column = @Column(name = "nb_debut")),
     @AttributeOverride(name = "unite", column = @Column(name = "unite_debut")) })
    private TempsPrescription debut = new TempsPrescription();

    /**
     * Durée.
     */
    @Embedded
    @AttributeOverrides(
    {@AttributeOverride(name = "nb", column = @Column(name = "nb_duree")),
     @AttributeOverride(name = "unite", column = @Column(name = "unite_duree")) })
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
     * Numero de traitement.
     */
    @Column(name = "numTraitement")
    private String numTraitement;

    /**
     * Dispensations produits associées.
     */
    @OneToMany(mappedBy = "produitPrescrit")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<DispensationProduit> dispensationProduits = new ArrayList<DispensationProduit>();

    /**
     * Methode en charge de constuire le chaine de caractère représentant le posologie.
     * @return La posologie sous forme de chaine de caractères.
     */
    public String getDosageAsString()
    {
        final StringBuffer buff = new StringBuffer();
        if (this.nbUniteDosage != null)
        {
            buff.append(this.nbUniteDosage);
            buff.append(" ");
            buff.append("doses");
        }
        if (buff.length() > 0)
        {
            buff.append(" - ");
        }
        if (this.conditionnement.getDosage() != null
            && this.conditionnement.getUniteDosage() != null)
        {
            buff.append(this.conditionnement.getDosage());
            buff.append(" ");
            buff.append(this.conditionnement.getUniteDosage());
        }
        return buff.toString();
    }

    /**
     * @return
     */
    @Override
    public ProduitPrescrit cloneMe()
    {
        final ProduitPrescrit produit = new ProduitPrescrit();
        produit.setDebut(this.getDebut());
        produit.setDescription(this.getDescription());
        produit.setDuree(this.getDuree());
        produit.setFrequence(this.getFrequence());
        produit.setNbUniteDosage(this.getNbUniteDosage());
        produit.setProduit(this.getProduit());
        produit.setConditionnement(this.conditionnement);
        return produit;
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
     * Getter sur debut.
     * @return Retourne le debut.
     */
    public TempsPrescription getDebut()
    {
        return this.debut;
    }

    /**
     * Setter pour debut.
     * @param debut le debut à écrire.
     */
    public void setDebut(final TempsPrescription debut)
    {
        this.debut = debut;
    }

    /**
     * Getter sur duree.
     * @return Retourne le duree.
     */
    public TempsPrescription getDuree()
    {
        return this.duree;
    }

    /**
     * Setter pour duree.
     * @param duree le duree à écrire.
     */
    public void setDuree(final TempsPrescription duree)
    {
        this.duree = duree;
    }

    /**
     * Getter sur nbUniteDosage.
     * @return Retourne le nbUniteDosage.
     */
    public BigDecimal getNbUniteDosage()
    {
        return this.nbUniteDosage;
    }

    /**
     * Setter pour nbUniteDosage.
     * @param nbUniteDosage le nbUniteDosage à écrire.
     */
    public void setNbUniteDosage(final BigDecimal nbUniteDosage)
    {
        this.nbUniteDosage = nbUniteDosage;
    }

    /**
     * Getter sur frequence.
     * @return Retourne le frequence.
     */
    public Frequence getFrequence()
    {
        return this.frequence;
    }

    /**
     * Setter pour frequence.
     * @param frequence le frequence à écrire.
     */
    public void setFrequence(final Frequence frequence)
    {
        this.frequence = frequence;
    }

    /**
     * Getter sur description.
     * @return Retourne le description.
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * Setter pour description.
     * @param description le description à écrire.
     */
    public void setDescription(final String description)
    {
        this.description = description;
    }

    /**
     * Getter sur prescription.
     * @return Retourne le prescription.
     */
    public Prescription getPrescription()
    {
        return this.prescription;
    }

    /**
     * Setter pour prescription.
     * @param prescription le prescription à écrire.
     */
    public void setPrescription(final Prescription prescription)
    {
        this.prescription = prescription;
    }

    /**
     * Getter sur numTraitement.
     * @return Retourne le numTraitement.
     */
    public String getNumTraitement()
    {
        return this.numTraitement;
    }

    /**
     * Setter pour numTraitement.
     * @param numTraitement le numTraitement à écrire.
     */
    public void setNumTraitement(final String numTraitement)
    {
        this.numTraitement = numTraitement;
    }

    /**
     * Getter sur dispense.
     * @return Retourne le dispense.
     */
    public Boolean getDispense()
    {
        return this.dispense;
    }

    /**
     * Setter pour dispense.
     * @param dispense le dispense à écrire.
     */
    public void setDispense(final Boolean dispense)
    {
        this.dispense = dispense;
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
     * Setter pour conditionnement.
     * @param conditionnement le conditionnement à écrire.
     */
    public void setConditionnement(final Conditionnement conditionnement)
    {
        this.conditionnement = conditionnement;
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
     * Setter pour dosage.
     * @param dosage le dosage à écrire.
     */
    public void setDosage(final BigDecimal dosage)
    {
        this.dosage = dosage;
    }

    /**
     * Getter pour aDispenser.
     * @return Le aDispenser
     */
    public Boolean getADispenser()
    {
        return this.aDispenser;
    }

    /**
     * Getter pour aDispenser.
     * @return Le aDispenser
     */
    public Boolean getaDispenser()
    {
        return this.aDispenser;
    }

    /**
     * Setter pour aDispenser.
     * @param aDispenser Le aDispenser à écrire.
     */
    public void setADispenser(final Boolean aDispenser)
    {
        this.aDispenser = aDispenser;
    }

    /**
     * Getter pour dispensationProduits.
     * @return Le dispensationProduits
     */
    public List<DispensationProduit> getDispensationProduits()
    {
        return this.dispensationProduits;
    }

    /**
     * Setter pour dispensationProduits.
     * @param dispensationProduits Le dispensationProduits à écrire.
     */
    public void setDispensationProduits(final List<DispensationProduit> dispensationProduits)
    {
        this.dispensationProduits = dispensationProduits;
    }

    @Override
    public String toString()
    {
        return this.produit
               + ":"
               + this.conditionnement;
    }

}

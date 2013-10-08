package fr.pharma.eclipse.domain.model.dispensation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import fr.pharma.eclipse.comparator.dispensation.DispensationProduitComparator;
import fr.pharma.eclipse.comparator.dispensation.ElementToCheckComparator;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.ordonnancier.OrdonnancierDisp;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Classe du modèle représentant une dispensation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "dispensation")
public class Dispensation extends BeanObject {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -3345617198080226144L;

    /**
     * Date de dispensation.
     */
    @Column(name = "dateDispensation")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateDispensation;

    /**
     * Prescription dont est issu la dispensation.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_prescription", nullable = false)
    @Index(name = "idx_prescription_dispensation")
    @NotNull
    private Prescription prescription;

    /**
     * Dispensé ?.
     */
    @Column(name = "dispense")
    private Boolean dispense;

    /**
     * Liste des éléments à vérifier avant la dispensation.
     */
    @OneToMany(mappedBy = "dispensation", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = ElementToCheckComparator.class)
    private SortedSet<ElementToCheck> elementsToCheck = new TreeSet<ElementToCheck>(new ElementToCheckComparator());

    /**
     * Liste des dispensations produits.
     */
    @OneToMany(mappedBy = "dispensation", cascade = {CascadeType.ALL })
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = DispensationProduitComparator.class)
    private SortedSet<DispensationProduit> dispensationsProduit = new TreeSet<DispensationProduit>(new DispensationProduitComparator());

    /**
     * Pharmacie de la dispensation.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pharmacie", nullable = false)
    @Index(name = "idx_disp_pharmacie")
    @NotNull
    private Pharmacie pharmacie;

    /**
     * Numéro de l'ordonnancier de dispensation.
     */
    @Column(name = "numOrdonnancier")
    private Integer numOrdonnancier;

    /**
     * Ordonnancier de dispensation.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ordonnancier")
    @Index(name = "idx_disp_ordon")
    private OrdonnancierDisp ordonnancier;

    /**
     * Commentaire
     */
    /**
     * Numéro de l'ordonnancier de dispensation.
     */
    @Column(name = "commentaire", columnDefinition = "TEXT")
    private String commentaire;

    /**
     * Getter sur dateDispensation.
     * @return Retourne le dateDispensation.
     */
    public Calendar getDateDispensation() {
        return this.dateDispensation;
    }

    /**
     * Setter pour dateDispensation.
     * @param dateDispensation le dateDispensation à écrire.
     */
    public void setDateDispensation(final Calendar dateDispensation) {
        this.dateDispensation = dateDispensation;
    }

    /**
     * Getter sur prescription.
     * @return Retourne le prescription.
     */
    public Prescription getPrescription() {
        return this.prescription;
    }

    /**
     * Setter pour prescription.
     * @param prescription le prescription à écrire.
     */
    public void setPrescription(final Prescription prescription) {
        this.prescription = prescription;
    }

    /**
     * Getter sur dispensationsProduit.
     * @return Retourne le dispensationsProduit.
     */
    public SortedSet<DispensationProduit> getDispensationsProduit() {
        return this.dispensationsProduit;
    }

    /**
     * Setter pour dispensationsProduit.
     * @param dispensationsProduit le dispensationsProduit à écrire.
     */
    public void setDispensationsProduit(final SortedSet<DispensationProduit> dispensationsProduit) {
        this.dispensationsProduit = dispensationsProduit;
    }

    /**
     * Getter sur elementsToCheck.
     * @return Retourne le elementsToCheck.
     */
    public SortedSet<ElementToCheck> getElementsToCheck() {
        return this.elementsToCheck;
    }

    /**
     * Setter pour elementsToCheck.
     * @param elementsToCheck le elementsToCheck à écrire.
     */
    public void setElementsToCheck(final SortedSet<ElementToCheck> elementsToCheck) {
        this.elementsToCheck = elementsToCheck;
    }

    /**
     * Getter sur dispense.
     * @return Retourne le dispense.
     */
    public Boolean getDispense() {
        return this.dispense;
    }

    /**
     * Setter pour dispense.
     * @param dispense le dispense à écrire.
     */
    public void setDispense(final Boolean dispense) {
        this.dispense = dispense;
    }

    /**
     * Getter pour numOrdonnancier.
     * @return Le numOrdonnancier
     */
    public Integer getNumOrdonnancier() {
        return this.numOrdonnancier;
    }

    /**
     * Setter pour numOrdonnancier.
     * @param numOrdonnancier Le numOrdonnancier à écrire.
     */
    public void setNumOrdonnancier(final Integer numOrdonnancier) {
        this.numOrdonnancier = numOrdonnancier;
    }

    /**
     * Getter pour ordonnancier.
     * @return Le ordonnancier
     */
    public OrdonnancierDisp getOrdonnancier() {
        return this.ordonnancier;
    }

    /**
     * Setter pour ordonnancier.
     * @param ordonnancier Le ordonnancier à écrire.
     */
    public void setOrdonnancier(final OrdonnancierDisp ordonnancier) {
        this.ordonnancier = ordonnancier;
    }

    /**
     * Getter pour pharmacie.
     * @return Le pharmacie
     */
    public Pharmacie getPharmacie() {
        return this.pharmacie;
    }

    /**
     * Setter pour pharmacie.
     * @param pharmacie Le pharmacie à écrire.
     */
    public void setPharmacie(final Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
    }

    public List<ProduitPrescrit> getProduitsPrescrits() {
        return new ArrayList<ProduitPrescrit>(this.getPrescription().getProduitsPrescrits());
    }

    /**
     * Retourne les produits prescrits sous forme de chaine de caractères.
     * @return les produits prescrits sous forme de chaine de caractères.
     */
    public String getProduitsPrescritsAsString() {
        final StringBuffer sb = new StringBuffer();
        for (final ProduitPrescrit p : this.getProduitsPrescrits()) {
            sb.append(p.getProduit().getNom()).append(",");
        }
        return StringUtils.substringBeforeLast(sb.toString(), ",");
    }

    /**
     * {@inheritDoc}
     */
    public Essai getEssai() {
        return this.getPrescription().getInclusion().getEssai();
    }

    /**
     * Getter pour commentaire.
     * @return Le commentaire
     */
    public String getCommentaire() {
        return this.commentaire;
    }

    /**
     * Setter pour commentaire.
     * @param commentaire Le commentaire à écrire.
     */
    public void setCommentaire(final String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * Marquer produit dispensé
     * @param clé la clé du produit
     */
    public void setProduitDispense(final Long cle) {
        if (this.prescription != null) {
            this.prescription.setProduitDispense(cle);
        }
    }
}

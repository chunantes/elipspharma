package fr.pharma.eclipse.domain.model.dispensation;

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

import fr.pharma.eclipse.domain.enums.TypeElementToCheck;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.common.BeanWithNom;
import fr.pharma.eclipse.domain.model.ordonnancier.OrdonnancierFabReconst;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;

/**
 * Classe du modèle représentant un élément à vérifier lors de la phase de
 * dispensation. Ces éléments sont définis dans les onglets "actes pharma" de
 * chaque produit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "element_to_check")
public class ElementToCheck extends BeanObject implements BeanWithNom {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 4885683509513611184L;

    /**
     * DispensationProduit auquel il faut appliquer la verification.
     */
    @ManyToOne
    @JoinColumn(name = "id_dispensation", nullable = false)
    @Index(name = "idx_dispensation_elementToCheck")
    @NotNull
    private Dispensation dispensation;

    /**
     * Nom du champs à vérifier.
     */
    @Column(name = "nomChamps")
    private String nomChamps;

    /**
     * Verifié?.
     */
    @Column(name = "checked")
    private Boolean checked;

    /**
     * Date de vérification de l'élément (date où l'utilisateur a coché la
     * case).
     */
    @Column(name = "dateChecked")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateChecked;

    /**
     * Personne ayant fait la vérification.
     */
    @ManyToOne
    @JoinColumn(name = "id_personne")
    @Index(name = "idx_eltcheck_personne")
    private Personne checkedBy;

    /**
     * Commentaire.
     */
    @Column(name = "commentaire", columnDefinition = "TEXT")
    private String commentaire;

    /**
     * Produit prescrit.
     */
    @ManyToOne
    @JoinColumn(name = "id_produitPrescrit", nullable = false)
    @Index(name = "idx_produit_prescrit_elementToCheck")
    @NotNull
    private ProduitPrescrit produitPrescrit;

    /**
     * Type.
     */
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TypeElementToCheck type;

    /**
     * Numéro de l'ordonnancier de fabrication/reconstitution.
     */
    @Column(name = "numOrdonnancier")
    private Integer numOrdonnancier;

    /**
     * Ordonnancier de fabrication / reconstitution.
     */
    @ManyToOne
    @JoinColumn(name = "id_ordonnancier")
    @Index(name = "idx_elementToCheck_ordon")
    private OrdonnancierFabReconst ordonnancier;

    /**
     * Getter sur nomChamps.
     * @return Retourne le nomChamps.
     */
    public String getNomChamps() {
        return this.nomChamps;
    }

    /**
     * Setter pour nomChamps.
     * @param nomChamps le nomChamps à écrire.
     */
    public void setNomChamps(final String nomChamps) {
        this.nomChamps = nomChamps;
    }

    /**
     * Getter sur checked.
     * @return Retourne le checked.
     */
    public Boolean getChecked() {
        return this.checked;
    }

    /**
     * Setter pour checked.
     * @param checked le checked à écrire.
     */
    public void setChecked(final Boolean checked) {
        this.checked = checked;
    }

    /**
     * Getter sur dispensation.
     * @return Retourne le dispensation.
     */
    public Dispensation getDispensation() {
        return this.dispensation;
    }

    /**
     * Setter pour dispensation.
     * @param dispensation le dispensation à écrire.
     */
    public void setDispensation(final Dispensation dispensation) {
        this.dispensation = dispensation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNom() {
        return this.getNomChamps();
    }

    /**
     * Getter sur produitPrescrit.
     * @return Retourne le produitPrescrit.
     */
    public ProduitPrescrit getProduitPrescrit() {
        return this.produitPrescrit;
    }

    /**
     * Setter pour produitPrescrit.
     * @param produitPrescrit le produitPrescrit à écrire.
     */
    public void setProduitPrescrit(final ProduitPrescrit produitPrescrit) {
        this.produitPrescrit = produitPrescrit;
    }

    /**
     * Getter sur commentaire.
     * @return Retourne le commentaire.
     */
    public String getCommentaire() {
        return this.commentaire;
    }

    /**
     * Setter pour commentaire.
     * @param commentaire le commentaire à écrire.
     */
    public void setCommentaire(final String commentaire) {
        this.commentaire = commentaire;
    }

    /**
     * Getter sur type.
     * @return Retourne le type.
     */
    public TypeElementToCheck getType() {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type le type à écrire.
     */
    public void setType(final TypeElementToCheck type) {
        this.type = type;
    }

    /**
     * Getter pour dateChecked.
     * @return Le dateChecked
     */
    public Calendar getDateChecked() {
        return this.dateChecked;
    }

    /**
     * Setter pour dateChecked.
     * @param dateChecked Le dateChecked à écrire.
     */
    public void setDateChecked(final Calendar dateChecked) {
        this.dateChecked = dateChecked;
    }

    /**
     * Getter pour checkedBy.
     * @return Le checkedBy
     */
    public Personne getCheckedBy() {
        return this.checkedBy;
    }

    /**
     * Setter pour checkedBy.
     * @param checkedBy Le checkedBy à écrire.
     */
    public void setCheckedBy(final Personne checkedBy) {
        this.checkedBy = checkedBy;
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
    public OrdonnancierFabReconst getOrdonnancier() {
        return this.ordonnancier;
    }

    /**
     * Setter pour ordonnancier.
     * @param ordonnancier Le ordonnancier à écrire.
     */
    public void setOrdonnancier(final OrdonnancierFabReconst ordonnancier) {
        this.ordonnancier = ordonnancier;
    }

}

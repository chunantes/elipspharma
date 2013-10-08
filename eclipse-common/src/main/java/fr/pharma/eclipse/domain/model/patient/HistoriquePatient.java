package fr.pharma.eclipse.domain.model.patient;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;

import fr.pharma.eclipse.domain.model.common.BeanObject;

/**
 * Historique de données patient.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "historique_patient")
public class HistoriquePatient extends BeanObject {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -2754231127754081162L;

    /**
     * Date de création de l'historique patient.
     */
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;

    /**
     * Poid.
     */
    @Column(name = "poid")
    private Double poid;

    /**
     * Taille.
     */
    @Column(name = "taille")
    private Double taille;

    /**
     * Surface corporelle.
     */
    @Column(name = "surfaceCorporelle")
    private Double surfaceCorporelle;

    /**
     * Commentaire.
     */
    @Column(name = "commentaire", columnDefinition = "TEXT")
    private String commentaire;

    /**
     * Patient.
     */
    @ManyToOne
    @JoinColumn(name = "id_patient", nullable = false)
    @Index(name = "idx_patient_historique_patient")
    @NotNull
    private Patient patient;

    /**
     * Getter sur date.
     * @return Retourne le date.
     */
    public Calendar getDate() {
        return this.date;
    }

    /**
     * Setter pour date.
     * @param date le date à écrire.
     */
    public void setDate(final Calendar date) {
        this.date = date;
    }

    /**
     * Getter sur poid.
     * @return Retourne le poid.
     */
    public Double getPoid() {
        return this.poid;
    }

    /**
     * Setter pour poid.
     * @param poid le poid à écrire.
     */
    public void setPoid(final Double poid) {
        this.poid = poid;
    }

    /**
     * Getter sur taille.
     * @return Retourne le taille.
     */
    public Double getTaille() {
        return this.taille;
    }

    /**
     * Setter pour taille.
     * @param taille le taille à écrire.
     */
    public void setTaille(final Double taille) {
        this.taille = taille;
    }

    /**
     * Getter sur surfaceCorporelle.
     * @return Retourne le surfaceCorporelle.
     */
    public Double getSurfaceCorporelle() {
        return this.surfaceCorporelle;
    }

    /**
     * Setter pour surfaceCorporelle.
     * @param surfaceCorporelle le surfaceCorporelle à écrire.
     */
    public void setSurfaceCorporelle(final Double surfaceCorporelle) {
        this.surfaceCorporelle = surfaceCorporelle;
    }

    /**
     * Getter sur patient.
     * @return Retourne le patient.
     */
    public Patient getPatient() {
        return this.patient;
    }

    /**
     * Setter pour patient.
     * @param patient le patient à écrire.
     */
    public void setPatient(final Patient patient) {
        this.patient = patient;
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

}

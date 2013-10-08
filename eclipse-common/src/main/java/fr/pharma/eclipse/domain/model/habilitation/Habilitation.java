package fr.pharma.eclipse.domain.model.habilitation;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.NotEmpty;

import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.essai.detail.contact.DetailContacts;

/**
 * Classe métier représentant une habilitation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "habilitation")
public class Habilitation extends BeanObject {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4625815658923476242L;

    /**
     * Personne.
     */
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE })
    @JoinColumn(name = "id_personne", nullable = false)
    @Index(name = "idx_personne_habilitation")
    @NotNull
    private Personne personne;

    /**
     * Détail auquel est rattachée l'habilitation.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detail_contacts", nullable = false)
    @Index(name = "idx_detail_contacts_habilitation")
    @NotNull
    private DetailContacts detailContacts;

    /**
     * Droit.
     */
    @Column(name = "droit")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Une habilitation ne contient pas de droit associé.")
    private Droit droit;

    /**
     * Habilitation active ou non.
     */
    @Column(name = "active")
    @NotNull
    private boolean active = Boolean.TRUE;

    /**
     * Indique si l'habilitation peut être désactivée.
     */
    @Column(name = "desactivable")
    @NotNull
    private boolean desactivable = Boolean.TRUE;

    /**
     * Date de création.
     */
    @Column(name = "creeLe")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Calendar dateCreation;

    /**
     * Auteur de la création.
     */
    @Column(name = "creePar")
    @NotNull
    @NotEmpty
    private String auteurCreation;

    /**
     * Date de désactivation.
     */
    @Column(name = "desactiveLe")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateDesactivation;

    /**
     * Auteur de la désactivation.
     */
    @Column(name = "desactivePar")
    private String auteurDesactivation;

    /**
     * Getter pour personne.
     * @return Le personne
     */
    public Personne getPersonne() {
        return this.personne;
    }

    /**
     * Setter pour personne.
     * @param personne Le personne à écrire.
     */
    public void setPersonne(final Personne personne) {
        this.personne = personne;
    }

    /**
     * Getter pour droit.
     * @return Le droit
     */
    public Droit getDroit() {
        return this.droit;
    }

    /**
     * Setter pour droit.
     * @param droit Le droit à écrire.
     */
    public void setDroit(final Droit droit) {
        this.droit = droit;
    }

    /**
     * Getter sur active.
     * @return Retourne le active.
     */
    public boolean isActive() {
        return this.active;
    }

    /**
     * Setter pour active.
     * @param active le active à écrire.
     */
    public void setActive(final boolean active) {
        this.active = active;
    }

    /**
     * Getter sur dateCreation.
     * @return Retourne le dateCreation.
     */
    public Calendar getDateCreation() {
        return this.dateCreation;
    }

    /**
     * Setter pour dateCreation.
     * @param dateCreation le dateCreation à écrire.
     */
    public void setDateCreation(final Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    /**
     * Getter sur auteurCreation.
     * @return Retourne le auteurCreation.
     */
    public String getAuteurCreation() {
        return this.auteurCreation;
    }

    /**
     * Setter pour auteurCreation.
     * @param auteurCreation le auteurCreation à écrire.
     */
    public void setAuteurCreation(final String auteurCreation) {
        this.auteurCreation = auteurCreation;
    }

    /**
     * Getter sur dateDesactivation.
     * @return Retourne le dateDesactivation.
     */
    public Calendar getDateDesactivation() {
        return this.dateDesactivation;
    }

    /**
     * Setter pour dateDesactivation.
     * @param dateDesactivation le dateDesactivation à écrire.
     */
    public void setDateDesactivation(final Calendar dateDesactivation) {
        this.dateDesactivation = dateDesactivation;
    }

    /**
     * Getter sur auteurDesactivation.
     * @return Retourne le auteurDesactivation.
     */
    public String getAuteurDesactivation() {
        return this.auteurDesactivation;
    }

    /**
     * Setter pour auteurDesactivation.
     * @param auteurDesactivation le auteurDesactivation à écrire.
     */
    public void setAuteurDesactivation(final String auteurDesactivation) {
        this.auteurDesactivation = auteurDesactivation;
    }

    /**
     * Getter sur detailContacts.
     * @return Retourne le detailContacts.
     */
    public DetailContacts getDetailContacts() {
        return this.detailContacts;
    }

    /**
     * Setter pour detailContacts.
     * @param detailContacts le detailContacts à écrire.
     */
    public void setDetailContacts(final DetailContacts detailContacts) {
        this.detailContacts = detailContacts;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("[");
        builder.append("id=").append(this.getId());
        builder.append(", droit=").append(this.droit);
        builder.append(", personne=").append(this.personne);
        return builder.append("]").toString();
    }

    /**
     * Getter sur desactivable.
     * @return Retourne le desactivable.
     */
    public boolean isDesactivable() {
        return this.desactivable;
    }

    /**
     * Setter pour desactivable.
     * @param desactivable le desactivable à écrire.
     */
    public void setDesactivable(final boolean desactivable) {
        this.desactivable = desactivable;
    }

}

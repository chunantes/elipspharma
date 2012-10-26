package fr.pharma.eclipse.domain.model.incident;

import java.util.Calendar;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.hibernate.validator.constraints.NotEmpty;

import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.model.common.BeanObjectSuivi;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.EssaiElement;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;
import fr.pharma.eclipse.domain.model.suivi.incident.IncidentSuivi;

/**
 * Bean métier représant un incident.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "incident")
public class Incident
    extends BeanObjectSuivi
    implements EssaiElement

{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6464980684968544458L;

    /**
     * Essai.
     */
    @ManyToOne(cascade =
    {CascadeType.REFRESH, CascadeType.MERGE })
    @JoinColumn(name = "id_essai", nullable = false)
    @Index(name = "idx_essai_incident")
    @NotNull
    private Essai essai;

    /**
     * Libellé.
     */
    @Column(name = "libelle")
    @NotNull
    @NotEmpty
    private String libelle;

    /**
     * Commentaire.
     */
    @Column(name = "commentaire", columnDefinition = "TEXT")
    private String commentaire;

    /**
     * Date de début.
     */
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Calendar date;

    /**
     * Liste des modifications de l'événement.
     */
    @OneToMany(mappedBy = "incident", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private final SortedSet<IncidentSuivi> modifs =
        new TreeSet<IncidentSuivi>(new SuiviComparator());

    /**
     * Getter pour libelle.
     * @return Le libelle
     */
    public String getLibelle()
    {
        return this.libelle;
    }

    /**
     * Setter pour libelle.
     * @param libelle Le libelle à écrire.
     */
    public void setLibelle(final String libelle)
    {
        this.libelle = libelle;
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
     * Getter pour dateDebut.
     * @return Le dateDebut
     */
    public Calendar getDate()
    {
        return this.date;
    }

    /**
     * Setter pour dateDebut.
     * @param dateDebut Le dateDebut à écrire.
     */
    public void setDate(final Calendar dateDebut)
    {
        this.date = dateDebut;
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
     * {@inheritDoc}
     */
    @Override
    public SortedSet<? extends Suivi> getModifs()
    {
        return this.modifs;
    }

}

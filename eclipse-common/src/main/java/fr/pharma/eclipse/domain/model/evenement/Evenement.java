package fr.pharma.eclipse.domain.model.evenement;

import java.util.Calendar;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.enums.RealisePar;
import fr.pharma.eclipse.domain.enums.evenement.ResultatVisite;
import fr.pharma.eclipse.domain.enums.evenement.TypeEvenement;
import fr.pharma.eclipse.domain.enums.evenement.TypeVisite;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.common.BeanObjectSuivi;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.EssaiElement;
import fr.pharma.eclipse.domain.model.suivi.evenement.EvenementSuivi;

/**
 * Bean métier représant un événement.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "evenement")
public class Evenement
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
    @JoinColumn(name = "id_essai")
    @Index(name = "idx_essai_evenement")
    private Essai essai;

    /**
     * Responsable.
     */
    @ManyToOne
    @JoinColumn(name = "id_personne")
    @Index(name = "idx_evenement_personne")
    private Personne responsable;

    /**
     * Nombre.
     */
    @Column(name = "nombre")
    private Integer nombre;

    /**
     * Type d'événement.
     */
    @Column(name = "typeEvenement")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TypeEvenement typeEvenement;

    /**
     * Type de la visite.
     */
    @Column(name = "typeVisite")
    @Enumerated(EnumType.STRING)
    private TypeVisite typeVisite;

    /**
     * Résultat de la visite.
     */
    @Column(name = "resultatVisite")
    @Enumerated(EnumType.STRING)
    private ResultatVisite resultatVisite;

    /**
     * Libellé.
     */
    @Column(name = "libelle")
    private String libelle;

    /**
     * Validation.
     */
    @Column(name = "validation")
    private String validation;

    /**
     * Réalisé par.
     */
    @Column(name = "realisePar")
    @Enumerated(EnumType.STRING)
    private RealisePar realisePar;

    /**
     * ARC.
     */
    @Column(name = "arc")
    private String arc;

    /**
     * Personnel Pharmacie.
     */
    @Column(name = "personnelPharmacie", columnDefinition = "TEXT")
    private String personnelPharmacie;

    /**
     * Commentaire.
     */
    @Column(name = "commentaire")
    private String commentaire;

    /**
     * Date de début.
     */
    @Column(name = "dateDebut")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Calendar dateDebut;

    /**
     * Date de fin.
     */
    @Column(name = "dateFin")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Calendar dateFin;

    /**
     * Heure de début.
     */
    @Column(name = "heureDebut")
    private String heureDebut;

    /**
     * Heure de début.
     */
    @Column(name = "heureFin")
    private String heureFin;

    /**
     * Evenement sur la journée.
     */
    @Column(name = "journee")
    private Boolean journee;

    /**
     * Date de réception.
     */
    @Column(name = "dateReception")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateReception;

    /**
     * Destinataire
     */
    @Column(name = "destinataire")
    private String destinataire;

    /**
     * Liste des modifications de l'événement.
     */
    @OneToMany(mappedBy = "evenement", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private final SortedSet<EvenementSuivi> modifs =
        new TreeSet<EvenementSuivi>(new SuiviComparator());

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
    public Calendar getDateDebut()
    {
        return this.dateDebut;
    }

    /**
     * Setter pour dateDebut.
     * @param dateDebut Le dateDebut à écrire.
     */
    public void setDateDebut(final Calendar dateDebut)
    {
        this.dateDebut = dateDebut;
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
     * Getter pour modifs.
     * @return Le modifs
     */
    @Override
    public SortedSet<EvenementSuivi> getModifs()
    {
        return this.modifs;
    }

    /**
     * Getter pour typeVisite.
     * @return Le typeVisite
     */
    public TypeVisite getTypeVisite()
    {
        return this.typeVisite;
    }

    /**
     * Setter pour typeVisite.
     * @param typeVisite Le typeVisite à écrire.
     */
    public void setTypeVisite(final TypeVisite typeVisite)
    {
        this.typeVisite = typeVisite;
    }

    /**
     * Getter pour resultatVisite.
     * @return Le resultatVisite
     */
    public ResultatVisite getResultatVisite()
    {
        return this.resultatVisite;
    }

    /**
     * Setter pour resultatVisite.
     * @param resultatVisite Le resultatVisite à écrire.
     */
    public void setResultatVisite(final ResultatVisite resultatVisite)
    {
        this.resultatVisite = resultatVisite;
    }

    /**
     * Getter pour typeEvenement.
     * @return Le typeEvenement
     */
    public TypeEvenement getTypeEvenement()
    {
        return this.typeEvenement;
    }

    /**
     * Setter pour typeEvenement.
     * @param typeEvenement Le typeEvenement à écrire.
     */
    public void setTypeEvenement(final TypeEvenement typeEvenement)
    {
        this.typeEvenement = typeEvenement;
    }

    /**
     * Getter pour heureDebut.
     * @return Le heureDebut
     */
    public String getHeureDebut()
    {
        return this.heureDebut;
    }

    /**
     * Setter pour heureDebut.
     * @param heureDebut Le heureDebut à écrire.
     */
    public void setHeureDebut(final String heureDebut)
    {
        this.heureDebut = heureDebut;
    }

    /**
     * Getter sur arc.
     * @return Retourne le arc.
     */
    public String getArc()
    {
        return this.arc;
    }

    /**
     * Getter sur personnelPharmacie.
     * @return Retourne le personnelPharmacie.
     */
    public String getPersonnelPharmacie()
    {
        return this.personnelPharmacie;
    }

    /**
     * Setter pour arc.
     * @param arc le arc à écrire.
     */
    public void setArc(final String arc)
    {
        this.arc = arc;
    }

    /**
     * Setter pour personnelPharmacie.
     * @param personnelPharmacie le personnelPharmacie à écrire.
     */
    public void setPersonnelPharmacie(final String personnelPharmacie)
    {
        this.personnelPharmacie = personnelPharmacie;
    }

    /**
     * Getter sur dateFin.
     * @return Retourne le dateFin.
     */
    public Calendar getDateFin()
    {
        return this.dateFin;
    }

    /**
     * Getter sur heureFin.
     * @return Retourne le heureFin.
     */
    public String getHeureFin()
    {
        return this.heureFin;
    }

    /**
     * Getter sur journee.
     * @return Retourne le journee.
     */
    public Boolean getJournee()
    {
        return this.journee;
    }

    /**
     * Setter pour dateFin.
     * @param dateFin le dateFin à écrire.
     */
    public void setDateFin(final Calendar dateFin)
    {
        this.dateFin = dateFin;
    }

    /**
     * Setter pour heureFin.
     * @param heureFin le heureFin à écrire.
     */
    public void setHeureFin(final String heureFin)
    {
        this.heureFin = heureFin;
    }

    /**
     * Setter pour journee.
     * @param journee le journee à écrire.
     */
    public void setJournee(final Boolean journee)
    {
        this.journee = journee;
    }

    /**
     * Getter pour responsable.
     * @return Le responsable
     */
    public Personne getResponsable()
    {
        return this.responsable;
    }

    /**
     * Setter pour responsable.
     * @param responsable Le responsable à écrire.
     */
    public void setResponsable(final Personne responsable)
    {
        this.responsable = responsable;
    }

    /**
     * Getter pour nombre.
     * @return Le nombre
     */
    public Integer getNombre()
    {
        return this.nombre;
    }

    /**
     * Setter pour nombre.
     * @param nombre Le nombre à écrire.
     */
    public void setNombre(final Integer nombre)
    {
        this.nombre = nombre;
    }

    /**
     * Getter pour validation.
     * @return Le validation
     */
    public String getValidation()
    {
        return this.validation;
    }

    /**
     * Setter pour validation.
     * @param validation Le validation à écrire.
     */
    public void setValidation(final String validation)
    {
        this.validation = validation;
    }

    /**
     * Getter pour realisePar.
     * @return Le realisePar
     */
    public RealisePar getRealisePar()
    {
        return this.realisePar;
    }

    /**
     * Setter pour realisePar.
     * @param realisePar Le realisePar à écrire.
     */
    public void setRealisePar(final RealisePar realisePar)
    {
        this.realisePar = realisePar;
    }

    /**
     * Getter pour dateReception.
     * @return Le dateReception
     */
    public Calendar getDateReception()
    {
        return this.dateReception;
    }

    /**
     * Setter pour dateReception.
     * @param dateReception Le dateReception à écrire.
     */
    public void setDateReception(final Calendar dateReception)
    {
        this.dateReception = dateReception;
    }

    /**
     * Getter pour destinataire.
     * @return Le destinataire
     */
    public String getDestinataire()
    {
        return this.destinataire;
    }

    /**
     * Setter pour destinataire.
     * @param destinataire Le destinataire à écrire.
     */
    public void setDestinataire(final String destinataire)
    {
        this.destinataire = destinataire;
    }

}

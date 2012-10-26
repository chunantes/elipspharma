package fr.pharma.eclipse.domain.model.essai.detail.administratif.embedded;

import java.io.Serializable;
import java.util.Calendar;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.CommentaireEssaiArchivage;

/**
 * Informations relatives à l'archivage sur le détail Administratif/Réglementaire d'un essai
 * clinique.
 
 * @version $Revision$ $Date$
 */
public class InfosArchivage
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2432275351486052947L;

    /**
     * Date d'archivage.
     */
    @Column(name = "arc_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;

    /**
     * Identification.
     */
    @Column(name = "arc_ident")
    private String identification = StringUtils.EMPTY;

    /**
     * Lieu.
     */
    @Column(name = "arc_lieu")
    private String lieu;

    /**
     * Durée (années).
     */
    @Column(name = "arc_duree")
    private Integer duree;

    /**
     * Commentaires.
     */
    @OneToMany(mappedBy = "detailAdministratif", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private SortedSet<CommentaireEssaiArchivage> commentaires =
        new TreeSet<CommentaireEssaiArchivage>(new SuiviComparator());

    /**
     * Getter sur date.
     * @return Retourne le date.
     */
    public Calendar getDate()
    {
        return this.date;
    }

    /**
     * Setter pour date.
     * @param date le date à écrire.
     */
    public void setDate(final Calendar date)
    {
        this.date = date;
    }

    /**
     * Getter sur identification.
     * @return Retourne le identification.
     */
    public String getIdentification()
    {
        return this.identification;
    }

    /**
     * Setter pour identification.
     * @param identification le identification à écrire.
     */
    public void setIdentification(final String identification)
    {
        this.identification = identification;
    }

    /**
     * Getter sur lieu.
     * @return Retourne le lieu.
     */
    public String getLieu()
    {
        return this.lieu;
    }

    /**
     * Setter pour lieu.
     * @param lieu le lieu à écrire.
     */
    public void setLieu(final String lieu)
    {
        this.lieu = lieu;
    }

    /**
     * Getter sur duree.
     * @return Retourne le duree.
     */
    public Integer getDuree()
    {
        return this.duree;
    }

    /**
     * Setter pour duree.
     * @param duree le duree à écrire.
     */
    public void setDuree(final Integer duree)
    {
        this.duree = duree;
    }

    /**
     * Getter sur commentaires.
     * @return Retourne le commentaires.
     */
    public SortedSet<CommentaireEssaiArchivage> getCommentaires()
    {
        return this.commentaires;
    }

    /**
     * Setter pour commentaires.
     * @param commentaires le commentaires à écrire.
     */
    public void setCommentaires(final SortedSet<CommentaireEssaiArchivage> commentaires)
    {
        this.commentaires = commentaires;
    }

}

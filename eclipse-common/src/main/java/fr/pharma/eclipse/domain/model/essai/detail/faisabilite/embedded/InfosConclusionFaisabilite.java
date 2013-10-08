package fr.pharma.eclipse.domain.model.essai.detail.faisabilite.embedded;

import java.io.Serializable;
import java.util.Calendar;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.hibernate.annotations.Where;

import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.model.essai.detail.faisabilite.CommentaireEssaiFaisabilite;

/**
 * Informations, de la partie détail de faisabilité de l'essai, relatives à la
 * conclusion de l'étude de faisabilité.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class InfosConclusionFaisabilite implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -167067922333695930L;

    /**
     * Etude de faisabilité favorable ou non.
     */
    @Column(name = "concl_favorable")
    private Boolean favorable;

    /**
     * Date de délibération.
     */
    @Column(name = "concl_date")
    private Calendar dateDeliberation;

    /**
     * Convention signée (promoteur externe/CHU) / promotion accordée (promotion
     * interne).
     */
    @Column(name = "concl_convSignee")
    private Boolean convSignee;

    /**
     * Liste des commentaires.
     */
    @OneToMany(mappedBy = "detailFaisabilite", cascade = CascadeType.ALL)
    @Where(clause = "type='FAISABILITE_CONCL'")
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private SortedSet<CommentaireEssaiFaisabilite> commentaires = new TreeSet<CommentaireEssaiFaisabilite>(new SuiviComparator());

    /**
     * Getter sur favorable.
     * @return Retourne le favorable.
     */
    public Boolean getFavorable() {
        return this.favorable;
    }

    /**
     * Setter pour favorable.
     * @param favorable le favorable à écrire.
     */
    public void setFavorable(final Boolean favorable) {
        this.favorable = favorable;
    }

    /**
     * Getter sur dateDeliberation.
     * @return Retourne le dateDeliberation.
     */
    public Calendar getDateDeliberation() {
        return this.dateDeliberation;
    }

    /**
     * Setter pour dateDeliberation.
     * @param dateDeliberation le dateDeliberation à écrire.
     */
    public void setDateDeliberation(final Calendar dateDeliberation) {
        this.dateDeliberation = dateDeliberation;
    }

    /**
     * Getter sur convSignee.
     * @return Retourne le convSignee.
     */
    public Boolean getConvSignee() {
        return this.convSignee;
    }

    /**
     * Setter pour convSignee.
     * @param convSignee le convSignee à écrire.
     */
    public void setConvSignee(final Boolean convSignee) {
        this.convSignee = convSignee;
    }

    /**
     * Setter pour commentaires.
     * @param commentaires le commentaires à écrire.
     */
    public void setCommentaires(final SortedSet<CommentaireEssaiFaisabilite> commentaires) {
        this.commentaires = commentaires;
    }

    /**
     * Getter sur commentaires.
     * @return Retourne le commentaires.
     */
    public SortedSet<CommentaireEssaiFaisabilite> getCommentaires() {
        return this.commentaires;
    }

}

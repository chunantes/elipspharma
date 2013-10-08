package fr.pharma.eclipse.domain.model.essai.detail.recherche;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.enums.NatureRecherche;
import fr.pharma.eclipse.domain.enums.ObjetRecherche;
import fr.pharma.eclipse.domain.enums.PhaseRecherche;
import fr.pharma.eclipse.domain.enums.Thematique;
import fr.pharma.eclipse.domain.enums.TypeRecherche;
import fr.pharma.eclipse.domain.model.common.BeanObjectSuivi;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.suivi.essai.detail.DetailRechercheSuivi;

/**
 * Classe métier représentant les informations de détail général d'un essai
 * clinique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_recherche")
public class DetailRecherche extends BeanObjectSuivi {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5575667739314928318L;

    /**
     * Essai auquel est rattaché le détail.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_essai")
    private Essai essai;

    /**
     * Titre du protocole.
     */
    @Column(name = "titreProtocole", columnDefinition = "TEXT")
    private String titreProtocole;

    /**
     * Numéro d'enregistrement (ou numéro SIGREC).
     */
    @Column(name = "numEnregistrement")
    private String numEnregistrement;

    /**
     * Type de recherche.
     */
    @Column(name = "typeRecherche")
    @Enumerated(EnumType.STRING)
    private TypeRecherche typeRecherche;

    /**
     * Objet de la recherche.
     */
    @Column(name = "objetRecherche")
    @Enumerated(EnumType.STRING)
    private ObjetRecherche objetRecherche;

    /**
     * Phase de la recherche.
     */
    @Column(name = "phaseRecherche")
    @Enumerated(EnumType.STRING)
    private PhaseRecherche phaseRecherche;

    /**
     * Nature de la recherche.
     */
    @Column(name = "natureRecherche")
    @Enumerated(EnumType.STRING)
    private NatureRecherche natureRecherche;

    /**
     * Thématique.
     */
    @Column(name = "thematique")
    @Enumerated(EnumType.STRING)
    private Thematique thematique;

    /**
     * Liste des mot-clés.
     */
    @Column(name = "motsCles")
    private String motsCles;

    /**
     * Liste des commentaires.
     */
    @OneToMany(mappedBy = "detailRecherche", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private SortedSet<CommentaireEssaiRecherche> commentaires = new TreeSet<CommentaireEssaiRecherche>(new SuiviComparator());

    /**
     * Liste des modifications du détail.
     */
    @OneToMany(mappedBy = "detailRecherche", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private SortedSet<DetailRechercheSuivi> modifs = new TreeSet<DetailRechercheSuivi>(new SuiviComparator());

    /**
     * {@inheritDoc}
     */
    @Override
    public SortedSet<DetailRechercheSuivi> getModifs() {
        return this.modifs;
    }

    /**
     * Getter sur titreProtocole.
     * @return Retourne le titreProtocole.
     */
    public String getTitreProtocole() {
        return this.titreProtocole;
    }

    /**
     * Setter pour titreProtocole.
     * @param titreProtocole le titreProtocole à écrire.
     */
    public void setTitreProtocole(final String titreProtocole) {
        this.titreProtocole = titreProtocole;
    }

    /**
     * Getter sur numEnregistrement.
     * @return Retourne le numEnregistrement.
     */
    public String getNumEnregistrement() {
        return this.numEnregistrement;
    }

    /**
     * Setter pour numEnregistrement.
     * @param numEnregistrement le numEnregistrement à écrire.
     */
    public void setNumEnregistrement(final String numEnregistrement) {
        this.numEnregistrement = numEnregistrement;
    }

    /**
     * Getter sur typeRecherche.
     * @return Retourne le typeRecherche.
     */
    public TypeRecherche getTypeRecherche() {
        return this.typeRecherche;
    }

    /**
     * Setter pour typeRecherche.
     * @param typeRecherche le typeRecherche à écrire.
     */
    public void setTypeRecherche(final TypeRecherche typeRecherche) {
        this.typeRecherche = typeRecherche;
    }

    /**
     * Getter sur objetRecherche.
     * @return Retourne le objetRecherche.
     */
    public ObjetRecherche getObjetRecherche() {
        return this.objetRecherche;
    }

    /**
     * Setter pour objetRecherche.
     * @param objetRecherche le objetRecherche à écrire.
     */
    public void setObjetRecherche(final ObjetRecherche objetRecherche) {
        this.objetRecherche = objetRecherche;
    }

    /**
     * Getter sur phaseRecherche.
     * @return Retourne le phaseRecherche.
     */
    public PhaseRecherche getPhaseRecherche() {
        return this.phaseRecherche;
    }

    /**
     * Setter pour phaseRecherche.
     * @param phaseRecherche le phaseRecherche à écrire.
     */
    public void setPhaseRecherche(final PhaseRecherche phaseRecherche) {
        this.phaseRecherche = phaseRecherche;
    }

    /**
     * Getter sur natureRecherche.
     * @return Retourne le natureRecherche.
     */
    public NatureRecherche getNatureRecherche() {
        return this.natureRecherche;
    }

    /**
     * Setter pour natureRecherche.
     * @param natureRecherche le natureRecherche à écrire.
     */
    public void setNatureRecherche(final NatureRecherche natureRecherche) {
        this.natureRecherche = natureRecherche;
    }

    /**
     * Getter sur thematique.
     * @return Retourne le thematique.
     */
    public Thematique getThematique() {
        return this.thematique;
    }

    /**
     * Setter pour thematique.
     * @param thematique le thematique à écrire.
     */
    public void setThematique(final Thematique thematique) {
        this.thematique = thematique;
    }

    /**
     * Getter sur motsCles.
     * @return Retourne le motsCles.
     */
    public String getMotsCles() {
        return this.motsCles;
    }

    /**
     * Setter pour motsCles.
     * @param motsCles le motsCles à écrire.
     */
    public void setMotsCles(final String motsCles) {
        this.motsCles = motsCles;
    }

    /**
     * Getter sur commentaires.
     * @return Retourne le commentaires.
     */
    public SortedSet<CommentaireEssaiRecherche> getCommentaires() {
        return this.commentaires;
    }

    /**
     * Setter pour commentaires.
     * @param commentaires le commentaires à écrire.
     */
    public void setCommentaires(final SortedSet<CommentaireEssaiRecherche> commentaires) {
        this.commentaires = commentaires;
    }

    /**
     * Getter sur essai.
     * @return Retourne le essai.
     */
    public Essai getEssai() {
        return this.essai;
    }

    /**
     * Setter pour essai.
     * @param essai le essai à écrire.
     */
    public void setEssai(final Essai essai) {
        this.essai = essai;
    }

    /**
     * Setter pour modifs.
     * @param modifs le modifs à écrire.
     */
    public void setModifs(final SortedSet<DetailRechercheSuivi> modifs) {
        this.modifs = modifs;
    }

}

package fr.pharma.eclipse.domain.model.essai.detail.surcout;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.hibernate.annotations.Where;

import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.model.common.BeanObjectSuivi;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.surcout.document.DocumentSurcouts;
import fr.pharma.eclipse.domain.model.suivi.essai.detail.DetailSurcoutSuivi;
import fr.pharma.eclipse.domain.model.surcout.DonneesPrevision;
import fr.pharma.eclipse.domain.model.surcout.Grille;

/**
 * Classe métier représentant les informations de détail des surcouts d'un essai
 * clinique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_surcout")
public class DetailSurcout extends BeanObjectSuivi {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -4555820200467933539L;

    /**
     * Essai auquel est rattaché le détail.
     */
    @OneToOne()
    @JoinColumn(name = "id_essai")
    private Essai essai;

    /**
     * Grille de surcout.
     */
    @OneToOne(mappedBy = "detailSurcout", fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL, orphanRemoval = true)
    private Grille grille;

    /**
     * Donnees prévisionnelles.
     */
    @OneToOne(mappedBy = "detailSurcout", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private DonneesPrevision donneesPrevision;

    /**
     * Documents previsionnels associés.
     */
    @OneToMany(mappedBy = "detailSurcout", cascade = CascadeType.ALL)
    @Where(clause = "type='PREVISIONNEL'")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private SortedSet<DocumentSurcouts> documentsPrevisionnels = new TreeSet<DocumentSurcouts>(new SuiviComparator());

    /**
     * Documents reels associés.
     */
    @OneToMany(mappedBy = "detailSurcout", cascade = CascadeType.ALL)
    @Where(clause = "type='REEL'")
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private SortedSet<DocumentSurcouts> documentsReels = new TreeSet<DocumentSurcouts>(new SuiviComparator());

    /**
     * Liste des modifications du détail.
     */
    @OneToMany(mappedBy = "detailSurcout", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private SortedSet<DetailSurcoutSuivi> modifs = new TreeSet<DetailSurcoutSuivi>(new SuiviComparator());

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
     * Getter sur modifs.
     * @return Retourne le modifs.
     */
    @Override
    public SortedSet<DetailSurcoutSuivi> getModifs() {
        return this.modifs;
    }

    /**
     * Setter pour modifs.
     * @param modifs le modifs à écrire.
     */
    public void setModifs(final SortedSet<DetailSurcoutSuivi> modifs) {
        this.modifs = modifs;
    }

    /**
     * Getter sur grille.
     * @return Retourne le grille.
     */
    public Grille getGrille() {
        return this.grille;
    }

    /**
     * Getter sur donneesPrevision.
     * @return Retourne le donneesPrevision.
     */
    public DonneesPrevision getDonneesPrevision() {
        return this.donneesPrevision;
    }

    /**
     * Setter pour donneesPrevision.
     * @param donneesPrevision le donneesPrevision à écrire.
     */
    public void setDonneesPrevision(final DonneesPrevision donneesPrevision) {
        this.donneesPrevision = donneesPrevision;
    }

    /**
     * Setter pour grille.
     * @param grille le grille à écrire.
     */
    public void setGrille(final Grille grille) {
        this.grille = grille;
    }

    /**
     * Getter sur documentsPrevisionnels.
     * @return Retourne le documentsPrevisionnels.
     */
    public SortedSet<DocumentSurcouts> getDocumentsPrevisionnels() {
        return this.documentsPrevisionnels;
    }

    /**
     * Setter pour documentsPrevisionnels.
     * @param documentsPrevisionnels le documentsPrevisionnels à écrire.
     */
    public void setDocumentsPrevisionnels(final SortedSet<DocumentSurcouts> documentsPrevisionnels) {
        this.documentsPrevisionnels = documentsPrevisionnels;
    }

    /**
     * Getter sur documentsReels.
     * @return Retourne le documentsReels.
     */
    public SortedSet<DocumentSurcouts> getDocumentsReels() {
        return this.documentsReels;
    }

    /**
     * Setter pour documentsReels.
     * @param documentsReels le documentsReels à écrire.
     */
    public void setDocumentsReels(final SortedSet<DocumentSurcouts> documentsReels) {
        this.documentsReels = documentsReels;
    }

}

package fr.pharma.eclipse.domain.model.essai.detail.autresdocs;

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
import fr.pharma.eclipse.domain.model.essai.detail.autresdocs.document.DocumentAutre;
import fr.pharma.eclipse.domain.model.suivi.essai.detail.DetailAutresDocumentsSuivi;

/**
 * Classe métier représentant les autres documents d'un essai clinique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "essai_detail_autres_documents")
public class DetailAutresDocuments extends BeanObjectSuivi {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8901925358885270535L;

    /**
     * Essai auquel est rattaché le détail.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_essai")
    private Essai essai;

    /**
     * Documents associés.
     */
    @OneToMany(mappedBy = "detailAutresDocuments", cascade = CascadeType.ALL)
    @Where(clause = "type='AUTRE'")
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private final SortedSet<DocumentAutre> documents = new TreeSet<DocumentAutre>(new SuiviComparator());

    /**
     * Liste des modifications du détail.
     */
    @OneToMany(mappedBy = "detailAutresDocuments", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private final SortedSet<DetailAutresDocumentsSuivi> modifs = new TreeSet<DetailAutresDocumentsSuivi>(new SuiviComparator());

    /**
     * Getter sur modifs.
     * @return Retourne le modifs.
     */
    @Override
    public SortedSet<DetailAutresDocumentsSuivi> getModifs() {
        return this.modifs;
    }

    /**
     * Getter sur documents.
     * @return Retourne le documents.
     */
    public SortedSet<DocumentAutre> getDocuments() {
        return this.documents;
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
}

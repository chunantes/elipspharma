package fr.pharma.eclipse.domain.model.essai.detail.administratif.embedded;

import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.hibernate.annotations.Where;

import fr.pharma.eclipse.comparator.document.DocumentBrochureComparator;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.document.DocumentAdministratif;

/**
 * Informations, de la partie détail de l'administratif de l'essai, relatives à la brochure
 * investigateur sur les produits.
 
 * @version $Revision$ $Date$
 */
public class InfosBrochureProduits
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5304064132101292736L;

    /**
     * Documents associés.
     */
    @OneToMany(mappedBy = "detailAdministratif", cascade = CascadeType.ALL)
    @Where(clause = "type='BROCHURE_PDUITS'")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = DocumentBrochureComparator.class)
    private SortedSet<DocumentAdministratif> documents =
        new TreeSet<DocumentAdministratif>(new DocumentBrochureComparator());

    /**
     * Booléen indiquant qu'il faut se référer au dossier papier<br>
     * pour consulter les documents.
     */
    @Column(name = "bropro_docsDossPapier")
    @NotNull
    private boolean docsDossierPapier = Boolean.FALSE;

    /**
     * Getter sur documents.
     * @return Retourne le documents.
     */
    public SortedSet<DocumentAdministratif> getDocuments()
    {
        return this.documents;
    }

    /**
     * Setter pour documents.
     * @param documents le documents à écrire.
     */
    public void setDocuments(final SortedSet<DocumentAdministratif> documents)
    {
        this.documents = documents;
    }

    /**
     * Getter sur docsDossierPapier.
     * @return Retourne le docsDossierPapier.
     */
    public boolean isDocsDossierPapier()
    {
        return this.docsDossierPapier;
    }

    /**
     * Setter pour docsDossierPapier.
     * @param docsDossierPapier le docsDossierPapier à écrire.
     */
    public void setDocsDossierPapier(final boolean docsDossierPapier)
    {
        this.docsDossierPapier = docsDossierPapier;
    }

}

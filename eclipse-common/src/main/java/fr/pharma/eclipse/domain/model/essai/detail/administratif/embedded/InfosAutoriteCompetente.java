package fr.pharma.eclipse.domain.model.essai.detail.administratif.embedded;

import java.io.Serializable;
import java.util.Calendar;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.hibernate.annotations.Where;

import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.enums.TypeAC;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.document.DocumentAdministratif;

/**
 * Informations relatives à l'autorité compétente sur le détail Administratif/Réglementaire d'un
 * essai clinique.
 
 * @version $Revision$ $Date$
 */
public class InfosAutoriteCompetente
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 8932329751943581566L;

    /**
     * Accord.
     */
    @Column(name = "ac_accord")
    private Boolean accordAC;

    /**
     * Autorité compétente.
     */
    @Column(name = "ac_type")
    @Enumerated(EnumType.STRING)
    private TypeAC typeAC;

    /**
     * Nom AC.
     */
    @Column(name = "ac_nom")
    // Initialisé pour instanciation objet embedded.
    private String nomAC = StringUtils.EMPTY;

    /**
     * N° identification Autorité Compétente.
     */
    @Column(name = "ac_numIdent")
    private String numIdentAC;

    /**
     * Date AC.
     */
    @Column(name = "ac_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateAC;

    /**
     * Documents associés.
     */
    @OneToMany(mappedBy = "detailAdministratif", cascade = CascadeType.ALL)
    @Where(clause = "type='AUTORITE_COMPETENTE'")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private SortedSet<DocumentAdministratif> documents =
        new TreeSet<DocumentAdministratif>(new SuiviComparator());

    /**
     * Booléen indiquant qu'il faut se référer au dossier papier<br>
     * pour consulter les documents.
     */
    @Column(name = "ac_docsDossPapier")
    @NotNull
    private boolean docsDossierPapier = Boolean.FALSE;

    /**
     * Getter sur accordAC.
     * @return Retourne le accordAC.
     */
    public Boolean getAccordAC()
    {
        return this.accordAC;
    }

    /**
     * Setter pour accordAC.
     * @param accordAC le accordAC à écrire.
     */
    public void setAccordAC(final Boolean accordAC)
    {
        this.accordAC = accordAC;
    }

    /**
     * Getter sur typeAC.
     * @return Retourne le typeAC.
     */
    public TypeAC getTypeAC()
    {
        return this.typeAC;
    }

    /**
     * Setter pour typeAC.
     * @param typeAC le typeAC à écrire.
     */
    public void setTypeAC(final TypeAC typeAC)
    {
        this.typeAC = typeAC;
    }

    /**
     * Getter sur nomAC.
     * @return Retourne le nomAC.
     */
    public String getNomAC()
    {
        return this.nomAC;
    }

    /**
     * Setter pour nomAC.
     * @param nomAC le nomAC à écrire.
     */
    public void setNomAC(final String nomAC)
    {
        this.nomAC = nomAC;
    }

    /**
     * Getter sur numIdentAC.
     * @return Retourne le numIdentAC.
     */
    public String getNumIdentAC()
    {
        return this.numIdentAC;
    }

    /**
     * Setter pour numIdentAC.
     * @param numIdentAC le numIdentAC à écrire.
     */
    public void setNumIdentAC(final String numIdentAC)
    {
        this.numIdentAC = numIdentAC;
    }

    /**
     * Getter sur dateAC.
     * @return Retourne le dateAC.
     */
    public Calendar getDateAC()
    {
        return this.dateAC;
    }

    /**
     * Setter pour dateAC.
     * @param dateAC le dateAC à écrire.
     */
    public void setDateAC(final Calendar dateAC)
    {
        this.dateAC = dateAC;
    }

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

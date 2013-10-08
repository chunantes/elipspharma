package fr.pharma.eclipse.domain.model.essai.detail.administratif.embedded;

import java.io.Serializable;
import java.util.Calendar;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.hibernate.annotations.Where;

import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.document.DocumentAdministratif;

/**
 * Informations relatives au comité de protection des personnes sur le détail
 * Administratif/Réglementaire d'un essai clinique.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class InfosComiteProtection implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -4136411833790622962L;

    /**
     * Accord.
     */
    @Column(name = "cpp_accord")
    private Boolean accordCPP;

    /**
     * Nom CPP.
     */
    @Column(name = "cpp_nom")
    // Initialisé pour instanciation objet embedded.
    private String nomCPP = StringUtils.EMPTY;

    /**
     * Date CPP.
     */
    @Column(name = "cpp_date")
    private Calendar dateCPP;

    /**
     * Documents associés.
     */
    @OneToMany(mappedBy = "detailAdministratif", cascade = CascadeType.ALL)
    @Where(clause = "type='COMITE_PROTEC_PERS'")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private SortedSet<DocumentAdministratif> documents = new TreeSet<DocumentAdministratif>(new SuiviComparator());

    /**
     * Booléen indiquant qu'il faut se référer au dossier papier<br>
     * pour consulter les documents.
     */
    @Column(name = "cpp_docsDossPapier")
    @NotNull
    private boolean docsDossierPapier = Boolean.FALSE;

    /**
     * Getter sur accordCPP.
     * @return Retourne le accordCPP.
     */
    public Boolean getAccordCPP() {
        return this.accordCPP;
    }

    /**
     * Setter pour accordCPP.
     * @param accordCPP le accordCPP à écrire.
     */
    public void setAccordCPP(final Boolean accordCPP) {
        this.accordCPP = accordCPP;
    }

    /**
     * Getter sur nomCPP.
     * @return Retourne le nomCPP.
     */
    public String getNomCPP() {
        return this.nomCPP;
    }

    /**
     * Setter pour nomCPP.
     * @param nomCPP le nomCPP à écrire.
     */
    public void setNomCPP(final String nomCPP) {
        this.nomCPP = nomCPP;
    }

    /**
     * Getter sur dateCPP.
     * @return Retourne le dateCPP.
     */
    public Calendar getDateCPP() {
        return this.dateCPP;
    }

    /**
     * Setter pour dateCPP.
     * @param dateCPP le dateCPP à écrire.
     */
    public void setDateCPP(final Calendar dateCPP) {
        this.dateCPP = dateCPP;
    }

    /**
     * Getter sur documents.
     * @return Retourne le documents.
     */
    public SortedSet<DocumentAdministratif> getDocuments() {
        return this.documents;
    }

    /**
     * Setter pour documents.
     * @param documents le documents à écrire.
     */
    public void setDocuments(final SortedSet<DocumentAdministratif> documents) {
        this.documents = documents;
    }

    /**
     * Getter sur docsDossierPapier.
     * @return Retourne le docsDossierPapier.
     */
    public boolean isDocsDossierPapier() {
        return this.docsDossierPapier;
    }

    /**
     * Setter pour docsDossierPapier.
     * @param docsDossierPapier le docsDossierPapier à écrire.
     */
    public void setDocsDossierPapier(final boolean docsDossierPapier) {
        this.docsDossierPapier = docsDossierPapier;
    }
}

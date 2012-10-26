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
 * Informations, de la partie détail de l'administratif de l'essai, relatives à l'assurance.
 
 * @version $Revision$ $Date$
 */
public class InfosAssurance
    implements Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -7853216204456433229L;

    /**
     * Numéro de la police d'assurance.
     */
    @Column(name = "assur_numero_contrat")
    // Initialisé pour instanciation objet embedded.
    private String numeroContrat = StringUtils.EMPTY;

    /**
     * Nom de la compagnie d'assurance.
     */
    @Column(name = "assur_nom_compagnie")
    private String nomCompagnie;

    /**
     * Code Postal de la compagnie d'assurance.
     */
    @Column(name = "assur_code_postal")
    private String codePostal;

    /**
     * Ville de la compagnie d'assurance.
     */
    @Column(name = "assur_ville")
    private String ville;

    /**
     * N° d'avenant d'assurance.
     */
    @Column(name = "assur_numero_avenant")
    private String numeroAvenant;

    /**
     * Date de début de validite.
     */
    @Column(name = "assur_date_debut_validite")
    private Calendar dateDebutValidite;

    /**
     * Date de fin de validite.
     */
    @Column(name = "assur_date_fin_validite")
    private Calendar dateFinValidite;

    /**
     * Documents associés.
     */
    @OneToMany(mappedBy = "detailAdministratif", cascade = CascadeType.ALL)
    @Where(clause = "type='ASSURANCE'")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private SortedSet<DocumentAdministratif> documents =
        new TreeSet<DocumentAdministratif>(new SuiviComparator());

    /**
     * Booléen indiquant qu'il faut se référer au dossier papier<br>
     * pour consulter les documents.
     */
    @Column(name = "assur_docsDossPapier")
    @NotNull
    private boolean docsDossierPapier = Boolean.FALSE;

    /**
     * Getter sur numeroContrat.
     * @return Retourne le numeroContrat.
     */
    public String getNumeroContrat()
    {
        return this.numeroContrat;
    }

    /**
     * Setter pour numeroContrat.
     * @param numeroContrat le numeroContrat à écrire.
     */
    public void setNumeroContrat(final String numeroContrat)
    {
        this.numeroContrat = numeroContrat;
    }

    /**
     * Getter sur nomCompagnie.
     * @return Retourne le nomCompagnie.
     */
    public String getNomCompagnie()
    {
        return this.nomCompagnie;
    }

    /**
     * Setter pour nomCompagnie.
     * @param nomCompagnie le nomCompagnie à écrire.
     */
    public void setNomCompagnie(final String nomCompagnie)
    {
        this.nomCompagnie = nomCompagnie;
    }

    /**
     * Getter sur codePostal.
     * @return Retourne le codePostal.
     */
    public String getCodePostal()
    {
        return this.codePostal;
    }

    /**
     * Setter pour codePostal.
     * @param codePostal le codePostal à écrire.
     */
    public void setCodePostal(final String codePostal)
    {
        this.codePostal = codePostal;
    }

    /**
     * Getter sur ville.
     * @return Retourne le ville.
     */
    public String getVille()
    {
        return this.ville;
    }

    /**
     * Setter pour ville.
     * @param ville le ville à écrire.
     */
    public void setVille(final String ville)
    {
        this.ville = ville;
    }

    /**
     * Getter sur dateDebutValidite.
     * @return Retourne le dateDebutValidite.
     */
    public Calendar getDateDebutValidite()
    {
        return this.dateDebutValidite;
    }

    /**
     * Setter pour dateDebutValidite.
     * @param dateDebutValidite le dateDebutValidite à écrire.
     */
    public void setDateDebutValidite(final Calendar dateDebutValidite)
    {
        this.dateDebutValidite = dateDebutValidite;
    }

    /**
     * Getter sur dateFinValidite.
     * @return Retourne le dateFinValidite.
     */
    public Calendar getDateFinValidite()
    {
        return this.dateFinValidite;
    }

    /**
     * Setter pour dateFinValidite.
     * @param dateFinValidite le dateFinValidite à écrire.
     */
    public void setDateFinValidite(final Calendar dateFinValidite)
    {
        this.dateFinValidite = dateFinValidite;
    }

    /**
     * Getter sur numeroAvenant.
     * @return Retourne le numeroAvenant.
     */
    public String getNumeroAvenant()
    {
        return this.numeroAvenant;
    }

    /**
     * Setter pour numeroAvenant.
     * @param numeroAvenant le numeroAvenant à écrire.
     */
    public void setNumeroAvenant(final String numeroAvenant)
    {
        this.numeroAvenant = numeroAvenant;
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

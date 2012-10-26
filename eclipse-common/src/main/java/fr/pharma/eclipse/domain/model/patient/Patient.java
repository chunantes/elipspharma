package fr.pharma.eclipse.domain.model.patient;

import java.util.Calendar;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import fr.pharma.eclipse.comparator.acteur.HistoriquePatientComparator;
import fr.pharma.eclipse.comparator.common.EclipseListComparator;
import fr.pharma.eclipse.comparator.inclusion.InclusionComparator;
import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.enums.Civilite;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.stock.RetourPatient;
import fr.pharma.eclipse.domain.model.suivi.patient.PatientSuivi;

/**
 * Classe métier représentant un patient.
 
 * @version $Revision$ $Date$
 */
@Entity(name = "patient")
public class Patient
    extends BeanObject
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -5950735046202266908L;

    /**
     * Numéro IPP.
     */
    @Column(name = "numeroIpp")
    @Pattern(regexp = "[0-9]+", message = "Le numéro IPP doit être un nombre.")
    private String numeroIpp;

    /**
     * Numéro IPP.
     */
    @Column(name = "numeroSejour")
    private String numeroSejour;

    /**
     * Civilite.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "civilite")
    private Civilite civilite;

    /**
     * Nom.
     */
    @Column(name = "nom")
    private String nom;

    /**
     * Nom de jeune fille.
     */
    @Column(name = "nomJeuneFille")
    private String nomJeuneFille;

    /**
     * Prenom.
     */
    @Column(name = "prenom")
    private String prenom;

    /**
     * Initiales calculées.
     */
    @Column(name = "initiales")
    private String initiales;

    /**
     * Adresse.
     */
    @Column(name = "adresse", columnDefinition = "TEXT")
    private String adresse;

    /**
     * Code postal.
     */
    @Column(name = "codePostal")
    private String codePostal;

    /**
     * Ville.
     */
    @Column(name = "ville")
    private String ville;

    /**
     * Téléphone.
     */
    @Column(name = "telephone")
    private String telephone;

    /**
     * Mail.
     */
    @Column(name = "mail")
    private String mail;

    /**
     * Date de naissance.
     */
    @Column(name = "dateNaissance")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dateNaissance;

    /**
     * Liste des inclusions dans des essais.
     */
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @Sort(type = SortType.COMPARATOR, comparator = InclusionComparator.class)
    private SortedSet<Inclusion> inclusions = new TreeSet<Inclusion>(new InclusionComparator());

    /**
     * Liste d'historique des donnees physio patient.
     */
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @Sort(type = SortType.COMPARATOR, comparator = HistoriquePatientComparator.class)
    private SortedSet<HistoriquePatient> historiquePatient =
        new TreeSet<HistoriquePatient>(new HistoriquePatientComparator());

    /**
     * Liste des inclusions dans des essais.
     */
    @OneToMany(mappedBy = "patient")
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = EclipseListComparator.class)
    private SortedSet<RetourPatient> retours =
        new TreeSet<RetourPatient>(new EclipseListComparator());

    /**
     * Liste des modifications du patient.
     */
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private SortedSet<PatientSuivi> modifs = new TreeSet<PatientSuivi>(new SuiviComparator());

    /**
     * Getter sur numeroIpp.
     * @return Retourne le numeroIpp.
     */
    public String getNumeroIpp()
    {
        return this.numeroIpp;
    }

    /**
     * Setter pour numeroIpp.
     * @param numeroIpp le numeroIpp à écrire.
     */
    public void setNumeroIpp(final String numeroIpp)
    {
        this.numeroIpp = numeroIpp;
    }

    /**
     * Getter sur nom.
     * @return Retourne le nom.
     */
    public String getNom()
    {
        return this.nom;
    }

    /**
     * Setter pour nom.
     * @param nom le nom à écrire.
     */
    public void setNom(final String nom)
    {
        this.nom = nom;
    }

    /**
     * Getter sur nomJeuneFille.
     * @return Retourne le nomJeuneFille.
     */
    public String getNomJeuneFille()
    {
        return this.nomJeuneFille;
    }

    /**
     * Setter pour nomJeuneFille.
     * @param nomJeuneFille le nomJeuneFille à écrire.
     */
    public void setNomJeuneFille(final String nomJeuneFille)
    {
        this.nomJeuneFille = nomJeuneFille;
    }

    /**
     * Getter sur prenom.
     * @return Retourne le prenom.
     */
    public String getPrenom()
    {
        return this.prenom;
    }

    /**
     * Setter pour prenom.
     * @param prenom le prenom à écrire.
     */
    public void setPrenom(final String prenom)
    {
        this.prenom = prenom;
    }

    /**
     * Getter sur adresse.
     * @return Retourne le adresse.
     */
    public String getAdresse()
    {
        return this.adresse;
    }

    /**
     * Setter pour adresse.
     * @param adresse le adresse à écrire.
     */
    public void setAdresse(final String adresse)
    {
        this.adresse = adresse;
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
     * Getter sur telephone.
     * @return Retourne le telephone.
     */
    public String getTelephone()
    {
        return this.telephone;
    }

    /**
     * Setter pour telephone.
     * @param telephone le telephone à écrire.
     */
    public void setTelephone(final String telephone)
    {
        this.telephone = telephone;
    }

    /**
     * Getter sur mail.
     * @return Retourne le mail.
     */
    public String getMail()
    {
        return this.mail;
    }

    /**
     * Setter pour mail.
     * @param mail le mail à écrire.
     */
    public void setMail(final String mail)
    {
        this.mail = mail;
    }

    /**
     * Getter sur historiquePatient.
     * @return Retourne le historiquePatient.
     */
    public SortedSet<HistoriquePatient> getHistoriquePatient()
    {
        return this.historiquePatient;
    }

    /**
     * Setter pour historiquePatient.
     * @param historiquePatient le historiquePatient à écrire.
     */
    public void setHistoriquePatient(final SortedSet<HistoriquePatient> historiquePatient)
    {
        this.historiquePatient = historiquePatient;
    }

    /**
     * Getter sur civilite.
     * @return Retourne le civilite.
     */
    public Civilite getCivilite()
    {
        return this.civilite;
    }

    /**
     * Setter pour civilite.
     * @param civilite le civilite à écrire.
     */
    public void setCivilite(final Civilite civilite)
    {
        this.civilite = civilite;
    }

    /**
     * Getter sur dateNaissance.
     * @return Retourne le dateNaissance.
     */
    public Calendar getDateNaissance()
    {
        return this.dateNaissance;
    }

    /**
     * Setter pour dateNaissance.
     * @param dateNaissance le dateNaissance à écrire.
     */
    public void setDateNaissance(final Calendar dateNaissance)
    {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Retourne la taille actuelle du patient sous forme de chaine de caractères.
     * @return La taille actuelle du patient sous forme de chaine de caractères.
     */
    public Double getTaille()
    {
        if (!this.historiquePatient.isEmpty())
        {
            return this.historiquePatient.first().getTaille();
        }
        else
        {
            return null;
        }
    }

    /**
     * Retourne le poid actuel du patient sous forme de chaine de caractères.
     * @return le poid actuel du patient sous forme de chaine de caractères.
     */
    public Double getPoid()
    {
        if (!this.historiquePatient.isEmpty())
        {
            return this.historiquePatient.first().getPoid();
        }
        else
        {
            return null;
        }
    }

    /**
     * Retourne la surface corporelle actuelle du patient sous forme de chaine de caractères.
     * @return La surface corporelle actuelle du patient sous forme de chaine de caractères.
     */
    public Double getSurface()
    {
        if (!this.historiquePatient.isEmpty())
        {
            return this.historiquePatient.first().getSurfaceCorporelle();
        }
        else
        {
            return null;
        }
    }

    /**
     * Getter sur modifs.
     * @return Retourne le modifs.
     */
    public SortedSet<PatientSuivi> getModifs()
    {
        return this.modifs;
    }

    /**
     * Setter pour modifs.
     * @param modifs le modifs à écrire.
     */
    public void setModifs(final SortedSet<PatientSuivi> modifs)
    {
        this.modifs = modifs;
    }

    /**
     * Getter sur inclusions.
     * @return Retourne le inclusions.
     */
    public SortedSet<Inclusion> getInclusions()
    {
        return this.inclusions;
    }

    /**
     * Setter pour inclusions.
     * @param inclusions le inclusions à écrire.
     */
    public void setInclusions(final SortedSet<Inclusion> inclusions)
    {
        this.inclusions = inclusions;
    }

    /**
     * Getter sur initiales.
     * @return Retourne le initiales.
     */
    public String getInitiales()
    {
        return this.initiales;
    }

    /**
     * Setter pour initiales.
     * @param initiales le initiales à écrire.
     */
    public void setInitiales(final String initiales)
    {
        this.initiales = initiales;
    }

    /**
     * Getter pour numeroSejour.
     * @return Le numeroSejour
     */
    public String getNumeroSejour()
    {
        return this.numeroSejour;
    }

    /**
     * Setter pour numeroSejour.
     * @param numeroSejour Le numeroSejour à écrire.
     */
    public void setNumeroSejour(final String numeroSejour)
    {
        this.numeroSejour = numeroSejour;
    }

    /**
     * Getter pour retours.
     * @return Le retours
     */
    public SortedSet<RetourPatient> getRetours()
    {
        return this.retours;
    }

    /**
     * Setter pour retours.
     * @param retours Le retours à écrire.
     */
    public void setRetours(final SortedSet<RetourPatient> retours)
    {
        this.retours = retours;
    }

}

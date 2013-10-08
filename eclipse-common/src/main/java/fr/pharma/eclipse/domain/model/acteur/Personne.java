package fr.pharma.eclipse.domain.model.acteur;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.hibernate.validator.constraints.NotEmpty;

import fr.pharma.eclipse.comparator.habilitation.HabilitationComparator;
import fr.pharma.eclipse.comparator.suivi.SuiviComparator;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.common.BeanObjectSuivi;
import fr.pharma.eclipse.domain.model.common.BeanWithNom;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.domain.model.suivi.acteur.PersonneSuivi;

/**
 * Classe métier représentant une Personne.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "personne")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Personne extends BeanObjectSuivi implements BeanWithNom {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8807832309559999474L;

    /**
     * Login de l'utilisateur.
     */
    @Column(name = "login")
    private String login;

    /**
     * Password.
     */
    @Column(name = "password")
    private String password;

    /**
     * Confirmation du password.
     */
    @Transient
    private String confirmPassword;

    /**
     * Nom.
     */
    @Column(name = "nom")
    @NotNull
    @NotEmpty
    private String nom;

    /**
     * Prénom.
     */
    @Column(name = "prenom")
    private String prenom;

    /**
     * Téléphone.
     */
    @Column(name = "telephone")
    private String telephone;

    /**
     * Téléphone portable.
     */
    @Column(name = "telephonePortable")
    private String telephonePortable;

    /**
     * Mail.
     */
    @Column(name = "mail")
    private String mail;

    /**
     * Fax.
     */
    @Column(name = "fax")
    private String fax;

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
     * Type de personne.
     */
    @Column(name = "type", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private TypePersonne type;

    /**
     * Booléen indiquant si la personne est administrateur.
     */
    private Boolean isAdmin = Boolean.FALSE;

    /**
     * Liste des habilitations.
     */
    @OneToMany(mappedBy = "personne", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = HabilitationComparator.class)
    private SortedSet<Habilitation> habilitations = new TreeSet<Habilitation>(new HabilitationComparator());

    /**
     * Liste des modifications de la personne.
     */
    @OneToMany(mappedBy = "personne", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.TRUE)
    @Sort(type = SortType.COMPARATOR, comparator = SuiviComparator.class)
    private final SortedSet<PersonneSuivi> modifs = new TreeSet<PersonneSuivi>(new SuiviComparator());

    /**
     * Getter sur nom.
     * @return Retourne le nom.
     */
    @Override
    public String getNom() {
        return this.nom;
    }

    /**
     * Setter pour nom.
     * @param nom le nom à écrire.
     */
    public void setNom(final String nom) {
        this.nom = nom;
    }

    /**
     * Getter sur prenom.
     * @return Retourne le prenom.
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Setter pour prenom.
     * @param prenom le prenom à écrire.
     */
    public void setPrenom(final String prenom) {
        this.prenom = prenom;
    }

    /**
     * Getter sur telephone.
     * @return Retourne le telephone.
     */
    public String getTelephone() {
        return this.telephone;
    }

    /**
     * Setter pour telephone.
     * @param telephone le telephone à écrire.
     */
    public void setTelephone(final String telephone) {
        this.telephone = telephone;
    }

    /**
     * Getter sur mail.
     * @return Retourne le mail.
     */
    public String getMail() {
        return this.mail;
    }

    /**
     * Setter pour mail.
     * @param mail le mail à écrire.
     */
    public void setMail(final String mail) {
        this.mail = mail;
    }

    /**
     * Getter sur fax.
     * @return Retourne le fax.
     */
    public String getFax() {
        return this.fax;
    }

    /**
     * Setter pour fax.
     * @param fax le fax à écrire.
     */
    public void setFax(final String fax) {
        this.fax = fax;
    }

    /**
     * Getter sur modifs.
     * @return Retourne le modifs.
     */
    @Override
    public SortedSet<PersonneSuivi> getModifs() {
        return this.modifs;
    }

    /**
     * Getter sur type.
     * @return Retourne le type.
     */
    public TypePersonne getType() {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type le type à écrire.
     */
    public void setType(final TypePersonne type) {
        this.type = type;
    }

    /**
     * Getter sur adresse.
     * @return Retourne le adresse.
     */
    public String getAdresse() {
        return this.adresse;
    }

    /**
     * Setter pour adresse.
     * @param adresse le adresse à écrire.
     */
    public void setAdresse(final String adresse) {
        this.adresse = adresse;
    }

    /**
     * Getter sur codePostal.
     * @return Retourne le codePostal.
     */
    public String getCodePostal() {
        return this.codePostal;
    }

    /**
     * Setter pour codePostal.
     * @param codePostal le codePostal à écrire.
     */
    public void setCodePostal(final String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * Getter sur ville.
     * @return Retourne le ville.
     */
    public String getVille() {
        return this.ville;
    }

    /**
     * Setter pour ville.
     * @param ville le ville à écrire.
     */
    public void setVille(final String ville) {
        this.ville = ville;
    }

    /**
     * Getter pour habilitations.
     * @return Le habilitations
     */
    public SortedSet<Habilitation> getHabilitations() {
        return this.habilitations;
    }

    /**
     * Setter pour habilitations.
     * @param habilitations Le habilitations à écrire.
     */
    public void setHabilitations(final SortedSet<Habilitation> habilitations) {
        this.habilitations = habilitations;
    }

    /**
     * Getter sur isAdmin.
     * @return Retourne le isAdmin.
     */
    public Boolean getIsAdmin() {
        return this.isAdmin;
    }

    /**
     * Setter pour isAdmin.
     * @param isAdmin le isAdmin à écrire.
     */
    public void setIsAdmin(final Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * Getter pour login.
     * @return Le login
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Setter pour login.
     * @param login Le login à écrire.
     */
    public void setLogin(final String login) {
        this.login = login;
    }

    /**
     * Getter pour password.
     * @return Le password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Setter pour password.
     * @param password Le password à écrire.
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Getter pour confirmPassword.
     * @return Le confirmPassword
     */
    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    /**
     * Setter pour confirmPassword.
     * @param confirmPassword Le confirmPassword à écrire.
     */
    public void setConfirmPassword(final String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("[");
        builder.append("id=").append(this.getId());
        builder.append(", ").append(this.prenom).append(" ").append(this.nom);
        builder.append(", type=").append(this.type);
        return builder.append("]").toString();
    }

    /**
     * Getter sur telephonePortable.
     * @return Retourne le telephonePortable.
     */
    public String getTelephonePortable() {
        return this.telephonePortable;
    }

    /**
     * Setter pour telephonePortable.
     * @param telephonePortable le telephonePortable à écrire.
     */
    public void setTelephonePortable(final String telephonePortable) {
        this.telephonePortable = telephonePortable;
    }

}

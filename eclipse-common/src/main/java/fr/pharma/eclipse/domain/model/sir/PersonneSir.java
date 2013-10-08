package fr.pharma.eclipse.domain.model.sir;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import fr.pharma.eclipse.domain.model.sir.common.BeanSirObject;

/**
 * Bean métier représentant une personne présente dans l'annuaire SIR.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "personne")
public class PersonneSir extends BeanSirObject {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1223506219425631198L;

    /**
     * Identifiant de la personne.
     */
    @Id
    @Column(name = "per_id")
    private Integer id;

    /**
     * Login.
     */
    @Column(name = "per_login")
    private String login;

    /**
     * Mot de passe.
     */
    @Column(name = "per_password")
    private String password;

    /**
     * Salt utilisé pour l'encryption du mot de passe.
     */
    @Column(name = "per_salt")
    private String salt;

    /**
     * Nom de la personne.
     */
    @Column(name = "per_nom")
    private String nom;

    /**
     * Prénom de la personne.
     */
    @Column(name = "per_prenom")
    private String prenom;

    /**
     * Mail.
     */
    @Column(name = "per_mail")
    private String mail;

    /**
     * Getter pour id.
     * @return Le id
     */
    @Override
    public Integer getId() {
        return this.id;
    }

    /**
     * Setter pour id.
     * @param id Le id à écrire.
     */
    @Override
    public void setId(final Integer id) {
        this.id = id;
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
     * Getter pour nom.
     * @return Le nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Setter pour nom.
     * @param nom Le nom à écrire.
     */
    public void setNom(final String nom) {
        this.nom = nom;
    }

    /**
     * Getter pour prenom.
     * @return Le prenom
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Setter pour prenom.
     * @param prenom Le prenom à écrire.
     */
    public void setPrenom(final String prenom) {
        this.prenom = prenom;
    }

    /**
     * Getter pour mail.
     * @return Le mail
     */
    public String getMail() {
        return this.mail;
    }

    /**
     * Setter pour mail.
     * @param mail Le mail à écrire.
     */
    public void setMail(final String mail) {
        this.mail = mail;
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
     * Getter pour salt.
     * @return Le salt
     */
    public String getSalt() {
        return this.salt;
    }

    /**
     * Setter pour salt.
     * @param salt Le salt à écrire.
     */
    public void setSalt(final String salt) {
        this.salt = salt;
    }

}

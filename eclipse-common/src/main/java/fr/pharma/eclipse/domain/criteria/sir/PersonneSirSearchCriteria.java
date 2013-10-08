package fr.pharma.eclipse.domain.criteria.sir;

import fr.pharma.eclipse.domain.criteria.common.AbstractSearchCriteria;

/**
 * Critère de recherche sur une Personne SIR.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class PersonneSirSearchCriteria extends AbstractSearchCriteria {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7363711356689819874L;

    /**
     * Login.
     */
    private String login;

    /**
     * Booléen indiquant si la recherche doit se faire de manière stricte
     * (égale) sur le login.
     */
    private Boolean strictSearchLogin;

    /**
     * Nom.
     */
    private String nom;

    /**
     * Prénom.
     */
    private String prenom;

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        this.setLogin(null);
        this.setNom(null);
        this.setPrenom(null);
        this.setStrictSearchLogin(null);
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
     * Getter sur nom.
     * @return Retourne le nom.
     */
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
     * Getter pour strictSearchLogin.
     * @return Le strictSearchLogin
     */
    public Boolean getStrictSearchLogin() {
        return this.strictSearchLogin;
    }

    /**
     * Setter pour strictSearchLogin.
     * @param strictSearchLogin Le strictSearchLogin à écrire.
     */
    public void setStrictSearchLogin(final Boolean strictSearchLogin) {
        this.strictSearchLogin = strictSearchLogin;
    }
}

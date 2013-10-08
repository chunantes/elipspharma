package fr.pharma.eclipse.domain.model.sigrec.acteur;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import fr.pharma.eclipse.domain.enums.Civilite;
import fr.pharma.eclipse.domain.model.common.BeanObject;

/**
 * Classe Contact du modèle métier Sigrec.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity(name = "contact_sigrec")
public class ContactSigrec extends BeanObject {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = 2613034116352092931L;

    /**
     * Nom.
     */
    @Column(name = "nom")
    private String nom;

    /**
     * Raison sociale.
     */
    @Column(name = "raisonSociale")
    private String raisonSociale;

    /**
     * Prenom.
     */
    @Column(name = "prenom")
    private String prenom;

    /**
     * Civilite.
     */
    @Column(name = "civilite")
    @Enumerated(EnumType.STRING)
    private Civilite civilite;

    /**
     * Code postal.
     */
    @Column(name = "codePostal")
    private String codePostal;

    /**
     * Adresse.
     */
    @Column(name = "adresse")
    private String adresse;

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
     * Fax.
     */
    @Column(name = "fax")
    private String fax;

    /**
     * Email.
     */
    @Column(name = "email")
    private String email;

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
     * Getter sur email.
     * @return Retourne le email.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter pour email.
     * @param email le email à écrire.
     */
    public void setEmail(final String email) {
        this.email = email;
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
     * Getter sur civilite.
     * @return Retourne le civilite.
     */
    public Civilite getCivilite() {
        return this.civilite;
    }

    /**
     * Setter pour civilite.
     * @param civilite le civilite à écrire.
     */
    public void setCivilite(final Civilite civilite) {
        this.civilite = civilite;
    }

    /**
     * Getter sur raisonSociale.
     * @return Retourne le raisonSociale.
     */
    public String getRaisonSociale() {
        return this.raisonSociale;
    }

    /**
     * Setter pour raisonSociale.
     * @param raisonSociale le raisonSociale à écrire.
     */
    public void setRaisonSociale(final String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

}

package fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.jasper.constants.JasperConstants;

/**
 * Classe représentant la source de données<br>
 * relative à un contact de l'essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanContact implements JasperReportBean {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8163702776902151650L;

    /**
     * Profil.
     */
    private String profil = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Habilitation.
     */
    private String habilitation = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Nom.
     */
    private String nom = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Prénom.
     */
    private String prenom = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Téléphone.
     */
    private String tel = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Mail.
     */
    private String mail = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Getter sur profil.
     * @return Retourne le profil.
     */
    public String getProfil() {
        return this.profil;
    }

    /**
     * Setter pour profil.
     * @param profil le profil à écrire.
     */
    public void setProfil(final String profil) {
        this.profil = profil;
    }

    /**
     * Getter sur habilitation.
     * @return Retourne le habilitation.
     */
    public String getHabilitation() {
        return this.habilitation;
    }

    /**
     * Setter pour habilitation.
     * @param habilitation le habilitation à écrire.
     */
    public void setHabilitation(final String habilitation) {
        this.habilitation = habilitation;
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
     * Getter sur tel.
     * @return Retourne le tel.
     */
    public String getTel() {
        return this.tel;
    }

    /**
     * Setter pour tel.
     * @param tel le tel à écrire.
     */
    public void setTel(final String tel) {
        this.tel = tel;
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

}

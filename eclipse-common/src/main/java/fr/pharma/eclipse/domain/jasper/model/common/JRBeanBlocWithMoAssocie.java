package fr.pharma.eclipse.domain.jasper.model.common;

import org.springframework.util.StringUtils;

import fr.pharma.eclipse.jasper.constants.JasperConstants;

/**
 * Classe représentant la source de données<br>
 * d'un bloc commun présentant un titre, une présence Oui/Non, un commentaire,<br>
 * et un document associé.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanBlocWithMoAssocie implements JasperReportBean {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5945160281978780930L;

    /**
     * Titre de l'acte pharmaceutique.
     */
    private String titre = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Présence.
     */
    private Boolean presence = Boolean.FALSE;

    /**
     * Indique s'il faut afficher la valeur du champ présence.
     */
    private boolean showPresence = true;

    /**
     * Flag pour indiquer si l'acte a un commentaire.
     */
    private Boolean hasCommentaire = Boolean.FALSE;

    /**
     * Flag pour indiquer si l'acte a un MO associé.
     */
    private Boolean hasMoAssocie = Boolean.FALSE;

    /**
     * Commentaire.
     */
    private String commentaire = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * MO associé.
     */
    private String moAssocie = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Getter sur titre.
     * @return Retourne le titre.
     */
    public String getTitre() {
        return this.titre;
    }

    /**
     * Setter pour titre.
     * @param titre le titre à écrire.
     */
    public void setTitre(final String titre) {
        this.titre = titre;
    }

    /**
     * Getter sur presence.
     * @return Retourne le presence.
     */
    public Boolean getPresence() {
        return this.presence;
    }

    /**
     * Setter pour presence.
     * @param presence le presence à écrire.
     */
    public void setPresence(final Boolean presence) {
        this.presence = presence;
    }

    /**
     * Getter sur commentaire.
     * @return Retourne le commentaire.
     */
    public String getCommentaire() {
        return this.commentaire;
    }

    /**
     * Setter pour commentaire.
     * @param commentaire le commentaire à écrire.
     */
    public void setCommentaire(final String commentaire) {
        this.commentaire = commentaire;
        this.hasCommentaire = StringUtils.hasText(commentaire);
    }

    /**
     * Getter sur moAssocie.
     * @return Retourne le moAssocie.
     */
    public String getMoAssocie() {
        return this.moAssocie;
    }

    /**
     * Setter pour moAssocie.
     * @param moAssocie le moAssocie à écrire.
     */
    public void setMoAssocie(final String moAssocie) {
        this.moAssocie = moAssocie;
        this.hasMoAssocie = StringUtils.hasText(moAssocie);
    }

    /**
     * Getter sur hasCommentaire.
     * @return Retourne le hasCommentaire.
     */
    public Boolean getHasCommentaire() {
        return this.hasCommentaire;
    }

    /**
     * Getter sur hasMoAssocie.
     * @return Retourne le hasMoAssocie.
     */
    public Boolean getHasMoAssocie() {
        return this.hasMoAssocie;
    }

    /**
     * Getter sur showPresence.
     * @return Retourne le showPresence.
     */
    public boolean getShowPresence() {
        return this.showPresence;
    }

    /**
     * Setter pour showPresence.
     * @param showPresence le showPresence à écrire.
     */
    public void setShowPresence(final boolean showPresence) {
        this.showPresence = showPresence;
    }

}

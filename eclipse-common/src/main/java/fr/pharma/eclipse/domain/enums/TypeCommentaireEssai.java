package fr.pharma.eclipse.domain.enums;

/**
 * Enumération des différents types de commentaires d'un essai.
 
 * @version $Revision$ $Date$
 */
public enum TypeCommentaireEssai
{
    /**
     * Commentaire du détail recherche.
     */
    RECHERCHE("detailRecherche.commentaires", "detailRecherche"),

    /**
     * Commentaire relatif à la conclusion de l'étude de faisabilité.
     */
    FAISABILITE_CONCL("detailFaisabilite.infosConclusion.commentaires", "detailFaisabilite"),

    /**
     * Commentaire relatif aux achats produits de l'étude de faisabilité.
     */
    FAISABILITE_ACHAT_PROD("detailFaisabilite.infosEtude.commentairesAchatsPUI",
            "detailFaisabilite"),

    /**
     * Commentaire relatif à la distribution possible à d'autres pharmacies de l'étude de
     * faisabilité.
     */
    FAISABILITE_DISTRIB_PHARMA("detailFaisabilite.infosEtude.commentairesDistribAutresPharma",
            "detailFaisabilite"),

    /**
     * Commentaire global sur l'étude de faisabilité de l'essai.
     */
    FAISABILITE_ETUDE("detailFaisabilite.infosEtude.commentaires", "detailFaisabilite"),

    /**
     * Commentaire concernant l'archivage sur le détail administratif/réglementaire.
     */
    ADMINREG_ARCHIVAGE("detailAdministratif.infosArchivage.commentaires", "detailAdministratif");

    /**
     * Propriété pour accéder à la liste des commentaires depuis le bean Essai.
     */
    private String commentairesPropertyFromEssai;

    /**
     * Propriété pour accéder au parent des commentaires depuis le bean Essai.
     */
    private String commentairesParentPropertyFromEssai;

    /**
     * Constructeur.
     * @param commentairesPropertyFromEssai Propriété pour accéder à la liste des commentaires
     * @param commentairesParentPropertyFromEssai Propriété pour accéder au parent des
     * commentaires depuis le bean Essai. depuis le bean Essai.
     */
    private TypeCommentaireEssai(
                                 final String commentairesPropertyFromEssai,
                                 final String commentairesParentPropertyFromEssai)
    {
        this.commentairesPropertyFromEssai = commentairesPropertyFromEssai;
        this.commentairesParentPropertyFromEssai = commentairesParentPropertyFromEssai;
    }

    /**
     * Getter sur commentairesPropertyFromEssai.
     * @return Retourne le commentairesPropertyFromEssai.
     */
    public String getCommentairesPropertyFromEssai()
    {
        return this.commentairesPropertyFromEssai;
    }

    /**
     * Getter sur commentairesParentPropertyFromEssai.
     * @return Retourne le commentairesParentPropertyFromEssai.
     */
    public String getCommentairesParentPropertyFromEssai()
    {
        return this.commentairesParentPropertyFromEssai;
    }
}

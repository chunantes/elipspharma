package fr.pharma.eclipse.domain.enums.document;

/**
 * Enumération des différents types de documents, relatifs à l'essai clinique,<br>
 * sauvegardés sur le disque via l'application.
 
 * @version $Revision$ $Date$
 */
public enum TypeDocumentEssai implements EnumTypeDocument
{
    /**
     * Document associé à l'autorité compétente dans le détail Administratif/Réglementaire de
     * l'essai clinique.
     */
    AUTORITE_COMPETENTE("ac", "detailAdministratif.infosAC.documents", "detailAdministratif"),

    /**
     * Document associé au comité de protection des personnes dans le détail<br>
     * Administratif/Réglementaire de l'essai clinique.
     */
    COMITE_PROTEC_PERS("cpp", "detailAdministratif.infosCPP.documents", "detailAdministratif"),

    /**
     * Document associé à la convention et ses avenants dans le détail<br>
     * Administratif/Réglementaire de l'essai clinique.
     */
    CONVENTION("avenants", "detailAdministratif.infosConvention.documents", "detailAdministratif"),

    /**
     * Document associé à l'assurance dans le détail<br>
     * Administratif/Réglementaire de l'essai clinique.
     */
    ASSURANCE("assu", "detailAdministratif.infosAssurance.documents", "detailAdministratif"),

    /**
     * Document associé au protocole dans le détail Administratif/Réglementaire de l'essai
     * clinique.
     */
    PROTOCOLE("proto", "detailAdministratif.infosProtocole.documents", "detailAdministratif"),

    /**
     * Document associé à la brochure investigateur sur les produits dans le détail
     * Administratif/Réglementaire de l'essai clinique.
     */
    BROCHURE_PDUITS("broPro", "detailAdministratif.infosBrochure.documents",
            "detailAdministratif"),

    /**
     * Document associé à la responsabilité de l'insu en cas de pharmacie dans le détail<br>
     * Donnees Pharma de l'essai clinique.
     */
    RESPONSABILITE_INSU("responsabiliteInsu",
            "detailDonneesPharma.infosComplementaires.documentResponsabiliteInsu",
            "detailDonneesPharma"),

    /**
     * Document associé à la modalite de reception des traitement dans le détail<br>
     * Donnees Pharma de l'essai clinique.
     */
    MODALITE_RECEPTION("modaliteReception",
            "detailDonneesPharma.infosComplementaires.documentModaliteReception",
            "detailDonneesPharma"),

    /**
     * Document associé à la modalite de Destruction des traitement dans le détail<br>
     * Donnees Pharma de l'essai clinique.
     */
    MODALITE_DESTRUCTION("modaliteDestruction",
            "detailDonneesPharma.infosComplementaires.documentModaliteDestruction",
            "detailDonneesPharma"),

    /**
     * Document associé à la responsabilité des commandes en cas de pharmacie dans le détail<br>
     * Donnees Pharma de l'essai clinique.
     */
    RESPONSABILITE_COMMANDE("responsabiliteCommande",
            "detailDonneesPharma.infosComplementaires.documentResponsabiliteCommande",
            "detailDonneesPharma"),

    /**
     * Document associé à la numerotation de conditionnement dans le détail<br>
     * Donnees Pharma de l'essai clinique.
     */
    NUMEROTATION_CONDITIONNEMENT("numerotationConditionnement",
            "detailDonneesPharma.infosDispensations.documentNumerotationConditionnement",
            "detailDonneesPharma"),

    /**
     * Document associé aux informations de conditionnements dans le détail<br>
     * Donnees Pharma de l'essai clinique.
     */
    INFORMATION_CONDITIONNEMENT("informationConditionnement",
            "detailDonneesPharma.infosDispensations.documentInformationConditionnement",
            "detailDonneesPharma"),

    /**
     * Document associé à la contre etiquette dans le détail<br>
     * Donnees Pharma de l'essai clinique.
     */
    CONTRE_ETIQUETTE("contreEtiquette",
            "detailDonneesPharma.infosDispensations.documentContreEtiquette",
            "detailDonneesPharma"),

    /**
     * Document associé aux conseils patients dans le détail<br>
     * Donnees Pharma de l'essai clinique.
     */
    CONSEIL_PATIENT("conseilPatient",
            "detailDonneesPharma.infosDispensations.documentConseilPatient",
            "detailDonneesPharma"),

    /**
     * Document associé à l'aide aux dispensations dans le détail<br>
     * Donnees Pharma de l'essai clinique.
     */
    AIDE_DISPENSATION("aideDispensation",
            "detailDonneesPharma.infosDispensations.documentAideDispensation",
            "detailDonneesPharma"),

    /**
     * Document associé à la responsabilité de la randomisation<br>
     * en cas de pharmacie dans le détail Donnees Pharma de l'essai clinique.
     */
    RESPONSABILITE_RANDOMISATION("responsabiliteRandomisation",
            "detailDonneesPharma.infosComplementaires.documentResponsabiliteRandomisation",
            "detailDonneesPharma"),

    /**
     * Document associé à l'autorisation d'importation dans le détail Administratif/Réglementaire
     * de l'essai clinique.
     */
    AUTORISATION_IMPORTATION("autorisationImportation",
            "detailAdministratif.infosAutorisationImportation.documents", "detailAdministratif"),

    /**
     * Document associé à l'autorisation de distribution dans le détail
     * Administratif/Réglementaire de l'essai clinique.
     */
    AUTORISATION_DISTRIBUTION("autorisationDistribution",
            "detailAdministratif.infosAutorisationDistribution.documents", "detailAdministratif"),

    /**
     * Document surcouts previsionnels.
     */
    PREVISIONNEL("previsionnel", "detailSurcout.documentsPrevisionnels", "detailSurcout"),

    /**
     * Document surcouts reels.
     */
    REEL("reel", "detailSurcout.documentsReels", "detailSurcout"),

    /**
     * Document autre.
     */
    AUTRE("autre", "detailAutresDocuments.documents", "detailAutresDocuments");

    /**
     * Chemin d'accès aux documents de ce type à partir du répertoire de l'essai.
     */
    private String repertoire;

    /**
     * Propriété pour accéder à la liste des documents depuis le bean Essai.
     */
    private String documentsPropertyFromEssai;

    /**
     * Propriété pour accéder au parent des documents depuis le bean Essai.
     */
    private String documentsParentPropertyFromEssai;

    /**
     * Constructeur.
     * @param repertoire Chemin d'accès aux documents de ce type à partir du répertoire de
     * l'essai.
     * @param documentsPropertyFromEssai Propriété pour accéder à la liste des documents depuis le
     * bean Essai.
     * @param documentsParentPropertyFromEssai Propriété pour accéder au parent des documents
     * depuis le bean Essai.
     */
    private TypeDocumentEssai(
                              final String repertoire,
                              final String documentsPropertyFromEssai,
                              final String documentsParentPropertyFromEssai)
    {
        this.repertoire = repertoire;
        this.documentsPropertyFromEssai = documentsPropertyFromEssai;
        this.documentsParentPropertyFromEssai = documentsParentPropertyFromEssai;
    }

    /**
     * Getter sur repertoire.
     * @return Retourne le repertoire.
     */
    public String getRepertoire()
    {
        return this.repertoire;
    }

    /**
     * Getter sur documentsPropertyFromEssai.
     * @return Retourne le documentsPropertyFromEssai.
     */
    public String getDocumentsPropertyFromEssai()
    {
        return this.documentsPropertyFromEssai;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypeDocumentEclipse getTypeEclipse()
    {
        return TypeDocumentEclipse.ESSAI;
    }

    /**
     * Getter sur documentsParentPropertyFromEssai.
     * @return Retourne le documentsParentPropertyFromEssai.
     */
    public String getDocumentsParentPropertyFromEssai()
    {
        return this.documentsParentPropertyFromEssai;
    }

}

package fr.pharma.eclipse.domain.enums.jasper;

/**
 * Enumération des différents types de rapports Jasper.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum TypeRapportJasper {
    /**
     * Rapport "Fiche d'information 'essais cliniques'" (CCTP n° 1).
     */
    FICHE_INFO_ESSAI("Fiche-essai", TypeExportJasper.DOC),

    /**
     * Rapport
     * "Fiche de gestion et d'aide à la dispensation d'un médicament en essai clinique"
     * (CCTP n°7).
     */
    FICHE_AIDE_DISPENSATION("Fiche-d'aide-à-la-dispensation", TypeExportJasper.DOC),

    /**
     * Rapport "Certificat de destruction"
     * http://intranet.netapsys.fr/jira/browse/PHARMA-275.
     */
    CERTIFICAT_DESTRUCTION("Certificat-destruction", TypeExportJasper.DOC),

    /**
     * Rapport "Accusé de réception"
     * http://intranet.netapsys.fr/jira/browse/PHARMA-274.
     */
    ACCUSE_RECEPTION("Accuse-reception", TypeExportJasper.DOC),

    /**
     * Rapport "Certificat de retour"
     * http://intranet.netapsys.fr/jira/browse/PHARMA-275.
     */
    CERTIFICAT_RETOUR("Certificat-retour", TypeExportJasper.DOC),

    /**
     * Rapport
     * "Prescription nominative de médicament en expérimentation clinique" (CCTP
     * n°8).
     */
    MODELE_PRESCRIPTION_NOMINATIVE("Modèle-prescription-nominative", TypeExportJasper.PDF);

    /**
     * Nom du rapport exporté.
     */
    private String reportName;

    /**
     * Type d'export du rapport.
     */
    private TypeExportJasper typeExport;

    /**
     * Constructeur privé.
     * @param reportName Nom du rapport exporté.
     * @param typeExport Type d'export du rapport.
     */
    private TypeRapportJasper(final String reportName, final TypeExportJasper typeExport) {
        this.reportName = reportName;
        this.typeExport = typeExport;
    }

    /**
     * Getter sur typeExport.
     * @return Retourne le typeExport.
     */
    public TypeExportJasper getTypeExport() {
        return this.typeExport;
    }

    /**
     * Getter sur reportName.
     * @return Retourne le reportName.
     */
    public String getReportName() {
        return this.reportName;
    }
}

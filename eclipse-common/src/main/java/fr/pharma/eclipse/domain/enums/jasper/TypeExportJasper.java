package fr.pharma.eclipse.domain.enums.jasper;

/**
 * Enumération des différents types de sorties de rapports Jasper.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum TypeExportJasper {
    /**
     * Export DOC.
     */
    DOC(".doc"),

    /**
     * Export PDF.
     */
    PDF(".pdf");

    /**
     * Extension des fichiers de ce type (avec le point).
     */
    private String extension;

    /**
     * Constructeur privé.
     * @param extension Extension des fichiers de ce type (avec le point).
     */
    private TypeExportJasper(final String extension) {
        this.extension = extension;
    }

    /**
     * Getter sur extension.
     * @return Retourne le extension.
     */
    public String getExtension() {
        return this.extension;
    }
}

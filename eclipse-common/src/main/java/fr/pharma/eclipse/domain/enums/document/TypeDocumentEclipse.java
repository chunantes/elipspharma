package fr.pharma.eclipse.domain.enums.document;

/**
 * Enumération des types de documents de l'application Eclipse.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum TypeDocumentEclipse {
    /**
     * Type des documents relatifs à l'essai.
     */
    ESSAI("essais"),

    /**
     * Type des documents relatifs au produit.
     */
    PRODUIT("produits"),

    /**
     * Type des documents relatifs aux pharmaciens.
     */
    PHARMACIEN("pharmaciens"),

    /**
     * Type des documents relatifs au mouvement de stock.
     */
    MVT_STOCK("mvtsStock");

    /**
     * Nom spécifique du répertoire de stockage de ces types de documents,
     * relatif à la base définie dans les propriétés de l'application.
     */
    private String repertoire;

    /**
     * Constructeur.
     * @param repertoire Nom spécifique du répertoire de stockage de ces types
     * de documents, relatif à la base définie dans les propriétés de
     * l'application.
     */
    private TypeDocumentEclipse(final String repertoire) {
        this.repertoire = repertoire;
    }

    /**
     * Getter sur repertoire.
     * @return Retourne le repertoire.
     */
    public String getRepertoire() {
        return this.repertoire;
    }
}

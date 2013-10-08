package fr.pharma.eclipse.domain.enums.document;

/**
 * Enumération des différents types de documents, relatifs à un mouvement de
 * stock, sauvegardés sur le disque via l'application.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum TypeDocumentStock implements EnumTypeDocument {
    /**
     * Document associé à un mouvement de stock de type Destruction.
     */
    DESTRUCTION("destruction", "documentDestruction"),

    /**
     * Document associé à un mouvement de stock de type Retour Promoteur.
     */
    RETOUR_PROMOTEUR("retourPromoteur", "documentRetourPromoteur"),

    /**
     * Document associé à un mouvement de stock de type Approvisionnement.
     */
    APPRO("appro", "documentAppro");

    /**
     * Chemin d'accès aux documents de ce type à partir du répertoire du
     * mouvement de stock.
     */
    private String repertoire;

    /**
     * Propriete du mouvement de stock représentant le document.
     */
    private String propriete;

    /**
     * Constructeur.
     * @param repertoire Chemin d'accès aux documents de ce type à partir du
     * répertoire du mouvement de stock.
     * @param propriete Propriété pour accéder au document depuis le mouvement
     * de stock.
     */
    private TypeDocumentStock(final String repertoire, final String propriete) {
        this.repertoire = repertoire;
        this.propriete = propriete;
    }

    /**
     * Getter sur repertoire.
     * @return Retourne le repertoire.
     */
    public String getRepertoire() {
        return this.repertoire;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TypeDocumentEclipse getTypeEclipse() {
        return TypeDocumentEclipse.MVT_STOCK;
    }

    /**
     * Getter sur propriete.
     * @return Retourne le propriete.
     */
    public String getPropriete() {
        return this.propriete;
    }

}

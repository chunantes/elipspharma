package fr.pharma.eclipse.domain.enums.document;

/**
 * Enumération représentant le type de document de brochure produits.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum TypeDocumentBrochure {
    /**
     * Brochure.
     */
    BROCHURE("Brochure"),

    /**
     * Avenant.
     */
    AVENANT("Avenant");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé.
     */
    TypeDocumentBrochure(final String libelle) {
        this.libelle = libelle;
    }

    /**
     * Getter pour libelle.
     * @return Retourne le libelle.
     */
    public String getLibelle() {
        return this.libelle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.getLibelle();
    }

}

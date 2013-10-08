package fr.pharma.eclipse.domain.enums.produit;

/**
 * Enumération représentant le type de détails stockage.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum TypeDetailStockage {
    /**
     * Retour.
     */
    RETOUR("Retour"),

    /**
     * Stock.
     */
    STOCK("Stock");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé du type de produit.
     */
    TypeDetailStockage(final String libelle) {
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

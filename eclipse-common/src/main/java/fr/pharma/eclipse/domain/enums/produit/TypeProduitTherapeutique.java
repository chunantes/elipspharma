package fr.pharma.eclipse.domain.enums.produit;

/**
 * Enumération représentant la nature d'un produit thérapeutique annexe.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum TypeProduitTherapeutique {
    /**
     * Alicament.
     */
    ALICAMENT("Alicament"),

    /**
     * Nutrition.
     */
    NUTRITION("Nutrition"),

    /**
     * Cosmétique.
     */
    COSMETIQUE("Cosmétique");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé de la nature du produit.
     */
    TypeProduitTherapeutique(final String libelle) {
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

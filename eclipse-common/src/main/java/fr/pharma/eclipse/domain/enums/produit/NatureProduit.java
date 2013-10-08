package fr.pharma.eclipse.domain.enums.produit;

/**
 * Enumération représentant la nature d'un produit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum NatureProduit {
    /**
     * Produit évalué.
     */
    PRODUIT_EVALUE("Produit évalué"),

    /**
     * Produit associé.
     */
    PRODUIT_ASSOCIE("Produit associé"),

    /**
     * Produit comparateur.
     */
    PRODUIT_COMPARATEUR("Produit comparateur");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé de la nature du produit.
     */
    NatureProduit(final String libelle) {
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

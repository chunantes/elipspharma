package fr.pharma.eclipse.domain.enums.stock;

/**
 * Enumération représentant les motifs de refus de réception de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum MotifRefus {
    /**
     * Problème de température.
     */
    PB_TEMPERATURE("Problème de température"),

    /**
     * Colis dégradé.
     */
    COLIS_DEGRADE("Colis dégradé");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé de la catégorie de mouvement.
     */
    MotifRefus(final String libelle) {
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

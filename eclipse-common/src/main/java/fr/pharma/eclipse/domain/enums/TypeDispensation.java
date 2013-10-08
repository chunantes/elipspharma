package fr.pharma.eclipse.domain.enums;

/**
 * Enumération représentant le Type de dispensation.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum TypeDispensation {
    /**
     * Globale.
     */
    GLOBALE("Globale"),

    /**
     * Nominative.
     */
    NOMINATIVE("Nominative");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé.
     */
    TypeDispensation(final String libelle) {
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

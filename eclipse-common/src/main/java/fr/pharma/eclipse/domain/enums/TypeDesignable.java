package fr.pharma.eclipse.domain.enums;

/**
 * Enumération représentant les types des éléments du design.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum TypeDesignable {
    /**
     * Bras.
     */
    BRAS("Bras"),

    /**
     * Sequence.
     */
    SEQUENCE("Sequence");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé.
     */
    TypeDesignable(final String libelle) {
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

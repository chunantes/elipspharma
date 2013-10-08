package fr.pharma.eclipse.domain.enums.surcout;

/**
 * Enumération des différentes règles de gestion possible concernant la grille
 * de surcouts.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum TypeRegle {
    /**
     * Première année.
     */
    ANNEES("Années"),

    /**
     * AND.
     */
    AND("And"),

    /**
     * OR.
     */
    OR("Or"),

    /**
     * NOT.
     */
    NOT("Not"),

    /**
     * Autres années.
     */
    AUTRES_ANNEES("Autres années");

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private TypeRegle(final String libelle) {
        this.libelle = libelle;
    }

    /**
     * Getter sur libelle.
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

package fr.pharma.eclipse.domain.enums;

/**
 * Enumération des différents phases de recherche.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum PhaseRecherche {
    /**
     * Phase I.
     */
    I("I"),

    /**
     * Phase IIa.
     */
    IIa("IIa"),

    /**
     * Phase IIb.
     */
    IIb("IIb"),

    /**
     * Phase III.
     */
    III("III"),

    /**
     * Phase IV.
     */
    IV("IV");

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private PhaseRecherche(final String libelle) {
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

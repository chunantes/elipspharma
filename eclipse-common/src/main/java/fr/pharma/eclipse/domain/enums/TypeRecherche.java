package fr.pharma.eclipse.domain.enums;

/**
 * Enumération des différents types de recherches.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum TypeRecherche {
    /**
     * Recherche biomédicale.
     */
    RECHERCHE_BIOMEDICALE("Biomédicale"),

    /**
     * Recherche sur collections biologiques.
     */
    COLLECTIONS_BIOLOGIQUES("Collections biologiques"),

    /**
     * Observationnelle.
     */
    OBSERVATIONNELLE("Observationnelle"),

    /**
     * Soins courants.
     */
    SOINS_COURANTS("Soins courants"),

    /**
     * Recherche sur données.
     */
    SUR_DONNEES("Sur données");

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private TypeRecherche(final String libelle) {
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

package fr.pharma.eclipse.domain.enums;

/**
 * Enumération pour les types de responsable de rélisation (ex: re etiquetage).
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum RealisePar {
    /**
     * Pharmacie).
     */
    PHARMACIE("Pharmacie"),

    /**
     * Promoteur.
     */
    PROMOTEUR("Promoteur");

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private RealisePar(final String libelle) {
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

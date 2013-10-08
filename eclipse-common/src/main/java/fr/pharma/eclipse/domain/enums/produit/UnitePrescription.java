package fr.pharma.eclipse.domain.enums.produit;

/**
 * Enumération représentant les unités de prescription.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum UnitePrescription {
    /**
     * Unité de gestion.
     */
    UNITE_GESTION("Unité de gestion"),

    /**
     * Forme.
     */
    FORME("Forme"),

    /**
     * Conditionnement primaire.
     */
    CONDITIONNEMENT_PRIMAIRE("Conditionnement primaire"),

    /**
     * Unitaire.
     */
    UNITAIRE("Unitaire"),

    /**
     * Dosage de l'UT.
     */
    DOSAGE("1 unité de dosage");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé de la nature du Dm.
     */
    UnitePrescription(final String libelle) {
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

package fr.pharma.eclipse.domain.enums;

/**
 * Enumération des différents objets de recherche.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum ObjetRecherche {
    /**
     * Alicament.
     */
    ALICAMENT("Alicament"),

    /**
     * Cosmétique.
     */
    COSMETIQUE("Cosmétique"),

    /**
     * Dispositif médical de diagnostic in vitro.
     */
    DM_DIAGNOSTIC_IN_VITRO("DM de diagnostic in vitro"),

    /**
     * Dispositif médical implantable actif.
     */
    DM_IMPLANTABLE_ACTIF("DM implantable actif"),

    /**
     * Dispositif médical autre.
     */
    DM_AUTRE("DM autre"),

    /**
     * Imagerie.
     */
    IMAGERIE("Imagerie"),

    /**
     * Médicament dérivé du sang.
     */
    MDS("MDS"),

    /**
     * Médicament.
     */
    MEDICAMENT("Médicament"),

    /**
     * Nutrition.
     */
    NUTRITION("Nutrition"),

    /**
     * Radiothérapie.
     */
    RADIOTHERAPIE("Radiothérapie"),

    /**
     * Thérapeutiques chirurgicales.
     */
    THERAPEUTIQUES_CHIR("Thérapeutiques chirurgicales"),

    /**
     * Thérapie cellulaire.
     */
    THERAPIE_CELLULAIRE("Thérapie cellulaire"),

    /**
     * Thérapie génique.
     */
    THERAPIE_GENIQUE("Thérapie génique"),

    /**
     * Autre.
     */
    AUTRE("Autre");

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private ObjetRecherche(final String libelle) {
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

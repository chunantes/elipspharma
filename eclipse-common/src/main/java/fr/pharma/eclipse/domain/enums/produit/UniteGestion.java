package fr.pharma.eclipse.domain.enums.produit;

/**
 * Enumération représentant les unités de gestion d'une prescription.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum UniteGestion {

    /**
     * Boite.
     */
    BOITE("Boîte", TypeUniteGestion.AUTRE),

    /**
     * Coffret.
     */
    COFFRET("Coffret", TypeUniteGestion.AUTRE),

    /**
     * Flacon.
     */
    FLACON("Flacon", TypeUniteGestion.VOLUME),

    /**
     * Seringue.
     */
    SERINGUE("Seringue", TypeUniteGestion.VOLUME),

    /**
     * Poche.
     */
    POCHE("Poche", TypeUniteGestion.VOLUME),

    /**
     * Cassette.
     */
    CASSETTE("Cassette", TypeUniteGestion.VOLUME),

    /**
     * Kit.
     */
    KIT("Kit", TypeUniteGestion.AUTRE),

    /**
     * Unitaire.
     */
    PILLULIER("Pilulier", TypeUniteGestion.AUTRE);

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Type d'untié de gestion.
     */
    private TypeUniteGestion type;

    /**
     * Constructeur.
     * @param libelle Le libellé de la nature du Dm.
     */
    UniteGestion(final String libelle, final TypeUniteGestion type) {
        this.libelle = libelle;
        this.type = type;
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

    /**
     * Getter pour type.
     * @return Le type
     */
    public TypeUniteGestion getType() {
        return this.type;
    }

    /**
     * Setter pour type.
     * @param type Le type à écrire.
     */
    public void setType(final TypeUniteGestion type) {
        this.type = type;
    }

}

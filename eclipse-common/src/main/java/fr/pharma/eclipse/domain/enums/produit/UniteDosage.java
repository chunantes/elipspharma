package fr.pharma.eclipse.domain.enums.produit;

/**
 * Enumération des différentes classes de Dispositifs médicaux.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum UniteDosage {
    /**
     * Comprimé.
     */
    COMPRIME("cp", "comprimé"),

    /**
     * Millilitre.
     */
    MILLILITRE("ml", "millilitre"),

    /**
     * Milligramme.
     */
    MILLIGRAMME("mg", "milligramme"),

    /**
     * Pourcentage.
     */
    POURCENTAGE("%", "%"),

    /**
     * Milligramme/milliLitre.
     */
    MILLIGRAMME_PAR_MILLILITRE("mg/mL", "milligramme/millilitre"),

    /**
     * UNIDOSE.
     */
    UNIDOSE("Unidose", "unidose"),

    /**
     * Gramme.
     */
    GRAMME("g", "gramme"),

    /**
     * Micro gramme.
     */
    MICROGRAMME("µg", "microgramme"),

    /**
     * Unités internationales.
     */
    UI("UI", "Unités internationales"),

    /**
     * MUI.
     */
    MUI("MUI", "MUI"),

    /**
     * GELULE.
     */
    GELULE("gélule", "gélule"),

    /**
     * Millicurie.
     */
    MILLICURIE("mCi", "millicurie"),

    /**
     * Milli becquerel.
     */
    MILLIBECQUEREL("mbecquerel", "millibecquerel");

    /**
     * Libellé.
     */
    private final String libelle;

    /**
     * Libellé long.
     */
    private String libelleLong;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private UniteDosage(final String libelle, final String libelleLong) {
        this.libelle = libelle;
        this.libelleLong = libelleLong;
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

    /**
     * Getter sur libelleLong.
     * @return Retourne le libelleLong.
     */
    public String getLibelleLong() {
        return this.libelleLong;
    }

    /**
     * Setter pour libelleLong.
     * @param libelleLong le libelleLong à écrire.
     */
    public void setLibelleLong(final String libelleLong) {
        this.libelleLong = libelleLong;
    }
}

package fr.pharma.eclipse.domain.enums.surcout;

/**
 * Enumération des modes de calcul.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum ModeCalcul {
    /**
     * Lot forfaitaire.
     */
    LOT_FORFAITAIRE("Lot forfaitaire"),

    /**
     * Lot forfaitaire par an.
     */
    LOT_FORFAITAIRE_AN("Lot forfaitaire / an (MEP)"),

    /**
     * Unités par an.
     */
    UNITE_AN("Unités / an (MEP)"),

    /**
     * Unité.
     */
    UNITE("Unité");

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private ModeCalcul(final String libelle) {
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

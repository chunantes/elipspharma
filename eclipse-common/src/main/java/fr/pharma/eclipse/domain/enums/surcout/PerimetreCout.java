package fr.pharma.eclipse.domain.enums.surcout;

/**
 * Enumération des perimetres de couts.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum PerimetreCout {
    /**
     * Patient.
     */
    PATIENT("Par patient"),

    /**
     * Essai.
     */
    ESSAI("Pour l'essai");

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private PerimetreCout(final String libelle) {
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

package fr.pharma.eclipse.domain.enums;

/**
 * Enumération représentant le type d'un retour patient.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum TypeRetour {
    /**
     * Plein.
     */
    PLEIN("Plein"),

    /**
     * Entamé.
     */
    ENTAME("Entamé"),

    /**
     * Vide.
     */
    VIDE("Vide");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé.
     */
    TypeRetour(final String libelle) {
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

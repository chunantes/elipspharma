package fr.pharma.eclipse.domain.enums;

/**
 * Enumération représentant l'état d'un retour.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum EtatRetour {
    /**
     * Présent en stock.
     */
    PRESENT("Présent en stock"),

    /**
     * Sortie du stock.
     */
    SORTI("Sorti du stock"),

    /**
     * Retourné au promoteur.
     */
    RETOURNE("Retourné au promoteur"),

    /**
     * Remis en stock.
     */
    REMIS("Remis en stock"),

    /**
     * Détruit.
     */
    DETRUIT("Détruit");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé.
     */
    EtatRetour(final String libelle) {
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

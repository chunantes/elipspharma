package fr.pharma.eclipse.domain.enums;

/**
 * Enumération représentant la civilite.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum Civilite {
    /**
     * Monsieur.
     */
    MR("M"),

    /**
     * Madame.
     */
    MME("Mme"),

    /**
     * Mademoiselle.
     */
    MLLE("Mlle");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé.
     */
    Civilite(final String libelle) {
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

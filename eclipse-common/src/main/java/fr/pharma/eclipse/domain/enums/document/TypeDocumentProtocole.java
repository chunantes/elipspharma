package fr.pharma.eclipse.domain.enums.document;

/**
 * Enumération représentant le type de design.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum TypeDocumentProtocole {
    /**
     * Protocole.
     */
    PROTOCOLE("Protocole"),

    /**
     * Amendement.
     */
    AMENDEMENT("Amendement");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé.
     */
    TypeDocumentProtocole(final String libelle) {
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

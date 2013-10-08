package fr.pharma.eclipse.domain.enums.document;

/**
 * Enumération des types de documents statiques.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum TypeDocumentStatique {
    /**
     * Lettre d'accusé réception pour les cessions PUI.
     */
    LETTRE_AR_CESSION("Lettre d'accusé réception pour les cessions PUI", "lettre_accuse_reception.doc"),

    /**
     * Document relatif aux cessions PUI.
     */
    CESSION_PUI("Document relatif aux la cession PUI", "cession_pui.doc");

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Nom.
     */
    private String nom;

    /**
     * Constructeur.
     * @param libelle Libellé.
     */
    private TypeDocumentStatique(final String libelle, final String nom) {
        this.libelle = libelle;
        this.nom = nom;
    }

    /**
     * Getter pour libelle.
     * @return Le libelle
     */
    public String getLibelle() {
        return this.libelle;
    }

    /**
     * Getter pour nom.
     * @return Le nom
     */
    public String getNom() {
        return this.nom;
    }
}

package fr.pharma.eclipse.domain.enums;

/**
 * Enumération représentant la responsabilité de la randomisation, de la levée
 * d'insu et de la commande de produit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum Responsabilite {
    /**
     * Pharmacie.
     */
    PHARMACIE("Pharmacie"),

    /**
     * Promoteur.
     */
    PROMOTEUR("Promoteur"),

    /**
     * Service.
     */
    SERVICE("Service"),

    /**
     * Simple aveugle.
     */
    INVESTIGATEUR("Investigateur");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé de la qualité de l'insu.
     */
    Responsabilite(final String libelle) {
        this.setLibelle(libelle);
    }

    /**
     * Setter pour libelle.
     * @param libelle le libelle à écrire.
     */
    public void setLibelle(final String libelle) {
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

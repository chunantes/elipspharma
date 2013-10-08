package fr.pharma.eclipse.domain.enums.produit;

/**
 * Enumération représentant le type de produit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum TypeProduit {
    /**
     * Médicament.
     */
    MEDICAMENT("Médicament", "medicaments"),

    /**
     * Dispositif médical.
     */
    DISPOSITIF_MEDICAL("Dispositif médical", "dispositifsMedicaux"),

    /**
     * Préparatifs.
     */
    PREPARATION("Préparation", "preparations"),

    /**
     * Produit thérapeutique.
     */
    PRODUIT_THERAPEUTIQUE("Produit thérapeutique", "produitsTherapeutiques");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Nom de la collection.
     */
    private String collection;

    /**
     * Constructeur.
     * @param libelle Le libellé du type de produit.
     */
    TypeProduit(final String libelle, final String collection) {
        this.libelle = libelle;
        this.collection = collection;
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
     * Getter pour collection.
     * @return Le collection
     */
    public String getCollection() {
        return this.collection;
    }

    /**
     * Setter pour collection.
     * @param collection Le collection à écrire.
     */
    public void setCollection(final String collection) {
        this.collection = collection;
    }

    /**
     * Setter pour libelle.
     * @param libelle Le libelle à écrire.
     */
    public void setLibelle(final String libelle) {
        this.libelle = libelle;
    }

}

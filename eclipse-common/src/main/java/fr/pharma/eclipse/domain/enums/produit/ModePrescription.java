package fr.pharma.eclipse.domain.enums.produit;

/**
 * Enumération représentant le mode de prescription d'un produit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum ModePrescription {
    /**
     * Numéro de traitement.
     */
    NUM_TRAITEMENT("N° de traitement"),

    /**
     * Forme galénique.
     */
    FORME_GALENIQUE("Forme galénique"),

    /**
     * Conditionnement primaire.
     */
    CONDITIONNEMENT_PRIMAIRE("Conditionnement primaire"),

    /**
     * Dose.
     */
    DOSE("Dose"),

    /**
     * Dose par kg.
     */
    DOSE_KG("Dose par kg"),

    /**
     * Dose par surface corporelle.
     */
    DOSE_SURFACE("Dose par surface corporelle");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé de la nature du Dm.
     */
    ModePrescription(final String libelle) {
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

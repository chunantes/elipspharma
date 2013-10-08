package fr.pharma.eclipse.domain.enums.alerte;

/**
 * Enumération représentant le type d'alerte.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum TypeAlerte {
    /**
     * Date de péremption du produit atteinte.
     */
    PRODUIT_PEREMPTION("Date de péremption"),

    /**
     * Quantité en stock du produit pa rapport au seuil.
     */
    STOCK_SEUIL("Stock seuil"),

    /**
     * Demandes de dotations à traiter.
     */
    DDES_DOTATIONS_A_TRAITER("Demandes de dotations"),

    /**
     * Résultat de visite non rempli le lendemain de la date de la visite.
     */
    RESULT_VISITE("Résultat de visite"),

    /**
     * Date de fin de l'essai arrivé.
     */
    DATE_FIN_ESSAI_ATTEINT("Date de fin d'essai"),

    /**
     * Cession de produit ( date du jour - date d'envoi > 5jours).
     */
    DATE_CESSION_PUI("Cession PUI"),

    /**
     * Destruction d'un essai 15 ans après la date de la visite de clotûre.
     */
    DESTRUCTION_ESSAI("Destruction d'un essai");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé.
     */
    TypeAlerte(final String libelle) {
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

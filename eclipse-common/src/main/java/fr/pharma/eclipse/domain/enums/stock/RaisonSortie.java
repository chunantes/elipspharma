package fr.pharma.eclipse.domain.enums.stock;

/**
 * Enumération représentant la raison d'une sortie.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum RaisonSortie {
    /**
     * Fin de l'étude.
     */
    FIN_ETUDE("Fin d'étude"),

    /**
     * Mise en quarantaine : périmé.
     */
    QUARANTAINE_PERIME("Mise en quarantaine : périmé"),

    /**
     * Mise en quarantaine : retrait de lot.
     */
    QUARANTAINE_RETRAIT("Mise en quarantaine : retrait de lot"),

    /**
     * Sortie patient.
     */
    SORTIE_PATIENT("Sortie du patient de l'étude"),

    /**
     * Autre.
     */
    AUTRE("Autre");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé du type de mouvement.
     * @param categorie La catégorie du type de mouvement.
     */
    RaisonSortie(final String libelle) {
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

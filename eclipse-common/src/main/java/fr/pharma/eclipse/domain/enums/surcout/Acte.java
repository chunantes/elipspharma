package fr.pharma.eclipse.domain.enums.surcout;

/**
 * Enumération contenant les différents actes pris en compte automatiquement
 * dans les surcouts.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum Acte {
    AUCUN("Aucun"), DESTRUCTION("Destruction"), DISPENSATION("Dispensation nominative : nouvelle"), DISPENSATION_RENOUVELLEMENT("Dispensation nominative : renouvellement"),
    PRESCRIPTION("Prescription"), CONDITION_CONSERVATION("Conditions de conservation"), TRACABILITE("Traçabilité"), VISITE_MONITORING("Visite de monitoring"), REETIQUETAGE(
            "Ré-étiquetage"), APPROVISIONNEMENT("Approvisionnement"), PREPARATIONS_STERILES("Préparations stériles"), PREPARATIONS_NON_STERILES("Préparations non stériles"),
    AUDIT("Type de visite \"Audit\" (interne ou externe)");

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private Acte(final String libelle) {
        this.libelle = libelle;
    }

    /**
     * Getter sur libelle.
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

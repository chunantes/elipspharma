package fr.pharma.eclipse.domain.enums.stock;

/**
 * Enumération représentant le type de mouvement de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum TypeMvtStock {
    /**
     * Approvisionnement.
     */
    APPROVISIONNEMENT("Approvisionnement"),

    /**
     * Entrée corrective : Mouvement d'entrée permettant de corriger des erreurs
     * de stocks. Il n'y a pas de réception de produits, par conséquent la
     * facturation et le calcul d'indicateurs ne sont pas impactés.
     */
    ENTREE_CORRECTIVE("Entrée Corrective"),

    /**
     * Cession PUI.
     */
    CESSION_PUI("Cession PUI"),

    /**
     * Dispensation.
     */
    DISPENSATION("Dispensation"),

    /**
     * Destruction.
     */
    DESTRUCTION("Destruction"),

    /**
     * Dotation.
     */
    DOTATION("Dotation"),

    /**
     * Retour promoteur.
     */
    RETOUR_PROMOTEUR("Retour Promoteur"),

    /**
     * Retour promoteur.
     */
    PREPARATION_SORTIE("Sortie pour préparation"),

    /**
     * Retour promoteur.
     */
    PREPARATION_ENTREE("Entrée de préparation"),

    /**
     * Autre sortie.
     */
    AUTRE_SORTIE("Autre Sortie");

    // Les groupes de mouvement par type (les entrees de stock, les sorties de
    // stock, ...)
    public static final TypeMvtStock[] ENTREES = {APPROVISIONNEMENT, ENTREE_CORRECTIVE, PREPARATION_ENTREE };
    public static final TypeMvtStock[] SORTIES = {CESSION_PUI, DESTRUCTION, RETOUR_PROMOTEUR, PREPARATION_SORTIE, AUTRE_SORTIE };
    public static final TypeMvtStock[] SORTIES_SANS_PREPARATION = {CESSION_PUI, DESTRUCTION, RETOUR_PROMOTEUR, AUTRE_SORTIE };
    public static final TypeMvtStock[] ALL_SORTIES = {DISPENSATION, DOTATION, CESSION_PUI, DESTRUCTION, RETOUR_PROMOTEUR, PREPARATION_SORTIE, AUTRE_SORTIE };

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé du type de mouvement.
     */
    TypeMvtStock(final String libelle) {
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

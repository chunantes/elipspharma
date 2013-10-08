package fr.pharma.eclipse.domain.enums;

/**
 * Enumération des différents historiques d'un produit.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum TypeHistoriqueProduit {
    /**
     * Historique général.
     */
    GENERAL(-1, "modifs", null),

    /**
     * Historique de l'onglet Informations générales.
     */
    ONG_GENERALES(0, "", ""),

    /**
     * Historique de l'onglet Prescription.
     */
    ONG_PRESCRIPTION(1, "", ""),

    /**
     * Historique de l'onglet Actes pharmaceutiques.
     */
    ONG_ACTES(2, "", ""),

    /**
     * Historique de l'onglet Logistique.
     */
    ONG_LOGISTIQUE(3, "", "");

    /**
     * Index de l'onglet correspondant dans l'IHM.
     */
    private int indexIHM;

    /**
     * Propriété pour accéder à l'ensemble des modifications par introspection
     * depuis un bean Produit.
     */
    private String modifsPropertyFromProduit;

    /**
     * Propriété pour accéder au bean parent des modifications par introspection
     * depuis un bean Produit.
     */
    private String modifsParentPropertyFromProduit;

    /**
     * Constructeur.
     * @param indexIHM Index de l'onglet correspondant dans l'IHM.
     * @param modifsPropertyFromProduit Propriété pour accéder à l'ensemble des
     * modifications par introspection depuis un bean Produit.
     * @param modifsParentPropertyFromProduit Propriété pour accéder au bean
     * parent des modifications par introspection depuis un bean Produit.
     */
    private TypeHistoriqueProduit(final int indexIHM, final String modifsPropertyFromProduit, final String modifsParentPropertyFromProduit) {
        this.indexIHM = indexIHM;
        this.modifsPropertyFromProduit = modifsPropertyFromProduit;
        this.modifsParentPropertyFromProduit = modifsParentPropertyFromProduit;
    }

    /**
     * Getter sur modifsPropertyFromProduit.
     * @return Retourne le modifsPropertyFromProduit.
     */
    public String getModifsPropertyFromProduit() {
        return this.modifsPropertyFromProduit;
    }

    /**
     * Getter sur modifsParentPropertyFromProduit.
     * @return Retourne le modifsParentPropertyFromProduit.
     */
    public String getModifsParentPropertyFromProduit() {
        return this.modifsParentPropertyFromProduit;
    }

    /**
     * Getter sur indexIHM.
     * @return Retourne le indexIHM.
     */
    public int getIndexIHM() {
        return this.indexIHM;
    }
}

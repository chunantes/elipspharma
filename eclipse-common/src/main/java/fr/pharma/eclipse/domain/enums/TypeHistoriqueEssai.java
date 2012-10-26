package fr.pharma.eclipse.domain.enums;

/**
 * Enumération des différents historiques d'un essai.
 
 * @version $Revision$ $Date$
 */
public enum TypeHistoriqueEssai
{
    /**
     * Historique général.
     */
    GENERAL(-1, "modifs", null),

    /**
     * Historique de l'onglet Recherche.
     */
    ONG_RECHERCHE(0, "detailRecherche.modifs", "detailRecherche"),

    /**
     * Historique de l'onglet Contacts.
     */
    ONG_CONTACTS(1, "detailContacts.modifs", "detailContacts"),

    /**
     * Historique de l'onglet Etude de faisabilité.
     */
    ONG_FAISABILITE(2, "detailFaisabilite.modifs", "detailFaisabilite"),

    /**
     * Historique de l'onglet Dates.
     */
    ONG_DATES(3, "detailDates.modifs", "detailDates"),

    /**
     * Historique de l'onglet Administratif/Réglementaire.
     */
    ONG_ADMIN_REG(4, "detailAdministratif.modifs", "detailAdministratif"),

    /**
     * Historique de l'onglet Donnees pharma.
     */
    ONG_DATA_PHARMA(5, "detailDonneesPharma.modifs", "detailDonneesPharma"),

    /**
     * Historique de l'onglet Produits.
     */
    ONG_PRODUITS(6, "detailProduit.modifs", "detailProduit"),

    /**
     * Historique de l'onglet Design.
     */
    ONG_DESIGN(7, "detailDesign.modifs", "detailDesign"),

    /**
     * Historique de l'onglet Autres Documents.
     */
    ONG_AUTRES_DOCS(8, "detailAutresDocuments.modifs", "detailAutresDocuments"),

    /**
     * Historique de l'onglet Autres Documents.
     */
    ONG_SURCOUTS(9, "detailSurcout.modifs", "detailSurcout"),

    /**
     * Historique de l'onglet Evenements.
     */
    ONG_EVTS(10, "", "");

    /**
     * Index de l'onglet correspondant dans l'IHM.
     */
    private int indexIHM;

    /**
     * Propriété pour accéder à l'ensemble des modifications par introspection depuis un bean
     * Essai.
     */
    private String modifsPropertyFromEssai;

    /**
     * Propriété pour accéder au bean parent des modifications par introspection depuis un bean
     * Essai.
     */
    private String modifsParentPropertyFromEssai;

    /**
     * Constructeur.
     * @param indexIHM Index de l'onglet correspondant dans l'IHM.
     * @param modifsPropertyFromEssai Propriété pour accéder à l'ensemble des modifications par
     * introspection depuis un bean Essai.
     * @param modifsParentPropertyFromEssai Propriété pour accéder au bean parent des
     * modifications par introspection depuis un bean Essai.
     */
    private TypeHistoriqueEssai(
                                final int indexIHM,
                                final String modifsPropertyFromEssai,
                                final String modifsParentPropertyFromEssai)
    {
        this.indexIHM = indexIHM;
        this.modifsPropertyFromEssai = modifsPropertyFromEssai;
        this.modifsParentPropertyFromEssai = modifsParentPropertyFromEssai;
    }

    /**
     * Getter sur modifsPropertyFromEssai.
     * @return Retourne le modifsPropertyFromEssai.
     */
    public String getModifsPropertyFromEssai()
    {
        return this.modifsPropertyFromEssai;
    }

    /**
     * Getter sur modifsParentPropertyFromEssai.
     * @return Retourne le modifsParentPropertyFromEssai.
     */
    public String getModifsParentPropertyFromEssai()
    {
        return this.modifsParentPropertyFromEssai;
    }

    /**
     * Getter sur indexIHM.
     * @return Retourne le indexIHM.
     */
    public int getIndexIHM()
    {
        return this.indexIHM;
    }
}

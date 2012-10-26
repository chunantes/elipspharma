package fr.pharma.eclipse.domain.enums;

/**
 * Enumération pour les rôles des utilisateurs (rôle monté sur le jeton de sécurité).
 
 * @version $Revision$ $Date$
 */
public enum RolePersonne
{
    /**
     * Rôle ARC Investigateur.
     */
    ARC_INVESTIGATEUR("ARC_INVESTIGATEUR"),

    /**
     * Rôle ARC Promoteur.
     */
    ARC_PROMOTEUR("ARC_PROMOTEUR"),

    /**
     * Rôle CRO.
     */
    CRO("CRO"),

    /**
     * Rôle Direction de la Recherche.
     */
    DIRECTION_RECHERCHE("DIRECTION_RECHERCHE"),

    /**
     * Rôle Investigateur.
     */
    INVESTIGATEUR("INVESTIGATEUR"),

    /**
     * Rôle Pharmacien.
     */
    PHARMACIEN("PHARMACIEN"),

    /**
     * Rôle Pharmacien interne.
     */
    PHARMACIEN_INTERNE("PHARMACIEN_INTERNE"),

    /**
     * Interne de garde.
     */
    PHARMACIEN_INTERNE_GARDE("PHARMACIEN_INTERNE_GARDE"),

    /**
     * Rôle Pharmacien externe.
     */
    PHARMACIEN_EXTERNE("PHARMACIEN_EXTERNE"),

    /**
     * Rôle Pharmacien préparateur.
     */
    PHARMACIEN_PREPARATEUR("PHARMACIEN_PREPARATEUR"),

    /**
     * Rôle Pharmacien titulaire.
     */
    PHARMACIEN_TITULAIRE("PHARMACIEN_TITULAIRE"),

    /**
     * Rôle Promoteur.
     */
    PROMOTEUR("PROMOTEUR"),

    /**
     * Rôle Administrateur.
     */
    ADMIN("ADMIN");

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private RolePersonne(final String libelle)
    {
        this.libelle = libelle;
    }

    /**
     * Getter sur libelle.
     * @return Retourne le libelle.
     */
    public String getLibelle()
    {
        return this.libelle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return this.getLibelle();
    }
}

package fr.pharma.eclipse.domain.enums;

/**
 * Enumération représentant le droit d'une personne sur un essai.
 
 * @version $Revision$ $Date$
 */
public enum Droit
{
    /**
     * Arc Investigateur.
     */
    ARC_INVESTIGATEUR("ARC Investigateur"),

    /**
     * Cro.
     */
    CRO("CRO"),

    /**
     * Co-investigateur.
     */
    INVESTIGATEUR_CO("Co Investigateur"),

    /**
     * Investigateur principal.
     */
    INVESTIGATEUR_PRINCIPAL("Investigateur Principal"),

    /**
     * Pharmacien interne.
     */
    PHARMACIEN_INTERNE("Interne Pharmacie"),

    /**
     * Pharmacien externe.
     */
    PHARMACIEN_EXTERNE("Externe Pharmacie"),

    /**
     * PHARMACIEN_PREPARATEUR.
     */
    PHARMACIEN_PREPARATEUR("Préparateur pharmacie"),

    /**
     * PHARMACIEN_TITULAIRE.
     */
    PHARMACIEN_TITULAIRE("Pharmacien praticien hospitalier"),

    /**
     * PHARMACIEN_ASSISTANT.
     */
    PHARMACIEN_ASSISTANT("Pharmacien assistant"),

    /**
     * PHARMACIEN_TITULAIRE.
     */
    PHARMACIEN_ATTACHE("Pharmacien attaché"),

    /**
     * PHARMACIEN_TITULAIRE.
     */
    PHARMACIEN_INTERNE_GARDE("Interne de garde en pharmacie"),

    /**
     * ARC_PROMOTEUR.
     */
    ARC_PROMOTEUR("ARC Promoteur"),

    /**
     * DIRECTION_RECHERCHE_ADMINISTRATIF.
     */
    DIRECTION_RECHERCHE_ADMINISTRATIF("Administratif"),

    /**
     * DIRECTION_RECHERCHE_PHARMACOVIGILANT.
     */
    DIRECTION_RECHERCHE_PHARMACOVIGILANT("PharmaCoVigilant"),

    /**
     * PROMOTEUR.
     */
    PROMOTEUR("Promoteur");

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private Droit(final String libelle)
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

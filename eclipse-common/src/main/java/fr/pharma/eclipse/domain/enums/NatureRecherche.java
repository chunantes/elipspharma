package fr.pharma.eclipse.domain.enums;

/**
 * Enumération pour les natures de recherche.
 
 * @version $Revision$ $Date$
 */
public enum NatureRecherche
{

    /**
     * Dépistage.
     */
    DEPISTAGE("Dépistage"),

    /**
     * Diagnostique.
     */
    DIAGNOSTIQUE("Diagnostique"),

    /**
     * Epidémiologie.
     */
    EPIDEMIOLOGIE("Epidémiologie"),

    /**
     * Génétique.
     */
    GENETIQUE("Génétique"),

    /**
     * Pharmacologie.
     */
    PHARMACOLOGIE("Pharmacologie"),

    /**
     * Physiologie.
     */
    PHYSIOLOGIE("Physiologie"),

    /**
     * Physiopathologie.
     */
    PHYSIOPATHOLOGIE("Physiopathologie"),

    /**
     * Prévention.
     */
    PREVENTION("Prévention"),

    /**
     * Pronostique.
     */
    PRONOSTIQUE("Pronostique"),

    /**
     * Thérapeutique.
     */
    THERAPEUTIQUE("Thérapeutique"),

    /**
     * Autre.
     */
    AUTRE("Autre");

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private NatureRecherche(final String libelle)
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

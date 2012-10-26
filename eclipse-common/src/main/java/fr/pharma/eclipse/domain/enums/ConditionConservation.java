package fr.pharma.eclipse.domain.enums;

/**
 * Enumération des différentes conditions de conservation (températures).
 
 * @version $Revision$ $Date$
 */
public enum ConditionConservation
{
    /**
     * -80°C.
     */
    MOINS_80("-80°C"),

    /**
     * -20°C.
     */
    MOINS_20("-20°C"),

    /**
     * +2/+8°C.
     */
    FROM_DEUX_TO_HUIT("[+2/+8°C]"),

    /**
     * <+25°C.
     */
    INF_25("<+25°C"),

    /**
     * <+30°C.
     */
    INF_30("<+30°C");

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private ConditionConservation(final String libelle)
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

package fr.pharma.eclipse.domain.enums.produit;

/**
 * Enumération des différentes formes.
 
 * @version $Revision$ $Date$
 */
public enum FormeConditionnement
{
    /**
     * Forme galénique.
     */
    GALENIQUE("Galénique"),

    /**
     * Conditionnement primaire.
     */
    CONDITIONNEMENT_PRIMAIRE("Conditionnement primaire");

    /**
     * Libellé.
     */
    private final String libelle;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private FormeConditionnement(final String libelle)
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

package fr.pharma.eclipse.domain.enums;

/**
 * Enumération pour les catégories de personne.
 
 * @version $Revision$ $Date$
 */
public enum CategoriePersonne
{
    /**
     * Interne (présent dans SIR).
     */
    INTERNE("Interne"),

    /**
     * Externe.
     */
    EXTERNE("Externe");

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private CategoriePersonne(final String libelle)
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

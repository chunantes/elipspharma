package fr.pharma.eclipse.domain.enums.produit;

/**
 * Enumération des types d'untiés de gestion.
 
 * @version $Revision$ $Date$
 */
public enum TypeUniteGestion
{
    /**
     * Voume.
     */
    VOLUME("Volume"),

    /**
     * Autres.
     */
    AUTRE("Autre");

    /**
     * Libellé.
     */
    private final String libelle;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private TypeUniteGestion(final String libelle)
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

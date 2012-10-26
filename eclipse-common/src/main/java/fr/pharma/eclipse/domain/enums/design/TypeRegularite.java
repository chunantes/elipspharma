package fr.pharma.eclipse.domain.enums.design;

/**
 * Enumération représentant le type de temps (utilisé dans les prescription).
 
 * @version $Revision$ $Date$
 */
public enum TypeRegularite
{
    /**
     * Fois.
     */
    FOIS("Fois"),

    /**
     * Tous les.
     */
    TOUS_LES("tous les"),

    /**
     * Par.
     */
    PAR("par");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé.
     */
    TypeRegularite(final String libelle)
    {
        this.libelle = libelle;
    }

    /**
     * Getter pour libelle.
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

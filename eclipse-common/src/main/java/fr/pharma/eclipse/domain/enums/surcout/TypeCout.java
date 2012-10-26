package fr.pharma.eclipse.domain.enums.surcout;

/**
 * Enumération des type de couts.
 
 * @version $Revision$ $Date$
 */
public enum TypeCout
{
    /**
     * Fixe.
     */
    FIXE("Fixe"),

    /**
     * Variable.
     */
    VARIABLE("Variable");

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private TypeCout(final String libelle)
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

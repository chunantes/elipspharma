package fr.pharma.eclipse.domain.enums;

/**
 * Enumération pour les types d'anonymisation.
 
 * @version $Revision$ $Date$
 */
public enum TypeAnonymisation
{
    /**
     * Interne (présent dans SIR).
     */
    ANONYMISE("Anonymisé"),

    /**
     * Externe.
     */
    NON_ANONYMISE("Non anonymisé");

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private TypeAnonymisation(final String libelle)
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

package fr.pharma.eclipse.domain.enums;

/**
 * Enumération représentant le type de recherche par essai pour le recherche de patient.
 
 * @version $Revision$ $Date$
 */
public enum TypeRechercheParEssai
{
    /**
     * Inclu actuellement.
     */
    ACUTELLEMENT_INCLU("Est actuellement inclu"),

    /**
     * A été ou est inclu actuellement.
     */
    INLCU("Est ou a été inclu");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé.
     */
    TypeRechercheParEssai(final String libelle)
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

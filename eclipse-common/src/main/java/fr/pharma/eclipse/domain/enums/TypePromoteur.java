package fr.pharma.eclipse.domain.enums;

/**
 * Enumération représentant le type de promoteur.
 
 * @version $Revision$ $Date$
 */
public enum TypePromoteur
{
    /**
     * Académique.
     */
    ACADEMIQUE("Académique"),

    /**
     * Etablissement de soins.
     */
    ETABLISSEMENT_SOINS("Etablissement de soins"),

    /**
     * Industriel.
     */
    INDUSTRIEL("Industriel"),

    /**
     * Autre.
     */
    AUTRE("Autre");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé du type de promoteur.
     */
    TypePromoteur(final String libelle)
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

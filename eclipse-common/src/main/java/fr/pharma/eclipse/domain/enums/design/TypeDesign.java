package fr.pharma.eclipse.domain.enums.design;

/**
 * Enumération représentant le type de design.
 
 * @version $Revision$ $Date$
 */
public enum TypeDesign
{
    /**
     * Cross-over.
     */
    CROSS_OVER("Cross-over"),

    /**
     * Parallèle.
     */
    PARALLELE("Parallèle");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé.
     */
    TypeDesign(final String libelle)
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

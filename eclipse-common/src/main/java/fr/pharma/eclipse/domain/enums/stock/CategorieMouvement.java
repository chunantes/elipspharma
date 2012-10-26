package fr.pharma.eclipse.domain.enums.stock;

/**
 * Enumération représentant les catégories de mouvements.
 
 * @version $Revision$ $Date$
 */
public enum CategorieMouvement
{
    /**
     * Entrée.
     */
    ENTREE("Entrée"),

    /**
     * Sortie.
     */
    SORTIE("Sortie"),

    /**
     * Préparation.
     */
    PREPARATION("Préparation"),

    /**
     * Dotation.
     */
    DOTATION("Dotation"),

    /**
     * Dispensation.
     */
    DISPENSATION("Dispensation");

    /**
     * Libelle.
     */
    private String libelle;
    /**
     * Constructeur.
     * @param libelle Le libellé de la catégorie de mouvement.
     */
    CategorieMouvement(final String libelle)
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

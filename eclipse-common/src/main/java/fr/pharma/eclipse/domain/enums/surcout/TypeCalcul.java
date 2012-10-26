package fr.pharma.eclipse.domain.enums.surcout;

/**
 * Enumération des type de calculs.
 
 * @version $Revision$ $Date$
 */
public enum TypeCalcul
{
    /**
     * Prévisionnel.
     */
    PREVISIONNEL("Prévisionnel", "previsionnel"),

    /**
     * Réel.
     */
    REEL("Réel", "reel");

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * REpertoire correspondant dans l'arborescence des fichiers.
     */
    private String repertoire;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private TypeCalcul(final String libelle, final String repertoire)
    {
        this.libelle = libelle;
        this.repertoire = repertoire;
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

    /**
     * Getter sur repertoire.
     * @return Retourne le repertoire.
     */
    public String getRepertoire()
    {
        return this.repertoire;
    }

    /**
     * Setter pour repertoire.
     * @param repertoire le repertoire à écrire.
     */
    public void setRepertoire(final String repertoire)
    {
        this.repertoire = repertoire;
    }
}

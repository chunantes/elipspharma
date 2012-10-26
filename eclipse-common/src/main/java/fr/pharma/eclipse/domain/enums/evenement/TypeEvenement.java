package fr.pharma.eclipse.domain.enums.evenement;

/**
 * Enumération représentant le type d'événement.
 
 * @version $Revision$ $Date$
 */
public enum TypeEvenement
{

    /**
     * Destruction.
     */
    DESTRUCTION("Destruction", "destruction"),

    /**
     * Reetiquetage.
     */
    REETIQUETAGE("Réétiquetage", "reetiquetage"),

    /**
     * Cession PUI.
     */
    CESSION_PUI("Cession PUI", "cession"),

    /**
     * Tâche.
     */
    TACHE("Tâche", "tache"),

    /**
     * Visite.
     */
    VISITE("Visite", "visite");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Classe CSS.
     */
    private String classeCSS;

    /**
     * Constructeur.
     * @param libelle Le libellé du type d'événement.
     */
    TypeEvenement(final String libelle, final String claseCSS)
    {
        this.libelle = libelle;
        this.classeCSS = claseCSS;
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

    /**
     * Getter pour classeCSS.
     * @return Le classeCSS
     */
    public String getClasseCSS()
    {
        return this.classeCSS;
    }

    /**
     * Setter pour classeCSS.
     * @param classeCSS Le classeCSS à écrire.
     */
    public void setClasseCSS(final String classeCSS)
    {
        this.classeCSS = classeCSS;
    }

}

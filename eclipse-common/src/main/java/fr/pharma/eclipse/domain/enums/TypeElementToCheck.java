package fr.pharma.eclipse.domain.enums;

/**
 * Enumération représentant les types ElementToCheck.
 
 * @version $Revision$ $Date$
 */
public enum TypeElementToCheck
{
    /**
     * Reconstitution simple.
     */
    RECONSTITUTION_SIMPLE("reconstitutionSimple", "Reconstitution Simple"),

    /**
     * Reconstitution PSM.
     */
    RECONSTITUTION_PSM("reconstitutionPSM", "Reconstitution PSM"),

    /**
     * Fabrication.
     */
    FABRICATION("fabrication", "Fabrication"),

    /**
     * Conditionnement.
     */
    CONDITIONNEMENT("conditionnement", "Conditionnement"),

    /**
     * Etiquetage.
     */
    ETIQUETAGE("etiquetage", "Etiquetage"),

    /**
     * Randomisation.
     */
    RANDOMISATION("randomisation", "Randomisation");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Libellé pour l'affichage.
     */
    private String libelleDisplay;

    /**
     * Constructeur.
     * @param libelle Le libellé du type d'élément.
     * @param libelleDisplay Le libellé du type d'élément pour l'affichage.
     */
    TypeElementToCheck(final String libelle, final String libelleDisplay)
    {
        this.libelle = libelle;
        this.libelleDisplay = libelleDisplay;
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
     * Getter pour libelleDisplay.
     * @return Le libelleDisplay
     */
    public String getLibelleDisplay()
    {
        return this.libelleDisplay;
    }

}

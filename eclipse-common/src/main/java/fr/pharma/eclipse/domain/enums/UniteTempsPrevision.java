package fr.pharma.eclipse.domain.enums;

/**
 * Enumération représentant la civilite.
 
 * @version $Revision$ $Date$
 */
public enum UniteTempsPrevision
{
    /**
     * Jours.
     */
    JOURS("Jours"),

    /**
     * Semaine.
     */
    SEMAINE("Semaines"),

    /**
     * Mois.
     */
    MOIS("Mois"),

    /**
     * Annee.
     */
    ANNEE("Années");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé.
     */
    UniteTempsPrevision(final String libelle)
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

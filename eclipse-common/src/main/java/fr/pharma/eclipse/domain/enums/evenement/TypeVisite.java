package fr.pharma.eclipse.domain.enums.evenement;

/**
 * Enumération représentant le type de visite.
 
 * @version $Revision$ $Date$
 */
public enum TypeVisite
{
    /**
     * Visite Audit interne.
     */
    AUDIT_INTERNE("Audit interne"),

    /**
     * Visite Audit externe.
     */
    AUDIT_EXTERNE("Audit externe"),

    /**
     * Visite de mise en place.
     */
    MISE_EN_PLACE("Mise en place"),

    /**
     * Visite de suivi de pharmacie.
     */
    SUIVI_PHAMACIE("Monitoring"),

    /**
     * Visite de pré-clôture.
     */
    PRE_CLOTURE("Pré Clôture"),

    /**
     * Sélection.
     */
    SELECTION("Sélection"),

    /**
     * Visite de clôture.
     */
    CLOTURE("Clôture");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé du type de visite.
     */
    TypeVisite(final String libelle)
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

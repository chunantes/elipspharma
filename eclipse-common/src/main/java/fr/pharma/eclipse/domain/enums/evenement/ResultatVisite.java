package fr.pharma.eclipse.domain.enums.evenement;

/**
 * Enumération représentant le résultat de la visite.
 
 * @version $Revision$ $Date$
 */
public enum ResultatVisite
{
    /**
     * Effectué.
     */
    EFFECTUE("Effectué"),

    /**
     * Annulé.
     */
    ANNULE("Annulé");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé du résultat de visite.
     */
    ResultatVisite(final String libelle)
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

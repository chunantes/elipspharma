package fr.pharma.eclipse.domain.enums.patient;

/**
 * Enumération pour les algorithme de calcul de la surface corporelle.
 
 * @version $Revision$ $Date$
 */
public enum FormuleSurfaceCorporelle
{
    /**
     * Interne (présent dans SIR).
     */
    DUBOIS("Formule de Dubois et Dubois²"),

    /**
     * Externe.
     */
    MOSTELLER("Formule de Mosteller");

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private FormuleSurfaceCorporelle(final String libelle)
    {
        this.libelle = libelle;
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
}

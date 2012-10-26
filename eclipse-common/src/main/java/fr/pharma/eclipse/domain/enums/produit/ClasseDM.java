package fr.pharma.eclipse.domain.enums.produit;

/**
 * Enumération des différentes classes de Dispositifs médicaux.
 
 * @version $Revision$ $Date$
 */
public enum ClasseDM
{
    /**
     * Classe I.
     */
    I("I"),

    /**
     * Classe IIa.
     */
    IIa("IIa"),

    /**
     * Classe IIb.
     */
    IIb("IIb"),

    /**
     * Classe III.
     */
    III("III");

    /**
     * Libellé.
     */
    private final String libelle;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     */
    private ClasseDM(final String libelle)
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

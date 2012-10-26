package fr.pharma.eclipse.domain.enums;

/**
 * Enumération représentant la qualité de l'insu.
 
 * @version $Revision$ $Date$
 */
public enum QualiteInsu
{
    /**
     * Ouvert.
     */
    ESSAI_OUVERT("Essai ouvert"),

    /**
     * Simple aveugle.
     */
    ESSAI_SIMPLE_AVEUGLE("Essai simple aveugle"),

    /**
     * Double aveugle.
     */
    ESSAI_DOUBLE_AVEUGLE("Essai double aveugle");
    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé de la qualité de l'insu.
     */
    QualiteInsu(final String libelle)
    {
        this.setLibelle(libelle);
    }

    /**
     * Setter pour libelle.
     * @param libelle le libelle à écrire.
     */
    public void setLibelle(final String libelle)
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

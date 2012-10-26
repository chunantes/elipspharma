package fr.pharma.eclipse.domain.enums;

/**
 * Enumération représentantn les types de Pharmacien.
 
 * @version $Revision$ $Date$
 */
public enum TypePharmacien
{
    /**
     * Pharmacien interne.
     */
    INTERNE("Interne Pharmacie", Droit.PHARMACIEN_INTERNE),

    /**
     * Pharmacien externe.
     */
    EXTERNE("Externe Pharmacie", Droit.PHARMACIEN_EXTERNE),

    /**
     * Pharmacien préparateur.
     */
    PREPARATEUR("Préparateur pharmacie", Droit.PHARMACIEN_PREPARATEUR),

    /**
     * Pharmacien titulaire.
     */
    TITULAIRE("Pharmacien praticien hospitalier", Droit.PHARMACIEN_TITULAIRE),

    /**
     * Pharmacien interne de arde.
     */
    INTERNE_GARDE("Interne de garde en pharmacie", Droit.PHARMACIEN_INTERNE_GARDE),

    /**
     * Pharmacien attaché
     */
    ATTACHE("Pharmacien attaché", Droit.PHARMACIEN_ATTACHE),

    /**
     * Pharmacien assistant.
     */
    ASSISTANT("Pharmacien assistant", Droit.PHARMACIEN_ASSISTANT);

    /**
     * Libellé.
     */
    private String libelle;

    /**
     * Droit associé.
     */
    private Droit droit;

    /**
     * Constructeur privé.
     * @param libelle Libellé de la valeur de l'énumération.
     * @param droit Droit associé.
     */
    private TypePharmacien(final String libelle, final Droit droit)
    {
        this.libelle = libelle;
        this.droit = droit;
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
     * Getter sur droit.
     * @return Retourne le droit.
     */
    public Droit getDroit()
    {
        return this.droit;
    }

}

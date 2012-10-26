package fr.pharma.eclipse.domain.enums.document;

/**
 * Enumération des différents types de documents, relatifs à un produit, sauvegardés sur le disque
 * via l'application.
 
 * @version $Revision$ $Date$
 */
public enum TypeDocumentProduit implements EnumTypeDocument
{
    /**
     * Document associé à la randomisation dans le détail Actes pharmaceutique d'un Dispositif
     * Medical.
     */
    RANDOMISATION("randomisation", "documentRandomisation"),

    /**
     * Document associé au conditionnement dans le détail Actes pharmaceutique d'un médicament.
     */
    CONDITIONNEMENT("conditionnement", "documentConditionnement"),

    /**
     * Document associé à l'étiquetage dans le détail Actes pharmaceutique d'un médicament.
     */
    ETIQUETAGE("etiquetage", "documentEtiquetage"),

    /**
     * Document associé à la reconstitution simple dans le détail Actes pharmaceutique d'un
     * médicament.
     */
    RECONSTITUTION_SIMPLE("reconstitutionSimple", "documentReconstitutionSimple"),

    /**
     * Document associé à la reconstitution PSM dans le détail Actes pharmaceutique d'un
     * médicament.
     */
    RECONSTITUTION_PSM("reconstitutionPSM", "documentReconstitutionPSM"),

    /**
     * Document associé à la fabrication dans le détail Actes pharmaceutique d'un médicament.
     */
    FABRICATION("fabrication", "documentFabrication");

    /**
     * Chemin d'accès aux documents de ce type à partir du répertoire du produit.
     */
    private String repertoire;

    /**
     * Propriete du produit représentant le document.
     */
    private String propriete;

    /**
     * Constructeur.
     * @param repertoire Chemin d'accès aux documents de ce type à partir du répertoire du
     * produit.
     * @param propriete Propriété pour accéder au document depuis le produit.
     */
    private TypeDocumentProduit(final String repertoire, final String propriete)
    {
        this.repertoire = repertoire;
        this.propriete = propriete;
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
     * {@inheritDoc}
     */
    @Override
    public TypeDocumentEclipse getTypeEclipse()
    {
        return TypeDocumentEclipse.PRODUIT;
    }

    /**
     * Getter sur propriete.
     * @return Retourne le propriete.
     */
    public String getPropriete()
    {
        return this.propriete;
    }

    /**
     * Setter pour propriete.
     * @param propriete le propriete à écrire.
     */
    public void setPropriete(final String propriete)
    {
        this.propriete = propriete;
    }

}

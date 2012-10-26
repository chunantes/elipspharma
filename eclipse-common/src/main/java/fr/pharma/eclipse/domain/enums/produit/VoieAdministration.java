package fr.pharma.eclipse.domain.enums.produit;

/**
 * Enumération représentant la voie d'administration du produit.
 
 * @version $Revision$ $Date$
 */
public enum VoieAdministration
{

    /**
     * Autre.
     */
    AUTRE("Autre"),

    /**
     * Bolus .
     */
    BOLUS("Bolus"),

    /**
     * Intra artérielle .
     */
    INTRA_ARTERIELLE("Intra-artérielle"),

    /**
     * Intra musculaire.
     */
    INTRA_MUSCULAIRE("Intra-musculaire"),

    /**
     * Intra oculaire.
     */
    INTRA_OCULAIRE("Intra-oculaire"),

    /**
     * Perfusion continue.
     */
    INTRA_OSSEUX("Intra-osseux"),

    /**
     * Intra peritoneal.
     */
    INTRA_PERITONEAL("Intra-peritoneal"),

    /**
     * Intra pleural.
     */
    INTRA_PLEURAL("Intra-pleural"),
    /**
     * Intra thécale.
     */
    INTRA_THECALE("Intra-thécale"),

    /**
     * Intraveineux .
     */
    INTRAVEINEUX("Intraveineux"),

    /**
     * Intra-veineuse directe.
     */
    INTRAVEINEUSE_DIRECTE("Intra-veineuse directe"),

    /**
     * Intra-veineuse lente.
     */
    INTRAVEINEUSE_LENTE("Intra-veineuse lente"),

    /**
     * Intra vesical.
     */
    INTRA_VESICAL("Intra-vesical"),

    /**
     * Perfusion continue.
     */
    INTRA_VITREEN("Intra-vitréen"),

    /**
     * Nebulisation.
     */
    NEBULISATION("Nebulisation"),

    /**
     * Orale.
     */
    ORALE("Orale"),

    /**
     * Per os.
     */
    PER_OS("Per os"),

    /**
     * Perfusion.
     */
    PERFUSION("Perfusion"),

    /**
     * Perfusion continue.
     */
    PERFUSION_CONTINUE("Perfusion continue"),

    /**
     * Sous-cutané.
     */
    SOUS_CUTANE("Sous-cutané"),

    /**
     * Topique.
     */
    TOPIQUE("Topique");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé.
     */
    VoieAdministration(final String libelle)
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

package fr.pharma.eclipse.domain.enums;

/**
 * Enumération représentant l'état de l'essai.
 
 * @version $Revision$ $Date$
 */
public enum EtatEssai
{
    /**
     * En évaluation.
     */
    EN_EVALUATION("Non activé - En évaluation"),

    /**
     * En attente de mise en place.
     */
    EN_ATTENTE_MISE_EN_PLACE("Non activé - En attente de mise en place"),

    /**
     * Mise en place.
     */
    MISE_EN_PLACE("Non activé - Mis en place"),

    /**
     * En cours.
     */
    EN_COURS("Activé - En cours"),

    /**
     * En attente de clôture.
     */
    EN_ATTENTE_CLOTURE("Activé - En attente de clôture"),

    /**
     * En attente de la lettre de clôture.
     */
    EN_ATTENTE_LETTRE_CLOTURE("Activé - En attente de la lettre de clôture"),

    /**
     * Clôturé.
     */
    CLOTURE("Désactivé - Clôturé"),

    /**
     * Archivé.
     */
    ARCHIVE("Désactivé - Archivé");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé du type de promoteur.
     */
    EtatEssai(final String libelle)
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

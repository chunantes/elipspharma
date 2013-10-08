package fr.pharma.eclipse.domain.enums.produit;

/**
 * Enumération représentant la nature d'un DM.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public enum NatureDM {
    /**
     * DM évalué.
     */
    DM_EVALUE("Dispositif médical évalué"),

    /**
     * DM comparateur.
     */
    DM_COMPARATEUR("Dispositif médical comparateur");

    /**
     * Libelle.
     */
    private String libelle;

    /**
     * Constructeur.
     * @param libelle Le libellé de la nature du Dm.
     */
    NatureDM(final String libelle) {
        this.libelle = libelle;
    }

    /**
     * Getter pour libelle.
     * @return Retourne le libelle.
     */
    public String getLibelle() {
        return this.libelle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.getLibelle();
    }

}

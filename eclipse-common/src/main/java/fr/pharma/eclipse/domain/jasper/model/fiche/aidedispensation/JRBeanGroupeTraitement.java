package fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.jasper.constants.JasperConstants;

/**
 * Classe représentant la source de données<br>
 * relative à un bras de l'essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JRBeanGroupeTraitement implements JasperReportBean {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 7533990998774235099L;

    /**
     * Nom du bras.
     */
    private String design = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Début (pour une séquence).
     */
    private String debut = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Fin (pour une séquence).
     */
    private String fin = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Liste des produits.
     */
    private String produits = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Getter sur design.
     * @return Retourne le design.
     */
    public String getDesign() {
        return this.design;
    }

    /**
     * Setter pour design.
     * @param design le design à écrire.
     */
    public void setDesign(final String design) {
        this.design = design;
    }

    /**
     * Getter sur debut.
     * @return Retourne le debut.
     */
    public String getDebut() {
        return this.debut;
    }

    /**
     * Setter pour debut.
     * @param debut le debut à écrire.
     */
    public void setDebut(final String debut) {
        this.debut = debut;
    }

    /**
     * Getter sur fin.
     * @return Retourne le fin.
     */
    public String getFin() {
        return this.fin;
    }

    /**
     * Setter pour fin.
     * @param fin le fin à écrire.
     */
    public void setFin(final String fin) {
        this.fin = fin;
    }

    /**
     * Getter sur produits.
     * @return Retourne le produits.
     */
    public String getProduits() {
        return this.produits;
    }

    /**
     * Setter pour produits.
     * @param produits le produits à écrire.
     */
    public void setProduits(final String produits) {
        this.produits = produits;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return new StringBuilder("[").append(this.design).append(", ").append(this.debut).append(", ").append(this.fin).append(", ").append(this.produits).append("]").toString();
    }

}

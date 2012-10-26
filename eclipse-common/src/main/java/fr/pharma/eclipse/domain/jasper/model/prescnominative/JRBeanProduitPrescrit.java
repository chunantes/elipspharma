package fr.pharma.eclipse.domain.jasper.model.prescnominative;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.jasper.constants.JasperConstants;

/**
 * Classe représentant la source de données<br>
 * relative à un produit prescrit d'une prescription.
 
 * @version $Revision$ $Date$
 */
public class JRBeanProduitPrescrit
    implements JasperReportBean
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5788689516409793037L;

    /**
     * Dénomination.
     */
    private String denomination = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Voie.
     */
    private String voie = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * POsologie.
     */
    private String posologie = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Durée.
     */
    private String duree = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Durée.
     */
    private String quantite = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Getter pour denomination.
     * @return Le denomination
     */
    public String getDenomination()
    {
        return this.denomination;
    }

    /**
     * Setter pour denomination.
     * @param denomination Le denomination à écrire.
     */
    public void setDenomination(final String denomination)
    {
        this.denomination = denomination;
    }

    /**
     * Getter pour voie.
     * @return Le voie
     */
    public String getVoie()
    {
        return this.voie;
    }

    /**
     * Setter pour voie.
     * @param voie Le voie à écrire.
     */
    public void setVoie(final String voie)
    {
        this.voie = voie;
    }

    /**
     * Getter pour posologie.
     * @return Le posologie
     */
    public String getPosologie()
    {
        return this.posologie;
    }

    /**
     * Setter pour posologie.
     * @param posologie Le posologie à écrire.
     */
    public void setPosologie(final String posologie)
    {
        this.posologie = posologie;
    }

    /**
     * Getter pour duree.
     * @return Le duree
     */
    public String getDuree()
    {
        return this.duree;
    }

    /**
     * Setter pour duree.
     * @param duree Le duree à écrire.
     */
    public void setDuree(final String duree)
    {
        this.duree = duree;
    }

    /**
     * Getter pour quantite.
     * @return Le quantite
     */
    public String getQuantite()
    {
        return this.quantite;
    }

    /**
     * Setter pour quantite.
     * @param quantite Le quantite à écrire.
     */
    public void setQuantite(final String quantite)
    {
        this.quantite = quantite;
    }

}

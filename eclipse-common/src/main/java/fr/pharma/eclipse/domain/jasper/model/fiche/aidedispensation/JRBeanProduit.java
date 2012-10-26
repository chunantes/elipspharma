package fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation;

import fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean;
import fr.pharma.eclipse.jasper.constants.JasperConstants;

/**
 * Classe représentant la source de données<br>
 * relative à un produit de l'essai.
 
 * @version $Revision$ $Date$
 */
public class JRBeanProduit
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
     * DCI.
     */
    private String dci = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Code.
     */
    private String code = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Classe thérapeuthique.
     */
    private String classeTherapeuthique = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Conditionnement.
     */
    private String conditionnement = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Conditions de conservation.
     */
    private String conservation = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Posologie et modalités d'administration.
     */
    private String posologie = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Influence de l'alimentation.
     */
    private String influAlimentation = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Durée de traitement.
     */
    private String dureeTraitement = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Lieu de stockage.
     */
    private String lieuStockage = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Getter sur dci.
     * @return Retourne le dci.
     */
    public String getDci()
    {
        return this.dci;
    }

    /**
     * Setter pour dci.
     * @param dci le dci à écrire.
     */
    public void setDci(final String dci)
    {
        this.dci = dci;
    }

    /**
     * Getter sur code.
     * @return Retourne le code.
     */
    public String getCode()
    {
        return this.code;
    }

    /**
     * Setter pour code.
     * @param code le code à écrire.
     */
    public void setCode(final String code)
    {
        this.code = code;
    }

    /**
     * Getter sur classeTherapeuthique.
     * @return Retourne le classeTherapeuthique.
     */
    public String getClasseTherapeuthique()
    {
        return this.classeTherapeuthique;
    }

    /**
     * Setter pour classeTherapeuthique.
     * @param classeTherapeuthique le classeTherapeuthique à écrire.
     */
    public void setClasseTherapeuthique(final String classeTherapeuthique)
    {
        this.classeTherapeuthique = classeTherapeuthique;
    }

    /**
     * Getter sur conditionnement.
     * @return Retourne le conditionnement.
     */
    public String getConditionnement()
    {
        return this.conditionnement;
    }

    /**
     * Setter pour conditionnement.
     * @param conditionnement le conditionnement à écrire.
     */
    public void setConditionnement(final String conditionnement)
    {
        this.conditionnement = conditionnement;
    }

    /**
     * Getter sur posologie.
     * @return Retourne le posologie.
     */
    public String getPosologie()
    {
        return this.posologie;
    }

    /**
     * Setter pour posologie.
     * @param posologie le posologie à écrire.
     */
    public void setPosologie(final String posologie)
    {
        this.posologie = posologie;
    }

    /**
     * Getter sur influAlimentation.
     * @return Retourne le influAlimentation.
     */
    public String getInfluAlimentation()
    {
        return this.influAlimentation;
    }

    /**
     * Setter pour influAlimentation.
     * @param influAlimentation le influAlimentation à écrire.
     */
    public void setInfluAlimentation(final String influAlimentation)
    {
        this.influAlimentation = influAlimentation;
    }

    /**
     * Getter sur dureeTraitement.
     * @return Retourne le dureeTraitement.
     */
    public String getDureeTraitement()
    {
        return this.dureeTraitement;
    }

    /**
     * Setter pour dureeTraitement.
     * @param dureeTraitement le dureeTraitement à écrire.
     */
    public void setDureeTraitement(final String dureeTraitement)
    {
        this.dureeTraitement = dureeTraitement;
    }

    /**
     * Getter sur lieuStockage.
     * @return Retourne le lieuStockage.
     */
    public String getLieuStockage()
    {
        return this.lieuStockage;
    }

    /**
     * Setter pour lieuStockage.
     * @param lieuStockage le lieuStockage à écrire.
     */
    public void setLieuStockage(final String lieuStockage)
    {
        this.lieuStockage = lieuStockage;
    }

    /**
     * Getter sur denomination.
     * @return Retourne le denomination.
     */
    public String getDenomination()
    {
        return this.denomination;
    }

    /**
     * Setter pour denomination.
     * @param denomination le denomination à écrire.
     */
    public void setDenomination(final String denomination)
    {
        this.denomination = denomination;
    }

    /**
     * Getter sur conservation.
     * @return Retourne le conservation.
     */
    public String getConservation()
    {
        return this.conservation;
    }

    /**
     * Setter pour conservation.
     * @param conservation le conservation à écrire.
     */
    public void setConservation(final String conservation)
    {
        this.conservation = conservation;
    }

}

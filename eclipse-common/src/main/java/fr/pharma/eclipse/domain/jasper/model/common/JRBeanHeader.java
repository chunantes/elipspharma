package fr.pharma.eclipse.domain.jasper.model.common;

import fr.pharma.eclipse.jasper.constants.JasperConstants;

/**
 * Classe représentant la source de données <br>
 * pour l'en-tête commune des rapprts japser.
 
 * @version $Revision$ $Date$
 */
public class JRBeanHeader
    implements JasperReportBean
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -8357216879475701764L;

    /**
     * Sous-titre de l'imprimé.
     */
    private String sousTitre = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Nom du processus de l'imprimé.
     */
    private String processus = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Thèmes de l'imprimé.
     */
    private String themes = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * URL de l'image de l'en-tête.
     */
    private String urlImage = null;

    /**
     * Responsable de la diffusion de l'imprimé.
     */
    private String diffusionPar = JasperConstants.DEFAULT_FIELD_VALUE;

    /**
     * Getter sur sousTitre.
     * @return Retourne le sousTitre.
     */
    public String getSousTitre()
    {
        return this.sousTitre;
    }
    /**
     * Setter pour sousTitre.
     * @param sousTitre le sousTitre à écrire.
     */
    public void setSousTitre(final String sousTitre)
    {
        this.sousTitre = sousTitre;
    }
    /**
     * Getter sur processus.
     * @return Retourne le processus.
     */
    public String getProcessus()
    {
        return this.processus;
    }
    /**
     * Setter pour processus.
     * @param processus le processus à écrire.
     */
    public void setProcessus(final String processus)
    {
        this.processus = processus;
    }
    /**
     * Getter sur themes.
     * @return Retourne le themes.
     */
    public String getThemes()
    {
        return this.themes;
    }
    /**
     * Setter pour themes.
     * @param themes le themes à écrire.
     */
    public void setThemes(final String themes)
    {
        this.themes = themes;
    }
    /**
     * Getter sur urlImage.
     * @return Retourne le urlImage.
     */
    public String getUrlImage()
    {
        return this.urlImage;
    }
    /**
     * Setter pour urlImage.
     * @param urlImage le urlImage à écrire.
     */
    public void setUrlImage(final String urlImage)
    {
        this.urlImage = urlImage;
    }
    /**
     * Getter sur diffusionPar.
     * @return Retourne le diffusionPar.
     */
    public String getDiffusionPar()
    {
        return this.diffusionPar;
    }
    /**
     * Setter pour diffusionPar.
     * @param diffusionPar le diffusionPar à écrire.
     */
    public void setDiffusionPar(final String diffusionPar)
    {
        this.diffusionPar = diffusionPar;
    }
}

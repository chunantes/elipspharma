package fr.pharma.eclipse.domain.jasper.model.common;

import java.io.Serializable;

import fr.pharma.eclipse.jasper.constants.JasperConstants;

/**
 * Classe abstraite des beans Jasper avec en-tête.
 
 * @version $Revision$ $Date$
 */
public abstract class AbstractJRBeanWithHeader
    implements Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1212977694252488018L;

    /**
     * Getter sur header.
     * @return header.
     */
    public abstract JRBeanHeader getHeader();

    /**
     * Getter sur headSousTitre.
     * @return header.sousTitre.
     */
    public String getHeadSousTitre()
    {
        if (this.getHeader() == null)
        {
            return JasperConstants.DEFAULT_FIELD_VALUE;
        }
        return this.getHeader().getSousTitre();
    }

    /**
     * Getter sur headProcessus.
     * @return header.processus.
     */
    public String getHeadProcessus()
    {
        if (this.getHeader() == null)
        {
            return JasperConstants.DEFAULT_FIELD_VALUE;
        }
        return this.getHeader().getProcessus();
    }

    /**
     * Getter sur headThemes.
     * @return header.themes.
     */
    public String getHeadThemes()
    {
        if (this.getHeader() == null)
        {
            return JasperConstants.DEFAULT_FIELD_VALUE;
        }
        return this.getHeader().getThemes();
    }

    /**
     * Getter sur headUrlImage.
     * @return header.urlImage.
     */
    public String getHeadUrlImage()
    {
        if (this.getHeader() == null)
        {
            return JasperConstants.DEFAULT_FIELD_VALUE;
        }
        return this.getHeader().getUrlImage();
    }

    /**
     * Getter sur headDiffusionPar.
     * @return header.diffusionPar.
     */
    public String getHeadDiffusionPar()
    {
        if (this.getHeader() == null)
        {
            return JasperConstants.DEFAULT_FIELD_VALUE;
        }
        return this.getHeader().getDiffusionPar();
    }

}

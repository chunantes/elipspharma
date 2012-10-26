package fr.pharma.eclipse.utils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.message.EclipseMessageBuilder;

/**
 * Classe utilitaire pour la gestion du context de gestion JSF.
 
 * @version $Revision$ $Date$
 */
public class FacesUtils
    implements Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -3931006423113397506L;

    /**
     * Message builder.
     */
    @Resource(name = "eclipseMessageBuilder")
    private EclipseMessageBuilder messageBuilder;

    /**
     * Méthode en charge d'ajouter un message sur l'instance courante du FacesContext.
     * @param severity Sévérité du message.
     * @param codeMessage Code du message dans les fichiers properties.
     */
    public void addMessage(final Severity severity,
                           final String codeMessage)
    {
        final String strMessage = this.messageBuilder.getMessage(codeMessage);
        final FacesMessage message = new FacesMessage(severity,
                                                      strMessage,
                                                      null);
        FacesContext.getCurrentInstance().addMessage(null,
                                                     message);
    }

    /**
     * Méthode en charge d'ajouter un paramètre dans le contexte de la requête.
     * @param param Paramètre.
     * @param value Valeur.
     */
    public void putCallbackParam(final String param,
                                 final Object value)
    {
        final RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.addCallbackParam(param,
                                        value);
    }

    /**
     * Méthode en charge d'ajouter le paramètre de validité dans le contexte de la requête.
     * @param isValid Valeur du paramètre de validité.
     */
    public void putCallbackValidityParam(final boolean isValid)
    {
        final RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.addCallbackParam("isValid",
                                        isValid);
    }

    /**
     * Méthode en charge de formatter une date.
     * @param calendar Calendar à formatter.
     * @return Calendar formatté.
     */
    public String formatDate(final Calendar calendar)
    {
        final SimpleDateFormat sdf = new SimpleDateFormat(EclipseConstants.PATTERN_SIMPLE);
        return sdf.format(calendar.getTime());
    }

    /**
     * Setter pour messageBuilder.
     * @param messageBuilder Le messageBuilder à écrire.
     */
    public void setMessageBuilder(final EclipseMessageBuilder messageBuilder)
    {
        this.messageBuilder = messageBuilder;
    }

}

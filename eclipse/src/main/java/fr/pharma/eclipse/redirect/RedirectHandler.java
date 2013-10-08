package fr.pharma.eclipse.redirect;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe en charge de gérer les redirections.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class RedirectHandler implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 9031169427130195111L;

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(RedirectHandler.class);

    /**
     * Méthode en charge de lancer une redirection à partir de l'url de
     * redirection fournit en paramètre.
     * @param urlRedirect URL de redirection.
     */
    public void redirect(final String urlRedirect) {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        final ExternalContext externalContext = facesContext.getExternalContext();
        try {
            externalContext.redirect(urlRedirect);
            facesContext.responseComplete();
        } catch (final IOException e) {
            this.log.error("Erreur lors de la redirection sur url : " + urlRedirect);
        }
    }

}

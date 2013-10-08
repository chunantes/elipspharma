package fr.pharma.eclipse.utils.message;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;

import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de récupérer les messages stockés dans les fichiers
 * properties.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class MessageBuilder implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -6543327443967650339L;

    /**
     * Messages.
     */
    @Resource(name = "messageSource")
    private MessageSource messageSource;

    /**
     * Méthode en charge de retourner le contenu d'un message présent dans un
     * fichier properties.
     * @param codeMessage Code du message.
     * @return Libellé du message.
     */
    public String getMessage(final String codeMessage) {
        return this.messageSource.getMessage(codeMessage, null, EclipseConstants.LOCALE);
    }

    /**
     * Méthode en charge de retourner le contenu d'un message présent dans un
     * fichier properties et contenant des arguments.
     * @param codeMessage Code du message.
     * @param args Tableau contenant la liste des arguments du message.
     * @return Libellé du message.
     */
    public String getMessage(final String codeMessage,
                             final Object[] args) {
        return this.messageSource.getMessage(codeMessage, args, EclipseConstants.LOCALE);
    }
}

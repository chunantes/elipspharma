package fr.pharma.eclipse.flow.handler;

import org.springframework.webflow.engine.RequestControlContext;

/**
 * Handler spécifique à une exception.
 
 * @version $Revision$ $Date$
 */
public interface ExceptionHandler
{
    /**
     * Méthode de gestion d'exception.
     * @param e Exception à gérer.
     * @param context Contexte de la requête.
     */
    void handle(Exception e,
                RequestControlContext context);
}

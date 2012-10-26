package fr.pharma.eclipse.flow.handler;

import org.springframework.webflow.engine.RequestControlContext;

/**
 * Handler pour les TechnicalException.
 
 * @version $Revision$ $Date$
 */
public class TechnicalExceptionHandler
    implements ExceptionHandler
{

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final Exception e,
                       final RequestControlContext context)
    {
        context.getFlashScope().put("messageContextKey",
                                    "globalError");
        context.getFlashScope().put("messageContext",
                                    context.getMessageContext());
    }

}

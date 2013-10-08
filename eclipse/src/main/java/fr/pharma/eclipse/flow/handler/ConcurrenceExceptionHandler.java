package fr.pharma.eclipse.flow.handler;

import org.springframework.webflow.engine.RequestControlContext;

/**
 * Handler d'exception pour ConcurrenceExceptionHandler (validation eclipse).
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConcurrenceExceptionHandler implements ExceptionHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final Exception exception,
                       final RequestControlContext context) {
        context.getFlashScope().put("messageContextKey", "concurrenceError");
        context.getFlashScope().put("messageContext", context.getMessageContext());

    }

}

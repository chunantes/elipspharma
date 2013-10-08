package fr.pharma.eclipse.flow.handler;

import java.util.Iterator;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang.StringUtils;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageResolver;
import org.springframework.webflow.engine.RequestControlContext;
import org.springframework.webflow.engine.support.TransitionExecutingFlowExecutionExceptionHandler;

/**
 * Handler pour les ContraintViolationException (Validation BeanValidation).
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ConstraintViolationExceptionHandler implements ExceptionHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final Exception e,
                       final RequestControlContext context) {
        final ConstraintViolationException constraintException = (ConstraintViolationException) e.getCause();
        for (final Iterator<ConstraintViolation<?>> it = constraintException.getConstraintViolations().iterator(); it.hasNext();) {
            final ConstraintViolation<?> violation = it.next();
            this.process(violation, context);
        }

    }

    /**
     * Méthode en charge de traiter une violation de contrainte.
     * @param violation Violation à traiter.
     * @param context Contexte de la requête.
     */
    private void process(final ConstraintViolation<?> violation,
                         final RequestControlContext context) {
        // Construction de la clé du message.
        final MessageBuilder builder = new MessageBuilder().error();
        builder.codes(new String[]{violation.getMessage() });

        // Construction des arguments du message.

        final MessageResolver resolver = builder.build();
        context.getMessageContext().addMessage(resolver);

        String messageKey = StringUtils.EMPTY;
        messageKey = violation.getMessage();
        context.getFlashScope().put("messageContextKey", messageKey);
        context.getFlashScope().put("messageContext", context.getMessageContext());
        context.getFlashScope().put("messageSource", violation.getRootBeanClass().getSimpleName());
        final String excAtt2 = TransitionExecutingFlowExecutionExceptionHandler.ROOT_CAUSE_EXCEPTION_ATTRIBUTE;
        context.getFlashScope().put(excAtt2, violation);
    }
}

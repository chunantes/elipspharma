package fr.pharma.eclipse.flow.handler;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageResolver;
import org.springframework.webflow.engine.RequestControlContext;
import org.springframework.webflow.engine.support.TransitionExecutingFlowExecutionExceptionHandler;

import fr.pharma.eclipse.exception.ValidationException;

/**
 * Handler d'exception pour ValidationExceptionHandler (validation eclipse).
 
 * @version $Revision$ $Date$
 */
public class ValidationExceptionHandler
    implements ExceptionHandler
{

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final Exception exception,
                       final RequestControlContext context)
    {
        final ValidationException exValidation = (ValidationException) exception.getCause();
        // Construction de la clé du message.
        final StringBuilder strToResolve = new StringBuilder();
        strToResolve.append(exValidation.getErrorCode());
        strToResolve.append(".");
        strToResolve.append(exValidation.getValues()[0]);
        final MessageBuilder builder = new MessageBuilder().error();
        builder.codes(new String[]
        {strToResolve.toString() });

        // Construction des arguments du message.
        final int valuesSize = exValidation.getValues().length;
        if (valuesSize > 1)
        {
            final String[] args = Arrays.copyOfRange(exValidation.getValues(),
                                                     1,
                                                     valuesSize);
            builder.args(args);
        }

        final MessageResolver resolver = builder.build();
        context.getMessageContext().addMessage(resolver);

        String messageKey = StringUtils.EMPTY;
        messageKey = exValidation.getValues()[0];
        context.getFlashScope().put("messageContextKey",
                                    messageKey);
        context.getFlashScope().put("messageContext",
                                    context.getMessageContext());
        context.getFlashScope().put("messageSource",
                                    exValidation.getSource());

        final String excAtt2 =
            TransitionExecutingFlowExecutionExceptionHandler.ROOT_CAUSE_EXCEPTION_ATTRIBUTE;
        context.getFlashScope().put(excAtt2,
                                    exValidation);

    }

}

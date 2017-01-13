/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package fr.pharma.eclipse.flow.handler;

import javax.persistence.OptimisticLockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageResolver;
import org.springframework.webflow.engine.RequestControlContext;
import org.springframework.webflow.engine.support.TransitionExecutingFlowExecutionExceptionHandler;

/**
 * @author dev
 */
public class OptimisticLockExceptionHandler implements ExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final Exception exception,
                       final RequestControlContext context) {
        final String errorMessage = "optimisticLockError";
        final MessageBuilder builder = new MessageBuilder().error();
        builder.codes(new String[]{errorMessage });
        final MessageResolver resolver = builder.build();
        context.getMessageContext().addMessage(resolver);

        context.getFlashScope().put("messageContextKey", "optimisticLockError");
        context.getFlashScope().put("messageContext", context.getMessageContext());

        final String excAtt2 = TransitionExecutingFlowExecutionExceptionHandler.ROOT_CAUSE_EXCEPTION_ATTRIBUTE;
        context.getFlashScope().put(excAtt2, exception);

        logger.warn("optimisticLockError occurs" +((OptimisticLockException)exception.getCause()).getEntity(), exception);
    }
}

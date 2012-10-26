package fr.pharma.eclipse.flow.handler;

import java.util.Map;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.definition.StateDefinition;
import org.springframework.webflow.engine.FlowExecutionExceptionHandler;
import org.springframework.webflow.engine.RequestControlContext;
import org.springframework.webflow.engine.Transition;
import org.springframework.webflow.engine.TransitionableState;
import org.springframework.webflow.engine.support.DefaultTargetStateResolver;
import org.springframework.webflow.engine.support.TransitionExecutingFlowExecutionExceptionHandler;
import org.springframework.webflow.execution.FlowExecutionException;

import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.utils.Utils;

/**
 * Classe de gestion des exceptions retournées par le métier. Cette classe permet d'intercepter
 * les erreurs de type ValidationException et de les ajouter à la liste des errors affichages avec
 * la balise 'form:error'.
 
 * @version $Revision$ $Date$
 */
public class FlowExceptionHandler
    implements FlowExecutionExceptionHandler
{
    /**
     * Logger par défaut.
     */
    private final Logger log = LoggerFactory.getLogger(FlowExceptionHandler.class);

    /**
     * Dictionnaire d'handler en fonction du type de l'exception.
     */
    private Map<String, ExceptionHandler> handlers;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canHandle(final FlowExecutionException exception)
    {
        return this.findBusinessException(exception) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(final FlowExecutionException exception,
                       final RequestControlContext context)
    {
        // recherche du handler corresondant à l'exception
        this.handlers.get(this.findBusinessException(exception)).handle(exception,
                                                                        context);
        final String excAtt1 =
            TransitionExecutingFlowExecutionExceptionHandler.FLOW_EXECUTION_EXCEPTION_ATTRIBUTE;
        context.getFlashScope().put(excAtt1,
                                    exception);

        final StateDefinition testState = context.getCurrentState();

        final Transition transition =
            new Transition(new DefaultTargetStateResolver(((TransitionableState) testState)
                    .getTransition("error")
                    .getTargetStateId()));
        context.execute(transition);
        // }
    }

    /**
     * Méthode en charge de caster les erreurs à attrapper en ValidationError.
     * @param ex Exception.
     * @return Erreur de type Validation Erreur.
     */
    private String findBusinessException(final FlowExecutionException ex)
    {
        final Throwable cause = ex.getCause();
        if (cause != null)
        {
            if (cause instanceof ValidationException)
            {
                return cause.getClass().getSimpleName();
            }
            if (cause instanceof ConstraintViolationException)
            {
                return cause.getClass().getSimpleName();
            }
            // Exception de persistence : multi-onglets (pb de concurrence)
            if (cause instanceof PersistenceException)
            {
                final String excep1 = "org.hibernate.TypeMismatchException";
                final String excep2 = "org.hibernate.action.DelayedPostInsertIdentifier";
                final String msg = cause.getMessage();
                if (msg.contains(excep1)
                    && msg.contains(excep2))
                {
                    return "ConcurrenceException";
                }
            }
        }
        // Erreur non gérée : log.
        this.logException(ex);
        return null;
    }
    /**
     * Méthode en charge de logger l'exception non gérée.
     * @param ex Exception.
     */
    private void logException(final FlowExecutionException ex)
    {
        String message;
        if (ex.getCause() != null)
        {
            message = this.getStackString(ex.getCause());
        }
        else
        {
            message = this.getStackString(ex);
        }
        this.log.error(message);
    }

    /**
     * Méthode en charge de construire la chaîne de caractères <br />
     * correspondant à la StackTrace d'une exception.
     * @param ex Exception à étudier.
     * @return La chaîne représentant la StackTrace de l'exception.
     */
    private String getStackString(final Throwable ex)
    {
        return Utils.getStringStack(ex);
    }

    /**
     * Getter sur handlers.
     * @return Retourne le handlers.
     */
    public Map<String, ExceptionHandler> getHandlers()
    {
        return this.handlers;
    }

    /**
     * Setter pour handlers.
     * @param handlers le handlers à écrire.
     */
    public void setHandlers(final Map<String, ExceptionHandler> handlers)
    {
        this.handlers = handlers;
    }

}

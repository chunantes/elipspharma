package fr.pharma.eclipse.profiling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.webflow.core.collection.AttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.definition.FlowDefinition;
import org.springframework.webflow.definition.StateDefinition;
import org.springframework.webflow.definition.TransitionDefinition;
import org.springframework.webflow.execution.EnterStateVetoException;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.FlowExecutionException;
import org.springframework.webflow.execution.FlowExecutionListener;
import org.springframework.webflow.execution.FlowSession;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.View;

/**
 * Classe permettant de logguer les différentes phases du cycle de view d'un
 * flow Spring WebFlow.
 * @author sebastien.helbert
 */
public class WebFlowProfilerListener implements FlowExecutionListener {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void requestSubmitted(RequestContext context) {
        logger.debug("requestSubmitted");
    }
    @Override
    public void requestProcessed(RequestContext context) {
        logger.debug("requestProcessed");
    }
    @Override
    public void sessionCreating(RequestContext context,
                                FlowDefinition definition) {
        logger.debug("sessionCreating");

    }
    @Override
    public void sessionStarting(RequestContext context,
                                FlowSession session,
                                MutableAttributeMap input) {
        logger.debug("sessionStarting");
    }
    @Override
    public void sessionStarted(RequestContext context,
                               FlowSession session) {
        logger.debug("sessionStarted");

    }
    @Override
    public void eventSignaled(RequestContext context,
                              Event event) {
        logger.debug("eventSignaled, event={}", event.getId());

    }
    @Override
    public void transitionExecuting(RequestContext context,
                                    TransitionDefinition transition) {
        logger.debug("transitionExecuting, state={}", transition.getId());
    }
    @Override
    public void stateEntering(RequestContext context,
                              StateDefinition state) throws EnterStateVetoException {
        logger.debug("viewRendering, state={}", state.getId());
    }
    @Override
    public void stateEntered(RequestContext context,
                             StateDefinition previousState,
                             StateDefinition state) {
        logger.debug("stateEntered, previousState={}, state={}", previousState == null ? null : previousState.getId(), state.getId());
    }
    @Override
    public void viewRendering(RequestContext context,
                              View view,
                              StateDefinition viewState) {
        logger.debug("viewRendering, view={}, viewState={}", view, viewState.getId());

    }
    @Override
    public void viewRendered(RequestContext context,
                             View view,
                             StateDefinition viewState) {
        logger.debug("viewRendered, view={}, viewState={}", view, viewState.getId());
    }
    @Override
    public void paused(RequestContext context) {
        logger.debug("paused");

    }
    @Override
    public void resuming(RequestContext context) {
        logger.debug("resuming");

    }
    @Override
    public void sessionEnding(RequestContext context,
                              FlowSession session,
                              String outcome,
                              MutableAttributeMap output) {
        logger.debug("sessionEnded, outcome={}", outcome);
    }
    @Override
    public void sessionEnded(RequestContext context,
                             FlowSession session,
                             String outcome,
                             AttributeMap output) {
        logger.debug("sessionEnded, outcome={}", outcome);
    }
    @Override
    public void exceptionThrown(RequestContext context,
                                FlowExecutionException exception) {
        logger.debug("exceptionThrown : {}", exception.getClass());
    }
}

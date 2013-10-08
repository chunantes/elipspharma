package fr.pharma.eclipse.utils;

import org.springframework.webflow.conversation.impl.SessionBindingConversationManager;
import org.springframework.webflow.execution.repository.impl.DefaultFlowExecutionRepository;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.executor.FlowExecutorImpl;

/**
 * Configuration de base du WebFlow.<br>
 * Utiliser pour configurer le timeout du conversation lock.<br>
 * Src: https://jira.springsource.org/browse/SWF-1059
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class WebFlowHelper {
    private final FlowExecutor flowExecutor;

    public WebFlowHelper(final FlowExecutor flowExecutor) {
        this.flowExecutor = flowExecutor;
    }

    public FlowExecutor getFlowExecutor() {
        return this.flowExecutor;
    }

    public SessionBindingConversationManager getConversationManager() {
        return (SessionBindingConversationManager) ((DefaultFlowExecutionRepository) ((FlowExecutorImpl) this.flowExecutor).getExecutionRepository()).getConversationManager();
    }

    public int getLockTimeoutSeconds() {
        return this.getConversationManager().getLockTimeoutSeconds();
    }

    public void setLockTimeoutSeconds(final int lockTimeoutSeconds) {
        this.getConversationManager().setLockTimeoutSeconds(lockTimeoutSeconds);
    }

}

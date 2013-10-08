package fr.pharma.eclipse.utils;

import java.util.Iterator;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseStream;
import javax.faces.context.ResponseWriter;
import javax.faces.render.RenderKit;

/**
 * Classe utilitaire pour mocker le Faces Context.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class FacesContextUtils extends FacesContext {

    /**
     * Méthode en charge de setter l'instance courante du FacesContext.
     * @param context Contexte.
     */
    protected void setFacesContext(final FacesContext context) {
        super.setCurrentInstance(context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setResponseWriter(final ResponseWriter responseWriter) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addMessage(final String clientId,
                           final FacesMessage message) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Application getApplication() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<String> getClientIdsWithMessages() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ExternalContext getExternalContext() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Severity getMaximumSeverity() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<FacesMessage> getMessages() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<FacesMessage> getMessages(final String clientId) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RenderKit getRenderKit() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getRenderResponse() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getResponseComplete() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseStream getResponseStream() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseWriter getResponseWriter() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UIViewRoot getViewRoot() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void release() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderResponse() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void responseComplete() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setResponseStream(final ResponseStream responseStream) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setViewRoot(final UIViewRoot root) {
    }
}

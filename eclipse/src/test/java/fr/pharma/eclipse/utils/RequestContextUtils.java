package fr.pharma.eclipse.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.primefaces.context.RequestContext;

/**
 * Classe utilitaire pour mocker le Request Context.
 
 * @version $Revision$ $Date$
 */
public class RequestContextUtils
    extends RequestContext
{

    /**
     * Méthode en charge de setter l'instance courante du RequestContext.
     * @param context Contexte.
     */
    protected void setRequestContext(final RequestContext context)
    {
        super.setCurrentInstance(context);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAjaxRequest()
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void release()
    {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCallbackParam(final String name,
                                 final Object value)
    {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Object> getCallbackParams()
    {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPartialUpdateTarget(final String name)
    {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPartialUpdateTargets(final Collection<String> collection)
    {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getPartialUpdateTargets()
    {
        return null;
    }

}

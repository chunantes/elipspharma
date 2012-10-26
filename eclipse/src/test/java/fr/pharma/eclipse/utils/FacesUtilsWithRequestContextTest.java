package fr.pharma.eclipse.utils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.primefaces.context.RequestContext;

import fr.pharma.eclipse.utils.message.EclipseMessageBuilder;

/**
 * Classe en charge de tester les méthodes accédant à la requête Primefaces, dans la classe
 * utilitaire Faces.
 
 * @version $Revision$ $Date$
 */
public class FacesUtilsWithRequestContextTest
    extends RequestContextUtils
{
    /**
     * FacesUtils à tester.
     */
    private FacesUtils facesUtils;

    /**
     * EclipseMessageBuilder mocké.
     */
    private EclipseMessageBuilder mockMessageBuilder;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init()
    {
        this.facesUtils = new FacesUtils();
        this.mockMessageBuilder = Mockito.mock(EclipseMessageBuilder.class);
        this.facesUtils.setMessageBuilder(this.mockMessageBuilder);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end()
    {
        this.facesUtils = null;
        this.mockMessageBuilder = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.facesUtils);
        Assert.assertNotNull(this.mockMessageBuilder);
    }

    /**
     * Test de la méthode putCallbackParam.
     */
    @Test
    public void testPutCallbackParam()
    {
        final RequestContext requestContext = Mockito.mock(RequestContext.class);
        super.setRequestContext(requestContext);
        final String paramName = "paramName";
        final String paramValue = "paramValue";
        this.facesUtils.putCallbackParam(paramName,
                                         paramValue);
        Mockito.verify(requestContext).addCallbackParam(paramName,
                                                        paramValue);
    }

    /**
     * Test de la méthode putCallbackValidityParam.
     */
    @Test
    public void testPutCallbackValidityParam()
    {
        final RequestContext requestContext = Mockito.mock(RequestContext.class);
        super.setRequestContext(requestContext);
        final boolean isValid = false;
        this.facesUtils.putCallbackValidityParam(isValid);
        Mockito.verify(requestContext).addCallbackParam("isValid",
                                                        isValid);
    }
}

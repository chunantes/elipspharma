package fr.pharma.eclipse.redirect;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.utils.FacesContextUtils;

/**
 * Classe en charge de tester le gestionnaire de redirection.
 
 * @version $Revision$ $Date$
 */
public class RedirectHandlerTest
    extends FacesContextUtils
{
    /**
     * RedirectHandler à tester.
     */
    private RedirectHandler redirectHandler;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init()
    {
        this.redirectHandler = new RedirectHandler();
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end()
    {
        this.redirectHandler = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.redirectHandler);
    }

    /**
     * Méthode en charge de tester la redirection vers une url.
     * @throws IOException Exception Input/Output.
     */
    @Test
    public void testRedirectOK()
        throws IOException
    {
        final String urlRedirect = "urlRedirect";
        final FacesContext facesContext = Mockito.mock(FacesContext.class);
        super.setFacesContext(facesContext);
        final ExternalContext externalContext = Mockito.mock(ExternalContext.class);
        Mockito.when(facesContext.getExternalContext()).thenReturn(externalContext);
        this.redirectHandler.redirect(urlRedirect);
        Mockito.verify(externalContext).redirect(urlRedirect);
        Mockito.verify(facesContext).responseComplete();
    }

    /**
     * Méthode en charge de tester la redirection vers une url.
     * @throws IOException Exception Input/Output.
     */
    @Test
    public void testRedirectWithException()
        throws IOException
    {
        final String urlRedirect = "urlRedirect";
        final FacesContext facesContext = Mockito.mock(FacesContext.class);
        super.setFacesContext(facesContext);
        final ExternalContext externalContext = Mockito.mock(ExternalContext.class);
        Mockito.when(facesContext.getExternalContext()).thenReturn(externalContext);
        Mockito.doThrow(new IOException()).when(externalContext).redirect(urlRedirect);
        this.redirectHandler.redirect(urlRedirect);
    }

}

package fr.pharma.eclipse.utils;

import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Classe utilitaire pour créer un contexte de sécurité factice pour les tests.
 
 * @version $Revision$ $Date$
 */
public final class SecurityUtils
{
    /**
     * Constructeur privé.
     */
    private SecurityUtils()
    {
        super();
    }

    /**
     * Méthode de configuration du contexte de sécurité Spring.
     * @param login Login de la personne connectée.
     */
    public static void mockContext(final String login)
    {
        final SecurityContext mockedContext = Mockito.mock(SecurityContext.class);
        final Authentication mockedAuthentication = Mockito.mock(Authentication.class);
        Mockito.when(mockedContext.getAuthentication()).thenReturn(mockedAuthentication);
        Mockito.when(mockedAuthentication.getName()).thenReturn(login);
        SecurityContextHolder.setContext(mockedContext);
    }
}

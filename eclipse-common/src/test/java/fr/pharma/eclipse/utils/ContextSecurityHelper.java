package fr.pharma.eclipse.utils;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import fr.pharma.eclipse.domain.enums.RolePersonne;
import fr.pharma.eclipse.domain.model.user.UserSecurity;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe utilitaire de test pour charger un contexte de sécurité.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ContextSecurityHelper {

    /**
     * Méthode de création d'un contexte de sécurité admin pharmacien.
     */
    public static void createSecurityContextMock() {
        final SecurityContext contextMocked = Mockito.mock(SecurityContext.class);
        final Authentication authMocked = Mockito.mock(Authentication.class);
        SecurityContextHolder.setContext(contextMocked);
        SecurityContextHolder.getContext().setAuthentication(authMocked);

        Mockito.when(contextMocked.getAuthentication()).thenReturn(authMocked);
        Mockito.when(authMocked.getName()).thenReturn("admin");

        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(EclipseConstants.ROLE + RolePersonne.PHARMACIEN));

        Mockito.when(contextMocked.getAuthentication().getPrincipal()).thenReturn(new UserSecurity("admin", "password", "salt", authorities, 3L, RolePersonne.PHARMACIEN, true));
    }

    /**
     * Méthode de création d'un contexte de sécurité pharmacien.
     */
    public static void createSecurityContextPharmacienMock() {
        final SecurityContext contextMocked = Mockito.mock(SecurityContext.class);
        final Authentication authMocked = Mockito.mock(Authentication.class);
        SecurityContextHolder.setContext(contextMocked);
        SecurityContextHolder.getContext().setAuthentication(authMocked);

        Mockito.when(contextMocked.getAuthentication()).thenReturn(authMocked);

        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(EclipseConstants.ROLE + RolePersonne.PHARMACIEN));

        Mockito.when(contextMocked.getAuthentication().getPrincipal()).thenReturn(new UserSecurity("pharmacien", "password", "salt", authorities, 10L, RolePersonne.PHARMACIEN,
                                                                                                   false));
    }

    /**
     * Méthode de création d'un contexte de sécurité investigateur.
     */
    public static void createSecurityContextInvestigateurMock() {
        final SecurityContext contextMocked = Mockito.mock(SecurityContext.class);
        final Authentication authMocked = Mockito.mock(Authentication.class);
        SecurityContextHolder.setContext(contextMocked);
        SecurityContextHolder.getContext().setAuthentication(authMocked);

        Mockito.when(contextMocked.getAuthentication()).thenReturn(authMocked);

        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(EclipseConstants.ROLE + RolePersonne.INVESTIGATEUR));

        Mockito.when(contextMocked.getAuthentication().getPrincipal()).thenReturn(new UserSecurity("investigateur", "password", "salt", authorities, 1L,
                                                                                                   RolePersonne.INVESTIGATEUR, false));
    }

}

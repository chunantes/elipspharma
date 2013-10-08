package fr.pharma.eclipse.service.actualite.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;

import fr.pharma.eclipse.dao.search.AclSearchDao;
import fr.pharma.eclipse.domain.model.actualite.Actualite;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.utils.ContextSecurityHelper;

/**
 * Classe en charge de tester le service de gestion des actualités.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ActualiteServiceImplTest {

    /**
     * ActualiteServiceImpl à tester.
     */
    @InjectMocks
    private ActualiteServiceImpl service;

    /**
     * Service de gestion des services mocké.
     */
    @Mock
    private EssaiService mockEssaiService;

    /**
     * DAO de recherche des acls mocké.
     */
    @Mock
    private AclSearchDao mockAclSearchDao;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        ContextSecurityHelper.createSecurityContextMock();
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInitData() {
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.mockEssaiService);
        Assert.assertNotNull(this.mockAclSearchDao);
        SecurityContextHolder.clearContext();
    }

    /**
     * Méthode en charge de tester la récupération des derniers essais pour un
     * profil admin
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetLastEssaisAdmin() {

        final List<Long> idsEssais = new ArrayList<Long>();
        idsEssais.add(1L);
        idsEssais.add(2L);
        Mockito.when(this.mockAclSearchDao.findIdsEssais()).thenReturn(idsEssais);
        final String paramIdsEssais = Arrays.toString(idsEssais.toArray(new Object[idsEssais.size()])).replace("[", "(").replace("]", ")");

        Mockito.when(this.mockEssaiService.executeSQLQuery(MessageFormat.format(ActualiteServiceImpl.REQ_ACTUALITES, paramIdsEssais), null, ActualiteServiceImpl.COLS,
                                                           Actualite.class)).thenReturn(Mockito.mock(List.class));

        final List<Actualite> result = this.service.getLastEssais();
        Assert.assertNotNull(result);
    }
}

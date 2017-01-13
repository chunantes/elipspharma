package fr.pharma.eclipse.dao.search;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.dto.EssaiDTO;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.utils.ContextSecurityHelper;

/**
 * Classe en charge de tester la DAO de recherche des essais.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class EssaiSearchDaoTest {

    /**
     * DAO de recherche des essais.
     */
    @Autowired
    private EssaiSearchDao essaiSearchDao;
    
    @Autowired
    private GenericDao<Essai> essaiDao;

    /**
     * Initialisation des données de test.
     */
    @Before
    public void setUp() {
        ContextSecurityHelper.createSecurityContextMock();
    }

    /**
     * Purge des données de test.
     */
    @After
    public void tearDown() {
        SecurityContextHolder.clearContext();
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.dao.search.EssaiSearchDao#findEssaiDTOByNumInterneOrNomOrPromoteur(java.lang.String)}
     * .
     */
    @Ignore("Non testable sous la base en mémoire H2")
    @Test
    public void testFindEssaiDTOByNumInterneOrNomOrPromoteur() {
        final List<EssaiDTO> essaiDTOs = this.essaiSearchDao.findEssaiDTOByNumInterneOrNomOrPromoteur("Essai 1");
        Assert.assertEquals(1, essaiDTOs.size());
        final EssaiDTO result = essaiDTOs.get(0);
        Assert.assertEquals(1L, result.getId().longValue());
        Assert.assertEquals("Essai 1", result.getNom());
        Assert.assertEquals("2010-01", result.getNumInterne());
        Assert.assertEquals("AMGEN", result.getRaisonSociale());
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.dao.search.EssaiSearchDao#findEssaiDTOById(java.lang.Long)}
     * .
     */
    @Test
    public void testFindEssaiDTOById() {
        final List<EssaiDTO> essaiDTOs = this.essaiSearchDao.findEssaiDTOById(1L);
        Assert.assertEquals(1, essaiDTOs.size());
        final EssaiDTO result = essaiDTOs.get(0);
        Assert.assertEquals(1L, result.getId().longValue());
        Assert.assertEquals("Essai 1", result.getNom());
        Assert.assertEquals("2010-01", result.getNumInterne());
        Assert.assertEquals("AMGEN", result.getRaisonSociale());
    }

}

package fr.pharma.eclipse.dao.search;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.domain.model.acl.AclPharmacie;
import fr.pharma.eclipse.utils.ContextSecurityHelper;

/**
 * Classe en charge de tester la DAO de recherche des ACLs.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext.xml" })
@Transactional
public class AclSearchDaoTest {

    /**
     * DAO de recherche des ACLs.
     */
    @Autowired
    private AclSearchDao aclSearchDao;

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
     * {@link fr.pharma.eclipse.dao.search.AclSearchDao#findIdsEssais()} .
     */
    @Test
    public void testFindIdsEssais() {
        final List<Long> idsEssais = this.aclSearchDao.findIdsEssais();
        Assert.assertEquals(2, idsEssais.size());
        Assert.assertEquals(1L, idsEssais.get(0).longValue());
        Assert.assertEquals(2L, idsEssais.get(1).longValue());
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.dao.search.AclSearchDao#findIdsPharmacies()} .
     */
    @Test
    public void testFindIdsPharmacies() {
        final List<Long> idsPharmacies = this.aclSearchDao.findIdsPharmacies();
        Assert.assertEquals(5, idsPharmacies.size());
        Assert.assertEquals(1L, idsPharmacies.get(0).longValue());
        Assert.assertEquals(2L, idsPharmacies.get(1).longValue());
        Assert.assertEquals(3L, idsPharmacies.get(2).longValue());
        Assert.assertEquals(4L, idsPharmacies.get(3).longValue());
        Assert.assertEquals(5L, idsPharmacies.get(4).longValue());
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.dao.search.AclSearchDao#deleteAclsPharmacies()}
     * .
     */
    @Test
    public void testRemoveAclsPharmaciesOfPersonne() {
        try {
            this.aclSearchDao.removeAclsPharmaciesOfPersonne(77L);
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.dao.search.AclSearchDao#saveAclsPharmaciesForAdmin()}
     * .
     */
    @Test
    public void testSaveAclsPharmaciesForAdmin() {
        try {
            final Long idPersonneAdmin = 3L;
            this.aclSearchDao.removeAclsPharmaciesOfPersonne(idPersonneAdmin);
            this.aclSearchDao.saveAclsPharmaciesForAdmin(idPersonneAdmin);
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.dao.search.AclSearchDao#saveAclsPharmacies()} .
     */
    @Test
    public void testSaveAclsPharmacies() {
        try {
            final Long idPersonneAdmin = 3L;
            final Long idPharmacie = 12L;
            final List<AclPharmacie> aclPharmacies = new ArrayList<AclPharmacie>();
            aclPharmacies.add(new AclPharmacie(idPersonneAdmin, idPharmacie));
            this.aclSearchDao.saveAclsPharmacies(aclPharmacies);
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.dao.search.AclSearchDao#removeAclsPharmaciesOfPharma()}
     * .
     */
    @Test
    public void testRemoveAclsPharmaciesOfPharma() {
        try {
            final Long idPharmacie = 22L;
            this.aclSearchDao.removeAclsPharmaciesOfPharma(idPharmacie);
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.dao.search.AclSearchDao#removeAclsEssaisOfEssai()}
     * .
     */
    @Test
    public void testRemoveAclsEssaisOfEssai() {
        try {
            final Long idEssai = 22L;
            this.aclSearchDao.removeAclsEssaisOfEssai(idEssai);
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.dao.search.AclSearchDao#saveAclsPharmaciesForAdmins()}
     * .
     */
    @Test
    public void testSaveAclsPharmaciesForAdmins() {
        try {
            final Long idPharmacie = 22L;
            this.aclSearchDao.saveAclsPharmaciesForAdmins(idPharmacie);
            this.aclSearchDao.removeAclsPharmaciesOfPharma(idPharmacie);
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Méthode de test de la sauvegarde des essais.
     */
    @Test
    public void testSaveAclsEssais() {
        try {
            final Long idEssai = 22L;
            this.aclSearchDao.saveAclsEssaisForAdmins(idEssai);
            this.aclSearchDao.saveAclsEssaisForPharmaciens(idEssai);
            this.aclSearchDao.saveAclsEssaisForOthersProfils(idEssai);
            this.aclSearchDao.removeAclsEssaisOfEssai(idEssai);
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Méthode de test de la sauvegarde des acls essais pour un admin.
     */
    @Test
    public void testSaveAclsEssaisForAdmin() {
        try {
            final Long idPersonne = 22L;
            this.aclSearchDao.saveAclsEssaisForAdmin(idPersonne);
            this.aclSearchDao.removeAclsEssaisOfPersonne(idPersonne);
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Méthode de test de la sauvegarde des acls essais pour un pharmacien.
     */
    @Test
    public void testSaveAclsEssaisForPharmacien() {
        try {
            final Long idPersonne = 10299L;
            final List<Long> idsPharmacies = new ArrayList<Long>();
            idsPharmacies.add(1L);
            this.aclSearchDao.saveAclsEssaisForPharmacien(idPersonne, idsPharmacies);
            this.aclSearchDao.removeAclsEssaisOfPersonne(idPersonne);
        } catch (final Exception e) {
            Assert.fail(e.getMessage());
        }
    }

}

package fr.pharma.eclipse.dao.search;

import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.domain.dto.DispensationDTO;
import fr.pharma.eclipse.domain.dto.EssaiDTO;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.patient.PatientService;
import fr.pharma.eclipse.utils.ContextSecurityHelper;

/**
 * Classe en charge de tester la DAO de recherche des dispensations.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class DispensationSearchDaoTest {

    /**
     * DAO de recherche des dispensations.
     */
    @Resource(name = "dispensationSearchDao")
    private DispensationSearchDao dispensationSearchDao;

    /**
     * Service de gestion des patients.
     */
    @Resource(name = "patientService")
    private PatientService patientService;

    /**
     * Service de gestion des essais.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

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
     * {@link fr.pharma.eclipse.dao.search.DispensationSearchDao#findByEssaiAndPatientAndDispenseOrderByIdAsc()}
     */
    @Test
    public void testFindByEssaiAndPatientAndDispenseOrderByIdAsc() {

        final Patient patient = this.patientService.get(1L);
        final Essai essai = this.essaiService.get(1L);
        final EssaiDTO essaiDTO = new EssaiDTO();
        essaiDTO.setId(essai.getId());

        final List<DispensationDTO> dispensations = this.dispensationSearchDao.findByEssaiAndPatientAndDispenseOrderByIdAsc(essaiDTO, patient, Boolean.FALSE);

        Assert.assertEquals(1, dispensations.size());

        final DispensationDTO dispensation = dispensations.get(0);
        Assert.assertEquals(1L, dispensation.getId().longValue());
        Assert.assertEquals(1L, dispensation.getIdEssai().longValue());
        Assert.assertEquals("Essai 1", dispensation.getNomEssai());
        Assert.assertEquals("P01-C", dispensation.getCodePromoteur());
        Assert.assertEquals("Dupond", dispensation.getNomPatient());
        Assert.assertEquals("Marcel", dispensation.getPrenomPatient());
        Assert.assertNull(dispensation.getInitialesPatient());
        Assert.assertEquals(1, dispensation.getNumPrescription().intValue());
        Assert.assertEquals("1", dispensation.getNumInclusion());
        Assert.assertFalse(dispensation.getDispense());
        Assert.assertNotNull(dispensation.getDatePrescription());
        Assert.assertEquals("AMGEN", dispensation.getRaisonSociale());
        Assert.assertNull(dispensation.getDateDispensation());
        Assert.assertEquals(1, dispensation.getNumOrdonnancier().intValue());
    }

    /**
     * Test method for
     * {@link fr.pharma.eclipse.dao.search.DispensationSearchDao#findProduitsByIdDispensation()}
     */
    @Test
    public void testFindProduitsByIdDispensation() {
        final List<Object[]> dispensationsProduits = this.dispensationSearchDao.findProduitsByIdDispensation(1L);
        Assert.assertNotNull(dispensationsProduits);
    }

}

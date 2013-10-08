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

import fr.pharma.eclipse.domain.dto.EssaiDTO;
import fr.pharma.eclipse.domain.dto.PrescriptionDTO;
import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.enums.TypeDispensation;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.patient.PatientService;
import fr.pharma.eclipse.utils.ContextSecurityHelper;

/**
 * Classe en charge de tester la DAO de recherche des prescriptions.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext.xml" })
@Transactional
public class PrescriptionSearchDaoTest {

    /**
     * DAO de recherche des prescriptions.
     */
    @Resource(name = "prescriptionSearchDao")
    private PrescriptionSearchDao prescriptionSearchDao;

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
     * {@link fr.pharma.eclipse.dao.search.PrescriptionSearchDao#findByEssaiAndPatientAndDispenseOrderByIdAsc()}
     */
    @Test
    public void testFindByEssaiAndPatientAndDispenseOrderByIdAsc() {

        final Patient patient = this.patientService.get(1L);
        final Essai essai = this.essaiService.get(1L);
        final EssaiDTO essaiDTO = new EssaiDTO();
        essaiDTO.setId(essai.getId());

        final List<PrescriptionDTO> prescriptions = this.prescriptionSearchDao.findByEssaiAndPatientAndDispenseOrderByIdAsc(essaiDTO, patient, Boolean.FALSE);

        Assert.assertEquals(1, prescriptions.size());
        final PrescriptionDTO pDto = prescriptions.get(0);
        Assert.assertEquals(1L, pDto.getNbDispensations().longValue());
        Assert.assertEquals(1L, pDto.getId().longValue());
        Assert.assertEquals(1L, pDto.getIdEssai().longValue());
        Assert.assertEquals("Essai 1", pDto.getNomEssai());
        Assert.assertEquals("P01-C", pDto.getCodePromoteur());
        Assert.assertEquals("Dupond", pDto.getNomPatient());
        Assert.assertEquals("Marcel", pDto.getPrenomPatient());
        Assert.assertNull(pDto.getInitialesPatient());
        Assert.assertEquals(1, pDto.getNumPrescription().intValue());
        Assert.assertEquals("1", pDto.getNumInclusion());
        Assert.assertFalse(pDto.getDispense());
        Assert.assertEquals(EtatEssai.EN_EVALUATION, pDto.getEtatEssai());
        Assert.assertTrue(pDto.getInclusionActive());
        Assert.assertEquals(TypeDispensation.NOMINATIVE, pDto.getTypeDispensation());
        Assert.assertNotNull(pDto.getDatePrescription());
    }

}

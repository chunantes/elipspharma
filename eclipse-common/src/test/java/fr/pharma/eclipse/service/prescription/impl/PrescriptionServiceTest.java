package fr.pharma.eclipse.service.prescription.impl;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.prescription.PrescriptionSearchCriteria;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.validator.save.impl.PrescriptionSaveValidator;

/**
 * Classe de test du service pour le bean métier Prescription.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class PrescriptionServiceTest {
    /**
     * Service.
     */
    private PrescriptionServiceImpl service;

    /**
     * Mock de la DAO.
     */
    private GenericDao<Prescription> mockedDao;

    /**
     * Validator mocké.
     */
    private PrescriptionSaveValidator validator;

    /**
     * Méthode d'initialisation.
     * @throws Exception Exception.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception {
        this.mockedDao = Mockito.mock(GenericDao.class);
        this.validator = Mockito.mock(PrescriptionSaveValidator.class);
        this.service = new PrescriptionServiceImpl(this.mockedDao);
        this.service.setPrscriptionSaveValidator(this.validator);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.service = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.mockedDao);
    }

    /**
     * Crée un Prescription pour le test.
     * @return Editeur initialisé.
     */
    private Prescription makePrescriptionForTest() {
        final Prescription prescription = new Prescription();
        prescription.setId(1L);
        return prescription;
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final Prescription prescription = this.makePrescriptionForTest();
        Mockito.when(this.mockedDao.save(prescription)).thenReturn(prescription);
        Prescription result = null;
        result = this.service.save(prescription);
        Mockito.verify(this.validator).validate(Matchers.any(Prescription.class), Matchers.any(PrescriptionServiceImpl.class));
        Assert.assertEquals(result, prescription);
    }

    /**
     * Test de la méthode getAll.
     */
    @Test
    public void testGetAll() {
        Mockito.when(this.mockedDao.getAll(Matchers.any(SearchCriteria.class))).thenReturn(new ArrayList<Prescription>());
        this.service.getAll();
        Mockito.verify(this.mockedDao).getAll(Matchers.any(SearchCriteria.class));
    }

    /**
     * Test de la méthode getAll.
     */
    @Test
    public void testGetAllCriteria() {
        final PrescriptionSearchCriteria criteria = Mockito.mock(PrescriptionSearchCriteria.class);
        Mockito.when(this.mockedDao.getAll(criteria)).thenReturn(new ArrayList<Prescription>());
        this.service.getAll(criteria);
        Mockito.verify(this.mockedDao).getAll(criteria);
    }
}

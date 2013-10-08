package fr.pharma.eclipse.service.dispensation.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.dispensation.Dispensation;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.validator.save.impl.DispensationSaveValidator;

/**
 * Classe de test du service pour le bean métier Dispensation.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class DispensationServiceTest {
    /**
     * Service.
     */
    private DispensationServiceImpl service;

    /**
     * Mock de la DAO.
     */
    private GenericDao<Dispensation> mockedDao;

    /**
     * Validator mocké.
     */
    private DispensationSaveValidator validator;

    /**
     * Service pharmacie.
     */
    private PharmacieService pharmacieService;

    /**
     * Méthode d'initialisation.
     * @throws Exception Exception.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception {
        this.pharmacieService = Mockito.mock(PharmacieService.class);
        this.mockedDao = Mockito.mock(GenericDao.class);
        this.validator = Mockito.mock(DispensationSaveValidator.class);
        this.service = new DispensationServiceImpl(this.mockedDao);
        this.service.setDispensationSaveValidator(this.validator);
        this.service.setPharmacieService(this.pharmacieService);
    }

    /**
     * Méthode de finalisation.
     * @throws Exception Exception.
     */
    @After
    public void tearDown() throws Exception {
        this.mockedDao = null;
        this.pharmacieService = null;
        this.service = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.mockedDao);
        Assert.assertNotNull(this.pharmacieService);
    }

    /**
     * Crée un Dispensation pour le test.
     * @return Editeur initialisé.
     */
    private Dispensation makeDispensationForTest() {
        final Dispensation dispensation = new Dispensation();
        dispensation.setId(1L);
        return dispensation;
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final Dispensation dispensation = this.makeDispensationForTest();
        dispensation.setDispense(true);
        Mockito.when(this.mockedDao.save(dispensation)).thenReturn(dispensation);
        Dispensation result = null;
        result = this.service.save(dispensation);
        Mockito.verify(this.validator).validate(Matchers.any(Dispensation.class), Matchers.any(DispensationServiceImpl.class));
        Assert.assertEquals(result, dispensation);
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testGenererNumOrdonanncierOK() {
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        Mockito.when(pharmacie.getNumOrdonnancierDisp()).thenReturn(1);

        final Dispensation dispensation = this.makeDispensationForTest();
        dispensation.setPharmacie(pharmacie);

        dispensation.setDispense(true);
        Mockito.when(this.mockedDao.save(dispensation)).thenReturn(dispensation);
        Dispensation result = null;
        result = this.service.save(dispensation);
        Mockito.verify(this.validator).validate(Matchers.any(Dispensation.class), Matchers.any(DispensationServiceImpl.class));
        Assert.assertEquals(result, dispensation);
    }
}

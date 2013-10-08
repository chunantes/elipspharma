package fr.pharma.eclipse.service.dispensation.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.stock.DispensationProduit;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;

/**
 * Classe de test du service pour le bean métier DispensationProduit.
 * @author NETAPSYS
 * @version $Revision$ $Date$
 */
public class DispensationProduitServiceTest {
    /**
     * Service.
     */
    private GenericServiceImpl<DispensationProduit> service;

    /**
     * Mock de la DAO.
     */
    private GenericDao<DispensationProduit> mockedDao;

    /**
     * String indiquant un point de passage normalement non atteint.
     */
    private static final String NOT_TO_BE_REACHED = "Passage à un point normalement non atteint";

    /**
     * Méthode d'initialisation.
     * @throws Exception Exception.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void setUp() throws Exception {
        this.mockedDao = Mockito.mock(GenericDao.class);
        this.service = new GenericServiceImpl<DispensationProduit>(this.mockedDao);
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
     * Crée un patient pour le test.
     * @return Editeur initialisé.
     */
    private DispensationProduit makeDispensationProduitTest() {
        final DispensationProduit patient = new DispensationProduit();
        patient.setId(1L);
        return patient;
    }

    /**
     * Test de la méthode get(int) OK.
     */
    @Test
    public void testGetIntOk() {
        final DispensationProduit patientExpected = this.makeDispensationProduitTest();
        final Long idTest = 1L;
        Mockito.when(this.mockedDao.get(idTest)).thenReturn(patientExpected);

        final DispensationProduit patient = this.service.get(idTest);

        Assert.assertNotNull(patient);
        Assert.assertEquals(patientExpected, patient);
    }

    /**
     * Test de la méthode get(int) KO.
     */
    @Test
    public void testGetIntKo() {
        final Long idTest = 1L;
        final MockitoException exception = new MockitoException("testGetIntKo");
        Mockito.when(this.mockedDao.get(idTest)).thenThrow(exception);
        try {
            this.service.get(idTest);
            Assert.fail(DispensationProduitServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de la méthode save OK.
     */
    @Test
    public void testSaveOK() {
        final DispensationProduit patientToSave = this.makeDispensationProduitTest();
        Mockito.when(this.mockedDao.save(patientToSave)).thenReturn(patientToSave);
        DispensationProduit patientSaved = null;
        patientSaved = this.service.save(patientToSave);
        Assert.assertEquals(patientSaved, patientToSave);
    }

    /**
     * Test de la méthode save KO.
     */
    @Test
    public void testSaveKO() {
        final DispensationProduit patientToSave = this.makeDispensationProduitTest();
        final MockitoException exception = new MockitoException("testSaveKO");
        Mockito.when(this.mockedDao.save(patientToSave)).thenThrow(exception);
        try {
            this.service.save(patientToSave);
            Assert.fail(DispensationProduitServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }

    /**
     * Test de l'appel de suppression OK par le manager.
     */
    @Test
    public void testRemoveOK() {
        final DispensationProduit bean = Mockito.mock(DispensationProduit.class);
        Mockito.when(this.mockedDao.reattach(bean)).thenReturn(bean);
        this.service.remove(bean);
        Mockito.verify(this.mockedDao).reattach(bean);
        Mockito.verify(this.mockedDao).remove(bean);
    }

    /**
     * Test de la suppression d'une liste de patients.
     */
    @Test
    public void testRemoveListe() {
        final List<DispensationProduit> beans = new ArrayList<DispensationProduit>();
        final DispensationProduit bean1 = Mockito.mock(DispensationProduit.class);
        final DispensationProduit bean2 = Mockito.mock(DispensationProduit.class);
        beans.add(bean1);
        beans.add(bean2);
        this.service.remove(beans);
        Mockito.verify(this.mockedDao, Mockito.times(2)).reattach((DispensationProduit) Matchers.any());
        Mockito.verify(this.mockedDao, Mockito.times(2)).remove((DispensationProduit) Matchers.any());
    }

    /**
     * Test de l'appel de suppression KO par le manager.
     */
    @Test
    public void testRemoveKO() {
        final DispensationProduit bean = Mockito.mock(DispensationProduit.class);
        final MockitoException exception = new MockitoException("testRemoveKO");
        Mockito.doThrow(exception).when(this.mockedDao).reattach(bean);
        try {
            this.service.remove(bean);
            Assert.fail(DispensationProduitServiceTest.NOT_TO_BE_REACHED);
        } catch (final MockitoException exceptionLeve) {
            Assert.assertEquals(exception, exceptionLeve);
        }
    }
}

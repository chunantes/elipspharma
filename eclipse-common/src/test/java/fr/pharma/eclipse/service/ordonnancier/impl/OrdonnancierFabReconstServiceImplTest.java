package fr.pharma.eclipse.service.ordonnancier.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.criteria.ordonnancier.OrdonnancierSearchCriteria;
import fr.pharma.eclipse.domain.model.ordonnancier.OrdonnancierFabReconst;
import fr.pharma.eclipse.domain.model.stock.PreparationEntree;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.exception.ValidationException;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.service.stock.ApprovisionnementService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester le service de gestion des ordonnanciers de
 * fabrications/reconstitutions.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class OrdonnancierFabReconstServiceImplTest {
    /**
     * OrdonnancierFabReconstServiceImpl à tester.
     */
    private OrdonnancierFabReconstServiceImpl service;

    /**
     * Mock de la dao des ordonnanciers de fabrication/reconstitution.
     */
    private GenericDao<OrdonnancierFabReconst> mockDao;

    /**
     * Factory d'ordonnancier de fabrication/reconstitution mocké.
     */
    private SuiviFactory<OrdonnancierFabReconst> mockOrdonnancierFactory;

    /**
     * Service de gestion des pharmacies mocké.
     */
    private ApprovisionnementService<PreparationEntree> mockApproService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.mockDao = Mockito.mock(GenericDao.class);
        this.service = new OrdonnancierFabReconstServiceImpl(this.mockDao);
        this.mockOrdonnancierFactory = Mockito.mock(SuiviFactory.class);
        this.mockApproService = Mockito.mock(ApprovisionnementService.class);
        this.service.setOrdoFactory(this.mockOrdonnancierFactory);
        this.service.setApprovisionnementService(this.mockApproService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.mockDao = null;
        this.service = null;
        this.mockOrdonnancierFactory = null;
        this.mockApproService = null;
    }

    /**
     * Classe en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.mockDao);
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.mockApproService);
        Assert.assertNotNull(this.mockOrdonnancierFactory);
    }

    /**
     * Méthode en charge de tester la récupération de la date de début d'un
     * ordonnancier pour une pharmacie.
     */
    @Test
    public void testGetDateDebutWithOrdonnancierEmpty() {
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        final List<OrdonnancierFabReconst> ordonnanciers = new ArrayList<OrdonnancierFabReconst>();
        Mockito.when(this.mockDao.getAll((SearchCriteria) Matchers.any())).thenReturn(ordonnanciers);
        final Calendar dateDebut = this.service.getDateDebut(pharmacie);
        Assert.assertEquals(OrdonnancierServiceImpl.AN_2011, dateDebut.get(Calendar.YEAR));
        Assert.assertEquals(Calendar.JANUARY, dateDebut.get(Calendar.MONTH));
        Assert.assertEquals(1, dateDebut.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Méthode en charge de tester la récupération de la date de début d'un
     * ordonnancier pour une pharmacie.
     */
    @Test
    public void testGetDateDebutWithOrdonnancierNotEmpty() {
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        final List<OrdonnancierFabReconst> ordonnanciers = new ArrayList<OrdonnancierFabReconst>();
        final OrdonnancierFabReconst ordonnancierFabReconst = new OrdonnancierFabReconst();
        final Calendar dateFin = Calendar.getInstance(EclipseConstants.LOCALE);
        dateFin.set(Calendar.HOUR_OF_DAY, 0);
        dateFin.set(Calendar.MINUTE, 0);
        dateFin.set(Calendar.SECOND, 0);
        dateFin.set(Calendar.MILLISECOND, 0);
        ordonnancierFabReconst.setDateFin(dateFin);
        ordonnanciers.add(ordonnancierFabReconst);

        Mockito.when(this.mockDao.getAll((SearchCriteria) Matchers.any())).thenReturn(ordonnanciers);

        final Calendar dateDebut = this.service.getDateDebut(pharmacie);

        dateFin.add(Calendar.DAY_OF_MONTH, 1);

        Assert.assertEquals(dateFin.get(Calendar.YEAR), dateDebut.get(Calendar.YEAR));
        Assert.assertEquals(dateFin.get(Calendar.MONTH), dateDebut.get(Calendar.MONTH));
        Assert.assertEquals(dateFin.get(Calendar.DAY_OF_MONTH), dateDebut.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Méthode en charge de tester la récupération de la date de fin d'un
     * ordonnancier.
     */
    @Test
    public void testGetDateFin() {
        final Calendar now = Calendar.getInstance(EclipseConstants.LOCALE);
        now.set(Calendar.HOUR_OF_DAY, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);
        now.add(Calendar.DAY_OF_MONTH, -1);
        final Calendar dateFin = this.service.getDateFin();
        Assert.assertEquals(now.get(Calendar.YEAR), dateFin.get(Calendar.YEAR));
        Assert.assertEquals(now.get(Calendar.MONTH), dateFin.get(Calendar.MONTH));
        Assert.assertEquals(now.get(Calendar.DAY_OF_MONTH), dateFin.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * Méthode en charge de tester le calcul de l'ordonnancier.
     */
    @Test(expected = ValidationException.class)
    public void testCalculerOrdonnancierWithEltsToCheckEmpty() {
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        final OrdonnancierSearchCriteria criteria = new OrdonnancierSearchCriteria();
        criteria.setPharmacie(pharmacie);

        final List<PreparationEntree> eltsToCheck = new ArrayList<PreparationEntree>();
        Mockito.when(this.mockApproService.getAll((SearchCriteria) Matchers.any())).thenReturn(eltsToCheck);

        this.service.calculerOrdonnancier(criteria);
    }

    /**
     * Méthode en charge de tester le calcul de l'ordonnancier.
     */
    @Test
    public void testCalculerOrdonnancierWithEltsToCheckNotEmpty() {
        final Pharmacie pharmacie = Mockito.mock(Pharmacie.class);
        final OrdonnancierSearchCriteria criteria = Mockito.mock(OrdonnancierSearchCriteria.class);
        Mockito.when(criteria.getPharmacie()).thenReturn(pharmacie);

        final List<PreparationEntree> eltsToCheck = new ArrayList<PreparationEntree>();
        final PreparationEntree eltToCheck = new PreparationEntree();
        eltsToCheck.add(eltToCheck);

        Mockito.when(this.mockApproService.getAll((SearchCriteria) Matchers.any())).thenReturn(eltsToCheck);

        final OrdonnancierFabReconst ordonnancier = Mockito.mock(OrdonnancierFabReconst.class);
        Mockito.when(this.mockOrdonnancierFactory.getInitializedObject()).thenReturn(ordonnancier);

        this.service.calculerOrdonnancier(criteria);

        Mockito.verify(this.mockDao).save(ordonnancier);
    }

}

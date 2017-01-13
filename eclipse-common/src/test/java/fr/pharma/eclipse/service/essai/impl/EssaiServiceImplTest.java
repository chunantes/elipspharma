package fr.pharma.eclipse.service.essai.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.BeanFactory;

import fr.pharma.eclipse.comparator.common.BeanWithNomComparator;
import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.criteria.essai.EssaiSearchCriteria;
import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.enums.TypeHistoriqueEssai;
import fr.pharma.eclipse.domain.enums.evenement.ResultatVisite;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.etat.DetailEtatEssai;
import fr.pharma.eclipse.domain.model.essai.detail.faisabilite.DetailFaisabilite;
import fr.pharma.eclipse.domain.model.essai.detail.faisabilite.embedded.InfosConclusionFaisabilite;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.DetailDonneesPharma;
import fr.pharma.eclipse.domain.model.essai.detail.recherche.DetailRecherche;
import fr.pharma.eclipse.domain.model.essai.detail.surcout.DetailSurcout;
import fr.pharma.eclipse.domain.model.essai.detail.surcout.document.DocumentSurcouts;
import fr.pharma.eclipse.domain.model.evenement.Evenement;
import fr.pharma.eclipse.domain.model.localisation.Site;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.domain.model.suivi.essai.EssaiSuivi;
import fr.pharma.eclipse.domain.model.suivi.essai.detail.DetailRechercheSuivi;
import fr.pharma.eclipse.domain.model.surcout.Grille;
import fr.pharma.eclipse.factory.common.BeanObjectWithParentFactory;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.service.acl.AclService;
import fr.pharma.eclipse.service.essai.updator.EssaiBeforeSaveUpdator;
import fr.pharma.eclipse.service.evenement.EvenementService;
import fr.pharma.eclipse.service.helper.DroitAccesHelper;
import fr.pharma.eclipse.service.helper.common.BeanHelper;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.validator.save.SaveValidator;

/**
 * Classe en charge de tester le service de gestion des essais.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiServiceImplTest {

    /**
     * Type de l'historique du détail de la recherche.
     */
    private static final TypeHistoriqueEssai TYPE_ONG_RECHERCHE = TypeHistoriqueEssai.ONG_RECHERCHE;

    /**
     * Service de gestion de essais à tester.
     */
    private EssaiServiceImpl service;

    /**
     * Dao mocké.
     */
    private GenericDao<Essai> mockDao;

    /**
     * Helper mocké.
     */
    private BeanHelper<Essai> mockedBeanHelper;

    /**
     * Mock de la fabrique Spring.
     */
    private BeanFactory mockedBeanFactory;

    /**
     * Updator before save mocké.
     */
    private EssaiBeforeSaveUpdator mockedBeforeSaveUpdator;

    /**
     * Validateur de sauvegarde mocké.
     */
    private SaveValidator<Essai> mockedSaveValidator;

    /**
     * Fabrique de suivi général mockée.
     */
    private BeanObjectWithParentFactory<EssaiSuivi, Essai> mockedModifGeneraleFactory;

    /**
     * Service de gestion des pharmacies mocké.
     */
    private PharmacieService mockPharmacieService;

    /**
     * Fabrique de suivi d'onglet recherche mockée.
     */
    private BeanObjectWithParentFactory<DetailRechercheSuivi, DetailRecherche> mockedModifDetailRechercheFactory;

    /**
     * Fabrique de DetailEtatEssai mocké.
     */
    private SuiviFactory<DetailEtatEssai> mockDetailEtatEssaiFactory;

    /**
     * Helper droit mocké.
     */
    private DroitAccesHelper droitAccesHelper;

    /**
     * Service de gestion des acls mocké.
     */
    private AclService mockAclService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.mockDao = Mockito.mock(GenericDao.class);
        this.mockedBeanHelper = Mockito.mock(BeanHelper.class);
        this.droitAccesHelper = Mockito.mock(DroitAccesHelper.class);
        this.mockedBeanFactory = Mockito.mock(BeanFactory.class);
        this.mockedBeforeSaveUpdator = Mockito.mock(EssaiBeforeSaveUpdator.class);
        this.mockedSaveValidator = Mockito.mock(SaveValidator.class);
        this.mockedModifGeneraleFactory = Mockito.mock(BeanObjectWithParentFactory.class);
        this.mockedModifDetailRechercheFactory = Mockito.mock(BeanObjectWithParentFactory.class);
        this.mockPharmacieService = Mockito.mock(PharmacieService.class);
        this.mockDetailEtatEssaiFactory = Mockito.mock(SuiviFactory.class);

        final Map<String, BeanObjectWithParentFactory<? extends BeanObject, ? extends BeanObject>> mapFactories =
            new TreeMap<String, BeanObjectWithParentFactory<? extends BeanObject, ? extends BeanObject>>();
        mapFactories.put(TypeHistoriqueEssai.GENERAL.name(), this.mockedModifGeneraleFactory);
        mapFactories.put(EssaiServiceImplTest.TYPE_ONG_RECHERCHE.name(), this.mockedModifDetailRechercheFactory);

        this.service = new EssaiServiceImpl(this.mockDao);
        this.service.setBeanHelper(this.mockedBeanHelper);
        this.service.setMapFactories(mapFactories);
        this.service.setBeanFactory(this.mockedBeanFactory);
        this.service.setBeforeSaveUpdators(Arrays.asList(this.mockedBeforeSaveUpdator));
        this.service.setSaveValidators(Arrays.asList(this.mockedSaveValidator));
        this.service.setPharmacieService(this.mockPharmacieService);
        this.service.setDetailEtatEssaiFactory(this.mockDetailEtatEssaiFactory);
        this.service.setDroitAccesHelper(this.droitAccesHelper);
        Mockito.when(this.droitAccesHelper.isEssaiLectureSeule()).thenReturn(false);
        this.mockAclService = Mockito.mock(AclService.class);
        this.service.setAclService(this.mockAclService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.mockDao = null;
        this.service = null;
        this.mockedBeanHelper = null;
        this.mockedSaveValidator = null;
        this.mockedBeanFactory = null;
        this.mockedModifGeneraleFactory = null;
        this.mockPharmacieService = null;
        this.mockDetailEtatEssaiFactory = null;
        this.mockAclService = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.mockDao);
        Assert.assertNotNull(this.mockedBeanHelper);
        Assert.assertNotNull(this.mockedBeanFactory);
        Assert.assertNotNull(this.mockedSaveValidator);
        Assert.assertNotNull(this.mockedModifGeneraleFactory);
        Assert.assertNotNull(this.mockedModifDetailRechercheFactory);
        Assert.assertNotNull(this.mockPharmacieService);
        Assert.assertNotNull(this.mockDetailEtatEssaiFactory);
        Assert.assertNotNull(this.mockAclService);
    }

    /**
     * Test de la méthode initNumEnregistrement.
     */
    @Test
    public void testInitNumEnregistrement() {
        final Integer anneeCreation = 2010;
        int ids = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(ids++);
        essai.setAnneeCreation(anneeCreation);
        final EssaiSearchCriteria essaiSearchCriteria = Mockito.mock(EssaiSearchCriteria.class);
        Mockito.when(this.mockedBeanFactory.getBean("essaiCriteria")).thenReturn(essaiSearchCriteria);
        final List<Essai> essais = new ArrayList<Essai>();
        essais.add(EssaiUtils.makeEssaiTest(ids++));
        essais.add(EssaiUtils.makeEssaiTest(ids++));
        essais.add(EssaiUtils.makeEssaiTest(ids++));
        Mockito.when(this.mockDao.count(essaiSearchCriteria)).thenReturn(3L);
        // 3 essais

        Assert.assertNull(essai.getDetailRecherche().getNumEnregistrement());
        this.service.initNumEnregistrement(essai);

        Mockito.verify(this.mockedBeanFactory).getBean("essaiCriteria");
        Mockito.verify(essaiSearchCriteria).setAnneeCreation(anneeCreation);
        Assert.assertNotNull(essai.getDetailRecherche().getNumEnregistrement());
        Assert.assertEquals("2010-04", essai.getDetailRecherche().getNumEnregistrement());
    }

    /**
     * Méthode en charge de tester la sauvegarde des essais.
     */
    @Test
    public void testSave() {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        final DetailRechercheSuivi detailRechercheSuivi = Mockito.mock(DetailRechercheSuivi.class);
        final EssaiSuivi essaiSuivi = Mockito.mock(EssaiSuivi.class);

        Mockito.when(this.mockedModifDetailRechercheFactory.getInitializedObject(essai.getDetailRecherche())).thenReturn(detailRechercheSuivi);
        Mockito.when(this.mockedModifGeneraleFactory.getInitializedObject(essai)).thenReturn(essaiSuivi);
        Mockito.when(this.mockDao.save(essai)).thenReturn(essai);

        this.service.save(essai, EssaiServiceImplTest.TYPE_ONG_RECHERCHE.name());

        Mockito.verify(this.mockedBeforeSaveUpdator).update(essai, this.service);
        Mockito.verify(this.mockedSaveValidator).validate(essai, this.service);
        Mockito.verify(this.mockedModifDetailRechercheFactory).getInitializedObject(essai.getDetailRecherche());
        Mockito.verify(this.mockedModifGeneraleFactory).getInitializedObject(essai);
        Mockito.verify(this.mockedBeanHelper).addToCollection(essai, TypeHistoriqueEssai.GENERAL.getModifsPropertyFromEssai(), essaiSuivi);
        Mockito.verify(this.mockedBeanHelper).addToCollection(essai, EssaiServiceImplTest.TYPE_ONG_RECHERCHE.getModifsPropertyFromEssai(), detailRechercheSuivi);
        Mockito.verify(this.mockDao).save(essai);
        Mockito.verify(this.mockAclService).updateAclsEssais(essai);

        this.service.save(essai, null);

        this.service.save(essai, "");

        try {
            this.service.save(essai, "invalidType");
            Assert.fail("An exception is expected");
        } catch (final Exception e) {
            // An exception is expected
        }
    }

    /**
     * Méthode en charge de tester la récupération des pharmacies d'un essai.
     */
    @Test
    public void testGetAllPharmacies() {
        final Essai essai = new Essai();
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        essai.setPharmaciePrincipale(pharmacie);

        final DetailDonneesPharma dataPharma = new DetailDonneesPharma();
        final Pharmacie pharma1 = new Pharmacie();
        pharma1.setNom("pharma1");
        final Pharmacie pharma2 = new Pharmacie();
        pharma2.setNom("pharma2");
        dataPharma.getPharmacies().add(pharma1);
        dataPharma.getPharmacies().add(pharma2);

        essai.setDetailDonneesPharma(dataPharma);

        final List<Pharmacie> result = this.service.getAllPharmacies(essai);
        Assert.assertEquals(2 + 1, result.size());
    }

    /**
     * Méthode en charge de tester la récupération des pharmacies d'un essai.
     */
    @Test
    public void testGetAllPharmaciesOfUser() {
        final Essai essai = new Essai();
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        essai.setPharmaciePrincipale(pharmacie);

        final DetailDonneesPharma dataPharma = new DetailDonneesPharma();
        final Pharmacie pharma1 = new Pharmacie();
        pharma1.setNom("pharma1");
        final Pharmacie pharma2 = new Pharmacie();
        pharma2.setNom("pharma2");
        dataPharma.getPharmacies().add(pharma1);
        dataPharma.getPharmacies().add(pharma2);

        essai.setDetailDonneesPharma(dataPharma);

        final List<Pharmacie> pharmaciesUser = new ArrayList<Pharmacie>();
        pharmaciesUser.add(pharmacie);

        Mockito.when(this.mockPharmacieService.getAll()).thenReturn(pharmaciesUser);

        final List<Pharmacie> result = this.service.getAllPharmaciesOfUser(essai);
        Assert.assertEquals(1, result.size());
    }

    /**
     * Classe en charge de tester la suppression d'un événement.
     */
    @Test
    public void testRemoveEvenement() {
        final Evenement evenement = new Evenement();
        evenement.setId(1L);
        evenement.setDateDebut(Calendar.getInstance());
        final Essai essai = new Essai();
        essai.getEvenements().add(evenement);
        this.service.removeEvenement(essai, evenement);
        Assert.assertEquals(0, essai.getEvenements().size());
    }

    /**
     * Classe en charge de tester l'ajout d'un DetailEtatEssai à un essai.
     */
    @Test
    public void testAddDetailEtatEssai() {
        final Essai essai = new Essai();
        final DetailEtatEssai detailEtatEssai = new DetailEtatEssai();
        Mockito.when(this.mockDetailEtatEssaiFactory.getInitializedObject()).thenReturn(detailEtatEssai);
        this.service.addDetailEtatEssai(essai, EtatEssai.ARCHIVE, "commentaireNewEtat");
        Assert.assertEquals(1, essai.getDetailsEtatEssai().size());
    }

    /**
     * Teste de la méthode hasPharmacieSite. Cas OK.
     */
    @Test
    public void testhasPharmacieSiteOk() {
        final Site siteRecherche = new Site();
        siteRecherche.setId(new Long(2));

        final Pharmacie pharmacie = new Pharmacie();
        final Site site1 = new Site();
        final Site site2 = new Site();
        final Site site3 = new Site();
        site1.setId(new Long(1));
        site2.setId(new Long(2));
        site3.setId(new Long(3));
        site1.setNom("a");
        site2.setNom("b");
        site3.setNom("c");
        final SortedSet<Site> sites = new TreeSet<Site>(new BeanWithNomComparator());
        sites.add(site1);
        sites.add(site2);
        sites.add(site3);
        pharmacie.setSites(sites);

        final boolean hasPharmacieSite = this.service.hasPharmacieSite(siteRecherche, pharmacie);

        Assert.assertTrue(hasPharmacieSite);
    }

    /**
     * Teste de la méthode hasPharmacieSite. Cas KO.
     */
    @Test
    public void testhasPharmacieSiteKo() {
        final Site siteRecherche = new Site();
        siteRecherche.setId(new Long(4));

        final Pharmacie pharmacie = new Pharmacie();
        final Site site1 = new Site();
        final Site site2 = new Site();
        final Site site3 = new Site();
        site1.setId(new Long(1));
        site2.setId(new Long(2));
        site3.setId(new Long(3));
        site1.setNom("a");
        site2.setNom("b");
        site3.setNom("c");
        final SortedSet<Site> sites = new TreeSet<Site>(new BeanWithNomComparator());
        sites.add(site1);
        sites.add(site2);
        sites.add(site3);
        pharmacie.setSites(sites);

        final boolean hasPharmacieSite = this.service.hasPharmacieSite(siteRecherche, pharmacie);

        Assert.assertFalse(hasPharmacieSite);
    }

    /**
     * Test de la méthode testHasPharmaciesSite. <br>
     * Cas ok.
     */
    @Test
    public void testHasPharmaciesSite() {

        final Site siteRecherche = new Site();
        siteRecherche.setId(new Long(4));

        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setNom("ph1");
        final Site site11 = new Site();
        final Site site12 = new Site();
        final Site site13 = new Site();
        site11.setId(new Long(1));
        site12.setId(new Long(2));
        site13.setId(new Long(3));
        site11.setNom("a");
        site12.setNom("b");
        site13.setNom("c");

        final SortedSet<Site> sites = new TreeSet<Site>(new BeanWithNomComparator());
        sites.add(site11);
        sites.add(site12);
        sites.add(site13);
        pharmacie.setSites(sites);

        final Pharmacie pharmacie2 = new Pharmacie();
        pharmacie2.setNom("ph2");
        final Site site21 = new Site();
        final Site site22 = new Site();
        final Site site23 = new Site();
        site21.setId(new Long(4));
        site22.setId(new Long(5));
        site23.setId(new Long(6));
        site21.setNom("a2");
        site22.setNom("b3");
        site23.setNom("c4");
        final SortedSet<Site> sites2 = new TreeSet<Site>(new BeanWithNomComparator());
        sites2.add(site21);
        sites2.add(site22);
        sites2.add(site23);
        pharmacie2.setSites(sites2);

        final SortedSet<Pharmacie> pharmas = new TreeSet<Pharmacie>(new BeanWithNomComparator());
        pharmas.add(pharmacie);
        pharmas.add(pharmacie2);

        final boolean hasPharmaciesSite = this.service.hasPharmaciesSite(siteRecherche, pharmas);

        Assert.assertTrue(hasPharmaciesSite);

    }

    /**
     * Test de la méthode testHasPharmaciesSite. <br>
     * Cas ko.
     */
    @Test
    public void testHasPharmaciesSiteKO() {

        final Site siteRecherche = new Site();
        siteRecherche.setId(new Long(9));

        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setNom("ph1");
        final Site site11 = new Site();
        final Site site12 = new Site();
        final Site site13 = new Site();
        site11.setId(new Long(1));
        site12.setId(new Long(2));
        site13.setId(new Long(3));
        site11.setNom("a");
        site12.setNom("b");
        site13.setNom("c");

        final SortedSet<Site> sites = new TreeSet<Site>(new BeanWithNomComparator());
        sites.add(site11);
        sites.add(site12);
        sites.add(site13);
        pharmacie.setSites(sites);

        final Pharmacie pharmacie2 = new Pharmacie();
        pharmacie2.setNom("ph2");
        final Site site21 = new Site();
        final Site site22 = new Site();
        final Site site23 = new Site();
        site21.setId(new Long(4));
        site22.setId(new Long(5));
        site23.setId(new Long(6));
        site21.setNom("a2");
        site22.setNom("b3");
        site23.setNom("c4");
        final SortedSet<Site> sites2 = new TreeSet<Site>(new BeanWithNomComparator());
        sites2.add(site21);
        sites2.add(site22);
        sites2.add(site23);
        pharmacie2.setSites(sites2);

        final SortedSet<Pharmacie> pharmas = new TreeSet<Pharmacie>(new BeanWithNomComparator());
        pharmas.add(pharmacie);
        pharmas.add(pharmacie2);

        final boolean hasPharmaciesSite = this.service.hasPharmaciesSite(siteRecherche, pharmas);

        Assert.assertFalse(hasPharmaciesSite);

    }

    /**
     * Test de la methode hasEssaiSite Cas OK : site dans pharmas liees
     */
    @Test
    public void testHasEssaiSiteOK1() {
        final Site siteRecherche = new Site();
        siteRecherche.setId(new Long(4));

        // pharmacie 1
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(new Long(1));
        pharmacie.setNom("ph1");

        // pharmacie 2
        final Pharmacie pharmacie2 = new Pharmacie();
        pharmacie2.setId(new Long(2));
        pharmacie2.setNom("ph2");
        final Site site21 = new Site();
        site21.setId(new Long(4));
        site21.setNom("a2");
        final SortedSet<Site> sites2 = new TreeSet<Site>(new BeanWithNomComparator());
        sites2.add(site21);
        pharmacie2.setSites(sites2);

        final SortedSet<Pharmacie> pharmas = new TreeSet<Pharmacie>(new BeanWithNomComparator());
        pharmas.add(pharmacie);
        pharmas.add(pharmacie2);

        // essai
        final Essai essai = new Essai();
        essai.setDetailDonneesPharma(new DetailDonneesPharma());
        essai.getDetailDonneesPharma().setPharmacies(pharmas);

        final boolean hasEssaiSite = this.service.hasEssaiSite(siteRecherche, essai);
        Assert.assertTrue(hasEssaiSite);
    }

    /**
     * Test de la methode hasEssaiSite <br>
     * Cas OK : site dans pharma principale
     */
    @Test
    public void testHasEssaiSiteOK2() {
        final Site siteRecherche = new Site();
        siteRecherche.setId(new Long(4));

        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(new Long(1));
        pharmacie.setNom("ph");
        final Site site1 = new Site();
        site1.setId(new Long(4));
        site1.setNom("a2");
        final SortedSet<Site> sites = new TreeSet<Site>(new BeanWithNomComparator());
        sites.add(site1);
        pharmacie.setSites(sites);

        // essai
        final Essai essai = new Essai();
        essai.setPharmaciePrincipale(pharmacie);

        final boolean hasEssaiSite = this.service.hasEssaiSite(siteRecherche, essai);

        Assert.assertTrue(hasEssaiSite);
    }

    /**
     * Test de la methode hasEssaiSite <br>
     * Cas KO : site ni dans pharma principale ni dans les pharmas liees
     */
    @Test
    public void testHasEssaiSiteKO() {
        final Site siteRecherche = new Site();
        siteRecherche.setId(new Long(4));

        final Essai essai = new Essai();

        final boolean hasEssaiSite = this.service.hasEssaiSite(siteRecherche, essai);

        Assert.assertFalse(hasEssaiSite);

    }

    /**
     * Test de la methode testFiltreEssaisParSite.
     */
    @Test
    public void testFiltreEssaisParSite() {
        final Site siteRecherche = new Site();
        siteRecherche.setId(new Long(4));

        // essai 1
        final Essai essai = new Essai();

        // essai 2
        final Essai essai2 = new Essai();
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(new Long(1));
        pharmacie.setNom("ph");
        final Site site1 = new Site();
        site1.setId(new Long(4));
        site1.setNom("a2");
        final SortedSet<Site> sites = new TreeSet<Site>(new BeanWithNomComparator());
        sites.add(site1);
        pharmacie.setSites(sites);

        essai2.setPharmaciePrincipale(pharmacie);

        final List<Essai> essais = new ArrayList<Essai>();
        essais.add(essai);
        essais.add(essai2);

        final List<Essai> essaisFiltres = this.service.filtreEssaisParSite(essais, siteRecherche);

        Assert.assertEquals(essaisFiltres.size(), 1);

    }

    @Test
    public void testUpdateEtatEssaiNull() {
        Assert.assertNull(this.service.updateEtat(null));
    }

    @Test
    public void testUpdateEtatEssaiSansDetailFaisabilite() {
        final Essai e = new Essai();
        e.setEtat(EtatEssai.EN_EVALUATION);

        final EtatEssai ee = this.service.updateEtat(e);
        Assert.assertEquals(EtatEssai.EN_EVALUATION, ee);
    }

    @Test
    public void testUpdateEtatEnAttenteMiseEnPlace() {
        // Preparation service detailEtatEssaiFactory
        final DetailEtatEssai detailEtatEssai = new DetailEtatEssai();
        Mockito.when(this.mockDetailEtatEssaiFactory.getInitializedObject()).thenReturn(detailEtatEssai);

        // Construction essai
        final Essai e = new Essai();
        e.setEtat(EtatEssai.EN_EVALUATION);
        e.setDetailFaisabilite(new DetailFaisabilite());

        final InfosConclusionFaisabilite icf = new InfosConclusionFaisabilite();
        icf.setFavorable(true);
        e.getDetailFaisabilite().setInfosConclusion(icf);

        // Test
        Assert.assertEquals(EtatEssai.EN_ATTENTE_MISE_EN_PLACE, this.service.updateEtat(e));

    }

    @Test
    public void testUpdateEtatNonEnAttenteMiseEnPlace() {
        // Preparation service detailEtatEssaiFactory
        final DetailEtatEssai detailEtatEssai = new DetailEtatEssai();
        Mockito.when(this.mockDetailEtatEssaiFactory.getInitializedObject()).thenReturn(detailEtatEssai);

        // Construction essai
        final Essai e = new Essai();
        e.setEtat(EtatEssai.EN_EVALUATION);
        e.setDetailFaisabilite(new DetailFaisabilite());

        final InfosConclusionFaisabilite icf = new InfosConclusionFaisabilite();
        icf.setFavorable(false);
        e.getDetailFaisabilite().setInfosConclusion(icf);

        // Test
        Assert.assertEquals(EtatEssai.EN_EVALUATION, this.service.updateEtat(e));

    }

    @Test
    public void testUpdateEtatMiseEnPlace() {
        // Preparation detailEtatEssaiFactory
        final DetailEtatEssai detailEtatEssai = new DetailEtatEssai();
        detailEtatEssai.setDateMaj(new GregorianCalendar());
        Mockito.when(this.mockDetailEtatEssaiFactory.getInitializedObject()).thenReturn(detailEtatEssai);

        // Preparation evenementService
        final Evenement ev = new Evenement();
        ev.setResultatVisite(ResultatVisite.EFFECTUE);
        final EvenementService evs = Mockito.mock(EvenementService.class);
        Mockito.when(evs.getVisiteMonitoring(Matchers.any(Essai.class))).thenReturn(ev);
        this.service.setEvenementService(evs);

        // Construction essai
        final Essai es = new Essai();
        es.setId(1L);
        es.setEtat(EtatEssai.EN_EVALUATION);
        es.setDetailFaisabilite(new DetailFaisabilite());

        final InfosConclusionFaisabilite icf = new InfosConclusionFaisabilite();
        icf.setFavorable(true);
        icf.setConvSignee(true);
        es.getDetailFaisabilite().setInfosConclusion(icf);

        final DetailSurcout ds = new DetailSurcout();
        ds.setGrille(Mockito.mock(Grille.class));
        @SuppressWarnings("unchecked")
        final SortedSet<DocumentSurcouts> docs = Mockito.mock(SortedSet.class);
        Mockito.when(docs.isEmpty()).thenReturn(false);
        ds.setDocumentsPrevisionnels(docs);
        es.setDetailSurcout(ds);

        // Test
        Assert.assertEquals(EtatEssai.MISE_EN_PLACE, this.service.updateEtat(es));

    }

    /**
     * L'etat passe de EN_EVALUATION à EN_ATTENTE_MISE_EN_PLACE mais reste la
     * malgre une ID d'essai non null parce que le resultat visite est ANNULE
     */
    @Test
    public void testUpdateEtatToujoursEnAttenteMiseEnPlace() {
        // Preparation detailEtatEssaiFactory
        final DetailEtatEssai detailEtatEssai = new DetailEtatEssai();
        detailEtatEssai.setDateMaj(new GregorianCalendar());
        Mockito.when(this.mockDetailEtatEssaiFactory.getInitializedObject()).thenReturn(detailEtatEssai);

        // Preparation evenementService
        final Evenement ev = new Evenement();
        ev.setResultatVisite(ResultatVisite.ANNULE);
        final EvenementService evs = Mockito.mock(EvenementService.class);
        Mockito.when(evs.getVisiteMonitoring(Matchers.any(Essai.class))).thenReturn(ev);
        this.service.setEvenementService(evs);

        // Construction essai
        final Essai es = new Essai();
        es.setId(1L);
        es.setEtat(EtatEssai.EN_EVALUATION);
        es.setDetailFaisabilite(new DetailFaisabilite());

        final InfosConclusionFaisabilite icf = new InfosConclusionFaisabilite();
        icf.setFavorable(true);
        icf.setConvSignee(true);
        es.getDetailFaisabilite().setInfosConclusion(icf);

        final DetailSurcout ds = new DetailSurcout();
        ds.setGrille(Mockito.mock(Grille.class));
        @SuppressWarnings("unchecked")
        final SortedSet<DocumentSurcouts> docs = Mockito.mock(SortedSet.class);
        Mockito.when(docs.isEmpty()).thenReturn(false);
        ds.setDocumentsPrevisionnels(docs);
        es.setDetailSurcout(ds);

        // Test
        Assert.assertEquals(EtatEssai.EN_ATTENTE_MISE_EN_PLACE, this.service.updateEtat(es));

    }

}

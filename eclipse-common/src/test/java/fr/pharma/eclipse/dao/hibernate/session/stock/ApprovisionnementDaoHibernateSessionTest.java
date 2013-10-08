package fr.pharma.eclipse.dao.hibernate.session.stock;

import java.util.Calendar;
import java.util.Collection;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate;
import fr.pharma.eclipse.dictionary.impl.CriteriaDictionaryImpl;
import fr.pharma.eclipse.domain.criteria.stock.MvtStockSearchCriteria;
import fr.pharma.eclipse.domain.enums.stock.MotifRefus;
import fr.pharma.eclipse.domain.enums.stock.TypeMvtStock;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.service.acteur.PersonneService;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.produit.ProduitService;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.utils.ContextSecurityHelper;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester la DAO Approvisionnement avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class ApprovisionnementDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Approvisionnement.
     */
    private GenericDaoHibernate<Approvisionnement> approvisionnementDao;

    /**
     * Dictionnaire de critère de recherche.
     */
    @Resource(name = "criteriaDictionary")
    private CriteriaDictionaryImpl dictionary;

    /**
     * Service de gestion des essais.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * Service de gestion des produits.
     */
    @Resource(name = "medicamentService")
    private ProduitService<Medicament> produitService;

    /**
     * Service de gestion des personnes.
     */
    @Resource(name = "personneService")
    private PersonneService<Personne> personneService;

    /**
     * Service de gestion des conditionnements.
     */
    @Resource(name = "conditionnementService")
    private GenericService<Conditionnement> conditionnementService;

    /**
     * Service de gestion des pharmacies.
     */
    @Resource(name = "pharmacieService")
    private PharmacieService pharmacieService;

    /**
     * Initialisation du test.
     */
    @Before
    public void init() {
        this.approvisionnementDao = new GenericDaoHibernate<Approvisionnement>(Approvisionnement.class);
        this.approvisionnementDao.setEntityManager(this.entityManager);
        this.approvisionnementDao.setCriteriaDictionary(this.dictionary);
        ContextSecurityHelper.createSecurityContextMock();
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.approvisionnementDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO Approvisionnement.
     * javax.validation.ConstraintViolationException: validation failed for
     * classes [fr.pharma.eclipse.domain.model.stock.Approvisionnement] during
     * persist time for groups [javax.validation.groups.Default, ] at
     * org.hibernate.cfg.beanvalidation.BeanValidationEventListener.validate(
     * BeanValidationEventListener.java:132) at
     * org.hibernate.cfg.beanvalidation.
     * BeanValidationEventListener.onPreInsert(BeanValidationEventListener
     * .java:71) at org.hibernate.action.EntityIdentityInsertAction.preInsert(
     * EntityIdentityInsertAction.java:159) at
     * org.hibernate.action.EntityIdentityInsertAction
     * .execute(EntityIdentityInsertAction.java:65) at
     * org.hibernate.engine.ActionQueue.execute(ActionQueue.java:268) at
     * org.hibernate.event.def.AbstractSaveEventListener.performSaveOrReplicate(
     * AbstractSaveEventListener.java:321) at
     * org.hibernate.event.def.AbstractSaveEventListener
     * .performSave(AbstractSaveEventListener.java:204) at
     * org.hibernate.event.def .AbstractSaveEventListener.saveWithGeneratedId(
     * AbstractSaveEventListener .java:130) at
     * org.hibernate.ejb.event.EJB3PersistEventListener.saveWithGeneratedId
     * (EJB3PersistEventListener.java:69) at
     * org.hibernate.event.def.DefaultPersistEventListener
     * .entityIsTransient(DefaultPersistEventListener.java:179) at
     * org.hibernate.event.def.DefaultPersistEventListener.onPersist(
     * DefaultPersistEventListener.java:135) at
     * org.hibernate.event.def.DefaultPersistEventListener
     * .onPersist(DefaultPersistEventListener.java:61) at
     * org.hibernate.impl.SessionImpl.firePersist(SessionImpl.java:800) at
     * org.hibernate.impl.SessionImpl.persist(SessionImpl.java:774) at
     * org.hibernate.impl.SessionImpl.persist(SessionImpl.java:778) at
     * org.hibernate
     * .ejb.AbstractEntityManagerImpl.persist(AbstractEntityManagerImpl
     * .java:668) at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     * at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.
     * java:57) at sun.reflect.DelegatingMethodAccessorImpl.invoke(
     * DelegatingMethodAccessorImpl.java:43) at
     * java.lang.reflect.Method.invoke(Method.java:601) at
     * org.springframework.orm
     * .jpa.SharedEntityManagerCreator$SharedEntityManagerInvocationHandler
     * .invoke(SharedEntityManagerCreator.java:240) at $Proxy48.persist(Unknown
     * Source) at
     * fr.pharma.eclipse.dao.hibernate.common.GenericDaoHibernate.save
     * (GenericDaoHibernate.java:170) at
     * fr.pharma.eclipse.dao.hibernate.session.
     * stock.ApprovisionnementDaoHibernateSessionTest
     * .saveAndGetAndRemove(ApprovisionnementDaoHibernateSessionTest.java:144)
     * at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at
     * sun.reflect
     * .NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57) at
     * sun. reflect.DelegatingMethodAccessorImpl.invoke(
     * DelegatingMethodAccessorImpl .java:43) at
     * java.lang.reflect.Method.invoke(Method.java:601) at org.junit
     * .runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod
     * .java:47) at org.junit.internal.runners.model.ReflectiveCallable.run(
     * ReflectiveCallable.java:12) at
     * org.junit.runners.model.FrameworkMethod.invokeExplosively
     * (FrameworkMethod.java:44) at
     * org.junit.internal.runners.statements.InvokeMethod
     * .evaluate(InvokeMethod.java:17) at
     * org.junit.internal.runners.statements.RunBefores
     * .evaluate(RunBefores.java:26) at
     * org.springframework.test.context.junit4.statements
     * .RunBeforeTestMethodCallbacks
     * .evaluate(RunBeforeTestMethodCallbacks.java:74) at
     * org.junit.internal.runners
     * .statements.RunAfters.evaluate(RunAfters.java:27) at
     * org.springframework.test
     * .context.junit4.statements.RunAfterTestMethodCallbacks
     * .evaluate(RunAfterTestMethodCallbacks.java:83) at
     * org.springframework.test
     * .context.junit4.statements.SpringRepeat.evaluate(SpringRepeat.java:72) at
     * org.springframework.test.context.junit4.SpringJUnit4ClassRunner.runChild(
     * SpringJUnit4ClassRunner.java:231) at
     * org.springframework.test.context.junit4
     * .SpringJUnit4ClassRunner.runChild(SpringJUnit4ClassRunner.java:88) at
     * org.junit.runners.ParentRunner$3.run(ParentRunner.java:238) at
     * org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:63) at
     * org.junit.runners.ParentRunner.runChildren(ParentRunner.java:236) at
     * org.junit.runners.ParentRunner.access$000(ParentRunner.java:53) at
     * org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:229) at
     * org.springframework
     * .test.context.junit4.statements.RunBeforeTestClassCallbacks
     * .evaluate(RunBeforeTestClassCallbacks.java:61) at
     * org.springframework.test
     * .context.junit4.statements.RunAfterTestClassCallbacks
     * .evaluate(RunAfterTestClassCallbacks.java:71) at
     * org.junit.runners.ParentRunner.run(ParentRunner.java:309) at
     * org.springframework .test.context.junit4.SpringJUnit4ClassRunner.run(
     * SpringJUnit4ClassRunner .java:174) at
     * org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run
     * (JUnit4TestReference.java:50) at
     * org.eclipse.jdt.internal.junit.runner.TestExecution
     * .run(TestExecution.java:38) at
     * org.eclipse.jdt.internal.junit.runner.RemoteTestRunner
     * .runTests(RemoteTestRunner.java:467) at
     * org.eclipse.jdt.internal.junit.runner
     * .RemoteTestRunner.runTests(RemoteTestRunner.java:683) at
     * org.eclipse.jdt.internal
     * .junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:390) at
     * org.eclipse
     * .jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner
     * .java:197)
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de Approvisionnement
        Approvisionnement approvisionnement = new Approvisionnement();
        approvisionnement.setEssai(this.essaiService.get(1L));
        approvisionnement.setPersonne(this.personneService.get(1L));
        approvisionnement.setQuantite(Integer.valueOf(1));
        approvisionnement.setProduit(this.produitService.get(1L));
        approvisionnement.setDateCreation(Calendar.getInstance(EclipseConstants.LOCALE));
        approvisionnement.setDatePeremption(Calendar.getInstance(EclipseConstants.LOCALE));
        approvisionnement.setNumLot("numLot");
        approvisionnement.setConditionnement(this.conditionnementService.get(1L));
        approvisionnement.setPharmacie(this.pharmacieService.get(1L));
        approvisionnement.setApproApprouve(Boolean.FALSE);
        approvisionnement.setMotifRefus(MotifRefus.COLIS_DEGRADE);
        approvisionnement.setCommentaireRefus("commentaireRefus");
        approvisionnement.setNumTraitement("numTraitement");
        approvisionnement.setExtensionPeremption(Boolean.TRUE);
        approvisionnement.setCommentaireExtensionPeremption("commentaireExtensionPeremption");
        approvisionnement.setDateReception(Calendar.getInstance(EclipseConstants.LOCALE));
        approvisionnement.setDateArriveeColis(Calendar.getInstance(EclipseConstants.LOCALE));

        approvisionnement.setType(TypeMvtStock.APPROVISIONNEMENT);
        approvisionnement = this.approvisionnementDao.save(approvisionnement);

        // Récupération de Approvisionnement
        final Approvisionnement approvisionnementReturn = this.approvisionnementDao.get(approvisionnement.getId());

        Assert.assertEquals(approvisionnement.getType().getLibelle(), approvisionnementReturn.getType().getLibelle());
        Assert.assertEquals(approvisionnement.getDatePeremption(), approvisionnementReturn.getDatePeremption());
        Assert.assertEquals(approvisionnement.getDateCreation(), approvisionnementReturn.getDateCreation());
        Assert.assertEquals(approvisionnement.getNumLot(), approvisionnementReturn.getNumLot());
        Assert.assertEquals(approvisionnement.getEssai(), approvisionnementReturn.getEssai());
        Assert.assertEquals(approvisionnement.getProduit(), approvisionnementReturn.getProduit());
        Assert.assertEquals(approvisionnement.getPersonne(), approvisionnementReturn.getPersonne());
        Assert.assertEquals(approvisionnement.getQuantite(), approvisionnementReturn.getQuantite());
        Assert.assertEquals(approvisionnement.getConditionnement(), approvisionnementReturn.getConditionnement());
        Assert.assertEquals(approvisionnement.getPharmacie(), approvisionnementReturn.getPharmacie());
        Assert.assertEquals(approvisionnement.getApproApprouve(), approvisionnementReturn.getApproApprouve());
        Assert.assertEquals(approvisionnement.getMotifRefus(), approvisionnementReturn.getMotifRefus());
        Assert.assertEquals(approvisionnement.getCommentaireRefus(), approvisionnementReturn.getCommentaireRefus());
        Assert.assertEquals(approvisionnement.getExtensionPeremption(), approvisionnementReturn.getExtensionPeremption());
        Assert.assertEquals(approvisionnement.getCommentaireExtensionPeremption(), approvisionnementReturn.getCommentaireExtensionPeremption());
        Assert.assertEquals(approvisionnement.getNumTraitement(), approvisionnementReturn.getNumTraitement());
        Assert.assertEquals(approvisionnement.getDateReception(), approvisionnementReturn.getDateReception());
        Assert.assertEquals(approvisionnement.getDateArriveeColis(), approvisionnementReturn.getDateArriveeColis());

        // Suppression de Approvisionnement
        this.approvisionnementDao.remove(approvisionnement);
    }

    /**
     * Test de la récupération de tous les Approvisionnements.
     */
    @Test
    public void getAll() {

        // Récupération de tous les Approvisionnement final
        final MvtStockSearchCriteria criteria = new MvtStockSearchCriteria();
        final Collection<Approvisionnement> approvisionnements = this.approvisionnementDao.getAll(criteria);
        Assert.assertNotNull(approvisionnements);
        for (final Approvisionnement approvisionnement : approvisionnements) {
            Assert.assertNotNull(approvisionnement.getId());
        }

    }

}

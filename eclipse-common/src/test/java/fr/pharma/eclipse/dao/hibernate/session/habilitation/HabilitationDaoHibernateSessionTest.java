package fr.pharma.eclipse.dao.hibernate.session.habilitation;

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
import fr.pharma.eclipse.domain.criteria.habilitation.HabilitationSearchCriteria;
import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.service.acteur.PersonneService;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.utils.ContextSecurityHelper;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester la DAO Habilitation avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class HabilitationDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de Habilitation.
     */
    private GenericDaoHibernate<Habilitation> habilitationDao;

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
     * Service de gestion des investigateurs.
     */
    @Resource(name = "investigateurService")
    private PersonneService<Investigateur> investigateurService;

    /**
     * Initialisation du test.
     */
    @Before
    public void init() {
        this.habilitationDao = new GenericDaoHibernate<Habilitation>(Habilitation.class);
        this.habilitationDao.setEntityManager(this.entityManager);
        this.habilitationDao.setCriteriaDictionary(this.dictionary);
        ContextSecurityHelper.createSecurityContextMock();
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.habilitationDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test des méthodes de DAO Habilitation.
     */
    @Test
    public void saveAndGetAndRemove() {
        // Création et sauvegarde de Habilitation
        Habilitation habilitation = new Habilitation();
        habilitation.setDroit(Droit.INVESTIGATEUR_PRINCIPAL);
        habilitation.setDetailContacts(this.essaiService.get(1L).getDetailContacts());
        habilitation.setPersonne(this.investigateurService.get(1L));
        habilitation.setDateCreation(Calendar.getInstance(EclipseConstants.LOCALE));
        habilitation.setAuteurCreation("SRM");

        habilitation = this.habilitationDao.save(habilitation);

        // Récupération de Habilitation
        final Habilitation habilitationReturn = this.habilitationDao.get(habilitation.getId());

        Assert.assertEquals(habilitation.getDroit(), habilitationReturn.getDroit());
        Assert.assertEquals(habilitation.getDetailContacts(), habilitationReturn.getDetailContacts());
        Assert.assertEquals(habilitation.getPersonne(), habilitationReturn.getPersonne());
        Assert.assertEquals(habilitation.getDateCreation(), habilitationReturn.getDateCreation());
        Assert.assertEquals(habilitation.getAuteurCreation(), habilitationReturn.getAuteurCreation());
        Assert.assertNull(habilitation.getDateDesactivation());
        Assert.assertNull(habilitation.getAuteurDesactivation());
        Assert.assertTrue(habilitation.isActive());

        // Suppression de Habilitation
        this.habilitationDao.remove(habilitation);
    }

    /**
     * Test de la récupération de tous les Habilitations.
     */
    @Test
    public void getAllWithCriteria() {
        // Récupération de tous les Habilitation
        final HabilitationSearchCriteria criteria = new HabilitationSearchCriteria();
        criteria.setEssai(this.essaiService.get(1L));
        criteria.setPersonne(this.investigateurService.get(1L));
        criteria.setDroit(Droit.INVESTIGATEUR_PRINCIPAL);
        criteria.setActive(true);
        criteria.setAscending(true);
        criteria.setCaseSensitiveOrder(true);
        final Collection<Habilitation> habilitations = this.habilitationDao.getAll(criteria);

        Assert.assertNotNull(habilitations);

        for (final Habilitation habilitation : habilitations) {
            Assert.assertNotNull(habilitation.getId());
            Assert.assertNotNull(habilitation.getDetailContacts());
            Assert.assertNotNull(habilitation.getPersonne());
            Assert.assertNotNull(habilitation.getDroit());
            Assert.assertNotNull(habilitation.getDateCreation());
            Assert.assertNotNull(habilitation.getAuteurCreation());
            Assert.assertNotNull(habilitation.isActive());
        }
    }

    /**
     * Test de la récupération de tous les Habilitations.
     */
    @Test
    public void getAll() {
        // Récupération de tous les Habilitations
        final HabilitationSearchCriteria criteria = new HabilitationSearchCriteria();
        final Collection<Habilitation> habilitations = this.habilitationDao.getAll(criteria);

        Assert.assertNotNull(habilitations);

        for (final Habilitation habilitation : habilitations) {
            Assert.assertNotNull(habilitation.getId());
            Assert.assertNotNull(habilitation.getDetailContacts());
            Assert.assertNotNull(habilitation.getPersonne());
            Assert.assertNotNull(habilitation.getDroit());
            Assert.assertNotNull(habilitation.getDateCreation());
            Assert.assertNotNull(habilitation.getAuteurCreation());
            Assert.assertNotNull(habilitation.isActive());
        }
    }

}

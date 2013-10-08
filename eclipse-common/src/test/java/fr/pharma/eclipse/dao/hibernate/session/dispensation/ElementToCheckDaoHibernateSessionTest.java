package fr.pharma.eclipse.dao.hibernate.session.dispensation;

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
import fr.pharma.eclipse.domain.criteria.dispensation.ElementToCheckForOrdoSearchCriteria;
import fr.pharma.eclipse.domain.criteria.dispensation.ElementToCheckSearchCriteria;
import fr.pharma.eclipse.domain.enums.TypeElementToCheck;
import fr.pharma.eclipse.domain.model.dispensation.ElementToCheck;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.stockage.PharmacieService;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de tester la DAO ElementToCheck avec une session réelle.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/applicationContext-test.xml" })
@Transactional
public class ElementToCheckDaoHibernateSessionTest {
    /**
     * Entité manager.
     */
    @PersistenceContext(unitName = "eclipse")
    private EntityManager entityManager;

    /**
     * Dao de gestion de ElementToCheck.
     */
    private GenericDaoHibernate<ElementToCheck> elementDao;

    /**
     * Dictionnaire de critère de recherche.
     */
    @Resource(name = "criteriaDictionary")
    private CriteriaDictionaryImpl dictionary;

    /**
     * Service de gestion des pharmacies.
     */
    @Resource(name = "pharmacieService")
    private PharmacieService pharmacieService;

    /**
     * Service de gestion des essais.
     */
    @Resource(name = "essaiService")
    private EssaiService essaiService;

    /**
     * Initialisation du test.
     */
    @Before
    public void init() {
        this.elementDao = new GenericDaoHibernate<ElementToCheck>(ElementToCheck.class);
        this.elementDao.setEntityManager(this.entityManager);
        this.elementDao.setCriteriaDictionary(this.dictionary);
    }

    /**
     * Méthode de finalisation de test.
     */
    @After
    public void end() {
        this.elementDao = null;
        this.entityManager = null;
        this.dictionary = null;
    }

    /**
     * Test de la récupération de tous les ElementToChecks.
     */
    @Test
    public void getAllWithCriteria() {
        // Récupération de tous les ElementToCheck
        final ElementToCheckSearchCriteria criteria = new ElementToCheckSearchCriteria();
        criteria.setActiveOrder("id");
        final Essai essai = this.essaiService.get(1L);
        criteria.setDateDebut(Calendar.getInstance());
        criteria.setDateFin(Calendar.getInstance());
        criteria.setEssai(essai);
        criteria.setType(TypeElementToCheck.CONDITIONNEMENT);
        criteria.setCaseSensitiveOrder(true);
        final Collection<ElementToCheck> elements = this.elementDao.getAll(criteria);

        Assert.assertNotNull(elements);

        for (final ElementToCheck element : elements) {
            Assert.assertNotNull(element.getId());
        }
    }

    /**
     * Test de la récupération de tous les ElementToChecks pour la partie
     * ordonnancier des fabrications/reconstitutions.
     */
    @Test
    public void getAllWithCriteriaForOrdo() {
        // Récupération de tous les ElementToCheck
        final ElementToCheckForOrdoSearchCriteria criteria = new ElementToCheckForOrdoSearchCriteria();
        criteria.setActiveOrder("dateChecked");
        criteria.setAscending(true);
        criteria.setPharmacie(this.pharmacieService.get(1L));
        criteria.setDateDebut(Calendar.getInstance(EclipseConstants.LOCALE));
        criteria.setDateFin(Calendar.getInstance(EclipseConstants.LOCALE));
        criteria.setCaseSensitiveOrder(true);
        final Collection<ElementToCheck> elements = this.elementDao.getAll(criteria);

        Assert.assertNotNull(elements);

        for (final ElementToCheck element : elements) {
            Assert.assertNotNull(element.getId());
        }
    }

    /**
     * Test de la récupération de tous les ElementToChecks.
     */
    @Test
    public void getAll() {
        // Récupération de tous les ElementToCheck
        final ElementToCheckSearchCriteria criteria = new ElementToCheckSearchCriteria();
        final Collection<ElementToCheck> elements = this.elementDao.getAll(criteria);

        Assert.assertNotNull(elements);

        for (final ElementToCheck element : elements) {
            Assert.assertNotNull(element.getId());
        }
    }

}

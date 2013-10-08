package fr.pharma.eclipse.dictionary.impl;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.impl.CriteriaImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.core.context.SecurityContextHolder;

import fr.pharma.eclipse.dao.search.AclSearchDao;
import fr.pharma.eclipse.dictionary.maker.CriteriaMaker;
import fr.pharma.eclipse.dictionary.maker.stockage.PharmacieSearchCriteriaMaker;
import fr.pharma.eclipse.domain.criteria.stockage.PharmacieSearchCriteria;
import fr.pharma.eclipse.utils.ContextSecurityHelper;

/**
 * Classe en charge de tester le dictionnaire de critère de recherche / maker de
 * requête.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class CriteriaDictionaryImplTest {
    /**
     * CriteriaDictionaryImpl à tester.
     */
    private CriteriaDictionaryImpl dico;

    /**
     * Map des makers mocké.
     */
    @SuppressWarnings("rawtypes")
    private Map<Class, CriteriaMaker> mockMakers;

    /**
     * DAO de recherche des acls mocké.
     */
    private AclSearchDao mockAclSearchDao;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("rawtypes")
    public void init() {
        this.dico = new CriteriaDictionaryImpl();
        this.mockMakers = new HashMap<Class, CriteriaMaker>();
        this.dico.setMakers(this.mockMakers);
        ContextSecurityHelper.createSecurityContextMock();
        this.mockAclSearchDao = Mockito.mock(AclSearchDao.class);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.dico = null;
        this.mockMakers = null;
        this.mockAclSearchDao = null;
        SecurityContextHolder.clearContext();
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.dico);
        Assert.assertNotNull(this.dico.getMakers());
        Assert.assertNotNull(this.mockAclSearchDao);
    }

    /**
     * Méthode en charge de tester l'application des critères de recherche.
     */
    @Test
    public void testApply() {
        final Criteria criteria = new CriteriaImpl("", null);
        final PharmacieSearchCriteria searchCrit = new PharmacieSearchCriteria();
        final PharmacieSearchCriteriaMaker criteriaMaker = new PharmacieSearchCriteriaMaker();
        criteriaMaker.setAclSearchDao(this.mockAclSearchDao);
        this.mockMakers.put(PharmacieSearchCriteria.class, criteriaMaker);
        this.dico.apply(criteria, searchCrit);
    }

    /**
     * Méthode en charge de tester l'application des critères de recherche.
     */
    @Test
    public void testApplyNotSupport() {
        final Criteria criteria = new CriteriaImpl("", null);
        final PharmacieSearchCriteria searchCrit = new PharmacieSearchCriteria();
        final PharmacieSearchCriteriaMaker criteriaMaker = Mockito.mock(PharmacieSearchCriteriaMaker.class);
        this.mockMakers.put(PharmacieSearchCriteria.class, criteriaMaker);
        this.dico.apply(criteria, searchCrit);
    }

}

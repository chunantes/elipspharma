package fr.pharma.eclipse.dictionary.maker.essai;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.criteria.essai.EssaiSearchCriteria;
import fr.pharma.eclipse.domain.model.localisation.Site;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;

/**
 * Classe en charge de tester le maker de critère de recherche sur Essai.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class EssaiSearchCriteriaMakerTest {

    /**
     * EssaiSearchCriteriaMaker à tester.
     */
    private EssaiSearchCriteriaMaker maker;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    public void init() {
        this.maker = new EssaiSearchCriteriaMaker();
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.maker = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.maker);
    }

    /**
     * Méthode en charge de tester le traitement des critères posés sur
     * Pharmacie/Site.
     */
    @Test
    public void handleCriteriaPharmaSiteAllNull() {
        final EssaiSearchCriteria crit = new EssaiSearchCriteria();
        final Criteria criteria = Mockito.mock(Criteria.class);
        this.maker.handleCriteriaPharma(criteria, crit);
    }

    /**
     * Méthode en charge de tester le traitement des critères posés sur
     * Pharmacie/Site.
     */
    @Test
    public void handleCriteriaPharmaSiteSiteNull() {
        final EssaiSearchCriteria crit = new EssaiSearchCriteria();
        final Pharmacie pharmacie = new Pharmacie();
        pharmacie.setId(1L);
        crit.setPharmacie(pharmacie);
        final Criteria criteria = Mockito.mock(Criteria.class);
        final Criteria critDetail = Mockito.mock(Criteria.class);
        Mockito.when(criteria.createCriteria("detailDonneesPharma", "detailDonneesPharma")).thenReturn(critDetail);
        final Criteria critPharmacie = Mockito.mock(Criteria.class);
        Mockito.when(critDetail.createCriteria("pharmacies", "pharmacies")).thenReturn(critPharmacie);
        this.maker.handleCriteriaPharma(criteria, crit);
        Mockito.verify(criteria).createCriteria("detailDonneesPharma", "detailDonneesPharma");
        Mockito.verify(critDetail).createCriteria("pharmacies", "aliasPharmacies", CriteriaSpecification.FULL_JOIN);
    }

    /**
     * Méthode en charge de tester le traitement des critères posés sur
     * Pharmacie/Site.
     */
    @Test
    public void handleCriteriaPharmaSitePharmacieNull() {
        final EssaiSearchCriteria crit = new EssaiSearchCriteria();
        final Site site = new Site();
        site.setId(1L);
        crit.setSite(site);
        final Pharmacie pharmacie = new Pharmacie();
        crit.setPharmacie(pharmacie);
        final Criteria criteria = Mockito.mock(Criteria.class);
        final Criteria critPharmacie = Mockito.mock(Criteria.class);
        final Criteria critSites = Mockito.mock(Criteria.class);
        final Criteria critDetail = Mockito.mock(Criteria.class);
        Mockito.when(criteria.createCriteria("detailDonneesPharma", "detailDonneesPharma")).thenReturn(critDetail);
        Mockito.when(critDetail.createCriteria("pharmacies", "aliasPharmacies", CriteriaSpecification.LEFT_JOIN)).thenReturn(critPharmacie);
        Mockito.when(critPharmacie.createCriteria("sites", "sites")).thenReturn(critSites);
        this.maker.handleCriteriaPharma(criteria, crit);
        Mockito.verify(criteria).createCriteria("detailDonneesPharma", "detailDonneesPharma");
        Mockito.verify(critDetail).createCriteria("pharmacies", "aliasPharmacies", CriteriaSpecification.FULL_JOIN);

    }
}

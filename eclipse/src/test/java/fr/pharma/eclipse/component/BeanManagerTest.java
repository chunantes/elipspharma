package fr.pharma.eclipse.component;

import java.util.Collection;

import javax.faces.model.DataModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import fr.pharma.eclipse.component.helper.BeanManagerHelper;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.service.stockage.PharmacieService;

/**
 * Classe en charge de tester le manager de Bean.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class BeanManagerTest {
    /**
     * BeanManager à tester.
     */
    private BeanManager<Pharmacie> pharmacieManager;

    /**
     * Helper mocké.
     */
    private BeanManagerHelper<Pharmacie> mockedHelper;

    /**
     * DataModel mocké.
     */
    private DataModel<BeanObject> mockedDataModel;

    /**
     * Service de gestion des pharmacies mocké.
     */
    private PharmacieService mockService;

    /**
     * Bean pharmacie.
     */
    private Pharmacie pharmacie;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @SuppressWarnings("unchecked")
    @Before
    public void init() {
        this.mockedHelper = Mockito.mock(BeanManagerHelper.class);
        this.mockedDataModel = Mockito.mock(DataModel.class);
        this.mockService = Mockito.mock(PharmacieService.class);
        this.pharmacieManager = new BeanManager<Pharmacie>(this.mockService);
        this.pharmacie = new Pharmacie();
        this.pharmacie.setId(1L);
        this.pharmacieManager.setBean(this.pharmacie);
        this.pharmacieManager.setHelper(this.mockedHelper);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.mockedHelper = null;
        this.mockedDataModel = null;
        this.mockService = null;
        this.pharmacieManager = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.pharmacieManager);
        Assert.assertNotNull(this.mockedHelper);
        Assert.assertNotNull(this.mockedDataModel);
        Assert.assertNotNull(this.mockService);
        Assert.assertNotNull(this.pharmacieManager.getService());
        Assert.assertNotNull(this.pharmacieManager.getBean());
    }

    /**
     * Initialisation de la méthode mockedHelper.
     */
    @SuppressWarnings("unchecked")
    private void initMockHelper() {
        Mockito.when(this.mockedHelper.returnAsDataModel(Matchers.anyCollection())).thenAnswer(new Answer<DataModel>() {

            @Override
            public DataModel answer(final InvocationOnMock invocation) throws Throwable {
                @SuppressWarnings("unused")
                final Collection coll = (Collection) invocation.getArguments()[0];
                return BeanManagerTest.this.mockedDataModel;
            }
        });
    }

    /**
     * Méthode en charge de tester la récupération d'une liste de l'objet.
     */
    @Test
    public void testGetListString() {
        this.initMockHelper();
        Mockito.when(this.mockService.reattach(this.pharmacie)).thenReturn(this.pharmacie);
        final DataModel<Pharmacie> list = this.pharmacieManager.getList("sites");
        // Vérification de l'appel au reattach
        Mockito.verify(this.mockService).reattach(this.pharmacie);
        Mockito.verify(this.mockedHelper).returnAsDataModel(this.pharmacie.getSites());
        Assert.assertNotNull(list);
        Assert.assertEquals(this.mockedDataModel, list);
    }

    /**
     * Méthode en charge de tester la récupération d'une liste de l'objet en
     * demandant ou non le réattachement du bean.
     */
    @Test
    public void testGetListStringBoolean() {
        this.initMockHelper();
        final DataModel<Pharmacie> list = this.pharmacieManager.getList("sites", false);

        Mockito.verify(this.mockedHelper).returnAsDataModel(this.pharmacie.getSites());
        Assert.assertNotNull(list);
        Assert.assertEquals(this.mockedDataModel, list);
    }

    /**
     * Méthode en charge de tester la méthode de reattach.
     */
    @Test
    public void testReattach() {
        this.pharmacieManager.reattach();
        Mockito.verify(this.mockService).reattach(this.pharmacie);
    }

    /**
     * Test de la méthode getBeanAfterReattach.
     */
    @Test
    public void testGetBeanAfterReattach() {
        final Pharmacie pharmacieReattached = Mockito.mock(Pharmacie.class);
        this.pharmacieManager.setBean(this.pharmacie);
        Mockito.when(this.mockService.reattach(this.pharmacie)).thenReturn(pharmacieReattached);

        final Pharmacie res = this.pharmacieManager.getBeanAfterReattach();
        Mockito.verify(this.mockService).reattach(this.pharmacie);
        Assert.assertEquals(pharmacieReattached, res);
    }

}

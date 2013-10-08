package fr.pharma.eclipse.component.prescription;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.component.prescription.helper.ProduitPrescritHelper;
import fr.pharma.eclipse.component.produit.helper.ConditionnementHelper;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.factory.prescription.ProduitPrescritFactory;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du manager ProduitPrescritManager.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ProduitPrescritManagerTest extends AbstractEclipseJUnitTest {

    /**
     * Manager.
     */
    private ProduitPrescritManager manager;

    /**
     * helper.
     */
    private ProduitPrescritHelper helper;

    /**
     * Conditionnement helper..
     */
    private ConditionnementHelper conditionnementHelper;

    /**
     * Service mocké.
     */
    private GenericService<ProduitPrescrit> service;

    /**
     * Factory mocké.
     */
    private ProduitPrescritFactory produitPrescritFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.produitPrescritFactory = Mockito.mock(ProduitPrescritFactory.class);
        this.helper = Mockito.mock(ProduitPrescritHelper.class);
        this.conditionnementHelper = Mockito.mock(ConditionnementHelper.class);
        this.service = Mockito.mock(GenericService.class);
        this.manager = new ProduitPrescritManager(this.service);
        this.manager.setConditionnementHelper(this.conditionnementHelper);
        this.manager.setProduitPrescritHelper(this.helper);
        this.manager.setProduitPrescritFactory(this.produitPrescritFactory);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.produitPrescritFactory = null;
        this.service = null;
        this.manager = null;
        this.helper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit() {
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.helper);
        Assert.assertNotNull(this.produitPrescritFactory);
    }

    /**
     * Test de la méthode init().
     */
    @Test
    public void testInitMethod() {
        this.manager.setBean(new ProduitPrescrit());
        this.manager.init();
        Assert.assertNull(this.manager.getBean());
    }

    /**
     * Test de la méthode initProduitPrescrit.
     */
    @Test
    public void testInitProduitPrescrit() {
        Mockito.when(this.produitPrescritFactory.getInitializedObject()).thenReturn(new ProduitPrescrit());
        this.manager.initProduitPrescrit();
        Mockito.verify(this.produitPrescritFactory).getInitializedObject();
        Assert.assertNotNull(this.manager.getBean());
    }

    /**
     * Test de la méthode editProduitPrescrit.
     */
    @Test
    public void testEditProduitPrescrit() {
        final Map<String, Object> value = new HashMap<String, Object>();
        Mockito.when(this.helper.buildResume(Matchers.any(ProduitPrescrit.class))).thenReturn("test");
        value.put("produitCurrent", new ProduitPrescrit());
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        Mockito.when(event.getComponent()).thenReturn(component);
        Mockito.when(component.getAttributes()).thenReturn(value);

        this.manager.editProduitPrescrit(event);

        Assert.assertEquals("test", this.manager.getResume());
        Assert.assertNotNull(this.manager.getBean());
    }

    /**
     * Test de la méthode isModePrescription.
     */
    @Test
    public void testIsModePrescription() {
        Assert.assertFalse(this.manager.isModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE));
    }

    /**
     * Test de la méthode isModePrescription.
     */
    @Test
    public void testIsModePrescription2() {
        this.manager.setBean(new ProduitPrescrit());
        Assert.assertFalse(this.manager.isModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE));
    }

    /**
     * Test de la méthode isModePrescription.
     */
    @Test
    public void testIsModePrescription3() {
        this.manager.setBean(new ProduitPrescrit());
        this.manager.getBean().setConditionnement(new Conditionnement());
        Assert.assertFalse(this.manager.isModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE));
    }

    /**
     * Test de la méthode isModePrescription.
     */
    @Test
    public void testIsModePrescription4() {
        this.manager.setBean(new ProduitPrescrit());
        this.manager.getBean().setConditionnement(new Conditionnement());
        this.manager.getBean().getConditionnement().setModePrescription(ModePrescription.DOSE_KG);
        Assert.assertFalse(this.manager.isModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE));
    }

    /**
     * Test de la méthode isModePrescription.
     */
    @Test
    public void testIsModePrescriptionOk() {
        this.manager.setBean(new ProduitPrescrit());
        this.manager.getBean().setConditionnement(new Conditionnement());
        this.manager.getBean().getConditionnement().setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        Assert.assertTrue(this.manager.isModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE));
    }
}

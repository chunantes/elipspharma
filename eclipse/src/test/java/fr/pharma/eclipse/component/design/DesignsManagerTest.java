package fr.pharma.eclipse.component.design;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.primefaces.event.SelectEvent;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import fr.pharma.eclipse.component.design.helper.TreeDesignHelper;
import fr.pharma.eclipse.domain.enums.TypeDesignable;
import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.design.DetailDesign;
import fr.pharma.eclipse.domain.model.essai.detail.produit.DetailProduit;
import fr.pharma.eclipse.domain.model.produit.DispositifMedical;
import fr.pharma.eclipse.factory.design.BrasFactory;
import fr.pharma.eclipse.json.DesignConverter;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.helper.design.TimeHelper;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.FacesUtils;
import fr.pharma.eclipse.validator.remove.RemoveValidator;
import fr.pharma.eclipse.validator.remove.impl.BrasRemoveValidator;
import fr.pharma.eclipse.validator.remove.impl.SequenceRemoveValidator;

/**
 * Test du manager DesignsManager.
 *
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DesignsManagerTest extends AbstractEclipseJUnitTest {

    /**
     * DesignsManager.
     */
    private DesignsManager designsManager;

    /**
     * Helper tree.
     */
    private TreeDesignHelper treeDesignHelper;

    /**
     * BrasFactory.
     */
    private BrasFactory brasFactory;

    /**
     * RemoveValidator Bras.
     */
    private RemoveValidator<Bras> brasRemoveValidator;

    /**
     * RemoveValidator Sequence.
     */
    private RemoveValidator<Sequence> sequenceRemoveValidator;

    /**
     * Design Converter.
     */
    private DesignConverter designConverter;

    /**
     * TimeHelper.
     */
    private TimeHelper timeHelper;

    /**
     * FacesUtils.
     */
    private FacesUtils facesUtils;
    
    /**
     * Detail design initialisé dans la fonction init()
     */
    private DetailDesign detail;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.designsManager = new DesignsManager();

        this.facesUtils = Mockito.mock(FacesUtils.class);
        this.designsManager.setFacesUtils(this.facesUtils);

        this.brasRemoveValidator = Mockito.mock(BrasRemoveValidator.class);
        this.designsManager.setBrasRemoveValidator(this.brasRemoveValidator);

        this.treeDesignHelper = Mockito.mock(TreeDesignHelper.class);
        this.designsManager.setTreeDesignHelper(this.treeDesignHelper);

        this.sequenceRemoveValidator = Mockito.mock(SequenceRemoveValidator.class);
        this.designsManager.setSequenceRemoveValidator(this.sequenceRemoveValidator);

        this.brasFactory = Mockito.mock(BrasFactory.class);
        this.designsManager.setBrasFactory(this.brasFactory);

        this.designConverter = Mockito.mock(DesignConverter.class);
        this.designsManager.setDesignConverter(this.designConverter);

        this.timeHelper = Mockito.mock(TimeHelper.class);
        this.designsManager.setTimeHelper(this.timeHelper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.facesUtils = null;
        this.brasRemoveValidator = null;
        this.treeDesignHelper = null;
        this.sequenceRemoveValidator = null;
        this.brasFactory = null;
        this.designConverter = null;
        this.timeHelper = null;
        this.designsManager = null;

    }

    /**
     * {@inheritDoc}
     */
    @Test
    @Override
    public void testInit() {
        Assert.assertNotNull(this.designsManager);
        Assert.assertNotNull(this.facesUtils);
        Assert.assertNotNull(this.brasRemoveValidator);
        Assert.assertNotNull(this.treeDesignHelper);
        Assert.assertNotNull(this.sequenceRemoveValidator);
        Assert.assertNotNull(this.brasFactory);
        Assert.assertNotNull(this.designConverter);
        Assert.assertNotNull(this.timeHelper);
    }

    /**
     * Test de la méthode initBras.
     */
    @Test
    public void testInitBras() {
        final Bras bras = Mockito.mock(Bras.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("designableParent", bras);
        Mockito.when(event.getComponent()).thenReturn(component);
        Mockito.when(component.getAttributes()).thenReturn(attributes);
        Mockito.when(this.brasFactory.getInitializedObject()).thenReturn(new Bras());
        this.designsManager.initBras(event);
        Assert.assertEquals(bras, this.designsManager.getBras().getParent());
    }

    /**
     * Test de la méthode initProduits.
     */
 /*   @Test
    public void testInitProduits() {
        final Essai essai = new Essai();
        essai.setDetailProduit(new DetailProduit());
        essai.getDetailProduit().getProduits().add(new Medicament());
        final DetailDesign design = new DetailDesign();
        design.setEssai(essai);
        this.designsManager.setDetailDesign(design);
        this.designsManager.initProduits();
        Assert.assertEquals(1, this.designsManager.getProduits().size());
    }*/

    /**
     * Test de la méthode editBras.
     */
    @Test
    public void testEditBras() {
        final Bras bras = Mockito.mock(Bras.class);
        final DetailDesign design = new DetailDesign();
        design.getBras().add(bras);

        final UIComponent component = Mockito.mock(UIComponent.class);
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("brasCurrent", bras);

        Mockito.when(bras.getNomComplet()).thenReturn("Nom complet");

        Mockito.when(event.getComponent()).thenReturn(component);
        Mockito.when(component.getAttributes()).thenReturn(attributes);
        this.designsManager.getActionEvent(event);
        this.designsManager.editBras(design);
        Assert.assertEquals(bras, this.designsManager.getBras());
        Assert.assertEquals(TypeDesignable.BRAS, this.designsManager.getType());
        Assert.assertEquals("EDIT", this.designsManager.getActionCurrent());
    }

    /**
     * Test de la méthode selectDateListener.
     */
    @Test
    public void testSelectDateListener() {
        final SelectEvent event = Mockito.mock(SelectEvent.class);
        Mockito.when(event.getObject()).thenReturn(Calendar.getInstance());
        this.designsManager.selectDateListener(event);
        Assert.assertNotNull(this.designsManager.getDateDebut());
    }

    /**
     * Test de la méthode processDateFin.
     */
    @Test
    public void testProcessDateFin() {
        // Prepare
        final DetailDesign detailDesign = new DetailDesign();
        detailDesign.setEssai(new Essai());

        Mockito.when(this.timeHelper.getDateFinForDesign(Matchers.any(DetailDesign.class))).thenReturn(new TempsPrescription());
        Mockito.when(this.timeHelper.convertTime(Matchers.any(Calendar.class), Matchers.any(TempsPrescription.class))).thenReturn(Calendar.getInstance());

        // Test
        this.designsManager.processDateFin(Calendar.getInstance(), detailDesign);

        // Verify
        Assert.assertNotNull(this.designsManager.getDateFin());
        Mockito.verify(this.timeHelper).convertTime(Matchers.any(Calendar.class), Matchers.any(TempsPrescription.class));
    }

    /**
     * Test de la méthode processDateFin.
     */
    @Test
    public void testProcessDateFinKo() {
        // Prepare
        final DetailDesign detailDesign = new DetailDesign();
        detailDesign.setEssai(new Essai());
        Mockito.when(this.timeHelper.getDateFinForDesign(Matchers.any(DetailDesign.class))).thenReturn(null);

        // Test
        this.designsManager.processDateFin(Calendar.getInstance(), detailDesign);

        // Verify
        Assert.assertNull(this.designsManager.getDateFin());
        Mockito.verify(this.timeHelper, Mockito.never()).convertTime(Matchers.any(Calendar.class), Matchers.any(TempsPrescription.class));
    }

    /**
     * Test de la méthode initSequence.
     */
    @Test
    public void testInitSequence() {
        this.initEssai();
    //    Assert.assertNotNull(this.designsManager.initSequence("Bras 1-sequence 1", "Bras 1"));
    }

    /**
     * Test de la méthode findBras.
     */
    @Test
    public void testFindBras() {
    	// Prepare
        this.initEssai();
        Assert.assertNotNull(this.designsManager.findBras("Bras 1",detail));
    }

    /**
     * Test de la méthode init.
     */
    @Test
    public void testInitMethod() {
        this.initEssai();
        this.designsManager.setActionCurrent("test");
        this.designsManager.setBras(new Bras());
        this.designsManager.setType(TypeDesignable.SEQUENCE);
        this.designsManager.setDateFin(Calendar.getInstance());
        this.designsManager.setJson(new JSONArray());
        this.designsManager.setJsonDate(new JSONObject());
        this.designsManager.setRoot(null);
        
        //On set le détailDesign dans l'essai. Dans la fonction init(), le lien bidirectionnel n'est pas fait
        detail.getEssai().setDetailDesign(detail);

        GenericService<DetailDesign> detailDesignService = Mockito.mock(GenericService.class);
        Mockito.when(detailDesignService.get(Matchers.any(Long.class))).thenReturn(detail);
        this.designsManager.init(detail.getEssai());

        Assert.assertNull(this.designsManager.getBras());
        Assert.assertNull(this.designsManager.getType());
        Assert.assertNull(this.designsManager.getDateFin());
        Assert.assertNull(this.designsManager.getJson());
        Assert.assertNull(this.designsManager.getJsonDate());
        Assert.assertEquals("", this.designsManager.getActionCurrent());
        Assert.assertNotNull(this.designsManager.getProduits());
    }

    /**
     * Méthode en charge d'initialiser l'essai pour les tests.
     */
    private void initEssai() {
        final Bras b1 = new Bras();
        b1.setId(Long.MIN_VALUE);
        b1.setNom("Bras 1");
        final Bras b2 = new Bras();
        b2.setId(Long.MIN_VALUE+1);
        b2.setNom("Bras 2");
        detail = new DetailDesign();
        detail.getBras().add(b1);
        detail.getBras().add(b2);
        final Sequence s1 = new Sequence();
        s1.setNom("sequence 1");
        s1.setId(Long.MIN_VALUE);
        s1.setParent(b1);
        s1.setDebut(new TempsPrescription());
        b1.getSequences().add(s1);
        detail.setEssai(new Essai());
        final DetailProduit produit = new DetailProduit();
        detail.getEssai().setDetailProduit(produit);
        produit.getMedicaments().add(new DispositifMedical());
    }

    /**
     * Test de la méthode removeSequence.
     */
    @Test
    public void testRemoveSequence() {
        this.initEssai();
        this.designsManager.setCurrent(detail.getBras().first().getSequences().first());
        this.designsManager.removeSequence(detail);
    }

    /**
     * Test de la méthode removeBras.
     */
    @Test
    public void testRemoveBras() {
        this.initEssai();

        Bras bras = detail.getBras().first();

        this.designsManager.setCurrent(bras);
        this.designsManager.removeBras(detail);

        Assert.assertFalse(detail.getBras().contains(bras));

        // test pour PHARMA-459
        bras = new Bras();
        bras.setNom("mon bras");
        this.designsManager.setCurrent(bras);
        this.designsManager.removeBras(detail);

        Assert.assertFalse(detail.getBras().contains(bras));
    }

    /**
     * Test de la méthode addSequence.
     */
    @Test
    public void testAddSequence() {
        this.initEssai();
        this.designsManager.setActionCurrent("EDIT");
        this.designsManager.addSequence(detail.getBras().first().getSequences().first(), detail);

    }

    @Test
    public void testInitDesignChronoNoBras() {
        // Prepare
        final DetailDesign design = new DetailDesign();
        design.setEssai(new Essai());
        // Test
        this.designsManager.initDesignChrono(design);

        // Validate
        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR, "designs.data.empty");

    }

    @Test
    public void testInitDesignChronoNoDateDebut() {
        // Prepare
        this.initEssai();
        this.designsManager.setDateDebut(null);

        // Test
        this.designsManager.initDesignChrono(detail);

        // Validate
        Mockito.verify(this.facesUtils).addMessage(FacesMessage.SEVERITY_ERROR, "designs.data.invalid");
    }

}

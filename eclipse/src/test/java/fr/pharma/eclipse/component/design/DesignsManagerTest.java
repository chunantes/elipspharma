package fr.pharma.eclipse.component.design;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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
import fr.pharma.eclipse.component.essai.EssaiManager;
import fr.pharma.eclipse.domain.enums.TypeDesignable;
import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.design.DetailDesign;
import fr.pharma.eclipse.domain.model.essai.detail.produit.DetailProduit;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.exception.common.CommonException;
import fr.pharma.eclipse.factory.design.BrasFactory;
import fr.pharma.eclipse.json.DesignConverter;
import fr.pharma.eclipse.service.helper.design.TimeHelper;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.FacesUtils;
import fr.pharma.eclipse.validator.remove.RemoveValidator;

/**
 * Test du manager DesignsManager.
 
 * @version $Revision$ $Date$
 */
public class DesignsManagerTest
    extends AbstractEclipseJUnitTest
{

    /**
     * DesignsManager.
     */
    private DesignsManager designsManager;

    /**
     * Essai Manager.
     */
    private EssaiManager essaiManager;

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
     * {@inheritDoc}
     */
    @Override
    public void setUp()
    {
        this.facesUtils = Mockito.mock(FacesUtils.class);
        this.essaiManager = Mockito.mock(EssaiManager.class);
        this.brasRemoveValidator = Mockito.mock(RemoveValidator.class);
        this.treeDesignHelper = Mockito.mock(TreeDesignHelper.class);
        this.sequenceRemoveValidator = Mockito.mock(RemoveValidator.class);
        this.brasFactory = Mockito.mock(BrasFactory.class);
        this.designConverter = Mockito.mock(DesignConverter.class);
        this.timeHelper = Mockito.mock(TimeHelper.class);
        this.designsManager = new DesignsManager();
        this.designsManager.setBrasFactory(this.brasFactory);
        this.designsManager.setBrasRemoveValidator(this.brasRemoveValidator);
        this.designsManager.setDesignConverter(this.designConverter);
        this.designsManager.setEssaiManager(this.essaiManager);
        this.designsManager.setSequenceRemoveValidator(this.sequenceRemoveValidator);
        this.designsManager.setTreeDesignHelper(this.treeDesignHelper);
        this.designsManager.setTimeHelper(this.timeHelper);

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown()
    {
        this.facesUtils = null;
        this.essaiManager = null;
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
    public void testInit()
    {
        Assert.assertNotNull(this.designsManager);
        Assert.assertNotNull(this.essaiManager);
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
    public void testInitBras()
    {
        final Bras bras = Mockito.mock(Bras.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("designableParent",
                       bras);
        Mockito.when(event.getComponent()).thenReturn(component);
        Mockito.when(component.getAttributes()).thenReturn(attributes);
        Mockito.when(this.brasFactory.getInitializedObject()).thenReturn(new Bras());
        this.designsManager.initBras(event);
        Assert.assertEquals(bras,
                            this.designsManager.getBras().getParent());
    }

    /**
     * Test de la méthode initProduits.
     */
    @Test
    public void testInitProduits()
    {
        final Essai essai = new Essai();
        essai.setDetailProduit(new DetailProduit());
        essai.getDetailProduit().getProduits().add(new Medicament());
        Mockito.when(this.essaiManager.getBean()).thenReturn(essai);
        this.designsManager.initProduits();
        Assert.assertEquals(1,
                            this.designsManager.getProduits().size());
    }

    /**
     * Test de la méthode editBras.
     */
    @Test
    public void testEditBras()
    {
        final Bras bras = Mockito.mock(Bras.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("brasCurrent",
                       bras);
        Mockito.when(event.getComponent()).thenReturn(component);
        Mockito.when(component.getAttributes()).thenReturn(attributes);
        this.designsManager.editBras(event);
        Assert.assertEquals(bras,
                            this.designsManager.getBras());
        Assert.assertEquals(TypeDesignable.BRAS,
                            this.designsManager.getType());
        Assert.assertEquals("EDIT",
                            this.designsManager.getActionCurrent());
    }

    /**
     * Test de la méthode selectDateListener.
     */
    @Test
    public void testSelectDateListener()
    {
        final SelectEvent event = Mockito.mock(SelectEvent.class);
        Mockito.when(event.getObject()).thenReturn(Calendar.getInstance());
        this.designsManager.selectDateListener(event);
        Assert.assertNotNull(this.designsManager.getDateDebut());
    }

    /**
     * Test de la méthode processDateFin.
     */
    @Test
    public void testProcessDateFin()
    {
        final Essai essai = new Essai();
        essai.setDetailDesign(new DetailDesign());
        Mockito.when(this.essaiManager.getBean()).thenReturn(essai);
        Mockito.when(this.timeHelper.getDateFinForDesign(Matchers.any(DetailDesign.class)))
                .thenReturn(new TempsPrescription());
        Mockito.when(this.timeHelper.convertTime(Matchers.any(Calendar.class),
                                                 Matchers.any(TempsPrescription.class)))
                .thenReturn(Calendar.getInstance());
        this.designsManager.processDateFin(Calendar.getInstance());
        Assert.assertNotNull(this.designsManager.getDateFin());
        Mockito.verify(this.timeHelper).convertTime(Matchers.any(Calendar.class),
                                                    Matchers.any(TempsPrescription.class));
    }

    /**
     * Test de la méthode processDateFin.
     */
    @Test(expected = CommonException.class)
    public void testProcessDateFinKo()
    {
        final Essai essai = new Essai();
        essai.setDetailDesign(new DetailDesign());
        Mockito.when(this.essaiManager.getBean()).thenReturn(essai);
        Mockito.doThrow(new CommonException())
                .when(this.timeHelper)
                .getDateFinForDesign(Matchers.any(DetailDesign.class));
        Mockito.when(this.timeHelper.convertTime(Matchers.any(Calendar.class),
                                                 Matchers.any(TempsPrescription.class)))
                .thenReturn(Calendar.getInstance());
        this.designsManager.processDateFin(Calendar.getInstance());
        Assert.assertNull(this.designsManager.getDateFin());
        Mockito.verify(this.timeHelper,
                       Mockito.never()).convertTime(Matchers.any(Calendar.class),
                                                    Matchers.any(TempsPrescription.class));
    }

    /**
     * Test de la méthode initSequence.
     */
    @Test
    public void testInitSequence()
    {
        this.initEssai();
        Assert.assertNotNull(this.designsManager.initSequence("Bras 1-sequence 1",
                                                              "Bras 1"));
    }

    /**
     * Test de la méthode findBras.
     */
    @Test
    public void testFindBras()
    {
        this.initEssai();
        Assert.assertNotNull(this.designsManager.findBras("Bras 1"));
    }

    /**
     * Test de la méthode init.
     */
    @Test
    public void testInitMethod()
    {
        this.initEssai();
        this.designsManager.setActionCurrent("test");
        this.designsManager.setBras(new Bras());
        this.designsManager.setType(TypeDesignable.SEQUENCE);
        this.designsManager.setDateFin(Calendar.getInstance());
        this.designsManager.setJson(new JSONArray());
        this.designsManager.setJsonDate(new JSONObject());
        this.designsManager.setRoot(null);

        this.designsManager.init();

        Assert.assertNull(this.designsManager.getBras());
        Assert.assertNull(this.designsManager.getType());
        Assert.assertNull(this.designsManager.getDateFin());
        Assert.assertNull(this.designsManager.getJson());
        Assert.assertNull(this.designsManager.getJsonDate());
        Assert.assertEquals("",
                            this.designsManager.getActionCurrent());
        Assert.assertNotNull(this.designsManager.getProduits());
    }
    /**
     * Méthode en charge d'initialiser l'essai pour les tests.
     */
    private void initEssai()
    {
        final Bras b1 = new Bras();
        b1.setNom("Bras 1");
        final Bras b2 = new Bras();
        b2.setNom("Bras 2");
        final Essai essai = new Essai();
        final DetailDesign detail = new DetailDesign();
        detail.getBras().add(b1);
        detail.getBras().add(b2);
        final Sequence s1 = new Sequence();
        s1.setNom("sequence 1");
        s1.setParent(b1);
        b1.getSequences().add(s1);
        essai.setDetailDesign(detail);
        Mockito.when(this.essaiManager.getBean()).thenReturn(essai);

        final DetailProduit produit = new DetailProduit();
        essai.setDetailProduit(produit);
    }

    /**
     * Test de la méthode removeSequence.
     */
    @Test
    public void testRemoveSequence()
    {
        this.initEssai();
        this.designsManager.setCurrent(this.essaiManager
                .getBean()
                .getDetailDesign()
                .getBras()
                .first()
                .getSequences()
                .first());
        this.designsManager.removeSequence();
    }

    /**
     * Test de la méthode removeBras.
     */
    @Test
    public void testRemoveBras()
    {
        this.initEssai();
        this.designsManager.setCurrent(this.essaiManager
                .getBean()
                .getDetailDesign()
                .getBras()
                .first());
        this.designsManager.removeBras();
    }

    /**
     * Test de la méthode addSequence.
     */
    @Test
    public void testAddSequence()
    {
        this.initEssai();
        this.designsManager.setNomCompletSequence("Bras 1-sequence 1");
        this.designsManager.setActionCurrent("EDIT");
        this.designsManager.addSequence(this.essaiManager
                .getBean()
                .getDetailDesign()
                .getBras()
                .first()
                .getSequences()
                .first());

    }
}

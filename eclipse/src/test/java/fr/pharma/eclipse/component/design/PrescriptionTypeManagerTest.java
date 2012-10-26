package fr.pharma.eclipse.component.design;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import fr.pharma.eclipse.component.design.helper.PrescriptionTypeHelper;
import fr.pharma.eclipse.component.design.validator.SequenceValidator;
import fr.pharma.eclipse.component.produit.helper.ConditionnementHelper;
import fr.pharma.eclipse.domain.enums.design.UniteTemps;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.enums.produit.TypeProduit;
import fr.pharma.eclipse.domain.model.design.PrescriptionType;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.design.embedded.TempsPrescription;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.helper.design.TimeHelper;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;

/**
 * Test du manager Prescriptionmanager.
 
 * @version $Revision$ $Date$
 */
public class PrescriptionTypeManagerTest
    extends AbstractEclipseJUnitTest
{

    /**
     * Manager a tester.
     */
    private PrescriptionTypeManager manager;

    /**
     * Service mocké.
     */
    private GenericService<PrescriptionType> mockedService;

    /**
     * Helper mocké.
     */
    private TimeHelper mockedHelper;

    /**
     * Validator de sequence.
     */
    private SequenceValidator mockedSequenceValidator;

    /**
     * Helper conditionnement.
     */
    private ConditionnementHelper conditionnementHelper;

    /**
     * Helper.
     */
    private PrescriptionTypeHelper resumeHelper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp()
    {
        this.resumeHelper = Mockito.mock(PrescriptionTypeHelper.class);
        this.mockedService = Mockito.mock(GenericService.class);
        this.mockedHelper = Mockito.mock(TimeHelper.class);
        this.mockedSequenceValidator = Mockito.mock(SequenceValidator.class);
        this.conditionnementHelper = Mockito.mock(ConditionnementHelper.class);
        this.manager = new PrescriptionTypeManager(this.mockedService);
        this.manager.setTimeHelper(this.mockedHelper);
        this.manager.setHelper(this.resumeHelper);
        this.manager.setSequenceValidator(this.mockedSequenceValidator);
        this.manager.setConditionnementHelper(this.conditionnementHelper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown()
    {
        this.mockedHelper = null;
        this.manager = null;
        this.conditionnementHelper = null;
        this.mockedService = null;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit()
    {
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.mockedHelper);
        Assert.assertNotNull(this.conditionnementHelper);
        Assert.assertNotNull(this.mockedService);
    }

    /**
     * Test de la méthode addPrescription.
     */
    @Test
    public void testAddPrescriptionValidate()
    {
        final Sequence sequence = new Sequence();
        sequence.setNom("seq");
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final UIComponent comp = Mockito.mock(UIComponent.class);
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("sequence",
                sequence);
        Mockito.when(event.getComponent()).thenReturn(comp);
        Mockito.when(comp.getAttributes()).thenReturn(map);
        Mockito.when(this.mockedHelper.getDebut(Matchers.anyCollection()))
                .thenReturn(new TempsPrescription());

        final TempsPrescription t = new TempsPrescription();
        t.setNb(1);
        t.setUnite(UniteTemps.JOUR);
        Mockito.when(this.mockedHelper.getFin(Matchers.anyCollection())).thenReturn(t);
        Mockito.when(this.mockedSequenceValidator.validateSequence(Matchers
                                                                           .any(PrescriptionType.class),
                                                                   Matchers.any(Sequence.class)))
                .thenReturn(true);
        this.manager.setBean(new PrescriptionType());

        this.manager.addPrescription(event);

        Assert.assertEquals(sequence.getNom(),
                            this.manager.getBean().getSequence().getNom());
    }

    /**
     * Test de la méthode addPrescription.
     */
    @Test
    public void testAddPrescriptionContainedValidate()
    {
        final Conditionnement c = new Conditionnement();
        final PrescriptionType p = new PrescriptionType();
        p.setConditionnement(c);
        p.setId(1L);
        final Produit med = new Medicament();
        med.setDenomination("test");
        p.setProduit(med);
        final Sequence sequence = new Sequence();
        sequence.getPrescriptions().add(p);
        sequence.setNom("seq");
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final UIComponent comp = Mockito.mock(UIComponent.class);
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("sequence",
                sequence);
        Mockito.when(event.getComponent()).thenReturn(comp);
        Mockito.when(comp.getAttributes()).thenReturn(map);
        Mockito.when(this.mockedHelper.getDebut(Matchers.anyCollection()))
                .thenReturn(new TempsPrescription());
        Mockito.when(this.mockedHelper.getFin(Matchers.anyCollection()))
                .thenReturn(new TempsPrescription());
        this.manager.setBean(p);
        Mockito.when(this.mockedSequenceValidator.validateSequence(Matchers
                                                                           .any(PrescriptionType.class),
                                                                   Matchers.any(Sequence.class)))
                .thenReturn(true);
        this.manager.addPrescription(event);

        Assert.assertEquals(sequence.getNom(),
                            this.manager.getBean().getSequence().getNom());
    }

    /**
     * Test de la méthode addPrescription.
     */
    @Test
    public void testAddPrescriptionContainedValidateKo()
    {
        final PrescriptionType p = new PrescriptionType();
        p.setId(1L);
        final Sequence sequence = new Sequence();
        sequence.getPrescriptions().add(p);
        sequence.setNom("seq");
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final UIComponent comp = Mockito.mock(UIComponent.class);
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("sequence",
                sequence);
        Mockito.when(event.getComponent()).thenReturn(comp);
        Mockito.when(comp.getAttributes()).thenReturn(map);
        Mockito.when(this.mockedHelper.getDebut(Matchers.anyCollection()))
                .thenReturn(new TempsPrescription());
        Mockito.when(this.mockedHelper.getFin(Matchers.anyCollection()))
                .thenReturn(new TempsPrescription());
        this.manager.setBean(p);
        Mockito.when(this.mockedSequenceValidator.validateSequence(Matchers
                                                                           .any(PrescriptionType.class),
                                                                   Matchers.any(Sequence.class)))
                .thenReturn(false);
        this.manager.addPrescription(event);

        Assert.assertNull(this.manager.getBean().getSequence());
    }

    /**
     * Test de la méthode isDosageVisible().
     */
    @Test
    public void testIsDosageVisible()
    {
        final PrescriptionType prescription = new PrescriptionType();
        Assert.assertFalse(this.manager.isDosageVisible());
        this.manager.setBean(prescription);
        Assert.assertFalse(this.manager.isDosageVisible());
        final Produit produit = Mockito.mock(Produit.class);
        Mockito.when(produit.getType())
                .thenReturn(TypeProduit.DISPOSITIF_MEDICAL)
                .thenReturn(TypeProduit.PRODUIT_THERAPEUTIQUE)
                .thenReturn(TypeProduit.MEDICAMENT);
        this.manager.getBean().setProduit(produit);
        Assert.assertFalse(this.manager.isDosageVisible());
        Assert.assertFalse(this.manager.isDosageVisible());
        Assert.assertTrue(this.manager.isDosageVisible());
    }

    /**
     * Test de la méthode removePrescription().
     */
    @Test
    public void testRemovePrescription()
    {
        final Conditionnement c = new Conditionnement();
        final PrescriptionType p = new PrescriptionType();
        p.setConditionnement(c);
        p.setId(1L);
        final Produit med = new Medicament();
        med.setDenomination("test");
        p.setProduit(med);
        final Sequence sequence = new Sequence();
        sequence.getPrescriptions().add(p);
        p.setSequence(sequence);
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final UIComponent comp = Mockito.mock(UIComponent.class);
        final Map<String, Object> map = new HashMap<String, Object>();
        map.put("prescriptionToDelete",
                p);
        Mockito.when(event.getComponent()).thenReturn(comp);
        Mockito.when(comp.getAttributes()).thenReturn(map);
        this.manager.removePrescription(event);

        Assert.assertEquals(0,
                            sequence.getPrescriptions().size());
    }

    /**
     * Test de la méthode handleConditionnement.
     */
    @Test
    public void testHandleConditionnement()
    {
        this.manager.setBean(new PrescriptionType());
        final Conditionnement conditionnement = Mockito.mock(Conditionnement.class);
        final AjaxBehaviorEvent event = Mockito.mock(AjaxBehaviorEvent.class);
        final HtmlSelectOneMenu select = Mockito.mock(HtmlSelectOneMenu.class);
        Mockito.when(event.getSource()).thenReturn(select);
        Mockito.when(select.getLocalValue()).thenReturn(conditionnement);
        Mockito.when(this.conditionnementHelper.buildResume(conditionnement)).thenReturn("test");
        this.manager.handleConditionnement(event);
        Assert.assertEquals("test",
                            this.manager.getResumeConditionnement());
        Assert.assertEquals(conditionnement,
                            this.manager.getBean().getConditionnement());

    }

    /**
     * Test de la méthode buildResume().
     */
    @Test
    public void testBuildResume()
    {
        this.manager.setBean(new PrescriptionType());
        Mockito.when(this.resumeHelper.buildResume(Matchers.any(PrescriptionType.class)))
                .thenReturn("test");
        this.manager.buildResume();
        Assert.assertEquals("test",
                            this.manager.getResume());
    }

    /**
     * Test de la méthode isModePrescription.
     */
    @Test
    public void testIsModePrescription()
    {
        Assert.assertFalse(this.manager
                .isModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE));
    }

    /**
     * Test de la méthode isModePrescription.
     */
    @Test
    public void testIsModePrescription2()
    {
        this.manager.setBean(new PrescriptionType());
        Assert.assertFalse(this.manager
                .isModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE));
    }

    /**
     * Test de la méthode isModePrescription.
     */
    @Test
    public void testIsModePrescription3()
    {
        this.manager.setBean(new PrescriptionType());
        this.manager.getBean().setConditionnement(new Conditionnement());
        Assert.assertFalse(this.manager
                .isModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE));
    }

    /**
     * Test de la méthode isModePrescription.
     */
    @Test
    public void testIsModePrescription4()
    {
        this.manager.setBean(new PrescriptionType());
        this.manager.getBean().setConditionnement(new Conditionnement());
        this.manager.getBean().getConditionnement().setModePrescription(ModePrescription.DOSE_KG);
        Assert.assertFalse(this.manager
                .isModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE));
    }

    /**
     * Test de la méthode isModePrescription.
     */
    @Test
    public void testIsModePrescriptionOk()
    {
        this.manager.setBean(new PrescriptionType());
        this.manager.getBean().setConditionnement(new Conditionnement());
        this.manager
                .getBean()
                .getConditionnement()
                .setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        Assert.assertTrue(this.manager
                .isModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE));
    }

    /**
     * Test de la méthode reinit().
     */
    @Test
    public void testReinit()
    {
        this.manager.setBean(new PrescriptionType());
        this.manager.setResume("");
        this.manager.setResumeConditionnement("");
        this.manager.reinit();
        Assert.assertNull(this.manager.getBean());
        Assert.assertNull(this.manager.getResume());
        Assert.assertNull(this.manager.getResumeConditionnement());
    }

    /**
     * Test de la méthode editPrescription.
     */
    @Test
    public void testEditPrescription()
    {
        final Conditionnement conditionnement = Mockito.mock(Conditionnement.class);
        final PrescriptionType p = Mockito.mock(PrescriptionType.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final Map<String, Object> attributes = new HashMap<String, Object>();
        attributes.put("prescriptionToEdit",
                       p);
        Mockito.when(event.getComponent()).thenReturn(component);
        Mockito.when(this.conditionnementHelper.buildResume(Matchers.any(Conditionnement.class)))
                .thenReturn("test");
        Mockito.when(component.getAttributes()).thenReturn(attributes);

        this.manager.setBean(new PrescriptionType());
        this.manager.getBean().setConditionnement(conditionnement);
        Mockito.when(this.resumeHelper.buildResume(Matchers.any(PrescriptionType.class)))
                .thenReturn("test");
        this.manager.editPrescription(event);
        Assert.assertEquals("test",
                            this.manager.getResume());
        Assert.assertEquals("test",
                            this.manager.getResumeConditionnement());
        Assert.assertNotNull(this.manager.getBean());
    }
}

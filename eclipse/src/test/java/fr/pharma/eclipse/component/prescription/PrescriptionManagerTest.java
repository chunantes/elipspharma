package fr.pharma.eclipse.component.prescription;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.TreeNode;

import fr.pharma.eclipse.comparator.habilitation.HabilitationComparator;
import fr.pharma.eclipse.component.design.helper.TreeDesignHelper;
import fr.pharma.eclipse.component.prescription.helper.PrescriptionManagerHelper;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.enums.TypeDispensation;
import fr.pharma.eclipse.domain.enums.produit.ModePrescription;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.design.Bras;
import fr.pharma.eclipse.domain.model.design.Sequence;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.pharma.DetailDonneesPharma;
import fr.pharma.eclipse.domain.model.essai.detail.produit.DetailProduit;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.domain.model.patient.Inclusion;
import fr.pharma.eclipse.domain.model.patient.Patient;
import fr.pharma.eclipse.domain.model.prescription.Prescription;
import fr.pharma.eclipse.domain.model.prescription.ProduitPrescrit;
import fr.pharma.eclipse.domain.model.produit.Conditionnement;
import fr.pharma.eclipse.domain.model.produit.Medicament;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.factory.prescription.PrescriptionFactory;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.service.habilitation.helper.HabilitationsHelper;
import fr.pharma.eclipse.service.patient.PatientService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.FacesUtils;
import fr.pharma.eclipse.validator.prescription.ProduitPrescritValidator;

/**
 * Test du manager PrescriptionManager.
 
 * @version $Revision$ $Date$
 */
public class PrescriptionManagerTest
    extends AbstractEclipseJUnitTest
{

    /**
     * Manager.
     */
    private PrescriptionManager manager;

    /**
     * Service Prescription mocké.
     */
    private GenericService<Prescription> mockedService;

    /**
     * Helper Design tree mocké.
     */
    private TreeDesignHelper mockedTreeHelper;

    /**
     * Helper prescription manager mocké.
     */
    private PrescriptionManagerHelper mockedPrescriptionHelper;

    /**
     * Habilitation helper mocké.
     */
    private HabilitationsHelper mockedHabilitationHelper;

    /**
     * Service patient mocké.
     */
    private PatientService mockedPatientService;

    /**
     * FacesUtils mocké.
     */
    private FacesUtils mockedFacesUtils;

    /**
     * Factory mocké.
     */
    private PrescriptionFactory mockedFactory;

    /**
     * Validator mocké.
     */
    private ProduitPrescritValidator validator;

    /**
     * Service Essai.
     */
    private EssaiService essaiService;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp()
    {
        this.validator = Mockito.mock(ProduitPrescritValidator.class);
        this.mockedService = Mockito.mock(GenericService.class);
        this.mockedPrescriptionHelper = Mockito.mock(PrescriptionManagerHelper.class);
        this.mockedTreeHelper = Mockito.mock(TreeDesignHelper.class);
        this.essaiService = Mockito.mock(EssaiService.class);
        this.mockedPatientService = Mockito.mock(PatientService.class);
        this.mockedHabilitationHelper = Mockito.mock(HabilitationsHelper.class);
        this.mockedFacesUtils = Mockito.mock(FacesUtils.class);
        this.mockedFactory = Mockito.mock(PrescriptionFactory.class);
        this.manager = new PrescriptionManager(this.mockedService);
        this.manager.setHabilitationHelper(this.mockedHabilitationHelper);
        this.manager.setPatientService(this.mockedPatientService);
        this.manager.setTreeDesignHelper(this.mockedTreeHelper);
        this.manager.setFacesUtils(this.mockedFacesUtils);
        this.manager.setPrescriptionManagerHelper(this.mockedPrescriptionHelper);
        this.manager.setFactory(this.mockedFactory);
        this.manager.setEssaiService(this.essaiService);
        this.manager.setValidator(this.validator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown()
    {
        this.mockedFacesUtils = null;
        this.mockedPatientService = null;
        this.mockedPrescriptionHelper = null;
        this.mockedService = null;
        this.mockedHabilitationHelper = null;
        this.mockedTreeHelper = null;
        this.mockedFactory = null;
        this.manager = null;
        this.essaiService = null;
        this.validator = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.mockedFacesUtils);
        Assert.assertNotNull(this.mockedPatientService);
        Assert.assertNotNull(this.mockedPrescriptionHelper);
        Assert.assertNotNull(this.mockedService);
        Assert.assertNotNull(this.mockedTreeHelper);
        Assert.assertNotNull(this.mockedHabilitationHelper);
        Assert.assertNotNull(this.mockedFactory);
        Assert.assertNotNull(this.validator);
        this.manager.setProduits(Mockito.mock(SortedSet.class));
        Assert.assertNotNull(this.essaiService);
        Assert.assertNotNull(this.manager.getProduits());
    }

    /**
     * Test de la méthode init().
     */
    @Test
    public void testInitMethod()
    {
        this.manager.setEssaiSelected(new Essai());
        this.manager.setPatientSelected(new Patient());
        this.manager.setValid(true);
        this.manager.setBean(new Prescription());
        this.manager.init();
        Assert.assertNull(this.manager.getBean());
        Assert.assertNull(this.manager.getEssaiSelected());
        Assert.assertNull(this.manager.getPatientSelected());
        Assert.assertFalse(this.manager.getValid());
        Assert.assertFalse(this.manager.getReadOnly());
        Assert.assertTrue(this.manager.getProduits().isEmpty());
    }
    /**
     * Test de la méthode handleSelectPatient.
     */
    @Test
    public void testHandleSelectPatientNull()
    {
        final SelectEvent event = Mockito.mock(SelectEvent.class);
        final Patient patient = new Patient();
        Mockito.when(event.getObject()).thenReturn(patient);
        Mockito.when(this.mockedPatientService.getInclusionCourante(Matchers.any(Patient.class)))
                .thenReturn(null);

        this.manager.handleSelectPatient(event);

        Mockito.verify(this.mockedFacesUtils).addMessage(FacesMessage.SEVERITY_ERROR,
                                                         "patient.non.inclu.error");
        Assert.assertFalse(this.manager.getValid());
    }

    /**
     * Test de la méthode handleSelectPatient.
     */
    @Test
    public void testHandleSelectPatientNotNullInvestigateursEmpty()
    {
        final Habilitation h = new Habilitation();
        h.setPersonne(new Investigateur());
        final SortedSet<Habilitation> habilitations =
            new TreeSet<Habilitation>(new HabilitationComparator());

        Mockito.when(this.mockedHabilitationHelper.getHabilitations(Matchers.any(Essai.class),
                                                                    Matchers.anyList(),
                                                                    Matchers.anyBoolean()))
                .thenReturn(habilitations);

        final SelectEvent event = Mockito.mock(SelectEvent.class);
        final Patient patient = new Patient();
        final Inclusion inclusion = new Inclusion();
        final Essai essai = new Essai();
        final DetailDonneesPharma detailDonneesPharma = new DetailDonneesPharma();
        essai.setDetailDonneesPharma(detailDonneesPharma);
        detailDonneesPharma
                .getInfosDispensations()
                .setTypeDispensation(TypeDispensation.NOMINATIVE);
        inclusion.setEssai(essai);
        inclusion.getEssai().setDetailProduit(new DetailProduit());
        Mockito.when(event.getObject()).thenReturn(patient);
        Mockito.when(this.mockedPatientService.getInclusionCourante(Matchers.any(Patient.class)))
                .thenReturn(inclusion);
        Mockito.when(this.mockedFactory.getInitializedObject(Matchers.any(Inclusion.class)))
                .thenReturn(new Prescription());

        this.manager.handleSelectPatient(event);

        Mockito.verify(this.mockedFacesUtils,
                       Mockito.never()).addMessage(FacesMessage.SEVERITY_ERROR,
                                                   "patient.non.inclu.error");

        Assert.assertNull(this.manager.getBean());
        Assert.assertFalse(this.manager.getValid());

        Mockito.verify(this.mockedFacesUtils).addMessage(FacesMessage.SEVERITY_ERROR,
                                                         "investigateur.non.present.error");
    }

    /**
     * Test de la méthode handleSelectPatient.
     */
    @Test
    public void testHandleSelectPatientNotNull()
    {
        final Habilitation h = new Habilitation();
        h.setPersonne(new Investigateur());
        final SortedSet<Habilitation> habilitations =
            new TreeSet<Habilitation>(new HabilitationComparator());
        habilitations.add(h);

        Mockito.when(this.mockedHabilitationHelper.getHabilitations(Matchers.any(Essai.class),
                                                                    Matchers.anyList(),
                                                                    Matchers.anyBoolean()))
                .thenReturn(habilitations);

        final SelectEvent event = Mockito.mock(SelectEvent.class);
        final Patient patient = new Patient();
        final Inclusion inclusion = new Inclusion();
        final Essai essai = new Essai();
        final DetailDonneesPharma detailDonneesPharma = new DetailDonneesPharma();
        essai.setDetailDonneesPharma(detailDonneesPharma);
        detailDonneesPharma
                .getInfosDispensations()
                .setTypeDispensation(TypeDispensation.NOMINATIVE);
        inclusion.setEssai(essai);
        inclusion.getEssai().setDetailProduit(new DetailProduit());
        Mockito.when(event.getObject()).thenReturn(patient);
        Mockito.when(this.mockedPatientService.getInclusionCourante(Matchers.any(Patient.class)))
                .thenReturn(inclusion);
        Mockito.when(this.mockedFactory.getInitializedObject(Matchers.any(Inclusion.class)))
                .thenReturn(new Prescription());

        this.manager.handleSelectPatient(event);

        Mockito.verify(this.mockedFacesUtils,
                       Mockito.never()).addMessage(FacesMessage.SEVERITY_ERROR,
                                                   "patient.non.inclu.error");

        Mockito.verify(this.mockedFactory).getInitializedObject(Matchers.any(Inclusion.class));
        Assert.assertNotNull(this.manager.getBean());
        Assert.assertTrue(this.manager.getValid());
    }

    /**
     * Test de la méthode handleSelectPatient.
     */
    @Test
    public void testHandleSelectPatientNotNullNotEmpty()
    {
        final Habilitation h = new Habilitation();
        h.setPersonne(new Investigateur());
        final SortedSet<Habilitation> habilitations =
            new TreeSet<Habilitation>(new HabilitationComparator());
        habilitations.add(h);

        Mockito.when(this.mockedHabilitationHelper.getHabilitations(Matchers.any(Essai.class),
                                                                    Matchers.anyList(),
                                                                    Matchers.anyBoolean()))
                .thenReturn(habilitations);
        final SelectEvent event = Mockito.mock(SelectEvent.class);
        final Patient patient = new Patient();
        final Inclusion inclusion = new Inclusion();
        final Essai essai = new Essai();
        final DetailDonneesPharma detailDonneesPharma = new DetailDonneesPharma();
        essai.setDetailDonneesPharma(detailDonneesPharma);
        detailDonneesPharma
                .getInfosDispensations()
                .setTypeDispensation(TypeDispensation.NOMINATIVE);
        inclusion.setEssai(essai);
        inclusion.getEssai().setDetailProduit(new DetailProduit());
        Mockito.when(event.getObject()).thenReturn(patient);
        Mockito.when(this.mockedPatientService.getInclusionCourante(Matchers.any(Patient.class)))
                .thenReturn(inclusion);
        Mockito.when(this.mockedFactory.getInitializedObject(Matchers.any(Prescription.class)))
                .thenReturn(new Prescription());

        final List<Prescription> liste = new ArrayList<Prescription>();
        liste.add(new Prescription());
        Mockito.when(this.mockedService.getAll(Matchers.any(SearchCriteria.class)))
                .thenReturn(liste);

        this.manager.handleSelectPatient(event);

        Mockito.verify(this.mockedFacesUtils,
                       Mockito.never()).addMessage(FacesMessage.SEVERITY_ERROR,
                                                   "patient.non.inclu.error");

        Mockito.verify(this.mockedFactory).getInitializedObject(Matchers.any(Prescription.class));
        Assert.assertNotNull(this.manager.getBean());
        Assert.assertTrue(this.manager.getValid());
    }

    /**
     * Test de la méthode getInvestigateurs.
     */
    @Test
    public void testGetInvestigateurs()
    {

        final Habilitation h = new Habilitation();
        final Personne p = new Investigateur();
        p.setId(1L);
        h.setPersonne(p);
        final SortedSet<Habilitation> liste = new TreeSet<Habilitation>();
        liste.add(h);
        this.manager.setBean(new Prescription());
        this.manager.getBean().setInclusion(new Inclusion());
        this.manager.getBean().getInclusion().setEssai(new Essai());
        Mockito.when(this.mockedHabilitationHelper.getHabilitations(Matchers.any(Essai.class),
                                                                    Matchers.anyList(),
                                                                    Matchers.anyBoolean()))
                .thenReturn(liste);

        final Collection<Personne> result = this.manager.getInvestigateurs();
        Assert.assertEquals(1,
                            liste.size());
    }

    /**
     * Test de la méthode delProduitPrescrit.
     */
    @Test
    public void testDelProduitPrescrit()
    {

        final ProduitPrescrit produitPrescrit = new ProduitPrescrit();
        final Produit p = new Medicament();
        final Prescription prescription = new Prescription();
        prescription.getProduitsPrescrits().add(produitPrescrit);
        this.manager.setBean(prescription);
        p.setDenomination("");
        produitPrescrit.setProduit(p);
        final Conditionnement c = new Conditionnement();
        c.setModePrescription(ModePrescription.CONDITIONNEMENT_PRIMAIRE);
        produitPrescrit.setConditionnement(c);
        final ActionEvent event = Mockito.mock(ActionEvent.class);
        final UIComponent component = Mockito.mock(UIComponent.class);
        Mockito.when(event.getComponent()).thenReturn(component);
        final Map<String, Object> value = new HashMap<String, Object>();
        value.put("produitToDelete",
                  produitPrescrit);
        Mockito.when(component.getAttributes()).thenReturn(value);

        Assert.assertEquals(1,
                            prescription.getProduitsPrescrits().size());
        this.manager.delProduitPrescrit(event);

        Assert.assertEquals(0,
                            prescription.getProduitsPrescrits().size());
    }

    /**
     * Test de la méthode updateDesign.
     */
    @Test
    public void testUpdateDesignNull()
    {
        this.manager.updateDesign();
        Mockito.verify(this.mockedFacesUtils,
                       Mockito.never()).addMessage(Matchers.any(Severity.class),
                                                   Matchers.anyString());
        Mockito.verify(this.mockedPrescriptionHelper,
                       Mockito.never()).initProduitsPrescrits(Matchers.any(Prescription.class));
    }

    /**
     * Test de la méthode updateDesign.
     */
    @Test
    public void testUpdateDesignBras()
    {
        final TreeNode node = Mockito.mock(TreeNode.class);
        Mockito.when(node.getData()).thenReturn(new Bras());
        this.manager.setNodeSelected(node);
        this.manager.setBean(new Prescription());
        this.manager.updateDesign();
        Mockito.verify(this.mockedFacesUtils).addMessage(FacesMessage.SEVERITY_ERROR,
                                                         "prescription.designable.sequence");
        Mockito.verify(this.mockedPrescriptionHelper,
                       Mockito.never()).initProduitsPrescrits(Matchers.any(Prescription.class));
        Assert.assertNull(this.manager.getNodeSelected());
    }

    /**
     * Test de la méthode updateDesign.
     */
    @Test
    public void testUpdateDesignSequence()
    {
        final TreeNode node = Mockito.mock(TreeNode.class);
        this.manager.setNodeSelected(node);
        Mockito.when(node.getData()).thenReturn(new Sequence());
        this.manager.setBean(new Prescription());
        this.manager.updateDesign();
        Mockito.verify(this.mockedFacesUtils,
                       Mockito.never()).addMessage(FacesMessage.SEVERITY_ERROR,
                                                   "prescription.designable.sequence");
        Mockito.verify(this.mockedPrescriptionHelper)
                .initProduitsPrescrits(Matchers.any(Prescription.class));

        Assert.assertNotNull(this.manager.getBean().getSequence());
    }

    /**
     * Test de la méthode getDesignablesSelectable.
     */
    @Test
    public void testGetDesignablesSelectableNull1()
    {

        Assert.assertNull(this.manager.getDesignablesSelectable());
        Mockito.verify(this.mockedTreeHelper,
                       Mockito.never()).buildTree(Matchers.any(Essai.class));
    }

    /**
     * Test de la méthode getDesignablesSelectable.
     */
    @Test
    public void testGetDesignablesSelectableNull2()
    {
        this.manager.setBean(new Prescription());
        Assert.assertNull(this.manager.getDesignablesSelectable());
        Mockito.verify(this.mockedTreeHelper,
                       Mockito.never()).buildTree(Matchers.any(Essai.class));
    }

    /**
     * Test de la méthode getDesignablesSelectable.
     */
    @Test
    public void testGetDesignablesSelectableNull3()
    {
        this.manager.setBean(new Prescription());
        this.manager.getBean().setInclusion(new Inclusion());
        Assert.assertNull(this.manager.getDesignablesSelectable());
        Mockito.verify(this.mockedTreeHelper,
                       Mockito.never()).buildTree(Matchers.any(Essai.class));
    }

    /**
     * Test de la méthode getDesignablesSelectable.
     */
    @Test
    public void testGetDesignablesSelectable()
    {
        final TreeNode node = Mockito.mock(TreeNode.class);
        this.manager.setBean(new Prescription());
        this.manager.getBean().setInclusion(new Inclusion());
        this.manager.getBean().getInclusion().setEssai(new Essai());
        Mockito.when(this.mockedTreeHelper.buildTree(Matchers.any(Essai.class))).thenReturn(node);
        Mockito.when(this.essaiService.get(Matchers.anyLong())).thenReturn(new Essai());
        Assert.assertNotNull(this.manager.getDesignablesSelectable());
        Mockito.verify(this.mockedTreeHelper).buildTree(Matchers.any(Essai.class));
    }
}

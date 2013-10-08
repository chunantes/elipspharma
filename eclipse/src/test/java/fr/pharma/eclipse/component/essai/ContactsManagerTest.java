package fr.pharma.eclipse.component.essai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import fr.pharma.eclipse.comparator.habilitation.HabilitationComparator;
import fr.pharma.eclipse.component.SelectableBeansManager;
import fr.pharma.eclipse.component.essai.helper.DroitHabilitationInitializer;
import fr.pharma.eclipse.component.essai.helper.SelectableContactsRetriever;
import fr.pharma.eclipse.component.helper.BeanManagerHelper;
import fr.pharma.eclipse.component.wrapper.SelectableBean;
import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.enums.GroupeContacts;
import fr.pharma.eclipse.domain.enums.TypePersonne;
import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.factory.habilitation.HabilitationFactory;
import fr.pharma.eclipse.factory.wrapper.SelectableBeanFactory;
import fr.pharma.eclipse.service.habilitation.helper.HabilitationsHelper;
import fr.pharma.eclipse.service.user.UserService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.PersonneUtils;
import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Test de la classe ContactsManagerTest.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ContactsManagerTest extends AbstractEclipseJUnitTest {

    /**
     * Manager testé.
     */
    private ContactsManager manager;

    /**
     * Helper mocké.
     */
    private BeanManagerHelper<BeanObject> mockedHelper;

    /**
     * Mock du retriever de contacts.
     */
    private SelectableContactsRetriever mockedRetriever;

    /**
     * Service des utilisateurs mocké.
     */
    private UserService mockedUserService;

    /**
     * Type de contact pour les tests.
     */
    private static final TypeContact TYPE_CONTACT = TypeContact.CRO;

    /**
     * Initialiseur mocké.
     */
    private DroitHabilitationInitializer mockedInitializer;

    /**
     * Mock de la fabrique d'habilitations.
     */
    private HabilitationFactory mockedHabilitationFactory;

    /**
     * Helper mocké des habilitations.
     */
    private HabilitationsHelper mockedHabilitationsHelper;

    /**
     * Manager de personnes mocké.
     */
    private SelectableBeansManager<Personne> mockedPersonnesManager;

    /**
     * Fabrique d'objets wrappés mockée.
     */
    private SelectableBeanFactory<Personne> mockedSelectableBeanFactory;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setUp() {
        this.mockedHelper = Mockito.mock(BeanManagerHelper.class);
        this.mockedRetriever = Mockito.mock(SelectableContactsRetriever.class);
        this.mockedUserService = Mockito.mock(UserService.class);
        this.mockedHabilitationFactory = Mockito.mock(HabilitationFactory.class);
        this.mockedHabilitationsHelper = Mockito.mock(HabilitationsHelper.class);
        this.mockedSelectableBeanFactory = Mockito.mock(SelectableBeanFactory.class);
        this.mockedPersonnesManager = Mockito.mock(SelectableBeansManager.class);
        this.mockedInitializer = Mockito.mock(DroitHabilitationInitializer.class);
        this.manager = new ContactsManager();
        this.manager.setHelper(this.mockedHelper);
        this.manager.setContactsRetriever(this.mockedRetriever);
        this.manager.setUserService(this.mockedUserService);
        this.manager.setSelectableContactsManager(this.mockedPersonnesManager);
        this.manager.setHabilitationFactory(this.mockedHabilitationFactory);
        this.manager.setHabilitationsHelper(this.mockedHabilitationsHelper);
        this.manager.setSelectableBeanFactory(this.mockedSelectableBeanFactory);
        this.manager.setInitializers(new TreeMap<String, DroitHabilitationInitializer>());
        this.manager.getInitializers().put(ContactsManagerTest.TYPE_CONTACT.name(), this.mockedInitializer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.manager = null;
        this.mockedHelper = null;
        this.mockedRetriever = null;
        this.mockedUserService = null;
        this.mockedHabilitationFactory = null;
        this.mockedHabilitationsHelper = null;
        this.mockedSelectableBeanFactory = null;
        this.mockedPersonnesManager = null;
        this.mockedInitializer = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.manager);
        Assert.assertEquals(this.mockedHelper, this.manager.getHelper());
        Assert.assertEquals(this.mockedUserService, this.manager.getUserService());
        Assert.assertEquals(this.mockedRetriever, this.manager.getContactsRetriever());
        Assert.assertEquals(this.mockedPersonnesManager, this.manager.getSelectableContactsManager());
        Assert.assertEquals(this.mockedHabilitationFactory, this.manager.getHabilitationFactory());
        Assert.assertEquals(this.mockedHabilitationsHelper, this.manager.getHabilitationsHelper());
        Assert.assertEquals(this.mockedInitializer, this.manager.getInitializers().get(ContactsManagerTest.TYPE_CONTACT.name()));
    }

    /**
     * Test de la méthode getGroupeHabilitations.
     */
    @Test
    public void testGetGroupeHabilitations() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final GroupeContacts groupeContact = GroupeContacts.INVESTIGATEURS;
        final SortedSet<Habilitation> expectedRes = new TreeSet<Habilitation>(new HabilitationComparator());
        expectedRes.addAll(Arrays.asList(EssaiUtils.makeHabilitationTest(id++, Droit.ARC_INVESTIGATEUR, "pers1", TypePersonne.ARC_INVESTIGATEUR),
                                         EssaiUtils.makeHabilitationTest(id++, Droit.ARC_INVESTIGATEUR, "pers1", TypePersonne.ARC_INVESTIGATEUR)));
        Mockito.when(this.mockedHabilitationsHelper.getHabilitations(essai, groupeContact.getDroits())).thenReturn(expectedRes);
        Assert.assertEquals(expectedRes, this.manager.getGroupeHabilitations(essai, groupeContact));
        Mockito.verify(this.mockedHabilitationsHelper).getHabilitations(essai, groupeContact.getDroits());
    }

    /**
     * Test de la méthode hasInvestigateurPrincipal - true.
     */
    @Test
    public void testHasInvestigateurPrincipalTrue() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        Mockito.when(this.mockedHabilitationsHelper.getInvestigateurPrincipal(essai)).thenReturn(new Investigateur());

        Assert.assertTrue(this.manager.hasInvestigateurPrincipal(essai));
        Mockito.verify(this.mockedHabilitationsHelper).getInvestigateurPrincipal(essai);
    }

    /**
     * Test de la méthode hasInvestigateurPrincipal - false.
     */
    @Test
    public void testHasInvestigateurPrincipalFalse() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);

        Mockito.when(this.mockedHabilitationsHelper.getInvestigateurPrincipal(essai)).thenReturn(null);

        Assert.assertFalse(this.manager.hasInvestigateurPrincipal(essai));
        Mockito.verify(this.mockedHabilitationsHelper).getInvestigateurPrincipal(essai);
    }

    /**
     * Test de la méthode de désactivation des contacts.
     */
    @Test
    public void testDisableContacts() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        essai.getDetailContacts().getHabilitations().add(EssaiUtils.makeHabilitationTest(id++));
        final String expectedLogin = "SRM";

        final Habilitation selectedHabilitation = EssaiUtils.makeHabilitationTest(id++, true, true);
        final Personne personne = Mockito.mock(Personne.class);
        Mockito.when(this.mockedUserService.getPersonne()).thenReturn(personne);
        Mockito.when(personne.getLogin()).thenReturn(expectedLogin);
        Mockito.when(this.mockedHelper.getBeansSelected(essai.getDetailContacts().getHabilitations())).thenReturn(Arrays.asList(selectedHabilitation));

        this.manager.disableContacts(essai);

        Mockito.verify(this.mockedUserService).getPersonne();
        this.verify(essai.getDetailContacts().getHabilitations(), selectedHabilitation, expectedLogin);
    }

    /**
     * Test de la méthode initSelectableContacts.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testInitSelectableContacts() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final TypeContact typeContact = ContactsManagerTest.TYPE_CONTACT;
        this.manager.setTypeContactToAdd(typeContact);

        final Personne cro1 = PersonneUtils.makeCro(id++);
        final SelectableBean<Personne> selectable1 = new SelectableBean<Personne>(cro1);
        final Personne cro2 = PersonneUtils.makeCro(id++);
        final SelectableBean<Personne> selectable2 = new SelectableBean<Personne>(cro2);
        final List<Personne> expectedBeans = Arrays.asList(cro1, cro2);
        final List<SelectableBean<Personne>> expectedSelectableBeans = new ArrayList<SelectableBean<Personne>>();
        Mockito.when(this.mockedPersonnesManager.getBeans()).thenReturn(expectedSelectableBeans);
        Mockito.when(this.mockedRetriever.retrieveSelectableContacts(essai, typeContact)).thenReturn(expectedBeans);
        Mockito.when(this.mockedSelectableBeanFactory.getInitializedObjects(expectedBeans)).thenReturn(Arrays.asList(selectable1, selectable2));
        this.manager.initSelectableContacts(essai);
        Mockito.verify(this.mockedPersonnesManager, Mockito.times(2)).getBeans();
        Mockito.verify(this.mockedRetriever).retrieveSelectableContacts(essai, typeContact);
        Mockito.verify(this.mockedSelectableBeanFactory).getInitializedObjects(expectedBeans);
        Assert.assertEquals(expectedBeans.size(), expectedSelectableBeans.size());
        Assert.assertTrue(expectedSelectableBeans.contains(selectable1));
        Assert.assertTrue(expectedSelectableBeans.contains(selectable2));
    }

    /**
     * Test de la méthode reset.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testReset() {
        final Habilitation habilitation = Mockito.mock(Habilitation.class);
        final TypeContact typeContact = ContactsManagerTest.TYPE_CONTACT;
        this.manager.setTypeContactToAdd(typeContact);
        this.manager.setSelectedHabilitation(habilitation);
        final List<SelectableBean<Personne>> expectedSelectableBeans = new ArrayList<SelectableBean<Personne>>();
        expectedSelectableBeans.add(Mockito.mock(SelectableBean.class));
        expectedSelectableBeans.add(Mockito.mock(SelectableBean.class));
        expectedSelectableBeans.add(Mockito.mock(SelectableBean.class));
        Mockito.when(this.mockedPersonnesManager.getBeans()).thenReturn(expectedSelectableBeans);
        this.manager.reset();
        Assert.assertEquals(typeContact, this.manager.getTypeContactToAdd());
        Assert.assertTrue(expectedSelectableBeans.isEmpty());
        Assert.assertNull(this.manager.getSelectedHabilitation());
    }

    /**
     * Test de la méthode ajouterContacts.
     */
    @Test
    public void testAjouterContacts() {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final TypeContact typeContact = ContactsManagerTest.TYPE_CONTACT;
        this.manager.setTypeContactToAdd(typeContact);
        final Personne expectedSelected = PersonneUtils.makeCro(id++);
        final List<Personne> expectedBeans = new ArrayList<Personne>();
        expectedBeans.addAll(Arrays.asList(PersonneUtils.makePharmacien(id++), PersonneUtils.makePharmacien(id++), PersonneUtils.makePharmacien(id++)));
        final List<SelectableBean<Personne>> expectedSelectableBeans = new ArrayList<SelectableBean<Personne>>();
        for (final Personne personne : expectedBeans) {
            expectedSelectableBeans.add(new SelectableBean<Personne>(personne));
        }
        Mockito.when(this.mockedPersonnesManager.getBeans()).thenReturn(expectedSelectableBeans);
        Mockito.when(this.mockedPersonnesManager.getBeansSelected()).thenReturn(Arrays.asList(new SelectableBean<Personne>(expectedSelected)));

        final Habilitation expectedHabilitation = new Habilitation();
        expectedHabilitation.setId(id++);
        Mockito.when(this.mockedHabilitationFactory.getInitializedObject()).thenReturn(expectedHabilitation);
        Mockito.doAnswer(new Answer<Object>() {

            @Override
            public Object answer(final InvocationOnMock invocation) throws Throwable {
                final Habilitation argHab = (Habilitation) invocation.getArguments()[0];
                Assert.assertEquals(expectedHabilitation.getId(), argHab.getId());
                return null;
            }
        }).when(this.mockedInitializer).initialize(Matchers.any(Habilitation.class));
        Assert.assertTrue(essai.getDetailContacts().getHabilitations().isEmpty());

        this.manager.ajouterContacts(essai);

        Mockito.verify(this.mockedPersonnesManager).getBeansSelected();
        Mockito.verify(this.mockedHabilitationFactory).getInitializedObject();
        Assert.assertEquals(1, essai.getDetailContacts().getHabilitations().size());
        final Habilitation habilitation = essai.getDetailContacts().getHabilitations().first();
        Assert.assertEquals(expectedSelected, habilitation.getPersonne());
        Assert.assertEquals(essai.getDetailContacts(), habilitation.getDetailContacts());
        // reset
        Mockito.verify(this.mockedInitializer).initialize(Matchers.any(Habilitation.class));
        Mockito.verify(this.mockedPersonnesManager).getBeans();
        Assert.assertEquals(typeContact, this.manager.getTypeContactToAdd());
        Assert.assertTrue(expectedSelectableBeans.isEmpty());
    }

    /**
     * Méthode de vérification unitaire.
     * @param groupe Groupe d'habilitations à vérifier.
     * @param selected Elément sélectionné dans le groupe.
     * @param expectedLogin Login de la personne ayant effectué la
     * désactivation.
     */
    private void verify(final SortedSet<Habilitation> groupe,
                        final Habilitation selected,
                        final Object expectedLogin) {
        Mockito.verify(this.mockedHelper).getBeansSelected(groupe);
        Assert.assertFalse(selected.isActive());
        Assert.assertFalse(selected.getSelected());
        Assert.assertEquals(expectedLogin, selected.getAuteurDesactivation());
        final String pattern = "yyyyMMddHHm";
        Assert.assertEquals(Utils.formatDate(Calendar.getInstance(EclipseConstants.LOCALE).getTime(), pattern),
                            Utils.formatDate(selected.getDateDesactivation().getTime(), pattern));
    }
}

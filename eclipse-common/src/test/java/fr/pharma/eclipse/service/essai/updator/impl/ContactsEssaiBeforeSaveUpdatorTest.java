package fr.pharma.eclipse.service.essai.updator.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.util.StringUtils;

import fr.pharma.eclipse.domain.criteria.acteur.PharmacienSearchCriteria;
import fr.pharma.eclipse.domain.criteria.common.SearchCriteria;
import fr.pharma.eclipse.domain.enums.Droit;
import fr.pharma.eclipse.domain.enums.TypePharmacien;
import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.domain.model.stockage.Pharmacie;
import fr.pharma.eclipse.factory.habilitation.HabilitationFactory;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.PersonneUtils;
import fr.pharma.eclipse.utils.PharmacieUtils;

/**
 * Test de la classe ContactsEssaiBeforeSaveUpdator.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class ContactsEssaiBeforeSaveUpdatorTest extends AbstractEclipseJUnitTest {
    /**
     * Updator testé.
     */
    private ContactsEssaiBeforeSaveUpdator updator;

    /**
     * Mock de la fabrique Spring.
     */
    private BeanFactory mockedBeanFactory;

    /**
     * Mock du service des pharmaciens.
     */
    private GenericService<Pharmacien> mockedPharmacienService;

    /**
     * Mock de la fabrique d'habilitations.
     */
    private HabilitationFactory mockedHabilitationFactory;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setUp() {
        this.mockedBeanFactory = Mockito.mock(BeanFactory.class);
        this.mockedHabilitationFactory = Mockito.mock(HabilitationFactory.class);
        this.mockedPharmacienService = Mockito.mock(GenericService.class);
        this.updator = new ContactsEssaiBeforeSaveUpdator();
        this.updator.setBeanFactory(this.mockedBeanFactory);
        this.updator.setHabilitationFactory(this.mockedHabilitationFactory);
        this.updator.setPharmacienService(this.mockedPharmacienService);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.updator = null;
        this.mockedBeanFactory = null;
        this.mockedHabilitationFactory = null;
        this.mockedPharmacienService = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.updator);
        Assert.assertEquals(this.mockedBeanFactory, this.updator.getBeanFactory());
        Assert.assertEquals(this.mockedHabilitationFactory, this.updator.getHabilitationFactory());
        Assert.assertEquals(this.mockedPharmacienService, this.updator.getPharmacienService());
    }

    /**
     * Test de la méthode update - nouvel essai.
     */
    @Test
    public void testUpdateNouvelEssai() {
        final String nameBeanCriteria = "pharmacienCriteria";
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        essai.setId(null);
        final Pharmacie pharmaciePrincipale = PharmacieUtils.makePharmacieTest(2);
        final Pharmacien expectedPharmacien = PersonneUtils.makePharmacien(3);
        final List<Pharmacien> pharmaciens = new ArrayList<Pharmacien>();
        pharmaciens.add(expectedPharmacien);
        essai.setPharmaciePrincipale(pharmaciePrincipale);

        final PharmacienSearchCriteria pharmacienCriteria = new PharmacienSearchCriteria();
        Mockito.when(this.mockedBeanFactory.getBean(nameBeanCriteria)).thenReturn(pharmacienCriteria);
        final Answer<List<Pharmacien>> answerGetAll = this.prepareGetAllAnswer(pharmaciePrincipale, pharmaciens);
        Mockito.when(this.mockedPharmacienService.getAll(pharmacienCriteria)).thenAnswer(answerGetAll);
        Mockito.when(this.mockedHabilitationFactory.getInitializedObject()).thenReturn(new Habilitation());

        Assert.assertTrue(essai.getDetailContacts().getHabilitations().isEmpty());
        this.updator.update(essai, null);

        Mockito.verify(this.mockedBeanFactory).getBean(nameBeanCriteria);
        Mockito.verify(this.mockedPharmacienService).getAll(pharmacienCriteria);
        Mockito.verify(this.mockedHabilitationFactory, Mockito.times(pharmaciens.size())).getInitializedObject();

        Assert.assertFalse(essai.getDetailContacts().getHabilitations().isEmpty());
        Assert.assertEquals(pharmaciens.size(), essai.getDetailContacts().getHabilitations().size());
        for (final Habilitation habilitation : essai.getDetailContacts().getHabilitations()) {
            Assert.assertTrue(pharmaciens.contains(habilitation.getPersonne()));
            Assert.assertEquals(essai.getDetailContacts(), habilitation.getDetailContacts());
            Assert.assertEquals(Droit.PHARMACIEN_TITULAIRE, habilitation.getDroit());
            Assert.assertFalse(habilitation.isDesactivable());
        }
    }

    /**
     * Test de la méthode update - essai existant.
     */
    @Test
    public void testUpdateEssaiExistant() {
        final String nameBeanCriteria = "pharmacienCriteria";
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        final Pharmacie pharmaciePrincipale = PharmacieUtils.makePharmacieTest(2);
        final Pharmacien expectedPharmacien = PersonneUtils.makePharmacien(3);
        final List<Pharmacien> pharmaciens = new ArrayList<Pharmacien>();
        pharmaciens.add(expectedPharmacien);
        essai.setPharmaciePrincipale(pharmaciePrincipale);

        final PharmacienSearchCriteria pharmacienCriteria = new PharmacienSearchCriteria();
        Mockito.when(this.mockedBeanFactory.getBean(nameBeanCriteria)).thenReturn(pharmacienCriteria);
        final Answer<List<Pharmacien>> answerGetAll = this.prepareGetAllAnswer(pharmaciePrincipale, pharmaciens);
        Mockito.when(this.mockedPharmacienService.getAll(pharmacienCriteria)).thenAnswer(answerGetAll);
        Mockito.when(this.mockedHabilitationFactory.getInitializedObject()).thenReturn(new Habilitation());

        Assert.assertTrue(essai.getDetailContacts().getHabilitations().isEmpty());
        this.updator.update(essai, null);

        Mockito.verify(this.mockedBeanFactory, Mockito.never()).getBean(Matchers.anyString());
        Mockito.verify(this.mockedPharmacienService, Mockito.never()).getAll(Matchers.any(SearchCriteria.class));
        Mockito.verify(this.mockedHabilitationFactory, Mockito.never()).getInitializedObject();

        Assert.assertTrue(essai.getDetailContacts().getHabilitations().isEmpty());
    }

    /**
     * Préparation de l'objet Answer pour le getAll du test.
     * @param pharmaciePrincipale Pharmacie principale de l'essai.
     * @param pharmaciens Liste des pharmaciens à retourner.
     * @return L'objet Answer.
     */
    private Answer<List<Pharmacien>> prepareGetAllAnswer(final Pharmacie pharmaciePrincipale,
                                                         final List<Pharmacien> pharmaciens) {
        final Answer<List<Pharmacien>> answerGetAll = new Answer<List<Pharmacien>>() {

            @Override
            public List<Pharmacien> answer(final InvocationOnMock invocation) throws Throwable {
                final PharmacienSearchCriteria argCriteria = (PharmacienSearchCriteria) invocation.getArguments()[0];
                Assert.assertFalse(StringUtils.hasText(argCriteria.getLogin()));
                Assert.assertFalse(StringUtils.hasText(argCriteria.getNom()));
                Assert.assertFalse(StringUtils.hasText(argCriteria.getPrenom()));
                Assert.assertFalse(StringUtils.hasText(argCriteria.getNomSociete()));
                Assert.assertEquals(TypePharmacien.TITULAIRE, argCriteria.getTypePharmacien());
                Assert.assertEquals(pharmaciePrincipale, argCriteria.getPharmacies().get(0));
                return pharmaciens;
            }
        };
        return answerGetAll;
    }
}

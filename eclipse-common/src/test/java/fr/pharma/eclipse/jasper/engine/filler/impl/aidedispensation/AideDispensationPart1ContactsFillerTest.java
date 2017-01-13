package fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.enums.GroupeContacts;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanContact;
import fr.pharma.eclipse.domain.jasper.model.fiche.aidedispensation.JRBeanFicheAideDispensationPart1;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.jasper.engine.factory.JRDataSourceFactory;
import fr.pharma.eclipse.jasper.engine.filler.impl.aidedispensation.helper.ContactsFillerHelper;
import fr.pharma.eclipse.service.habilitation.helper.HabilitationsHelper;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Test de la classe {@link AideDispensationPart1ContactsFiller}.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class AideDispensationPart1ContactsFillerTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private AideDispensationPart1ContactsFiller filler;

    /**
     * Helper mocké pour la gestion des habilitations.
     */
    private HabilitationsHelper mockedHabilitationsHelper;

    /**
     * Fabrique mockée de JRDataSource.
     */
    private JRDataSourceFactory mockedFactory;

    /**
     * Helper mocké.
     */
    private ContactsFillerHelper mockedHelper;

    /**
     * Groupe de contacts pour lequel travaille ce filler.
     */
    private static final GroupeContacts GROUPE_CONTACTS = GroupeContacts.PROMOTEURS;

    /**
     * Propriété du JasperReportBean valorisée par ce filler.
     */
    private final static String PROPERTY_TO_SET = "contactsPromoteurs";

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedFactory = Mockito.mock(JRDataSourceFactory.class);
        this.mockedHabilitationsHelper = Mockito.mock(HabilitationsHelper.class);
        this.mockedHelper = Mockito.mock(ContactsFillerHelper.class);
        this.filler =
            new AideDispensationPart1ContactsFiller(AideDispensationPart1ContactsFillerTest.PROPERTY_TO_SET, AideDispensationPart1ContactsFillerTest.GROUPE_CONTACTS.name());
        this.filler.setHabilitationsHelper(this.mockedHabilitationsHelper);
        this.filler.setHelper(this.mockedHelper);
        this.filler.setJrDataSourceFactory(this.mockedFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.filler = null;
        this.mockedFactory = null;
        this.mockedHabilitationsHelper = null;
        this.mockedHelper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.filler);
        Assert.assertEquals(AideDispensationPart1ContactsFillerTest.GROUPE_CONTACTS, this.filler.getGroupeContacts());
        Assert.assertEquals(AideDispensationPart1ContactsFillerTest.PROPERTY_TO_SET, this.filler.getJrPropertyToSet());
        Assert.assertEquals(this.mockedFactory, this.filler.getJrDataSourceFactory());
        Assert.assertEquals(this.mockedHabilitationsHelper, this.filler.getHabilitationsHelper());
        Assert.assertEquals(this.mockedHelper, this.filler.getHelper());
    }

    /**
     * Test de la méthode fill(fr.pharma.eclipse.domain.model.essai.Essai,
     * fr.pharma.eclipse.domain.jasper.model.common.JasperReportBean).
     */
    @Test
    public void testFill() {
        long id = 1;
        final JRBeanFicheAideDispensationPart1 bean = new JRBeanFicheAideDispensationPart1();
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final Habilitation habilitation = new Habilitation();
        habilitation.setId(id++);
        final SortedSet<Habilitation> habilitations = new TreeSet<Habilitation>();
        habilitations.add(habilitation);
        final Collection<JRBeanContact> beansContact = new ArrayList<JRBeanContact>();
        beansContact.add(new JRBeanContact());
        beansContact.add(new JRBeanContact());
        final JRDataSource expectedDataSource = Mockito.mock(JRDataSource.class);

        Mockito.when(this.mockedHabilitationsHelper.getHabilitations(essai, AideDispensationPart1ContactsFillerTest.GROUPE_CONTACTS.getDroits(), true)).thenReturn(habilitations);
        Mockito.when(this.mockedHelper.transform(habilitations)).thenReturn(beansContact);
        Mockito.when(this.mockedFactory.getInitializedObject(beansContact)).thenReturn(expectedDataSource);

        Assert.assertNull(bean.getContactsPromoteurs());
        this.filler.fill(essai, bean);

        Mockito.verify(this.mockedHabilitationsHelper).getHabilitations(essai, AideDispensationPart1ContactsFillerTest.GROUPE_CONTACTS.getDroits(), true);
        Mockito.verify(this.mockedHelper).transform(habilitations);
        Mockito.verify(this.mockedFactory).getInitializedObject(beansContact);
        Assert.assertEquals(expectedDataSource, bean.getContactsPromoteurs());
    }
}

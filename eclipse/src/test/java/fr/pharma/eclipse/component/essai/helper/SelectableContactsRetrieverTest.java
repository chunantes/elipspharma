package fr.pharma.eclipse.component.essai.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import fr.pharma.eclipse.component.essai.TypeContact;
import fr.pharma.eclipse.component.essai.seeker.SelectableContactsSeeker;
import fr.pharma.eclipse.domain.model.acteur.Cro;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.PersonneUtils;

/**
 * Test de la classe SelectableContactsRetriever.
 
 * @version $Revision$ $Date$
 */
public class SelectableContactsRetrieverTest
    extends AbstractEclipseJUnitTest
{
    /**
     * Classe testée.
     */
    private SelectableContactsRetriever retriever;

    /**
     * Mock de seeker de contact.
     */
    private SelectableContactsSeeker mockedSeeker;

    /**
     * Filtre mocké.
     */
    private SelectableContactsFilter mockedFilter;

    /**
     * Type de contact.
     */
    private static final TypeContact TYPE_CONTACT = TypeContact.CRO;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp()
    {
        this.mockedSeeker = Mockito.mock(SelectableContactsSeeker.class);
        this.mockedFilter = Mockito.mock(SelectableContactsFilter.class);
        this.retriever = new SelectableContactsRetriever();
        this.retriever.setFilter(this.mockedFilter);
        this.retriever.setContactsSeekers(new ArrayList<SelectableContactsSeeker>());
        this.retriever.getContactsSeekers().add(this.mockedSeeker);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown()
    {
        this.retriever = null;
        this.mockedSeeker = null;
        this.mockedFilter = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit()
    {
        Assert.assertNotNull(this.retriever);
        Assert.assertEquals(this.mockedFilter,
                            this.retriever.getFilter());
        Assert.assertEquals(this.mockedSeeker,
                            this.retriever.getContactsSeekers().get(0));
    }

    /**
     * Test de la méthode retrieveSelectableContacts - mauvais type.
     */
    @Test
    public void testRetrieveSelectableContactsKo()
    {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        final TypeContact typeContact = SelectableContactsRetrieverTest.TYPE_CONTACT;
        Mockito.when(this.mockedSeeker.supports(typeContact)).thenReturn(false);

        final Collection<? extends Personne> result =
            this.retriever.retrieveSelectableContacts(essai,
                                                      typeContact);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isEmpty());
    }

    /**
     * Test de la méthode retrieveSelectableContacts.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testRetrieveSelectableContacts()
    {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final TypeContact typeContact = SelectableContactsRetrieverTest.TYPE_CONTACT;
        Mockito.when(this.mockedSeeker.supports(typeContact)).thenReturn(true);

        final Cro personne1 = PersonneUtils.makeCro(id++);
        final Cro personne2 = PersonneUtils.makeCro(id++);
        final Cro personne3 = PersonneUtils.makeCro(id++);
        final List<Personne> expectedContacts = new ArrayList<Personne>();
        expectedContacts.add(personne1);
        expectedContacts.add(personne2);
        expectedContacts.add(personne3);

        Mockito.when(this.mockedSeeker.getContacts(essai)).thenReturn(expectedContacts);
        Mockito
                .doAnswer(this.prepareFilterAnswer(essai,
                                                   typeContact,
                                                   expectedContacts))
                .when(this.mockedFilter)
                .filter(Matchers.any(Essai.class),
                        Matchers.any(TypeContact.class),
                        Matchers.anyList());

        final Collection<? extends Personne> result =
            this.retriever.retrieveSelectableContacts(essai,
                                                      typeContact);
        Mockito.verify(this.mockedSeeker).getContacts(essai);
        Mockito.verify(this.mockedFilter).filter(Matchers.any(Essai.class),
                                                 Matchers.any(TypeContact.class),
                                                 Matchers.anyList());
        Assert.assertNotNull(result);
        Assert.assertEquals(expectedContacts.size(),
                            result.size());
        for (final Personne personne : expectedContacts)
        {
            Assert.assertTrue(result.contains(personne));
        }
    }

    /**
     * Création de l'objet Answer pour le mock du filtre.
     * @param essai Essai attendu.
     * @param typeContact Type de contact attendu.
     * @param expectedContacts Contacts attendus dans la liste des sélectionnables.
     * @return L'objet Answer.
     */
    private Answer<Object> prepareFilterAnswer(final Essai essai,
                                               final TypeContact typeContact,
                                               final List<Personne> expectedContacts)
    {
        return new Answer<Object>() {

            @SuppressWarnings("unchecked")
            @Override
            public Object answer(final InvocationOnMock invocation)
                throws Throwable
            {
                int i = 0;
                final Essai argEssai = (Essai) invocation.getArguments()[i++];
                final TypeContact argTypeContact = (TypeContact) invocation.getArguments()[i++];
                final Collection<Personne> argSelectable =
                    (Collection<Personne>) invocation.getArguments()[i++];
                Assert.assertEquals(essai,
                                    argEssai);
                Assert.assertEquals(typeContact,
                                    argTypeContact);
                Assert.assertEquals(expectedContacts.size(),
                                    argSelectable.size());
                for (final Personne personne : expectedContacts)
                {
                    Assert.assertTrue(argSelectable.contains(personne));
                }
                return null;
            }
        };
    }

}

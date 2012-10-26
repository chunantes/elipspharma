package fr.pharma.eclipse.component.essai;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.faces.model.DataModel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.primefaces.component.tabview.Tab;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;

import fr.pharma.eclipse.component.SelectableBeansManager;
import fr.pharma.eclipse.component.essai.helper.DicoSuivisEssai;
import fr.pharma.eclipse.component.helper.BeanManagerHelper;
import fr.pharma.eclipse.domain.enums.EtatEssai;
import fr.pharma.eclipse.domain.enums.GroupeContacts;
import fr.pharma.eclipse.domain.enums.TypeCommentaireEssai;
import fr.pharma.eclipse.domain.enums.TypeHistoriqueEssai;
import fr.pharma.eclipse.domain.enums.TypePromoteur;
import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.acteur.Promoteur;
import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.essai.CommentaireEssai;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.DetailAdministratif;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.document.DocumentAutoriteCompetente;
import fr.pharma.eclipse.domain.model.essai.detail.recherche.CommentaireEssaiRecherche;
import fr.pharma.eclipse.domain.model.essai.detail.recherche.DetailRecherche;
import fr.pharma.eclipse.domain.model.habilitation.Habilitation;
import fr.pharma.eclipse.domain.model.suivi.common.Suivi;
import fr.pharma.eclipse.service.common.GenericService;
import fr.pharma.eclipse.service.essai.EssaiService;
import fr.pharma.eclipse.utils.DocumentsUtils;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.PromoteurUtils;
import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Test de la classe EssaiManager.
 
 * @version $Revision$ $Date$
 */
public class EssaiManagerTest
{
    /**
     * Type de commentaires managé pour les tests.
     */
    private static final TypeCommentaireEssai TYPE_COMMENTAIRE_MANAGE =
        TypeCommentaireEssai.RECHERCHE;

    /**
     * Type de commentaires non managé pour les tests.
     */
    private static final TypeCommentaireEssai TYPE_COMMENTAIRE_NON_MANAGE =
        TypeCommentaireEssai.FAISABILITE_ACHAT_PROD;

    /**
     * Type de documents managé pour les tests.
     */
    private static final TypeDocumentEssai TYPE_DOCUMENT_MANAGE =
        TypeDocumentEssai.AUTORITE_COMPETENTE;

    /**
     * Type de documents non managé pour les tests.
     */
    private static final TypeDocumentEssai TYPE_DOCUMENT_NON_MANAGE =
        TypeDocumentEssai.COMITE_PROTEC_PERS;

    /**
     * Manager testé.
     */
    private EssaiManager manager;

    /**
     * Mock du service.
     */
    private GenericService<Essai> mockedService;

    /**
     * Helper mocké.
     */
    private BeanManagerHelper<Essai> mockedHelper;

    /**
     * DataModel mocké.
     */
    private DataModel<BeanObject> mockedDataModel;

    /**
     * Manager de contacts mocké.
     */
    private ContactsManager mockedContactsManager;

    /**
     * Manager des commentaires mocké.
     */
    private GenericCommentaireManager<CommentaireEssaiRecherche, DetailRecherche> mockedCommentaireManager;

    /**
     * Manager des documents mocké.
     */
    private GenericDocumentEssaiManager<DocumentAutoriteCompetente, DetailAdministratif> mockedDocumentManager;

    /**
     * Dictionnaire des dernières modifications mocké.
     */
    private DicoSuivisEssai mockedDico;

    /**
     * Méthode d'initialisation.
     */
    @SuppressWarnings("unchecked")
    @Before
    public void setUp()
    {
        this.mockedService = Mockito.mock(EssaiService.class);
        this.mockedHelper = Mockito.mock(BeanManagerHelper.class);
        this.mockedDataModel = Mockito.mock(DataModel.class);
        this.mockedContactsManager = Mockito.mock(ContactsManager.class);
        this.mockedCommentaireManager = Mockito.mock(GenericCommentaireManager.class);
        this.mockedDocumentManager = Mockito.mock(GenericDocumentEssaiManager.class);
        this.mockedDico = Mockito.mock(DicoSuivisEssai.class);
        final SortedMap<String, GenericCommentaireManager> commentManagers =
            new TreeMap<String, GenericCommentaireManager>();
        commentManagers.put(EssaiManagerTest.TYPE_COMMENTAIRE_MANAGE.name(),
                            this.mockedCommentaireManager);
        final SortedMap<String, GenericDocumentEssaiManager> documentsManagers =
            new TreeMap<String, GenericDocumentEssaiManager>();
        documentsManagers.put(EssaiManagerTest.TYPE_DOCUMENT_MANAGE.name(),
                              this.mockedDocumentManager);
        this.manager = new EssaiManager(this.mockedService);
        this.manager.setHelper(this.mockedHelper);
        this.manager.setCommentairesManagers(commentManagers);
        this.manager.setDocumentsCollectionManagers(documentsManagers);
        this.manager.setDicoDernieresModifs(this.mockedDico);
        this.manager.setContactsManager(this.mockedContactsManager);
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown()
    {
        this.manager = null;
        this.mockedService = null;
        this.mockedHelper = null;
        this.mockedDataModel = null;
        this.mockedDocumentManager = null;
        this.mockedCommentaireManager = null;
        this.mockedDico = null;
        this.mockedContactsManager = null;
    }

    /**
     * Initialisation de la méthode mockedHelper.
     */
    @SuppressWarnings("unchecked")
    private void initMockHelper()
    {
        Mockito
                .when(this.mockedHelper.returnAsDataModel(Matchers.anyCollection()))
                .thenAnswer(new Answer<DataModel>() {

                    @Override
                    public DataModel answer(final InvocationOnMock invocation)
                        throws Throwable
                    {
                        @SuppressWarnings("unused")
                        final Collection coll = (Collection) invocation.getArguments()[0];
                        return EssaiManagerTest.this.mockedDataModel;
                    }
                });
    }

    /**
     * Test de la méthode updateTypePromoteur - promoteur de l'essai avec un type par défaut.
     */
    @Test
    public void testUpdateTypePromoteurTypePromoteurOk()
    {
        Long ids = 1L;
        final TypePromoteur typePromoteurSelected = TypePromoteur.INDUSTRIEL;
        final TypePromoteur typePromoteurEssai = TypePromoteur.AUTRE;
        final Essai essaiOriginal = EssaiUtils.makeEssaiTest(ids++,
                                                             typePromoteurEssai);
        this.manager.setBean(essaiOriginal);

        final Promoteur promoteurIHM = PromoteurUtils.makePromoteurTest(ids++,
                                                                        typePromoteurSelected);
        this.manager.updateTypePromoteur(promoteurIHM);
        Assert.assertEquals(typePromoteurSelected,
                            this.manager.getBean().getTypePromoteur());
    }

    /**
     * Test de la méthode updateTypePromoteur - promoteur de l'essai sans type par défaut.
     */
    @Test
    public void testUpdateTypePromoteurTypePromoteurKo()
    {
        Long ids = 1L;
        final TypePromoteur typePromoteurEssai = TypePromoteur.AUTRE;
        final Essai essaiOriginal = EssaiUtils.makeEssaiTest(ids++,
                                                             typePromoteurEssai);
        this.manager.setBean(essaiOriginal);

        final Promoteur promoteurIHM = PromoteurUtils.makePromoteurTest(ids++,
                                                                        null);
        this.manager.updateTypePromoteur(promoteurIHM);
        Assert.assertEquals(typePromoteurEssai,
                            this.manager.getBean().getTypePromoteur());
    }

    /**
     * Test de la méthode handleSelectPromoteur.
     */
    @Test
    public void testHandleSelectPromoteur()
    {
        Long ids = 1L;
        final TypePromoteur typePromoteurSelected = TypePromoteur.INDUSTRIEL;
        final TypePromoteur typePromoteurEssai = TypePromoteur.AUTRE;
        final Essai essaiOriginal = EssaiUtils.makeEssaiTest(ids++,
                                                             typePromoteurEssai);
        this.manager.setBean(essaiOriginal);

        final Promoteur promoteurIHM = PromoteurUtils.makePromoteurTest(ids++,
                                                                        typePromoteurSelected);

        final SelectEvent event = Mockito.mock(SelectEvent.class);
        Mockito.when(event.getObject()).thenReturn(promoteurIHM);

        this.manager.handleSelectPromoteur(event);

        Mockito.verify(event).getObject();
        Assert.assertEquals(typePromoteurSelected,
                            this.manager.getBean().getTypePromoteur());
    }

    /**
     * Test de la méthode getCommentaireManager.
     */
    @Test
    public void testGetCommentaireManager()
    {
        Assert
                .assertEquals(this.mockedCommentaireManager,
                              this.manager
                                      .getCommentaireManager(EssaiManagerTest.TYPE_COMMENTAIRE_MANAGE
                                              .name()));
    }

    /**
     * Test de la méthode initLastCommentaires - liste de String.
     */
    @Test
    public void testInitLastCommentairesStrings()
    {
        long ids = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(ids++);
        this.manager.setBean(essai);

        Mockito
                .when(this.mockedCommentaireManager.getTypeCommentaire())
                .thenReturn(EssaiManagerTest.TYPE_COMMENTAIRE_MANAGE);

        this.manager.initLastCommentaires(EssaiManagerTest.TYPE_COMMENTAIRE_MANAGE.name(),
                                          EssaiManagerTest.TYPE_COMMENTAIRE_NON_MANAGE.name());

        Mockito.verify(this.mockedCommentaireManager).getTypeCommentaire();
        Mockito.verify(this.mockedCommentaireManager).initLastCommentaire(essai);
        Assert.assertEquals(essai,
                            this.manager.getBean());
    }

    /**
     * Test de la méthode initLastCommentaires - liste de Strings vide.
     */
    @Test
    public void testInitLastCommentairesStringsEmpty()
    {
        long ids = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(ids++);
        this.manager.setBean(essai);

        this.manager.initLastCommentaires(new String[]
        {});

        Assert.assertEquals(essai,
                            this.manager.getBean());
        Mockito.verify(this.mockedCommentaireManager).initLastCommentaire(essai);
    }

    /**
     * Test de la méthode getLastCommentaire.
     */
    @Test
    public void testGetLastCommentaireOk()
    {
        final CommentaireEssaiRecherche expectedCommentaire =
            Mockito.mock(CommentaireEssaiRecherche.class);
        Mockito
                .when(this.mockedCommentaireManager.getLastCommentaire())
                .thenReturn(expectedCommentaire);
        final CommentaireEssai commentaireResult =
            this.manager.getLastCommentaire(EssaiManagerTest.TYPE_COMMENTAIRE_MANAGE);
        Mockito.verify(this.mockedCommentaireManager).getLastCommentaire();
        Assert.assertEquals(expectedCommentaire,
                            commentaireResult);
    }

    /**
     * Test de la méthode getLastCommentaire : pas de manager pour le type donné.
     */
    @Test
    public void testGetLastCommentaireKo()
    {
        Assert.assertNull(this.manager
                .getLastCommentaire(EssaiManagerTest.TYPE_COMMENTAIRE_NON_MANAGE));
    }

    /**
     * Test de la méthode ajouterCommentaire.
     * @throws ParseException Erreur de création des jeux de données.
     */
    @Test
    public void testAjouterCommentaireOk()
        throws ParseException
    {
        long ids = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(ids++);
        final CommentaireEssaiRecherche expectedCommentaire =
            EssaiUtils.makeCommentaireTest(ids++,
                                           Utils.parseDate("01/11/2010",
                                                           EclipseConstants.PATTERN_SIMPLE));
        this.manager.setBean(essai);

        Mockito.when(this.mockedCommentaireManager.canCreateCommentaire()).thenReturn(true);
        Mockito
                .when(this.mockedCommentaireManager.createCommentaire(essai))
                .thenReturn(expectedCommentaire);
        final Answer<Object> answer = new Answer<Object>() {

            @Override
            public Object answer(final InvocationOnMock invocation)
                throws Throwable
            {
                essai.getDetailRecherche().getCommentaires().add(expectedCommentaire);
                return null;
            }
        };
        Mockito
                .doAnswer(answer)
                .when(this.mockedHelper)
                .addToCollection(Matchers.any(Essai.class),
                                 Matchers.anyString(),
                                 Matchers.any(CommentaireEssaiRecherche.class));

        this.manager.ajouterCommentaire(EssaiManagerTest.TYPE_COMMENTAIRE_MANAGE);

        Mockito.verify(this.mockedCommentaireManager).canCreateCommentaire();
        Mockito.verify(this.mockedCommentaireManager,
                       Mockito.never()).resetLibelle();
        Mockito.verify(this.mockedCommentaireManager).createCommentaire(essai);
        Mockito
                .verify(this.mockedHelper)
                .addToCollection(essai,
                                 EssaiManagerTest.TYPE_COMMENTAIRE_MANAGE
                                         .getCommentairesPropertyFromEssai(),
                                 expectedCommentaire);
        Mockito.verify(this.mockedCommentaireManager).setLastCommentaire(expectedCommentaire);
        Assert.assertEquals(essai,
                            this.manager.getBean());
    }

    /**
     * Test de la méthode ajouterCommentaire ko : type non managé.
     * @throws ParseException Erreur de création des jeux de données.
     */
    @Test
    public void testAjouterCommentaireKoMauvaisType()
        throws ParseException
    {
        long ids = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(ids++);
        this.manager.setBean(essai);

        this.manager.ajouterCommentaire(EssaiManagerTest.TYPE_COMMENTAIRE_NON_MANAGE);

        Mockito.verify(this.mockedCommentaireManager,
                       Mockito.never()).canCreateCommentaire();
        Mockito.verify(this.mockedCommentaireManager,
                       Mockito.never()).resetLibelle();
        Mockito.verify(this.mockedCommentaireManager,
                       Mockito.never()).createCommentaire(Matchers.any(Essai.class));
        Mockito
                .verify(this.mockedHelper,
                        Mockito.never())
                .addToCollection(Matchers.any(Essai.class),
                                 Matchers.anyString(),
                                 Matchers.any(CommentaireEssaiRecherche.class));
        Mockito.verify(this.mockedCommentaireManager,
                       Mockito.never()).setLastCommentaire(Matchers
                .any(CommentaireEssaiRecherche.class));
        Assert.assertEquals(essai,
                            this.manager.getBean());
    }

    /**
     * Test de la méthode ajouterCommentaire - cas de libellé de commentaire non valide.
     * @throws ParseException Erreur de création des jeux de données.
     */
    @Test
    public void testAjouterCommentaireKoLibNonValide()
        throws ParseException
    {
        long ids = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(ids++);

        this.manager.setBean(essai);

        Mockito.when(this.mockedCommentaireManager.canCreateCommentaire()).thenReturn(false);

        this.manager.ajouterCommentaire(EssaiManagerTest.TYPE_COMMENTAIRE_MANAGE);

        Mockito.verify(this.mockedCommentaireManager).canCreateCommentaire();
        Mockito.verify(this.mockedCommentaireManager).resetLibelle();
        Mockito.verify(this.mockedCommentaireManager,
                       Mockito.never()).createCommentaire(Matchers.any(Essai.class));
        Mockito
                .verify(this.mockedHelper,
                        Mockito.never())
                .addToCollection(Matchers.any(Essai.class),
                                 Matchers.anyString(),
                                 Matchers.any(CommentaireEssaiRecherche.class));
        Mockito.verify(this.mockedCommentaireManager,
                       Mockito.never()).setLastCommentaire(Matchers
                .any(CommentaireEssaiRecherche.class));
    }

    /**
     * Test de la méthode initLastModifs.
     */
    @Test
    public void testInitLastModifs()
    {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        this.manager.setBean(essai);
        this.manager.initLastModifs();
        Mockito.verify(this.mockedDico).init(essai);
    }

    /**
     * Test de la méthode hasLastModif.
     */
    @Test
    public void testHasLastModif()
    {
        final TypeHistoriqueEssai typeSuivi = TypeHistoriqueEssai.ONG_RECHERCHE;
        final boolean expectedRes = true;
        Mockito.when(this.mockedDico.hasDerniereModif(typeSuivi)).thenReturn(expectedRes);
        Assert.assertEquals(expectedRes,
                            this.manager.hasLastModif(typeSuivi.name()));
        Mockito.verify(this.mockedDico).hasDerniereModif(typeSuivi);
    }

    /**
     * Test de la méthode getLastModif.
     */
    @Test
    public void testGetLastModif()
    {
        final TypeHistoriqueEssai typeSuivi = TypeHistoriqueEssai.ONG_RECHERCHE;
        final Suivi expectedSuivi = Mockito.mock(Suivi.class);
        Mockito.when(this.mockedDico.getDerniereModif(typeSuivi)).thenReturn(expectedSuivi);
        final Suivi actualSuivi = this.manager.getLastModif(typeSuivi.name());
        Mockito.verify(this.mockedDico).getDerniereModif(typeSuivi);
        Assert.assertEquals(expectedSuivi,
                            actualSuivi);
    }

    /**
     * Test de la méthode onOngletChange.
     */
    @Test
    public void testOnOngletChange()
    {
        final int initialOngletCourant = TypeHistoriqueEssai.ONG_DATES.getIndexIHM();
        final TypeHistoriqueEssai expectedSelectedTab = TypeHistoriqueEssai.ONG_FAISABILITE;
        final int expectedOngletCourant = expectedSelectedTab.getIndexIHM();
        this.manager.setIndexOngletCourant(initialOngletCourant);

        final TabChangeEvent mockedEvent = Mockito.mock(TabChangeEvent.class);
        final Tab mockedTab = Mockito.mock(Tab.class);
        Mockito.when(mockedEvent.getTab()).thenReturn(mockedTab);
        Mockito.when(mockedTab.getId()).thenReturn(expectedSelectedTab.name());
        this.manager.onOngletChange(mockedEvent);
        Mockito.verify(mockedEvent).getTab();
        Mockito.verify(mockedTab).getId();
        Assert.assertEquals(expectedOngletCourant,
                            this.manager.getIndexOngletCourant());
    }

    /**
     * Test de la méthode selectOngletCourant ok.
     */
    @Test
    public void testSelectOngletCourantOk()
    {
        final int initialOngletCourant = TypeHistoriqueEssai.ONG_DATES.getIndexIHM();
        final TypeHistoriqueEssai expectedSelectedTab = TypeHistoriqueEssai.ONG_FAISABILITE;
        final int expectedOngletCourant = expectedSelectedTab.getIndexIHM();
        this.manager.setIndexOngletCourant(initialOngletCourant);

        this.manager.selectOngletCourant(expectedSelectedTab.name());
        Assert.assertEquals(expectedOngletCourant,
                            this.manager.getIndexOngletCourant());
    }

    /**
     * Test de la méthode selectOngletCourant ko.
     */
    @Test
    public void testSelectOngletCourantKo()
    {
        final int initialOngletCourant = TypeHistoriqueEssai.ONG_DATES.getIndexIHM();
        this.manager.setIndexOngletCourant(initialOngletCourant);

        this.manager.selectOngletCourant("Type inexistant");
        Assert.assertEquals(initialOngletCourant,
                            this.manager.getIndexOngletCourant());
    }

    /**
     * Test de la méthode getDocumentManager.
     */
    @Test
    public void testGetDocumentManager()
    {
        Assert.assertNull(this.manager
                .getDocumentManager(EssaiManagerTest.TYPE_DOCUMENT_NON_MANAGE,
                                    true));
        Assert.assertEquals(this.mockedDocumentManager,
                            this.manager
                                    .getDocumentManager(EssaiManagerTest.TYPE_DOCUMENT_MANAGE,
                                                        true));
    }

    /**
     * Test de la méthode initLastDocuments.
     */
    @Test
    public void testInitLastDocuments()
    {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        this.manager.setBean(essai);
        this.manager.initLastDocuments();
        Mockito.verify(this.mockedDocumentManager).initLastDocument(essai);
    }

    /**
     * Test de la méthode getLastDocument.
     */
    @Test
    public void testGetLastDocument()
    {
        Assert
                .assertNull(this.manager
                        .getLastDocument(EssaiManagerTest.TYPE_DOCUMENT_NON_MANAGE));

        final DocumentAutoriteCompetente expectedDoc =
            Mockito.mock(DocumentAutoriteCompetente.class);
        Mockito.when(this.mockedDocumentManager.getLastDocument()).thenReturn(expectedDoc);
        Assert.assertEquals(expectedDoc,
                            this.manager.getLastDocument(EssaiManagerTest.TYPE_DOCUMENT_MANAGE));
        Mockito.verify(this.mockedDocumentManager).getLastDocument();
    }

    /**
     * Test de la méthode ajouterDocument.
     * @throws ParseException Erreur de création des jeux de données.
     */
    @Test
    public void testAjouterDocumentOk()
        throws ParseException
    {
        long ids = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(ids++);
        final DocumentAutoriteCompetente expectedDoc = DocumentsUtils.makeDocumentACTest(ids++);
        this.manager.setBean(essai);

        Mockito.when(this.mockedDocumentManager.canCreateDocument()).thenReturn(true);
        Mockito.when(this.mockedDocumentManager.createDocument(essai)).thenReturn(expectedDoc);
        final Answer<Object> answer = new Answer<Object>() {

            @Override
            public Object answer(final InvocationOnMock invocation)
                throws Throwable
            {
                essai.getDetailAdministratif().getInfosAC().getDocuments().add(expectedDoc);
                return null;
            }
        };
        Mockito
                .doAnswer(answer)
                .when(this.mockedHelper)
                .addToCollection(Matchers.any(Essai.class),
                                 Matchers.anyString(),
                                 Matchers.any(DocumentAutoriteCompetente.class));

        this.manager.ajouterDocument(EssaiManagerTest.TYPE_DOCUMENT_MANAGE,
                                     true);

        Mockito.verify(this.mockedDocumentManager).canCreateDocument();
        Mockito.verify(this.mockedDocumentManager,
                       Mockito.never()).resetFormDatas();
        Mockito.verify(this.mockedDocumentManager).createDocument(essai);
        Mockito
                .verify(this.mockedHelper)
                .addToCollection(essai,
                                 EssaiManagerTest.TYPE_DOCUMENT_MANAGE
                                         .getDocumentsPropertyFromEssai(),
                                 expectedDoc);
        Mockito.verify(this.mockedDocumentManager).setLastDocument(expectedDoc);
        Assert.assertEquals(essai,
                            this.manager.getBean());
    }
    /**
     * Test de la méthode ajouterDocument ko : type non managé.
     * @throws ParseException Erreur de création des jeux de données.
     */
    @Test
    public void testAjouterDocumentKoMauvaisType()
        throws ParseException
    {
        long ids = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(ids++);
        this.manager.setBean(essai);

        this.manager.ajouterDocument(EssaiManagerTest.TYPE_DOCUMENT_NON_MANAGE,
                                     true);

        Mockito.verify(this.mockedDocumentManager,
                       Mockito.never()).canCreateDocument();
        Mockito.verify(this.mockedDocumentManager,
                       Mockito.never()).resetFormDatas();
        Mockito.verify(this.mockedDocumentManager,
                       Mockito.never()).createDocument(Matchers.any(Essai.class));
        Mockito
                .verify(this.mockedHelper,
                        Mockito.never())
                .addToCollection(Matchers.any(Essai.class),
                                 Matchers.anyString(),
                                 Matchers.any(CommentaireEssaiRecherche.class));
        Mockito.verify(this.mockedDocumentManager,
                       Mockito.never()).setLastDocument(Matchers
                .any(DocumentAutoriteCompetente.class));
        Assert.assertEquals(essai,
                            this.manager.getBean());
    }

    /**
     * Test de la méthode ajouterDocument - cas de fichier non valide.
     * @throws ParseException Erreur de création des jeux de données.
     */
    @Test
    public void testAjouterDocumentKoLibNonValide()
        throws ParseException
    {
        long ids = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(ids++);

        this.manager.setBean(essai);

        Mockito.when(this.mockedDocumentManager.canCreateDocument()).thenReturn(false);

        this.manager.ajouterDocument(EssaiManagerTest.TYPE_DOCUMENT_MANAGE,
                                     true);

        Mockito.verify(this.mockedDocumentManager).canCreateDocument();
        Mockito.verify(this.mockedDocumentManager).resetFormDatas();
        Mockito.verify(this.mockedDocumentManager,
                       Mockito.never()).createDocument(Matchers.any(Essai.class));
        Mockito
                .verify(this.mockedHelper,
                        Mockito.never())
                .addToCollection(Matchers.any(Essai.class),
                                 Matchers.anyString(),
                                 Matchers.any(CommentaireEssaiRecherche.class));
        Mockito.verify(this.mockedDocumentManager,
                       Mockito.never()).setLastDocument(Matchers
                .any(DocumentAutoriteCompetente.class));
    }

    /**
     * Test de la méthode disableContacts.
     */
    @Test
    public void testDisableContacts()
    {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        this.manager.setBean(essai);
        this.manager.disableContacts();
        Mockito.verify(this.mockedContactsManager).disableContacts(essai);
    }

    /**
     * Test de la méthode getTypeContactToAdd.
     */
    @Test
    public void testGetTypeContactToAdd()
    {
        final TypeContact expectedRes = TypeContact.CRO;
        Mockito.when(this.mockedContactsManager.getTypeContactToAdd()).thenReturn(expectedRes);
        Assert.assertEquals(expectedRes,
                            this.manager.getTypeContactToAdd());
        Mockito.verify(this.mockedContactsManager).getTypeContactToAdd();
    }

    /**
     * Test de la méthode setTypeContactToAdd.
     */
    @Test
    public void testSetTypeContactToAdd()
    {
        final TypeContact expectedRes = TypeContact.CRO;
        this.manager.setTypeContactToAdd(expectedRes);
        Mockito.verify(this.mockedContactsManager).setTypeContactToAdd(expectedRes);
    }

    /**
     * Test de la méthode calculatePersons.
     */
    @Test
    public void testCalculatePersons()
    {
        final Essai essai = EssaiUtils.makeEssaiTest(2);
        this.manager.setBean(essai);
        this.manager.calculatePersons(TypeContact.ARC_INVESTIGATEUR);
        Mockito
                .verify(this.mockedContactsManager)
                .setTypeContactToAdd(TypeContact.ARC_INVESTIGATEUR);
        Mockito.verify(this.mockedContactsManager).initSelectableContacts(essai);
    }

    /**
     * Test de la méthode getContactsBeansManager.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetContactsBeansManager()
    {
        final SelectableBeansManager<Personne> expectedManager =
            Mockito.mock(SelectableBeansManager.class);
        Mockito
                .when(this.mockedContactsManager.getSelectableContactsManager())
                .thenReturn(expectedManager);
        Assert.assertEquals(expectedManager,
                            this.manager.getContactsBeansManager());
    }

    /**
     * Test de la méthode validerAjoutContacts.
     */
    @Test
    public void testValiderAjoutContacts()
    {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        this.manager.setBean(essai);
        this.manager.validerAjoutContacts();
        Mockito.verify(this.mockedContactsManager).ajouterContacts(essai);
    }

    /**
     * Test de la méthode getGroupePromoteurs.
     */
    @Test
    public void testGetGroupePromoteurs()
    {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        this.manager.setBean(essai);
        final GroupeContacts expectedGroupe = GroupeContacts.PROMOTEURS;
        final Set<Habilitation> expectedList =
            new HashSet<Habilitation>(Arrays.asList(EssaiUtils.makeHabilitationTest(id++),
                                                    EssaiUtils.makeHabilitationTest(id++)));
        this.initMockHelper();
        Mockito.when(this.mockedService.reattach(essai)).thenReturn(essai);
        Mockito
                .when(this.mockedContactsManager.getGroupeHabilitations(essai,
                                                                        expectedGroupe))
                .thenReturn(expectedList);
        final DataModel<Habilitation> res = this.manager.getGroupePromoteurs();
        Mockito.verify(this.mockedService).reattach(essai);
        Mockito.verify(this.mockedContactsManager).getGroupeHabilitations(essai,
                                                                          expectedGroupe);
        Mockito.verify(this.mockedHelper).returnAsDataModel(expectedList);
        Assert.assertNotNull(res);
        Assert.assertEquals(this.mockedDataModel,
                            res);
    }

    /**
     * Test de la méthode getGroupeInvestigateurs.
     */
    @Test
    public void testGetGroupeInvestigateurs()
    {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        this.manager.setBean(essai);
        final GroupeContacts expectedGroupe = GroupeContacts.INVESTIGATEURS;
        final Set<Habilitation> expectedList =
            new HashSet<Habilitation>(Arrays.asList(EssaiUtils.makeHabilitationTest(id++),
                                                    EssaiUtils.makeHabilitationTest(id++)));
        this.initMockHelper();
        Mockito.when(this.mockedService.reattach(essai)).thenReturn(essai);
        Mockito
                .when(this.mockedContactsManager.getGroupeHabilitations(essai,
                                                                        expectedGroupe))
                .thenReturn(expectedList);
        final DataModel<Habilitation> res = this.manager.getGroupeInvestigateurs();
        Mockito.verify(this.mockedContactsManager).getGroupeHabilitations(essai,
                                                                          expectedGroupe);
        Mockito.verify(this.mockedHelper).returnAsDataModel(expectedList);
        Assert.assertNotNull(res);
        Assert.assertEquals(this.mockedDataModel,
                            res);
    }

    /**
     * Test de la méthode getGroupeDRC.
     */
    @Test
    public void testGetGroupeDRC()
    {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        this.manager.setBean(essai);
        final GroupeContacts expectedGroupe = GroupeContacts.DRC;
        final Set<Habilitation> expectedList =
            new HashSet<Habilitation>(Arrays.asList(EssaiUtils.makeHabilitationTest(id++),
                                                    EssaiUtils.makeHabilitationTest(id++)));
        this.initMockHelper();
        Mockito.when(this.mockedService.reattach(essai)).thenReturn(essai);
        Mockito
                .when(this.mockedContactsManager.getGroupeHabilitations(essai,
                                                                        expectedGroupe))
                .thenReturn(expectedList);
        final DataModel<Habilitation> res = this.manager.getGroupeDRC();
        Mockito.verify(this.mockedContactsManager).getGroupeHabilitations(essai,
                                                                          expectedGroupe);
        Mockito.verify(this.mockedHelper).returnAsDataModel(expectedList);
        Assert.assertNotNull(res);
        Assert.assertEquals(this.mockedDataModel,
                            res);
    }

    /**
     * Test de la méthode getGroupePharmaciens.
     */
    @Test
    public void testGetGroupePharmaciens()
    {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        this.manager.setBean(essai);
        final GroupeContacts expectedGroupe = GroupeContacts.PHARMACIENS;
        final Set<Habilitation> expectedList =
            new HashSet<Habilitation>(Arrays.asList(EssaiUtils.makeHabilitationTest(id++),
                                                    EssaiUtils.makeHabilitationTest(id++)));
        this.initMockHelper();
        Mockito.when(this.mockedService.reattach(essai)).thenReturn(essai);
        Mockito
                .when(this.mockedContactsManager.getGroupeHabilitations(essai,
                                                                        expectedGroupe))
                .thenReturn(expectedList);
        final DataModel<Habilitation> res = this.manager.getGroupePharmaciens();
        Mockito.verify(this.mockedContactsManager).getGroupeHabilitations(essai,
                                                                          expectedGroupe);
        Mockito.verify(this.mockedHelper).returnAsDataModel(expectedList);
        Assert.assertNotNull(res);
        Assert.assertEquals(this.mockedDataModel,
                            res);
    }

    /**
     * Test de la méthode hasInvestigateurPrincipal.
     */
    @Test
    public void testHasInvestigateurPrincipal()
    {
        long id = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(id++);
        final boolean expectedRes = true;
        this.manager.setBean(essai);
        Mockito
                .when(this.mockedContactsManager.hasInvestigateurPrincipal(essai))
                .thenReturn(expectedRes);

        final boolean res = this.manager.hasInvestigateurPrincipal();

        Mockito.verify(this.mockedContactsManager).hasInvestigateurPrincipal(essai);
        Assert.assertEquals(expectedRes,
                            res);
    }

    /**
     * Test de la méthode getSelectedHabilitation.
     */
    @Test
    public void testGetSelectedHabilitation()
    {
        final Habilitation expected = Mockito.mock(Habilitation.class);
        Mockito.when(this.mockedContactsManager.getSelectedHabilitation()).thenReturn(expected);
        Assert.assertEquals(expected,
                            this.manager.getSelectedHabilitation());
        Mockito.verify(this.mockedContactsManager).getSelectedHabilitation();
    }

    /**
     * Test de la méthode setSelectedPersonne.
     */
    @Test
    public void testSetSelectedHabilitation()
    {
        final Habilitation expected = Mockito.mock(Habilitation.class);
        this.manager.setSelectedHabilitation(expected);
        Mockito.verify(this.mockedContactsManager).setSelectedHabilitation(expected);
    }

    /**
     * Test de la méthode resetContactsManager.
     */
    @Test
    public void testResetContactsManager()
    {
        this.manager.resetContactsManager();
        Mockito.verify(this.mockedContactsManager).reset();
    }

    /**
     * Test de la méthode de reset de changement d'état d'un essai.
     */
    @Test
    public void testResetChangementEtat()
    {
        final Essai essai = new Essai();
        essai.setEtat(EtatEssai.EN_EVALUATION);
        this.manager.setBean(essai);

        this.manager.resetChangementEtat();
        Assert.assertEquals(EtatEssai.EN_EVALUATION,
                            this.manager.getEtatChgtEtat());
        Assert.assertNull(this.manager.getCommentaireChgtEtat());
    }

    /**
     * Test de la méthode d'ajout de DetailEtatEssai.
     */
    @Test
    public void testAddDetailEtatEssai()
    {
        final Essai essai = new Essai();
        essai.setEtat(EtatEssai.EN_EVALUATION);

        this.manager.setEtatChgtEtat(EtatEssai.ARCHIVE);
        this.manager.setCommentaireChgtEtat("commentaireChgtEtat");
        this.manager.setBean(essai);
        this.manager.ajouterDetailEtatEssai();

        Assert.assertEquals(EtatEssai.ARCHIVE,
                            essai.getEtat());
    }

}

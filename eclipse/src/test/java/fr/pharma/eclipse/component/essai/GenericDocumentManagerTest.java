package fr.pharma.eclipse.component.essai;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEssai;
import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.domain.model.essai.DocumentEssai;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.DetailAdministratif;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.document.DocumentAutoriteCompetente;
import fr.pharma.eclipse.factory.document.essai.DocumentEssaiFactory;
import fr.pharma.eclipse.file.FichierFiller;
import fr.pharma.eclipse.service.document.DocumentService;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe GenericDocumentManager.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GenericDocumentManagerTest {
    /**
     * Manager testé.
     */
    private GenericDocumentEssaiManager<DocumentAutoriteCompetente, DetailAdministratif> manager;

    /**
     * Type des documents managés par le manager.
     */
    private static final TypeDocumentEssai TYPE_DOC_MANAGE = TypeDocumentEssai.AUTORITE_COMPETENTE;

    /**
     * Service de documents mocké.
     */
    private DocumentService mockedDocService;

    /**
     * Filler mocké.
     */
    private FichierFiller mockedFiller;

    /**
     * Fabrique mockée.
     */
    private DocumentEssaiFactory<DocumentAutoriteCompetente, DetailAdministratif> mockedFactory;

    /**
     * Méthode d'initialisation.
     */
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        this.mockedDocService = Mockito.mock(DocumentService.class);
        this.mockedFactory = Mockito.mock(DocumentEssaiFactory.class);
        this.mockedFiller = Mockito.mock(FichierFiller.class);
        this.manager = new GenericDocumentEssaiManager<DocumentAutoriteCompetente, DetailAdministratif>(GenericDocumentManagerTest.TYPE_DOC_MANAGE.name());
        this.manager.setDocService(this.mockedDocService);
        this.manager.setFactory(this.mockedFactory);
        this.manager.setFichierFiller(this.mockedFiller);
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown() {
        this.manager = null;
        this.mockedDocService = null;
        this.mockedFactory = null;
        this.mockedFiller = null;
    }

    /**
     * Test de l'initialisation.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.manager);
        Assert.assertEquals(GenericDocumentManagerTest.TYPE_DOC_MANAGE, this.manager.getTypeDocuments());
        Assert.assertEquals(this.mockedDocService, this.manager.getDocService());
        Assert.assertEquals(this.mockedFactory, this.manager.getFactory());
        Assert.assertEquals(this.mockedFiller, this.manager.getFichierFiller());
        Assert.assertNull(this.manager.getLastDocument());
        Assert.assertEquals(StringUtils.EMPTY, this.manager.getCommentaire());
        Assert.assertNotNull(this.manager.getFichier());
        Assert.assertEquals(0, this.manager.getFichier().getContenu().length);
    }

    /**
     * Test de la méthode canCreateDocument.
     */
    @Test
    public void testCanCreateDocument() {
        Assert.assertFalse(this.manager.canCreateDocument());

        this.manager.getFichier().setContenu(new byte[]{0, 1, 0, });
        Assert.assertTrue(this.manager.canCreateDocument());
    }

    /**
     * Test de la méthode createDocument.
     */
    @Test
    public void testCreateDocument() {
        final String commentaire = "  commentaire de document  ";
        final Fichier fichier = Mockito.mock(Fichier.class);
        this.manager.setCommentaire(commentaire);
        this.manager.setFichier(fichier);
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        final DetailAdministratif detailAdministratif = Mockito.mock(DetailAdministratif.class);
        essai.setDetailAdministratif(detailAdministratif);
        final DocumentAutoriteCompetente expectedDoc = Mockito.mock(DocumentAutoriteCompetente.class);
        Mockito.when(this.mockedFactory.getInitializedObject(detailAdministratif, fichier, commentaire.trim())).thenReturn(expectedDoc);

        final DocumentEssai actualDoc = this.manager.createDocument(essai);
        Mockito.verify(this.mockedFactory).getInitializedObject(detailAdministratif, fichier, commentaire.trim());
        Mockito.verify(this.mockedDocService).saveOnDisk(essai, expectedDoc, fichier);
        Assert.assertEquals(expectedDoc, actualDoc);
        Assert.assertEquals(StringUtils.EMPTY, this.manager.getCommentaire());
        Assert.assertFalse(this.manager.getFichier().equals(fichier));
        Assert.assertEquals(0, this.manager.getFichier().getContenu().length);
    }
    /**
     * Test de la méthode initLastDocument - liste de documents vide.
     */
    @Test
    public void testInitLastDocumentEmptyList() {
        final Essai essai = EssaiUtils.makeEssaiTest(1);
        Assert.assertTrue(essai.getDetailAdministratif().getInfosAC().getDocuments().isEmpty());
        final DocumentAutoriteCompetente oldLast = Mockito.mock(DocumentAutoriteCompetente.class);
        this.manager.setLastDocument(oldLast);
        this.manager.initLastDocument(essai);
        Assert.assertNull(this.manager.getLastDocument());
    }

    /**
     * Test de la méthode initLastDocument.
     */
    @Test
    public void testInitLastDocument() {
        final DocumentAutoriteCompetente expectedLast = Mockito.mock(DocumentAutoriteCompetente.class);
        final DocumentAutoriteCompetente oldLast = Mockito.mock(DocumentAutoriteCompetente.class);

        final Essai essai = EssaiUtils.makeEssaiTest(1);
        essai.getDetailAdministratif().getInfosAC().getDocuments().add(expectedLast);

        this.manager.setLastDocument(oldLast);
        this.manager.initLastDocument(essai);
        Assert.assertEquals(expectedLast, this.manager.getLastDocument());
    }

    /**
     * Test de la méthode setFile.
     */
    @Test
    public void testSetFile() {
        final UploadedFile file = Mockito.mock(UploadedFile.class);
        final Fichier fichier = Mockito.mock(Fichier.class);
        this.manager.setFichier(fichier);

        final Answer<Object> answer = new Answer<Object>() {

            @Override
            public Object answer(final InvocationOnMock invocation) throws Throwable {
                int i = 0;
                final UploadedFile argFile = (UploadedFile) invocation.getArguments()[i++];
                final Fichier argFichier = (Fichier) invocation.getArguments()[i++];
                Assert.assertEquals(file, argFile);
                Assert.assertNotNull(argFichier);
                Assert.assertFalse(argFichier.equals(fichier));
                Assert.assertFalse(org.springframework.util.StringUtils.hasText(argFichier.getNom()));
                Assert.assertFalse(org.springframework.util.StringUtils.hasText(argFichier.getTypeFichier()));
                Assert.assertEquals(0, argFichier.getContenu().length);
                return null;
            }
        };
        Mockito.doAnswer(answer).when(this.mockedFiller).fill(Matchers.any(UploadedFile.class), Matchers.any(Fichier.class));
        this.manager.setFile(file);
        Mockito.verify(this.mockedFiller).fill(Matchers.any(UploadedFile.class), Matchers.any(Fichier.class));
    }

    /**
     * Test de la méthode setFile(null).
     */
    @Test
    public void testSetFileNull() {
        final Fichier fichier = new Fichier();
        this.manager.setFichier(fichier);
        final File f = null;
        this.manager.setFileType(f);
        Mockito.verify(this.mockedFiller, Mockito.never()).fill(Matchers.any(UploadedFile.class), Matchers.any(Fichier.class));
    }

    /**
     * Test de la méthode getFile.
     */
    @Test
    public void testGetFile() {
        final UploadedFile file = Mockito.mock(UploadedFile.class);
        Assert.assertNull(this.manager.getFile());
        this.manager.setFile(file);
        Assert.assertNull(this.manager.getFile());
    }

}

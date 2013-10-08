package fr.pharma.eclipse.component.stock;

import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentStock;
import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.domain.model.stock.DocumentStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.domain.model.stock.document.DocumentAppro;
import fr.pharma.eclipse.factory.document.stock.DocumentStockFactory;
import fr.pharma.eclipse.file.FichierFiller;
import fr.pharma.eclipse.service.document.DocumentService;

/**
 * Classe en charge de tester le manager générique de stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GenericStockManagerTest {
    /**
     * GenericStockManager à tester.
     */
    private GenericStockManager<DocumentStock> manager;

    /**
     * Mock du service de gestion des documents.
     */
    private DocumentService mockDocService;

    /**
     * Mock de fabrique d'objets DocumentStock.
     */
    private DocumentStockFactory<DocumentStock> mockFactory;

    /**
     * Filler mocké.
     */
    private FichierFiller mockFiller;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.manager = new GenericStockManager<DocumentStock>(TypeDocumentStock.APPRO.name());
        this.mockDocService = Mockito.mock(DocumentService.class);
        this.manager.setDocService(this.mockDocService);
        this.mockFactory = Mockito.mock(DocumentStockFactory.class);
        this.manager.setFactory(this.mockFactory);
        this.mockFiller = Mockito.mock(FichierFiller.class);
        this.manager.setFichierFiller(this.mockFiller);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.manager = null;
        this.mockDocService = null;
        this.mockFactory = null;
        this.mockFiller = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.manager);
        Assert.assertNotNull(this.manager.getFichierFiller());
        Assert.assertNotNull(this.manager.getDocService());
        Assert.assertNotNull(this.mockDocService);
        Assert.assertNotNull(this.mockFactory);
        Assert.assertNotNull(this.mockFiller);
        Assert.assertEquals(TypeDocumentStock.APPRO, this.manager.getTypeDocuments());
    }

    /**
     * Méthode en charge de tester la méthode canCreateDocument.
     */
    @Test
    public void testCanCreateDocument() {
        Assert.assertFalse(this.manager.canCreateDocument());

        this.manager.getFichier().setContenu(new byte[]{0, 1, 0, });
        Assert.assertTrue(this.manager.canCreateDocument());
    }

    /**
     * Méthode en charge de tester la création de document.
     */
    @Test
    public void testCreateDocument() {
        final Fichier fichier = Mockito.mock(Fichier.class);
        this.manager.setFichier(fichier);
        final MvtStock mvtStock = Mockito.mock(Approvisionnement.class);
        final DocumentAppro expectedDoc = Mockito.mock(DocumentAppro.class);

        Mockito.when(this.mockFactory.getInitializedObject(fichier, mvtStock)).thenReturn(expectedDoc);

        final DocumentStock actualDoc = this.manager.createDocument(mvtStock);
        Mockito.verify(this.mockFactory).getInitializedObject(fichier, mvtStock);
        Mockito.verify(this.mockDocService).saveOnDisk(mvtStock, expectedDoc, fichier);
        Assert.assertEquals(expectedDoc, actualDoc);
        Assert.assertFalse(this.manager.getFichier().equals(fichier));
        Assert.assertEquals(0, this.manager.getFichier().getContenu().length);
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
        Mockito.doAnswer(answer).when(this.mockFiller).fill(Matchers.any(UploadedFile.class), Matchers.any(Fichier.class));
        this.manager.setFile(file);
        Mockito.verify(this.mockFiller).fill(Matchers.any(UploadedFile.class), Matchers.any(Fichier.class));
    }

    /**
     * Test de la méthode setFile(null).
     */
    @Test
    public void testSetFileNull() {
        final Fichier fichier = new Fichier();
        this.manager.setFichier(fichier);
        this.manager.setFile(null);
        Mockito.verify(this.mockFiller, Mockito.never()).fill(Matchers.any(UploadedFile.class), Matchers.any(Fichier.class));
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

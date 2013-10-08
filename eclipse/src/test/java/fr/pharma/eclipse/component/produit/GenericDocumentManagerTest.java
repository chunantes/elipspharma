package fr.pharma.eclipse.component.produit;

import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentProduit;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.domain.model.produit.DispositifMedical;
import fr.pharma.eclipse.domain.model.produit.DocumentProduit;
import fr.pharma.eclipse.domain.model.produit.document.DocumentRandomisation;
import fr.pharma.eclipse.factory.document.produit.DocumentProduitFactory;
import fr.pharma.eclipse.file.FichierFiller;
import fr.pharma.eclipse.service.document.DocumentService;

/**
 * Test de la classe GenericDocumentManager.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class GenericDocumentManagerTest {
    /**
     * Manager testé.
     */
    private GenericDocumentManager<DocumentRandomisation> manager;

    /**
     * Type des documents managés par le manager.
     */
    private static final TypeDocumentProduit TYPE_DOC_MANAGE = TypeDocumentProduit.RANDOMISATION;

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
    private DocumentProduitFactory<DocumentRandomisation> mockedFactory;

    /**
     * Méthode d'initialisation.
     */
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        this.mockedDocService = Mockito.mock(DocumentService.class);
        this.mockedFactory = Mockito.mock(DocumentProduitFactory.class);
        this.mockedFiller = Mockito.mock(FichierFiller.class);
        this.manager = new GenericDocumentManager<DocumentRandomisation>(GenericDocumentManagerTest.TYPE_DOC_MANAGE.name());
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
        final Fichier fichier = Mockito.mock(Fichier.class);
        this.manager.setFichier(fichier);
        final DispositifMedical produit = new DispositifMedical();
        final DocumentRandomisation expectedDoc = Mockito.mock(DocumentRandomisation.class);
        Mockito.when(this.mockedFactory.getInitializedObject(Matchers.any(Fichier.class), Matchers.any(DispositifMedical.class))).thenReturn(expectedDoc);

        final DocumentProduit actualDoc = this.manager.createDocument(produit);
        Mockito.verify(this.mockedFactory).getInitializedObject(Matchers.any(Fichier.class), Matchers.any(DispositifMedical.class));
        Mockito.verify(this.mockedDocService).saveOnDisk(Matchers.any(DispositifMedical.class), Matchers.any(DocumentEclipse.class), Matchers.any(Fichier.class));
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
        this.manager.setFile(null);
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

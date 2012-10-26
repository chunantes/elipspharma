package fr.pharma.eclipse.controller.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import fr.pharma.eclipse.domain.model.essai.DocumentEssai;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.factory.primefaces.DefaultStreamedContentFactory;
import fr.pharma.eclipse.factory.utils.IOStreamsFactory;
import fr.pharma.eclipse.service.document.DocumentServiceImpl;

/**
 * Test de la classe DocumentDownloadController.
 
 * @version $Revision$ $Date$
 */
public class DocumentDownloadControllerTest
{
    /**
     * Classe testée.
     */
    private DocumentDownloadController controller;

    /**
     * Mock de la fabrique de FileInputStream.
     */
    private IOStreamsFactory mockedFileInFactory;

    /**
     * Mock de FileInputStream.
     */
    private FileInputStream expectedStream;

    /**
     * Mock de la fabrique de StreamedContent.
     */
    private DefaultStreamedContentFactory mockedPrimefacesFactory;

    /**
     * StreamedContent mocké pour cas nominal.
     */
    private DefaultStreamedContent expectedNominalResult;

    /**
     * StreamedContent mocké pour cas erreur.
     */
    private DefaultStreamedContent expectedErrorResult;

    /**
     * Mock du service de documents.
     */
    private DocumentServiceImpl mockedDocService;

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void setUp()
    {
        this.mockedPrimefacesFactory = Mockito.mock(DefaultStreamedContentFactory.class);
        this.mockedFileInFactory = Mockito.mock(IOStreamsFactory.class);
        this.mockedDocService = Mockito.mock(DocumentServiceImpl.class);
        this.controller = new DocumentDownloadController();
        this.controller.setFileInStreamFactory(this.mockedFileInFactory);
        this.controller.setStreamedContentFactory(this.mockedPrimefacesFactory);
        this.controller.setDocService(this.mockedDocService);

        this.expectedNominalResult = Mockito.mock(DefaultStreamedContent.class);
        this.expectedErrorResult = Mockito.mock(DefaultStreamedContent.class);
        this.expectedStream = Mockito.mock(FileInputStream.class);
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown()
    {
        this.controller = null;
        this.mockedDocService = null;
        this.mockedPrimefacesFactory = null;
        this.mockedFileInFactory = null;
        this.expectedErrorResult = null;
        this.expectedNominalResult = null;
        this.expectedStream = null;
    }

    /**
     * Méthode d'initialisation des fabriques.
     */
    private void initMockedFactories()
    {
        Mockito
                .when(this.mockedPrimefacesFactory
                        .getInitializedObject(Matchers.any(InputStream.class),
                                              Matchers.anyString()))
                .thenReturn(this.expectedNominalResult);
        Mockito
                .when(this.mockedPrimefacesFactory.getInitializedObjectInError())
                .thenReturn(this.expectedErrorResult);
        try
        {
            Mockito.when(this.mockedFileInFactory.getInitializedInputStream(Matchers
                    .any(File.class))).thenReturn(this.expectedStream);
        }
        catch (final FileNotFoundException e)
        {
            Assert.fail("Erreur d'initialisation des données.");
        }
    }
    /**
     * Test de l'initialisation du test.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.controller);
        Assert.assertEquals(this.mockedDocService,
                            this.controller.getDocService());
        Assert.assertEquals(this.mockedFileInFactory,
                            this.controller.getFileInStreamFactory());
        Assert.assertEquals(this.mockedPrimefacesFactory,
                            this.controller.getStreamedContentFactory());
    }

    /**
     * Test de la méthode downloadDocument - fichier inexistant.
     */
    @Test
    public void testDownloadDocumentFileNotFound()
    {
        this.initMockedFactories();
        final Essai essai = Mockito.mock(Essai.class);
        final DocumentEssai doc = Mockito.mock(DocumentEssai.class);
        final String expectedFilename = "fichier.txt";
        final File expectedFile = Mockito.mock(File.class);
        final Throwable expectedExc = Mockito.mock(FileNotFoundException.class);

        Mockito.when(this.mockedDocService.getFile(essai,
                                                   doc)).thenReturn(expectedFile);
        Mockito.when(expectedFile.getName()).thenReturn(expectedFilename);
        try
        {
            Mockito
                    .when(this.mockedFileInFactory.getInitializedInputStream(expectedFile))
                    .thenThrow(expectedExc);
        }
        catch (final FileNotFoundException e)
        {
            Assert.fail("Erreur de paramétrage des données de test.");
        }

        final StreamedContent actualStream = this.controller.downloadDocument(essai,
                                                                              doc);
        try
        {
            Mockito.verify(this.mockedDocService).getFile(essai,
                                                          doc);
            Mockito.verify(this.mockedFileInFactory).getInitializedInputStream(expectedFile);
            Mockito.verify(this.mockedPrimefacesFactory,
                           Mockito.never()).getInitializedObject(Matchers.any(InputStream.class),
                                                                 Matchers.anyString());
            Mockito.verify(this.mockedPrimefacesFactory).getInitializedObjectInError();
            Assert.assertEquals(this.expectedErrorResult,
                                actualStream);
        }
        catch (final FileNotFoundException e)
        {
            Assert.fail("Erreur de paramétrage des données de test.");
        }
    }
    /**
     * Test de la méthode downloadDocument.
     */
    @Test
    public void testDownloadDocumentFileOk()
    {
        this.initMockedFactories();
        final Essai essai = Mockito.mock(Essai.class);
        final DocumentEssai doc = Mockito.mock(DocumentEssai.class);
        final String expectedFilename = "fichier.txt";
        final File expectedFile = Mockito.mock(File.class);

        Mockito.when(this.mockedDocService.getFile(essai,
                                                   doc)).thenReturn(expectedFile);
        Mockito.when(doc.getNomUtilisateur()).thenReturn(expectedFilename);

        final StreamedContent actualStream = this.controller.downloadDocument(essai,
                                                                              doc);
        try
        {
            Mockito.verify(this.mockedDocService).getFile(essai,
                                                          doc);
            Mockito.verify(this.mockedFileInFactory).getInitializedInputStream(expectedFile);
            Mockito.verify(doc).getNomUtilisateur();
            Mockito
                    .verify(this.mockedPrimefacesFactory)
                    .getInitializedObject(this.expectedStream,
                                          expectedFilename);
            Mockito.verify(this.mockedPrimefacesFactory,
                           Mockito.never()).getInitializedObjectInError();
            Assert.assertEquals(this.expectedNominalResult,
                                actualStream);
        }
        catch (final FileNotFoundException e)
        {
            Assert.fail("Erreur de paramétrage des données de test.");
        }
    }
}

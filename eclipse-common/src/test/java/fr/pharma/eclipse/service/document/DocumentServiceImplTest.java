package fr.pharma.eclipse.service.document;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.common.BeanParentDocument;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.document.DocumentAutoriteCompetente;
import fr.pharma.eclipse.service.document.builder.FileDocumentBuilder;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.file.FileHelper;

/**
 * Test de la classe DocumentServiceImpl.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DocumentServiceImplTest extends AbstractEclipseJUnitTest {
    /**
     * Service testé.
     */
    private DocumentServiceImpl service;

    /**
     * Mock du générateur de File.
     */
    private FileDocumentBuilder mockedBuilder;

    /**
     * Mock de FileHelper.
     */
    private FileHelper mockedFileHelper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedBuilder = Mockito.mock(FileDocumentBuilder.class);
        this.mockedFileHelper = Mockito.mock(FileHelper.class);
        this.service = new DocumentServiceImpl(Mockito.mock(GenericDao.class));
        this.service.setFileBuilder(this.mockedBuilder);
        this.service.setFileHelper(this.mockedFileHelper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.service = null;
        this.mockedBuilder = null;
        this.mockedFileHelper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.service);
        Assert.assertEquals(this.mockedBuilder, this.service.getFileBuilder());
        Assert.assertEquals(this.mockedFileHelper, this.service.getFileHelper());
    }

    /**
     * Test de la méthode getFile.
     */
    @Test
    public void testGetFile() {
        final BeanParentDocument bean = Mockito.mock(BeanParentDocument.class);
        final DocumentEclipse doc = Mockito.mock(DocumentEclipse.class);
        final File expectedFile = Mockito.mock(File.class);
        Mockito.when(this.mockedBuilder.build(bean, doc)).thenReturn(expectedFile);
        final File actualFile = this.service.getFile(bean, doc);
        Mockito.verify(this.mockedBuilder).build(bean, doc);
        Assert.assertEquals(expectedFile, actualFile);
    }

    /**
     * Test de la méthode canBeDownloaded.
     */
    @Test
    public void testCanBeDownloaded() {
        Assert.assertFalse(this.service.canBeDownloaded(null));
        final DocumentEclipse doc = new DocumentAutoriteCompetente();
        Assert.assertFalse(this.service.canBeDownloaded(doc));
        doc.setId(1L);
        Assert.assertTrue(this.service.canBeDownloaded(doc));
    }

    /**
     * Test de la méthode saveOnDisk.
     */
    @Test
    public void testSaveOnDisk() {
        final BeanParentDocument bean = Mockito.mock(BeanParentDocument.class);
        final DocumentEclipse doc = Mockito.mock(DocumentEclipse.class);
        final Fichier fichier = Mockito.mock(Fichier.class);
        final File expectedFile = Mockito.mock(File.class);
        final String expectedNomDisque = "test_20101125.txt";

        Mockito.when(this.mockedFileHelper.getSystemFileSeparator()).thenReturn("\\");
        Mockito.when(doc.getNomDisque()).thenReturn(expectedNomDisque);
        Mockito.when(this.mockedBuilder.build(bean, doc)).thenReturn(expectedFile);
        Mockito.when(expectedFile.getAbsolutePath()).thenReturn("D:\\pharma\\ac\\1\\test.txt");

        this.service.saveOnDisk(bean, doc, fichier);

        Mockito.verify(this.mockedFileHelper).getSystemFileSeparator();
        Mockito.verify(this.mockedBuilder).build(bean, doc);
        Mockito.verify(expectedFile).getAbsolutePath();
        Mockito.verify(doc).getNomDisque();
        Mockito.verify(this.mockedFileHelper).save(fichier, expectedNomDisque, "D:\\pharma\\ac\\1");
    }
}

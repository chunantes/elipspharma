package fr.pharma.eclipse.file;

import java.io.IOException;

import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.util.StringUtils;

import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.file.FileHelper;

/**
 * Test de la classe FichierFiller.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class FichierFillerTest extends AbstractEclipseJUnitTest {
    /**
     * Classe testée.
     */
    private FichierFiller filler;

    /**
     * Helper mocké.
     */
    private FileHelper mockedFileHelper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUp() {
        this.mockedFileHelper = Mockito.mock(FileHelper.class);
        this.filler = new FichierFiller();
        this.filler.setFileHelper(this.mockedFileHelper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.filler = null;
        this.mockedFileHelper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.filler);
        Assert.assertEquals(this.mockedFileHelper, this.filler.getFileHelper());
    }

    /**
     * Test de la méthode fill - cas nominal.
     * @throws IOException Erreur de configuration des mocks.
     */
    @Test
    public void testFillOk() throws IOException {
        final String filename = "C:\\test.txt";
        final String expectedFilename = "test.txt";
        final String expectedContentType = "plain/text";
        final byte[] expectedBytes = new byte[]{0, 1, 100, };
        final Fichier fichier = new Fichier();
        final UploadedFile upFile = Mockito.mock(UploadedFile.class);
        Mockito.when(upFile.getName()).thenReturn(filename);
        Mockito.when(upFile.getBytes()).thenReturn(expectedBytes);
        Mockito.when(upFile.getContentType()).thenReturn(expectedContentType);
        Mockito.when(this.mockedFileHelper.extractFileName(filename)).thenReturn(expectedFilename);

        this.filler.fill(upFile, fichier);

        Mockito.verify(upFile).getName();
        Mockito.verify(upFile).getBytes();
        Mockito.verify(upFile).getContentType();
        Mockito.verify(this.mockedFileHelper).extractFileName(filename);
        Assert.assertEquals(expectedBytes, fichier.getContenu());
        Assert.assertEquals(expectedFilename, fichier.getNom());
        Assert.assertEquals(expectedContentType, fichier.getTypeFichier());
    }

    /**
     * Test de la méthode fill - cas d'erreur.
     * @throws IOException Erreur de configuration des mocks.
     */
    @Test
    public void testFillIOException() throws IOException {
        final Throwable ioException = new IOException();
        final Fichier fichier = new Fichier();
        final UploadedFile upFile = Mockito.mock(UploadedFile.class);
        Mockito.when(upFile.getBytes()).thenThrow(ioException);

        this.filler.fill(upFile, fichier);

        Mockito.verify(upFile).getBytes();
        Mockito.verify(upFile, Mockito.never()).getName();
        Mockito.verify(upFile, Mockito.never()).getContentType();
        Assert.assertEquals(0, fichier.getContenu().length);
        Assert.assertFalse(StringUtils.hasText(fichier.getNom()));
        Assert.assertFalse(StringUtils.hasText(fichier.getTypeFichier()));
    }
}

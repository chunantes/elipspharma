package fr.pharma.eclipse.service.document.builder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.essai.DocumentEssai;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.service.document.resolver.DocEclipseFileNameResolver;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.file.FileHelper;

/**
 * Test de la classe FileDocumentBuilder.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class FileDocumentBuilderTest {
    /**
     * Classe testée.
     */
    private FileDocumentBuilder builder;

    /**
     * Résolveur mocké.
     */
    private DocEclipseFileNameResolver mockedResolver;

    /**
     * Mock du fileHelper.
     */
    private FileHelper mockedFileHelper;

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void setUp() {
        this.mockedFileHelper = Mockito.mock(FileHelper.class);
        this.mockedResolver = Mockito.mock(DocEclipseFileNameResolver.class);
        this.builder = new FileDocumentBuilder();
        this.builder.setFileHelper(this.mockedFileHelper);
        final List<DocEclipseFileNameResolver> resolvers = new ArrayList<DocEclipseFileNameResolver>();
        resolvers.add(this.mockedResolver);
        this.builder.setResolvers(resolvers);
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown() {
        this.builder = null;
        this.mockedFileHelper = null;
        this.mockedResolver = null;
    }

    /**
     * Test de lma méthode build.
     */
    @Test
    public void testBuild() {
        long ids = 1;
        final Essai essai = EssaiUtils.makeEssaiTest(ids++);
        final DocumentEssai doc = Mockito.mock(DocumentEssai.class);
        final String expectedFilename = "C://temp";
        final File expectedFile = Mockito.mock(File.class);

        Mockito.when(this.mockedResolver.supports(doc)).thenReturn(true);
        Mockito.when(this.mockedResolver.resolve(essai, doc)).thenReturn(expectedFilename);
        Mockito.when(this.mockedFileHelper.getFile(expectedFilename)).thenReturn(expectedFile);

        final File actualFile = this.builder.build(essai, doc);
        Mockito.verify(this.mockedResolver).supports(doc);
        Mockito.verify(this.mockedResolver).resolve(essai, doc);
        Mockito.verify(this.mockedFileHelper).getFile(expectedFilename);
        Assert.assertEquals(expectedFile, actualFile);
    }

}

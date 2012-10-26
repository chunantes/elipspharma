package fr.pharma.eclipse.factory.primefaces;

import java.io.InputStream;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.primefaces.model.DefaultStreamedContent;

import fr.pharma.eclipse.utils.file.FileHelper;

/**
 * Test de la classe DefaultStreamedContentFactory.
 
 * @version $Revision$ $Date$
 */
public class DefaultStreamedContentFactoryTest
{
    /**
     * Fabrique testée.
     */
    private DefaultStreamedContentFactory factory;

    /**
     * Helper de fichiers mocké.
     */
    private FileHelper mockedFileHelper;

    /**
     * Clé de l'extension de test.
     */
    private static final String KEY_EXTENSION = "pdf";

    /**
     * Extension de test.
     */
    private static final String VALUE_EXTENSION = "application/pdf";

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void setUp()
    {
        this.mockedFileHelper = Mockito.mock(FileHelper.class);
        this.factory = new DefaultStreamedContentFactory();
        this.factory.setFileHelper(this.mockedFileHelper);
        final SortedMap<String, String> dicoExtensions = new TreeMap<String, String>();
        dicoExtensions.put(DefaultStreamedContentFactoryTest.KEY_EXTENSION,
                           DefaultStreamedContentFactoryTest.VALUE_EXTENSION);
        this.factory.setDicoExtensions(dicoExtensions);
    }

    /**
     * Test de l'initialisation du test.
     */
    @Test
    public void testInit()
    {
        Assert.assertNotNull(this.factory);
        Assert.assertNotNull(this.factory.getDicoExtensions());
        Assert.assertEquals(DefaultStreamedContentFactoryTest.VALUE_EXTENSION,
                            this.factory
                                    .getDicoExtensions()
                                    .get(DefaultStreamedContentFactoryTest.KEY_EXTENSION));
        Assert.assertEquals(this.mockedFileHelper,
                            this.factory.getFileHelper());
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown()
    {
        this.factory = null;
        this.mockedFileHelper = null;
    }

    /**
     * Test de la méthode getInitializedObject.
     */
    @Test
    public void testGetInitializedObject()
    {
        final InputStream stream = Mockito.mock(InputStream.class);
        final String fileName = "file.txt";

        Mockito
                .when(this.mockedFileHelper.getExtension(fileName))
                .thenReturn(DefaultStreamedContentFactoryTest.KEY_EXTENSION);
        final DefaultStreamedContent res = this.factory.getInitializedObject(stream,
                                                                             fileName);
        Mockito.verify(this.mockedFileHelper).getExtension(fileName);
        Assert.assertNotNull(res);
        Assert.assertEquals(stream,
                            res.getStream());
    }

    /**
     * Test de la méthode getInitializedObject - extension non gérée.
     */
    @Test
    public void testGetInitializedObjectBadExtension()
    {
        final InputStream stream = Mockito.mock(InputStream.class);
        final String fileName = "file.txt";

        Mockito
                .when(this.mockedFileHelper.getExtension(fileName))
                .thenReturn(DefaultStreamedContentFactoryTest.KEY_EXTENSION.concat("_badPart"));
        final DefaultStreamedContent res = this.factory.getInitializedObject(stream,
                                                                             fileName);
        Mockito.verify(this.mockedFileHelper).getExtension(fileName);
        Assert.assertNotNull(res);
        Assert.assertEquals(stream,
                            res.getStream());
    }

    /**
     * Test de la méthode getInitializedObjectInError.
     */
    @Test
    public void testGetInitializedObjectInError()
    {
        final DefaultStreamedContent res = this.factory.getInitializedObjectInError();
        Assert.assertNotNull(res);
        Assert.assertNotNull(res.getStream());
    }

}

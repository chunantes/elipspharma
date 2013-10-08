package fr.pharma.eclipse.utils.file;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.domain.model.common.Fichier;

/**
 * Test de la classe FileHelper.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class FileHelperTest {
    /**
     * FileHelper à tester.
     */
    private FileHelper fileHelper;

    /**
     * Directory mocké.
     */
    private File directory;

    /**
     * Méthode d'initialisation des données de test.
     */
    @Before
    public void init() {
        this.directory = Mockito.mock(File.class);
        this.fileHelper = new FileHelper();
    }

    /**
     * Méthode de purge des données de test.
     */
    @After
    public void end() {
        this.fileHelper = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.fileHelper);
    }

    /**
     * Méthode en charge de tester la création de répertoire KO (le répertoire
     * existe => pas de création).
     */
    @Test
    public void testCreateDirectoryKO() {
        Mockito.when(this.directory.exists()).thenReturn(Boolean.TRUE);
        this.fileHelper.createDirectory(this.directory);
    }

    /**
     * Méthode en charge de tester la création de répertoire OK (le répertoire
     * n'existe pas => création de l'arborescence). Cas sans exception.
     */
    @Test
    public void testCreateDirectoryOKWithoutException() {
        Mockito.when(this.directory.exists()).thenReturn(Boolean.FALSE);
        Mockito.when(this.directory.mkdirs()).thenReturn(Boolean.TRUE);
        this.fileHelper.createDirectory(this.directory);
    }

    /**
     * Méthode en charge de tester la création de répertoire OK (le répertoire
     * n'existe pas => création de l'arborescence). Cas avec exception.
     */
    @Test
    public void testCreateDirectoryOKWithException() {
        Mockito.when(this.directory.exists()).thenReturn(Boolean.FALSE);
        Mockito.when(this.directory.mkdirs()).thenReturn(Boolean.FALSE);
        this.fileHelper.createDirectory(this.directory);
    }

    /**
     * Méthode en charge de tester la récupération de l'extension d'un fichier.
     */
    @Test
    public void testGetExtension() {
        Assert.assertEquals("xls", this.fileHelper.getExtension("01.test.xls"));
        Assert.assertEquals("csv", this.fileHelper.getExtension("test.csv"));
    }

    /**
     * Méthode en charge de tester la récupération de la date courante.
     */
    @Test
    public void testGetCurrentDate() {
        final String currentDate = this.fileHelper.getCurrentDate();
        Assert.assertNotNull(currentDate);
    }

    /**
     * Méthode en charge de tester la suppression d'un répertoire pour un
     * répertoire qui n'existe pas.
     */
    @Test
    public void testDeleteDirectoryWithBadPath() {
        final File path = new File("BAD");
        final Boolean result = this.fileHelper.deleteDirectory(path);
        Assert.assertEquals(Boolean.FALSE, result);
    }

    /**
     * Méthode en charge de tester la suppression d'un répertoire pour un
     * répertoire existant et vide.
     */
    @Test
    public void testDeleteDirectoryEmpty() {
        final File dir = new File("directory");
        final boolean directoryCreated = dir.mkdir();
        Assert.assertEquals(Boolean.TRUE, directoryCreated);

        final Boolean result = this.fileHelper.deleteDirectory(dir);
        Assert.assertEquals(Boolean.TRUE, result);
    }

    /**
     * Méthode en charge de tester la suppression d'un répertoire pour un
     * répertoire existant contenant des fichiers.
     * @throws IOException Exception Input/Output.
     */
    @Test
    public void testDeleteDirectoryWithFiles() throws IOException {
        final File dir = new File("directory");
        final boolean directoryCreated = dir.mkdir();
        Assert.assertEquals(Boolean.TRUE, directoryCreated);
        final File file1 = new File(dir, "file1");
        file1.createNewFile();

        final Boolean result = this.fileHelper.deleteDirectory(dir);
        Assert.assertEquals(Boolean.TRUE, result);
    }

    /**
     * Méthode en charge de tester la suppression d'un répertoire pour un
     * répertoire existant contenant des fichiers.
     * @throws IOException Exception Input/Output.
     */
    @Test
    public void testDeleteDirectoryWithFilesAndDirectories() throws IOException {
        final File dir = new File("directory");
        final boolean directoryCreated = dir.mkdir();
        Assert.assertEquals(Boolean.TRUE, directoryCreated);
        final File file1 = new File(dir, "file1");
        file1.createNewFile();
        final File dir2 = new File(dir, "dir2");
        dir2.mkdir();

        final Boolean result = this.fileHelper.deleteDirectory(dir);
        Assert.assertEquals(Boolean.TRUE, result);
    }

    /**
     * Test des méthodes de sérialisation/désérialisation.
     */
    @Test
    public void testSerializationDeserializationOk() {
        final String str = "String to serialize";

        // Sérialisation
        final byte[] outStr1 = this.fileHelper.serialize(str);
        final byte[] outStr2 = this.fileHelper.serialize(str);
        Assert.assertNotNull(outStr1);
        Assert.assertFalse(outStr1.length == 0);

        // Désérialisation
        final Object object1 = this.fileHelper.deserialize(outStr1);
        final Object object2 = this.fileHelper.deserialize(outStr2);
        this.verifyDeserialization(str, object1);
        this.verifyDeserialization(str, object2);
        Assert.assertEquals(object1, object2);
    }

    /**
     * Méthode de vérification pour les beans désérialisés.
     * @param ref String à l'origine de la sérialisation.
     * @param object Objet récupéré de la désérialisation.
     */
    private void verifyDeserialization(final String ref,
                                       final Object object) {
        Assert.assertNotNull(object);
        Assert.assertTrue(object instanceof String);
        final String res = (String) object;
        Assert.assertEquals(ref, res);
    }

    /**
     * Méthode en charge de tester la sauvegarde d'un fichier sur disque.
     */
    @Test
    public void testSaveOK() {
        final Fichier fichier = this.prepareFichierTest();
        final String directoryPath = "directoryPath";
        this.fileHelper.save(fichier, "test.xls", directoryPath);
        // Suppression des arborescences créées
        final File file = new File("directoryPath" + File.separator + "test.xls");
        Assert.assertTrue(file.delete());
        final File rep = new File("directoryPath");
        Assert.assertTrue(rep.delete());
    }

    /**
     * Méthode en charge de tester la sauvegarde d'un fichier vide sur disque.
     */
    @Test
    public void testSaveEmptyFichier() {
        final Fichier fichier = new Fichier();
        final String directoryPath = "directoryPath";
        this.fileHelper.save(fichier, "test.xls", directoryPath);
        // Suppression des arborescences créées
        final File file = new File("directoryPath" + File.separator + "test.xls");
        Assert.assertFalse(file.delete());
        final File rep = new File("directoryPath");
        Assert.assertFalse(rep.delete());
    }

    /**
     * Test de la méthode getSystemFileSeparator.
     */
    @Test
    public void testGetSystemFileSeparator() {
        Assert.assertEquals(System.getProperty("file.separator"), this.fileHelper.getSystemFileSeparator());
    }

    /**
     * Test de la méthode extractFileName.
     */
    @Test
    public void testExtractFileName() {
        final String expectedName = "test.txt";
        Assert.assertEquals(expectedName, this.fileHelper.extractFileName(expectedName));

        final String fileSeparator = System.getProperty("file.separator");
        final StringBuilder completeName = new StringBuilder("C:").append(fileSeparator).append("temp").append(fileSeparator).append(expectedName);
        Assert.assertEquals(expectedName, this.fileHelper.extractFileName(completeName.toString()));
    }

    /**
     * Prépare un fichier de test pour la sauvegarde.
     * @return Un fichier avec un contenu.
     */
    private Fichier prepareFichierTest() {
        final Fichier fichier = new Fichier();
        final byte[] contenu = new byte[]{41, 52 };
        fichier.setContenu(contenu);
        return fichier;
    }

}

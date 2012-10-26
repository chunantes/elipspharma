package fr.pharma.eclipse.utils.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe de Helper pour le traitement des fichiers.
 
 * @version $Revision$ $Date$
 */
public class FileHelper
    implements Serializable
{
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -2998770766883621851L;

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(FileHelper.class);

    /**
     * Méthode en charge de récupérer le séparateur de fichier du système.
     * @see http://download.oracle.com/javase/tutorial/essential/environment/sysprop.html
     * @return "Character that separates components of a file path. <br />
     * This is "/" on UNIX and "\ " on Windows."
     */
    public String getSystemFileSeparator()
    {
        return System.getProperty("file.separator");
    }

    /**
     * Méthode en charge de renvoyer le nom simple (nom.extension) à partir d'un nom complet
     * (C:\...\nom.extension).
     * @param completeFileName Nom avec chemin.
     * @return Nom simple avec extension.
     */
    public String extractFileName(final String completeFileName)
    {
        if (!StringUtils.hasText(completeFileName)
            || !completeFileName.contains(this.getSystemFileSeparator()))
        {
            return completeFileName;
        }
        return completeFileName.substring(completeFileName.lastIndexOf(this
                .getSystemFileSeparator()) + 1);
    }

    /**
     * Méthode en charge de récupérer un fichier sur le disque.
     * @param filePath Chemins d'accès au fichier.
     * @return Le fichier correcpondant au path.
     */
    public File getFile(final String filePath)
    {
        return new File(filePath);
    }

    /**
     * Méthode permettant d'enregistrer un fichier sur disque.
     * @param fichier Fichier à enregistrer.
     * @param nameFichier Nom à utiliser pour l'enregistrement du fichier (avec extension).
     * @param directoryPath Chemin du répertoire pour l'enregistrement du fichier.
     */
    public void save(final Fichier fichier,
                     final String nameFichier,
                     final String directoryPath)
    {
        if (fichier.getContenu().length == 0)
        {
            return;
        }

        final File directory = new File(directoryPath);

        // Création du répertoire
        this.createDirectory(directory);

        // Création du fichier dans le répertoire
        final File file = new File(directory,
                                   nameFichier);

        FileChannel out = null;

        try
        {
            final byte[] contenu = fichier.getContenu();
            out = new FileOutputStream(file.getPath()).getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(contenu.length);
            byteBuffer = ByteBuffer.wrap(contenu);
            out.write(byteBuffer);
        }
        catch (final FileNotFoundException e)
        {
            this.log.error("Erreur sur la récupération de fichier",
                           e);
        }
        catch (final IOException e)
        {
            this.log.error("Erreur IOException",
                           e);
        }
        finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (final IOException e)
                {
                    this.log.error("Erreur lors de la fermeture du FileChannel",
                                   e);
                }
            }
        }
    }

    /**
     * Méthode en charge de créer l'arborescence d'un répertoire si elle n'existe pas.
     * @param directory Répertoire.
     */
    void createDirectory(final File directory)
    {
        if (!directory.exists())
        {
            final boolean result = directory.mkdirs();
            if (!result)
            {
                this.log.error("Erreur lors de la création du répertoire "
                               + "de stockage des fichiers : "
                               + directory.getName());
            }
        }
    }

    /**
     * Méthode en charge de récupérer l'extension d'un nom de fichier.
     * @param nameFile Nom du fichier.
     * @return Extension.
     */
    public String getExtension(final String nameFile)
    {
        final String[] tmp = nameFile.split("\\.");
        return tmp[tmp.length - 1];
    }

    /**
     * Méthode en charge de retourner la date système au format AAAAMMJJHHMMSS.
     * @return Date système.
     */
    String getCurrentDate()
    {
        return Utils.formatDate(Calendar.getInstance(EclipseConstants.LOCALE).getTime(),
                                "yyyyMMddHHmmss");
    }

    /**
     * Méthode en charge de supprimer le contenu d'un répertoire. Si le répertoire n'est pas vide,
     * suppression de tous les éléments de façon récursive.
     * @param file Chemin du répertoire à supprimer.
     * @return Résultat de la suppression.
     */
    public boolean deleteDirectory(final File file)
    {
        if (file.exists())
        {
            final File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++)
            {
                if (files[i].isDirectory())
                {
                    this.deleteDirectory(files[i]);
                }
                else
                {
                    files[i].delete();
                }
            }
        }
        return file.delete();
    }

    /**
     * Méthode en charge de sérialiser un objet et de retourner le tableau de bytes correspondant.
     * @param <BEAN> Type de l'objet à sérialiser. L'objet doit être sérialisable.
     * @param object Objet sérialisable à sérialiser.
     * @return Le tableau de bytes de l'objet.
     */
    public <BEAN extends Serializable> byte[] serialize(final BEAN object)
    {
        final ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        try
        {
            final ObjectOutputStream objectOS = new ObjectOutputStream(byteArrayOS);
            objectOS.writeObject(object);
        }
        catch (final IOException e)
        {
            this.log.error("Erreur IOException",
                           e);
        }
        return byteArrayOS.toByteArray();
    }

    /**
     * Méthode de récupération d'objet séralisé.
     * @param bytes Tableau de bytes représentant une sérialisation d'objet.
     * @return Un objet désérialisé (null si erreur).
     */
    public Object deserialize(final byte[] bytes)
    {
        final ByteArrayInputStream byteArrayIS = new ByteArrayInputStream(bytes);
        ObjectInputStream objectIS;
        Object res = null;
        try
        {
            objectIS = new ObjectInputStream(byteArrayIS);
            res = objectIS.readObject();
        }
        catch (final IOException e)
        {
            this.log.error("Erreur IOException",
                           e);
        }
        catch (final ClassNotFoundException e)
        {
            this.log.error("Erreur ClassNotFoundException",
                           e);
        }
        catch (final ClassCastException e)
        {
            this.log.error("Erreur ClassCastException",
                           e);
        }
        return res;
    }

}

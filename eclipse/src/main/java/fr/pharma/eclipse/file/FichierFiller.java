package fr.pharma.eclipse.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.file.FileHelper;

/**
 * Classe en charge de remplir les données d'un fichier à partir d'un objet de l'IHM correspondant
 * au fileUpload.
 
 * @version $Revision$ $Date$
 */
public class FichierFiller
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3042977922337723980L;

    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(FichierFiller.class);

    /**
     * Helper pour la gestion des fichiers.
     */
    private FileHelper fileHelper;

    /**
     * Méthode qui renseigne les informations du fichier métier à partir de celles du fichier
     * remonté de l'IHM.
     * @param file Fichier téléchargé via l'IHM.
     * @param fichier Fichier métier.
     */
    public void fill(final UploadedFile file,
                     final Fichier fichier)
    {
        try
        {
            fichier.setContenu(file.getBytes());
            fichier.setNom(this.fileHelper.extractFileName(file.getName()));
            fichier.setTypeFichier(file.getContentType());
        }
        catch (final IOException e)
        {
            FichierFiller.LOG.error(new StringBuilder("Erreur de récupération de fichier : ")
                    .append(Utils.getStringStack(e))
                    .toString());
        }
    }

    /**
     * Méthode qui renseigne les informations du fichier métier à partir de celles du fichier en
     * paramètre.
     * @param file Fichier.
     * @param fichier Fichier métier.
     */
    public void fill(final File file,
                     final Fichier fichier)
    {
        try
        {

            final byte[] b = new byte[(int) file.length()];
            final FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(b);
            fichier.setContenu(b);
            fichier.setNom(file.getName());
            fichier.setTypeFichier("application/vnd.ms-excel");
        }
        catch (final IOException e)
        {
            FichierFiller.LOG.error(new StringBuilder("Erreur de récupération de fichier : ")
                    .append(Utils.getStringStack(e))
                    .toString());
        }
    }

    /**
     * Getter sur fileHelper.
     * @return Retourne le fileHelper.
     */
    FileHelper getFileHelper()
    {
        return this.fileHelper;
    }

    /**
     * Setter pour fileHelper.
     * @param fileHelper le fileHelper à écrire.
     */
    public void setFileHelper(final FileHelper fileHelper)
    {
        this.fileHelper = fileHelper;
    }

}

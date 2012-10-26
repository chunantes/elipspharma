package fr.pharma.eclipse.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.domain.enums.surcout.TypeCalcul;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Resultat;
import fr.pharma.eclipse.exception.TechnicalException;
import fr.pharma.eclipse.poi.builder.SheetBuilder;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe en charge de créer le fichier excel dans l'arborescence de fichier de l'application.
 
 * @version $Revision$ $Date$
 */
public class FileCreator
    implements Serializable
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -5976935064576009308L;

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(FileCreator.class);

    /**
     * Répertoire contenant les documents.
     */
    private String documentsDirectory;

    /**
     * Builders en charge de construire les grilles excel.
     */
    private Map<TypeCalcul, SheetBuilder> builders;

    /**
     * Méthode en charge de créer et d'appeler le constructeur de fichier correspondant au type de
     * calcul.
     * @param essai Essai.
     * @param datas Les données
     * @param type Le type.
     */
    public File createFile(final Essai essai,
                           final Map<Item, Resultat> datas,
                           final TypeCalcul type)
    {

        final HSSFWorkbook wb = new HSSFWorkbook();
        try
        {
            final HSSFSheet sheet = wb.createSheet("Surcouts");

            this.builders.get(type).build(essai,
                                          datas,
                                          sheet,
                                          wb);

            final String filename = this.buildFileName(essai,
                                                       type);

            // creation du répertoire
            final File directory =
                new File(StringUtils.substringBeforeLast(filename,
                                                         EclipseConstants.SLASH));
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
            final File file = new File(filename);
            file.delete();
            final FileOutputStream fileOut = new FileOutputStream(file);
            wb.write(fileOut);
            fileOut.close();
            return file;
        }
        catch (final IOException e)
        {
            throw new TechnicalException("Le fichier ne peut être ecrit.");
        }

    }
    /**
     * Méthode en charge de construire le nom du fichier sur le disque.
     * @param essai L'essai.
     * @param type Le type de calcul
     * @return Le nom du fichier.
     */
    private String buildFileName(final Essai essai,
                                 final TypeCalcul type)
    {

        final StringBuilder builder = new StringBuilder();
        builder.append(this.documentsDirectory);
        builder.append(EclipseConstants.SLASH);
        builder.append("essais");
        builder.append(EclipseConstants.SLASH);
        builder.append(essai.getId());
        builder.append(EclipseConstants.SLASH);
        builder.append(type.getRepertoire());
        builder.append(EclipseConstants.SLASH);
        builder.append("surcouts.xls");
        return builder.toString();
    }

    /**
     * Setter pour builders.
     * @param builders le builders à écrire.
     */
    public void setBuilders(final Map<TypeCalcul, SheetBuilder> builders)
    {
        this.builders = builders;
    }

    /**
     * Setter pour documentsDirectory.
     * @param documentsDirectory le documentsDirectory à écrire.
     */
    public void setDocumentsDirectory(final String documentsDirectory)
    {
        this.documentsDirectory = documentsDirectory;
    }

}

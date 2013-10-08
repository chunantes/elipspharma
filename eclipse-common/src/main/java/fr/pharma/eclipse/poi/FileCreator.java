package fr.pharma.eclipse.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pharma.eclipse.domain.enums.surcout.TypeCalcul;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.surcout.Item;
import fr.pharma.eclipse.domain.model.surcout.Resultat;
import fr.pharma.eclipse.poi.builder.SheetBuilder;

/**
 * Classe en charge de créer le fichier excel dans l'arborescence de fichier de
 * l'application.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class FileCreator implements Serializable {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -5976935064576009308L;

    /**
     * Logger.
     */
    private final Logger log = LoggerFactory.getLogger(FileCreator.class);

    /**
     * Répertoire contenant les documents des essais.
     */
    private File documentsDirectory;

    /**
     * Builders en charge de construire les grilles excel.
     */
    private Map<TypeCalcul, SheetBuilder> builders;

    /**
     * Méthode en charge de créer et d'appeler le constructeur de fichier
     * correspondant au type de calcul.
     * @param essai Essai.
     * @param datas Les données
     * @param type Le type.
     * @throws IOException
     */
    public File createFile(final Essai essai,
                           final Map<Item, Resultat> datas,
                           final TypeCalcul type) throws IOException {
        final File file = new File(buildEssaiDirectory(essai, type), "surcouts.xls");

        file.delete();

        final FileOutputStream fileOut = new FileOutputStream(file);
        try {
            final HSSFWorkbook wb = new HSSFWorkbook();
            final HSSFSheet sheet = wb.createSheet("Surcouts");

            this.builders.get(type).build(essai, datas, sheet, wb);
            wb.write(fileOut);
        } finally {
            if (fileOut != null) {
                fileOut.close();
            }
        }

        return file;

    }

    /**
     * @param essai - l'essai pour lequel ou souhaite générer la grille de
     * surcoûts
     * @param type le type d'essai
     * @return le répertoire permettant de stocker le fichier de surcoûts pour
     * l'essai spécifié.
     */
    private File buildEssaiDirectory(final Essai essai,
                                     final TypeCalcul type) {
        final File directory = new File(new File(this.documentsDirectory, String.valueOf(essai.getId())), type.getRepertoire());

        final boolean result = directory.mkdirs();
        if (!result && this.log.isWarnEnabled()) {
            this.log.warn("Echec de création du répertoire de stockage des fichiers : " + directory.getAbsolutePath());
        }
        return directory;
    }

    /**
     * Setter pour builders.
     * @param builders le builders à écrire.
     */
    public void setBuilders(final Map<TypeCalcul, SheetBuilder> builders) {
        this.builders = builders;
    }

    /**
     * Setter pour documentsDirectory.
     * @param documentsDirectory le documentsDirectory à écrire.
     */
    public void setDocumentsDirectory(final File documentsDirectory) {
        this.documentsDirectory = documentsDirectory;
    }
}

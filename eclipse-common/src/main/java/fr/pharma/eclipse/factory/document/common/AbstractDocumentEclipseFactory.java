package fr.pharma.eclipse.factory.document.common;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.commons.lang.StringUtils;

import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.utils.Utils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import fr.pharma.eclipse.utils.file.FileHelper;

/**
 * Fabrique abstraite de documents Eclipse.
 * @param <DOC> Type de document Eclipse créé.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public abstract class AbstractDocumentEclipseFactory<DOC extends DocumentEclipse> implements Serializable {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -9048204467262894414L;

    /**
     * Helper pour la manipulation des fichiers.
     */
    private FileHelper fileHelper;

    /**
     * Méthode en charge d'initinialiser un document Eclipse à partir d'un
     * fichier.
     * @param document Document Eclipse à initialiser.
     * @param fichier Fichier.
     */
    public void initializeObject(final DOC document,
                                 final Fichier fichier) {
        document.setNomUtilisateur(this.formatNomUtilisateur(fichier));
        document.setNomDisque(this.makeNomDisque(document.getNomUtilisateur()));
    }

    /**
     * Méthode en charge de formater le nom utilisateur.
     * @param fichier Fichier importé par l'utilisateur.
     * @return Le nom de fichier formaté.
     */
    private String formatNomUtilisateur(final Fichier fichier) {
        String nom = fichier.getNom();
        nom = nom.replace(" ", EclipseConstants.UNDERSCORE);
        return nom;
    }

    /**
     * Méthode en charge de créer le nom du fichier sur le disque à partir du
     * nom utilisateur.
     * @param nomUtilisateur Nom utilisateur du document.
     * @return Le nom Disque du document.
     */
    private String makeNomDisque(final String nomUtilisateur) {
        final String extension = EclipseConstants.DOT.concat(this.fileHelper.getExtension(nomUtilisateur));
        final String nomSimple = StringUtils.removeEnd(nomUtilisateur, extension);
        final StringBuilder builder =
            new StringBuilder().append(nomSimple).append(EclipseConstants.UNDERSCORE)
                    .append(Utils.formatDate(Calendar.getInstance(EclipseConstants.LOCALE).getTime(), EclipseConstants.PATTERN_FILE_DISK)).append(extension);
        return builder.toString();
    }

    /**
     * Getter sur fileHelper.
     * @return Retourne le fileHelper.
     */
    protected FileHelper getFileHelper() {
        return this.fileHelper;
    }

    /**
     * Setter pour fileHelper.
     * @param fileHelper le fileHelper à écrire.
     */
    public void setFileHelper(final FileHelper fileHelper) {
        this.fileHelper = fileHelper;
    }

}

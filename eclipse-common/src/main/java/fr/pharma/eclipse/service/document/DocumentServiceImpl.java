package fr.pharma.eclipse.service.document;

import java.io.File;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.common.BeanParentDocument;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.service.common.impl.GenericServiceImpl;
import fr.pharma.eclipse.service.document.builder.FileDocumentBuilder;
import fr.pharma.eclipse.utils.file.FileHelper;

/**
 * Service pour la gestion des documents.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DocumentServiceImpl extends GenericServiceImpl<DocumentEclipse> implements DocumentService {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 694011218405182237L;

    /**
     * Générateur d'objets File correspondant à des documents.
     */
    private FileDocumentBuilder fileBuilder;

    /**
     * Helper pour la récupération d'un fichier.
     */
    private FileHelper fileHelper;

    /**
     * Constructeur.
     * @param genericDao Dao.
     */
    public DocumentServiceImpl(final GenericDao<DocumentEclipse> genericDao) {
        super(genericDao);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File getFile(final BeanParentDocument bean,
                        final DocumentEclipse doc) {
        return this.fileBuilder.build(bean, doc);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canBeDownloaded(final DocumentEclipse doc) {
        return doc != null && doc.getId() != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOnDisk(final BeanParentDocument bean,
                           final DocumentEclipse doc,
                           final Fichier fichier) {
        final File file = this.getFile(bean, doc); // sert à récupérer le chemin
        final String absPath = file.getAbsolutePath();
        final String directoryPath = absPath.substring(0, absPath.lastIndexOf(this.fileHelper.getSystemFileSeparator()));
        this.fileHelper.save(fichier, doc.getNomDisque(), directoryPath);
    }

    /**
     * Getter sur fileBuilder.
     * @return Retourne le fileBuilder.
     */
    FileDocumentBuilder getFileBuilder() {
        return this.fileBuilder;
    }

    /**
     * Setter pour fileBuilder.
     * @param fileBuilder le fileBuilder à écrire.
     */
    public void setFileBuilder(final FileDocumentBuilder fileBuilder) {
        this.fileBuilder = fileBuilder;
    }

    /**
     * Getter sur fileHelper.
     * @return Retourne le fileHelper.
     */
    FileHelper getFileHelper() {
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

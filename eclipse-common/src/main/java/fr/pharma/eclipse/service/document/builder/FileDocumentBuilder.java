package fr.pharma.eclipse.service.document.builder;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.util.Assert;

import fr.pharma.eclipse.domain.model.common.BeanParentDocument;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.service.document.resolver.DocEclipseFileNameResolver;
import fr.pharma.eclipse.utils.file.FileHelper;

/**
 * Classe en charge de fabriquer l'objet File correspondant à un document.
 
 * @version $Revision$ $Date$
 */
public class FileDocumentBuilder
    implements Serializable
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5916513096501530321L;

    /**
     * Liste des résolveurs de noms.
     */
    private List<DocEclipseFileNameResolver> resolvers;

    /**
     * Helper de File.
     */
    private FileHelper fileHelper;

    /**
     * Méthode en charge de créer l'objet File correspondant au document.
     * @param bean Bean porteur (ou à la base du document)
     * @param doc Document.
     * @return L'objet File correspondant.
     */
    public File build(final BeanParentDocument bean,
                      final DocumentEclipse doc)
    {
        final DocEclipseFileNameResolver resolver = this.getResolver(doc);
        Assert.notNull(resolver,
                       "Aucun résolveur n'est présent dans la liste pour le type "
                               + doc.getTypeDocument());
        final String filename = resolver.resolve(bean,
                                                 doc);
        return this.fileHelper.getFile(filename);
    }

    /**
     * Méthode en charge de récupérer, dans la liste des résolveurs,<br>
     * le résolveur pour un document particulier.
     * @param doc Document à résoudre.
     * @return Le résolveur associé.
     */
    private DocEclipseFileNameResolver getResolver(final DocumentEclipse doc)
    {
        final Predicate predicate = new Predicate() {

            @Override
            public boolean evaluate(final Object object)
            {
                return ((DocEclipseFileNameResolver) object).supports(doc);
            }
        };
        return (DocEclipseFileNameResolver) CollectionUtils.find(this.resolvers,
                                                                 predicate);
    }

    /**
     * Setter pour resolvers.
     * @param resolvers le resolvers à écrire.
     */
    public void setResolvers(final List<DocEclipseFileNameResolver> resolvers)
    {
        this.resolvers = resolvers;
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

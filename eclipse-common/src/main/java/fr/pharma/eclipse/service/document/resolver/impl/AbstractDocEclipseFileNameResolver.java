package fr.pharma.eclipse.service.document.resolver.impl;

import fr.pharma.eclipse.domain.enums.document.EnumTypeDocument;
import fr.pharma.eclipse.domain.model.common.BeanParentDocument;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.service.document.resolver.DocEclipseFileNameResolver;
import fr.pharma.eclipse.utils.constants.EclipseConstants;

/**
 * Classe de résolveur de filename de document Eclipse.
 
 * @version $Revision$ $Date$
 */
public abstract class AbstractDocEclipseFileNameResolver
    implements DocEclipseFileNameResolver
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 4281661647719474469L;

    /**
     * Chemin de stockage des documents sur le disque.
     */
    private final String documentsDirectory;

    /**
     * Constructeur.
     * @param documentsDirectory Chemin de stockage des documents sur le disque.
     */
    public AbstractDocEclipseFileNameResolver(final String documentsDirectory)
    {
        this.documentsDirectory = documentsDirectory;
    }

    /**
     * {@inheritDoc}
     */
    public boolean supports(final DocumentEclipse doc)
    {
        return doc.getTypeDocument() != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String resolve(final BeanParentDocument bean,
                          final DocumentEclipse doc)
    {
        // chemin : <base>/<dynamic_enumTypeDoc>/<dynamic_bean>/<dynamic_doc>/<doc.nomDique>
        final StringBuilder builder = new StringBuilder();
        builder.append(this.documentsDirectory); // <base>
        this.appendSeparator(builder);
        this.fillDynamicEnumTypeDoc(builder,
                                    doc.getTypeDocument()); // <dynamic_enumTypeDoc>
        this.appendSeparator(builder);
        this.fillDynamicBeanPart(builder,
                                 bean); // <dynamic_bean>
        // this.appendSeparator(builder);
        this.fillDynamicDocPart(builder,
                                doc); // <dynamic_doc>
        this.appendSeparator(builder);
        builder.append(doc.getNomDisque()); // <doc.nomDique>
        return builder.toString();
    }

    /**
     * Méthode qui rajoute au builder le séparateur.
     * @param builder Builder de fileName.
     */
    protected final void appendSeparator(final StringBuilder builder)
    {
        builder.append(EclipseConstants.SLASH);
    }

    /**
     * Méthode en charge d'ajouter au builder la partie fixe correspondant au répertoire de
     * l'énumération TypeDocumentEclipse rattachée au document.
     * @param builder Builder de fileName.
     * @param enumTypeDoc Type du document.
     */
    private void fillDynamicEnumTypeDoc(final StringBuilder builder,
                                        final EnumTypeDocument enumTypeDoc)
    {
        builder.append(enumTypeDoc.getTypeEclipse().getRepertoire());
    }

    /**
     * Méthode en charge de remplir la partie du chemin propre au bean.
     * @param builder Builder de filename.
     * @param bean Bean porteur du document.
     */
    protected void fillDynamicBeanPart(final StringBuilder builder,
                                       final BeanParentDocument bean)
    {
        builder.append(bean.getId());
    }

    /**
     * Méthode en charge de remplir la partie du chemin propre au document.
     * @param builder Builder de filename.
     * @param doc Document dont on cherche l'emplacement.
     */
    protected void fillDynamicDocPart(final StringBuilder builder,
                                      final DocumentEclipse doc)
    {

    }

    /**
     * Getter sur documentsDirectory.
     * @return Retourne le documentsDirectory.
     */
    String getDocumentsDirectory()
    {
        return this.documentsDirectory;
    }

}

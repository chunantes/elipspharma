package fr.pharma.eclipse.service.document.resolver.impl;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEclipse;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.domain.model.produit.DocumentProduit;

/**
 * Classe de résolveur de filename de document Produit.
 
 * @version $Revision$ $Date$
 */
public class DocProduitFileNameResolver
    extends AbstractDocEclipseFileNameResolver
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -153590398836544576L;

    /**
     * Constructeur.
     * @param documentsDirectory Chemin de stockage des documents sur le disque.
     */
    public DocProduitFileNameResolver(final String documentsDirectory)
    {
        super(documentsDirectory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(final DocumentEclipse doc)
    {
        return super.supports(doc)
               && TypeDocumentEclipse.PRODUIT.equals(doc.getTypeDocument().getTypeEclipse());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillDynamicDocPart(final StringBuilder builder,
                                      final DocumentEclipse doc)
    {
        super.fillDynamicDocPart(builder,
                                 doc);
        super.appendSeparator(builder);
        final DocumentProduit docProduit = (DocumentProduit) doc; // car supports
        builder.append(docProduit.getType().getRepertoire());
    }
}

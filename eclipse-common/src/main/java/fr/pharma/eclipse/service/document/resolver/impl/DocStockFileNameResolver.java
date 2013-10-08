package fr.pharma.eclipse.service.document.resolver.impl;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEclipse;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.domain.model.stock.DocumentStock;

/**
 * Classe de résolveur de filename de document Appro.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DocStockFileNameResolver extends AbstractDocEclipseFileNameResolver {
    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 5031704699345251368L;

    /**
     * Constructeur.
     * @param documentsDirectory Chemin de stockage des documents sur le disque.
     */
    public DocStockFileNameResolver(final String documentsDirectory) {
        super(documentsDirectory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(final DocumentEclipse doc) {
        return super.supports(doc) && TypeDocumentEclipse.MVT_STOCK.equals(doc.getTypeDocument().getTypeEclipse());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillDynamicDocPart(final StringBuilder builder,
                                      final DocumentEclipse doc) {
        super.fillDynamicDocPart(builder, doc);
        super.appendSeparator(builder);
        final DocumentStock docStock = (DocumentStock) doc; // car supports
        builder.append(docStock.getType().getRepertoire());
    }
}

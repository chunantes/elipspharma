package fr.pharma.eclipse.service.document.resolver.impl;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentEclipse;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.domain.model.essai.DocumentEssai;

/**
 * Classe de résolveur de filename de document Essai.
 
 * @version $Revision$ $Date$
 */
public class DocEssaiFileNameResolver
    extends AbstractDocEclipseFileNameResolver
{

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5525428770527317309L;

    /**
     * Constructeur.
     * @param documentsDirectory Chemin de stockage des documents sur le disque.
     */
    public DocEssaiFileNameResolver(final String documentsDirectory)
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
               && TypeDocumentEclipse.ESSAI.equals(doc.getTypeDocument().getTypeEclipse());
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
        final DocumentEssai docEssai = (DocumentEssai) doc; // car supports
        builder.append(docEssai.getType().getRepertoire());
    }
}

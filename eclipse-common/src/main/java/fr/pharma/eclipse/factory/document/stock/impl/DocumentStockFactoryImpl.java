package fr.pharma.eclipse.factory.document.stock.impl;

import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.domain.model.stock.DocumentStock;
import fr.pharma.eclipse.domain.model.stock.MvtStock;
import fr.pharma.eclipse.factory.common.BeanObjectWithParentFactory;
import fr.pharma.eclipse.factory.document.common.AbstractDocumentEclipseFactory;
import fr.pharma.eclipse.factory.document.stock.DocumentStockFactory;
import fr.pharma.eclipse.utils.file.FileHelper;

/**
 * Fabrique de documents de Stock.
 * @param <DOC> Type de document de Stock.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DocumentStockFactoryImpl<DOC extends DocumentStock> extends AbstractDocumentEclipseFactory<DOC> implements DocumentStockFactory<DOC> {
    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -6420237485005838646L;

    /**
     * Fabrique d'objet avec parent.
     */
    private BeanObjectWithParentFactory<DOC, MvtStock> factoryWithParent;

    /**
     * {@inheritDoc}
     */
    @Override
    public DOC getInitializedObject(final Fichier fichier,
                                    final MvtStock mvtStock) {
        final DOC doc = this.factoryWithParent.getInitializedObject(mvtStock);
        super.initializeObject(doc, fichier);
        return doc;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected FileHelper getFileHelper() {
        return super.getFileHelper();
    }

    /**
     * Setter pour factoryWithParent.
     * @param factoryWithParent Le factoryWithParent à écrire.
     */
    public void setFactoryWithParent(final BeanObjectWithParentFactory<DOC, MvtStock> factoryWithParent) {
        this.factoryWithParent = factoryWithParent;
    }

}

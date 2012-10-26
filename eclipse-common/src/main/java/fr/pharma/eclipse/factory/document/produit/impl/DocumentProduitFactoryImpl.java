package fr.pharma.eclipse.factory.document.produit.impl;

import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.domain.model.produit.DocumentProduit;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.factory.common.BeanObjectWithParentFactory;
import fr.pharma.eclipse.factory.document.common.AbstractDocumentEclipseFactory;
import fr.pharma.eclipse.factory.document.produit.DocumentProduitFactory;
import fr.pharma.eclipse.utils.file.FileHelper;

/**
 * Fabrique de documents de Produit.
 * @param <DOC> Type de document de Produit créé.
 
 * @version $Revision$ $Date$
 */
public class DocumentProduitFactoryImpl<DOC extends DocumentProduit>
    extends AbstractDocumentEclipseFactory<DOC>
    implements DocumentProduitFactory<DOC>
{

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -6420237485005838646L;

    /**
     * Fabrique d'objet avec parent.
     */
    private BeanObjectWithParentFactory<DOC, Produit> factoryWithParent;

    /**
     * {@inheritDoc}
     */
    @Override
    public DOC getInitializedObject(final Fichier fichier,
                                    final Produit produit)
    {
        final DOC doc = this.factoryWithParent.getInitializedObject(produit);
        super.initializeObject(doc,
                               fichier);
        return doc;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected FileHelper getFileHelper()
    {
        return super.getFileHelper();
    }

    /**
     * Setter pour factoryWithParent.
     * @param factoryWithParent le factoryWithParent à écrire.
     */
    public void setFactoryWithParent(final BeanObjectWithParentFactory<DOC, Produit> factoryWithParent)
    {
        this.factoryWithParent = factoryWithParent;
    }

}

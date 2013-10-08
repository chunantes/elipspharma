package fr.pharma.eclipse.factory.document.essai.impl;

import fr.pharma.eclipse.domain.model.common.BeanObject;
import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.domain.model.essai.DocumentEssai;
import fr.pharma.eclipse.factory.common.BeanObjectWithParentFactory;
import fr.pharma.eclipse.factory.document.common.AbstractDocumentEclipseFactory;
import fr.pharma.eclipse.factory.document.essai.DocumentEssaiFactory;
import fr.pharma.eclipse.utils.file.FileHelper;

/**
 * Fabrique de documents d'essais.
 * @param <DOC> Type de document d'essai créé.
 * @param <PARENT> Type de l'objet porteur du document.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DocumentEssaiFactoryImpl<DOC extends DocumentEssai, PARENT extends BeanObject> extends AbstractDocumentEclipseFactory<DOC> implements
    DocumentEssaiFactory<DOC, PARENT> {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1860916772744529865L;

    /**
     * Fabrique d'objet avec parent.
     */
    private BeanObjectWithParentFactory<DOC, PARENT> factoryWithParent;

    /**
     * {@inheritDoc}
     */
    @Override
    public DOC getInitializedObject(final PARENT parent,
                                    final Fichier fichier,
                                    final String commentaire) {
        final DOC doc = this.factoryWithParent.getInitializedObject(parent);
        super.initializeObject(doc, fichier);
        doc.setCommentaire(commentaire);
        return doc;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DOC getInitializedObject(final PARENT parent,
                                    final Fichier fichier) {
        final DOC doc = this.factoryWithParent.getInitializedObject(parent);
        super.initializeObject(doc, fichier);
        return doc;
    }

    /**
     * Setter pour factoryWithParent.
     * @param factoryWithParent le factoryWithParent à écrire.
     */
    public void setFactoryWithParent(final BeanObjectWithParentFactory<DOC, PARENT> factoryWithParent) {
        this.factoryWithParent = factoryWithParent;
    }

    /**
     * Getter sur factoryWithParent.
     * @return Retourne le factoryWithParent.
     */
    BeanObjectWithParentFactory<DOC, PARENT> getFactoryWithParent() {
        return this.factoryWithParent;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected FileHelper getFileHelper() {
        return super.getFileHelper();
    }

}

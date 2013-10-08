package fr.pharma.eclipse.factory.document.pharmacien.impl;

import fr.pharma.eclipse.domain.model.acteur.Pharmacien;
import fr.pharma.eclipse.domain.model.acteur.document.DocumentPharmacien;
import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.factory.common.BeanObjectWithParentFactory;
import fr.pharma.eclipse.factory.document.common.AbstractDocumentEclipseFactory;
import fr.pharma.eclipse.factory.document.pharmacien.DocumentPharmacienFactory;
import fr.pharma.eclipse.utils.file.FileHelper;

/**
 * Fabrique de documents de Produit.
 * @param <DOC> Type de document de Produit créé.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DocumentPharmacienFactoryImpl<DOC extends DocumentPharmacien> extends AbstractDocumentEclipseFactory<DOC> implements DocumentPharmacienFactory<DOC> {

    /**
     * SerialVersionUID.
     */
    private static final long serialVersionUID = -6420237485005838646L;

    /**
     * Fabrique d'objet avec parent.
     */
    private BeanObjectWithParentFactory<DOC, Pharmacien> factoryWithParent;

    /**
     * {@inheritDoc}
     */
    @Override
    public DOC getInitializedObject(final Fichier fichier,
                                    final Pharmacien pharmacien) {
        final DOC doc = this.factoryWithParent.getInitializedObject(pharmacien);
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
     * @param factoryWithParent le factoryWithParent à écrire.
     */
    public void setFactoryWithParent(final BeanObjectWithParentFactory<DOC, Pharmacien> factoryWithParent) {
        this.factoryWithParent = factoryWithParent;
    }

}

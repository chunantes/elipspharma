package fr.pharma.eclipse.factory.document.produit.impl;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.util.StringUtils;

import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.domain.model.produit.DispositifMedical;
import fr.pharma.eclipse.domain.model.produit.Produit;
import fr.pharma.eclipse.domain.model.produit.document.DocumentRandomisation;
import fr.pharma.eclipse.factory.common.BeanObjectWithParentFactory;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.DocumentsUtils;
import fr.pharma.eclipse.utils.file.FileHelper;

/**
 * Test de la fabrique DocumentEssaiFactoryImpl.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DocumentPharmacienFactoryImplTest extends AbstractEclipseJUnitTest {
    /**
     * Fabrique testée.
     */
    private DocumentProduitFactoryImpl<DocumentRandomisation> factory;

    /**
     * Helper de File mocké.
     */
    private FileHelper mockedFileHelper;

    /**
     * Fabrique avec parent mockée.
     */
    private BeanObjectWithParentFactory<DocumentRandomisation, Produit> mockedBeanObjWithParentFactory;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setUp() {
        this.mockedBeanObjWithParentFactory = Mockito.mock(BeanObjectWithParentFactory.class);
        this.mockedFileHelper = Mockito.mock(FileHelper.class);
        this.factory = new DocumentProduitFactoryImpl<DocumentRandomisation>();
        this.factory.setFactoryWithParent(this.mockedBeanObjWithParentFactory);
        this.factory.setFileHelper(this.mockedFileHelper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown() {
        this.factory = null;
        this.mockedBeanObjWithParentFactory = null;
        this.mockedFileHelper = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void testInit() {
        Assert.assertNotNull(this.factory);
        Assert.assertEquals(this.mockedFileHelper, this.factory.getFileHelper());
    }

    /**
     * Test de la méthode getInitializedObject.
     */
    @Test
    public void testGetInitializedObject() {
        final String nomFichier = "document.pdf";

        final Produit produit = new DispositifMedical();
        final Fichier fichier = new Fichier();
        fichier.setNom(nomFichier);
        final DocumentRandomisation expectedDoc = DocumentsUtils.makeDocumentRandomisationTest(1);

        Mockito.when(this.mockedBeanObjWithParentFactory.getInitializedObject(Matchers.any(DispositifMedical.class))).thenReturn(expectedDoc);
        Mockito.when(this.mockedFileHelper.getExtension(nomFichier)).thenReturn("pdf");

        final DocumentRandomisation actualDoc = this.factory.getInitializedObject(fichier, produit);

        Mockito.verify(this.mockedBeanObjWithParentFactory).getInitializedObject(Matchers.any(DispositifMedical.class));
        Mockito.verify(this.mockedFileHelper).getExtension(nomFichier);
        Assert.assertEquals(expectedDoc, actualDoc);
        Assert.assertEquals(nomFichier, actualDoc.getNomUtilisateur());
        Assert.assertTrue(StringUtils.hasText(actualDoc.getNomDisque()));
        Assert.assertTrue(actualDoc.getNomDisque().startsWith("document_"));
        Assert.assertTrue(actualDoc.getNomDisque().endsWith(".pdf"));
    }

}

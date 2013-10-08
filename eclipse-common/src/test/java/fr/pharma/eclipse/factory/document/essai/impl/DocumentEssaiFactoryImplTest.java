package fr.pharma.eclipse.factory.document.essai.impl;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.util.StringUtils;

import fr.pharma.eclipse.domain.model.common.Fichier;
import fr.pharma.eclipse.domain.model.essai.Essai;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.DetailAdministratif;
import fr.pharma.eclipse.domain.model.essai.detail.administratif.document.DocumentAutoriteCompetente;
import fr.pharma.eclipse.factory.common.BeanObjectWithParentFactory;
import fr.pharma.eclipse.utils.AbstractEclipseJUnitTest;
import fr.pharma.eclipse.utils.DocumentsUtils;
import fr.pharma.eclipse.utils.EssaiUtils;
import fr.pharma.eclipse.utils.file.FileHelper;

/**
 * Test de la fabrique DocumentEssaiFactoryImpl.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DocumentEssaiFactoryImplTest extends AbstractEclipseJUnitTest {
    /**
     * Fabrique testée.
     */
    private DocumentEssaiFactoryImpl<DocumentAutoriteCompetente, DetailAdministratif> factory;

    /**
     * Helper de File mocké.
     */
    private FileHelper mockedFileHelper;

    /**
     * Fabrique avec parent mockée.
     */
    private BeanObjectWithParentFactory<DocumentAutoriteCompetente, DetailAdministratif> mockedBeanObjWithParentFactory;

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void setUp() {
        this.mockedBeanObjWithParentFactory = Mockito.mock(BeanObjectWithParentFactory.class);
        this.mockedFileHelper = Mockito.mock(FileHelper.class);
        this.factory = new DocumentEssaiFactoryImpl<DocumentAutoriteCompetente, DetailAdministratif>();
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
        Assert.assertEquals(this.mockedBeanObjWithParentFactory, this.factory.getFactoryWithParent());
        Assert.assertEquals(this.mockedFileHelper, this.factory.getFileHelper());
    }

    /**
     * Test de la méthode getInitializedObject.
     */
    @Test
    public void testGetInitializedObject() {
        final String expectedCommentaire = "mon commentaire de document";
        final String nomFichier = "document avec espaces.pdf";
        final String expectedNomUtilisateur = nomFichier.replace(" ", "_");

        final Essai essai = EssaiUtils.makeEssaiTest(1);
        final Fichier fichier = new Fichier();
        fichier.setNom(nomFichier);
        final DocumentAutoriteCompetente expectedDoc = DocumentsUtils.makeDocumentACTest(1);

        Mockito.when(this.mockedBeanObjWithParentFactory.getInitializedObject(essai.getDetailAdministratif())).thenReturn(expectedDoc);
        Mockito.when(this.mockedFileHelper.getExtension(expectedNomUtilisateur)).thenReturn("pdf");

        final DocumentAutoriteCompetente actualDoc = this.factory.getInitializedObject(essai.getDetailAdministratif(), fichier, expectedCommentaire);

        Mockito.verify(this.mockedBeanObjWithParentFactory).getInitializedObject(essai.getDetailAdministratif());
        Mockito.verify(this.mockedFileHelper).getExtension(expectedNomUtilisateur);
        Assert.assertEquals(expectedDoc, actualDoc);
        Assert.assertEquals(expectedCommentaire, actualDoc.getCommentaire());
        Assert.assertEquals(expectedNomUtilisateur, actualDoc.getNomUtilisateur());
        Assert.assertTrue(StringUtils.hasText(actualDoc.getNomDisque()));
        Assert.assertTrue(actualDoc.getNomDisque().startsWith("document_"));
        Assert.assertTrue(actualDoc.getNomDisque().endsWith(".pdf"));
    }
}

package fr.pharma.eclipse.service.document.resolver.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.domain.model.stock.Approvisionnement;
import fr.pharma.eclipse.utils.DocumentsUtils;

/**
 * Test de la classe DocStockFileNameResolver.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DocStockFileNameResolverTest {
    /**
     * Résolveur testé.
     */
    private DocStockFileNameResolver resolver;

    /**
     * Répertoire de base de stockage des documents Eclipse.
     */
    private static final String DOCS_DIRECTORY = "C://temp";

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void setUp() {
        this.resolver = new DocStockFileNameResolver(DocStockFileNameResolverTest.DOCS_DIRECTORY);
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown() {
        this.resolver = null;
    }

    /**
     * Test de la méthode supports.
     */
    @Test
    public void testSupports() {
        Assert.assertTrue(this.resolver.supports(DocumentsUtils.makeDocumentApproTest(1)));
    }

    /**
     * Test de la méthode resolve.
     */
    @Test
    public void testResolve() {
        final String nomDisque = "document_20101126101530.pdf";
        final String expectedRes = DocStockFileNameResolverTest.DOCS_DIRECTORY + "//mvtsStock//1//appro//document_20101126101530.pdf";
        long nextId = 1;
        final Approvisionnement bean = new Approvisionnement();
        bean.setId(1L);
        final DocumentEclipse doc = DocumentsUtils.makeDocumentApproTest(nextId++);
        doc.setNomDisque(nomDisque);
        final String res = this.resolver.resolve(bean, doc);
        Assert.assertEquals(expectedRes, res);
    }

}

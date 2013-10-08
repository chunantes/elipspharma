package fr.pharma.eclipse.service.document.resolver.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.common.BeanParentDocument;
import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.utils.DocumentsUtils;
import fr.pharma.eclipse.utils.EssaiUtils;

/**
 * Test de la classe DocEssaiFileNameResolver.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DocEssaiFileNameResolverTest {
    /**
     * Résolveur testé.
     */
    private DocEssaiFileNameResolver resolver;

    /**
     * Répertoire de base de stockage des documents Eclipse.
     */
    private static final String DOCS_DIRECTORY = "C://temp";

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void setUp() {
        this.resolver = new DocEssaiFileNameResolver(DocEssaiFileNameResolverTest.DOCS_DIRECTORY);
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
        Assert.assertTrue(this.resolver.supports(DocumentsUtils.makeDocumentACTest(1)));
    }

    /**
     * Test de la méthode resolve.
     */
    @Test
    public void testResolve() {
        final String nomDisque = "document_20101126101530.pdf";
        final String expectedRes = DocEssaiFileNameResolverTest.DOCS_DIRECTORY + "//essais//1//ac//document_20101126101530.pdf";
        long nextId = 1;
        final BeanParentDocument bean = EssaiUtils.makeEssaiTest(nextId++);
        final DocumentEclipse doc = DocumentsUtils.makeDocumentACTest(nextId++);
        doc.setNomDisque(nomDisque);
        final String res = this.resolver.resolve(bean, doc);
        Assert.assertEquals(expectedRes, res);
    }

}

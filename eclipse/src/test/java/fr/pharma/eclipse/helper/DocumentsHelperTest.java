package fr.pharma.eclipse.helper;

import java.text.ParseException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.common.DocumentEclipse;
import fr.pharma.eclipse.utils.DocumentsUtils;
import fr.pharma.eclipse.utils.Utils;

/**
 * Test de la classe DocumentsHelper.
 
 * @version $Revision$ $Date$
 */
public class DocumentsHelperTest
{
    /**
     * Helper testé.
     */
    private DocumentsHelper helper;

    /**
     * Méthode d'initialisation.
     */
    @Before
    public void setUp()
    {
        this.helper = new DocumentsHelper();
    }

    /**
     * Méthode de finalisation.
     */
    @After
    public void tearDown()
    {
        this.helper = null;
    }

    /**
     * Test de la méthode getLastDocTitle.
     * @throws ParseException Erreur de préparation des données.
     */
    @Test
    public void testGetLastDocTitle()
        throws ParseException
    {
        final DocumentEclipse doc = DocumentsUtils.makeDocumentACTest(1);
        doc.setDateMaj(Utils.parseDate("11/11/2010",
                                       "dd/MM/yyyy"));
        doc.setMajPar("netapsys");
        final String expectedTitle = "Ajouté le 11/11/2010 par netapsys";
        final String actualTitle = this.helper.buildDocTitle(doc,
                                                             "Ajouté le",
                                                             "par",
                                                             "dd/MM/yyyy");
        Assert.assertEquals(expectedTitle,
                            actualTitle);
    }

}

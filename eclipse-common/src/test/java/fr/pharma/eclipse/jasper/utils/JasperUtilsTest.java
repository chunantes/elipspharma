package fr.pharma.eclipse.jasper.utils;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import fr.pharma.eclipse.domain.model.acteur.Investigateur;
import fr.pharma.eclipse.jasper.constants.JasperConstants;
import fr.pharma.eclipse.utils.PersonneUtils;
import fr.pharma.eclipse.utils.constants.EclipseConstants;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Test de la classe utilitaire JasperUtils.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class JasperUtilsTest {

    /**
     * Test de la méthode getKey.
     */
    @Test
    public void testGetKey() {
        final String jasperParamName = "myParameter";
        final String key = JasperUtils.getKey(jasperParamName);
        Assert.assertEquals(JasperConstants.JASPER_PREFIX.concat(jasperParamName), key);
    }

    /**
     * Test de la méthode getParameterName.
     */
    @Test
    public void testGetParameterName() {
        final String expectedParamName = "myParamName";
        final String jasperKey = JasperConstants.JASPER_PREFIX + expectedParamName;
        final String paramName = JasperUtils.getParameterName(jasperKey);
        Assert.assertEquals(expectedParamName, paramName);
    }

    /**
     * Test de la méthode getParameterName - préfixe invalide.
     */
    @Test
    public void testGetParameterNamePrefixeInvalide() {
        final String expectedParamName = "myParamName";
        final String jasperKey = expectedParamName;
        final String paramName = JasperUtils.getParameterName(jasperKey);
        Assert.assertNull(paramName);
    }

    /**
     * Test de la méthode formatterListeStrings.
     */
    @Test
    public void testFormatterListeStrings() {
        final Collection<String> listeLibelles = Arrays.asList("el1", "el2", "el3");
        final String expectedRes = "el1; el2; el3";
        final String res = JasperUtils.formatterListeStrings(listeLibelles);
        Assert.assertEquals(expectedRes, res);
    }

    /**
     * Test de la méthode createJRDataSource.
     */
    @Test
    public void testCreateJRDataSource() {
        final Collection<? extends Object> listData = Arrays.asList("el1", "el2", "el3");
        final JRBeanCollectionDataSource res = JasperUtils.createJRDataSource(listData);
        Assert.assertEquals(3, res.getRecordCount());
    }

    /**
     * Test de la méthode makeLibelleInvestigateur.
     */
    @Test
    public void testMakeLibelleInvestigateur() {
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, JasperUtils.makeLibelleInvestigateur(null));

        final Investigateur investigateur = PersonneUtils.makeInvestigateur(1);
        investigateur.setTitre("professeur");
        investigateur.setPrenom("jean");
        investigateur.setNom("valjean");

        Assert.assertEquals("professeur jean valjean", JasperUtils.makeLibelleInvestigateur(investigateur));
    }

    /**
     * Test de la méthode transformToOuiNon.
     */
    @Test
    public void testTransformToOuiNon() {
        Assert.assertEquals(JasperConstants.DEFAULT_FIELD_VALUE, JasperUtils.transformToOuiNon(null));
        Assert.assertEquals(EclipseConstants.OUI, JasperUtils.transformToOuiNon(true));
        Assert.assertEquals(EclipseConstants.NON, JasperUtils.transformToOuiNon(false));
    }

}

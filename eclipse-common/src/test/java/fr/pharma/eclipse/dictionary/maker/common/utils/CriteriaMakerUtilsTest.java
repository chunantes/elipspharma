package fr.pharma.eclipse.dictionary.maker.common.utils;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Classe en charge de tester l'utilitaire de maker de critère de recherche.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class CriteriaMakerUtilsTest {
    /**
     * Méthode en charge de tester la transformation de chaîne.
     */
    @Test
    public void testTransformForSql() {
        Assert.assertEquals("Check for wildcards escaping", "%\\*%", CriteriaMakerUtils.transformForPostgreSqlSimilarTo("*"));
        Assert.assertEquals("Check for plus symbol escaping", "%\\+%", CriteriaMakerUtils.transformForPostgreSqlSimilarTo("+"));
        Assert.assertEquals("Check for pipes escaping", "%\\|%", CriteriaMakerUtils.transformForPostgreSqlSimilarTo("|"));
        Assert.assertEquals("Check for parentheses escaping", "%\\(%", CriteriaMakerUtils.transformForPostgreSqlSimilarTo("("));
        Assert.assertEquals("Check for parentheses escaping", "%\\)%", CriteriaMakerUtils.transformForPostgreSqlSimilarTo(")"));
        Assert.assertEquals("Check for brackets escaping", "%\\[%", CriteriaMakerUtils.transformForPostgreSqlSimilarTo("["));
        Assert.assertEquals("Check for brackets escaping", "%\\]%", CriteriaMakerUtils.transformForPostgreSqlSimilarTo("]"));

        Assert.assertEquals("%[AÀÄÂaàäâ][EÉÈÊËeéèêë][IÎÏiîï][OÔÖoôö][UÛÜuûü]Y\\|\\*\\+\\(\\)\\[\\]%", CriteriaMakerUtils.transformForPostgreSqlSimilarTo("aeiouy|*+()[]"));
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du A.
     */
    @Test
    public void testHandleA1() {
        final String retour = CriteriaMakerUtils.handleA('A');
        Assert.assertEquals("[AÀÄÂaàäâ]", retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du À.
     */
    @Test
    public void testHandleA2() {
        final String retour = CriteriaMakerUtils.handleA('À');
        Assert.assertEquals("[AÀÄÂaàäâ]", retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du Ä.
     */
    @Test
    public void testHandleA3() {
        final String retour = CriteriaMakerUtils.handleA('Ä');
        Assert.assertEquals("[AÀÄÂaàäâ]", retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du Â.
     */
    @Test
    public void testHandleA4() {
        final String retour = CriteriaMakerUtils.handleA('Â');
        Assert.assertEquals("[AÀÄÂaàäâ]", retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du A KO.
     */
    @Test
    public void testHandleAKO() {
        final String retour = CriteriaMakerUtils.handleA('x');
        Assert.assertEquals(StringUtils.EMPTY, retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du E.
     */
    @Test
    public void testHandleE1() {
        final String retour = CriteriaMakerUtils.handleE('E');
        Assert.assertEquals("[EÉÈÊËeéèêë]", retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du É.
     */
    @Test
    public void testHandleE2() {
        final String retour = CriteriaMakerUtils.handleE('É');
        Assert.assertEquals("[EÉÈÊËeéèêë]", retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du È.
     */
    @Test
    public void testHandleE3() {
        final String retour = CriteriaMakerUtils.handleE('È');
        Assert.assertEquals("[EÉÈÊËeéèêë]", retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du Ê.
     */
    @Test
    public void testHandleE4() {
        final String retour = CriteriaMakerUtils.handleE('Ê');
        Assert.assertEquals("[EÉÈÊËeéèêë]", retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du Ë.
     */
    @Test
    public void testHandleE5() {
        final String retour = CriteriaMakerUtils.handleE('Ë');
        Assert.assertEquals("[EÉÈÊËeéèêë]", retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du E KO.
     */
    @Test
    public void testHandleEKO() {
        final String retour = CriteriaMakerUtils.handleE('x');
        Assert.assertEquals(StringUtils.EMPTY, retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du I.
     */
    @Test
    public void testHandleI1() {
        final String retour = CriteriaMakerUtils.handleI('I');
        Assert.assertEquals("[IÎÏiîï]", retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du Î.
     */
    @Test
    public void testHandleI2() {
        final String retour = CriteriaMakerUtils.handleI('Î');
        Assert.assertEquals("[IÎÏiîï]", retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du Ï.
     */
    @Test
    public void testHandleI3() {
        final String retour = CriteriaMakerUtils.handleI('Ï');
        Assert.assertEquals("[IÎÏiîï]", retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du I KO.
     */
    @Test
    public void testHandleIKO() {
        final String retour = CriteriaMakerUtils.handleI('x');
        Assert.assertEquals(StringUtils.EMPTY, retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du O.
     */
    @Test
    public void testHandleO1() {
        final String retour = CriteriaMakerUtils.handleO('O');
        Assert.assertEquals("[OÔÖoôö]", retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du Ô.
     */
    @Test
    public void testHandleO2() {
        final String retour = CriteriaMakerUtils.handleO('Ô');
        Assert.assertEquals("[OÔÖoôö]", retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du Ö.
     */
    @Test
    public void testHandleO3() {
        final String retour = CriteriaMakerUtils.handleO('Ö');
        Assert.assertEquals("[OÔÖoôö]", retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du O KO.
     */
    @Test
    public void testHandleOKO() {
        final String retour = CriteriaMakerUtils.handleO('x');
        Assert.assertEquals(StringUtils.EMPTY, retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du U.
     */
    @Test
    public void testHandleU1() {
        final String retour = CriteriaMakerUtils.handleU('U');
        Assert.assertEquals("[UÛÜuûü]", retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du Û.
     */
    @Test
    public void testHandleU2() {
        final String retour = CriteriaMakerUtils.handleU('Û');
        Assert.assertEquals("[UÛÜuûü]", retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du Ü.
     */
    @Test
    public void testHandleU3() {
        final String retour = CriteriaMakerUtils.handleU('Ü');
        Assert.assertEquals("[UÛÜuûü]", retour);
    }

    /**
     * Méthode en charge de traiter la méthode de gestion du U KO.
     */
    @Test
    public void testHandleUKO() {
        final String retour = CriteriaMakerUtils.handleU('x');
        Assert.assertEquals(StringUtils.EMPTY, retour);
    }

}

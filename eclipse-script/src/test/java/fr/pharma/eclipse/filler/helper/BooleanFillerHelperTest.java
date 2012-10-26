package fr.pharma.eclipse.filler.helper;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test du helper BooleanFillerHelper.
 
 * @version $Revision$ $Date$
 */
public class BooleanFillerHelperTest
{
    /**
     * Helper à tester.
     */
    private BooleanFillerHelper helper;

    /**
     * SetUp().
     */
    @Before
    public void setUp()
    {
        this.helper = new BooleanFillerHelper();
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown()
    {
        this.helper = null;
    }

    /**
     * Test de la méthode checkTrue().
     */
    @Test
    public void testCheckTrue()
    {
        Assert.assertTrue(this.helper.checkTrue("Y"));
        Assert.assertFalse(this.helper.checkTrue("N"));
        Assert.assertFalse(this.helper.checkTrue("Nsdfs"));
        Assert.assertFalse(this.helper.checkTrue(null));
    }
}

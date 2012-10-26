package fr.pharma.eclipse.script;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Test des méthode de la classe ScriptParameterBuilder.
 
 * @version $Revision$ $Date$
 */
public class ScriptParameterBuilderTest
{
    /**
     * Builder à tester.
     */
    private ScriptParameterBuilder builder;

    /**
     * Initiailisation.
     */
    @Before
    public void setUp()
    {
        this.builder = new ScriptParameterBuilder();
    }

    /**
     * Test de la méthode getScriptParameter.
     */
    @Test
    public void testGetScriptParameterOk()
    {
        final String[] args = new String[2];

        try
        {
            this.builder.getScriptParameter(new String[0],
                                            Mockito.mock(ScriptParameter.class));
        }
        catch (final Exception e)
        {
            Assert.fail("Aucune exception n'aurait du être levée.");
        }

    }

}
